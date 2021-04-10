package X;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import com.facebook.assistant.common.config.tts.api.AssistantSettingsJobService;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.18  reason: invalid class name */
public abstract class AnonymousClass18 extends Service {
    public static final Object A05 = new Object();
    public static final HashMap A06 = new HashMap();
    public AnonymousClass17 A00;
    public boolean A01 = false;
    public AnonymousClass14 A02;
    public AnonymousClass15 A03;
    public final ArrayList A04;

    public static void A00(Context context, Intent intent) {
        ComponentName componentName = new ComponentName(context, AssistantSettingsJobService.class);
        synchronized (A05) {
            HashMap hashMap = A06;
            AnonymousClass17 r0 = (AnonymousClass17) hashMap.get(componentName);
            if (r0 == null) {
                if (Build.VERSION.SDK_INT >= 26) {
                    r0 = new C0636db(context, componentName);
                } else {
                    r0 = new dX(context, componentName);
                }
                hashMap.put(componentName, r0);
            }
            r0.A01();
            r0.A02(intent);
        }
    }

    public final void A01() {
        ArrayList arrayList = this.A04;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.A02 = null;
                if (arrayList.size() > 0) {
                    A02(false);
                } else if (!this.A01) {
                    this.A00.A00();
                }
            }
        }
    }

    public final void A02(boolean z) {
        if (this.A02 == null) {
            this.A02 = new AnonymousClass14(this);
            AnonymousClass17 r3 = this.A00;
            if (r3 != null && z && (r3 instanceof dX)) {
                dX dXVar = (dX) r3;
                synchronized (dXVar) {
                    if (!dXVar.A01) {
                        dXVar.A01 = true;
                        dXVar.A04.acquire(600000);
                        dXVar.A03.release();
                    }
                }
            }
            this.A02.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public final IBinder onBind(Intent intent) {
        AnonymousClass15 r0 = this.A03;
        if (r0 != null) {
            return r0.A1R();
        }
        return null;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        ArrayList arrayList = this.A04;
        if (arrayList == null) {
            return 2;
        }
        AnonymousClass17 r1 = this.A00;
        if (r1 instanceof dX) {
            dX dXVar = (dX) r1;
            synchronized (dXVar) {
                dXVar.A00 = false;
            }
        }
        synchronized (arrayList) {
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new C0633dY(this, intent, i2));
            A02(true);
        }
        return 3;
    }

    public AnonymousClass18() {
        if (Build.VERSION.SDK_INT < 26) {
            this.A04 = new ArrayList();
        }
    }

    public final void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.A03 = new job.JobServiceEngineC0635da(this);
            this.A00 = null;
            return;
        }
        this.A03 = null;
        ComponentName componentName = new ComponentName(this, getClass());
        HashMap hashMap = A06;
        AnonymousClass17 r0 = (AnonymousClass17) hashMap.get(componentName);
        if (r0 == null) {
            if (Build.VERSION.SDK_INT >= 26) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            r0 = new dX(this, componentName);
            hashMap.put(componentName, r0);
        }
        this.A00 = r0;
    }

    public final void onDestroy() {
        super.onDestroy();
        ArrayList arrayList = this.A04;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.A01 = true;
                this.A00.A00();
            }
        }
    }
}
