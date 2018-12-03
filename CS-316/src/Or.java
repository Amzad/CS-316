class Or extends OperatorExp
{
	Or(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "or";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("or " + now);
		expList.argumentCounter = current;
	}
}