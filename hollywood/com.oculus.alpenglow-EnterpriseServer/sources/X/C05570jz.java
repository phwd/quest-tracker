package X;

import java.io.Closeable;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* renamed from: X.0jz  reason: invalid class name and case insensitive filesystem */
public final class C05570jz {
    public static final Charset A00 = Charset.forName("UTF-8");
    public static final TimeZone A01 = TimeZone.getTimeZone("GMT");
    public static final Pattern A02 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    public static final AbstractC05690kc A03;
    public static final AbstractC05650kC A04;
    public static final byte[] A05;
    public static final String[] A06 = new String[0];
    public static final Charset A07 = Charset.forName("UTF-16BE");
    public static final Charset A08 = Charset.forName("UTF-16LE");
    public static final Charset A09 = Charset.forName("UTF-32BE");
    public static final Charset A0A = Charset.forName("UTF-32LE");
    public static final C04610h7 A0B = C04610h7.A03("feff");
    public static final C04610h7 A0C = C04610h7.A03("fffe");
    public static final C04610h7 A0D = C04610h7.A03("0000ffff");
    public static final C04610h7 A0E = C04610h7.A03("ffff0000");
    public static final C04610h7 A0F = C04610h7.A03("efbbbf");

    static {
        byte[] bArr = new byte[0];
        A05 = bArr;
        AnonymousClass0HR r1 = new AnonymousClass0HR();
        r1.A0I(bArr);
        long j = (long) 0;
        A04 = new AnonymousClass0Pz(j, r1);
        byte[] bArr2 = A05;
        int length = bArr2.length;
        long j2 = (long) length;
        if ((j | j2) < 0 || j > j2 || j2 - j < j2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        A03 = new AnonymousClass0Q0(length, bArr2);
    }

    public static String A03(String str) {
        boolean z;
        try {
            String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (!lowerCase.isEmpty()) {
                int i = 0;
                while (true) {
                    if (i >= lowerCase.length()) {
                        z = false;
                        break;
                    }
                    char charAt = lowerCase.charAt(i);
                    if (charAt <= 31 || charAt >= 127 || " #%/:?@[\\]".indexOf(charAt) != -1) {
                        z = true;
                    } else {
                        i++;
                    }
                }
                z = true;
                if (!z) {
                    return lowerCase;
                }
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static int A00(String str, int i, int i2) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static int A01(String str, int i, int i2) {
        while (true) {
            i2--;
            if (i2 < i) {
                return i;
            }
            char charAt = str.charAt(i2);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i2 + 1;
            }
        }
    }

    public static int A02(String str, int i, int i2, String str2) {
        while (i < i2) {
            if (str2.indexOf(str.charAt(i)) != -1) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static String A04(C05890la r4, boolean z) {
        String str = r4.A02;
        if (str.contains(":")) {
            str = AnonymousClass006.A07("[", str, "]");
        }
        if (z || r4.A00 != C05890la.A01(r4.A03)) {
            return AnonymousClass006.A06(str, ":", r4.A00);
        }
        return str;
    }

    public static void A06(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static void A07(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!A08(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static boolean A09(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(Ljava/lang/Class<TT;>;[TT;[TT;)[TT; */
    public static Object[] A0B(Object[] objArr, Object[] objArr2) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            int length = objArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Object obj2 = objArr2[i];
                if (obj.equals(obj2)) {
                    arrayList.add(obj2);
                    break;
                }
                i++;
            }
        }
        return arrayList.toArray((Object[]) Array.newInstance(String.class, arrayList.size()));
    }

    public static <T> List<T> A05(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static boolean A08(AssertionError assertionError) {
        if (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        if (r0 != Long.MAX_VALUE) goto L_0x0066;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A0A(X.AbstractC04550h0 r12, int r13, java.util.concurrent.TimeUnit r14) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05570jz.A0A(X.0h0, int, java.util.concurrent.TimeUnit):boolean");
    }
}
