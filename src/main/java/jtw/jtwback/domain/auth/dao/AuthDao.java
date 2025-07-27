package jtw.jtwback.domain.auth.dao;

import jtw.jtwback.com.BaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthDao {
    List<BaseMap> selectAuthAll(BaseMap body);
}
