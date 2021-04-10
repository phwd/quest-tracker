package X;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.room.InvalidationTracker;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.0FX  reason: invalid class name */
public final class AnonymousClass0FX {
    public static final String[] A0B = {"UPDATE", "DELETE", "INSERT"};
    public AnonymousClass0FU A00;
    @VisibleForTesting
    public Runnable A01 = new AnonymousClass0FT(this);
    @NonNull
    public Map<String, Set<String>> A02;
    public AtomicBoolean A03 = new AtomicBoolean(false);
    @SuppressLint({"RestrictedApi"})
    @VisibleForTesting
    public final C005905t<InvalidationTracker.Observer, AnonymousClass0FW> A04 = new C005905t<>();
    public final AnonymousClass0Fr A05;
    @NonNull
    @VisibleForTesting
    public final HashMap<String, Integer> A06;
    public final String[] A07;
    public final AnonymousClass0FS A08;
    public volatile AbstractC03360cA A09;
    public volatile boolean A0A = false;

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public AnonymousClass0FX(AnonymousClass0Fr r7, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.A05 = r7;
        int length = strArr.length;
        this.A00 = new AnonymousClass0FU(length);
        this.A06 = new HashMap<>();
        this.A02 = map2;
        this.A08 = new AnonymousClass0FS(this.A05);
        this.A07 = new String[length];
        for (int i = 0; i < length; i++) {
            String lowerCase = strArr[i].toLowerCase(Locale.US);
            this.A06.put(lowerCase, Integer.valueOf(i));
            String str = map.get(strArr[i]);
            if (str != null) {
                this.A07[i] = str.toLowerCase(Locale.US);
            } else {
                this.A07[i] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String lowerCase2 = entry.getValue().toLowerCase(Locale.US);
            if (this.A06.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(Locale.US);
                HashMap<String, Integer> hashMap = this.A06;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void A00(AnonymousClass0GQ r14) {
        ReentrantReadWriteLock.ReadLock readLock;
        Throwable th;
        if (!r14.A58()) {
            while (true) {
                readLock = this.A05.mCloseLock.readLock();
                readLock.lock();
                try {
                    AnonymousClass0FU r6 = this.A00;
                    synchronized (r6) {
                        try {
                            if (r6.A00 && !r6.A01) {
                                long[] jArr = r6.A03;
                                int length = jArr.length;
                                int i = 0;
                                while (true) {
                                    int i2 = 1;
                                    if (i >= length) {
                                        break;
                                    }
                                    boolean z = false;
                                    if (jArr[i] > 0) {
                                        z = true;
                                    }
                                    boolean[] zArr = r6.A04;
                                    if (z != zArr[i]) {
                                        int[] iArr = r6.A02;
                                        if (!z) {
                                            i2 = 2;
                                        }
                                        iArr[i] = i2;
                                    } else {
                                        r6.A02[i] = 0;
                                    }
                                    zArr[i] = z;
                                    i++;
                                }
                                r6.A01 = true;
                                r6.A00 = false;
                                int[] iArr2 = r6.A02;
                                if (iArr2 == null) {
                                    break;
                                }
                                int length2 = iArr2.length;
                                r14.A1H();
                                for (int i3 = 0; i3 < length2; i3++) {
                                    try {
                                        int i4 = iArr2[i3];
                                        if (i4 == 1) {
                                            r14.A2Q(AnonymousClass006.A02("INSERT OR IGNORE INTO room_table_modification_log VALUES(", i3, ", 0)"));
                                            String str = this.A07[i3];
                                            StringBuilder sb = new StringBuilder();
                                            String[] strArr = A0B;
                                            for (String str2 : strArr) {
                                                sb.setLength(0);
                                                sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
                                                sb.append("`");
                                                sb.append("room_table_modification_trigger_");
                                                sb.append(str);
                                                sb.append("_");
                                                sb.append(str2);
                                                sb.append("`");
                                                sb.append(" AFTER ");
                                                sb.append(str2);
                                                sb.append(" ON `");
                                                sb.append(str);
                                                sb.append("` BEGIN UPDATE ");
                                                sb.append("room_table_modification_log");
                                                sb.append(" SET ");
                                                sb.append("invalidated");
                                                sb.append(" = 1");
                                                sb.append(" WHERE ");
                                                sb.append("table_id");
                                                sb.append(" = ");
                                                sb.append(i3);
                                                sb.append(" AND ");
                                                sb.append("invalidated");
                                                sb.append(" = 0");
                                                sb.append("; END");
                                                r14.A2Q(sb.toString());
                                            }
                                        } else if (i4 == 2) {
                                            String str3 = this.A07[i3];
                                            StringBuilder sb2 = new StringBuilder();
                                            String[] strArr2 = A0B;
                                            for (String str4 : strArr2) {
                                                sb2.setLength(0);
                                                sb2.append("DROP TRIGGER IF EXISTS ");
                                                sb2.append("`");
                                                sb2.append("room_table_modification_trigger_");
                                                sb2.append(str3);
                                                sb2.append("_");
                                                sb2.append(str4);
                                                sb2.append("`");
                                                r14.A2Q(sb2.toString());
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        r14.A2K();
                                        throw th;
                                    }
                                }
                                r14.A8D();
                                r14.A2K();
                                AnonymousClass0FU r1 = this.A00;
                                synchronized (r1) {
                                    try {
                                        r1.A01 = false;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        throw th;
                                    }
                                }
                                try {
                                    readLock.unlock();
                                } catch (SQLiteException | IllegalStateException e) {
                                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
                                    return;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            throw th;
                        }
                    }
                } catch (Throwable th5) {
                    readLock.unlock();
                    throw th5;
                }
            }
            readLock.unlock();
        }
    }
}
