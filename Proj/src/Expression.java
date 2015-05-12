/**
 * Created by João on 12/05/2015.
 */
public class Expression {
    public enum Type {
        All,
        Attr,
        Class,
        Eq,
        Gt,
        Lt,
        Even,
        Odd
    }

    private Type type;
    private String evaluator;
    private boolean neg;

    private String from;
    private String to;

    public Expression(Type type, String evaluator, boolean neg, String from, String to) {
        this.type = type;
        this.evaluator = evaluator;
        this.neg = neg;
        this.from = from;
        this.to = to;
    }

    public String print()
    {
        String ret = "";
        if (type == Type.Class)
        {
            ret = "(";
            if (neg)
                ret += " ! (";
            ret += from;
            ret += ".get(i) instanceof " + evaluator;
            if (neg)
                ret += ")";
            ret += ")";
            return ret;
        }

        return ret;
    }
}
