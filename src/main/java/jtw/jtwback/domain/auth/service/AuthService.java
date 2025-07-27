package jtw.jtwback.domain.auth.service;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.auth.dao.AuthDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static jtw.jtwback.com.utilMap.fromDbList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthDao authDao;

    public BaseMap readAuthAll(BaseMap body) {
        List<BaseMap> result = fromDbList(authDao.selectAuthAll(body));
        return new BaseMap().set("result", result);
    }
}
