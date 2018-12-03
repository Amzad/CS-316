class Lt extends OperatorExp
{
	Lt(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "<";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("lt");
		expList.argumentCounter = current;
	}
}