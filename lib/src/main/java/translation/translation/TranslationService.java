package translation.translation;

import translation.translation.business.PlaceholderManager;
import translation.translation.business.TranslationManager;

import java.util.Map;

public class TranslationService {
    private final TranslationManager translationManager;
    private final PlaceholderManager placeholderManager;

    public TranslationService(
            TranslationManager translationManager,
            PlaceholderManager placeholderManager
    ) {
        this.translationManager = translationManager;
        this.placeholderManager = placeholderManager;
    }

    public String translate(String iso3LanguageCode, String key)
    {
        return this.translationManager.translate(iso3LanguageCode, key);
    }

    public String translate(String iso3LanguageCode, String key, Map<String, String> placeholders)
    {
        var translation = this.translationManager.translate(iso3LanguageCode, key);
        return this.placeholderManager.replacePlaceholders(translation, placeholders);
    }
}
