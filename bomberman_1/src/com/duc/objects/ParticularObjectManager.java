/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duc.objects;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ParticularObjectManager {

    protected List<ParticularObject> particularObjects;

    private GameWorld gameWorld;

    public ParticularObjectManager(GameWorld gameWorld) {

        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
        this.gameWorld = gameWorld;

    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void addObject(ParticularObject particularObject) {

        synchronized (particularObjects) {
            particularObjects.add(particularObject);
        }

    }

    public void RemoveObject(ParticularObject particularObject) {
        synchronized (particularObjects) {

            for (int id = 0; id < particularObjects.size(); id++) {

                ParticularObject object = particularObjects.get(id);
                if (object == particularObject) {
                    particularObjects.remove(id);
                }

            }
        }
    }

    public ParticularObject getCollisionWidthEnemyObject(ParticularObject object) {
        synchronized (particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++) {

                ParticularObject objectInList = particularObjects.get(id);

                if (object.getTeamType() != objectInList.getTeamType()
                        && object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())) {
                    return objectInList;
                }
                
            }
        }
        return null;
    }

    public void updateObjects() {

        synchronized (particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++) {

                ParticularObject object = particularObjects.get(id);

                object.update();

                if (object.getState() == ParticularObject.DEATH) {
                    particularObjects.remove(id);
                }
            }
        }

    }

    public void draw(Graphics2D g2) {
        synchronized (particularObjects) {
            for (int id = particularObjects.size() - 1; id >= 0; id--) {

                ParticularObject object = particularObjects.get(id);

                if (!object.isObjectOutOfCameraView()) {
                    object.draw(g2);
                }
            }

        }
    }

}
