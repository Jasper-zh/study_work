package www.lagou.dao;

import www.lagou.entity.Phone;

import java.sql.SQLException;
import java.util.List;

public interface PhoneDao {
    public List<Phone> queryPhone(Phone phone) throws SQLException;
}
