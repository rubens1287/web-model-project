package localization;

import config.ConfigurationLog4j;
import org.aeonbits.owner.ConfigCache;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageParser {

    private ResourceBundle bundle= ResourceBundle.getBundle("messages/messages",
            Locale.forLanguageTag(ConfigCache.getOrCreate(ConfigurationLog4j.class).language()));

    public String parse(String label, Object[] params) {
        MessageFormat formatter = new MessageFormat(bundle.getString(label));
        return formatter.format(params);
    }

    public String parse(String label){
        return bundle.getString(label);
    }
}
