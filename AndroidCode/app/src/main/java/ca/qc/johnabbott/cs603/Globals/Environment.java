package ca.qc.johnabbott.cs603.Globals;

/**
 * Created by benjamin on 3/27/2015.
 */
// This class is simply used to store
// "environment" variables (i.e: Globals)
public class Environment {
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static String Token = null;

    public static void setToken(String token){
        Token = token;
    }

    public static String getToken(){
        return Token;
    }
}
