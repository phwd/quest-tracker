package X;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class T3 extends AbstractC0131Ob<Date> {
    public static final AbstractC0132Os A01 = new T4();
    public final DateFormat A00 = new SimpleDateFormat("MMM d, yyyy");

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Date A02(lk lkVar) throws IOException {
        Date date;
        synchronized (this) {
            if (lkVar.A0G() == AnonymousClass07.A08) {
                lkVar.A0P();
                date = null;
            } else {
                try {
                    date = new Date(this.A00.parse(lkVar.A0J()).getTime());
                } catch (ParseException e) {
                    throw new U0(e);
                }
            }
        }
        return date;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Date date) throws IOException {
        String str;
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                str = null;
            } else {
                str = this.A00.format(date2);
            }
            mmVar.A0G(str);
        }
    }
}
