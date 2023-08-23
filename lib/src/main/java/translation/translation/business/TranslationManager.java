package translation.translation.business;

import translation.translation.GettextResourceBundleProviderInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationManager {
    private Map<String, GettextResourceBundle> resourceBundles = new HashMap<>();

    public TranslationManager(
            List<GettextResourceBundleProviderInterface> resourceBundleProviders
    ) throws IOException {
        for (var resourceBundle : resourceBundleProviders) {
            this.resourceBundles.put(resourceBundle.getIso3LanguageCode(), new GettextResourceBundle(resourceBundle.getResourceBundlePath()));
        }
    }

    public String translate(
            String iso3LanguageCode,
            String key
    ) {
        var resourceBundle = this.resourceBundles.get(iso3LanguageCode);
        if (resourceBundle == null) {
            return key;
        }

        return resourceBundle.getTranslation(key);
    }
}
