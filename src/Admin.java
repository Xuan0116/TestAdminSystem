import java.io.*;
import java.util.ArrayList;

public class Admin implements Serializable {
    private static final long SerialVersionUID = 6523585098267757770L;
    public static ArrayList<Admin> adminArrayList = new ArrayList<Admin>();

    /** Data field for sign in */
    private String Username;
    private String Password;

    private static void Serialize(){
        try{
            FileOutputStream fos = new FileOutputStream("TestAdmin1");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(adminArrayList);
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
        adminArrayList.clear();
        try{
            FileInputStream fis = new FileInputStream("TestAdmin1");
            ObjectInputStream ois = new ObjectInputStream(fis);
            adminArrayList = (ArrayList) ois.readObject();
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

    /** No arg constructor */
    public Admin(){}

    /** user defined constructor */
    public Admin(String Username, String Password)
            throws MaxCharException {
      /*  if(Password.length() > 16)
            throw new MaxCharException(Password);*/
        setUsername(Username);
        setPassword(Password);
        /** Add admin to arraylist */
        adminArrayList.add(this);
        /** Serialize */
        Serialize();
    }

    /** Setter */
    public void setPassword(String password)
            throws MaxCharException {
        if(password.length() > 16)
            throw new MaxCharException(password);
        Password = password;
    }

    public void setUsername(String username) { Username = username; }

    /** Getter(Only for Username)
      Password is not allow to display to anyone. */
    public String getUsername() {
        return Username;
    }

    /** Method */
    public static void SignIn(){

    }

    public static void SignUp(){

    }

    @Override
    public String toString() {
        return "Admin{" +
                "Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
