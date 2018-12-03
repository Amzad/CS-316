class Le extends OperatorExp
{
	Le(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "<=";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("le");
		expList.argumentCounter = current;
	}
}