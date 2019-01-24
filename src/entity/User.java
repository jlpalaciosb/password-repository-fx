package entity;

public class User implements java.io.Serializable {

    private Integer id;
    private String name;
    private byte[] passwordSHA256;

    public User() {}
    
    public User(String name, byte[] passwordSHA256) {
        this.name = name;
        this.passwordSHA256 = passwordSHA256;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public byte[] getPasswordSHA256() {
        return this.passwordSHA256;
    }
    
    public void setPasswordSHA256(byte[] passwordSha256) {
        this.passwordSHA256 = passwordSha256;
    }
    
}
