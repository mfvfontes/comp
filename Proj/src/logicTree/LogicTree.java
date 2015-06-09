package logicTree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by João on 07/06/2015.
 */
public class LogicTree {
    String from, to;
    LogicNode root;
    HashMap<String, String> colTypes;
    ArrayList<String> attributesList;

    public LogicTree(String from, String to, LogicNode root, HashMap<String, String> colTypes, ArrayList<String> attributesList) {
        this.from = from;
        this.to = to;
        this.root = root;
        this.colTypes = colTypes;
        this.attributesList = attributesList;
    }

    @Override
    public String toString() {

        String index = from + "Index";


        String forIterationOpen = "for (int " + index + " = 0; " + index + " < " + from + ".size(); " + index + " ++) {\n";

        String declaration = colTypes.get(from) + " obj = " + from + ".get(" + index + ");\n";

        String attribCheck = "";

        if (attributesList.size() > 0) {

            attribCheck = "if (! (obj." + attributesList.get(0) + " instanceof String ";

            for (int attributeIndex = 1; attributeIndex < attributesList.size(); attributeIndex++) {
                attribCheck += "&& obj." + attributesList.get(attributeIndex) + " instanceof String ";
            }

            attribCheck += ")) {\nSystem.err.println(\"At least one of the required attributes is not of type String\");\n}\n else {\n";

            for (int attributeIndex = 0; attributeIndex < attributesList.size(); attributeIndex++) {
                attribCheck += "String " + attributesList.get(attributeIndex) + " = (String) obj." + attributesList.get(attributeIndex) + ";\n" ;
            }
        }

        String evaluator = "if (" + logicExpression(root) + ")\n";

        String add = to + ".add(obj);\n";

        String closeAttribCheck = "";
        if (attributesList.size() > 0) {
            closeAttribCheck = "}\n";
        }

        String forIterationClose = "}\n";

        return forIterationOpen + declaration + attribCheck + evaluator + add + closeAttribCheck + forIterationClose;
    }

    private String logicExpression(LogicNode node) {
        if (node instanceof OpNode) {
            return "(" + logicExpression(((OpNode) node).left) + ((OpNode) node).getOperator() + logicExpression(((OpNode) node).right) + ")" ;
        } else if (node instanceof SelectorExpression) {
            return ((SelectorExpression) node).getText(from);
        } else {
            return "";
        }
    }
}
