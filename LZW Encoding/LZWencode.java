import java.util.ArrayList;
import java.util.Iterator;

public class LZWencode {

    Boolean debug = true;
    ArrayList<ArrayList<Integer>> table = new ArrayList<ArrayList<Integer>>();
    //ArrayList<Integer> s = new ArrayList<Integer>();
    //ArrayList<Integer> output = new ArrayList<Integer>();
    
    public LZWencode() {

	for(int i = 0;i < 258;i++) {
	    ArrayList<Integer> e = new ArrayList<Integer>();
	    e.add(i);
	    table.add(e);
	}
	/*
	this.printTable();
	Iterator<ArrayList<Integer>> itr = table.iterator();
	while(itr.hasNext()) {
	    System.out.println(itr.next());
	}
	*/
    }

    public ArrayList<Integer> encode(ArrayList<Integer> input) {
	ArrayList<Integer> output = new ArrayList<Integer>();
	ArrayList<Integer> s = new ArrayList<Integer>();
	ArrayList<Integer> sn = new ArrayList<Integer>();
	ArrayList<Integer> n = new ArrayList<Integer>();
	Iterator<Integer> itr = input.iterator();
	while(itr.hasNext()) {
	    n.clear();
	    sn.clear();
	    n.add(itr.next());
	    sn.addAll(s);
	    sn.addAll(n);
	    if(debug) {
		System.out.println("n =" + n);
		System.out.println("s =" + s);
		System.out.println("sn =" + sn);
		System.out.println("-----");
	    }
	    if(table.contains(sn)) {
		s.addAll(n);
		if(debug) System.out.println("table["+(table.size()-1) + "] = " + table.get(table.size()-1));
		if(debug) System.out.println("table contains sn? Yes: s = " + s);
	    } else {
		output.add(table.indexOf(s));
		if(debug) System.out.println("Adding to output " + table.indexOf(s));
		if(debug) System.out.println("sn class = " + sn.getClass());

		// Bug here, list does not grow. Keeps appending to the same last element.
		// Need to add new object
		table.add(new ArrayList<Integer>());
		table.get(table.size()-1).add(sn);
		// Fix me
		
		if(debug) System.out.println("Appending to table s + n : " + table.get(table.size()-1));
		if(debug) System.out.println("table contains sn? No: sn = " + sn);
		s.clear();
		s.add(n.get(0));
	    }
	}
	//System.out.println(n);
	return output;
    }

    public void printTable() {
	/*
	Iterator<ArrayList<Integer>> itr = table.iterator();
	while(itr.hasNext()) {
	    System.out.println(itr.next());
	}
	*/
	System.out.println(table);
    }
    
    public static void main(String[] args) {

	ArrayList<Integer> input = new ArrayList<Integer>();
	ArrayList<Integer> output;

	// Example Input
	input.add(1);
	input.add(2);
	input.add(3);
	input.add(1);
	input.add(2);
	input.add(3);
	input.add(1);
	input.add(2);
	input.add(3);
   
	LZWencode lzw = new LZWencode();
	output = lzw.encode(input);
	lzw.printTable();
	//System.out.println(output);
	/*
	Iterator<Integer> itr = output.iterator();
	System.out.print("[ ");
	while(itr.hasNext()) {
	    System.out.print(itr.next()+", ");
	}
	System.out.println("]");
	*/
    }
}
