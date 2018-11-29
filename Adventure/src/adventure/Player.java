/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import javax.swing.ImageIcon;

/**
 *
 * @author Raymond
 */
public class Player extends Cell {
    private int direction;
    private ImageIcon icon;
    private boolean alive;
    private Item[] inventory;
    
    public Player (int x, int y, boolean b, int d, ImageIcon icon) {
        super(x, y, b);
        direction = d;
        this.icon = icon;
        alive = true;
        inventory = new Item[1];
    }
        
    /**
     * returns player's direction
     * pre: none
     * post: an int is returned
     * @return 
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * checks is player is alive
     * pre: none
     * post: a boolean is returned
     * @return 
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * returns the player icon
     * pre: none
     * post: an ImageIcon is returned
     * @return 
     */
    public ImageIcon getIcon() {
        return icon;
    }
    
    /**
     * returns item in inventory at i
     * pre: i >= 0
     * post: an Item is returned
     * @param i
     * @return 
     */
    public Item getItem(int i) {
        return inventory[i];
    }
    
    /**
     * sets the player's direction
     * pre: d must be 0 or 1
     * post: direction is set to d
     * @param d 
     */
    public void setDirection(int d) {
        direction = d;
    }    
    
    /**
     * sets the player's icon
     * pre: none
     * post: icon is set to i
     * @param i 
     */
    public void setIcon(ImageIcon i) {
        icon = i;
    }
    
    /**
     * sets if the player is alive or not
     * pre: none
     * post: alive is set to a
     * @param a 
     */
    public void setAlive(boolean a) {
        alive = a;
    }
        
    /**
     * adds an item to the player's inventory, sets the item to obtained
     * pre: i >= 0
     * post: inventory at i is set to item, item is obtained
     * @param item
     * @param i 
     */
    public void addItem(Item item, int i) {
        inventory[i] = item;
        item.setObtained(true);
        item.setVisible(false);
    }
        
}
