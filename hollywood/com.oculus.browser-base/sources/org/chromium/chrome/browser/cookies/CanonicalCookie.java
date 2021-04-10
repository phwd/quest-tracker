package org.chromium.chrome.browser.cookies;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CanonicalCookie {

    /* renamed from: a  reason: collision with root package name */
    public final String f10645a;
    public final String b;
    public final String c;
    public final String d;
    public final long e;
    public final long f;
    public final long g;
    public final boolean h;
    public final boolean i;
    public final int j;
    public final int k;
    public final boolean l;
    public final int m;
    public final int n;

    public CanonicalCookie(String str, String str2, String str3, String str4, long j2, long j3, long j4, boolean z, boolean z2, int i2, int i3, boolean z3, int i4, int i5) {
        this.f10645a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = j2;
        this.f = j3;
        this.g = j4;
        this.h = z;
        this.i = z2;
        this.j = i2;
        this.k = i3;
        this.l = z3;
        this.m = i4;
        this.n = i5;
    }

    public static List a(DataInputStream dataInputStream) {
        if (dataInputStream.readInt() == 20201111) {
            int readInt = dataInputStream.readInt();
            if (readInt >= 0) {
                ArrayList arrayList = new ArrayList(readInt);
                int i2 = 0;
                while (i2 < readInt) {
                    arrayList.add(new CanonicalCookie(dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readBoolean(), dataInputStream.readBoolean(), dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readBoolean(), dataInputStream.readInt(), dataInputStream.readInt()));
                    i2++;
                    readInt = readInt;
                }
                return arrayList;
            }
            throw new C3260jm(AbstractC2531fV.w("Negative length: ", readInt));
        }
        throw new C3260jm("Unexpected version");
    }

    public static void b(DataOutputStream dataOutputStream, CanonicalCookie[] canonicalCookieArr) {
        if (canonicalCookieArr != null) {
            for (CanonicalCookie canonicalCookie : canonicalCookieArr) {
                if (canonicalCookie == null) {
                    throw new IllegalArgumentException("cookies arg contains null value");
                }
            }
            int length = canonicalCookieArr.length;
            dataOutputStream.writeInt(20201111);
            dataOutputStream.writeInt(length);
            for (CanonicalCookie canonicalCookie2 : canonicalCookieArr) {
                dataOutputStream.writeUTF(canonicalCookie2.f10645a);
                dataOutputStream.writeUTF(canonicalCookie2.b);
                dataOutputStream.writeUTF(canonicalCookie2.c);
                dataOutputStream.writeUTF(canonicalCookie2.d);
                dataOutputStream.writeLong(canonicalCookie2.e);
                dataOutputStream.writeLong(canonicalCookie2.f);
                dataOutputStream.writeLong(canonicalCookie2.g);
                dataOutputStream.writeBoolean(canonicalCookie2.h);
                dataOutputStream.writeBoolean(canonicalCookie2.i);
                dataOutputStream.writeInt(canonicalCookie2.j);
                dataOutputStream.writeInt(canonicalCookie2.k);
                dataOutputStream.writeBoolean(canonicalCookie2.l);
                dataOutputStream.writeInt(canonicalCookie2.m);
                dataOutputStream.writeInt(canonicalCookie2.n);
            }
            return;
        }
        throw new IllegalArgumentException("cookies arg is null");
    }
}
