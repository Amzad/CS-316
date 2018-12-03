class Mul extends OperatorExp
{
	Mul(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "*";
	}
	
	void emitInstructions() 
	{
		int current = expList.argumentCounter;
		expList.emitInstructions();
		int now = expList.argumentCounter - current;
		IO.displayln("mul " + now);
		expList.argumentCounter = current;
	}
}