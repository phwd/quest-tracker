package X;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class TR extends AbstractC0131Ob<Date> {
    public static final AbstractC0132Os A01 = new TS();
    public final List<DateFormat> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Date date) throws IOException {
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                mmVar.A0B();
            } else {
                mmVar.A0G(this.A00.get(0).format(date2));
            }
        }
    }

    public TR() {
        ArrayList arrayList = new ArrayList();
        this.A00 = arrayList;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.A00.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (C0183Ui.A00 >= 9) {
            this.A00.add(new SimpleDateFormat(AnonymousClass06.A05("MMM d, yyyy", " ", "h:mm:ss a"), Locale.US));
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
        r2 = X.AnonymousClass06.A05("(", r3.getClass().getName(), ")");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0206, code lost:
        r0 = new java.text.ParseException(X.AnonymousClass06.A06("Failed to parse date [", r4, "]: ", r2), r11.getIndex());
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
    @Override // X.AbstractC0131Ob
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Date A02(X.lk r21) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 549
        */
        throw new UnsupportedOperationException("Method not decompiled: X.TR.A02(X.lk):java.lang.Object");
    }
}
