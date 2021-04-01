package www.lagou.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import www.lagou.dao.AccountDao;
import www.lagou.utils.DruidUtils;

import java.sql.SQLException;
import java.util.Map;

public class AccountDaoImpl implements AccountDao {

    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    public int updateAccount(String card, double money) throws SQLException {
        String sql = "UPDATE account set balance = balance + ? WHERE card = ?";
        int rows = queryRunner.update(sql, money, card);
        return rows;
    }

    public double queryBalance(String card) throws SQLException {
        String sql = "SELECT balance FROM account WHERE card = ?";
        Double result = queryRunner.query(sql, new ScalarHandler<Double>(), card);
        System.out.println(result);
        return result;
    }
}
