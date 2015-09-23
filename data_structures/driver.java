package data_structures;
import java.util.Iterator;

public class driver {
    public static void main(String[] args) {
	    LinearListADT<Integer> list = new ArrayLinearList<Integer>(10);
        System.out.println("added stuff");
	    for(int i=1; i <= 10; i++) 
            list.addFirst(i);
        System.out.println("Should now print 10 .. 1");
        for(Integer i : list)
            System.out.println(i);
        System.out.println("Finished Printing 10 - 1");
        for(int i=1; i <= 10; i++) 
            if(list.removeFirst() == null)
                throw new RuntimeException("ERROR with removeFirst");
        System.out.println("finished removing stuff");
        for(Integer i : list)
            System.out.println(i);

        System.out.println("REMOVAL OKAY");
            
        if(!list.isEmpty())
            throw new RuntimeException("ERROR in inEmpty");  
        if(list.size() != 0)
            throw new RuntimeException("ERROR in size");
            
        System.out.println("Finished size checking");
        for(int i=1; i <= 1000; i++) {
            for(int j=1; j <= 7; j++)
                list.addFirst(j);
            for(int j=1; j <= 7; j++)                
                list.removeLast();
            }

            System.out.println("Finished adding and removing");
            
        for(int i=1; i <= 1000; i++) {
            for(int j=1; j <= 7; j++)
                list.addLast(j);
            for(int j=1; j <= 7; j++)                
                list.removeFirst();
            }
            System.out.println("Finished Adding and Removing seoncd time");
        list.addFirst(-1);
        System.out.println("negative one worked");
        if(list.peekLast() != -1)   
            throw new RuntimeException("ERROR in peekLast");
        list.clear();
        list.addLast(-1);
        if(list.peekFirst() != -1)   
            throw new RuntimeException("ERROR in peekLast"); 

        System.out.println("Peeking good.");
            
        list.clear();
        for(int i=1; i <= 10; i++)
            list.addFirst(i);
        System.out.println("Add First works");

        for(int i=1; i <= 10; i++){
            if(list.find(i) != i)
                throw new RuntimeException("ERROR in find"); 
        }
        System.out.println("Find works");
        for(int i=1; i <= 10; i++)
            if(!list.contains(i))
                throw new RuntimeException("ERROR in find"); 
        System.out.println("Contains works \n\n");

                System.out.println("size is: " + list.size());
        System.out.println(list.find(1));
        list.removeFirst();
        list.removeFirst();
        System.out.println("size is: " + list.size());
        System.out.println("Removed: " + list.remove(1));
        System.out.println("Find again: " + list.find(1) + "\n\n\n");
        System.out.println("size is: " + list.size());
        System.out.println("First is: " + list.peekFirst());


        System.out.println("\n\n Find 6: " +list.find(6));
        System.out.println("Remove 6: " + list.remove(6));
        System.out.println("Find 6 again: " + list.find(6));

        list.addFirst(1337);
        list.addFirst(2020);
        System.out.println("\n\n Find 1337: " +list.find(1337));
        System.out.println("Remove 1337: " + list.remove(1337));
        System.out.println("Find 1337 again: " + list.find(1337));


    }
}
