package item;

public class Item {
    private String name;
    private int restoreHP;
    private int amount;

    public Item(String name, int restoreHP, int amount) {
        this.name = name;
        this.restoreHP = restoreHP;
        this.amount = amount;
    }
}