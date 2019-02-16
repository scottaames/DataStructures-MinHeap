package p9_package;

public class GenericHeapClassMain
   {

   public static void main(String[] args)
      {
       int index, maxItems = 20;
       GenericHeapClass<OpCodeClass> ghc = new GenericHeapClass<OpCodeClass>();
       OpCodeClass newItem, removedItem;
       
       ghc.setDisplayFlag( true );
       
       System.out.println( "Adding Items to heap");
       System.out.println( "Items are added by priority first, and entry item next.");
       
       for( index = 0; index < maxItems; index++ )
          {
           newItem = new OpCodeClass( index + 1 );
           
           ghc.addItem( newItem );
           
           //ghc.showArray();
          }

       System.out.println( "\nRemoving Items from heap");

       while( !ghc.isEmpty() )
          {
           removedItem = ghc.removeItem();
           
           System.out.println( removedItem.toString() + "\n");
          }
          
      }

   }