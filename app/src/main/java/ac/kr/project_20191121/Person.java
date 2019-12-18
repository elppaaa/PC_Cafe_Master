package ac.kr.project_20191121;

public class Person {
    /*
    Only For Data Object;
     */
    private String ID, PASS, NAME, PHONE, BIRTH;


    public Person(String _id, String _pass, String _name, String _phone, String _birth){
        this.ID = _id;
        this.PASS = _pass;
        this.NAME = _name;
        this.PHONE = _phone;
        this.BIRTH = _birth;
    }

    public void setInfo(String _id, String _pass, String _name, String _phone, String _birth){
        this.ID = _id;
        this.PASS = _pass;
        this.NAME = _name;
        this.PHONE = _phone;
        this.BIRTH = _birth;
    }
    public String getID(){
        return this.ID;
    }
    public String getPASS(){
        return this.PASS;
    }


}
