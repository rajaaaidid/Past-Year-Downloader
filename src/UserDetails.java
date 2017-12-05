
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserDetails {
    private static String studentID;
    private static String password;
    private static List<Subject> subjects = new ArrayList<Subject>();
    private static List<String> subjectsString = new ArrayList<String>();
    private static boolean loginStatus;
        
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<String> getSubjectsString() {
        return subjectsString;
    }

    public void setSubjectsString(List<String> aSubjectsString) {
        subjectsString = aSubjectsString;
    }
    
    public void convertSubjects(){
        Iterator<String> ss = subjectsString.iterator();
        while(ss.hasNext()){
            String subjectCode =  ss.next();
            String alphabet;
            String numbers;
            alphabet = subjectCode.substring(0,3);
            numbers = subjectCode.substring(3);
            subjectCode = alphabet+" "+numbers;
            subjects.add(new Subject(subjectCode.substring(0,8))); 
        }
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean aLoginStatus) {
        loginStatus = aLoginStatus;
    }

}
