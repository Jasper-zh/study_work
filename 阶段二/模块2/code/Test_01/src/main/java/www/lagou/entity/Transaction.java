package www.lagou.entity;

import java.util.Date;

public class Transaction {
    private Integer id;
    private String card;
    private String Type;
    private Double money;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", card='" + card + '\'' +
                ", Type='" + Type + '\'' +
                ", money=" + money +
                ", date=" + date +
                '}';
    }
}
