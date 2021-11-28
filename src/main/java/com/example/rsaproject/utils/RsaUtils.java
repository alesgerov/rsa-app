package com.example.rsaproject.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class RsaUtils {

    public String encrypt(String secretMessage) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(secretMessageBytes);
    }

    public   String  decrypt(String s) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        byte[] decryptedMessageBytes=Base64.getDecoder().decode(s);
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }

}
