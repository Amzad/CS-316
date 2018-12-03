class Not extends OperatorExp
{
	Not(ExpList el)
	{
		expList = el;
	}

	String getOp()
	{
		return "not";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("not");
		expList.argumentCounter = current;
	}
}