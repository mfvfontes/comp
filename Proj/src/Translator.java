/**
 * Created by João on 11/05/2015.
 */
public class Translator extends jJQueryParserBaseListener {

    @Override
    public void enterJQueryBlock(jJQueryParser.JQueryBlockContext ctx) {
        System.out.println("Entering jJQuery Block");
    }

    @Override
    public void exitJQueryBlock(jJQueryParser.JQueryBlockContext ctx) {
        System.out.println("Exiting jJQuery Block");
    }

    @Override
    public void enterJava(jJQueryParser.JavaContext ctx) {
        System.out.println(ctx.getText());
    }
}
