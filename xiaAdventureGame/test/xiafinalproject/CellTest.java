/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiafinalproject;

import xiaadventuregame.Cell;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raymond
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @Test
    public void testGetX() {
        System.out.println("* CellTest: testGetX()");
        Cell instance = new Cell(5, 6);
        int expResult = 5;
        int result = instance.getX();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetY() {
        System.out.println("* CellTest: testGetY()");
        Cell instance = new Cell(1, 2);
        int expResult = 2;
        int result = instance.getY();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsVisible() {
        System.out.println("* CellTest: testIsVisible()");
        Cell instance1 = new Cell(4,2);
        Cell instance2 = new Cell(4,2, true);
        assertFalse(instance1.isVisible());
        assertTrue(instance2.isVisible());        
    }
    
    @Test
    public void testSetX() {
        System.out.println("* CellTest: testSetX()");
        Cell instance = new Cell(2,3);
        instance.setX(4);
        assertEquals(4, instance.getX());
    }
    
    @Test
    public void testSetY() {
        System.out.println("* CellTest: testSetY()");
        Cell instance = new Cell(3,4);
        instance.setY(5);
        assertEquals(5, instance.getY());
    }
    
    @Test
    public void testSetVisible() {
        System.out.println("* CellTest: testSetVisible()");
        Cell instance1 = new Cell(3,4);
        Cell instance2 = new Cell(3,4, true);
        instance1.setVisible(true);
        assertEquals(true, instance1.isVisible());
        instance2.setVisible(false);
        assertEquals(false, instance2.isVisible());
    }
    
    @Test
    public void testEquals() {
        System.out.println("* CellTest: testEquals()");
        Cell instance = new Cell(1,2);
        Cell c1 = new Cell(2,3);
        Cell c2 = new Cell(1,2);
        assertFalse(instance.equals(c1));
        assertTrue(instance.equals(c2));        
    }

}
