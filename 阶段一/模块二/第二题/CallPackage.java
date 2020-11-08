package mk2.b;

/**
 * 通话套餐类
 * 特征：通话时长、短信条数、每月资费
 * 行为: 显示所有套餐信息
 */
public class CallPackage extends Combo implements CallService{
    private int callTime;
    private int messages;

    @Override
    public void show(){
        super.show();
        System.out.println("通话时长："+callTime+" 短信条数："+messages);
    }

    @Override
    public void call(int talkTime, SimCard simCard) {

    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public void setMoney(int money) {
        super.setMoney(money);
    }

    public int getCallTime() {
        return callTime;
    }

    public int getMessages() {
        return messages;
    }

    public int getMoney() {
        return super.getMoney();
    }


}
