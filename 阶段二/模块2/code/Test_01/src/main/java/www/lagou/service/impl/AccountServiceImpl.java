package www.lagou.service.impl;

import www.lagou.dao.AccountDao;
import www.lagou.dao.TransDao;
import www.lagou.dao.impl.AccountDaoImpl;
import www.lagou.dao.impl.TransDaoImpl;
import www.lagou.entity.Transaction;
import www.lagou.service.AccountService;

import java.sql.SQLException;
import java.util.Date;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();
    private TransDao transDao = new TransDaoImpl();

    public String transAccount(String out, String in, double money) {
        try {
            // 1.是否余额足够
            if(!isBalance(out,money)){
                System.out.println("---------余额不足--------");
                return "余额不足";
            }
            // 2.开始转账
            System.out.println("---------开始转账--------");
            accountDao.updateAccount(out,-money);
            accountDao.updateAccount(in,money);
            // 3.记录历史
            System.out.println("---------记录账单--------");
            addHis(out,-money);
            addHis(in,money);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("---------转账成功--------");
        return "转账成功！！！";
    }

    private boolean isBalance(String out,double money){
        // 1.查询余额
        double balance = 0;
        try {
            balance = accountDao.queryBalance(out);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 2.判定余额
        boolean flag = balance >= money;
        // 3.返回结果
        return flag;
    }

    private void addHis(String card,double money){
        // 1.组装保存参数
        String type = money < 0 ? "转出" : "转入";
        money = money > 0 ? money : -money;
        Date date = new Date();
        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setType(type);
        transaction.setMoney(money);
        transaction.setDate(date);
        // 2.进行保存
        try {
            transDao.addTransHis(transaction);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
