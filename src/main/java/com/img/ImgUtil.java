package com.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgUtil {
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File("f://moban1.png"));
			Graphics2D g = img.createGraphics();
			g.setColor(Color.BLACK);
			setXy_1(g,"撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方");
			setXy_2(g,"撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方");
			setXy_3(g,"撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方");
			setXy_4(g,"撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方");

			g.dispose();
			ImageIO.write(img, "png", new File("f://moban1_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setXy_4(Graphics2D g, String content) {
		if(content != null && !content.equals("")) {
			String []s="撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方".split("，");
			int x=320;
			int y=420;
			int k = 20;
			for(int i = 0;i<s.length;i++) {
				g.drawString(s[i], x, y);
				y+=k;
			}
		}
	}

	private static void setXy_3(Graphics2D g, String content) {
		if(content != null && !content.equals("")) {
			String []s="撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方".split("，");
			int x=80;
			int y=420;
			int k = 20;
			for(int i = 0;i<s.length;i++) {
				g.drawString(s[i], x, y);
				y+=k;
			}
		}
	}

	private static void setXy_2(Graphics2D g, String content) {
		if(content != null && !content.equals("")) {
			String []s="撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方".split("，");
			int x=80;
			int y=160;
			int k = 20;
			for(int i = 0;i<s.length;i++) {
				g.drawString(s[i], x, y);
				y+=k;
			}
		}
	}

	private static void setXy_1(Graphics2D g, String content) {
		if(content != null && !content.equals("")) {
			String []s="撒旦发生大，萨芬士大夫撒旦发生，啊撒旦飞洒地方".split("，");
			int x=320;
			int y=160;
			int k = 20;
			for(int i = 0;i<s.length;i++) {
				g.drawString(s[i], x, y);
				y+=k;
			}
		}
	}
}
