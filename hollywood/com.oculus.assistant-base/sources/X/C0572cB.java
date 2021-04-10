package X;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* renamed from: X.cB  reason: case insensitive filesystem */
public final class C0572cB extends ThreadLocal {
    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(C0561by.A01);
        return simpleDateFormat;
    }
}
