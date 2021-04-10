package X;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* renamed from: X.0WL  reason: invalid class name */
public class AnonymousClass0WL extends AnonymousClass0Bd<Calendar> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r3, Calendar calendar) throws IOException {
        Calendar calendar2 = calendar;
        if (calendar2 == null) {
            r3.A0A();
            return;
        }
        r3.A07();
        r3.A0D("year");
        r3.A0B((long) calendar2.get(1));
        r3.A0D("month");
        r3.A0B((long) calendar2.get(2));
        r3.A0D("dayOfMonth");
        r3.A0B((long) calendar2.get(5));
        r3.A0D("hourOfDay");
        r3.A0B((long) calendar2.get(11));
        r3.A0D("minute");
        r3.A0B((long) calendar2.get(12));
        r3.A0D("second");
        r3.A0B((long) calendar2.get(13));
        r3.A09();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Calendar A02(AnonymousClass0Fo r10) throws IOException {
        if (r10.A0D() == AnonymousClass007.A0I) {
            r10.A0L();
            return null;
        }
        r10.A0I();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (r10.A0D() != AnonymousClass007.A0D) {
            String A0E = r10.A0E();
            int A0A = r10.A0A();
            if ("year".equals(A0E)) {
                i = A0A;
            } else if ("month".equals(A0E)) {
                i2 = A0A;
            } else if ("dayOfMonth".equals(A0E)) {
                i3 = A0A;
            } else if ("hourOfDay".equals(A0E)) {
                i4 = A0A;
            } else if ("minute".equals(A0E)) {
                i5 = A0A;
            } else if ("second".equals(A0E)) {
                i6 = A0A;
            }
        }
        r10.A0K();
        return new GregorianCalendar(i, i2, i3, i4, i5, i6);
    }
}
