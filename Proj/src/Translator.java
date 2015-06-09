import logicTree.LogicNode;
import logicTree.LogicTree;
import logicTree.OpNode;
import logicTree.SelectorExpression;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.PrintStream;
import java.util.*;
import java.lang.Object;

/**
 * Created by João on 11/05/2015.
 */
public class Translator extends jJQueryParserBaseListener {

    ANTLRErrorListener strategy;
    HashMap<String, Boolean> vars = new HashMap<String, Boolean>();
    HashMap<String, String> colTypes = new HashMap<String, String>();

    boolean aborted = false;

    // Where to print to
    private PrintStream out;
    private TokenStreamRewriter rewriter;

    // Input and Output collections
    private String to;
    private String from;

    // State of denial :P
    private boolean neg;

    LogicTree tree;
    // Stack to build tree
    Stack<LogicNode> nodes;
    HashSet<String> attributes;

    ArrayList<LogicTree> trees;

    jJQueryParser parser;

    public Translator(PrintStream stream, TokenStreamRewriter rewriter, ANTLRErrorListener str, jJQueryParser parser) {
        out = stream;
        this.rewriter = rewriter;
        strategy = str;
        this.parser = parser;
        nodes = new Stack<LogicNode>();

        trees = new ArrayList<LogicTree>();
        attributes = new HashSet<String>();
    }

    @Override
    public void enterClassDeclaration(jJQueryParser.ClassDeclarationContext ctx) {
        rewriter.replace(ctx.Identifier().getSymbol(),"j"+ctx.Identifier().getText());
    }

    @Override
    public void enterJQueryBlock(jJQueryParser.JQueryBlockContext ctx) {

    }

    @Override
    public void exitJQueryBlock(jJQueryParser.JQueryBlockContext ctx) {

        if (!aborted) {
            String selectors = "";

            for (LogicTree tree : trees) {
                selectors += tree.toString() + '\n';
            }

            rewriter.replace(ctx.getStart(), ctx.getStop(), selectors);
        }

        aborted = false;

        trees.clear();
    }

    @Override
    public void enterLocalVariableDeclaration(jJQueryParser.LocalVariableDeclarationContext ctx) {

        String type = ctx.type().getText();

        for (int i = 0; i < ctx.variableDeclarators().variableDeclarator().size(); i++) {
            String identifier = ctx.variableDeclarators().variableDeclarator(i).variableDeclaratorId().getText();

            if (identifier.matches("\\w+\\[\\]")) {
                vars.put(identifier.split("\\[")[0], true);

                colTypes.put(identifier.split("\\[")[0],type);

            } else if (type.matches("\\w+<\\w+>")){
                vars.put(identifier,true);

                colTypes.put(identifier,type.subSequence(type.indexOf("<")+1,type.indexOf(">")).toString());

            } else {
                vars.put(identifier,false);
            }

        }
    }

    @Override
    public void enterIn(jJQueryParser.InContext ctx) {

        Boolean isCollection = vars.get(ctx.ID().getSymbol().getText());

        if (isCollection == null) {
            printError("Variable " + ctx.ID().getSymbol().getText() + " is not declared", ctx.ID().getSymbol().getLine());
            aborted = true;
        } else if (!isCollection) {
            printError("Variable " + ctx.ID().getSymbol().getText() + " is not a collection", ctx.ID().getSymbol().getLine());
            aborted = true;
        }
    }

    @Override
    public void enterOut(jJQueryParser.OutContext ctx) {
        Boolean isCollection = vars.get(ctx.ID().getSymbol().getText());

        if (isCollection == null) {
            printError("Variable " + ctx.ID().getSymbol().getText() + " is not declared", ctx.ID().getSymbol().getLine());
            aborted = true;
        } else if (!isCollection) {
            printError("Variable " + ctx.ID().getSymbol().getText() + " is not a collection", ctx.ID().getSymbol().getLine());
            aborted = true;
        }
    }

    @Override
    public void enterExpr(jJQueryParser.ExprContext ctx) {

        to = ctx.ID().getText();

        from = ctx.selector().ID(0).getText();

        nodes.clear();

    }

    @Override
    public void exitExpr(jJQueryParser.ExprContext ctx) {

        if (!aborted) {
            LogicTree tree = new LogicTree(from, to, nodes.pop(), colTypes, new ArrayList<String>(attributes));

            trees.add(tree);
        }

        attributes.clear();
    }


    @Override
    public void exitAndOp(jJQueryParser.AndOpContext ctx) {
        LogicNode right = nodes.pop();
        LogicNode left = nodes.pop();

        OpNode node = new OpNode(left,right,'&');
        nodes.push(node);
    }

    @Override
    public void exitOrOP(jJQueryParser.OrOPContext ctx) {
        LogicNode right = nodes.pop();
        LogicNode left = nodes.pop();

        OpNode node = new OpNode(left,right,'|');
        nodes.push(node);
    }

    @Override
    public void exitAttr_expr(jJQueryParser.Attr_exprContext ctx) {
        addSelectorExpression(ctx.op().getText(), ctx.attribute().getText() ,SelectorExpression.Type.Attr,ctx.evaluator().getText());
        attributes.add(ctx.attribute().getText());
    }

    @Override
    public void exitClass_expr(jJQueryParser.Class_exprContext ctx) {
        addSelectorExpression(SelectorExpression.Type.Class,ctx.ID().getText());
    }

    @Override
    public void exitEq_expr(jJQueryParser.Eq_exprContext ctx) {
        addSelectorExpression(SelectorExpression.Type.Eq,ctx.evaluator().getText());
    }

    @Override
    public void exitGt_expr(jJQueryParser.Gt_exprContext ctx) {
        addSelectorExpression(SelectorExpression.Type.Gt,ctx.evaluator().getText());
    }

    @Override
    public void exitLt_expr(jJQueryParser.Lt_exprContext ctx) {
        addSelectorExpression(SelectorExpression.Type.Lt, ctx.evaluator().getText());
    }

    @Override
    public void exitEven_expr(jJQueryParser.Even_exprContext ctx) {
        addSelectorExpression(SelectorExpression.Type.Even, "");
    }

    @Override
    public void exitOdd_expr(jJQueryParser.Odd_exprContext ctx) {
        addSelectorExpression(SelectorExpression.Type.Odd, "");
    }

    private void addSelectorExpression(SelectorExpression.Type type, String evaluator) {
        nodes.push(new SelectorExpression(type,evaluator,neg));
    }
    private void addSelectorExpression(String op, String attribute, SelectorExpression.Type type, String evaluator) {
        nodes.push(new SelectorExpression(op, attribute, type,evaluator,neg));
    }

    @Override
    public void enterNot_expr(jJQueryParser.Not_exprContext ctx) {
        neg = true;
    }

    @Override
    public void exitNot_expr(jJQueryParser.Not_exprContext ctx) {
        neg = false;
    }

    @Override
    public void exitCompilationUnit(jJQueryParser.CompilationUnitContext ctx) {
        out.print(rewriter.getText());
    }


    private void printError(String error, int line) {
        System.err.println("Error in line " + line + ":\t " + error );

    }


}
