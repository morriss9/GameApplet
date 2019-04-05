
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//Sarah Morris
//Block: A
//May 13, 2016
//Description:

public class FlappyBird extends JoeApplet implements KeyListener
{
	//random generator
	Random gen = new Random();
	
	//screen size 
	int width = 930;
	int height = 540;
	
	//all images
	Image Background;
	Image Title;
	Image Birdy;
	Image Flappy;
	Image RedBird;
	Image BlueBird;
	
	//the bird
	int birdX = 30;
	int birdY = 270;
	
	
	//spacing between walls 
 	int spacing = 400;
	
	//the walls
	int wall1X = 500;
	int wall1H = gen.nextInt(height-100)+60;
	int wall1Y = wall1H +150;
	int wall2X = 500 + spacing;
	int wall2H = gen.nextInt(height-100)+60;
	int wall2Y = wall2H+150;
	int wall3X = 500 + 2*spacing;
	int wall3H = gen.nextInt(height-100)+60;
	int wall3Y = wall3H+150;
	int wall4X = 500 + 3*spacing;
	int wall4H = gen.nextInt(height-100)+60;
	int wall4Y = wall4H+150;
	
	
	//speed of the walls (to be modified later for levels)
	int wallspeed = 3;
	
	//speed of bird
	int birdspeed = 10;
	
	//the score
	int score = 0;
	
	//lets game begin
	boolean playGame = true;
	
	//create SolidObjects
	SolidObject birdSO = new SolidObject();
	SolidObject wall1topSO = new SolidObject();
	SolidObject wall1btmSO = new SolidObject();
	SolidObject wall1lipSO = new SolidObject();
	SolidObject wall1lipbtm = new SolidObject();
	SolidObject wall1holeSO = new SolidObject();
	SolidObject wall2topSO = new SolidObject();
	SolidObject wall2btmSO = new SolidObject();
	SolidObject wall2lipSO = new SolidObject();
	SolidObject wall2lipbtm = new SolidObject();
	SolidObject wall2holeSO = new SolidObject();
	SolidObject wall3topSO = new SolidObject();
	SolidObject wall3btmSO = new SolidObject();
	SolidObject wall3lipSO = new SolidObject();
	SolidObject wall3lipbtm = new SolidObject();
	SolidObject wall3holeSO = new SolidObject();
	SolidObject wall4topSO = new SolidObject();
	SolidObject wall4btmSO = new SolidObject();
	SolidObject wall4lipSO = new SolidObject();
	SolidObject wall4lipbtm = new SolidObject();
	SolidObject wall4holeSO = new SolidObject();
	
	//used to make sure score goes up by one 
	boolean firstHit = true;
	
	boolean showStartScreen = true;
	boolean showEndScreen = false;
	

	//adds listener at the beginning of the program
	public void init()
	{
		addKeyListener(this);
		
		//images
		Birdy = getImage(getCodeBase(), "Birdy.png");
		Background = getImage(getCodeBase(), "Background.png");
		Title = getImage(getCodeBase(), "Title.png");
		Flappy = getImage(getCodeBase(), "Flappy.gif");
		RedBird = getImage(getCodeBase(), "RedBird.png");
		BlueBird = getImage(getCodeBase(), "BlueBird.png");
	}
	
	//draw the start screen
	public void drawStartScreen(Graphics art)
	{
		setBackground(Color.blue);
		art.setColor(Color.white);
		art.setFont(new Font("AR DESTINE", Font.BOLD, 40));
		int titleWidth = Title.getWidth(this);
		int titleHeight = Title.getHeight(this);
		art.drawImage(Title, 200, 40, titleWidth*2, titleHeight*2, this);
		art.drawString("Instructions:", 100, 225);
		art.setFont(new Font("Batang", Font.BOLD, 16));
		art.drawString("Use the up and down keys to move your bird and avoid the walls.", 100, 275);
		art.drawString("The object is to get the bird as far as possible.", 100, 325);
		art.drawString("Once the bird hits a wall, the game is over.", 100, 375);
		art.setFont(new Font("Batang", Font.BOLD, 25 ));
		art.drawString("Hit enter to begin!", 100, 465);
		int birdyWidth = Birdy.getWidth(this);
		int birdyHeight = Birdy.getHeight(this);
		art.drawImage(Birdy, 600, 300, birdyWidth/2, birdyHeight/2, this);
		int redWidth = RedBird.getWidth(this);
		int redHeight = RedBird.getHeight(this);
		art.drawImage(RedBird, 700, 150, redWidth, redHeight, this);
		int blueWidth = BlueBird.getWidth(this);
		int blueHeight = BlueBird.getHeight(this);
		art.drawImage(BlueBird, 425, 375, blueWidth, blueHeight, this);
	}
	
