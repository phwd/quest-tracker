package X;

import com.google.gson.internal.bind.TimeTypeAdapter$1;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: X.0dk  reason: invalid class name and case insensitive filesystem */
public final class C01350dk extends AnonymousClass13Y<Time> {
    public static final AnonymousClass13Z A01 = new TimeTypeAdapter$1();
    public final DateFormat A00 = new SimpleDateFormat("hh:mm:ss a");

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Time A02(AnonymousClass14I r4) throws IOException {
        Time time;
        synchronized (this) {
            if (r4.A0G() == AnonymousClass007.A09) {
                r4.A0P();
                time = null;
            } else {
                try {
                    time = new Time(this.A00.parse(r4.A0J()).getTime());
                } catch (ParseException e) {
                    throw new AnonymousClass0eR(e);
                }
            }
        }
        return time;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Time time) throws IOException {
        String str;
        Time time2 = time;
        synchronized (this) {
            if (time2 == null) {
                str = null;
            } else {
                str = this.A00.format(time2);
            }
            r2.A0D(str);
        }
    }
}
