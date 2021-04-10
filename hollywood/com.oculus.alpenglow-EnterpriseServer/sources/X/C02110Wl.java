package X;

import com.google.gson.internal.bind.SqlDateTypeAdapter$1;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: X.0Wl  reason: invalid class name and case insensitive filesystem */
public final class C02110Wl extends AnonymousClass0Bd<Date> {
    public static final AnonymousClass0C3 A01 = new SqlDateTypeAdapter$1();
    public final DateFormat A00 = new SimpleDateFormat("MMM d, yyyy");

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Date A02(AnonymousClass0Fo r4) throws IOException {
        Date date;
        synchronized (this) {
            if (r4.A0D() == AnonymousClass007.A0I) {
                r4.A0L();
                date = null;
            } else {
                try {
                    date = new Date(this.A00.parse(r4.A0F()).getTime());
                } catch (ParseException e) {
                    throw new AnonymousClass0XQ(e);
                }
            }
        }
        return date;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Date date) throws IOException {
        String str;
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                str = null;
            } else {
                str = this.A00.format(date2);
            }
            r2.A0E(str);
        }
    }
}
