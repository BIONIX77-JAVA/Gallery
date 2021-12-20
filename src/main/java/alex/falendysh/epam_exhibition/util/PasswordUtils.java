package alex.falendysh.epam_exhibition.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/*
 * PasswordUtils helps to encrypt user passwords during
 * registration process
 */
public class PasswordUtils {

    public static final int ITERATIONS = 1000;
    public static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateSalt(final int length) {
        byte[] salt = new byte[length];
        RANDOM.nextBytes(salt);
        return Optional.of(Base64.getEncoder().encodeToString(salt)).get();
    }

    public static String hashPassword(String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();
        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
        Arrays.fill(chars, Character.MIN_VALUE);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = factory.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword)).get();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword, String salt) {
        String encrypted = hashPassword(password, salt);
        if (encrypted == null) {
            return false;
        }
        return encrypted.equals(hashedPassword);
    }


}
