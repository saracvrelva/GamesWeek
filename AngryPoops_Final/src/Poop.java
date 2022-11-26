import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

public class Poop {
    private final Picture picture;

    //pixelsY counts the number of pixels the poop moves;
    private int pixelsY;

    private static int lostPoops;

    private char identifier;


    //when we create the poop we will immediately draw it in the screen
    public Poop(int x, int y, String source, char identifier) {
        this.picture = new Picture(x, y, source);
        this.pixelsY = 0;
        picture.draw();
        this.identifier = identifier;

    }

    public static void movePoops(Player player, ArrayList<Poop> poops) throws InterruptedException {
        //this array will save the index in the array of all the poops that already disappeared from the screen
        ArrayList<Integer> index = new ArrayList<Integer>();
        // array of poops --> this array belongs to Shin Chan, and it saves all the poops he makes
        // for every poop in poops we will move one by one until is caught by ShinSis or until it reaches the floor
        // the movement of the poops occur one by one but because it all happens so fast (in milis) we have the impression that all the poops move at the same time
        for (Poop p:poops) {

                //we will move the poop below by one (if I want to improve velocity of the poop, 2 instead of 1, for example,
                    // I need to change my pixelsY below in the code, to make the poop disappear if pixelsY >= 500/2, and so one)
                p.picture.translate(0, 1);
                p.pixelsY++;

                //position of shinSis on the X and Y
                int shinSisX = player.getShinSis().getX();
                int shinSixY = player.getShinSis().getY();

                //if the poop gets near the position of shinSis, shinSis will make a point and the poop disappears
                if (p.picture.getX() >= shinSisX && p.picture.getX() <= shinSisX + 100 && p.picture.getY() >= shinSixY + 50 && p.picture.getY() <= shinSixY + 100 && p.identifier == 'P') {
                    p.picture.delete();
                    //adds to the index's arrayList the index of the deleted poop, caught by shinSis
                    index.add(poops.indexOf(p));
                    //increment shinSisPoints
                    player.setPoints(player.getPoints() + 1);

                    //if it is a bomb it will decrease the lives of the player
                } else if (p.picture.getX() >= shinSisX && p.picture.getX() <= shinSisX + 100 && p.picture.getY() >= shinSixY + 50 && p.picture.getY() <= shinSixY + 100 && p.identifier == 'B') {
                    p.picture.delete();
                    //adds to the index's arrayList the index of the deleted bomb
                    index.add(poops.indexOf(p));
                    //decrement lives by 5
                    player.setLives(player.getLives() - 3);

                } else if (p.picture.getX() >= shinSisX && p.picture.getX() <= shinSisX + 100 && p.picture.getY() >= shinSixY + 50 && p.picture.getY() <= shinSixY + 100 && p.identifier == 'T') {
                    p.picture.delete();
                    //adds to the index's arrayList the index of the deleted toilet paper
                    index.add(poops.indexOf(p));
                    //increment shinSis lives by 2
                    player.setLives(player.getLives() + 1);
                }
                //when the poop reaches the 550 pixels moved it will disappear
                //the 550 pixelsY is not a final number. It can be 275 (550/2) if we determine that poop.translate(0,2), for example
                else if(p.pixelsY >= 550 && p.identifier == 'P') {
                lostPoops ++;
                player.setLives(player.getLives() - 1);
                p.getPicture().delete();
                //adds to the index's arrayList the index of the deleted poop
                index.add(poops.indexOf(p));
            } else if(p.pixelsY >= 550 && p.identifier == 'B') {
                    p.getPicture().delete();
                    //adds to the index's arrayList the index of the deleted poop
                    index.add(poops.indexOf(p));
                } else if (p.pixelsY >= 550 && p.identifier == 'T') {
                    p.getPicture().delete();
                    //adds to the index's arrayList the index of the deleted poop
                    index.add(poops.indexOf(p));

                }
        }
        // if my array of Index's is not empty I will remove all the poops from the array of poops with the index's saved on the index's arrayList
        if(index.size() > 0 ) {
            for (int i:index) {
                poops.remove(i);
            }
        }
    }

    public static int getLostPoops() {
        return lostPoops;
    }

    public Picture getPicture() {
        return this.picture;
    }
}
