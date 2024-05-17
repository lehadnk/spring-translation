package translation.translation;

import org.junit.jupiter.api.Test;
import translation.translation.business.PlaceholderManager;
import translation.translation.business.TranslationManager;
import translation.translation.business.bundle_providers.FrGettextResourceBundleProvider;
import translation.translation.business.locale_providers.MockLocaleProvider;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static translation.translation.TranslationFacadeSingleton.__;

public class TranslationFacadeSingletonTest {
    @Test
    public void testDoubleUnderscore() throws IOException {
        this.createTranslationFacadeSingleton();

        assertEquals("Bonjour", __("Hello"));
    }

    @Test
    public void testDoubleUnderscoreWithPlaceholders() throws IOException {
        this.createTranslationFacadeSingleton();

        assertEquals("Vous avez 100 points!", __("You have %score% points!", Map.of("score", "100")));
    }

    @Test
    public void testPlaceholdersInKey() throws IOException {
        this.createTranslationFacadeSingleton();

        assertEquals("This is non-existing string", __("This is %type% string", Map.of("type", "non-existing")));
    }

    @Test
    public void testArgumentUnpacking() throws IOException {
        this.createTranslationFacadeSingleton();

        assertEquals("This is non-existing string", __("This is %type% string", "non-existing"));
    }

    private void createTranslationFacadeSingleton() throws IOException {
        new TranslationFacadeSingleton(
                new TranslationService(
                        new TranslationManager(
                                List.of(
                                        new FrGettextResourceBundleProvider()
                                )
                        ),
                        new PlaceholderManager()
                ),
                new MockLocaleProvider(Locale.FRANCE)
        );
    }
}
