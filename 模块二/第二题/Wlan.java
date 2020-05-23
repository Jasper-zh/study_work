package mk2.b;

/**
 * 上网套餐类
 * 特征：上网流量、每月资费
 * 行为：显示所有套餐信息
 */
public class Wlan extends Combo implements WebService{
    private int data;

    @Override
    public void show(){
        super.show();
        System.out.println("上网流量："+data);
    }
    @Override
    public void surfInternet(int data, SimCard simCard) {

    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setMoney(int money){
        super.setMoney(money);
    }
    public int getMoney() {
        return super.getMoney();
    }


}
