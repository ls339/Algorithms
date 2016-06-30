import java.util.ArrayList;
import java.util.Iterator;

public class LZWencode {

    ArrayList<ArrayList<Integer>> table = new ArrayList<ArrayList<Integer>>();
    
    public LZWencode() {

	for(int i = 0;i < 258;i++) {
	    ArrayList<Integer> e = new ArrayList<Integer>();
	    e.add(i);
	    table.add(e);
	}
    }

    public ArrayList<Integer> encode(ArrayList<Integer> input) {

	ArrayList<Integer> output = new ArrayList<Integer>();
	ArrayList<Integer> s = new ArrayList<Integer>();
	ArrayList<Integer> sn = new ArrayList<Integer>();
	ArrayList<Integer> n = new ArrayList<Integer>();
	Iterator<Integer> itr = input.iterator();
	output.add(256);
	
	while(itr.hasNext()) {
	    n.clear();
	    sn.clear();
	    n.add(itr.next());
	    sn.addAll(s);
	    sn.addAll(n);
	    if(table.contains(sn)) {
		s.addAll(n);
	    } else {
		output.add(table.indexOf(s));
		table.add(new ArrayList<Integer>());
		ArrayList<Integer> tmp = table.get(table.size()-1);
		tmp.addAll(sn);
		s.clear();
		s.add(n.get(0));
	    }
	}
	output.add(table.indexOf(s));
	output.add(257);
	return output;
    }

    public void printTable() {
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
	//lzw.printTable();
	System.out.println(output);
    }
}
