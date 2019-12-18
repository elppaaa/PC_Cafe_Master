package ac.kr.project_20191121;

public class Person {
    /*
    Only For Data Object;
     */
    private String ID, PASS, NAME, PHONE, BIRTH;
    private int TIME;


    public Person(String _id, String _pass, String _name, String _phone, String _birth, int _time){
        this.ID = _id;
        this.PASS = _pass;
        this.NAME = _name;
        this.PHONE = _phone;
        this.BIRTH = _birth;
        this.TIME = _time;
    }

    public void setInfo(String _id, String _pass, String _name, String _phone, String _birth, int _time){
        this.ID = _id;
        this.PASS = _pass;
        this.NAME = _name;
        this.PHONE = _phone;
        this.BIRTH = _birth;
        this.TIME = _time;
    }
    public String getID(){
        return this.ID;
    }
    public String getPASS(){
        return this.PASS;
    }
    public String getNAME(){
        return this.NAME;
    }
    public int getTIME() {
        return this.TIME;
    }



}
