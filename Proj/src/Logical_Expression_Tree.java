/**
 * Created by João on 13/05/2015.
 */
public class Logical_Expression_Tree {
    private class L_Node {
        L_Node left, right;
        Expression expression;

        public L_Node(Expression expression) {
            this.expression = expression;
        }

        public void setRight(L_Node right) {
            this.right = right;
        }

        public void setLeft(L_Node left) {
            this.left = left;
        }
    }

    L_Node root;

    public Logical_Expression_Tree(L_Node root) {
        this.root = root;
    }
}
