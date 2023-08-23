public class NodeStmtBegin extends NodeStmt {
    private NodeBlock block;

    public NodeStmtBegin(NodeBlock block) {
        this.block = block;
    }

    public double eval(Environment env) throws EvalException {
        return block.eval(env);
    }
}
