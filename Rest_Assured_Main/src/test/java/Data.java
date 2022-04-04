public class Data {
    private String name;
    private String email;
    private String password;
    private int  age;
    private String description;
    public Data(String name, String email,String password,int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }
    public Data(String email,String password) {

        this.email = email;
        this.password = password;
    }
    public Data(String description){
        this.description=description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String  getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}