package X;

import java.io.IOException;

/* renamed from: X.0zL  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09070zL {
    public final boolean A00;
    public final boolean A01;
    public final String A02;

    public abstract void A00(C09120zR v, Object obj) throws IOException, IllegalAccessException;

    public abstract void A01(C09130zU v, Object obj) throws IOException, IllegalAccessException;

    public abstract boolean A02(Object obj) throws IOException, IllegalAccessException;

    public AbstractC09070zL(String str, boolean z, boolean z2) {
        this.A02 = str;
        this.A01 = z;
        this.A00 = z2;
    }
}
