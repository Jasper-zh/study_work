package mk2.b;

public class Test {
    public static void main(String[] args) {
        SimCard simCard = new SimCard();
        simCard.setCardNumber("14315773467835354");
        simCard.setUsername("hao");
        simCard.setAccountBalance(400);
        System.out.println("-------------------------------");
        simCard.show();

        Combo combo1 = new CallPackage();
        combo1.setMoney(1000);
        CallPackage callPackage = (CallPackage) combo1;
        callPackage.setCallTime(100);
        callPackage.setMessages(20);
        System.out.println("--------------------------------");
        combo1.show();

        Combo combo2 = new Wlan();
        combo2.setMoney(200);
        Wlan wlan = (Wlan)combo2;
        wlan.setData(1000);
        System.out.println("---------------------------------");
        combo2.show();
    }
}
