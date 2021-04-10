package X;

import android.content.Context;
import com.facebook.assistant.oacr.OacrConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class ZP {
    public static final Boolean A0J = false;
    public static final Double A0K = Double.valueOf(0.0d);
    public static final Long A0L = 0L;
    public static final AtomicInteger A0M = new AtomicInteger();
    public int A00 = AbstractC0447Yr.LOG_RATE_LIMIT;
    public ZH A01;
    public ZK A02 = null;
    public ZM A03 = null;
    public String A04;
    public Map A05;
    public Set A06 = new HashSet();
    public Set A07 = new HashSet();
    public Set A08 = new HashSet();
    public boolean A09 = false;
    public final Context A0A;
    public final C0167Fs A0B;
    public final GO A0C;
    public final C1421zD A0D = new C1421zD(this);
    public final Object A0E = new Object();
    public final Object A0F = new Object();
    public final Map A0G = new HashMap();
    public final Map A0H = new HashMap();
    public final AtomicBoolean A0I = new AtomicBoolean(false);

    public static String A02(ZP zp, String str, int i) {
        String bool;
        Number number;
        Number number2;
        if (i == 1) {
            bool = Boolean.toString(A01(zp, str).booleanValue());
        } else if (i != 2) {
            if (i == 3) {
                Object obj = zp.A0H.get(str);
                if (obj instanceof String) {
                    bool = (String) obj;
                }
            } else if (i != 4) {
                ZT.A01(zp.A0A, i);
            } else {
                Object obj2 = zp.A0H.get(str);
                if (obj2 instanceof Double) {
                    number2 = (Number) obj2;
                } else {
                    number2 = A0K;
                }
                bool = Double.toString(number2.doubleValue());
            }
            bool = OacrConstants.AUTO_SPEECH_DOMAIN;
        } else {
            Object obj3 = zp.A0H.get(str);
            if (obj3 instanceof Long) {
                number = (Number) obj3;
            } else {
                number = A0L;
            }
            bool = Long.toString(number.longValue());
        }
        return AnonymousClass08.A05(str, ":", bool);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0302, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass08.A04("could not find key in configs ", r12));
     */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x026c A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0295 A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02a9 A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02ef A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x032f A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x035f A[Catch:{ Exception -> 0x0376 }, LOOP:4: B:151:0x0359->B:153:0x035f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0398  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x03f1 A[Catch:{ Exception -> 0x04b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x03f8 A[Catch:{ Exception -> 0x04b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0413  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x041a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00b9 A[Catch:{ Exception -> 0x0376 }, LOOP:0: B:16:0x00b6->B:18:0x00b9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0451 A[Catch:{ JSONException -> 0x0456 }] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x045f A[Catch:{ JSONException -> 0x0464 }] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0467  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00cc A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0488 A[Catch:{ JSONException -> 0x048d }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d2 A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x04c2  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x04d1  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e8 A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x05b1  */
    /* JADX WARNING: Removed duplicated region for block: B:347:0x065a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0154 A[Catch:{ Exception -> 0x0376 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0165 A[Catch:{ Exception -> 0x0376 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ZP(android.content.Context r34) {
        /*
        // Method dump skipped, instructions count: 1688
        */
        throw new UnsupportedOperationException("Method not decompiled: X.ZP.<init>(android.content.Context):void");
    }

    public static ZR A00(ZP zp, Map map, String str) {
        ZR zr;
        synchronized (zp.A0E) {
            zr = (ZR) map.get(str);
        }
        if (zr == null || zr.A00 != ZQ.FromService) {
            return null;
        }
        return zr;
    }

    public static Boolean A01(ZP zp, String str) {
        Object obj = zp.A0H.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return A0J;
    }

    public static void A03(ZP zp) {
        ZM zm;
        synchronized (zp.A0F) {
            zm = zp.A03;
            if (zm == null) {
                zm = new ZM(zp);
                zp.A03 = zm;
            }
        }
        ZO zo = zm.A00;
        zo.A00.set(true);
        synchronized (zo) {
            zo.notifyAll();
        }
    }

    public static boolean A05(ZP zp) {
        GH gh = (GH) zp.A05.get("oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger");
        if (gh == null) {
            ZT.A08(zp.A0A, "Can't find param", "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger");
            return false;
        } else if (gh.A01 == 1) {
            return true;
        } else {
            ZT.A00(zp.A0A);
            return false;
        }
    }

    public static boolean A06(ZP zp, ZM zm, boolean z) {
        synchronized (zp.A0F) {
            if (zp.A03 != zm || (z && (!zp.A07.isEmpty() || !zp.A08.isEmpty() || !zp.A06.isEmpty()))) {
                return false;
            }
            zp.A03 = null;
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A04(X.ZP r8, java.util.Map r9, java.lang.String r10, java.lang.Object r11, java.lang.Integer r12, java.lang.String r13, boolean r14) {
        /*
        // Method dump skipped, instructions count: 316
        */
        throw new UnsupportedOperationException("Method not decompiled: X.ZP.A04(X.ZP, java.util.Map, java.lang.String, java.lang.Object, java.lang.Integer, java.lang.String, boolean):void");
    }
}
