package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
/* renamed from: X.0KY  reason: invalid class name */
public final class AnonymousClass0KY extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public AnonymousClass0KY() {
        super(Calendar.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws IllegalArgumentException, AnonymousClass0aG {
        Date A0L = r4.A0L(str);
        if (A0L == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance(r4._config._base._timeZone);
        instance.setTime(A0L);
        return instance;
    }
}
