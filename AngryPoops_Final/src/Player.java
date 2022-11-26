import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.KEY_LEFT;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.KEY_RIGHT;

public class Player implements KeyboardHandler{
    private Picture shinSis;

    //will represent the number of poops caught
    private int points;

    private int lives = 5;


    public Player(int x, int y, String source){
        this.shinSis = new Picture(x, y, source);
        shinSis.draw();
        this.points = 0;
        initKeyboard();
    }


    //because Player implements KeyboardHandler, we need to give body to keyPressed and keyReleased methods

    public void keyPressed(KeyboardEvent keyboardEvent) {
     int key = keyboardEvent.getKey();
     //when we press left we will move the player 15 pixels to the left
     if(key == KEY_LEFT && shinSis.getX() != 5){
         shinSis.translate(-15, 0 );
         //when we press right we will move the player 15 pixels to the right
     } else if (key == KEY_RIGHT && shinSis.getX() != 1130) {
         shinSis.translate(15, 0);
     }
    }

    //when we don't press left or right the player doesn't move
    public void keyReleased(KeyboardEvent keyboardEvent) {
        int key = keyboardEvent.getKey();
        if(key == KEY_LEFT){
            shinSis.translate(0, 0 );
        } else if (key == KEY_RIGHT) {
            shinSis.translate(0, 0);

        }

    }

    //invoking this static method with an argument that's a player that has ability to me moved by keyboard's keys (aka Keyboard Handler)
    //we are able to move the player object to the left and to the right
    public void initKeyboard(){
        // receive a player that is also a KeyBoardHandler
        Keyboard keyboard = new Keyboard(this);

        //Creates a keyboard event for when we press Left and set the Key to KEY_LEFT and the type for KEY_PRESSED
        KeyboardEvent keyPressedLeft = new KeyboardEvent();
        keyPressedLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyPressedLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        //with the addEventListener we make possible the new keyboard Event to be "listened"
        keyboard.addEventListener(keyPressedLeft);

        //same as the above but for the right
        KeyboardEvent keyPressedRight = new KeyboardEvent();
        keyPressedRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyPressedRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyPressedRight);

    }

    public Picture getShinSis() {
        return shinSis;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
