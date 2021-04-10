package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0386gl;
import X.QB;
import X.QC;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.logging.analytics2.EventCache;
import com.oculus.multiuser.UserClassifier;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

@SuppressLint({"BadArgument-FbInjector#get-0"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class EventCacheFlushBroadcastReceiver extends OculusSystemSecureBroadcastReceiver implements QB {
    public static final String TAG = "EventCacheFlushBroadcastReceiver";
    public QC _UL_mInjectionContext;

    public EventCacheFlushBroadcastReceiver() {
        super(new String[0]);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void A08(Context context, Intent intent, AbstractC0386gl glVar) {
        String action;
        QC qc = new QC(2, AbstractC0096Hu.get(context));
        this._UL_mInjectionContext = qc;
        if (((UserClassifier) AbstractC0096Hu.A03(0, 22, qc)).A01() && (action = intent.getAction()) != null) {
            final EventCache eventCache = ((UserMonitor) AbstractC0096Hu.A03(1, 86, this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory.mEventCache;
            int hashCode = action.hashCode();
            if (hashCode != 505380757) {
                if (hashCode == 798292259 && action.equals("android.intent.action.BOOT_COMPLETED") && eventCache.A04()) {
                    OculusThreadExecutor.A00().execute(new Runnable() {
                        /* class com.oculus.unifiedtelemetry.unifiedlogging.EventCacheFlushBroadcastReceiver.AnonymousClass2 */

                        public final void run() {
                            eventCache.A02();
                        }
                    });
                }
            } else if (action.equals("android.intent.action.TIME_SET") && !eventCache.A04()) {
                synchronized (eventCache) {
                    eventCache.mTimeSet = true;
                    eventCache.mDeviceProtectedPrefs.edit().putBoolean(EventCache.TIME_SET, true).apply();
                }
                OculusThreadExecutor.A00().execute(new Runnable() {
                    /* class com.oculus.unifiedtelemetry.unifiedlogging.EventCacheFlushBroadcastReceiver.AnonymousClass1 */

                    public final void run() {
                        eventCache.A02();
                    }
                });
            }
        }
    }
}
