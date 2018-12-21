class FunDef
{
	Header header;
	Exp exp;
	
	FunDef(Header h, Exp e)
	{
		header = h;
		exp = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		IO.displayln(indent + indent.length() + " <fun def>");		
		header.printParseTree(indent1);
		exp.printParseTree(indent1);
	}
	
	void emitInstructions() {
		//Compiler.hashLabel.put(Compiler.count, header.funName);
		//int funName = Compiler.hashLabel.get(header.funName);
		int funName = Compiler.count;
		//IO.displayln(funName + ":");
		header.emitInstructions();
		exp.emitInstructions();
		IO.displayln(Compiler.indent + "return");
		
	}
}