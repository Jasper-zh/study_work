package mk4.four;

import java.io.Serializable;

public class UserMessage implements Serializable {
    private String state;
    private User user;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "state='" + state + '\'' +
                ", user=" + user.getUsername() +":"+user.getPassword()+
                '}';
    }
}
