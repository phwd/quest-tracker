package X;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.room.InvalidationTracker;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.0FT  reason: invalid class name */
public class AnonymousClass0FT implements Runnable {
    public static final String __redex_internal_original_name = "androidx.room.InvalidationTracker$1";
    public final /* synthetic */ AnonymousClass0FX A00;

    public AnonymousClass0FT(AnonymousClass0FX r1) {
        this.A00 = r1;
    }

    /* JADX INFO: finally extract failed */
    private Set<Integer> A00() {
        HashSet hashSet = new HashSet();
        AnonymousClass0FX r3 = this.A00;
        Cursor A002 = r3.A05.A00(new C03370cB("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
        while (A002.moveToNext()) {
            try {
                hashSet.add(Integer.valueOf(A002.getInt(0)));
            } catch (Throwable th) {
                A002.close();
                throw th;
            }
        }
        A002.close();
        if (!hashSet.isEmpty()) {
            r3.A09.A2S();
        }
        return hashSet;
    }

    public final void run() {
        AnonymousClass0FX r3 = this.A00;
        AnonymousClass0Fr r4 = r3.A05;
        ReentrantReadWriteLock.ReadLock readLock = r4.mCloseLock.readLock();
        Set<Integer> set = null;
        readLock.lock();
        AnonymousClass0GQ r0 = r4.mDatabase;
        if (r0 != null && r0.isOpen()) {
            if (!r3.A0A) {
                r4.mOpenHelper.A4y();
            }
            if (!r3.A0A) {
                Log.e("ROOM", "database is not initialized even though it is open");
            } else if (r3.A03.compareAndSet(true, false) && !r4.mOpenHelper.A4y().A58()) {
                if (r4.mWriteAheadLoggingEnabled) {
                    AnonymousClass0GQ A4y = r4.mOpenHelper.A4y();
                    A4y.A1H();
                    try {
                        set = A00();
                        A4y.A8D();
                        A4y.A2K();
                    } catch (SQLiteException | IllegalStateException e) {
                        try {
                            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
                        } catch (Throwable th) {
                            readLock.unlock();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        A4y.A2K();
                        throw th2;
                    }
                } else {
                    set = A00();
                }
                readLock.unlock();
                if (!(set == null || set.isEmpty())) {
                    C005905t<InvalidationTracker.Observer, AnonymousClass0FW> r6 = r3.A04;
                    synchronized (r6) {
                        Iterator<Map.Entry<InvalidationTracker.Observer, AnonymousClass0FW>> it = r6.iterator();
                        while (it.hasNext()) {
                            AnonymousClass0FW value = it.next().getValue();
                            int[] iArr = value.A01;
                            int length = iArr.length;
                            Set set2 = null;
                            for (int i = 0; i < length; i++) {
                                if (set.contains(Integer.valueOf(iArr[i]))) {
                                    if (length == 1) {
                                        set2 = value.A00;
                                    } else {
                                        if (set2 == null) {
                                            set2 = new HashSet(length);
                                        }
                                        set2.add(value.A02[i]);
                                    }
                                }
                            }
                            if (set2 != null) {
                                throw null;
                            }
                        }
                    }
                    return;
                }
                return;
            }
        }
        readLock.unlock();
    }
}
