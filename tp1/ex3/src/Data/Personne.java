package Data;
public class Personne implements Serializable {
    private Integer age;
    private String name;

    public Personne(){

    }
    public Personne(int age,String name){
        this.age=age;
        this.name=name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
}
