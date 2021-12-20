package alex.falendysh.epam_exhibition.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    private PasswordEncryptor() {
    }

    public static String encrypt(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = messageDigest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : result) {
            stringBuffer
                    .append(Integer.toString((b & 0xff) + 0x100, 16)
                            .substring(1));
        }
        return stringBuffer.toString();
    }

}
