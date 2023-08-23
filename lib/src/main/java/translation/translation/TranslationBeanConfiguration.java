package translation.translation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import translation.translation.business.TranslationManager;

import java.io.IOException;
import java.util.List;

@Configuration
public class TranslationBeanConfiguration {
    @Bean
    public TranslationFacadeSingleton createTranslationFacadeSingleton(
            TranslationService translationService,
            LocaleProviderInterface localeProviderInterface
    ) {
        return new TranslationFacadeSingleton(
                translationService,
                localeProviderInterface
        );
    }

    @Bean
    public TranslationService createTranslationService(
            List<GettextResourceBundleProviderInterface> gettextResourceBundleProviders
    ) throws IOException {
        return new TranslationService(
                new TranslationManager(gettextResourceBundleProviders)
        );
    }
}
