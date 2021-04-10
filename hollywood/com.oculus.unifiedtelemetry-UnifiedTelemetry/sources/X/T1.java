package X;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class T1 extends AbstractC0131Ob<Time> {
    public static final AbstractC0132Os A01 = new T2();
    public final DateFormat A00 = new SimpleDateFormat("hh:mm:ss a");

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Time A02(lk lkVar) throws IOException {
        Time time;
        synchronized (this) {
            if (lkVar.A0G() == AnonymousClass07.A08) {
                lkVar.A0P();
                time = null;
            } else {
                try {
                    time = new Time(this.A00.parse(lkVar.A0J()).getTime());
                } catch (ParseException e) {
                    throw new U0(e);
                }
            }
        }
        return time;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Time time) throws IOException {
        String str;
        Time time2 = time;
        synchronized (this) {
            if (time2 == null) {
                str = null;
            } else {
                str = this.A00.format(time2);
            }
            mmVar.A0G(str);
        }
    }
}
