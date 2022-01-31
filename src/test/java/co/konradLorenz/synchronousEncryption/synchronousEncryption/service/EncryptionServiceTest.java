package co.konradLorenz.synchronousEncryption.synchronousEncryption.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

@SpringBootTest
public class EncryptionServiceTest {

    @Autowired
    private IEncryptionService encryptionService;
    private String text  = "Texto de prueba";

    @Test
    void EncryptionText() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        var encrypted = encryptionService.encryption(text);

        assertNotNull(encrypted);
    }

}
