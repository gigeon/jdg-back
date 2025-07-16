package jtw.jtwback.domain.user.dao;

import jtw.jtwback.com.BaseMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    BaseMap userLogin(BaseMap body);
}
