
public class Subject {
    private String subjectCode;
    private String subjectName;
    
    Subject(String subjectCode){
        this.subjectCode = subjectCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        if(SubjectCode.split(" ").length==1){
            String alphabet;
            String numbers;
            alphabet = SubjectCode.substring(0,3);
            numbers = SubjectCode.substring(3);
            SubjectCode = alphabet+" "+numbers;
        }
        this.subjectCode = SubjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.subjectName = SubjectName;
    }
    
}
