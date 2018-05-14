package musta.belmo.utils.textutils;


/**
 * Created by mustabelmo on 14/05/2018.
 */
public class HighlightPosition {

    private int end;
    private int start;

    public HighlightPosition(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
}
