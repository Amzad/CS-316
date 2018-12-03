class And extends OperatorExp
{
	And(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "and";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("and " + now);
		expList.argumentCounter = current;
	}
}