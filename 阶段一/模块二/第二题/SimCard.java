package mk2.b;

/**
 * 手机卡类
 * 特征：卡类型、卡号、用户名、密码、账户余额、通话时长(分钟)、上网流量
 * 行为：显示（卡号 + 用户名 + 当前余额）
 */
public class SimCard {
    private CardType type;
    private String cardNumber;
    private String username;
    private String password;
    private int accountBalance;
    private int talkTime;
    private int data;

    public void show(){
        System.out.println("卡号："+cardNumber+" 用户名："+username+" 账户余额："+accountBalance);
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public void setData(int data) {
        this.data = data;
    }

    public CardType getType() {
        return type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public int getData() {
        return data;
    }
}
