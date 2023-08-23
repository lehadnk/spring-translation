package translation.translation.bundle_providers;

import translation.translation.GettextResourceBundleProviderInterface;

public class FrGettextResourceBundleProvider implements GettextResourceBundleProviderInterface {

    @Override
    public String getLanguage() {
        return "fr";
    }

    @Override
    public String getResourceBundlePath() {
        return "src/test/resources/bundles/fr_FR.po";
    }
}
