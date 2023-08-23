public class NodeStmtIf extends NodeStmt {
    private NodeBoolExpr boolExpr;
    private NodeStmt thenStmt;
    private NodeStmt elseStmt;

    public NodeStmtIf(NodeBoolExpr boolExpr, NodeStmt thenStmt, NodeStmt elseStmt) {
        this.boolExpr = boolExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public double eval(Environment env) throws EvalException {
        if (boolExpr.eval(env) == 1.0) {
            return thenStmt.eval(env);
        } else if (elseStmt != null) { //if there is an else stmt
            return elseStmt.eval(env);
        }
        return 0.0;
    }
}
