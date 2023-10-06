package pe.miempresa.booker.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static String getTemplate(String templatePath) {
        try {
            return IOUtils.toString((new ClassPathResource(templatePath)).getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException var2) {
            throw new RuntimeException(var2);
        }
    }
}
