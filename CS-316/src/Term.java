import java.util.*;

abstract class Term {
	Primary primary;

	abstract void printParseTree(String indent);
}