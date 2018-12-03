class Add extends OperatorExp
{	
	Add(ExpList e)
	{
		expList = e;
	}
	
	String getOp()
	{
		return "+";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("add " + now);
		expList.argumentCounter = current;
	}
	
}