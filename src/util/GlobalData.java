package util;

import entity.User;

/* This class stores important data of global scope for the application */
public class GlobalData {
    
    /* The user who started the session */
    private static User user;
    
    /* Key for encrypting/decrypting passwords in the database, this is
       equal to the user password */
    private static String key;

    public static void setUser(User user) {
        GlobalData.user = user;
    }
    
    public static User getUser() {
        return user;
    }

    public static void setKey(String key) {
        GlobalData.key = key;
    }
    
    public static String getKey() {
        return key;
    }
    
}
