
// import ANTLR's runtime libraries
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by João on 09/04/2015.
 */

public class jjQueryCompiler {
    public static void main(String[] args) throws Exception {

        File inFile = new File("in/" + args[0]);
        InputStream stream = new FileInputStream(inFile);

        File outFile = new File("src/" + args[0]);

        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(stream);

        // create a lexer that feeds off of input CharStream
        jJQueryLexer lexer = new jJQueryLexer(input);


        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        jJQueryParser parser = new jJQueryParser(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        walker.walk(new Translator(new PrintStream(outFile), rewriter, lexer.getErrorListenerDispatch()), tree);

        //System.out.println(rewriter.getText());


    }
}