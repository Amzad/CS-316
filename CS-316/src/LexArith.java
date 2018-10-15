/**
 * 
 * This class is a lexical analyzer for the tokens defined by the grammar:
 * 
⟨letter⟩ → a | b | ... | z | A | B | ... | Z 
⟨digit⟩ → 0 | 1 | ... | 9 
⟨basic id⟩ → ⟨letter⟩ {⟨letter⟩ | ⟨digit⟩} 
⟨letters and digits⟩ → {⟨letter⟩ | ⟨digit⟩}+ 
⟨id⟩ → ⟨basic id⟩ { ("_" | "−") ⟨letters and digits⟩ }    // Note: "_" is the underscore char, "−" is the hyphen (i.e. minus) char. 
⟨int⟩ → [+|−] {⟨digit⟩}+ 
⟨float⟩ → [+|−] ( {⟨digit⟩}+ "." {⟨digit⟩}  |  "." {⟨digit⟩}+ ) 
⟨floatE⟩ → ⟨float⟩ (e|E) [+|−] {⟨digit⟩}+ 
⟨add⟩ → + 
⟨sub⟩ → − 
⟨mul⟩ → * 
⟨div⟩ → / 
⟨lt⟩ → "<" 
⟨le⟩ → "<=" 
⟨gt⟩ → ">" 
⟨ge⟩ → ">=" 
⟨eq⟩ → "=" 
⟨LParen⟩ → "(" 
⟨RParen⟩ → ")" 
⟨quote⟩ → " ' "    // the single quote char 
⟨false⟩ → "#f" | "#F" 
⟨true⟩ → "#t" | "#T" 
⟨period⟩ → "." 

 * 
 * This class implements a DFA that will accept the above tokens.
 * 
 * The DFA states are represented by the Enum type "State". The DFA has the
 * following 10 final states represented by enum-type literals:
 * 
 * state token accepted
 * 
 * Id identifiers Int integers Float floats without exponentiation part FloatE
 * floats with exponentiation part Plus + Minus - Times * Div / LParen ( RParen
 * )
 * 
 * The DFA also uses the following 5 non-final states:
 * 
 * state string recognized
 * 
 * Start the empty string Period float parts ending with "." E float parts
 * ending with E or e EPlusMinus float parts ending with + or - in
 * exponentiation part
 * 
 * The function "driver" operates the DFA. The function "nextState" returns the
 * next state given the current state and the input character.
 * 
 * To recognize a different token set, modify the following:
 * 
 * enum type "State" and function "isFinal" function "nextState"
 * 
 * The functions "driver", "getToken" remain the same.
 * 
 **/

public abstract class LexArith extends IO {
	public enum State {
		// non-final states ordinal number

		Start, // 0
		Sharp, // 1
		PlusMinusPeriod, // 2
		UnderScoreMinus, // 3
		E, // 4
		EPlusMinus, // 5

		// final states
		Id, // 6
		Int, // 7
		Float, // 8
		FloatE, // 9
		Add, // 10
		Sub, // 11
		Mul, // 12
		Div, // 13
		Lt, // 14
		Gt, // 15
		Le, // 16
		Ge, // 17
		Eq, // 18
		LParen, // 19
		RParen, // 20
		Quote, // 21
		False, // 22
		True, // 23
		Period, // 24
		

		// keywords

		Keyword_define, Keyword_if, Keyword_cond, Keyword_else, Keyword_and, Keyword_or, Keyword_not, Keyword_equal, Keyword_car, Keyword_cdr, Keyword_cons,

		UNDEF;

		private boolean isFinal() {
			return (this.compareTo(State.Id) >= 0);
		}
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is greater than or equal to that of Id.

	// The following variables of "IO" class are used:

	// static int a; the current input character
	// static char c; used to convert the variable "a" to the char type whenever
	// necessary

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA

	private static int driver()

	// This is the driver of the FA.
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character,
	// returns -1.

