package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.entities.Document;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.repository.DocumentRepository;
import br.ueg.personalsystem.repository.UserRepository;
import br.ueg.personalsystem.service.impl.UserService;
import br.ueg.personalsystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.util.Base64;

@RestController
@RequestMapping("${api.version}/signature")
public class SignatureController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping(path = "/sign")
    @Transactional
    public ResponseEntity<?> sign(@RequestPart(name = "file") MultipartFile file) throws Exception {
        User user = userRepository.getReferenceById(Util.getIdUserLogged());

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(file.getBytes());
        String hash = generateSHA256Hash(hashBytes);

        byte[] privateBytes = Base64.getDecoder().decode(user.getPrivateKey());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(file.getBytes());

        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setHash(hash);
        document.setSignature(Base64.getEncoder().encodeToString(signature.sign()));
        document.setSignedDate(LocalDate.now());
        document.setSignedUser(user);

        documentRepository.save(document);

        String signedName = adicionarSufixoAssinado(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(signedName, StandardCharsets.UTF_8) + "\"")
                .body(file.getBytes());
    }

    @GetMapping(path = "/verify-sign")
    public ResponseEntity<?> verifySign(@RequestPart(name = "file") MultipartFile file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(file.getBytes());
        String hash = generateSHA256Hash(hashBytes);

        if (documentRepository.existsDocumentByHash(hash)) {
            Document document = documentRepository.findByHash(hash);

            byte[] publicBytes = Base64.getDecoder().decode(document.getSignedUser().getPublicKey());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(file.getBytes());

            byte[] signatureBytes = Base64.getDecoder().decode(document.getSignature());
            if (signature.verify(signatureBytes)) {
                return ResponseEntity.ok().body("Documento assinado válido");
            } else {
                return ResponseEntity.badRequest().body("Documento foi corrompido e não está coerente com a assinatura");
            }
        } else {
            return ResponseEntity.badRequest().body("Documento não encontrado ou não assinado");
        }
    }

    private String generateSHA256Hash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return Base64.getEncoder().encodeToString(hash);
    }

    private String adicionarSufixoAssinado(String originalName) {
        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex == -1) {
            return originalName + "_assinado";
        }
        String baseName = originalName.substring(0, dotIndex);
        String extension = originalName.substring(dotIndex);
        return baseName + "_assinado" + extension;
    }

}
