package translation.translation.business;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PlaceholderManager {
    public String replacePlaceholders(String inputString, Map<String, String> placeholders)
    {
        for (var placeholder : placeholders.entrySet()) {
            inputString = inputString.replace(
                    "%" + placeholder.getKey() + "%",
                    placeholder.getValue()
            );
        }

        return inputString;
    }

    public LinkedHashMap<String, String> getPlaceholders(String inputString)
    {
        var result = new LinkedHashMap<String, String>();
        var pattern = Pattern.compile("%[aA-zZ0-9-_]*%");
        pattern.matcher(inputString).results().map(mr -> mr.group()).forEach(ph -> result.put(ph.substring(1, ph.length() - 1), null));

        return result;
    }
}
