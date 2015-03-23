// Generated from D:/Dropbox/FEUP/3_2 COMP/Proj/src\Hello.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull HelloParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(@NotNull HelloParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(@NotNull HelloParser.InContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#out}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOut(@NotNull HelloParser.OutContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull HelloParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(@NotNull HelloParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#attr_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_op(@NotNull HelloParser.Attr_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#attr_equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_equals(@NotNull HelloParser.Attr_equalsContext ctx);
}