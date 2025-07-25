package jtw.jtwback.domain.user.dao;

import jtw.jtwback.com.BaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    BaseMap userLogin(BaseMap body);

    void insertUser(BaseMap body);

    List<BaseMap> selectUserList(BaseMap body);
}
