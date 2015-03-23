// Generated from D:/Dropbox/FEUP/3_2 COMP/Proj/src\jQuery.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link jQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface jQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link jQueryParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull jQueryParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link jQueryParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(@NotNull jQueryParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jQueryParser#in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(@NotNull jQueryParser.InContext ctx);
	/**
	 * Visit a parse tree produced by {@link jQueryParser#out}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOut(@NotNull jQueryParser.OutContext ctx);
	/**
	 * Visit a parse tree produced by {@link jQueryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull jQueryParser.ExprContext ctx);
}