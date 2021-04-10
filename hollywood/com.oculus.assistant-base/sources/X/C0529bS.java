package X;

/* renamed from: X.bS  reason: case insensitive filesystem */
public final class C0529bS {
    public final String A00;
    public final String A01;
    public final String A02;
    public final C0603cm A03;

    public C0529bS(String str, String str2) {
        StringBuilder sb;
        int i;
        C0603cm cmVar;
        int i2;
        String str3 = str;
        this.A02 = str3;
        if (str3.startsWith("*.")) {
            sb = new StringBuilder();
            sb.append("http://");
            str3 = str3.substring(2);
        } else {
            sb = new StringBuilder();
            sb.append("http://");
        }
        sb.append(str3);
        String obj = sb.toString();
        C0543bg bgVar = new C0543bg();
        this.A00 = (bgVar.A02(null, obj) == AnonymousClass09.A00 ? bgVar.A03() : null).A02;
        if (str2.startsWith("sha1/")) {
            this.A01 = "sha1/";
            i = 5;
        } else if (str2.startsWith("sha256/")) {
            this.A01 = "sha256/";
            i = 7;
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A04("pins must start with 'sha256/' or 'sha1/': ", str2));
        }
        String substring = str2.substring(i);
        if (substring != null) {
            int length = substring.length();
            while (length > 0 && ((r1 = substring.charAt(length - 1)) == '=' || r1 == '\n' || r1 == '\r' || r1 == ' ' || r1 == '\t')) {
                length--;
            }
            int i3 = (int) ((((long) length) * 6) / 8);
            byte[] bArr = new byte[i3];
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                if (i4 >= length) {
                    int i8 = i5 % 4;
                    if (i8 != 1) {
                        if (i8 == 2) {
                            bArr[i7] = (byte) ((i6 << 12) >> 16);
                            i7++;
                        } else if (i8 == 3) {
                            int i9 = i6 << 6;
                            int i10 = i7 + 1;
                            bArr[i7] = (byte) (i9 >> 16);
                            i7 = i10 + 1;
                            bArr[i10] = (byte) (i9 >> 8);
                        }
                        if (i7 != i3) {
                            byte[] bArr2 = new byte[i7];
                            System.arraycopy(bArr, 0, bArr2, 0, i7);
                            bArr = bArr2;
                        }
                        cmVar = new C0603cm(bArr);
                    }
                } else {
                    char charAt = substring.charAt(i4);
                    if (charAt >= 'A' && charAt <= 'Z') {
                        i2 = charAt - 'A';
                    } else if (charAt >= 'a' && charAt <= 'z') {
                        i2 = charAt - 'G';
                    } else if (charAt >= '0' && charAt <= '9') {
                        i2 = charAt + 4;
                    } else if (charAt == '+' || charAt == '-') {
                        i2 = 62;
                    } else if (charAt == '/' || charAt == '_') {
                        i2 = 63;
                    } else {
                        if (!(charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
                            break;
                        }
                        i4++;
                    }
                    i6 = (i6 << 6) | ((byte) i2);
                    i5++;
                    if (i5 % 4 == 0) {
                        int i11 = i7 + 1;
                        bArr[i7] = (byte) (i6 >> 16);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (i6 >> 8);
                        i7 = i12 + 1;
                        bArr[i12] = (byte) i6;
                    }
                    i4++;
                }
            }
            cmVar = null;
            this.A03 = cmVar;
            if (cmVar == null) {
                throw new IllegalArgumentException(AnonymousClass08.A04("pins must be base64: ", str2));
            }
            return;
        }
        throw new IllegalArgumentException("base64 == null");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0529bS) {
            C0529bS bSVar = (C0529bS) obj;
            if (!this.A02.equals(bSVar.A02) || !this.A01.equals(bSVar.A01) || !this.A03.equals(bSVar.A03)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((((527 + this.A02.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A03.hashCode();
    }

    public final String toString() {
        return AnonymousClass08.A04(this.A01, this.A03.A06());
    }
}