	//draw the bird
	public void drawBird(Graphics art)
	{
		//background image
		art.drawImage(Background, 0, 0, width, height, this);
		
		//bird image
		int birdWidth = Flappy.getWidth(this);
		int birdHeight = Flappy.getHeight(this);
		art.drawImage(Flappy, birdX, birdY, birdWidth/2, birdHeight/2, this);
		birdSO.makeSolidObject(birdX, birdY, birdWidth/2, birdHeight/2);
	}
	
	
	//draw the score
	public void drawScore(Graphics art)
	{
		art.setColor(Color.white);
		art.drawString("Score = "+score+"", 50, 100);
	}
	
	//draw the walls 
	public void drawWalls(Graphics art)
	{
		Color lightGreen = new Color(39,208,33);
		art.setColor(lightGreen);
		
		//wall 1 top and bottom
		art.fillRect(wall1X, 0, 90, wall1H);
  		wall1topSO.makeSolidObject(wall1X, 0, 90, wall1H);
  		art.fillRect(wall1X-5, wall1H-30, 100, 30);
  		wall1lipSO.makeSolidObject(wall1X-5, wall1H-30, 100, 30);
		art.fillRect(wall1X, wall1Y, 90, height);   //goes below screen.  No big deal
		wall1btmSO.makeSolidObject(wall1X, wall1Y, 90, height);
		art.fillRect(wall1X-5, wall1Y-30, 100, 30);
		wall1lipbtm.makeSolidObject(wall1X-5, wall1Y-30, 100, 30);
		wall1holeSO.makeSolidObject(wall1X, wall1H, 90, 150);
		
		//wall 2 top and bottom
		art.fillRect(wall2X, 0, 90, wall2H);
		wall2topSO.makeSolidObject(wall2X, 0, 90, wall2H);
		art.fillRect(wall2X-5, wall2H-30, 100, 30);
		wall2lipSO.makeSolidObject(wall2X-5, wall2H-30, 100, 30);
		art.fillRect(wall2X, wall2Y, 90, height);
		wall2btmSO.makeSolidObject(wall2X, wall2Y, 90, height);
		art.fillRect(wall2X-5, wall2Y-30, 100, 30);
		wall2lipbtm.makeSolidObject(wall2X-5, wall2Y-30, 100, 30);
		wall2holeSO.makeSolidObject(wall2X, wall2H, 90, 150);
		
		//wall 3 top and bottom
		art.fillRect(wall3X, 0, 90, wall3H);
		wall3topSO.makeSolidObject(wall3X, 0, 90, wall3H);
		art.fillRect(wall3X-5, wall3H-30, 100, 30);
		wall3lipSO.makeSolidObject(wall3X-5, wall3H-30, 100, 30);
		art.fillRect(wall3X, wall3Y, 90, height);
		wall3btmSO.makeSolidObject(wall3X, wall3Y, 90, height);
		art.fillRect(wall3X-5, wall3Y-30, 100, 30);
		wall3lipbtm.makeSolidObject(wall3X-5, wall3Y-30, 100, 30);
		wall3holeSO.makeSolidObject(wall3X, wall3H, 90, 150);
		
		//wall 4 top and bottom
		art.fillRect(wall4X, 0, 90, wall4H);
		wall4topSO.makeSolidObject(wall4X, 0, 90, wall4H);
		art.fillRect(wall4X-5, wall4H-30, 100, 30);
		wall4lipSO.makeSolidObject(wall4X-5, wall4H-30, 100, 30);
		art.fillRect(wall4X, wall4Y, 90, height);
		wall4btmSO.makeSolidObject(wall4X, wall4Y, 90, height);
		art.fillRect(wall4X-5, wall4Y-30, 100, 30);
		wall4lipbtm.makeSolidObject(wall4X-5, wall4Y-30, 100, 30);
		wall4holeSO.makeSolidObject(wall4X, wall4H, 90, 150);
	}
	
	//movement of the walls across the screen
	public void movement(Graphics art)
	{
			wall1X = wall1X - wallspeed;
			wall2X = wall2X - wallspeed;
			wall3X = wall3X - wallspeed;
			wall4X = wall4X - wallspeed;
			if(wall1X + 90 < 0)
			{
				wall1H = gen.nextInt(200)+100;  //this can be modified and based on variables
				wall1Y = wall1H + 150;
				wall1X = 4*spacing;
			}
			if(wall2X + 90 < 0)
			{
				wall2H = gen.nextInt(200)+100;
				wall2Y = wall2H + 150;
				wall2X = 4*spacing;
			}
			if(wall3X + 90 < 0)
			{
				wall3H = gen.nextInt(200)+100;
				wall3Y = wall3H + 150;
				wall3X = 4*spacing;
			}
			if(wall4X + 90 < 0)
			{
				wall4H = gen.nextInt(200)+100;
				wall4Y = wall4H + 150;
				wall4X = 4*spacing;
			}
			
		//speed of walls to be modified depending on score for difficulty
			if(score==10)
			{
				wallspeed = 4;
				art.setColor(Color.white);
				art.drawString("Now that was easy right...", 300, 100);
			}
			if(score==20)
			{
				wallspeed = 5;
				art.setColor(Color.white);
				art.drawString("Okay, not too shabby...", 300, 100);
			}
			if(score==30)
			{
				wallspeed = 6;
				art.setColor(Color.white);
				art.drawString("You're getting there...", 300, 100);
			}
			if(score==40)
			{
				wallspeed = 7;
				art.setColor(Color.white);
				art.drawString("Don't get too confident...", 300, 100);
			}
			if(score==50)
			{
				wallspeed = 8;
				art.setColor(Color.white);
				art.drawString("Let's really speed it up...", 300, 100);
			}
			if(score==60)
			{
				wallspeed = 9;
				art.setColor(Color.white);
				art.drawString("I must say you're pretty good...", 300, 100);
			}
			if(score==70)
			{
				wallspeed = 10 ;
				art.setColor(Color.white);
				art.drawString("I give up, you're too good...", 300, 100);
			}	
	}
	
