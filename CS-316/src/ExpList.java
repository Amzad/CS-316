class ExpList
{
	Exp exp;
	ExpList expList;
	static int argumentCounter = 0;
	
	ExpList(Exp e, ExpList el)
	{
		exp = e;
		expList = el;
	}
	
	void printParseTree(String indent)
	{
		ExpList p = this;
		do
		{
			p.exp.printParseTree(indent);
			p = p.expList;
		} while ( p != null );
	}
	
	void emitInstructions() {
		exp.emitInstructions();
		if (expList != null) {
			expList.emitInstructions();
		}
		argumentCounter++;
		//IO.display("push ");
	}
}