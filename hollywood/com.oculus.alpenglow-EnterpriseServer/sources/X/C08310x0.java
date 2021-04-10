package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.oculus.util.constants.OculusConstants;
import com.squareup.okhttp.internal.DiskLruCache;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* renamed from: X.0x0  reason: invalid class name and case insensitive filesystem */
public final class C08310x0 {
    public final AnonymousClass0yS A00;
    public final ConcurrentMap<String, AtomicLong> A01 = new ConcurrentHashMap();
    public final Context A02;
    public final C07710vp A03;
    public final RealtimeSinceBootClock A04;
    public final C07640vh A05;
    public final C08800xq A06;
    public final C08400xB A07;
    public final C08270ww A08;
    public final String A09;
    public final HashMap<AnonymousClass0z3, AtomicLong> A0A;
    public final HashMap<String, AbstractC07670vl> A0B;
    public final boolean A0C;
    public volatile Integer A0D;
    public volatile String A0E = "";
    public volatile String A0F = "";
    public volatile String A0G = "";
    public volatile String A0H = "";
    public volatile String A0I = "";

    public static synchronized AtomicLong A04(C08310x0 r2, AnonymousClass0z3 r3) {
        AtomicLong atomicLong;
        synchronized (r2) {
            HashMap<AnonymousClass0z3, AtomicLong> hashMap = r2.A0A;
            if (!hashMap.containsKey(r3)) {
                hashMap.put(r3, new AtomicLong());
            }
            atomicLong = hashMap.get(r3);
        }
        return atomicLong;
    }

