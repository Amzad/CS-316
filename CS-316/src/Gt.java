class Gt extends OperatorExp
{
	Gt(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return ">";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("gt");
		expList.argumentCounter = current;
	}
}