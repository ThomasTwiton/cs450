/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulerproc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author twitth01
 */
public class SimpleProcessTest {
    
    public SimpleProcessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNextBurst method, of class SimpleProcess.
     */
    @Test
    public void testGetNextBurst() {
        System.out.println("getNextBurst");
        SimpleProcess instance = null;
        int expResult = 0;
        int result = instance.getNextBurst();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeLeft method, of class SimpleProcess.
     */
    @Test
    public void testGetTimeLeft() {
        System.out.println("getTimeLeft");
        SimpleProcess instance = null;
        int expResult = 0;
        int result = instance.getTimeLeft();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceTimeLeft method, of class SimpleProcess.
     */
    @Test
    public void testReduceTimeLeft() {
        System.out.println("reduceTimeLeft");
        int i = 0;
        SimpleProcess instance = null;
        instance.reduceTimeLeft(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriority method, of class SimpleProcess.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        SimpleProcess instance = null;
        int expResult = 0;
        int result = instance.getPriority();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrivalTime method, of class SimpleProcess.
     */
    @Test
    public void testGetArrivalTime() {
        System.out.println("getArrivalTime");
        SimpleProcess instance = null;
        int expResult = 0;
        int result = instance.getArrivalTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
