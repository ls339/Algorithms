import java.util.ArrayList;
import java.util.Iterator;

public class LZWencode {

    ArrayList<ArrayList<Integer>> table = new ArrayList<ArrayList<Integer>>();
    //ArrayList<Integer> s 
    public LZWencode() {

	for(int i = 0;i <= 258;i++) {
	    table.add(new ArrayList<Integer>(i));
	}
	//this.printTable();
	Iterator<ArrayList<Integer>> itr = table.iterator();
	
    }

    public void printTable() {
	Iterator<ArrayList<Integer>> itr = table.iterator();
	while(itr.hasNext()) {
	    System.out.println(itr.next());
	}
    }
    
    public static void main(String[] args) {

	LZWencode lzw = new LZWencode();
	//lzw.printTable();
    }
}
