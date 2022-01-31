package co.konradLorenz.synchronousEncryption.synchronousEncryption.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface IEncryptionService {
    String encryption(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
}
