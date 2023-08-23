package translation.translation;

import org.junit.jupiter.api.Test;
import translation.translation.business.TranslationManager;
import translation.translation.business.bundle_providers.FrGettextResourceBundleProvider;
import translation.translation.business.locale_providers.MockLocaleProvider;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static translation.translation.TranslationFacadeSingleton.__;

public class TranslationFacadeSingletonTest {
    @Test
    public void testDoubleUnderscore() throws IOException {
        this.createTranslationFacadeSingleton();

        assertEquals("Bonjour", __("Hello"));
    }

    private void createTranslationFacadeSingleton() throws IOException {
        new TranslationFacadeSingleton(
                new TranslationService(
                        new TranslationManager(
                                List.of(
                                        new FrGettextResourceBundleProvider()
                                )
                        )
                ),
                new MockLocaleProvider(Locale.FRANCE)
        );
    }
}
