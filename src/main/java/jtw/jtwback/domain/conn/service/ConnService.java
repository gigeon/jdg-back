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
        BaseMap db = fromDbMap(connDao.selectDbDetail(body));
        String url = db.getString("accsUrl").toString();
        String input = body.getString("inputText").toString();

        try (Connection conn = DriverManager.getConnection(url, "root", "1234");
             Statement stmt = conn.createStatement()) {

            boolean hasResultSet = stmt.execute(input);

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

                return ResponseEntity.ok(results);

            } else {
                int updateCount = stmt.getUpdateCount();
                return ResponseEntity.ok("Query OK, " + updateCount + " rows affected.");
            }

        } catch (SQLException e) {
            return ResponseEntity.status(500).body("SQL Error: " + e.getMessage());
        }

        return null;
    }

}
