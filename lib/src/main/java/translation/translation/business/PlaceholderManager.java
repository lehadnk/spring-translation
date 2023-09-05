package translation.translation.business;

import java.util.Map;

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
}
