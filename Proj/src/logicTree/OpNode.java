package logicTree;

/**
 * Created by João on 07/06/2015.
 */
public class OpNode extends LogicNode {
    LogicNode left, right;
    Type type;

    private enum Type {
        OR,
        AND
    }

    public OpNode(LogicNode left, LogicNode right, char op) {
        this.left = left;
        this.right = right;
        if (op == '|')
            this.type = Type.OR;
        else if (op == '&')
            this.type = Type.AND;
    }

    public String getOperator() {
        switch (type) {
            case OR:
                return "||";
            case AND:
                return "&&";
            default:
                return "";
        }
    }
}
