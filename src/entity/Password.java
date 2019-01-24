package entity;

import util.Encryptor;
import util.GlobalData;

public class Password implements java.io.Serializable {

    private Integer id;
    private User user;
    private String site;
    private String identity;
    private byte[] passwordAES;
    private String clearPassword;

    public Password() {}

    public Password(User user, String site, String identity, byte[] passwordAES) {
        this.user = user;
        this.site = site;
        this.identity = identity;
        this.passwordAES = passwordAES;
        clearPassword = Encryptor.decrypt(GlobalData.getKey(), passwordAES);
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getSite() {
        return this.site;
    }
    
    public void setSite(String site) {
        this.site = site;
    }
    
    public String getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    
    public byte[] getPasswordAES() {
        return this.passwordAES;
    }
    
    public void setPasswordAES(byte[] passwordAES) {
        this.passwordAES = passwordAES;
        clearPassword = Encryptor.decrypt(GlobalData.getKey(), passwordAES);
    }
    
    public String getClearPassword() {
        return clearPassword;
    }
    public void setClearPassword(String clearPassword) {
        this.clearPassword = clearPassword;
        this.passwordAES = Encryptor.encrypt(GlobalData.getKey(), clearPassword);
    }
}
