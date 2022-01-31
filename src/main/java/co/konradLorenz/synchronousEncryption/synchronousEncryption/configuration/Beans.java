package co.konradLorenz.synchronousEncryption.synchronousEncryption.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class Beans {

    @Bean
    public Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    @Bean
    public KeyPair getKeyPair() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        var publicKeyData = new FileInputStream(ResourceUtils.getFile("classpath:pubkey.der")).readAllBytes();
        var privateKeyData = new FileInputStream(ResourceUtils.getFile("classpath:pkcs8_prikey.der")).readAllBytes();

        var publicKeySpec = new X509EncodedKeySpec(publicKeyData);
        var privateKeySec = new PKCS8EncodedKeySpec(privateKeyData);

        var keyFactory = KeyFactory.getInstance("RSA");
        var publicKey = keyFactory.generatePublic(publicKeySpec);
        var privateKey = keyFactory.generatePrivate(privateKeySec);

        return new KeyPair(publicKey, privateKey);
    }

}
