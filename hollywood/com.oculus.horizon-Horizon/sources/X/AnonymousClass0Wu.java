package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.squareup.okhttp.internal.DiskLruCache;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* renamed from: X.0Wu  reason: invalid class name */
public final class AnonymousClass0Wu {
    public final C01710Ww A00;
    public final ConcurrentMap<String, AtomicLong> A01 = new ConcurrentHashMap();
    public final Context A02;
    public final C06510nV A03;
    public final RealtimeSinceBootClock A04;
    public final AnonymousClass0nN A05;
    public final C01630Wg A06;
    public final AnonymousClass0XJ A07;
    public final AnonymousClass0XL A08;
    public final String A09;
    public final HashMap<EnumC01700Wt, AtomicLong> A0A;
    public final HashMap<String, AbstractC01730Wz> A0B;
    public final boolean A0C;
    public volatile Integer A0D;
    public volatile String A0E = "";
    public volatile String A0F = "";
    public volatile String A0G = "";
    public volatile String A0H = "";
    public volatile String A0I = "";

    public static synchronized AtomicLong A04(AnonymousClass0Wu r2, EnumC01700Wt r3) {
        AtomicLong atomicLong;
        synchronized (r2) {
            HashMap<EnumC01700Wt, AtomicLong> hashMap = r2.A0A;
            if (!hashMap.containsKey(r3)) {
                hashMap.put(r3, new AtomicLong());
            }
            atomicLong = hashMap.get(r3);
        }
        return atomicLong;
    }

