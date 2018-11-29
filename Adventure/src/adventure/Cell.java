/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import java.io.Serializable;

/**
 *
 * @author Raymond
 */
public class Cell implements Serializable {
    private int x, y;
    private boolean visible;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        visible = false;
    }
    
    public Cell(int x, int y, boolean v) {
        this.x = x;
        this.y = y;
        visible = v;
    }

    /**
     * returns x position
     * pre: none
     * post: an int is returned
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * returns y position
     * pre: none
     * post: an int is returned
     * @return 
     */
    public int getY() {
        return y;
    }
    
    /**
     * checks if cell is visible
     * pre: none
     * post: boolean is returned
     * @return 
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * sets x position
     * pre: parameter x >= 0
     * post: x is set to the given x parameter
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * sets y position
     * pre: parameter y >= 0
     * post: y is set to parameter y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * sets the cell visible
     * pre: none
     * post: visible is set to b
     * @param b 
     */
    public void setVisible(boolean b) {
        visible = b;
    }
    
    /**
     * checks if two cells are at the same position
     * pre: none
     * post: a boolean is returned
     * @param c
     * @return 
     */
    @Override
    public boolean equals(Object c) {
        Cell testCell = (Cell)c;
        if (x == testCell.getX() && y == testCell.getY()) {
            return true;
        } else {
            return false;
        }
    }
    
    

}
