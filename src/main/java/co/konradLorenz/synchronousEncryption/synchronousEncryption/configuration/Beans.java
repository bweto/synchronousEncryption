package co.konradLorenz.synchronousEncryption.synchronousEncryption.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class Beans {

    @Value("classpath:pubkey.der")
    private Resource pub;

    @Value("classpath:pkcs8_prikey.der")
    private Resource priv;

    @Bean
    public KeyPair getKeyPair() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        var publicKeyData = pub.getInputStream().readAllBytes();
        var privateKeyData = priv.getInputStream().readAllBytes();

        var publicKeySpec = new X509EncodedKeySpec(publicKeyData);
        var privateKeySec = new PKCS8EncodedKeySpec(privateKeyData);

        var keyFactory = KeyFactory.getInstance("RSA");
        var publicKey = keyFactory.generatePublic(publicKeySpec);
        var privateKey = keyFactory.generatePrivate(privateKeySec);

        return new KeyPair(publicKey, privateKey);
    }

}
