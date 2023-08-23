package translation.translation;

import translation.translation.business.TranslationManager;

public class TranslationService {
    private final TranslationManager translationManager;
    private final String defaultLanguage;
    private final ThreadLocal<String> language;

    public TranslationService(
            TranslationManager translationManager,
            String defaultLanguage
    ) {
        this.translationManager = translationManager;
        this.defaultLanguage = defaultLanguage;
        this.language = new ThreadLocal<String>();
    }

    public String getLanguage()
    {
        if (this.language.get() == null) {
            return this.defaultLanguage;
        }

        return this.language.get();
    }

    public void setLanguage(String language)
    {
        this.language.set(language);
    }

    public String translate(String key)
    {
        return this.translationManager.translate(this.getLanguage(), key);
    }
}
