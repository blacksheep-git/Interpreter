public class NodeStmtWr extends NodeStmt {
    private NodeExpr expr;

    public NodeStmtWr(NodeExpr expr) {
        this.expr = expr;
    }

    public double eval(Environment env) throws EvalException {
        double eval = expr.eval(env);
        System.out.println(eval); //print evaluation of the double
        return eval;
    }
}
