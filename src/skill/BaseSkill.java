package skill;

public abstract class BaseSkill {
    private String name;
    private int pp;
    private int power;
    private int accuracy;
    private int maxPP;

    public BaseSkill(String name, int pp, int power, int accuracy) {
        this.setName(name);
        this.setPp(pp);
        this.setPower(power);
        this.setAccuracy(accuracy);
        this.setMaxPP(pp*160/100);
    }

    public abstract void useSkill();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public void setMaxPP(int maxPP) {
        this.maxPP = maxPP;
    }
}
