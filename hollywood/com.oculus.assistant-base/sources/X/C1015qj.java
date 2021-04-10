package X;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* renamed from: X.qj  reason: case insensitive filesystem */
public final class C1015qj implements Na, Serializable {
    public transient String A00;
    public char[] _quotedChars;
    public byte[] _quotedUTF8Ref;
    public byte[] _unquotedUTF8Ref;
    public final String _value;

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeUTF(this._value);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        r14 = r11 + 1;
        r13 = r9.charAt(r11);
        r0 = r6[r13];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        if (r0 >= 0) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        r12 = r10.A01;
        r12[1] = 'u';
        r11 = X.Ni.A03;
        r12[4] = r11[r13 >> 4];
        r12[5] = r11[r13 & 15];
        r11 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        r1 = r2 + r11;
        r0 = r7.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        if (r1 <= r0) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0072, code lost:
        r0 = r0 - r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0073, code lost:
        if (r0 <= 0) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        java.lang.System.arraycopy(r12, 0, r7, r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0078, code lost:
        r7 = r8.A0C();
        r11 = r11 - r0;
        java.lang.System.arraycopy(r12, r0, r7, 0, r11);
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0081, code lost:
        r11 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0083, code lost:
        java.lang.System.arraycopy(r12, 0, r7, r2, r11);
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0088, code lost:
        r12 = r10.A01;
        r12[1] = (char) r0;
        r11 = 2;
     */
    @Override // X.Na
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final char[] A1H() {
        /*
        // Method dump skipped, instructions count: 170
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1015qj.A1H():char[]");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._value.equals(((C1015qj) obj)._value);
    }

    public final int hashCode() {
        return this._value.hashCode();
    }

    public Object readResolve() {
        return new C1015qj(this.A00);
    }

    public C1015qj(String str) {
        if (str != null) {
            this._value = str;
            return;
        }
        throw new IllegalStateException("Null String illegal for SerializedString");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.A00 = objectInputStream.readUTF();
    }

    @Override // X.Na
    public final String getValue() {
        return this._value;
    }

    public final String toString() {
        return this._value;
    }
}
