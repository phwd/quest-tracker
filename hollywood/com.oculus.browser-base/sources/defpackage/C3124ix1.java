package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: ix1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3124ix1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f10176a = Pattern.compile("webapk:\\d+:([a-fA-F0-9]+)");
    public final ByteBuffer b;
    public int c;
    public int d;
    public int e;
    public String f;
    public ArrayList g;

    public C3124ix1(ByteBuffer byteBuffer) {
        this.b = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] b(String str) {
        Matcher matcher = f10176a.matcher(str);
        byte[] bArr = null;
        if (!matcher.find()) {
            return null;
        }
        String group = matcher.group(1);
        int length = group.length();
        if (length % 2 == 0) {
            bArr = new byte[(length / 2)];
            for (int i = 0; i < length; i += 2) {
                bArr[i / 2] = (byte) (Character.digit(group.charAt(i + 1), 16) + (Character.digit(group.charAt(i), 16) << 4));
            }
        }
        return bArr;
    }

    public int a(Signature signature) {
        Collections.sort(this.g);
        Iterator it = this.g.iterator();
        int i = 0;
        while (it.hasNext()) {
            C2954hx1 hx1 = (C2954hx1) it.next();
            if (hx1.G.indexOf("META-INF/") == 0) {
                i++;
                if (i > 5) {
                    return 6;
                }
            } else {
                byte[] bytes = hx1.G.getBytes();
                int length = bytes.length;
                ByteBuffer allocate = ByteBuffer.allocate(4);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                allocate.order(byteOrder);
                allocate.putInt(length);
                signature.update(allocate.array());
                signature.update(bytes);
                int i2 = hx1.f10113J;
                ByteBuffer allocate2 = ByteBuffer.allocate(4);
                allocate2.order(byteOrder);
                allocate2.putInt(i2);
                signature.update(allocate2.array());
                this.b.position(hx1.H + hx1.I);
                ByteBuffer slice = this.b.slice();
                slice.limit(hx1.f10113J);
                signature.update(slice);
            }
        }
        return 0;
    }

    public final int c() {
        return this.b.getShort();
    }

    public final int d() {
        return this.b.getInt();
    }

    public int e() {
        this.g = new ArrayList(this.c);
        this.b.position(this.d);
        for (int i = 0; i < this.c; i++) {
            if (((long) d()) != 33639248) {
                return 1;
            }
            h(16);
            int d2 = d();
            h(4);
            int c2 = c();
            int c3 = c();
            int c4 = c();
            h(8);
            int d3 = d();
            String g2 = g(c2);
            h(c3 + c4);
            if (c3 > 4160) {
                return 2;
            }
            if (c4 > 0) {
                return 3;
            }
            this.g.add(new C2954hx1(g2, d3, d2));
        }
        if (this.b.position() != this.e) {
            return 7;
        }
        Collections.sort(this.g, C2954hx1.F);
        Iterator it = this.g.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            C2954hx1 hx1 = (C2954hx1) it.next();
            int i3 = hx1.H;
            if (i3 != i2) {
                return 7;
            }
            this.b.position(i3);
            if (((long) d()) != 67324752) {
                return 1;
            }
            h(2);
            int c5 = c();
            h(18);
            int c6 = c();
            int c7 = c();
            if (c7 > 4160) {
                return 2;
            }
            int position = this.b.position();
            int i4 = hx1.H;
            int i5 = (position - i4) + c6 + c7;
            hx1.I = i5;
            int i6 = i4 + i5 + hx1.f10113J;
            if ((c5 & 8) != 0) {
                this.b.position(i6);
                i6 = ((long) d()) == 134695760 ? i6 + 16 : i6 + 12;
            }
            i2 = i6;
        }
        int i7 = this.d;
        if (i2 != i7) {
            this.b.position(i7 - 16);
            if (!"APK Sig Block 42".equals(g(16))) {
                return 7;
            }
            if (this.d - i2 > 24576) {
                return 8;
            }
        }
        return 0;
    }

    public final int f() {
        int limit = this.b.limit() - 22;
        int max = Math.max(0, limit - 65536);
        while (true) {
            if (limit < max) {
                limit = -1;
                break;
            }
            this.b.position(limit);
            if (((long) d()) == 101010256) {
                break;
            }
            limit--;
        }
        if (limit < 0) {
            return 1;
        }
        this.e = limit;
        this.b.position(limit + 10);
        this.c = c();
        h(4);
        this.d = d();
        this.f = g(c());
        if (this.b.position() < this.b.limit()) {
            return 7;
        }
        return 0;
    }

    public final String g(int i) {
        if (i <= 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        this.b.get(bArr);
        return new String(bArr);
    }

    public final void h(int i) {
        ByteBuffer byteBuffer = this.b;
        byteBuffer.position(byteBuffer.position() + i);
    }

    public int i(PublicKey publicKey) {
        byte[] b2 = b(this.f);
        if (b2 == null || b2.length == 0) {
            return 5;
        }
        try {
            Signature instance = Signature.getInstance("SHA256withECDSA");
            instance.initVerify(publicKey);
            int a2 = a(instance);
            if (a2 != 0) {
                return a2;
            }
            return instance.verify(b2) ? 0 : 4;
        } catch (Exception e2) {
            AbstractC1220Ua0.a("WebApkVerifySignature", "Exception calculating signature", e2);
            return 4;
        }
    }
}
