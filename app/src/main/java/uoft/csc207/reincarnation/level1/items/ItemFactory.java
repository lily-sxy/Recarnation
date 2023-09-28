package uoft.csc207.reincarnation.level1.items;

import uoft.csc207.reincarnation.level1.manager.Storage;

/**
 * Return the different object extends from Item base on the itemType
 */
public class ItemFactory {

    //Where user store the inventory
    private Storage storage;

    /**
     * Setter
     *
     * @param storage is use by Open and Get Item
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Return one of the object from Open, Get, Change, Room, Pause, Escape, Exit
     *
     * @param itemType is the type of the Item want to be create
     * @return the Item base on the item Type
     */
    public Item createItem(String itemType) {
        if (itemType == null) {
            return null;
        }
        if (itemType.equalsIgnoreCase("OPEN")) {
            Open open = new Open();
            open.setStorage(storage);
            return open;
        } else if (itemType.equalsIgnoreCase("GET")) {
            Get get = new Get();
            get.setStorage(storage);
            return get;
        } else if (itemType.equalsIgnoreCase("CHANGE")) {
            return new Change();
        } else if (itemType.equalsIgnoreCase("ROOM")) {
            return new Room();
        } else if (itemType.equalsIgnoreCase("PAUSE")) {
            return new Pause();
        } else if (itemType.equalsIgnoreCase("ESCAPE")) {
            return new Escape();
        } else if (itemType.equalsIgnoreCase("OTHERS")) {
            return new Exit();
        }
        return null;
    }

}
