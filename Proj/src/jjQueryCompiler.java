
// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;

/**
 * Created by João on 09/04/2015.
 */

public class jjQueryCompiler {
    public static void main(String[] args) throws Exception {
        

        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        // create a lexer that feeds off of input CharStream
        jjQueryLexer lexer = new jjQueryLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        jjQueryParser parser = new jjQueryParser(tokens);
        ParseTree tree = parser.start(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}