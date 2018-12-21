import java.util.HashMap;

public class Compiler extends Parser
{
	public static int label = 0;
	public static final String indent = "\t";
	static int count = 1;
	
	public static HashMap<String, Integer>  hashLabel = new HashMap<String, Integer> ();
	public static HashMap<String, Integer> hashFormalParam = new HashMap<String, Integer>();
	public static HashMap<String, Integer> hashSeqParam = new HashMap<String, Integer>();

	public static void main(String argv[])
	{
		// argv[0]: input file containing an expression of category <exp>
		// argv[1]: output file containing instruction stream or lex/syntax error messages

		setIO( argv[0], argv[1] );
		setLex();
		
		getToken();
		FunDefListAndExp fdeflistandexp = funDefListAndExp();  // build a parse tree
		
		if ( ! t.isEmpty() ) 
			IO.displayln(t + "  -- unexpected symbol");
		else if ( ! syntaxErrorFound )
			fdeflistandexp.emitInstructions();
		//fdeflistandexp.emitInstructions();
		closeIO();
		//System.out.println(hashLabel.toString());
		//System.out.println(hashLabelRev.toString());
		//System.out.println(hashFormalParam.toString());
		
		
	}
	
}