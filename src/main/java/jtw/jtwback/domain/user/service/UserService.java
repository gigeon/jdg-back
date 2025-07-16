package jtw.jtwback.domain.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.user.dao.UserDao;
import static jtw.jtwback.com.utilMap.*;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public BaseMap userLogin(BaseMap body) {
        BaseMap result = fromDbMap(userDao.userLogin(body));
        result.set("flag", 0);
        if(result.getString("userId") != null) {
            result.set("flag", 1);
        }
        return result;
    }
}
