package Snake;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class SnakeFood {
	public int x, y, width = 20, height=20;
	public BufferedImage foodImage;
	public Rectangle food;
	public Random random ;
	public Snake snake;
	public SnakeFood(int x, int y , Snake snake) {
		this.x = x;
		this.y = y;
		this.snake= snake;
		random = new Random();
		try {
			foodImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/Food.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		food= new Rectangle(x, y, width, height);
	}
	public void paint(Graphics g){
		// food Drawn
		g.drawImage(foodImage, food.x, food.y,food.width ,food.height,null, null);
	}
	public void Move(){
		snake.GetBoundFood(food.x, food.y,food.width ,food.height);
		
		if(snake.intersect){
		food.x = random.nextInt(700)+40;
		food.y = random.nextInt(500)+40;
		snake.intersect = false;	
		}
		
		
	}
}
