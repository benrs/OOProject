package ca.qc.johnabbott.cs603.Globals;

import java.util.ArrayList;

import ca.qc.johnabbott.cs603.Structures.PictureInfo;

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
    private static ArrayList<PictureInfo> picsArray= new ArrayList<PictureInfo>();
    public static void setToken(String token){
        Token = token;
    }

    public static String getToken(){
        return Token;
    }

    public static void setPicturesArray(ArrayList<PictureInfo> list)
    {
        picsArray = list;
    }

    public static PictureInfo getPicture(int position)
    {
        return picsArray.get(position);
    }
}
