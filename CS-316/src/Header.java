class Header
{
	String funName;
	ParameterList parameterList; // This is null if <parameter list> is empty.
	
	Header(String s, ParameterList p)
	{
		funName = s;
		parameterList = p;
		Compiler.hashLabel.put(funName, Compiler.count);
		Compiler.count++;
		
		int paramcount = 0;
		while (parameterList != null) {
			paramcount++;
			Compiler.hashSeqParam.put(parameterList.id, paramcount);
			parameterList = parameterList.parameterList;
			
		}
		Compiler.hashFormalParam.put(funName, paramcount);
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		IO.displayln(indent + indent.length() + " <header>");
		IO.displayln(indent1 + indent1.length() + " <fun name> " + funName);
		if ( parameterList != null )	
			parameterList.printParseTree(indent1);
	}
	
	void emitInstructions() {
		//Compiler.hashLabel.put(Compiler.getAndIncrementCount(), funName);
		if (parameterList != null) {
			parameterList.emitInstructions();
		}
		
		//System.out.println(funName);
	}
}