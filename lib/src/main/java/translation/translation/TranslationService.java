package translation.translation;

import translation.translation.business.TranslationManager;

public class TranslationService {
    private final TranslationManager translationManager;

    public TranslationService(
            TranslationManager translationManager
    ) {
        this.translationManager = translationManager;
    }

    public String translate(String iso3LanguageCode, String key)
    {
        return this.translationManager.translate(iso3LanguageCode, key);
    }
}
