public class NodeStmtWhile extends NodeStmt {
    private NodeBoolExpr boolExpr;
    private NodeStmt stmt;

    public NodeStmtWhile(NodeBoolExpr boolExpr, NodeStmt stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }

    public double eval(Environment env) throws EvalException {
        while (boolExpr.eval(env) == 1.0) {
            stmt.eval(env);
        }
        return 0.0;
    }
}
