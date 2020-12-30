import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/** Visit log has case status. */
public class Case implements Serializable {
    private static final long serialVersionUID = 7843585098267757690L;
    private static int count = 0;
    private int CaseID;

    private LocalDateTime dt;
    private int custID;
    private int shopID;


    public static ArrayList<Case> caseArrayList = new ArrayList<Case>();


    public Case(){};

    public Case(LocalDateTime dt, int custID, int shopID) {
        this.CaseID = ++count;
        setDt(dt);
        setCustID(custID);
        setShopID(shopID);
        caseArrayList.add(this);
        Serialize();

    }

    private static void Serialize(){
        try{
            FileOutputStream fos = new FileOutputStream("Case.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(caseArrayList);
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
        caseArrayList.clear();
        try{
            FileInputStream fis = new FileInputStream("Case.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            caseArrayList = (ArrayList) ois.readObject();
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

    public int getCaseID() {
        return CaseID;
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
        return "Case{" +
                "CaseID=" + CaseID +
                ", dt=" + dt +
                ", custID=" + custID +
                ", shopID=" + shopID +
                '}';
    }
}
