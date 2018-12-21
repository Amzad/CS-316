class FunDefListAndExp
{
	FunDefList funDefList;
	Exp exp;
	
	FunDefListAndExp(FunDefList f, Exp e)
	{
		funDefList = f;
		exp = e;
	}
	
	void printParseTree(String indent)
	{
		funDefList.printParseTree(indent);
		exp.printParseTree(indent);
	}

	void emitInstructions(){
		//int funName = Compiler.hashLabel.get(funDefList.funDef.header.funName);
		int size = Compiler.hashLabel.size() + 1;
		IO.displayln(Compiler.indent + "goto " + size);
		Compiler.label = size;
		funDefList.emitInstructions();
		IO.displayln(Integer.toString(Compiler.count)+ ":");
		exp.emitInstructions();

	}
	
}