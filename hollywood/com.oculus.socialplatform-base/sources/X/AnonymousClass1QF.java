package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.rti.common.sharedprefs.IRtiSharedPrefsProvider;
import com.facebook.rti.common.time.Clock;
import com.facebook.rti.common.time.MonotonicClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.oculus.vrshell.notifications.NotificationUri;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* renamed from: X.1QF  reason: invalid class name */
public final class AnonymousClass1QF {
    public final AnonymousClass1QH A00;
    public final HashMap<AnonymousClass1Fj, AtomicLong> A01;
    public final Context A02;
    public final AnonymousClass1PM A03;
    public final RealtimeSinceBootClock A04;
    public final AnonymousClass1PJ A05;
    @Nullable
    public final AnonymousClass1QM<Boolean> A06;
    public final AnonymousClass1QQ A07;
    public final AnonymousClass1QS A08;
    public final AnonymousClass1XQ A09;
    public final String A0A;
    public final HashMap<String, AnonymousClass1PS> A0B;
    public final ConcurrentMap<String, AtomicLong> A0C = new ConcurrentHashMap();
    public final boolean A0D;
    public volatile Integer A0E;
    public volatile String A0F = "";
    public volatile String A0G = "";
    public volatile String A0H = "";
    public volatile String A0I = "";
    public volatile String A0J = "";

