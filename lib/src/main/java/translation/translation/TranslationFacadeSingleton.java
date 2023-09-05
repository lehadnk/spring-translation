package translation.translation;

import java.util.Map;

public class TranslationFacadeSingleton {
    public static TranslationFacadeSingleton instance;
    private final TranslationService translationService;
    private final LocaleProviderInterface localeProvider;

    public TranslationFacadeSingleton(
            TranslationService translationService,
            LocaleProviderInterface localeProviderInterface
    ) {
        this.translationService = translationService;
        this.localeProvider = localeProviderInterface;
        TranslationFacadeSingleton.instance = this;
    }

    public static String __(String key)
    {
        var locale = TranslationFacadeSingleton.instance.localeProvider.getLocale();
        if (locale == null) {
            return key;
        }

        return TranslationFacadeSingleton.instance.translationService.translate(
                locale.getISO3Language(),
                key
        );
    }

    public static String __(String key, Map<String, String> placeholders)
    {
        var locale = TranslationFacadeSingleton.instance.localeProvider.getLocale();
        if (locale == null) {
            return key;
        }

        return TranslationFacadeSingleton.instance.translationService.translate(
                locale.getISO3Language(),
                key,
                placeholders
        );
    }
}
