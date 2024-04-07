/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

/**
 *
 * @author Valeena
 */
public abstract class MainCharacters extends AbstractCharacter {
    
    public MainCharacters  () {
    }

    @Override
    public void move(int index) {
        int x = point.x;
        int y = point.y;
        if (movement.getCurrent() == Direction.UP) {
            y -= velocity;
        } else if (movement.getCurrent() == Direction.DOWN) {
            y += velocity;
        } else if (movement.getCurrent() == Direction.LEFT) {
            x -= velocity;
        } else if (movement.getCurrent() == Direction.RIGHT) {
            x += velocity;
        }
        if (collision(index, x, y)) {
            movement.setCurrent(getRandomDirection());
            move(index);
            return;
        }
        point.x = x;
        point.y = y;
    }
    
}
