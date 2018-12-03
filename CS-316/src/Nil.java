class Nil extends SExp
{
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " ()");
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		IO.displayln("push nil");
	}
}