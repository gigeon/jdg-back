package jtw.jtwback.util;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import jtw.jtwback.com.BaseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    public BaseMap getOtpQr(BaseMap body) {
        String url = getOtpUrl(body);
        return new BaseMap().set("qr", url);
    }

    public String getOtpUrl(BaseMap body) {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secret = key.getKey();
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpSession session = attrs.getRequest().getSession();
            session.setAttribute("userNm", body.getString("userNm"));
            session.setAttribute("secret", secret);
            session.setAttribute("otpExpireTime", System.currentTimeMillis() + 3600_000L);
        }

        // QR 코드용 URL 반환
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("jdg", (String)body.getString("userNm"), key);
    }

    public boolean verifyOtp(BaseMap user, BaseMap body) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        return gAuth.authorize(user.getString("userOtp").toString(), Integer.parseInt(body.getString("userOtp").toString()));
    }

//    public String createOtpSecret(String username) {
//        GoogleAuthenticatorKey key = gAuth.createCredentials();
//        String secret = key.getKey();
//
//        // 저장: 유저 DB에 secret 저장해야 함
//        saveSecretToUser(username, secret);
//
//        // QR 코드용 URL 반환
//        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("내앱이름", username, key);
//    }
//
//    public boolean verifyCode(String username, int code) {
//        String secret = getSecretFromUser(username);
//        return gAuth.authorize(secret, code);
//    }
}