public class MaxCharException extends Exception {
    private String Password;
    /** Default constructor */
    public MaxCharException(){}

    public MaxCharException(String Password) {
        super( Password + " Exceed maximum character for password. ");
        this.Password = Password;
    }

    public String GetPassword(){return Password;}


}
