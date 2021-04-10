package X;

import android.database.Cursor;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.RoomDatabase;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.0Fr  reason: invalid class name */
public abstract class AnonymousClass0Fr {
    public static final String DB_IMPL_SUFFIX = "_Impl";
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    public boolean mAllowMainThreadQueries;
    public final Map<String, Object> mBackingFieldMap = new ConcurrentHashMap();
    @Nullable
    @Deprecated
    public List<RoomDatabase.Callback> mCallbacks;
    public final ReentrantReadWriteLock mCloseLock = new ReentrantReadWriteLock();
    @Deprecated
    public volatile AnonymousClass0GQ mDatabase;
    public final AnonymousClass0FX mInvalidationTracker = A01();
    public AnonymousClass0GV mOpenHelper;
    public Executor mQueryExecutor;
    public final ThreadLocal<Integer> mSuspendingTransactionId = new ThreadLocal<>();
    public Executor mTransactionExecutor;
    public boolean mWriteAheadLoggingEnabled;

    @NonNull
    public abstract AnonymousClass0FX A01();

    @NonNull
    public abstract AnonymousClass0GV A02(AnonymousClass0FB v);

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A03() {
        if (!this.mAllowMainThreadQueries && Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP})
    public final void A04() {
        if (!this.mOpenHelper.A4y().A58() && this.mSuspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    @Deprecated
    public final void A06() {
        this.mOpenHelper.A4y().A2K();
        if (!this.mOpenHelper.A4y().A58()) {
            AnonymousClass0FX r3 = this.mInvalidationTracker;
            if (r3.A03.compareAndSet(false, true)) {
                r3.A05.mQueryExecutor.execute(r3.A01);
            }
        }
    }

    @Deprecated
    public final void A07() {
        this.mOpenHelper.A4y().A8D();
    }

    @NonNull
    public final Cursor A00(@NonNull AnonymousClass0GX r2) {
        A03();
        A04();
        return this.mOpenHelper.A4y().A73(r2);
    }

    @Deprecated
    public final void A05() {
        A03();
        AnonymousClass0GQ A4y = this.mOpenHelper.A4y();
        this.mInvalidationTracker.A00(A4y);
        A4y.A1H();
    }
}
