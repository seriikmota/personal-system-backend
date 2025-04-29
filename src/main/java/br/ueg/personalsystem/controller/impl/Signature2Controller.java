package br.ueg.personalsystem.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@RestController
@RequestMapping("${api.version}/signature2")
public class Signature2Controller {

    @PostMapping(path = "/sign")
    @Transactional
    public ResponseEntity<?> sign(@RequestPart(name = "file") MultipartFile documentFile, @RequestPart(name = "privatekey") MultipartFile privatekeyFile) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(documentFile.getBytes());

        PrivateKey privateKey = getPrivateKeyFromFile(privatekeyFile);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] signature = cipher.doFinal(hash);

        return ResponseEntity.ok().body(Base64.getEncoder().encodeToString(signature));
    }

    @GetMapping(path = "/verify-sign")
    public ResponseEntity<?> verifySign(@RequestPart(name = "file") MultipartFile file, @RequestPart(name = "publickey") MultipartFile publickeyFile, @RequestPart(name = "signature") String signature) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(file.getBytes());

        PublicKey publicKey = getPublicKeyFromFile(publickeyFile);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedHash = cipher.doFinal(Base64.getDecoder().decode(signature));

        if (hash == decryptedHash) {
            return ResponseEntity.ok().body("Documento assinado válido");
        } else {
            return ResponseEntity.badRequest().body("Documento foi corrompido e não está coerente com a assinatura");
        }
    }

    private PrivateKey getPrivateKeyFromFile(MultipartFile file) throws Exception {
        String keyPem = new String(file.getBytes());
        String base64 = keyPem.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(base64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public PublicKey getPublicKeyFromFile(MultipartFile file) throws Exception {
        String keyPem = new String(file.getBytes());
        String base64 = keyPem.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(base64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

}