	{
		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if (Character.isWhitespace((char) a))
			a = getChar(); // get the next non-whitespace character
		if (a == -1) // end-of-stream is reached
			return -1;

		while (a != -1) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			//System.out.println("1 " + t);
			nextSt = nextState(state, c);
			if (nextSt == State.UNDEF) // The FA will halt.
			{
				if (state.isFinal())
					return 1; // valid token extracted
				else // "c" is an unexpected character
				{
					t = t + c;
					a = getNextChar();
					return 0; // invalid token found
				}
			} else // The FA will go on.
			{
				state = nextSt;
				t = t + c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if (state.isFinal())
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if (i == 0)
			displayln(t + " : Lexical Error, invalid token");
	}

	private static State nextState(State s, char c)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned.

	{
		//System.out.println(t);
		switch (state) {

		case Start:
			if (Character.isLetter(c))
				return State.Id;
			else if (Character.isDigit(c))
				return State.Int;
			else if (c == '+')
				return State.Add;
			else if (c == '-')
				return State.Sub;
			else if (c == '.')
				return State.Period;
			else if (c == '*')
				return State.Mul;
			else if (c == '/')
				return State.Div;
			else if (c == '<')
				return State.Lt;
			else if (c == '>')
				return State.Gt;
			else if (c == '=')
				return State.Eq;
			else if (c == '(')
				return State.LParen;
			else if (c == ')')
				return State.RParen;
			else if (c == '\'')
				return State.Quote;
			else if (c == '#')
				return State.Sharp;
			else
				return State.UNDEF;

		case Id:
			
			if (Character.isLetterOrDigit(c)) {
				if ((t + c).equals("define")) {
					return State.Keyword_define;
				}

				if ((t + c).equals("if")) {
					return State.Keyword_if;
				}
				if ((t + c).equals("cond")) {
					return State.Keyword_cond;
				}
				if ((t + c).equals("else")) {
					return State.Keyword_else;
				}
				if ((t + c).equals("and")) {
					return State.Keyword_and;
				}
				if ((t + c).equals("or")) {
					return State.Keyword_or;
				}
				if ((t + c).equals("not")) {
					return State.Keyword_not;
				}
				if ((t + c).equals("equal")) {
					return State.Keyword_equal;
				}
				if ((t + c).equals("car")) {
					return State.Keyword_car;
				}
				if ((t + c).equals("cdr")) {
					return State.Keyword_cdr;
				}
				if ((t + c).equals("cons")) {
					return State.Keyword_cons;
				}
				return State.Id;
			} else if ((c == '-') || (c == '_')) {
				return State.UnderScoreMinus;
			} else
				return State.UNDEF;

		case UnderScoreMinus:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			} else {
				return State.UNDEF;
			}
		case Lt:
			if (c == '=') {
				return State.Le;
			} else {
				return State.UNDEF;
			}

		case Sharp:
			if ((c == 'f') || (c == 'F')) {
				return State.False;
			} else if ((c == 't') || (c == 'T')) {
				return State.False;
			} else {
				return State.UNDEF;
			}

		case Gt:
			if (c == '=') {
				return State.Ge;
			} else {
				return State.UNDEF;
			}

		case Int:
			if (Character.isDigit(c)) {
				return State.Int;
			} else if (c == '.') {
				return State.Float;
			} else {
				return State.UNDEF;
			}

		case Add:
			if (c == '.') {
				return State.PlusMinusPeriod;
			} else if (Character.isDigit(c)) {
				return State.Int;
			} else {
				return State.UNDEF;
			}

		case Sub:
			if (c == '.') {
				return State.PlusMinusPeriod;
			} else if (Character.isDigit(c)) {
				return State.Int;
			} else {
				return State.UNDEF;
			}

		case Period:
			if (Character.isDigit(c)) {
				return State.Float;
			} else {
				return State.UNDEF;
			}
			
		case EPlusMinus:
			if (Character.isDigit(c)) {
				return State.FloatE;
			} else {
				return State.UNDEF;
			}
			
		case PlusMinusPeriod:
			if (Character.isDigit(c)) {
				return State.Float;
			} else {
				return State.UNDEF;
			}

		case Float:
			if (Character.isDigit(c))
				return State.Float;
			else if (c == 'e' || c == 'E')
				return State.E;
			else
				return State.UNDEF;

		case E:
			if (Character.isDigit(c)) {
				return State.FloatE;
			} else if (c == '+' || c == '-') {
				return State.EPlusMinus;
			} else {
				return State.UNDEF;
			}

		case FloatE:
			if (Character.isDigit(c)) {
				return State.FloatE;
			} else {
				return State.UNDEF;
			}
			

		case Keyword_define:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_if:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}
		case Keyword_cond:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_else:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_and:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_or:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}
			
		case Keyword_not:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_equal:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_car:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_cdr:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		case Keyword_cons:
			if (Character.isLetterOrDigit(c)) {
				return State.Id;
			}
			if (c == '_') {
				return State.UnderScoreMinus;
			} else {
				return State.UNDEF;
			}

		default:
			return State.UNDEF;
		}

	} // end nextState

	public static void main(String argv[])

	{
		// argv[0]: input file containing source code using tokens defined above
		// argv[1]: output file displaying a list of the tokens
		//System.out.println("Testing");
		setIO(argv[0], argv[1]);

		int i;

		while (a != -1) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if (i == 1)
				displayln(t + "   : " + state.toString());
			else if (i == 0)
				displayln(t + " : Lexical Error, invalid token");
		}

		closeIO();
	}
}
