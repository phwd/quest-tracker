package X;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* renamed from: X.0Uq  reason: invalid class name and case insensitive filesystem */
public class C01390Uq extends AnonymousClass0yl<Calendar> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r3, Calendar calendar) throws IOException {
        Calendar calendar2 = calendar;
        if (calendar2 == null) {
            r3.A09();
            return;
        }
        r3.A06();
        r3.A0C("year");
        r3.A0A((long) calendar2.get(1));
        r3.A0C("month");
        r3.A0A((long) calendar2.get(2));
        r3.A0C("dayOfMonth");
        r3.A0A((long) calendar2.get(5));
        r3.A0C("hourOfDay");
        r3.A0A((long) calendar2.get(11));
        r3.A0C("minute");
        r3.A0A((long) calendar2.get(12));
        r3.A0C("second");
        r3.A0A((long) calendar2.get(13));
        r3.A08();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Calendar A02(C09120zR r10) throws IOException {
        if (r10.A0G() == AnonymousClass007.A0I) {
            r10.A0P();
            return null;
        }
        r10.A0M();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (r10.A0G() != AnonymousClass007.A0D) {
            String A0I = r10.A0I();
            int A0D = r10.A0D();
            if ("year".equals(A0I)) {
                i = A0D;
            } else if ("month".equals(A0I)) {
                i2 = A0D;
            } else if ("dayOfMonth".equals(A0I)) {
                i3 = A0D;
            } else if ("hourOfDay".equals(A0I)) {
                i4 = A0D;
            } else if ("minute".equals(A0I)) {
                i5 = A0D;
            } else if ("second".equals(A0I)) {
                i6 = A0D;
            }
        }
        r10.A0O();
        return new GregorianCalendar(i, i2, i3, i4, i5, i6);
    }
}
