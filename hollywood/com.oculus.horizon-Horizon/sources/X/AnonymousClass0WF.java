package X;

import com.google.gson.internal.bind.SqlDateTypeAdapter$1;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: X.0WF  reason: invalid class name */
public final class AnonymousClass0WF extends AnonymousClass0yl<Date> {
    public static final AbstractC08860ym A01 = new SqlDateTypeAdapter$1();
    public final DateFormat A00 = new SimpleDateFormat("MMM d, yyyy");

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Date A02(C09120zR r4) throws IOException {
        Date date;
        synchronized (this) {
            if (r4.A0G() == AnonymousClass007.A0I) {
                r4.A0P();
                date = null;
            } else {
                try {
                    date = new Date(this.A00.parse(r4.A0J()).getTime());
                } catch (ParseException e) {
                    throw new C03080c5(e);
                }
            }
        }
        return date;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, Date date) throws IOException {
        String str;
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                str = null;
            } else {
                str = this.A00.format(date2);
            }
            r2.A0D(str);
        }
    }
}
