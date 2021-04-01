package www.lagou.service.impl;

import www.lagou.dao.impl.PhoneDaoImpl;
import www.lagou.entity.Phone;
import www.lagou.service.PhoneService;

import java.sql.SQLException;
import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    @Override
    public List<Phone> queryPhone(Phone phone) {
        try {
            return new PhoneDaoImpl().queryPhone(phone);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
