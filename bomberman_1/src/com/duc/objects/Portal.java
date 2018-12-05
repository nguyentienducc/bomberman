/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duc.objects;

import com.duc.animation.Animation;
import com.duc.animation.LoadData;
import static com.duc.objects.ParticularObject.ALIVE;
import static com.duc.objects.ParticularObject.SPEED_ITEM;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author DELL
 */
public class Portal extends ParticularObject{
    private Animation portal;

    public Portal(float x, float y, float width, float height, GameWorld gameWorld) {
        super(x, y, width, height, gameWorld);
        setState(ALIVE);
        setTeamType(PORTAL);
        portal = LoadData.getInstance().getAnimation("portal");
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();

        rect.x = (int) getPos_x() - 50;
        rect.y = (int) getPos_y() - 50;
        rect.width = 100;
        rect.height = 100;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {

        switch (getState()) {
            case ALIVE:
                portal.Update(System.nanoTime());

                portal.draw((int) (getPos_x() - getGameWorld().camera.getPos_x()), (int) getPos_y() - (int) getGameWorld().camera.getPos_y(), g2);
                if (portal.getCurrentFrame() == 1) {
                    portal.setIgnoreFrame(0);
                }
                break;

        }

    }

    @Override
    public void update() {
        super.update();

    }

}
