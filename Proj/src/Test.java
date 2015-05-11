import java.util.ArrayList;

/**
 * Created by João on 11/05/2015.
 */
public class Test {
    public static void main(String[] args) {
        /* Comment */

        ArrayList<Integer> selectFrom = new ArrayList<Integer>();
        selectFrom.add(1);
        selectFrom.add(2);

        ArrayList<Integer> selectTo = new ArrayList<Integer>();

        /*@jQ
        in selectFrom;
        out selectTo;

        selectTo = $("selectFrom:eq(1)");
         */

        System.out.println(selectFrom);
        System.out.println(selectTo);
    }
}
