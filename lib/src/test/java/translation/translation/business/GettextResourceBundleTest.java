package translation.translation.business;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GettextResourceBundleTest {

    @Test
    public void testLoadStrings() throws IOException {
        GettextResourceBundle gettextResourceBundle = new GettextResourceBundle("bundles/fr_FR.po");
        assertEquals("Nous ne sommes pas en mesure de fournir la fonction de changement de mot de passe en raison de problèmes techniques de notre part. Veuillez réessayer plus tard.", gettextResourceBundle.getTranslation("We're unable to provide the change password function due to technical issues on our side. Please try again later."));
        assertEquals("Bonjour", gettextResourceBundle.getTranslation("Hello"));
        assertEquals("Au revoir", gettextResourceBundle.getTranslation("Bye"));
    }
}
