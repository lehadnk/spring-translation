package translation.translation;

import org.junit.jupiter.api.Test;
import translation.translation.bundle_providers.FrGettextResourceBundleProvider;
import translation.translation.business.TranslationManager;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationServiceTest {
    @Test
    public void testGetDefaultLanguage() throws IOException {
        var translationService = this.createTranslationService();
        assertEquals("en", translationService.getLanguage());
    }

    @Test
    public void testSetLanguage() throws IOException {
        var translationService = this.createTranslationService();

        translationService.setLanguage("fr");
        assertEquals("fr", translationService.getLanguage());
    }

    @Test
    public void testTranslateWithNonExistingLanguage() throws IOException {
        var translationService = this.createTranslationService();
        translationService.setLanguage("it");

        assertEquals("Hello", translationService.translate("Hello"));
    }

    @Test
    public void testTranslationWithExistingLanguageAndString() throws IOException {
        var translationService = this.createTranslationService();
        translationService.setLanguage("fr");

        assertEquals("Bonjour", translationService.translate("Hello"));
    }

    @Test
    public void testTranslateWithNonExistingKey() throws IOException {
        var translationService = this.createTranslationService();
        translationService.setLanguage("fr");

        assertEquals("Test", translationService.translate("Test"));
    }

    private TranslationService createTranslationService() throws IOException {
        return new TranslationService(
                new TranslationManager(
                    List.of(
                            new FrGettextResourceBundleProvider()
                    )
                ),
                "en"
        );
    }
}
