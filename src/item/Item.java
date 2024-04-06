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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestoreHP() {
        return restoreHP;
    }

    public void setRestoreHP(int restoreHP) {
        this.restoreHP = restoreHP;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}