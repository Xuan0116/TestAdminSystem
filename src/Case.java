import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/** Visit log has case status. */
public class Case extends Visit{
    private static final long serialVersionUID = 7843585098267757690L;
    private  static int count;
    private int CaseID;

    public static ArrayList<Case> caseArrayList = new ArrayList<Case>();

    /** Case status Date Time ArrayList */
    public static ArrayList<Long> caseDateTime = new ArrayList<Long>();

    public Case(){};

    public Case(LocalDateTime dt, int custID, int shopID) {
        super(dt, custID, shopID);
        this.CaseID = ++count;
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

    public static void getCaseDT(){
        for(int i = 0; i < Case.caseArrayList.size();i++){
            LocalDateTime dt = Case.caseArrayList.get(i).getDt();
            /** Change Dt from systemDefault time zone to instants.*/
            Instant instant = dt.atZone(ZoneId.systemDefault()).toInstant();
            /** Convert Instant to Epoch Millisecond. */
            long dtinMillis = instant.toEpochMilli();
            caseDateTime.add(dtinMillis);
        }
    }

    public int getCaseID() {
        return CaseID;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseID = " + CaseID + ", Date Time = " + super.getDt() +
                ", custID = " + super.getCustID() +
                ", shopID = " + super.getShopID() +
                '}';
    }
}
