import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Visit implements Serializable {
    private static final long serialVersionUID = 6543585098267757690L;
    private static int count = 0;
    public static ArrayList<Visit> visits = new ArrayList<Visit>();

    private int id;

    private LocalDateTime dt;
    private int custID;
    private int shopID;

    private static void Serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("Visits.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(visits);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Deserialize() {
        visits.clear();
        try {
            FileInputStream fis = new FileInputStream("Visits.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            visits = (ArrayList) ois.readObject();
            count = visits.size();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            return; //No serialization found
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Visit(int custID, int shopID) { //Proper Method
        this.id = ++count;
        this.dt = LocalDateTime.now();
        this.custID = custID;
        this.shopID = shopID;
        visits.add(this);
        Serialize();
    }

    public Visit(LocalDateTime dt, int custID, int shopID) { //Method for fake 30 data
        this.id = ++count;
        this.dt = dt;
        this.custID = custID;
        this.shopID = shopID;
        visits.add(this);
        Serialize();
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        return dtf.format(this.dt);
    }

    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("k:mm:ss");
        return dtf.format(this.dt);
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", dt=" + dt +
                ", custID=" + custID +
                ", shopID=" + shopID +
                '}';
    }
}
