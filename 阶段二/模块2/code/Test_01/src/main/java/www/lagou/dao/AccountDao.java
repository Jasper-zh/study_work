package www.lagou.dao;

import java.sql.SQLException;

public interface AccountDao {
    public int updateAccount(String card,double money) throws SQLException;
    public double queryBalance(String card) throws SQLException;
}
