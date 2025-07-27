package jtw.jtwback.domain.conn.service;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.conn.dao.ConnDao;
import static jtw.jtwback.com.utilMap.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConnService {
    private final ConnDao connDao;

    public BaseMap pushCli(BaseMap body) {
        BaseMap resultMap = new BaseMap();
        try {
            BaseMap accs = fromDbMap(connDao.selectDbDetail(body));
            String url = setUrl(accs);
            System.out.println(url);
            String inputText = body.getString("inputText").toString();

            try (Connection conn = DriverManager.getConnection(url, "root", "1234");
                 Statement stmt = conn.createStatement()) {

                boolean hasResultSet = stmt.execute(inputText);

                resultMap.set("success", true);
                resultMap.set("query", inputText);

                if (hasResultSet) {
                    ResultSet rs = stmt.getResultSet();
                    ResultSetMetaData meta = rs.getMetaData();
                    int colCount = meta.getColumnCount();

                    List<BaseMap> results = new ArrayList<>();
                    while (rs.next()) {
                        BaseMap row = new BaseMap();
                        for (int i = 1; i <= colCount; i++) {
                            row.put(meta.getColumnLabel(i), rs.getObject(i));
                        }
                        results.add(row);
                    }

                    resultMap.set("type", "resultSet");
                    resultMap.set("result", results);
                } else {
                    int updateCount = stmt.getUpdateCount();
                    resultMap.set("type", "updateCount");
                    resultMap.set("updateCount", updateCount);
                    resultMap.set("message", "Query OK, " + updateCount + " rows affected.");
                }

            } catch (SQLException e) {
                resultMap.set("success", false);
                resultMap.set("type", "sqlError");
                resultMap.set("result", e.getMessage());
                resultMap.set("sqlState", e.getSQLState());
                resultMap.set("errorCode", e.getErrorCode());
            }

        } catch (Exception e) {
            resultMap.set("success", false);
            resultMap.set("type", "systemError");
            resultMap.set("result", e.getMessage());
        }

        return resultMap;
    }

    public String setUrl(BaseMap body) {
        String result = new String();
        String accsUrl = body.getString("accsUrl").toString();
        String accsPort = body.getString("accsPort").toString();
        String accsDb = body.getString("accsDb").toString();
        String accsCd = body.getString("accsCd").toString();

        if(accsCd.equals("001")) {
            result = "jdbc:mysql://" + accsUrl + ":" + accsPort + "/" +accsDb;
        }
        return result;
    }

}
