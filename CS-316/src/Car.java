class Car extends OperatorExp
{
	Car(ExpList e)
	{
		expList = e;
	}

	String getOp()
	{
		return "car";
	}
	
	void emitInstructions() 
	{
		expList.exp.emitInstructions();
		IO.displayln("car");
	}
}