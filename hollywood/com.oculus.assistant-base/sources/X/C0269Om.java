package X;

import java.io.Serializable;

/* renamed from: X.Om  reason: case insensitive filesystem */
public final class C0269Om implements Serializable {
    public static final long serialVersionUID = 1;
    public final int index;
    public final String key;
    public final C0269Om next;
    public final AbstractC1034r7 value;

    public C0269Om(C0269Om om, String str, AbstractC1034r7 r7Var, int i) {
        this.next = om;
        this.key = str;
        this.value = r7Var;
        this.index = i;
    }
}
