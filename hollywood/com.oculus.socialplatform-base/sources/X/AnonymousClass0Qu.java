package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
/* renamed from: X.0Qu  reason: invalid class name */
public final class AnonymousClass0Qu extends AbstractC02030hq {
    public static final long serialVersionUID = 1;

    public AnonymousClass0Qu() {
        super(Calendar.class);
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r4) throws IllegalArgumentException, C02180iD {
        Date A0M = r4.A0M(str);
        if (A0M == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance(r4._config._base._timeZone);
        instance.setTime(A0M);
        return instance;
    }
}
