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
		funDefList.emitInstructions();
		exp.emitInstructions();

	}
}