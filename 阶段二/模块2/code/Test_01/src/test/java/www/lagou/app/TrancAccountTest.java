package www.lagou.app;

import org.junit.Test;
import www.lagou.dao.AccountDao;
import www.lagou.dao.TransDao;
import www.lagou.dao.impl.AccountDaoImpl;
import www.lagou.dao.impl.TransDaoImpl;
import www.lagou.entity.Account;
import www.lagou.entity.Phone;
import www.lagou.entity.Transaction;
import www.lagou.service.AccountService;
import www.lagou.service.PhoneService;
import www.lagou.service.impl.AccountServiceImpl;
import www.lagou.service.impl.PhoneServiceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TrancAccountTest {
    AccountService accountService = new AccountServiceImpl();
    AccountDao accountDao = new AccountDaoImpl();
    TransDao transDao = new TransDaoImpl();
    PhoneService phoneService = new PhoneServiceImpl();
    @Test
    public void testTrancAccount(){
        String out = "1122334455";
        String in = "55443332211";
        int money = 5000;
        accountService.transAccount(out,in,money);
    }
    @Test
    public void testPhoneService(){
        // 初始查询条件
        Phone phone = new Phone();
        // 查询全部
        System.out.println("-------查询全部-------");
        List<Phone> phones = phoneService.queryPhone(phone);
        System.out.println(phones);
        // 模糊查询名称
        System.out.println("-------查询名称-------");
        phone.setPname("6");
        phones = phoneService.queryPhone(phone);
        System.out.println(phones);
        // 查询价格高于2000 生产日期2019之前
        System.out.println("-------查询价格与日期-------");
        phone.setPname(null);
        phone.setPrice(2000d);
        phone.setProdate("2019-01-01");
        phones = phoneService.queryPhone(phone);
        System.out.println(phones);
        // 查询白色手机
        System.out.println("-------查询颜色-------");
        phone.setPname(null);
        phone.setPrice(null);
        phone.setProdate(null);
        phone.setColor("白色");
        phones = phoneService.queryPhone(phone);
        System.out.println(phones);
    }
    @Test
    public void testAccountDao(){
        try {
            accountDao.updateAccount("1122334455",5000);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testAccountDao1(){
        try {
            accountDao.queryBalance("1122334455");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testAccountDao2(){
        try {
            Date date = new Date();
            Transaction transaction = new Transaction();
            transaction.setCard("1122334455");
            transaction.setType("转出");
            transaction.setMoney(5000.0);
            transaction.setDate(date);
            transDao.addTransHis(transaction);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
