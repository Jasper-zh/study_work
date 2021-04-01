package www.lagou.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import www.lagou.dao.TransDao;
import www.lagou.entity.Transaction;
import www.lagou.utils.DruidUtils;

import java.sql.SQLException;

public class TransDaoImpl implements TransDao {

    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());


    public int addTransHis(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction(`cardid`,`tratype`,`tramoney`,`tradate`) values(?,?,?,?)";
        int rows = queryRunner.update(sql,transaction.getCard(),transaction.getType(),transaction.getMoney(),transaction.getDate());
        return rows;
    }
}
