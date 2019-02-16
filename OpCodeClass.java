package p9_package;

import java.util.Random;

/**
 * Individual process action called op code is used
 * for queueing in an operating system to decide which
 * running action must be implemented next
 * <p>
 * op code has priority but it can also be given a
 * "queue entry" value meaning a numerical value related
 * to when the op code entered a queue
 *  
 * @author MichaelL
 *
 */
public class OpCodeClass implements Comparable<OpCodeClass>
   {
    /**
     * constant minimum priority value for generating op code priority
     */
    private final int MIN_PRIORITY_VAL = 100;
    
    /**
     * constant maximum priority value for generating op code priority
     */
    private final int MAX_PRIORITY_VAL = 999;
    
    /**
     * constant minimum number of cycles for generating op code
     * number of cycles
     */
    private final int MIN_NUM_CYCLES = 10;
    
    /**
     * constant maximum number of cycles for generating op code
     * number of cycles
     */
    private final int MAX_NUM_CYCLES = 99;
    
    /**
     * member holding priority of this op code
     */
    private int priority;
    
    /**
     * member holding number of cycles for this op code
     */
    private int numCycles;
    
    /**
     * member holding queue entry value which is set
     * as the op code is placed in a queue
     */
    private int queueEntryValue;
    
    /**
     * Default constructor
     */
    public OpCodeClass()
       {
        priority = 0;
        
        numCycles = 0;
        
        queueEntryValue = 0;
       }
    
    /**
     * Initialization constructor
     * 
     * @param priorityIn integer holding op code priority
     * 
     * @param numCyclesIn integer holding op code number of cycles
     * 
     * @param queueEntryIn integer holding queue entry value
     */
    public OpCodeClass( int priorityIn, int numCyclesIn, int queueEntryIn )
       {
        priority = priorityIn;
        
        numCycles = numCyclesIn;
        
        queueEntryValue = queueEntryIn;
       }
   
    /**
     * Copy constructor
     * 
     * @param copied OpCodeClass object to be copied
     */
    public OpCodeClass( OpCodeClass copied )
       {
        priority = copied.priority;
        
        numCycles = copied.numCycles;
        
        queueEntryValue = copied.queueEntryValue;
       }
   
    /**
     * Generation constructor - randomly generates op code
     * 
     * @param queueEntryIn integer holding queue entry value
     * provided to op code as it is enqueued
     */
    public OpCodeClass( int queueEntryIn )
       {
        priority = getRandBetween( MIN_PRIORITY_VAL, MAX_PRIORITY_VAL );
        
        numCycles = getRandBetween( MIN_NUM_CYCLES, MAX_NUM_CYCLES );
        
        queueEntryValue = queueEntryIn;
       }
    
    /**
     * Sets queue entry value
     * 
     * @param entryVal integer value holding entry value
     */
    public void setQueueEntryValue( int entryVal )
       {
        queueEntryValue = entryVal;
       }
    
    /**
     * Gets op code priority value
     * 
     * @return integer priority value
     */
    public int getPriorityValue()
       {
        return priority;
       }
    
    /**
     * Gets number of op code cycles
     * 
     * @return integer number of cycles in this op code
     */
    public int getNumCycles()
       {
        return numCycles;
       }
    
    /**
     * Gets the complete op code as a text quantity
     * 
     * @return String value holding op code in the form
     * "P(RunNN)PPP;" where NN is the number of cycles
     * and PPP is the priority value
     */
    @Override
    public String toString()
       {
        String outString = "P(Run" + numCycles + ")" + priority;
        
        if( queueEntryValue > 0 )
           {
            outString += "/" + queueEntryValue;
           }
        
        outString += ";";
        
        return outString;
       }
    
    /**
     * Comparison method required of Comparable interface
     * <p>
     * First compares priorities but if they are the same,
     * then compares enqueue value to see which one came
     * into the queue first
     */
    public int compareTo( OpCodeClass other )
       {
        int difference = this.priority - other.priority;
        
        if( difference != 0 )
           {
            return difference;
           }
        
        return this.queueEntryValue - other.queueEntryValue;
       }
    
    /**
     * Random generation of values between two numbers, inclusive
     * 
     * @param lowInclusive lowest value of result
     * 
     * @param highInclusive highest value of result
     * 
     * @return randomly generated value between the two given values, inclusive
     */
    private int getRandBetween( int lowInclusive, int highInclusive )
       {
        Random newRand = new Random();
        int range = highInclusive - lowInclusive + 1;
        
        return lowInclusive + newRand.nextInt( range );
       }
    

   }