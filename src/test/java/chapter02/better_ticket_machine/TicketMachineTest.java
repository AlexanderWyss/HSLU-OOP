package chapter02.better_ticket_machine;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test class TicketMachineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TicketMachineTest
{


    @Test
    public void testTicketMachineValid() {
        assertEquals(100, new TicketMachine(100).getPrice());
    }
    @Test
    public void testTicketMachineInvalid() {
        assertEquals(0, new TicketMachine(0).getPrice());
    }
    
    @Test
    public void testGetBalance() {
        assertEquals(0, new TicketMachine(99).getBalance());
    }
}
