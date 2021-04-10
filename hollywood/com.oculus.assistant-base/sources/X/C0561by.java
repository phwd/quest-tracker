package X;

import com.facebook.acra.LogCatCollector;
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

/* renamed from: X.by  reason: case insensitive filesystem */
public final class C0561by {
    public static final Charset A00 = Charset.forName(LogCatCollector.UTF_8_ENCODING);
    public static final TimeZone A01 = TimeZone.getTimeZone("GMT");
    public static final Pattern A02 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    public static final AbstractC0552bp A03;
    public static final AbstractC0555bs A04;
    public static final byte[] A05;
    public static final String[] A06 = new String[0];
    public static final Charset A07 = Charset.forName("UTF-16BE");
    public static final Charset A08 = Charset.forName("UTF-16LE");
    public static final Charset A09 = Charset.forName("UTF-32BE");
    public static final Charset A0A = Charset.forName("UTF-32LE");
    public static final C0603cm A0B = C0603cm.A01("feff");
    public static final C0603cm A0C = C0603cm.A01("fffe");
    public static final C0603cm A0D = C0603cm.A01("0000ffff");
    public static final C0603cm A0E = C0603cm.A01("ffff0000");
    public static final C0603cm A0F = C0603cm.A01("efbbbf");

    static {
        byte[] bArr = new byte[0];
        A05 = bArr;
        AnonymousClass33 r1 = new AnonymousClass33();
        r1.A0F(bArr, 0, 0);
        long j = (long) 0;
        A04 = new C1144tk(j, r1);
        byte[] bArr2 = A05;
        int length = bArr2.length;
        long j2 = (long) length;
        if ((j | j2) < 0 || j > j2 || j2 - j < j2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        A03 = new C1145tl(length, bArr2);
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

    public static String A04(C0544bh bhVar, boolean z) {
        String str = bhVar.A02;
        if (str.contains(":")) {
            str = AnonymousClass08.A05("[", str, "]");
        }
        if (!z && bhVar.A00 == C0544bh.A01(bhVar.A03)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        sb.append(bhVar.A00);
        return sb.toString();
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

    public static List A05(Object... objArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) objArr.clone()));
    }

    public static boolean A08(AssertionError assertionError) {
        if (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        if (r1 != Long.MAX_VALUE) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A0A(X.AbstractC0609cs r13, int r14, java.util.concurrent.TimeUnit r15) {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0561by.A0A(X.cs, int, java.util.concurrent.TimeUnit):boolean");
    }
}
