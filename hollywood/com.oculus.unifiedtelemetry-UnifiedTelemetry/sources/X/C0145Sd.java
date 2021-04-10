package X;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* renamed from: X.Sd  reason: case insensitive filesystem */
public class C0145Sd extends AbstractC0131Ob<Calendar> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Calendar calendar) throws IOException {
        Calendar calendar2 = calendar;
        if (calendar2 == null) {
            mmVar.A0B();
            return;
        }
        mmVar.A08();
        mmVar.A0F("year");
        mmVar.A0C((long) calendar2.get(1));
        mmVar.A0F("month");
        mmVar.A0C((long) calendar2.get(2));
        mmVar.A0F("dayOfMonth");
        mmVar.A0C((long) calendar2.get(5));
        mmVar.A0F("hourOfDay");
        mmVar.A0C((long) calendar2.get(11));
        mmVar.A0F("minute");
        mmVar.A0C((long) calendar2.get(12));
        mmVar.A0F("second");
        mmVar.A0C((long) calendar2.get(13));
        mmVar.A0A();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Calendar A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        lkVar.A0M();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (lkVar.A0G() != AnonymousClass07.A03) {
            String A0I = lkVar.A0I();
            int A0D = lkVar.A0D();
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
        lkVar.A0O();
        return new GregorianCalendar(i, i2, i3, i4, i5, i6);
    }
}
