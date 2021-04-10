package X;

import com.facebook.acra.CrashTimeDataCollector;
import java.io.Serializable;

public final class NT implements Serializable {
    public static final NT A01 = new NT("N/A", -1, -1, -1, -1);
    public static final long serialVersionUID = 1;
    public final transient Object A00;
    public final int _columnNr;
    public final int _lineNr;
    public final long _totalBytes;
    public final long _totalChars;

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r7.A00 != null) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r5 = 1
            if (r7 == r6) goto L_0x003c
            r2 = 0
            if (r7 == 0) goto L_0x0014
            boolean r0 = r7 instanceof X.NT
            if (r0 == 0) goto L_0x0014
            X.NT r7 = (X.NT) r7
            java.lang.Object r1 = r6.A00
            if (r1 != 0) goto L_0x0015
            java.lang.Object r0 = r7.A00
            if (r0 == 0) goto L_0x001e
        L_0x0014:
            return r2
        L_0x0015:
            java.lang.Object r0 = r7.A00
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x001e
            return r2
        L_0x001e:
            int r1 = r6._lineNr
            int r0 = r7._lineNr
            if (r1 != r0) goto L_0x003b
            int r1 = r6._columnNr
            int r0 = r7._columnNr
            if (r1 != r0) goto L_0x003b
            long r3 = r6._totalChars
            long r1 = r7._totalChars
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x003b
            long r3 = r6._totalBytes
            long r1 = r7._totalBytes
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x003b
            return r5
        L_0x003b:
            r5 = 0
        L_0x003c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: X.NT.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int hashCode;
        Object obj = this.A00;
        if (obj == null) {
            hashCode = 1;
        } else {
            hashCode = obj.hashCode();
        }
        return (((hashCode ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    public final String toString() {
        String obj;
        StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        Object obj2 = this.A00;
        if (obj2 == null) {
            obj = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        } else {
            obj = obj2.toString();
        }
        sb.append(obj);
        sb.append("; line: ");
        sb.append(this._lineNr);
        sb.append(", column: ");
        sb.append(this._columnNr);
        sb.append(']');
        return sb.toString();
    }

    public NT(Object obj, long j, long j2, int i, int i2) {
        this.A00 = obj;
        this._totalBytes = j;
        this._totalChars = j2;
        this._lineNr = i;
        this._columnNr = i2;
    }
}
