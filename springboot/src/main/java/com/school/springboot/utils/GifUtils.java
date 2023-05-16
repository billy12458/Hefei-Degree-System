package com.school.springboot.utils;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
// import com.wf.captcha.GifCaptcha;
// import com.wf.captcha.utils.CaptchaUtil;

@SuppressWarnings("all")
@Component
public class GifUtils {

    public static void getImage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            String codeName)
            throws IOException {
        // GifCaptcha captcha = new GifCaptcha(125, 40, 5);
        // response.setContentType("img/png");
        // CaptchaUtil.out(captcha, request, response);
        // request.getSession().setAttribute(codeName, captcha.text());
    }
}
