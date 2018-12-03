class Div extends OperatorExp
{
	Div(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "/";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("div " + now);
		expList.argumentCounter = current;
	}
}