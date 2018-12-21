class FunDefList
{
	FunDef funDef;
	FunDefList funDefList;
	
	FunDefList(FunDef f, FunDefList fl)
	{
		funDef = f;
		funDefList = fl;
	}

	void printParseTree(String indent)
	{
		FunDefList p = this;
		do
		{
			p.funDef.printParseTree(indent);
			IO.displayln("\n--------------------\n");
			p = p.funDefList;
		} while ( p != null );
	}
	
	void emitInstructions() {
		//IO.displayln(Compiler.indent + "goto " + Compiler.count);
		int funName = Compiler.hashLabel.get(funDef.header.funName);
		IO.displayln(funName + ":");
		funDef.emitInstructions();
		if (funDefList != null) {
		funDefList.emitInstructions();
		
		}
	}
}