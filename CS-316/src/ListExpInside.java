
public class ListExpInside {

	If ifk;
	Cond cond;
	FunCall fc;
	OperatorExp opexp;

	public ListExpInside(If ifk) {
		this.ifk = ifk;
	}

	public ListExpInside(Cond cond) {
		this.cond = cond;
	}

	public ListExpInside(FunCall fc) {
		this.fc = fc;
	}

	public ListExpInside(OperatorExp opexp) {
		this.opexp = opexp;
	}

	/**
	void printParseTree(String indent) {
		String indent1 = indent + " ";

		if (aexp != null) {
			ifk.printParseTree(indent1);
		} else {
			IO.displayln(indent + indent.length() + " <quote>");
			IO.displayln(indent1 + " LParen");
			se.printParseTree(indent1);
			p.printParseTree(indent1);
			se2.printParseTree(indent1);
			IO.displayln(indent1 + " RParen");
		}
	}
	**/
}
