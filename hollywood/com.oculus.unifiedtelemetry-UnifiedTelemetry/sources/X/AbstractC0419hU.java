package X;

import java.io.IOException;

/* renamed from: X.hU  reason: case insensitive filesystem */
public abstract class AbstractC0419hU {
    public final boolean A00;
    public final boolean A01;
    public final String A02;

    public abstract void A00(lk lkVar, Object obj) throws IOException, IllegalAccessException;

    public abstract void A01(mm mmVar, Object obj) throws IOException, IllegalAccessException;

    public abstract boolean A02(Object obj) throws IOException, IllegalAccessException;

    public AbstractC0419hU(String str, boolean z, boolean z2) {
        this.A02 = str;
        this.A01 = z;
        this.A00 = z2;
    }
}
