package uoft.csc207.reincarnation.language;

public interface Language {

    /**
     * Get the text correspond to target in the language.
     *
     * @param text_target the target text, format "a.b.c"
     * @return The translated version of text. If text not found, original text will be returned.
     */
    String getText(String text_target);
}
