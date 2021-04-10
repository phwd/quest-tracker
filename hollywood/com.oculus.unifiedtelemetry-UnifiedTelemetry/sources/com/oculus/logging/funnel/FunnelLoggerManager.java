package com.oculus.logging.funnel;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.C0516sv;
import X.C0521tH;
import X.HandlerC0483rj;
import X.QC;
import X.rh;
import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.base.app.AppInfo;
import com.oculus.horizon.foreground.ApplicationForegroundListener;
import com.oculus.logging.EventFactory;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_oculus_directboot_ForDeviceProtectedStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID"})
@SuppressLint({"FieldInjectedContext"})
public class FunnelLoggerManager implements ApplicationForegroundListener {
    public QC _UL_mInjectionContext;
    public final rh mFunnelLogger;

    public static class AlwaysOnSampler {
    }

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public final void A3b() {
    }

    public static class OculusFunnelEventSender {
        public static final String TAG = "OculusFunnelEventSender";
        public final EventFactory mEventFactory;

        public OculusFunnelEventSender(EventFactory eventFactory) {
            this.mEventFactory = eventFactory;
        }
    }

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public final void A3a(long j) {
        rh rhVar = this.mFunnelLogger;
        synchronized (rhVar) {
            HandlerC0483rj rjVar = rhVar.A02;
            rjVar.sendMessage(rjVar.obtainMessage(6));
        }
    }

    @Inject
    public FunnelLoggerManager(AbstractC0247Xu xu, @Assisted EventFactory eventFactory) {
        QC qc = new QC(2, xu);
        this._UL_mInjectionContext = qc;
        this.mFunnelLogger = new rh(new OculusFunnelEventSender(eventFactory), new AlwaysOnSampler(), new C0521tH(), new C0516sv((Context) AbstractC0096Hu.A03(0, 99, qc), ((AppInfo) AbstractC0096Hu.A03(1, 111, qc)).appName, new Object() {
            /* class com.oculus.logging.funnel.FunnelLoggerManager.AnonymousClass1 */
        }));
    }
}
