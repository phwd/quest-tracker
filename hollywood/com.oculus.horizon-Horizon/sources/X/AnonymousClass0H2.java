package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
/* renamed from: X.0H2  reason: invalid class name */
public final class AnonymousClass0H2 extends AbstractC03840gD {
    public static final long serialVersionUID = 1;

    public AnonymousClass0H2() {
        super(Calendar.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r4) throws IllegalArgumentException, C03990gZ {
        Date A0E = r4.A0E(str);
        if (A0E == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance(r4._config._base._timeZone);
        instance.setTime(A0E);
        return instance;
    }
}
