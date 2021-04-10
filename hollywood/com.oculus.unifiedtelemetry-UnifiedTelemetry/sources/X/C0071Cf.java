package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
/* renamed from: X.Cf  reason: case insensitive filesystem */
public final class C0071Cf extends WN {
    public static final long serialVersionUID = 1;

    public C0071Cf() {
        super(Calendar.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws IllegalArgumentException, C0223Wj {
        Date A0I = wn.A0I(str);
        if (A0I == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance(wn._config._base._timeZone);
        instance.setTime(A0I);
        return instance;
    }
}
