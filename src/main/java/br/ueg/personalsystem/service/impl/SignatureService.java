package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.base.exception.BusinessException;
import br.ueg.personalsystem.entities.Document;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.repository.DocumentRepository;
import br.ueg.personalsystem.service.ISignatureService;
import br.ueg.personalsystem.service.IUserService;
import br.ueg.personalsystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class SignatureService implements ISignatureService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private IUserService userService;

    @Override
    public byte[] sign(String filename, byte[] documentBytes) {
        try {
            User user = userService.getById(Util.getIdUserLogged());

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(documentBytes);

            byte[] privateBytes = Base64.getDecoder().decode(user.getPrivateKey());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(hashBytes);

            Document document = new Document();
            document.setName(filename);
            document.setHash(generateSHA256Hash(hashBytes));
            document.setSignature(Base64.getEncoder().encodeToString(signature.sign()));
            document.setSignedDate(LocalDateTime.now());
            document.setSignedUser(user);

            documentRepository.save(document);

            return documentBytes;
        } catch (Exception e) {
            throw new BusinessException(ErrorEnum.GENERAL_ERROR);
        }
    }

    @Override
    public String validateSignature(String filename, byte[] documentBytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(documentBytes);

            String hash = generateSHA256Hash(hashBytes);
            if (documentRepository.existsDocumentByHash(hash) || documentRepository.existsDocumentByName(filename)) {
                Document document = documentRepository.findFirstByHashOrderByIdDesc(hash);
                if (document == null) { document = documentRepository.findFirstByName(filename); }

                byte[] publicBytes = Base64.getDecoder().decode(document.getSignedUser().getPublicKey());
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
                PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);

                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initVerify(publicKey);
                signature.update(hashBytes);

                byte[] signatureBytes = Base64.getDecoder().decode(document.getSignature());
                if (signature.verify(signatureBytes)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    return "Documento assinado válido. Assinado por " + document.getSignedUser().getName() + " em " + document.getSignedDate().format(formatter);
                } else {
                    return "Documento foi corrompido e não está coerente com a assinatura";
                }
            } else {
                return "Documento não encontrado ou não assinado";
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorEnum.GENERAL_ERROR);
        }
    }

    private String generateSHA256Hash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return Base64.getEncoder().encodeToString(hash);
    }
}
