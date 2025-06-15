// File: ui/LanguageSwitcher.java

package ui;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LanguageSwitcher {

    private static Locale currentLocale = Locale.ENGLISH;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.Messages", currentLocale);

    public static void setLanguage(String languageCode) {
        try {
            currentLocale = new Locale(languageCode);
            resourceBundle = ResourceBundle.getBundle("i18n.Messages", currentLocale);
            System.out.println("Language switched to: " + currentLocale.getLanguage());
        } catch (MissingResourceException e) {
            System.err.println("Missing translation file for language: " + languageCode);
        }
    }

    public static String get(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return "[!" + key + "]";
        }
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static ResourceBundle getBundle() {
        return resourceBundle;
    }
}
