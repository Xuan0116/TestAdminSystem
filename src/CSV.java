import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSV {
    public static void export(String filename, DataContainer dataContainer) {
        /** From DataContainer arraylist assign to Local Arraylist */
        ArrayList<String> metadata = dataContainer.metadata;
        ArrayList<HashMap<String, String>> data = dataContainer.data;
        /** Res is assigned with delimited join with String Metadata Arraylist */
        String res = String.join(",", metadata);

        /** Loop Hashmap Arraylist */
        for (HashMap<String, String> d : data) {
            /** Create a temporary arraylist for each key.*/
            ArrayList<String> tmpList = new ArrayList<String>();
            for (String item : metadata)
                tmpList.add(d.get(item));
        /** Res append newline + delimiter + String of templist */
            res += "\n" + String.join(",", tmpList);
        }

        //Create file
        try{
            File f = new File(filename+".csv");
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write file
        try {
            FileWriter f = new FileWriter(filename+".csv");
            f.write(res);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
