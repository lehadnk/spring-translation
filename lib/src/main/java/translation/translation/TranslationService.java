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

    public String translate(String iso3LanguageCode, String key, String... args)
    {
        var placeholders = this.placeholderManager.getPlaceholders(key);

        if (placeholders.size() != args.length) {
            throw new IllegalArgumentException("Placeholder count doesn't match the function args count");
        }

        var i = 0;
        for(var ph : placeholders.entrySet()) {
            ph.setValue(args[i]);
            i++;
        }

        var translation = this.translationManager.translate(iso3LanguageCode, key);
        return this.placeholderManager.replacePlaceholders(translation, placeholders);
    }
}
