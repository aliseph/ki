package com.ingta.framework.util.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = -3841980037140967735L;
	private static final Random random = new Random();
	public static final String CAPTCHAKEY = "key_captch";
	// 验证码图片的宽度。
	private static int WIDTH = 70;
	// 验证码图片的高度。
	private static int HEIGHT = 30;

	public BufferedImage gen(String text, int width, int height)
			throws IOException {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < 10; i++) {
			g.setColor(randColor(150, 250));
			g.drawOval(random.nextInt(110), random.nextInt(24),
					5 + random.nextInt(10), 5 + random.nextInt(10));
			Font f = new Font("Arial", Font.ITALIC, 20);
			g.setFont(f);
			g.setColor(randColor(10, 240));
			g.drawString(text, 4, 24);
		}
		return bi;
	}

        @Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		String text = randText();
		// 将code的真实数据放入session中
		HttpSession session = request.getSession();
		session.setAttribute(CAPTCHAKEY, text);
		// 绘制干扰线
		// for (int i = 0; i < 10; i++) {
		// g.setColor(randomColor(r));
		// g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r
		// .nextInt(HEIGHT));
		// }
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(gen(text, WIDTH, HEIGHT), "jpeg", sos);
		sos.close();
	}

	private Color randColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private String randText() {
		String text = RandomStringUtils.randomAlphanumeric(4).toUpperCase()
				.replace('0', 'A').replace('O', 'E').replace('1', 'V')
				.replace('I', 'R');
		return text;
	}

}
