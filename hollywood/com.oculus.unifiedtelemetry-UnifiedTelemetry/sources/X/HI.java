package X;

import java.io.IOException;

public class HI extends IOException {
    public final int mStatusCode;

    public HI(int i, String str) {
        super(str);
        this.mStatusCode = i;
    }
}
