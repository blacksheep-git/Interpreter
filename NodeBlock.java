public class NodeBlock extends NodeStmt {

    private NodeBlock block;
    private NodeStmt stmt;

    public NodeBlock(NodeStmt stmt, NodeBlock block) {
        this.stmt = stmt;
        this.block = block;
    }

    public double eval(Environment env) throws EvalException {
        if (block == null) {
            return stmt.eval(env);
        }
        stmt.eval(env);
        return block.eval(env);
    }
}
