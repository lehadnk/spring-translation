package translation.translation.business;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GettextResourceBundle {
    private static final Pattern LINE_PATTERN = Pattern.compile(
            "^([\\w_\\[\\]]*)\\s*\\\"(.*)\\\"$"
    );

    private final Map<String, String> resources = new HashMap<>();

    public GettextResourceBundle(
            String translationFilePath
    ) throws IOException {
        this.loadTranslations(translationFilePath);
    }

    private void loadTranslations(
            String translationFilePath
    ) throws IOException {
        var loader = Thread.currentThread().getContextClassLoader();
        var resourceUrl = loader.getResource(translationFilePath);
        if (resourceUrl == null) {
            throw new IOException("Resource not found: " + translationFilePath);
        }

        var connection = resourceUrl.openConnection();
        try {
            connection.connect();
            try (var inputStream = resourceUrl.openStream()) {
                var reader = new LineNumberReader(new InputStreamReader(inputStream));
                this.readTranslationLines(reader);
            }
        } finally {
            if (connection instanceof AutoCloseable closeable) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw e;
                } catch (Exception e) {
                    throw new IOException(e);
                }
            }
        }
    }

    private void readTranslationLines(LineNumberReader reader) throws IOException {
        String line = null;
        String key = null;
        String value = null;
        while((line = reader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }

            if (line.trim().isEmpty()) {
                continue;
            }

            Matcher matcher = LINE_PATTERN.matcher(line);
            if (matcher.matches()) {
                String type = matcher.group(1);
                String str = matcher.group(2);
                if ("msgid".equals(type)) {
                    if (key != null && value != null) {
                        value = null;
                    }
                    key = str;
                } else if ("msgstr".equals(type)) {
                    value = str;
                    this.resources.put(StringEscapeUtils.unescapeJava(key), StringEscapeUtils.unescapeJava(value));
                    key = null;
                } else if (type == null || type.isEmpty()) {
                    if (value == null) {
                        key += str;
                    } else {
                        value += str;
                    }
                }
            }
        }
    }

    public String getTranslation(String key) {
        if (!this.resources.containsKey(key)) {
            return key;
        }

        var translation = this.resources.get(key);
        if (translation.isEmpty()) {
            return key;
        }

        return translation;
    }
}
