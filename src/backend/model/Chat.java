package backend.model;

public class Chat {
    private String id;
    private User firstSide;
    private User secondSide;

    public Chat(String id, User firstSide, User secondSide) {
        this.id = id;
        this.firstSide = firstSide;
        this.secondSide = secondSide;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(User firstSide) {
        this.firstSide = firstSide;
    }

    public User getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(User secondSide) {
        this.secondSide = secondSide;
    }
}
