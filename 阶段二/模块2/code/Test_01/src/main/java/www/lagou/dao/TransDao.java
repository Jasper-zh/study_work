package www.lagou.dao;

import www.lagou.entity.Transaction;

import java.sql.SQLException;

public interface TransDao {
    public int addTransHis(Transaction transaction) throws SQLException;
}
