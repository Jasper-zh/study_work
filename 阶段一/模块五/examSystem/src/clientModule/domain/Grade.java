package clientModule.domain;

import java.io.Serializable;

public class Grade implements Serializable {
    private static final long serialVersionUID = -2162102476571244538L;
    private String username;
    private String grade;
    private String date;

    public Grade(String username, String grade,String date) {
        this.username = username;
        this.grade = grade;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "username='" + username + '\'' +
                ", grade='" + grade + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