    public final synchronized <T extends AnonymousClass1PS> T A04(Class<T> cls) {
        String name;
        HashMap<String, AnonymousClass1PS> hashMap;
        T newInstance;
        try {
            name = cls.getName();
            hashMap = this.A0B;
            if (!hashMap.containsKey(name)) {
                if (cls == C06101Pi.class) {
                    newInstance = new C06101Pi(this.A02, this.A0A, this.A05, this.A04, this.A0D);
                } else if (cls == C06081Pg.class) {
                    newInstance = new C06081Pg(this.A02, this.A0A, this.A05, this.A04, this.A0D);
                } else if (cls == C06091Ph.class) {
                    newInstance = new C06091Ph(this.A02, this.A0A, this.A05, this.A04, this.A0D);
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

    public static AnonymousClass1Q8 A00(AnonymousClass1QF r7) {
        Map<String, ?> all;
        String str;
        String str2;
        String upperCase;
        String str3;
        String upperCase2;
        String str4;
        String upperCase3;
        String str5;
        AnonymousClass1Q8 r2 = (AnonymousClass1Q8) r7.A04(AnonymousClass1Q8.class);
        r2.A02(AnonymousClass1QG.ServiceName, r7.A0A);
        r2.A02(AnonymousClass1QG.ClientCoreName, r7.A0F);
        r2.A02(AnonymousClass1QG.NotificationStoreName, r7.A0H);
        Context context = r7.A02;
        SharedPreferences A002 = AnonymousClass1PN.A00(context, AnonymousClass1PL.ANALYTICS);
        r2.A02(AnonymousClass1QG.YearClass, String.valueOf(A002.getInt("year_class", 0)));
        AnonymousClass1PC A003 = r7.A03.A00(AnonymousClass1PL.GATEKEEPERS);
        AnonymousClass1QG r1 = AnonymousClass1QG.MqttGKs;
        synchronized (A003) {
            all = A003.A00.getAll();
        }
        r2.A02(r1, A02(all));
        r2.A02(AnonymousClass1QG.MqttFlags, A02(AnonymousClass1PN.A00(context, AnonymousClass1PL.FLAGS).getAll()));
        AnonymousClass1QM<Boolean> r0 = r7.A06;
        if (r0 != null) {
            AnonymousClass1QG r12 = AnonymousClass1QG.AppState;
            if (r0.get().booleanValue()) {
                str5 = "fg";
            } else {
                str5 = "bg";
            }
            r2.A02(r12, str5);
        }
        AnonymousClass1QG r13 = AnonymousClass1QG.ScreenState;
        if (r7.A09.A00()) {
            str = "1";
        } else {
            str = "0";
        }
        r2.A02(r13, str);
        AnonymousClass1QO A004 = r7.A07.A00(NotificationUri.PHONE, TelephonyManager.class);
        AnonymousClass1QG r14 = AnonymousClass1QG.Country;
        if (A004.A02()) {
            str2 = ((TelephonyManager) A004.A01()).getNetworkCountryIso();
            if (str2 == null) {
                upperCase = null;
            }
            upperCase = str2.toUpperCase();
        } else {
            str2 = "";
            upperCase = str2.toUpperCase();
        }
        r2.A02(r14, upperCase);
        AnonymousClass1QG r3 = AnonymousClass1QG.NetworkType;
        AnonymousClass1QS r6 = r7.A08;
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
        AnonymousClass1QG r32 = AnonymousClass1QG.NetworkSubtype;
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
        r2.A02(AnonymousClass1QG.IsEmployee, Boolean.valueOf(A002.getBoolean("is_employee", false)));
        r2.A02(AnonymousClass1QG.ValidCompatibleApps, r7.A0J);
        r2.A02(AnonymousClass1QG.EnabledCompatibleApps, r7.A0G);
        r2.A02(AnonymousClass1QG.RegisteredApps, r7.A0I);
        return r2;
    }

    public static AnonymousClass1Q5 A01(AnonymousClass1QF r9, long j) {
        AtomicLong atomicLong;
        AnonymousClass1Q5 r4 = (AnonymousClass1Q5) r9.A04(AnonymousClass1Q5.class);
        ((AtomicLong) r4.A00(AnonymousClass1QD.MqttDurationMs)).set(j);
        AtomicLong atomicLong2 = (AtomicLong) r4.A00(AnonymousClass1QD.NetworkDurationMs);
        AnonymousClass1QS r8 = r9.A08;
        AtomicLong atomicLong3 = r8.A06;
        long j2 = atomicLong3.get();
        long j3 = 0;
        if (j2 != 0) {
            j3 = SystemClock.elapsedRealtime() - j2;
        }
        atomicLong2.set(j3);
        AtomicLong atomicLong4 = (AtomicLong) r4.A00(AnonymousClass1QD.NetworkTotalDurationMs);
        long j4 = r8.A05.get();
        long j5 = atomicLong3.get();
        long j6 = 0;
        if (j5 != 0) {
            j6 = SystemClock.elapsedRealtime() - j5;
        }
        atomicLong4.set(j4 + j6);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        AnonymousClass1Fj r5 = AnonymousClass1Fj.ServiceCreatedTimestamp;
        synchronized (r9) {
            HashMap<AnonymousClass1Fj, AtomicLong> hashMap = r9.A01;
            if (!hashMap.containsKey(r5)) {
                hashMap.put(r5, new AtomicLong());
            }
            atomicLong = hashMap.get(r5);
        }
        ((AtomicLong) r4.A00(AnonymousClass1QD.ServiceDurationMs)).set(elapsedRealtime - atomicLong.get());
        return r4;
    }

    public static String A02(Map<String, ?> map) {
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

    public final void A05(String str, String str2, String str3) {
        boolean booleanValue;
        String str4;
        String str5;
        AnonymousClass1QM<Boolean> r0 = this.A06;
        if (r0 == null) {
            booleanValue = false;
        } else {
            booleanValue = r0.get().booleanValue();
        }
        AnonymousClass1QI r5 = AnonymousClass1QI.A02;
        boolean z = false;
        if (SystemClock.elapsedRealtime() - r5.A00 > 17000) {
            z = true;
        }
        StringBuilder sb = new StringBuilder();
        if (booleanValue) {
            sb.append(str);
            str4 = "_FG";
        } else {
            sb.append(str);
            str4 = "_BG";
        }
        sb.append(str4);
        String obj = sb.toString();
        String str6 = "fg";
        if (z) {
            str5 = "rw";
        } else {
            str5 = "nw";
        }
        if (booleanValue) {
            ((AnonymousClass1PR) A04(C06081Pg.class)).A03(1, "tc", str6, str5, str3);
        } else {
            ((AnonymousClass1PR) A04(C06081Pg.class)).A03(1, "tc", "bg", str5, str3);
            str6 = "bg";
        }
        if (!TextUtils.isEmpty(str2)) {
            if (str2.startsWith("/")) {
                obj = str2.substring(1);
            } else {
                obj = str2;
            }
        }
        ((AnonymousClass1PR) A04(C06091Ph.class)).A03(1, obj, str6);
        r5.A00 = SystemClock.elapsedRealtime();
    }

    public AnonymousClass1QF(Context context, AnonymousClass1QQ r3, String str, AnonymousClass1QS r5, AnonymousClass1XQ r6, MonotonicClock monotonicClock, Clock clock, @Nullable AnonymousClass1QM<Boolean> r9, boolean z, IRtiSharedPrefsProvider iRtiSharedPrefsProvider) {
        this.A02 = context;
        this.A07 = r3;
        this.A0A = str;
        this.A08 = r5;
        this.A09 = r6;
        this.A00 = new AnonymousClass1QH(context, monotonicClock);
        this.A05 = clock;
        this.A04 = monotonicClock;
        this.A06 = r9;
        this.A01 = new HashMap<>();
        this.A0B = new HashMap<>();
        this.A0D = z;
        this.A03 = iRtiSharedPrefsProvider;
    }

    public final AnonymousClass1Q6 A03(long j) {
        return new AnonymousClass1Q6(A00(this), A01(this, j), null, (AnonymousClass1QA) A04(AnonymousClass1QA.class), null, null, null, null, true, false);
    }
}
