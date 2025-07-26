package jtw.jtwback.domain.conn.dao;

import jtw.jtwback.com.BaseMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConnDao {
    BaseMap selectDbDetail(BaseMap body);
}
