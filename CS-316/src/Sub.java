class Sub extends OperatorExp
{
	Sub(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "-";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("sub " + now);
		expList.argumentCounter = current;
		
	}
}