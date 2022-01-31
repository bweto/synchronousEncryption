package co.konradLorenz.synchronousEncryption.synchronousEncryption.controller;

import co.konradLorenz.synchronousEncryption.synchronousEncryption.model.Text;
import co.konradLorenz.synchronousEncryption.synchronousEncryption.service.IEncryptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EncryptionController {

    private final IEncryptionService encryption;

    @PostMapping("/encryption")
    public ResponseEntity<Text> encryptedText(@RequestBody Text text) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return ResponseEntity.ok(new Text(encryption.encryption(text.getText())));
    }

}
