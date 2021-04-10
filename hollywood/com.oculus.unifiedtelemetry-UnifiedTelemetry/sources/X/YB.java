package X;

import java.io.Serializable;

public final class YB implements Serializable {
    public static final long serialVersionUID = 1;
    public final int index;
    public final String key;
    public final YB next;
    public final AbstractC0073Cr value;

    public YB(YB yb, String str, AbstractC0073Cr cr, int i) {
        this.next = yb;
        this.key = str;
        this.value = cr;
        this.index = i;
    }
}
