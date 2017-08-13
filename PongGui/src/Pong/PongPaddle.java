package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PongPaddle {
	public int x, y, width = 20, height = 125, whichPlayer, YDirection , up , down;
	public Rectangle paddle;
	public PongBall ball;
	
	public PongPaddle(PongBall ball,int up, int down, int whichPlayer) {
		this.ball = ball;
		this.up = up;
		this.down = down;
		this.whichPlayer = whichPlayer;
		y = 200;
		if (whichPlayer == 1) {
			x = 5;

		} else {
			x = 625;
		}
		paddle = new Rectangle(x, y, width, height);
	}

	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);

	}
	public void keyPressed(int keyCode) {
        if (keyCode == up)
        	setYDirection(-5);
        else if (keyCode == down)
        	setYDirection(5);
    }

    public void keyReleased(int keyCode) {
        if (keyCode == up || keyCode == down)
        	setYDirection(0);
    }
	public void setYDirection(int str){
		this.YDirection =str;
	}

	public void move( int whichPaddle) {
		paddle.y += YDirection;
		if(paddle.y <= 5 ){
			paddle.y = 5;
		}
		else if( paddle.y+paddle.getHeight() >= 545 ){
			paddle.y= (545 -paddle.height);
		}
		ball.GetPaddleInfo(paddle.x , paddle.y , paddle.width , paddle.height , whichPaddle);
	}
	 
	
	

}
