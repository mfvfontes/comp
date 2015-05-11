
// import ANTLR's runtime libraries
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by João on 09/04/2015.
 */

public class jjQueryCompiler {
    public static void main(String[] args) throws Exception {

        File inFile = new File(args[0]);
        InputStream stream = new FileInputStream(inFile);

        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(stream);

        // create a lexer that feeds off of input CharStream
        jJQueryLexer lexer = new jJQueryLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        jJQueryParser parser = new jJQueryParser(tokens);

        ParseTree tree = parser.start();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Translator(), tree);

        System.out.println();


    }
}