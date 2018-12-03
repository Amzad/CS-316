class Case extends CaseExp
{
	static int gotoCount = 1;
	Exp exp1;
	Exp exp2;
	
	Case(Exp e1, Exp e2)
	{
		exp1 = e1;
		exp2 = e2;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);	
		exp1.printParseTree(indent1);
		exp2.printParseTree(indent1);
	}

	@Override
	void emitInstructions() {
		gotoCount++;
		exp1.emitInstructions();
		IO.displayln("if_f goto " + gotoCount);
		exp2.emitInstructions();
		IO.displayln("goto 1");
		IO.display(gotoCount + ":\n");
		
	}
}