package translation.translation.business.locale_providers;

import translation.translation.LocaleProviderInterface;

import java.util.Locale;

public class MockLocaleProvider implements LocaleProviderInterface {
    private final Locale locale;

    public MockLocaleProvider(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }
}
