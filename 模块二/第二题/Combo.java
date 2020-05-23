package mk2.b;


public abstract class Combo {
    private int money;

    public void show(){
        System.out.println("每月资费："+money);
    };

    public int getMoney() {
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }
}
