// Generated from D:/Dropbox/FEUP/3_2 COMP/Proj/src\Hello.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull HelloParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull HelloParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(@NotNull HelloParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(@NotNull HelloParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(@NotNull HelloParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(@NotNull HelloParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#out}.
	 * @param ctx the parse tree
	 */
	void enterOut(@NotNull HelloParser.OutContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#out}.
	 * @param ctx the parse tree
	 */
	void exitOut(@NotNull HelloParser.OutContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull HelloParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull HelloParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#attr}.
	 * @param ctx the parse tree
	 */
	void enterAttr(@NotNull HelloParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#attr}.
	 * @param ctx the parse tree
	 */
	void exitAttr(@NotNull HelloParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#attr_op}.
	 * @param ctx the parse tree
	 */
	void enterAttr_op(@NotNull HelloParser.Attr_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#attr_op}.
	 * @param ctx the parse tree
	 */
	void exitAttr_op(@NotNull HelloParser.Attr_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#attr_equals}.
	 * @param ctx the parse tree
	 */
	void enterAttr_equals(@NotNull HelloParser.Attr_equalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#attr_equals}.
	 * @param ctx the parse tree
	 */
	void exitAttr_equals(@NotNull HelloParser.Attr_equalsContext ctx);
}