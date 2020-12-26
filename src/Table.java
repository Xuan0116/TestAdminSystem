import java.util.ArrayList;
import java.util.HashMap;

public class Table {
    /** return an integer array for storing max length of the element in arraylist */
    private static int[] maxColLen(ArrayList<String> metadata, ArrayList<HashMap<String, String>> data) {
        int[] res = new int[metadata.size()];

        for (int i = 0; i < metadata.size(); i++)
            /** eg: number 0 element length 7 ,1st element length 8  -> res = {7,8} */
            res[i] = metadata.get(i).length();

        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < metadata.size(); j++)
                /** Compared Key length in res integer array with data's value's length */
                res[j] = Math.max(res[j], data.get(i).get(metadata.get(j)).length());

        return res;
    }
    /** int[] cMaxLen - integer array that contain col max length */
    private static String getLine(int[] cMaxLen, ArrayList<String> colString) {
        String[] newColString = new String[colString.size()];
        for (int i = 0; i < colString.size(); i++) {
            int rightSpace = cMaxLen[i] - colString.get(i).length();
            /** A string composed of this string repeated rightSpace times */
            /** To have fix alignment */
            newColString[i] = colString.get(i) + " ".repeat(rightSpace);
        }

        return String.join("  ", newColString);
    }


    public static void display(String title, DataContainer dataContainer) {
        ArrayList<String> metadata = dataContainer.metadata;
        ArrayList<HashMap<String, String>> data = dataContainer.data;

        if (!title.isEmpty())
            System.out.println(title + "\n"); /** Eg string : Master Visit History */

        int[] cMaxLen = maxColLen(metadata, data);

        /** Print Key aka title */
        System.out.println(getLine(cMaxLen, metadata));

        for (HashMap<String, String> d : data) {
            ArrayList<String> tmpList = new ArrayList<String>();
            /** Add data's value into array list. */
            for (String item : metadata)
                tmpList.add(d.get(item));
            System.out.println(getLine(cMaxLen, tmpList));
        }
    }
}
