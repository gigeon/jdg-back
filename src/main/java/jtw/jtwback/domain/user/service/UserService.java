package jtw.jtwback.domain.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.user.dao.UserDao;
import static jtw.jtwback.com.utilMap.*;

import jtw.jtwback.util.OtpService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final OtpService otpService;

    public BaseMap userLogin(BaseMap body) {
        int flag = 0;
        BaseMap user = fromDbMap(userDao.userLogin(body));
        System.out.println(user);
        boolean otpResult = otpService.verifyOtp(user, body);
        System.out.println(otpResult);
        if(!otpResult) {
            throw new RuntimeException("otp 번호를 다시 확인해주세요.");
        }
        if(user.getString("userId") != null) {
            flag = 1;
        }
        return new BaseMap().set("user", user).set("flag", flag);
    }

    public BaseMap readUserOtp(BaseMap body) {
        return otpService.getOtpQr(body);
    }

    public void joinUser(BaseMap body) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            try {
                HttpSession session = attrs.getRequest().getSession();
                String key = session.getAttribute("secret").toString();
                body.set("userOtp", key);
            } catch(Exception ex) {
                throw new RuntimeException("OTP 인증 정보 없음", ex);
            }
        }
        userDao.insertUser(body);
    }

    public BaseMap selectUserList(BaseMap body) {
        List<BaseMap> result = fromDbList(userDao.selectUserList(body));
        return new BaseMap().set("result", result);
    }
}
