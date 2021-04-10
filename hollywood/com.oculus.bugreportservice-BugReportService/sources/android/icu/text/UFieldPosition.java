package android.icu.text;

import java.text.FieldPosition;

public class UFieldPosition extends FieldPosition {
    private int countVisibleFractionDigits = -1;
    private long fractionDigits = 0;

    public UFieldPosition() {
        super(-1);
    }

    public void setFractionDigits(int i, long j) {
        this.countVisibleFractionDigits = i;
        this.fractionDigits = j;
    }
}
