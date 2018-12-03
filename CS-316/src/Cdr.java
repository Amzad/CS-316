class Cdr extends OperatorExp
{
	Cdr(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "cdr";
	}
	
	void emitInstructions() 
	{
		expList.exp.emitInstructions();
		IO.displayln("cdr");
	}
}