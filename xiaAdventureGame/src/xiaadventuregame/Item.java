/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaadventuregame;

import javax.swing.ImageIcon;

/**
 *
 * @author Raymond
 */
public class Item extends Cell {
    private final ImageIcon icon;
    private boolean obtained;
    
    public Item(int x, int y, boolean b, ImageIcon i) {
        super(x, y, b);
        icon = i;
        obtained = false;
        
    }
        
    /**
     * returns item icon
     * pre: none
     * post: ImageIcon is returned
     * @return 
     */
    public ImageIcon getIcon() {
        return icon;
    }
    
    /**
     * checks if item is obtained
     * pre: none
     * post: boolean is returned
     * @return 
     */
    public boolean isObtained() {
        return obtained;
    }
    
    /**
     * sets if item is obtained
     * pre: none
     * post: obtained is set to b
     * @param b 
     */
    public void setObtained(boolean b) {
        obtained = b;
    }    
    
}
