// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

public class Parser {

    private Scanner scanner;

	// match a token as string 's'
    private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
    }

	//return the last/current scanend token of the scanner
    private Token curr() throws SyntaxException {
		return scanner.curr();
    }

    private int pos() {
	return scanner.pos();
    }

	// parse multiplication operation
    private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(),"*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(),"/");
		}
	return null;
    }

	//parse relationship opperators
	private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(), "<");
		}
		if (curr().equals(new Token("<="))) {
			match("<=");
			return new NodeRelop(pos(), "<=");
		}
		if (curr().equals(new Token(">"))) {
			match(">");
			return new NodeRelop(pos(), ">");
		}
		if (curr().equals(new Token(">="))) {
			match(">=");
			return new NodeRelop(pos(), ">=");
		}
		if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(), "<>");
		}
		if (curr().equals(new Token("=="))) {
			match("==");
			return new NodeRelop(pos(), "==");
		}
		return null;
	}

	// parse add operation
    private NodeAddop parseAddop() throws SyntaxException {
	if (curr().equals(new Token("+"))) {
	    match("+");
	    return new NodeAddop(pos(),"+");
	}
	if (curr().equals(new Token("-"))) {
	    match("-");
	    return new NodeAddop(pos(),"-");
	}
	return null;
    }

	//parse factor
    private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("-"))) {  // if factor is '-' prefix return a unary minus node factor object
			match("-");
			return new NodeFactUnMinus(parseFact());
		}
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr=parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id=curr();
			match("id");
			return new NodeFactId(pos(),id.lex());
		}
		Token num=curr();
		match("num");
		return new NodeFactNum(num.lex());
    }
	//parse boolean expression
	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr exprA = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr exprB = parseExpr();
		NodeBoolExpr boolExpr = new NodeBoolExpr(exprA, relop, exprB);
		return boolExpr;
	}

	// parse term
    private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact=parseFact();
		NodeMulop mulop=parseMulop();
		if (mulop==null)
			return new NodeTerm(fact,null,null);
		NodeTerm term=parseTerm();
		term.append(new NodeTerm(fact,mulop,null));
		return term;
    }

	// parse expression
    private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term=parseTerm();
		NodeAddop addop=parseAddop();
		if (addop==null)
			return new NodeExpr(term,null,null);
		NodeExpr expr=parseExpr();
		expr.append(new NodeExpr(term,addop,null));
		return expr;
    }

	// parse Assignment
    private NodeAssn parseAssn() throws SyntaxException {
		Token id=curr();
		match("id");
		match("=");
		NodeExpr expr=parseExpr();
		return new NodeAssn(id.lex(),expr);
    }

	//parse statement
    private NodeStmt parseStmt() throws SyntaxException {
		if (curr().equals(new Token("rd"))) {
			match("rd");
			Token id = curr();
			match("id");
			return new NodeStmtRd(id.lex());
		}
		if (curr().equals(new Token("wr"))) {
			match("wr");
			return new NodeStmtWr(parseExpr());
		}
		if (curr().equals(new Token("if"))) {
			match("if");
			NodeBoolExpr boolexpr = parseBoolExpr();
			match("then");
			NodeStmt thenStmt = parseStmt();
			if (curr().lex().equals("else")) {
				match("else");
				return new NodeStmtIf(boolexpr, thenStmt, parseStmt());
			}
			return new NodeStmtIf(boolexpr, thenStmt, null);
		}
		if (curr().equals(new Token("while"))) {
			match("while");
			NodeBoolExpr boolexpr = parseBoolExpr();
			match("do");
			return new NodeStmtWhile(boolexpr, parseStmt());
		}
		if (curr().equals(new Token("begin"))) {
			match("begin");
			NodeBlock block = parseBlock();
			match("end");
			return new NodeStmtBegin(block);
		}
		return new NodeStmtAssn(parseAssn());
    }

	// parse block
	private NodeBlock parseBlock() throws SyntaxException {
		NodeStmt stmt = parseStmt();
		if (curr().equals(new Token(";"))) {
			match(";");
			return new NodeBlock(stmt, parseBlock());
		}
		return new NodeBlock(stmt, null);
	}

	// Parse program
	private Node parseProgram() throws SyntaxException {
		return parseBlock();
	}

	// use scanner object to scan the next string in 'program' and use the parseProgram() to parse the contents of the program
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		return parseProgram();
	}
}
