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
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class AppCrashCollector implements ICollector {
    public static final String APP_CRASH_DIALOG_INTENT = "com.oculus.intent.action.app_crash_dialog";
    public static final String EVENT_NAME_APP_CRASHED = "oculus_mobile_app_crash_dialog";
    public static final String PROCESS_NAME = "processName";
    public static final String TAG = "AppCrashCollector";
    public static volatile AppCrashCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_AppCrashCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final Fa mBroadcastReceiver = new Fa(APP_CRASH_DIALOG_INTENT, new AbstractC0385gk() {
        /* class com.oculus.unifiedtelemetry.collectors.AppCrashCollector.AnonymousClass1 */

        @Override // X.AbstractC0385gk
        public final void A3q(Context context, Intent intent, AbstractC0386gl glVar) {
            if (AppCrashCollector.APP_CRASH_DIALOG_INTENT.equals(intent.getAction())) {
                AppCrashCollector appCrashCollector = AppCrashCollector.this;
                String stringExtra = intent.getStringExtra(AppCrashCollector.PROCESS_NAME);
                Event event = new Event(AppCrashCollector.EVENT_NAME_APP_CRASHED);
                event.A06("process_name", stringExtra);
                event.A03("realtime_ms", SystemClock.elapsedRealtime());
                event.A03("uptime_ms", SystemClock.uptimeMillis());
                ((LoggingHandler) AbstractC0096Hu.A03(0, 114, appCrashCollector._UL_mInjectionContext)).A07(event.mName, event.mContent);
            }
        }
    });

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(APP_CRASH_DIALOG_INTENT);
        ((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext)).registerReceiver(this.mBroadcastReceiver, intentFilter);
    }

    @Inject
    public AppCrashCollector(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(2, xu);
    }
}