    public final synchronized <T extends AbstractC01730Wz> T A06(Class<T> cls) {
        String name;
        HashMap<String, AbstractC01730Wz> hashMap;
        T newInstance;
        try {
            name = cls.getName();
            hashMap = this.A0B;
            if (!hashMap.containsKey(name)) {
                if (cls == AnonymousClass0Ic.class) {
                    newInstance = new AnonymousClass0Ic(this.A02, this.A09, this.A05, this.A04, this.A0C);
                } else if (cls == AnonymousClass0IU.class) {
                    newInstance = new AnonymousClass0IU(this.A02, this.A09, this.A05, this.A04, this.A0C);
                } else if (cls == AnonymousClass0IV.class) {
                    newInstance = new AnonymousClass0IV(this.A02, this.A09, this.A05, this.A04, this.A0C);
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

    public final void A07(String str, String str2, String str3, boolean z) {
        char c;
        C02590an r0 = C02590an.A02;
        boolean z2 = false;
        if (SystemClock.elapsedRealtime() - r0.A00 > 17000) {
            z2 = true;
        }
        String str4 = r0.A01;
        if (str4 != null && (z ? EnumC02120Zg.PINGRESP.name().equals(str) : EnumC02120Zg.PINGREQ.name().equals(str))) {
            str = AnonymousClass006.A07(str, "_", str4);
        }
        String A052 = AnonymousClass006.A05(str, "_BG");
        if (z2) {
            ((AnonymousClass0nI) A06(AnonymousClass0IU.class)).A03(1, "tc", "bg", "rw", str3);
        } else {
            ((AnonymousClass0nI) A06(AnonymousClass0IU.class)).A03(1, "tc", "bg", "nw", str3);
        }
        if (TextUtils.isEmpty(str2)) {
            c = 1;
        } else if (str2.startsWith("/")) {
            c = 1;
            A052 = str2.substring(1);
        } else {
            c = 1;
            A052 = str2;
        }
        String[] strArr = new String[2];
        strArr[0] = A052;
        strArr[c] = "bg";
        ((AnonymousClass0nI) A06(AnonymousClass0IV.class)).A03(1, strArr);
        r0.A00 = SystemClock.elapsedRealtime();
    }

    public static AnonymousClass0Ib A00(AnonymousClass0Wu r7) {
        String str;
        String str2;
        String upperCase;
        String str3;
        String upperCase2;
        String str4;
        String upperCase3;
        AnonymousClass0Ib r2 = (AnonymousClass0Ib) r7.A06(AnonymousClass0Ib.class);
        r2.A02(EnumC06500nH.ServiceName, r7.A09);
        r2.A02(EnumC06500nH.ClientCoreName, r7.A0E);
        r2.A02(EnumC06500nH.NotificationStoreName, r7.A0G);
        Context context = r7.A02;
        SharedPreferences A002 = AnonymousClass0WJ.A00(context, AnonymousClass0WE.ANALYTICS);
        r2.A02(EnumC06500nH.YearClass, String.valueOf(A002.getInt("year_class", 0)));
        r2.A02(EnumC06500nH.MqttGKs, A03(r7.A03.A00(AnonymousClass0WE.GATEKEEPERS).A2x()));
        r2.A02(EnumC06500nH.MqttFlags, A03(AnonymousClass0WJ.A00(context, AnonymousClass0WE.FLAGS).getAll()));
        EnumC06500nH r1 = EnumC06500nH.ScreenState;
        if (r7.A08.A00()) {
            str = DiskLruCache.VERSION_1;
        } else {
            str = "0";
        }
        r2.A02(r1, str);
        AnonymousClass0W8 A003 = r7.A06.A00("phone", TelephonyManager.class);
        EnumC06500nH r12 = EnumC06500nH.Country;
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
        EnumC06500nH r3 = EnumC06500nH.NetworkType;
        AnonymousClass0XJ r6 = r7.A07;
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
        EnumC06500nH r32 = EnumC06500nH.NetworkSubtype;
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
        r2.A02(EnumC06500nH.IsEmployee, Boolean.valueOf(A002.getBoolean("is_employee", false)));
        r2.A02(EnumC06500nH.ValidCompatibleApps, r7.A0I);
        r2.A02(EnumC06500nH.EnabledCompatibleApps, r7.A0F);
        r2.A02(EnumC06500nH.RegisteredApps, r7.A0H);
        return r2;
    }

    public static AnonymousClass0IW A01(AnonymousClass0Wu r9, long j) {
        AnonymousClass0IW r4 = (AnonymousClass0IW) r9.A06(AnonymousClass0IW.class);
        ((AtomicLong) r4.A00(AnonymousClass0nF.MqttDurationMs)).set(j);
        AtomicLong atomicLong = (AtomicLong) r4.A00(AnonymousClass0nF.NetworkDurationMs);
        AnonymousClass0XJ r8 = r9.A07;
        AtomicLong atomicLong2 = r8.A05;
        long j2 = atomicLong2.get();
        long j3 = 0;
        if (j2 != 0) {
            j3 = SystemClock.elapsedRealtime() - j2;
        }
        atomicLong.set(j3);
        AtomicLong atomicLong3 = (AtomicLong) r4.A00(AnonymousClass0nF.NetworkTotalDurationMs);
        long j4 = r8.A04.get();
        long j5 = atomicLong2.get();
        long j6 = 0;
        if (j5 != 0) {
            j6 = SystemClock.elapsedRealtime() - j5;
        }
        atomicLong3.set(j4 + j6);
        ((AtomicLong) r4.A00(AnonymousClass0nF.ServiceDurationMs)).set(SystemClock.elapsedRealtime() - A04(r9, EnumC01700Wt.ServiceCreatedTimestamp).get());
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

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/0Wg;Ljava/lang/String;LX/0XJ;LX/0XL;Lcom/facebook/rti/common/time/MonotonicClock;Lcom/facebook/rti/common/time/Clock;LX/0WY<Ljava/lang/Boolean;>;ZLcom/facebook/rti/common/sharedprefs/IRtiSharedPrefsProvider;)V */
    public AnonymousClass0Wu(Context context, C01630Wg r3, String str, AnonymousClass0XJ r5, AnonymousClass0XL r6, RealtimeSinceBootClock realtimeSinceBootClock, AnonymousClass0nN r8, @Nullable boolean z, C06510nV r10) {
        this.A02 = context;
        this.A06 = r3;
        this.A09 = str;
        this.A07 = r5;
        this.A08 = r6;
        this.A00 = new C01710Ww(context, realtimeSinceBootClock);
        this.A05 = r8;
        this.A04 = realtimeSinceBootClock;
        this.A0A = new HashMap<>();
        this.A0B = new HashMap<>();
        this.A0C = z;
        this.A03 = r10;
    }

    public static String A02(List<String> list) {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            Map<String, Integer> A032 = ((AbstractC01570Vx) AnonymousClass0W2.A00).A03();
            if (A032.containsKey(next)) {
                listIterator.set(String.valueOf(A032.get(next)));
            } else {
                AnonymousClass0NO.A0F("MqttHealthStatsHelper", "appPkgName %s not found in encoding map", next);
            }
        }
        return TextUtils.join(";", list);
    }

    public final AnonymousClass0Ws A05(long j) {
        return new AnonymousClass0Ws(A00(this), A01(this, j), null, (AnonymousClass0Ia) A06(AnonymousClass0Ia.class), null, null, null, null, true, false);
    }
}
