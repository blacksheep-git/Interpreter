public class NodeBoolExpr extends Node {
    private NodeExpr exprA;
    private NodeExpr exprB;
    private NodeRelop relop;

    public NodeBoolExpr(NodeExpr exprA, NodeRelop relop, NodeExpr exprB) {
        this.exprA = exprA;
        this.relop = relop;
        this.exprB = exprB;
    }

    public double eval(Environment env) throws EvalException {
        return relop.op(exprA.eval(env), exprB.eval(env));
    }
}
