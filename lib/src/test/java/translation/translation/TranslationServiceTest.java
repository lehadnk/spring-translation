package translation.translation;

import org.junit.jupiter.api.Test;
import translation.translation.business.bundle_providers.FrGettextResourceBundleProvider;
import translation.translation.business.TranslationManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationServiceTest {
    @Test
    public void testTranslateWithNonExistingLanguage() throws IOException {
        var translationService = this.createTranslationService();
        assertEquals("Hello", translationService.translate(Locale.ITALIAN.getISO3Language(), "Hello"));
    }

    @Test
    public void testTranslationWithExistingLanguageAndString() throws IOException {
        var translationService = this.createTranslationService();

        assertEquals("Bonjour", translationService.translate(Locale.FRANCE.getISO3Language(), "Hello"));
    }

    @Test
    public void testTranslateWithNonExistingKey() throws IOException {
        var translationService = this.createTranslationService();

        assertEquals("Test", translationService.translate(Locale.FRANCE.getISO3Language(), "Test"));
    }

    @Test
    public void testTranslationReturnsKeyWhenTranslationIsEmpty() throws IOException {
        var translationService = this.createTranslationService();

        assertEquals("Untranslated string", translationService.translate(Locale.FRANCE.getISO3Language(), "Untranslated string"));
    }

    private TranslationService createTranslationService() throws IOException {
        return new TranslationService(
                new TranslationManager(
                    List.of(
                            new FrGettextResourceBundleProvider()
                    )
                )
        );
    }
}
