package logicTree;

/**
 * Created by João on 07/06/2015.
 */
public class SelectorExpression extends LogicNode {

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

    public enum AttrOp {
        Attrib,
        Contains,
        NotContains,
        Begins,
        Ends,
        Includes,
        Prefix
    }

    AttrOp op;
    Type type;
    String evaluator, attribute;
    boolean negative;

    public SelectorExpression(Type type, String evaluator, boolean negative) {
        this.type = type;
        this.evaluator = evaluator;
        this.negative = negative;
    }

    public SelectorExpression(String op, String attribute, Type type, String evaluator, boolean negative) {

        this.type = type;
        this.evaluator = evaluator;
        this.negative = negative;
        this.attribute = attribute;

        if (op.equals("="))
            this.op = AttrOp.Attrib;
        else if (op.equals("*="))
            this.op = AttrOp.Contains;
        else if (op.equals("!="))
            this.op = AttrOp.NotContains;
        else if (op.equals("^="))
            this.op = AttrOp.Begins;
        else if (op.equals("$="))
            this.op = AttrOp.Ends;
        else if (op.equals("~="))
            this.op = AttrOp.Includes;
        else if (op.equals("|="))
            this.op = AttrOp.Prefix;

    }

    public String getText(String from) {
        String result = "";

        if (negative) {
            result += "!(";
        }

        switch (type) {
            case Attr:
                switch (op) {
                    case Attrib:
                        result += attribute + ".equals(\"" + evaluator + "\")";
                        break;
                    case Contains:
                        result += attribute + ".matches(\"(.*)" + evaluator + "(.*)\")";
                        break;
                    case NotContains:
                        result += "(!" + attribute + ".matches(\"(.*)" + evaluator + "(.*)\"))";
                        break;
                    case Begins:
                        result += attribute + ".matches(\"" + evaluator + "(.*)\")";
                        break;
                    case Ends:
                        result += attribute + ".matches(\"(.*)" + evaluator + "\")";
                        break;
                    case Includes:
                        result += attribute + ".matches(\"(.*)\\\\b" + evaluator + "\\\\b(.*)\")";
                        break;
                    case Prefix:
                        result += "(" + attribute + ".equals(\"" + evaluator + "\") || " + attribute + ".matches(\"" + evaluator + "-(.*)))";
                        break;
                }

                break;
        }


        if (negative) {
            result += ")";
        }

        return result;
    }
}
