
public class Quote {

	String q;
	
	public Quote(String q) {
		this.q = q;
	}
	
	void printParseTree(String indent){
	       IO.displayln(indent + indent.length() + " <quote>");
	    }
}
