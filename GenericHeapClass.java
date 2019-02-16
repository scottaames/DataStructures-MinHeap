package p9_package;

public class GenericHeapClass<GenericData extends Comparable<GenericData>>
{
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Array for heap
     */
    private Object[] heapArray;
    
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    
    /**
     * Default constructor sets up array management conditions 
     * and default display flag setting
     */
    public GenericHeapClass()
    {
        arraySize = 0;
        
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        
        displayFlag = false;
        
        heapArray = new Object[ arrayCapacity ];
    }
    
    /**
     * Copy constructor copies array and array management conditions and 
     * default display flag setting
     * 
     * @param copied - GenericHeapClass object to be copied
     */
    public GenericHeapClass( GenericHeapClass<GenericData> copied )
    {
        arraySize = copied.arraySize;
        
        arrayCapacity = copied.arrayCapacity;
        
        displayFlag = copied.displayFlag;
        
        heapArray = new Object[ arrayCapacity ];
        
        int index;
        
        for( index = 0; index < arraySize; index++ )
        {
            heapArray[ index ] = copied.heapArray[ index ];
        }
    }
    
    /**
     * Accepts GenericData item and adds it to heap at the end
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced 
     * heap after data addition
     * 
     * @param newItem - GenericData item to be added
     */
    public void addItem( GenericData newItem )
    {
        
        checkForResize();
        
        heapArray[ arraySize ] = newItem;
        
        if( displayFlag )
        {
           System.out.println( "Adding new process: " + 
                 heapArray[ arraySize ].toString() + "\n" );
        }
        
        bubbleUpArrayHeap( arraySize );
        
        arraySize++;
    }
    
    /**
     * Automatic resize operation used prior to any 
     * new data addition in the heap
     * <p>
     * Tests for full heap array, and resizes to twice the 
     * current capacity as required
     */
    private void checkForResize()
    {
       if( arraySize == arrayCapacity )
       {
           arrayCapacity *= 2;
            
           Object[] doubledHeapArray = new Object[ arrayCapacity ];
           int index;
           
           for( index = 0; index < arraySize; index++ )
           {
               doubledHeapArray[ index ] = heapArray[ index ];
           }
            
           heapArray = doubledHeapArray;
       }
   }
    
    /**
     * Tests for empty heap
     * 
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }
    
    /**
     * Removes GenericData item from top of min heap, thus being the 
     * operation with the lowest priority value
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced 
     * heap after data removal
     * 
     * @return GenericData item removed
     */
    @SuppressWarnings("unchecked")
   public GenericData removeItem()
    {
       GenericData minValue = null;

       if( !isEmpty() )
       {
          arraySize--;
          
          minValue = ( GenericData ) heapArray[ 0 ];
          
          heapArray[ 0 ] = ( GenericData ) heapArray[ arraySize ];
          
          heapArray[ arraySize ] = minValue;
          
          if( displayFlag )
          {
             System.out.println( "Removing process: " + 
                            minValue.toString() );
          }
          
          trickleDownArrayHeap( 0 );
       }
       
       return minValue;
    }
    
    /**
     * Utility method to set the display flag for displaying internal 
     * operations of the heap bubble and trickle operations
     * 
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag( boolean setState )
    {
        displayFlag = setState;
    }
    
    /**
     * displays the array
     */
    public void showArray()
    {
       int index;
       String outString = "";
     
       for( index = 0; index < arraySize; index++ )
       {
           if( index > 0 )
           {
               outString += " - ";
           }
         
           outString += heapArray[ index ].toString();
       }
     
       System.out.println( outString + "\n");
    }
    
    /**
     * Recursive operation to reset data in the correct order for 
     * the min heap after new data addition
     * 
     * @param currentIndex- index of current item being assessed, 
     * and moved up as needed
     */
    @SuppressWarnings("unchecked")
    private void bubbleUpArrayHeap( int currentIndex )
    {
       int parentIndex = (currentIndex - 1) / 2;
       
       GenericData parent, child;
       
       if ( currentIndex > 0 )
       {
           parent = ( GenericData ) heapArray[ parentIndex ];
           child = ( GenericData ) heapArray[ currentIndex ];
           
           if( parent.compareTo( child ) > 0 )
           {
               heapArray[ parentIndex ] = child;
               heapArray[ currentIndex ] = parent;
           }

           if( displayFlag )
           {
             System.out.println( "\t- Bubble up: \n \t - Swapping parent: " +
                parent.toString() + " with child: " +  child.toString() );
           }
          
           bubbleUpArrayHeap( parentIndex );
       }
    }
    
    /**
     * Recursive operation to reset data in the correct order for the 
     * min heap after data removal
     * 
     * @param currentIndex - index of current item being assessed, 
     * and moved down as required
     */
    @SuppressWarnings("unchecked")
   private void trickleDownArrayHeap( int currentIndex )
   {
        int leftChildIndex = 2 * currentIndex + 1;
        int rightChildIndex = 2 * currentIndex + 2;
        GenericData leftChildData, rightChildaData;
        GenericData parentData = (GenericData)heapArray[ currentIndex ];
        
        if( rightChildIndex < arraySize )
        {
            leftChildData = (GenericData)heapArray[ leftChildIndex ];
            rightChildaData = (GenericData)heapArray[ rightChildIndex ];
            
            if( rightChildaData.compareTo( parentData ) < 0 && 
                    leftChildData.compareTo( parentData ) < 0 )
            {
                heapArray[ currentIndex ] = rightChildaData;
                heapArray[ rightChildIndex ] = parentData;
                
                trickleDownArrayHeap( rightChildIndex );
            }
        }
        
        if( leftChildIndex < arraySize )
        {
            leftChildData = (GenericData)heapArray[ leftChildIndex ];
            
            if( leftChildData.compareTo( parentData ) < 0)
            {
                heapArray[ currentIndex ] = leftChildData;
                heapArray[ leftChildIndex ]= parentData;
                
                trickleDownArrayHeap( leftChildIndex );
            }
        }
   }
}