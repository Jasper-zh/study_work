package clientModule.domain;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {
    private static final long serialVersionUID = 6809290548977725345L;

    String problem;
    String select;
    String answer;

    public Subject(String problem, String select, String answer) {
        this.problem = problem;
        this.select = select;
        this.answer = answer;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "problem='" + problem + '\'' +
                ", select='" + select + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getProblem() {
        return problem;
    }

    public String getSelect() {
        return select;
    }

    public String getAnswer() {
        return answer;
    }
}
