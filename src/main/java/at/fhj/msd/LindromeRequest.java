package at.fhj.msd;

import java.io.Serializable;

public class LindromeRequest implements Serializable {

    private String word;

    public LindromeRequest(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }

}
