import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Closed implements Serializable{
    private static final long serialVersionUID = 7878585098267757690L;
    private  static int count;
    private int closedID;

    private LocalDateTime dt;
    private int custID;
    private int shopID;

    /** Closed status array list*/
    public static ArrayList<Closed> ClosedArrayList = new ArrayList<Closed>();

    public Closed(){}

    public Closed(LocalDateTime dt, int custID, int shopID) {
        this.closedID = ++count;
        setDt(dt);
        setCustID(custID);
        setShopID(shopID);
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

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getClosedID() {
        return closedID;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public int getCustID() {
        return custID;
    }

    public int getShopID() {
        return shopID;
    }

    @Override
    public String toString() {
        return "Closed{" +
                "closedID=" + closedID +
                ", dt=" + dt +
                ", custID=" + custID +
                ", shopID=" + shopID +
                '}';
    }
}
