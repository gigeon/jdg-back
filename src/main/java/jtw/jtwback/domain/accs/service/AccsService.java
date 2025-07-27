package jtw.jtwback.domain.accs.service;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.accs.dao.AccsDao;
import static jtw.jtwback.com.utilMap.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccsService {
    private final AccsDao accsDao;

    public BaseMap readAccsAll(BaseMap body) {
        System.out.println(body);
        List<BaseMap> result = fromDbList(accsDao.selectAccsAll(body));
        return new BaseMap().set("result", result);
    }

}