    public final synchronized <T extends AbstractC07670vl> T A06(Class<T> cls) {
        String name;
        HashMap<String, AbstractC07670vl> hashMap;
        T newInstance;
        try {
            name = cls.getName();
            hashMap = this.A0B;
            if (!hashMap.containsKey(name)) {
                if (cls == C09510za.class) {
                    newInstance = new C09510za(this.A02, this.A09, this.A05, this.A04, this.A0C);
                } else if (cls == C09490zY.class) {
                    newInstance = new C09490zY(this.A02, this.A09, this.A05, this.A04, this.A0C);
                } else if (cls == C09500zZ.class) {
                    newInstance = new C09500zZ(this.A02, this.A09, this.A05, this.A04, this.A0C);
                } else {
                    newInstance = cls.newInstance();
                }
                hashMap.put(name, newInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException("Incorrect stat category used:", e);
        }
        return (T) hashMap.get(name);
    }

    public static AnonymousClass102 A00(C08310x0 r7) {
        String str;
        String str2;
        String upperCase;
        String str3;
        String upperCase2;
        String str4;
        String upperCase3;
        AnonymousClass102 r2 = (AnonymousClass102) r7.A06(AnonymousClass102.class);
        r2.A02(EnumC08540xP.ServiceName, r7.A09);
        r2.A02(EnumC08540xP.ClientCoreName, r7.A0E);
        r2.A02(EnumC08540xP.NotificationStoreName, r7.A0G);
        Context context = r7.A02;
        SharedPreferences A002 = C07680vm.A00(context, EnumC07690vn.ANALYTICS);
        r2.A02(EnumC08540xP.YearClass, String.valueOf(A002.getInt("year_class", 0)));
        r2.A02(EnumC08540xP.MqttGKs, A03(r7.A03.A00(EnumC07690vn.GATEKEEPERS).A2u()));
        r2.A02(EnumC08540xP.MqttFlags, A03(C07680vm.A00(context, EnumC07690vn.FLAGS).getAll()));
        EnumC08540xP r1 = EnumC08540xP.ScreenState;
        if (r7.A08.A00()) {
            str = DiskLruCache.VERSION_1;
        } else {
            str = OculusConstants.DEFAULT_ENTERPRISE_USER_ID;
        }
        r2.A02(r1, str);
        AbstractC09150yk A003 = r7.A06.A00("phone", TelephonyManager.class);
        EnumC08540xP r12 = EnumC08540xP.Country;
        if (A003.A02()) {
            str2 = ((TelephonyManager) A003.A01()).getNetworkCountryIso();
            if (str2 == null) {
                upperCase = null;
            }
            upperCase = str2.toUpperCase();
        } else {
            str2 = "";
            upperCase = str2.toUpperCase();
        }
        r2.A02(r12, upperCase);
        EnumC08540xP r3 = EnumC08540xP.NetworkType;
        C08400xB r6 = r7.A07;
        NetworkInfo A022 = r6.A02();
        if (A022 == null || TextUtils.isEmpty(A022.getTypeName())) {
            str3 = "none";
            upperCase2 = str3.toUpperCase();
        } else {
            str3 = A022.getTypeName();
            if (str3 == null) {
                upperCase2 = null;
            }
            upperCase2 = str3.toUpperCase();
        }
        r2.A02(r3, upperCase2);
        EnumC08540xP r32 = EnumC08540xP.NetworkSubtype;
        NetworkInfo A023 = r6.A02();
        if (A023 == null || TextUtils.isEmpty(A023.getSubtypeName())) {
            str4 = "none";
            upperCase3 = str4.toUpperCase();
        } else {
            str4 = A023.getSubtypeName();
            if (str4 == null) {
                upperCase3 = null;
            }
            upperCase3 = str4.toUpperCase();
        }
        r2.A02(r32, upperCase3);
        r2.A02(EnumC08540xP.IsEmployee, Boolean.valueOf(A002.getBoolean("is_employee", false)));
        r2.A02(EnumC08540xP.ValidCompatibleApps, r7.A0I);
        r2.A02(EnumC08540xP.EnabledCompatibleApps, r7.A0F);
        r2.A02(EnumC08540xP.RegisteredApps, r7.A0H);
        return r2;
    }

    public static C08560xS A01(C08310x0 r9, long j) {
        C08560xS r4 = (C08560xS) r9.A06(C08560xS.class);
        ((AtomicLong) r4.A00(EnumC08570xT.MqttDurationMs)).set(j);
        AtomicLong atomicLong = (AtomicLong) r4.A00(EnumC08570xT.NetworkDurationMs);
        C08400xB r8 = r9.A07;
        AtomicLong atomicLong2 = r8.A06;
        long j2 = atomicLong2.get();
        long j3 = 0;
        if (j2 != 0) {
            j3 = SystemClock.elapsedRealtime() - j2;
        }
        atomicLong.set(j3);
        AtomicLong atomicLong3 = (AtomicLong) r4.A00(EnumC08570xT.NetworkTotalDurationMs);
        long j4 = r8.A05.get();
        long j5 = atomicLong2.get();
        long j6 = 0;
        if (j5 != 0) {
            j6 = SystemClock.elapsedRealtime() - j5;
        }
        atomicLong3.set(j4 + j6);
        ((AtomicLong) r4.A00(EnumC08570xT.ServiceDurationMs)).set(SystemClock.elapsedRealtime() - A04(r9, AnonymousClass0z3.ServiceCreatedTimestamp).get());
        return r4;
    }

    public static String A03(Map<String, ?> map) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(";");
            }
            sb.append(entry.getKey());
            sb.append("|");
            sb.append(String.valueOf(entry.getValue()));
        }
        return sb.toString();
    }

    public final void A07(String str, String str2, String str3, boolean z) {
        AnonymousClass10A r0 = AnonymousClass10A.A02;
        boolean z2 = false;
        if (SystemClock.elapsedRealtime() - r0.A00 > 17000) {
            z2 = true;
        }
        String str4 = r0.A01;
        if (str4 != null && (z ? EnumC08830xt.PINGRESP.name().equals(str) : EnumC08830xt.PINGREQ.name().equals(str))) {
            str = AnonymousClass006.A07(str, "_", str4);
        }
        String A052 = AnonymousClass006.A05(str, "_BG");
        if (z2) {
            ((AbstractC07790w1) A06(C09490zY.class)).A03(1, "tc", "bg", "rw", str3);
        } else {
            ((AbstractC07790w1) A06(C09490zY.class)).A03(1, "tc", "bg", "nw", str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            if (str2.startsWith("/")) {
                A052 = str2.substring(1);
            } else {
                A052 = str2;
            }
        }
        ((AbstractC07790w1) A06(C09500zZ.class)).A03(1, A052, "bg");
        r0.A00 = SystemClock.elapsedRealtime();
    }

    public static String A02(List<String> list) {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            Map<String, Integer> map = ((AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00)).A03;
            if (map.containsKey(next)) {
                listIterator.set(String.valueOf(map.get(next)));
            } else {
                AnonymousClass0NK.A07("MqttHealthStatsHelper", "appPkgName %s not found in encoding map", next);
            }
        }
        return TextUtils.join(";", list);
    }

    public final C08550xR A05(long j) {
        return new C08550xR(A00(this), A01(this, j), null, (C09210yr) A06(C09210yr.class), null, null, null, null, true, false);
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/0xq;Ljava/lang/String;LX/0xB;LX/0ww;Lcom/facebook/rti/common/time/MonotonicClock;Lcom/facebook/rti/common/time/Clock;LX/0zk<Ljava/lang/Boolean;>;ZLcom/facebook/rti/common/sharedprefs/IRtiSharedPrefsProvider;)V */
    public C08310x0(Context context, C08800xq r3, String str, C08400xB r5, C08270ww r6, RealtimeSinceBootClock realtimeSinceBootClock, C07640vh r8, @Nullable boolean z, C07710vp r10) {
        this.A02 = context;
        this.A06 = r3;
        this.A09 = str;
        this.A07 = r5;
        this.A08 = r6;
        this.A00 = new AnonymousClass0yS(context, realtimeSinceBootClock);
        this.A05 = r8;
        this.A04 = realtimeSinceBootClock;
        this.A0A = new HashMap<>();
        this.A0B = new HashMap<>();
        this.A0C = z;
        this.A03 = r10;
    }
}
