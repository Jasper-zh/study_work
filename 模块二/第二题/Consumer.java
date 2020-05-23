package mk2.b;

/**
 * 用户消费信息类
 * 特征：统计通话时长、统计上网流量、每月消费金额
 */
public class Consumer {
    private int talkTime;
    private int data;
    private int money;

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public int getData() {
        return data;
    }

    public int getMoney() {
        return money;
    }
}
