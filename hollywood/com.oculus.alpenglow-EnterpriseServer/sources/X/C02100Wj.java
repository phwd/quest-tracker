package X;

import com.google.gson.internal.bind.TimeTypeAdapter$1;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: X.0Wj  reason: invalid class name and case insensitive filesystem */
public final class C02100Wj extends AnonymousClass0Bd<Time> {
    public static final AnonymousClass0C3 A01 = new TimeTypeAdapter$1();
    public final DateFormat A00 = new SimpleDateFormat("hh:mm:ss a");

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Time A02(AnonymousClass0Fo r4) throws IOException {
        Time time;
        synchronized (this) {
            if (r4.A0D() == AnonymousClass007.A0I) {
                r4.A0L();
                time = null;
            } else {
                try {
                    time = new Time(this.A00.parse(r4.A0F()).getTime());
                } catch (ParseException e) {
                    throw new AnonymousClass0XQ(e);
                }
            }
        }
        return time;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Time time) throws IOException {
        String str;
        Time time2 = time;
        synchronized (this) {
            if (time2 == null) {
                str = null;
            } else {
                str = this.A00.format(time2);
            }
            r2.A0E(str);
        }
    }
}