	//collision situations
	public void collision(Graphics art)
	{	
		if(birdSO.isCollidingWith(wall1topSO)||birdSO.isCollidingWith(wall1btmSO)
				||birdSO.isCollidingWith(wall2topSO)||birdSO.isCollidingWith(wall2btmSO)||
				birdSO.isCollidingWith(wall3topSO)||birdSO.isCollidingWith(wall3btmSO)||
				birdSO.isCollidingWith(wall4topSO)||birdSO.isCollidingWith(wall4btmSO))
		{
			playGame = false; //stop moving game over
			showEndScreen = true;
		}
		else
		{
			if(birdSO.isCollidingWith(wall1holeSO)||birdSO.isCollidingWith(wall2holeSO)||
					birdSO.isCollidingWith(wall3holeSO)||birdSO.isCollidingWith(wall4holeSO))
			{
				if(firstHit)
				{
					score = score + 1;
					firstHit = false;
				}
			}
			else
			{
				firstHit = true;
			}
		}
	 }
	
	//draws the end screen
	public void drawEndScreen(Graphics art)
	{
		setBackground(Color.blue);
		art.setColor(Color.orange);
		art.setFont(new Font("Bauhaus 93", Font.BOLD, 100));
		art.drawString("Game Over!", 180, 150);
		art.setColor(Color.white);
		art.setFont(new Font("Batang", Font.PLAIN, 40));
		art.drawString("Your score = "+score+"", 320, 275);
		art.drawString("If you would like to play again,", 180, 400);
		art.drawString("hit the space bar to begin!", 200, 450);
		int redWidth = RedBird.getWidth(this);
		int redHeight = RedBird.getHeight(this);
		art.drawImage(RedBird, 25, 175, redWidth, redHeight, this);
		art.drawImage(RedBird, 725, 175, redWidth, redHeight, this);
	}
	
	public void reset()
	{
		//reset screen size 
		width = 930;
		height = 540;
		
		//reset the bird
		birdX = 30;
		birdY = 270;
		
		
		//reset spacing between walls 
	 	spacing = 400;
		
		//reset the walls
		wall1X = 500;
		wall1H = gen.nextInt(height-100)+60;
		wall1Y = wall1H +150;
		wall2X = 500 + spacing;
		wall2H = gen.nextInt(height-100)+60;
		wall2Y = wall2H+150;
		wall3X = 500 + 2*spacing;
		wall3H = gen.nextInt(height-100)+60;
		wall3Y = wall3H+150;
		wall4X = 500 + 3*spacing;
		wall4H = gen.nextInt(height-100)+60;
		wall4Y = wall4H+150;
		
		
		//reset speed of the walls 
		wallspeed = 3;
		
		//reset speed of bird
		birdspeed = 10;
		
		//reset the score
		score = 0;
	}
	
	public void paint(Graphics art)
	{
	setSize(width,height);
		
		if(showStartScreen)
		{
			drawStartScreen(art);	
		}    
		else
		{
			if(playGame)
			{
				drawBird(art);
				drawWalls(art);
				movement(art);
				collision(art);
				drawScore(art);
			}
			else
			{
				if(showEndScreen)
				{
					drawEndScreen(art);
				}
			}
		}
		
		repaint();
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		//bird movement
		if(key==e.VK_UP)
		{
			birdY = birdY - birdspeed;
		}
	
		if(key==e.VK_DOWN)
		{
			birdY = birdY + birdspeed;
		}	
		
		//to exit start screen and begin game
		if(key==e.VK_ENTER)
		{
			showStartScreen = false;
			playGame = true;
		}
		
		//to restart game
		if(key==e.VK_SPACE)
		{
			showEndScreen = false;
			reset(); 
			showStartScreen = true;
		}
	}

	public void keyReleased(KeyEvent arg0)
	{
	}

	public void keyTyped(KeyEvent arg0) 
	{
	}

}
