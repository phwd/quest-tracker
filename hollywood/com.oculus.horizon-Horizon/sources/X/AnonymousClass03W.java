package X;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.03W  reason: invalid class name */
public abstract class AnonymousClass03W extends Service {
    public static final boolean DEBUG = false;
    public static final String TAG = "JobIntentService";
    public static final HashMap<ComponentName, AnonymousClass03V> sClassWorkEnqueuer = new HashMap<>();
    public static final Object sLock = new Object();
    public final ArrayList<AnonymousClass0sm> mCompatQueue;
    public AnonymousClass03V mCompatWorkEnqueuer;
    public AnonymousClass03S mCurProcessor;
    public boolean mDestroyed = false;
    public boolean mInterruptIfStopped = false;
    public AnonymousClass03T mJobImpl;
    public boolean mStopped = false;

    public abstract void onHandleWork(@NonNull Intent intent);

    public boolean onStopCurrentWork() {
        return true;
    }

    public static AnonymousClass03V getWorkEnqueuer(Context context, ComponentName componentName, boolean z, int i) {
        AnonymousClass03V r1 = sClassWorkEnqueuer.get(componentName);
        if (r1 == null) {
            if (Build.VERSION.SDK_INT < 26) {
                r1 = new C07450sn(context, componentName);
            } else if (z) {
                r1 = new AnonymousClass0sj(context, componentName, i);
            } else {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            sClassWorkEnqueuer.put(componentName, r1);
        }
        return r1;
    }

    public AnonymousClass03U dequeueWork() {
        AnonymousClass0sm r0;
        AnonymousClass03T r02 = this.mJobImpl;
        if (r02 != null) {
            return r02.A2D();
        }
        synchronized (this.mCompatQueue) {
            ArrayList<AnonymousClass0sm> arrayList = this.mCompatQueue;
            if (arrayList.size() > 0) {
                r0 = arrayList.remove(0);
            } else {
                r0 = null;
            }
        }
        return r0;
    }

    public boolean doStopCurrentWork() {
        AnonymousClass03S r1 = this.mCurProcessor;
        if (r1 != null) {
            r1.cancel(this.mInterruptIfStopped);
        }
        this.mStopped = true;
        return true;
    }

    public void ensureProcessorRunningLocked(boolean z) {
        if (this.mCurProcessor == null) {
            this.mCurProcessor = new AnonymousClass03S(this);
            AnonymousClass03V r0 = this.mCompatWorkEnqueuer;
            if (r0 != null && z) {
                r0.A01();
            }
            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public IBinder onBind(@NonNull Intent intent) {
        AnonymousClass03T r0 = this.mJobImpl;
        if (r0 != null) {
            return r0.A1l();
        }
        return null;
    }

    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (this.mCompatQueue == null) {
            return 2;
        }
        this.mCompatWorkEnqueuer.A02();
        synchronized (this.mCompatQueue) {
            ArrayList<AnonymousClass0sm> arrayList = this.mCompatQueue;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new AnonymousClass0sm(this, intent, i2));
            ensureProcessorRunningLocked(true);
        }
        return 3;
    }

    public void processorFinished() {
        ArrayList<AnonymousClass0sm> arrayList = this.mCompatQueue;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.mCurProcessor = null;
                ArrayList<AnonymousClass0sm> arrayList2 = this.mCompatQueue;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    ensureProcessorRunningLocked(false);
                } else if (!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.A00();
                }
            }
        }
    }

    public AnonymousClass03W() {
        ArrayList<AnonymousClass0sm> arrayList;
        if (Build.VERSION.SDK_INT >= 26) {
            arrayList = null;
        } else {
            arrayList = new ArrayList<>();
        }
        this.mCompatQueue = arrayList;
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mJobImpl = new AnonymousClass0sk(this);
            this.mCompatWorkEnqueuer = null;
            return;
        }
        this.mJobImpl = null;
        this.mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0);
    }

    public void onDestroy() {
        super.onDestroy();
        ArrayList<AnonymousClass0sm> arrayList = this.mCompatQueue;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.mDestroyed = true;
                this.mCompatWorkEnqueuer.A00();
            }
        }
    }

    public void setInterruptIfStopped(boolean z) {
        this.mInterruptIfStopped = z;
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName componentName, int i, @NonNull Intent intent) {
        if (intent != null) {
            synchronized (sLock) {
                AnonymousClass03V workEnqueuer = getWorkEnqueuer(context, componentName, true, i);
                workEnqueuer.A03(i);
                workEnqueuer.A04(intent);
            }
            return;
        }
        throw new IllegalArgumentException("work must not be null");
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class<?> cls, int i, @NonNull Intent intent) {
        enqueueWork(context, new ComponentName(context, cls), i, intent);
    }
}
