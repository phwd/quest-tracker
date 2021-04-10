package X;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* renamed from: X.0aS  reason: invalid class name and case insensitive filesystem */
public final class C02620aS implements AbstractC05960li, Serializable {
    public transient String A00;
    public char[] _quotedChars;
    public byte[] _quotedUTF8Ref;
    public byte[] _unquotedUTF8Ref;
    public final String _value;

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(this._value);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0071, code lost:
        r14 = r11 + 1;
        r13 = r5.charAt(r11);
        r0 = r9[r13];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
        if (r0 >= 0) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        r12 = r8.A01;
        r12[1] = 'u';
        r11 = X.C06100lx.A03;
        r12[4] = r11[r13 >> 4];
        r12[5] = r11[r13 & 15];
        r11 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0093, code lost:
        r1 = r3 + r11;
        r0 = r10.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0096, code lost:
        if (r1 <= r0) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
        r0 = r0 - r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0099, code lost:
        if (r0 <= 0) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009b, code lost:
        java.lang.System.arraycopy(r12, 0, r10, r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009e, code lost:
        r10 = r7.A06();
        r11 = r11 - r0;
        java.lang.System.arraycopy(r12, r0, r10, 0, r11);
        r3 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a7, code lost:
        r11 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a9, code lost:
        java.lang.System.arraycopy(r12, 0, r10, r3, r11);
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ae, code lost:
        r12 = r8.A01;
        r12[1] = (char) r0;
        r11 = 2;
     */
    @Override // X.AbstractC05960li
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final char[] A1A() {
        /*
        // Method dump skipped, instructions count: 287
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02620aS.A1A():char[]");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._value.equals(((C02620aS) obj)._value);
    }

    @Override // X.AbstractC05960li
    public final String getValue() {
        return this._value;
    }

    public final int hashCode() {
        return this._value.hashCode();
    }

    public Object readResolve() {
        return new C02620aS(this.A00);
    }

    public final String toString() {
        return this._value;
    }

    public C02620aS(String str) {
        if (str != null) {
            this._value = str;
            return;
        }
        throw new IllegalStateException("Null String illegal for SerializedString");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.A00 = objectInputStream.readUTF();
    }
}
