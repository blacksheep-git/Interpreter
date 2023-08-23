// Node for unary minus fact
public class NodeFactUnMinus extends NodeFact {
    private NodeFact fact;
    public NodeFactUnMinus(NodeFact fact) {
        this.fact = fact;
    }

    // return the mulopp of -1 * num
    public double eval(Environment env) throws EvalException {
        return (-1 * fact.eval(env));
    }
}
