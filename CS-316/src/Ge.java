class Ge extends OperatorExp
{
	Ge(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return ">=";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("ge");
		expList.argumentCounter = current;
	}
}