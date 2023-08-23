import java.util.Scanner;

// Read statement node class
public class NodeStmtRd extends NodeStmt {

    private static Scanner scanner = new Scanner(System.in); // to read user input('rd')
    private String lex;

    public NodeStmtRd(String lex) {
        this.lex = lex;
    }

    public double eval(Environment env) throws EvalException {
        return env.put(lex, scanner.nextDouble()); // read next double from user input and put in environment map
    }
}
