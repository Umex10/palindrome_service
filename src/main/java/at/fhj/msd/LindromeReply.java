package at.fhj.msd;

import java.io.Serializable;

public class LindromeReply implements Serializable {

    private boolean isLindrome;
    private String message;

    public LindromeReply(boolean isLindrome, String messsage) {
        this.isLindrome = isLindrome;
        this.message = messsage;
    }

    public boolean isLindrome() {
        return isLindrome;
    }

    public String getMessage() {
        return message;
    }

}
