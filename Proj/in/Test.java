import java.util.ArrayList;

/**
 * Created by João on 11/05/2015.
 */
public class Test {
    public static void main(String[] args) {
        /* Comment */

        ArrayList<Object> selectFrom = new ArrayList<Object>();
        selectFrom.add(1);
        selectFrom.add("abc");
        selectFrom.add("def");
        selectFrom.add(4);

        ArrayList<Object> selectTo = new ArrayList<Object>();
        int a[] , b;

        /*@jQ
        in selectFrom;
        in a;

        in b;
        in c;

        out selectTo;

        selectTo = $("selectFrom:not(.Integer)|:gt(2)");

         */

        selectTo.clear();
        for (int i = 0; i < selectFrom.size(); i++) {
            if (
                    (! selectFrom.get(i) instanceof Integer) &&
                    (i > 2))
                selectTo.add(selectFrom.get(i));
        }

        System.out.println(selectFrom);
        System.out.println(selectTo);
    }
}