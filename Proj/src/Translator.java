import com.sun.org.apache.xpath.internal.operations.Bool;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by João on 11/05/2015.
 */
public class Translator extends jJQueryParserBaseListener {

    ANTLRErrorListener strategy;
    HashMap<String, Boolean> vars = new HashMap<String, Boolean>();
    HashMap<String, String> colTypes = new HashMap<String, String>();

    // Where to print to
    private PrintStream out;
    private TokenStreamRewriter rewriter;

    // Input and Output collections
    private String to;
    private String from;

    // State of denial :P
    private boolean neg;

    // Collection of Expressions
    private ArrayList<Expression> expressions;

    public Translator(PrintStream stream, TokenStreamRewriter rewriter, ANTLRErrorListener str) {
        out = stream;
        this.rewriter = rewriter;
        strategy = str;
    }

    @Override
    public void enterJQueryBlock(jJQueryParser.JQueryBlockContext ctx) {
        Token start = ctx.OPENTAG().getSymbol();

        rewriter.replace(start,"/* start jQuery Block */");

        Token end = ctx.CLOSETAG().getSymbol();

        rewriter.replace(end,"/* end jQuery Block */");
    }

    @Override
    public void enterLocalVariableDeclaration(jJQueryParser.LocalVariableDeclarationContext ctx) {

        String type = ctx.type().getText();

        for (int i = 0; i < ctx.variableDeclarators().variableDeclarator().size(); i++) {
            String identifier = ctx.variableDeclarators().variableDeclarator(i).variableDeclaratorId().getText();

            if (identifier.matches("\\w+\\[\\]")) {
                System.out.println("Found Array " + identifier.split("\\[")[0]);
                vars.put(identifier.split("\\[")[0], true);

                colTypes.put(identifier.split("\\[")[0],type);

            } else if (type.matches("\\w+<\\w+>")){
                System.out.println("Found Collection");
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
            System.err.println("ERROR: " + ctx.ID().getSymbol().getText() + " is not declared");
        } else if (!isCollection) {
            System.err.println("ERROR: " + ctx.ID().getSymbol().getText() + " is not a collection");
        }
    }

    @Override
    public void enterOut(jJQueryParser.OutContext ctx) {
    }

    @Override
    public void enterExpr(jJQueryParser.ExprContext ctx) {

        to = ctx.ID().getText();

        from = ctx.selector().ID(0).getText();

    }

    @Override
    public void exitExpr(jJQueryParser.ExprContext ctx) {
        /*
        if (! expressions.isEmpty())
        {
            out.print("if (");
            printExpressions();
            out.println(")");
            out.println(to + ".add(" + from + ".get(i));");
        }
        out.println("}");
        */
    }

    @Override
    public void enterSelector(jJQueryParser.SelectorContext ctx) {
        expressions = new ArrayList<Expression>();
    }

    @Override
    public void enterClass_expr(jJQueryParser.Class_exprContext ctx) {
        expressions.add(new Expression(Expression.Type.Class,ctx.ID().getText(), neg, from, to));
    }

    @Override
    public void enterNot_expr(jJQueryParser.Not_exprContext ctx) {
        neg = true;
    }

    @Override
    public void exitNot_expr(jJQueryParser.Not_exprContext ctx) {
        neg = false;
    }

    private void printExpressions()
    {
        for (int i = 0; i < expressions.size(); i++) {
            out.print(expressions.get(i).print());
            if (i != (expressions.size() - 1))
                out.print(" && ");
            out.println();
        }
    }

    @Override
    public void exitCompilationUnit(jJQueryParser.CompilationUnitContext ctx) {
        System.out.println(vars.toString());
        System.out.println(colTypes.toString());
    }

}
