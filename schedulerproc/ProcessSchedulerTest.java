package schedulerproc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yasiro01
 */
public class ProcessSchedulerTest {

    public ProcessSchedulerTest() {
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
     * Test of useFirstComeFirstServe method, of class ProcessScheduler.
     */
    @Test
    public void testUseFirstComeFirstServe() {
        System.out.println("useFirstComeFirstServe");
        ProcessScheduler instance = new ProcessScheduler();
        instance.add(new SimpleProcess(24, 0, 0));
        instance.add(new SimpleProcess(3, 0, 0));
        instance.add(new SimpleProcess(3, 0, 0));

        double expResult = 17.0;
        double result = instance.useFirstComeFirstServe();

        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of useShortestJobFirst method, of class ProcessScheduler.
     */
    @Test
    public void testUseShortestJobFirst() {
        System.out.println("useShortestJobFirst");
        ProcessScheduler instance = new ProcessScheduler();
        instance.add(new SimpleProcess(24, 0, 1));
        instance.add(new SimpleProcess(3, 0, 2));
        instance.add(new SimpleProcess(3, 0, 3));

        double expResult = 3.0;
        double result = instance.useShortestJobFirst();

        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of usePriorityScheduling method, of class ProcessScheduler.
     */
    @Test
    public void testUsePriorityScheduling() {
        System.out.println("usePriorityScheduling");
        ProcessScheduler instance = new ProcessScheduler();
        instance.add(new SimpleProcess(24, 2, 1));
        instance.add(new SimpleProcess(3, 1, 2));
        instance.add(new SimpleProcess(3, 3, 3));

        double expResult = 10.0;
        double result = instance.usePriorityScheduling();

        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of useRoundRobin method, of class ProcessScheduler.
     */
    @Test
    public void testUseRoundRobin() {
        System.out.println("useRoundRobin");
        ProcessScheduler instance = new ProcessScheduler();
        instance.add(new SimpleProcess(24, 0, 1));
        instance.add(new SimpleProcess(3, 0, 2));
        instance.add(new SimpleProcess(3, 0, 3));

        double expResult = 5.666;
        double result = instance.useRoundRobin();

        assertEquals(expResult, result, 0.001);
    }

}
