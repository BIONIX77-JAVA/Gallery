package alex.falendysh.epam_exhibition.util;

import alex.falendysh.epam_exhibition.exceptions.ImpossibleToReadPropertyFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static alex.falendysh.epam_exhibition.util.Constants.PathsConstants.APPLICATION_PROP;

public class FileUtils {

    public static Properties readProperties() {
        try (InputStream input = new FileInputStream(APPLICATION_PROP)) {

            Properties props = new Properties();

            /** load a properties file
             *
             */
            props.load(input);

            /** get the property value and print it out
             *
             */
            System.out.println(props.getProperty("db.url"));
            System.out.println(props.getProperty("db.user"));
            System.out.println(props.getProperty("db.password"));
            return props;

        } catch (IOException ex) {
            throw new ImpossibleToReadPropertyFile();
        }
    }
}
