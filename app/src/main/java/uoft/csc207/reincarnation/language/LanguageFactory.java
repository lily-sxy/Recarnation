package uoft.csc207.reincarnation.language;

public class LanguageFactory {

    /**
     *  Return the Language object by language.
     *  language == 0, return English
     *  language == 1, return Chinese
     * @param language code use to identify language
     * @return English or Chinese depend on language
     */
    public static Language createLanguage(int language) {

        if (language == 0) {
            return new English();
        } else if (language == 1) {
            return new Chinese();
        }
        return null;
    }
}
