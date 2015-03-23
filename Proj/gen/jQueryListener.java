// Generated from D:/Dropbox/FEUP/3_2 COMP/Proj/src\jQuery.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link jQueryParser}.
 */
public interface jQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link jQueryParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull jQueryParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link jQueryParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull jQueryParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link jQueryParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(@NotNull jQueryParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jQueryParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(@NotNull jQueryParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jQueryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(@NotNull jQueryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link jQueryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(@NotNull jQueryParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link jQueryParser#out}.
	 * @param ctx the parse tree
	 */
	void enterOut(@NotNull jQueryParser.OutContext ctx);
	/**
	 * Exit a parse tree produced by {@link jQueryParser#out}.
	 * @param ctx the parse tree
	 */
	void exitOut(@NotNull jQueryParser.OutContext ctx);
	/**
	 * Enter a parse tree produced by {@link jQueryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull jQueryParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link jQueryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull jQueryParser.ExprContext ctx);
}