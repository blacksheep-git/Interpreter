// expression node
public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
		this.term=term;
		this.addop=addop;
		this.expr=expr;
    }

	// an expression can either be a term or a term followed by an expression, so this handles both cases.
    public void append(NodeExpr expr) {
		if (this.expr==null) { //just a term
			this.addop=expr.addop;
			this.expr=expr;
			expr.addop=null;
		} else // term followed by expression
			this.expr.append(expr);
    }

	//if node does not contain an expression then eval term and return, else eval expression
    public double eval(Environment env) throws EvalException {
		return expr==null
			? term.eval(env)
			: addop.op(expr.eval(env),term.eval(env));
    }

}
