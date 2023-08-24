package translation.translation.business.bundle_providers;

import translation.translation.GettextResourceBundleProviderInterface;

import java.util.Locale;

public class FrGettextResourceBundleProvider implements GettextResourceBundleProviderInterface {

    @Override
    public String getIso3LanguageCode() {
        return Locale.FRANCE.getISO3Language();
    }

    @Override
    public String getResourceBundlePath() {
        return "classpath:/resources/bundles/fr_FR.po";
    }
}
