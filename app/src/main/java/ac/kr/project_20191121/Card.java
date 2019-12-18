package ac.kr.project_20191121;

public class Card {
    /*
Only For Data Object;
 */
    private String NUM, NICK, DUE, PW, JM;

    public Card(String _num, String _nick, String _due, String _pw, String _jm){
        this.NUM = _num;
        this.NICK = _nick;
        this.DUE = _due;
        this.PW = _pw;
        this.JM  = _jm;
    }

    public String GetNum(){return this.NUM;}
    public String GetNick(){return this.NICK;}
    public String GetDue(){return  this.DUE;}
    public String GetPw(){return  this.PW;}
    public String GetJm(){return  this.JM;}
}
