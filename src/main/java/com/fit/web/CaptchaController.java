package com.fit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CaptchaController {

    private static final int WIDTH = 75;
    private static final int HEIGHT = 23;
    private static final String CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();
    private static final Font font = new Font("Arial", Font.BOLD, 20);

    @GetMapping({"/captcha", "/captcha.do", "/image.jsp", "/validate.jsp", "/validate"})
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        BufferedImage captchaImage = captchaImage(session, randomStr(4));
        response.setContentType("image/png");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        ImageIO.write(captchaImage, "PNG", response.getOutputStream());
    }

    private static BufferedImage captchaImage(HttpSession session, String text) {
        session.setAttribute("captcha", text);
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        // 设置背景颜色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 设置字体
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(text, 20, 20);
        // 添加干扰线
        for (int i = 0; i < 10; i++) {
            g.setColor(getRandColor(1, 255));
            g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
        // 设置边框
        g.setColor(new Color(199, 199, 199));
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);

        g.dispose();
        return bufferedImage;
    }

    private String randomStr(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
