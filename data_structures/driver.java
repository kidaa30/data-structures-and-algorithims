package data_structures;

public class driver {
    public static void main(String[] args) {
	    LinearListADT list = new ArrayLinearList(10);
	    System.out.println(list.isEmpty());
	    list.addLast("Something");
	    System.out.println(list.isEmpty());
    }
}
