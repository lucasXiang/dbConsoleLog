package com.tech.member.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValidateCodeController {
	private static final int WIDTH = 60;// 设置验证码图片宽度
	private static final int HEIGHT = 20;// 设置验证码图片高度
	private static final int LENGTH = 4;// 设置验证码长度
	// 设置验证码随机出现的字符
	private static final String str = "1234567890" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	char[] chars = str.toCharArray();// 将字符放在数组中方便随机读取
	private static Random random = new Random();

	@RequestMapping("/AuthCode/code")
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置输出的类型为图片
		response.setContentType("image/jpeg");

		// 设置不进行缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		// 获取验证码图片
		BufferedImage codeImage = getCodeImage(request.getSession());
		// 将图片输出到response中
		ImageIO.write(codeImage, "JPEG", response.getOutputStream());
	}

	/**
	 * 生成验证码图片
	 * 
	 * @return
	 */
	public BufferedImage getCodeImage(HttpSession session) {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		// 画笔
		Graphics graphics = image.getGraphics();
		// 设置背景颜色并绘制矩形背景
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		// 用于记录生成的验证码
		String code="";
		// 生成验证码并绘制
		for (int i = 0; i < LENGTH; i++) {
			String c = "" + chars[random.nextInt(str.length())];
			graphics.setColor(Color.BLACK);
			graphics.drawString(c, 10 * i + 10, 18);
			code += c;
		}
		// 将生成的验证码存入session中，以便进行校验
		session.setAttribute("code", code);
		// 生成干扰点
		for (int i = 0; i < 50; i++) {
			graphics.setColor(getColor());
			graphics.drawOval(random.nextInt(60), random.nextInt(20), 1, 1);
		}

		// 绘制图片
		graphics.dispose();
		return image;
	}

	// 随机生成颜色
	private Color getColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
}
