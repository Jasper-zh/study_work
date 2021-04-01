package www.lagou.entity;

public class Account {
    private Integer id;
    private String username;
    private String card;
    private Double balance;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCard() {
        return card;
    }

    public Double getBalance() {
        return balance;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", card='" + card + '\'' +
                ", balance=" + balance +
                '}';
    }
}
