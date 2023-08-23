public class NodeRelop extends Node {
    private String relop;
    private int pos;

    public NodeRelop(int pos, String relop) {
        this.relop = relop;
        this.pos = pos;
    }



    // compute relop, then convert boolean true/false to double 1.0/0.0
    public double op(double a, double b) throws EvalException {
        switch (relop) {
            case "==":
                return (a == b) ? 1.0 : 0.0;
            case "<>":
                return (a != b) ? 1.0 : 0.0;
            case ">":
                return (a > b) ? 1.0 : 0.0;
            case "<":
                return (a < b) ? 1.0 : 0.0;
            case ">=":
                return (a >= b) ? 1.0 : 0.0;
            case "<=":
                return (a <= b) ? 1.0 : 0.0;
            default:
                throw new EvalException(pos, "bogus relop: " + relop);
        }
    }

}
