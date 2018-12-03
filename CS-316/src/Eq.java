class Eq extends OperatorExp
{
	Eq(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "=";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("eq");
		expList.argumentCounter = current;
	}
}