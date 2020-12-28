import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Closed extends Case {
    private static final long serialVersionUID = 7878585098267757690L;
    private  static int count;
    private int closedID;

    /** Closed status array list*/
    public static ArrayList<Closed> ClosedArrayList = new ArrayList<Closed>();



    public Closed(){}

    public Closed(LocalDateTime dt, int custID, int shopID) {
        super(dt, custID, shopID);
        this.closedID = ++count;
        ClosedArrayList.add(this);
        Serialize();
    }

    private static void Serialize(){
        try{
            FileOutputStream fos = new FileOutputStream("Closed.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ClosedArrayList);
            oos.close();
            fos.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void Deserialize(){
        /** clear Arraylist */
        ClosedArrayList.clear();
        try{
            FileInputStream fis = new FileInputStream("Closed.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ClosedArrayList = (ArrayList) ois.readObject();
            fis.close();
            ois.close();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (ClassNotFoundException class_ex){
            class_ex.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }



    public int getClosedID() {
        return closedID;
    }

    @Override
    public String toString() {
        return "Closed{" +
                "closedID = " + closedID + ", Date Time = " + super.getDt() +
                ", custID = " + super.getCustID() +
                ", shopID = " + super.getShopID() +
                '}';
    }
}
