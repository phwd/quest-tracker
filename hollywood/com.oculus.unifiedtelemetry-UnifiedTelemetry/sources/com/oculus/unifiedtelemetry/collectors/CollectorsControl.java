package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0385gk;
import X.AbstractC0386gl;
import X.Fa;
import X.QC;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import java.util.Set;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_unifiedtelemetry_collectors_ICollector_ULGT__ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_unifiedtelemetry_collectors_ICollectorWithScreenEvents_ULGT__ULSEP_BINDING_ID"})
@ApplicationScoped
public class CollectorsControl implements AbstractC0385gk {
    public static final int NB_TICKS_PER_FLUSH = 30;
    public static final int SCHEDULER_TICK_PERIOD_MSEC = 2000;
    public static final String TAG = "CollectorsControl";
    public static volatile CollectorsControl _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_CollectorsControl_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final Handler mHandler = new Handler();
    public boolean mStarted = false;
    public int mTickCounter = 0;

    /* renamed from: com.oculus.unifiedtelemetry.collectors.CollectorsControl$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public final void run() {
            CollectorsControl collectorsControl = CollectorsControl.this;
            if (collectorsControl.mTickCounter == 30) {
                for (ICollector iCollector : (Set) AbstractC0096Hu.A03(1, 93, collectorsControl._UL_mInjectionContext)) {
                    iCollector.A3k();
                }
                for (ICollector iCollector2 : (Set) AbstractC0096Hu.A03(2, 13, collectorsControl._UL_mInjectionContext)) {
                    iCollector2.A3k();
                }
            } else {
                for (ICollector iCollector3 : (Set) AbstractC0096Hu.A03(1, 93, collectorsControl._UL_mInjectionContext)) {
                    iCollector3.A44();
                }
                for (ICollector iCollector4 : (Set) AbstractC0096Hu.A03(2, 13, collectorsControl._UL_mInjectionContext)) {
                    iCollector4.A44();
                }
            }
            collectorsControl.mTickCounter = (collectorsControl.mTickCounter + 1) % 31;
            collectorsControl.mHandler.postDelayed(new AnonymousClass1(), 2000);
        }
    }

    @Inject
    public CollectorsControl(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(3, xu);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("android.intent.action.SCREEN_ON", this);
        builder.put("android.intent.action.SCREEN_OFF", this);
        ((Context) AbstractC0096Hu.A03(0, 3, this._UL_mInjectionContext)).registerReceiver(new Fa(builder.build().entrySet().iterator()), intentFilter);
    }

    @Override // X.AbstractC0385gk
    public final void A3q(Context context, Intent intent, AbstractC0386gl glVar) {
        boolean z;
        String action = intent.getAction();
        if (action != null && this.mStarted) {
            int hashCode = action.hashCode();
            if (hashCode != -2128145023) {
                if (hashCode == -1454123155 && action.equals("android.intent.action.SCREEN_ON")) {
                    z = false;
                } else {
                    return;
                }
            } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                z = true;
            } else {
                return;
            }
            if (!z) {
                for (ICollectorWithScreenEvents iCollectorWithScreenEvents : (Set) AbstractC0096Hu.A03(2, 13, this._UL_mInjectionContext)) {
                    iCollectorWithScreenEvents.A3v();
                }
                this.mHandler.postDelayed(new AnonymousClass1(), 2000);
            } else if (z) {
                for (ICollectorWithScreenEvents iCollectorWithScreenEvents2 : (Set) AbstractC0096Hu.A03(2, 13, this._UL_mInjectionContext)) {
                    iCollectorWithScreenEvents2.A3u();
                    iCollectorWithScreenEvents2.A3k();
                }
                this.mHandler.removeCallbacksAndMessages(null);
            }
        }
    }
}
