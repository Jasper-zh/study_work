package www.lagou.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import www.lagou.dao.PhoneDao;
import www.lagou.entity.Phone;
import www.lagou.utils.DruidUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {

    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Phone> queryPhone(Phone phone) throws SQLException {
        // 1.编写sql
        String sql = "SELECT * FROM phone WHERE 1=1";
        List<Object> params = new ArrayList<>();
        // 2.根据不定参拼接sql组装参数
        if(phone.getPrice()!=null){
            sql += " and price > ?";
            params.add(phone.getPrice());
        }
        if(phone.getProdate()!=null){
            sql += " and prodate < (select date_format(?,'%Y-%m-%d'))";
            params.add(phone.getProdate());
        }
        if(phone.getColor()!=null){
            sql += " and color = ?";
            params.add(phone.getColor());
        }
        if(phone.getPname()!=null){
            sql += " and pname like '%' ? '%'";
            params.add(phone.getPname());
        }
        System.out.println(sql);
        List<Phone> query = queryRunner.query(sql, new BeanListHandler<Phone>(Phone.class), params.toArray());
        return query;
    }
}
