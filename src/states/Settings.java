package states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import util.AssetManager;

public class Settings extends State {
	public int scrollBtnX=Game.gameWidth/2,scrollBtnY=Game.gameHeight/2;
	BufferedImage window;
	BufferedImage closeBtn;
	BufferedImage soundBtn;
	BufferedImage scrollBar;
	BufferedImage scrollBtn;
	Rectangle closeRect = new Rectangle(1275, 75, 64, 64);
	Rectangle barRect = new Rectangle(Game.gameWidth/2, Game.gameHeight/2, 144, 40);
	Rectangle scrollBtnRect = new Rectangle(Game.gameWidth/2, Game.gameHeight/2, 48, 64);
	int pressedInt=0;
	boolean pressed;
	private Game game;
	public Settings(Game game) {
		this.game = game;
	}

	@Override
    public void draw(Graphics g) {
		loadSprite();
		g.drawImage(window, 0, 0, Game.gameWidth, Game.gameHeight, null);
		g.drawImage(closeBtn, 1275, 75, 64, 64, null);
		g.drawImage(soundBtn, Game.gameWidth/2-50, Game.gameHeight/2, 48, 64, null);
		g.drawImage(scrollBar, Game.gameWidth/2, Game.gameHeight/2, 144, 64, null);
		g.drawImage(scrollBtn, scrollBtnX, scrollBtnY, 48, 64, null);

    }
	
    public void loadSprite() {
    	BufferedImage temp = AssetManager.getSprite("/assets/settingsmenu/GUI_4x.png");
    	window = temp.getSubimage(0,0,64,64);
    	temp = AssetManager.getSprite("/assets/settingsmenu/buttons_4x.png");
    	closeBtn = temp.getSubimage(352,0, 32, 32);
    	soundBtn = temp.getSubimage(320, 32, 12, 32);
    	temp = AssetManager.getSprite("/assets/settingsmenu/scrollbars free.png");
    	scrollBar = temp.getSubimage(16, 32, 48, 16);
    	scrollBtn = temp.getSubimage(0, 0, 16, 16);
    }
    @Override
    public void update() {
    	
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
		
	}

    @Override
    public void mouseClicked(MouseEvent e) {

    	if(isIn(e,closeRect))
        	game.changeGameState(GameState.MENU);
    	else if(isIn(e,barRect) && !isIn(e,scrollBtnRect)) {
        	if(e.getX()>Game.gameWidth/2+128)
        		scrollBtnX = Game.gameWidth/2+96;
        	else
        		scrollBtnX = e.getX()-24;
        }else if(isIn(e,scrollBtnRect)) {
        	if(e.getX()<Game.gameWidth/2+32)
        		scrollBtnX = Game.gameWidth/2;
        	else
        		scrollBtnX = e.getX()-24;
        }	
    }
    @Override
    public void mousePressed(MouseEvent e) {
    	
    	
    }
    @Override
    public void mouseReleased(MouseEvent e) {

	}
    
    private boolean isIn(MouseEvent e, Rectangle rect) {
		return rect.contains(e.getX(), e.getY());
	}
}
