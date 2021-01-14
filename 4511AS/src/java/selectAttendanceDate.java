
import ict.db.StudentDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dk
 */
public class selectAttendanceDate {

    public static void main(String[] args) {
        StudentDB sDB;
        String url = "jdbc:mysql://localhost:3306/ITP4511_DB";
        String username = "root";
        String password = "";
        sDB = new StudentDB(url, username, password);
        int name = 64;
        int j = 1;
        int form = 1;
        int classname = 64;
        
        int k = 0;
        String[] allForm = {"1", "2", "3", "4", "5", "6"};
        String[] allClassName = {"A", "B", "C", "D", "E", "F"};
        
        for (int i = 67; i < 80; i++) {sDB.addRecord(i + "", "John" + String.valueOf((char) (name + j++)), "3", allClassName[k]);
            if (i % 6 == 0) {
                
                k++;
                
                j = 1;
            }
            //sDB.addRecord(i + "", "John" + String.valueOf((char) (name + j++)), "1", allClassName[k]);
            
        }
        
    }
}
