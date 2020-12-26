import java.io.*;
import java.util.ArrayList;

public class Customer implements Serializable, Role {
    private static final long serialVersionUID = 6523585098267757690L;
    private static int count = 0;
    public static ArrayList<Customer> custs = new ArrayList<Customer>();

    private int id;

    private String password;
    private String name;
    private String phone;
    private String status;

    private static void Serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("Customers.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(custs);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Deserialize() {
        custs.clear();
        try {
            FileInputStream fis = new FileInputStream("Customers.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            custs = (ArrayList) ois.readObject();
            count = custs.size();
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

    public Customer(String name, String password, String phone, String status) {
        this.id = ++count;
        setPassword(password);
        setName(name);
        setPhone(phone);
        setStatus(status);
        custs.add(this);
        Serialize();
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
