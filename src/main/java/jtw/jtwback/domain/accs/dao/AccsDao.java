package jtw.jtwback.domain.accs.dao;

import jtw.jtwback.com.BaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccsDao {
    List<BaseMap> selectAccsAll(BaseMap body);
}
