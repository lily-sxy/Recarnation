package uoft.csc207.reincarnation.level1.items;

public interface ItemI {
    /**
     * Action done when user click the image
     */
    void interact();

    /**
     * Depend on the action in interact(), print output for user
     * The actual output depend on current language
     */
    void setOutput();

}
