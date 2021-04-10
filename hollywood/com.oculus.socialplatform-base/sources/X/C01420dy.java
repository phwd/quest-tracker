package X;

import com.google.gson.internal.bind.DateTypeAdapter$1;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.0dy  reason: invalid class name and case insensitive filesystem */
public final class C01420dy extends AnonymousClass13Y<Date> {
    public static final AnonymousClass13Z A01 = new DateTypeAdapter$1();
    public final List<DateFormat> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r3, Date date) throws IOException {
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                r3.A09();
            } else {
                r3.A0D(this.A00.get(0).format(date2));
            }
        }
    }

    public C01420dy() {
        ArrayList arrayList = new ArrayList();
        this.A00 = arrayList;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.A00.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (C057513m.A00 >= 9) {
            this.A00.add(new SimpleDateFormat(AnonymousClass006.A09("MMM d, yyyy", HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, "h:mm:ss a"), Locale.US));
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01d5, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01d7, code lost:
        r1 = new java.lang.StringBuilder("\"");
        r1.append(r12);
        r1.append('\"');
        r4 = r1.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01ea, code lost:
        r2 = r3.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01f6, code lost:
        r2 = X.AnonymousClass006.A09("(", r3.getClass().getName(), ")");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0206, code lost:
        r0 = new java.text.ParseException(X.AnonymousClass006.A0B("Failed to parse date [", r4, "]: ", r2), r11.getIndex());
        r0.initCause(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x021a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01d2, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01d3, code lost:
        if (r12 == null) goto L_0x01d5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01ca A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01d2, IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01d2 A[ExcHandler: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException (r3v1 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:93:0x01c0] */
    @Override // X.AnonymousClass13Y
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Date A02(X.AnonymousClass14I r21) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 549
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01420dy.A02(X.14I):java.lang.Object");
    }
}
