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
public class Room implements Serializable {
    private int[][] grid;
    private final int roomX;
    private final int roomY;
    private final String[] dialogue;
    private int dialogueLine;
    private Item[] items;
    
    public Room(int[][] map, int x, int y, String[] d) {
        grid = map; 
        roomX = x;
        roomY = y;
        dialogue = d;
        dialogueLine = 0;
        items = new Item[0];
    }
    
    public Room(int[][] map, int x, int y, String[] d, Item[] i) {
        grid = map; 
        roomX = x;
        roomY = y;
        dialogue = d;
        dialogueLine = 0;
        items = i;
    }
        
    /**
     * returns the room's map
     * pre: none
     * post: int[][] is returned
     * @return 
     */
    public int[][] getMap() {
        return grid;
    }
    
    /**
     * returns room's x position in the level
     * pre: none
     * post: int is returned
     * @return 
     */
    public int getX() {
        return roomX;
    }
    
    /**
     * returns room's y position in the level
     * pre: none
     * post: int is returned
     * @return 
     */
    public int getY() {
        return roomY;
    }
    
    /**
     * returns the room's list of dialogue
     * pre: none
     * post: a String[] is returned
     * @return 
     */
    public String[] getDialogue() {
        return dialogue;
    }
    
    /**
     * returns the dialogue line pointer
     * pre: none
     * post: int is returned
     * @return 
     */
    public int getDialogueLine() {
        return dialogueLine;
    }
    
    public Item[] getItems() {
        return items;
    }
    
    /**
     * sets the dialogue line pointer
     * pre: l >= 0 and l < dialogue.length-1
     * post: dialogueLine is set to l
     * @param l 
     */
    public void setDialogueLine(int l) {
        dialogueLine = l;
    }
    
    
        
}
