package localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageParser {

    private static ResourceBundle prop = ResourceBundle.getBundle("log4j2");
    private static ResourceBundle bundle = ResourceBundle.getBundle("messages/messages",
            Locale.forLanguageTag(prop.getString("language")));

    public String parse(String label, Object[] params) {
        MessageFormat formatter = new MessageFormat(bundle.getString(label));
        return formatter.format(params);
    }

    public String parse(String label) {
        return bundle.getString(label);
    }
}
