package data_structures;

public class driver {
    public static void main(String[] args) {
	    ArrayLinearList list = new ArrayLinearList(10);

	    System.out.println("list is empty.");
	    System.out.println("(true) List should be empty: " + list.isEmpty()); 
	    System.out.println("(false) List should not be full: " + list.isFull());

	    System.out.println("Add Last");
	    list.addLast("uno");
	    System.out.println("(false) List should not be empty: " + list.isEmpty()); 
	    System.out.println("(false) List should not be full: " + list.isFull()); 

	    System.out.println("Add First");
	    list.addFirst("dos");
	    System.out.println("(false) List should not be empty: " + list.isEmpty()); 
	    System.out.println("(false) List should not be full: " + list.isFull()); 

	    System.out.println("Clearing List.");
	    list.clear();
	    System.out.println("(true) List should be empty: " + list.isEmpty()); 
	    System.out.println("(false) List should not be full: " + list.isFull()); 

	    System.out.println("Add First");
	    list.addFirst("uno");
	    System.out.println("(false) List should not be empty: " + list.isEmpty()); 
	    System.out.println("(false) List should not be full: " + list.isFull()); 

	    System.out.println("Add Last");
	    list.addLast("dos");
	    System.out.println("(false) List should not be empty: " + list.isEmpty()); 
	    System.out.println("(false) List should not be full: " + list.isFull()); 

	    System.out.println("Peek First");
	    System.out.println("(Uno) Should be Uno: " + list.peekFirst());
	    System.out.println("Peek First");
	    System.out.println("(Dos) Should be Dos: " + list.peekLast());

	    System.out.println("Adding First.");
	    list.addFirst("tres");
	    System.out.println("(Tres) Should be tres: " + list.peekFirst());
	    System.out.println("(Dos) Should be Dos: " + list.peekLast());

  	    System.out.println("Adding Last.");
	    list.addLast("cuatro");
	    System.out.println("(Tres) Should be tres: " + list.peekFirst());
	    System.out.println("(Cuatro) Should be Cuatro: " + list.peekLast());


	    System.out.println("------ REMOVAL --------");
	    list.printArray();
	    System.out.println("Removing First.");
	    list.removeFirst();
	    System.out.println("(uno) Should be Uno: " + list.peekFirst());
	   	System.out.println("(cuatro) Should be Cuatro: " + list.peekLast());
	   	System.out.println("Removing Last.");
	   	list.removeLast();
	    System.out.println("(uno) Should be Uno: " + list.peekFirst());
	   	System.out.println("(dos) Should be Dos: " + list.peekLast());

  	    System.out.println("Adding First.");
	    list.addFirst("44");
	    System.out.println("(44) Should be 44: " + list.peekFirst());
	    System.out.println("(Dos) Should be Dos: " + list.peekLast());

  	    System.out.println("Adding Last.");
	    list.addLast("1337");
	    System.out.println("(44) Should be 44: " + list.peekFirst());
	    System.out.println("(1337) Should be 1337: " + list.peekLast());

	    System.out.println("(4) Size should be 4: " + list.size());
	    list.printArray();

	    System.out.println("Removing Last.");
	    list.removeLast();
	    System.out.println("(44) Should be 44: " + list.peekFirst());
	    System.out.println("(dos) Should be Dos: " + list.peekLast());
	    System.out.println("Removing Last Again.");
	    list.removeLast();
	    System.out.println("(44) Should be 44: " + list.peekFirst());
	    System.out.println("(uno) Should be Uno: " + list.peekLast());
	  
    }
}
