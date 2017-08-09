package JumpOverPaddlePackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Paddler {
	public MainPanel panel;
	public ArrayList<Rectangle> paddlerRect;
	public Thread threadForPaddler;
	public ArrayList<Integer> animationSpeed;
	public int stage;
	public Paddler(MainPanel panel ) {
		this.panel = panel;
		paddlerRect = new ArrayList<>();
		animationSpeed = new ArrayList<>();
		CreatedAnPaddle(1);
		AnimateAnPaddle();
	}

	public void paint(Graphics g) {
		for (int i = 0; i < paddlerRect.size(); i++) {
			g.drawImage(panel.paddlerImage, paddlerRect.get(i).x, paddlerRect.get(i).y, paddlerRect.get(i).width,
					paddlerRect.get(i).height, null);
		}
	}

	public void CreatedAnPaddle(int stage) {
		this.stage = stage;
		if(stage == 1) {
			paddlerRect.add(new Rectangle(500, 450, 120, 15));
			animationSpeed.add(paddlerRect.get(0).y - 200);
			paddlerRect.add(new Rectangle(125, 250, 120, 15));
			animationSpeed.add(paddlerRect.get(1).x + 200);
			paddlerRect.add(new Rectangle(0 ,200, 120, 15));
			animationSpeed.add(paddlerRect.get(2).y - 200);
		}
			else if(stage ==2 ) {
				paddlerRect.add(new Rectangle(700, 450, 120, 15));
				animationSpeed.add(paddlerRect.get(0).y - 200);
				paddlerRect.add(new Rectangle(300, 300, 120, 15));
				animationSpeed.add(paddlerRect.get(1).x + 200);
				
				paddlerRect.add(new Rectangle(150 ,350, 120, 15));
				animationSpeed.add(paddlerRect.get(2).y - 200);
				paddlerRect.add(new Rectangle(300, 80, 120, 15));
				animationSpeed.add(paddlerRect.get(3).x + 200);
			}
		
			
			
		
	}

	
	public void AnimateAnPaddle() {
		threadForPaddler = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(stage == 1) {
						for (int i = 0; i < 3; i+=2) {
							if (animationSpeed.get(i) <= paddlerRect.get(i).y) {
								paddlerRect.get(i).y-=3+i;
								if (animationSpeed.get(i) >= paddlerRect.get(i).y) 
									animationSpeed.set(i, animationSpeed.get(i)+200);
								}
								else if (animationSpeed.get(i) >= paddlerRect.get(i).y) {
									
									paddlerRect.get(i).y+=3+i;
									if (animationSpeed.get(i) <= paddlerRect.get(i).y)
										animationSpeed.set(i, paddlerRect.get(i).y - 200);
								}
						}
						
					if (animationSpeed.get(1) <= paddlerRect.get(1).x) {
						paddlerRect.get(1).x-=4;
						if (animationSpeed.get(1) >= paddlerRect.get(1).x) 
							animationSpeed.set(1, animationSpeed.get(1)+200);
						}
						else if (animationSpeed.get(1) >= paddlerRect.get(1).x) {
							
							paddlerRect.get(1).x+=4+1;
							if (animationSpeed.get(1) <= paddlerRect.get(1).x)
								animationSpeed.set(1, paddlerRect.get(1).x - 200);
						}
					
						
					}else if(stage == 2) {
						for (int i = 0; i < 3; i+=2) {
							if (animationSpeed.get(i) <= paddlerRect.get(i).y) {
								paddlerRect.get(i).y-=3+i;
								if (animationSpeed.get(i) >= paddlerRect.get(i).y) 
									animationSpeed.set(i, animationSpeed.get(i)+200);
								}
								else if (animationSpeed.get(i) >= paddlerRect.get(i).y) {
									
									paddlerRect.get(i).y+=3+i;
									if (animationSpeed.get(i) <= paddlerRect.get(i).y)
										animationSpeed.set(i, paddlerRect.get(i).y - 200);
								}
						}
						for (int i = 1; i < 4; i+=2) {
					if (animationSpeed.get(i) <= paddlerRect.get(i).x) {
						paddlerRect.get(i).x-=4+i;
						if (animationSpeed.get(i) >= paddlerRect.get(i).x) 
							animationSpeed.set(i, animationSpeed.get(i)+200);
						}
						else if (animationSpeed.get(i) >= paddlerRect.get(i).x) {
							
							paddlerRect.get(i).x+=4+i;
							if (animationSpeed.get(i) <= paddlerRect.get(i).x)
								animationSpeed.set(i, paddlerRect.get(i).x - 200);
						}
					
						}
					}
					panel.repaint();
				}

			}
		});
	}
}
