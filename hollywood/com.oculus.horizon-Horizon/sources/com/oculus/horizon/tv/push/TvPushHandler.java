package com.oculus.horizon.tv.push;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.C02600ao;
import X.C02780bN;
import X.C02870bf;
import X.C02880bg;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.horizon.push.FbnsPushHandler;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class TvPushHandler implements FbnsPushHandler {
    public static final ImmutableSet<String> HANDLED_TYPES = new SingletonImmutableSet(TYPE_TV_MEDIA_DOWNLOAD);
    public static final String OMD_SERVICE = "com.oculus.mediadownloader.OmdService";
    public static final String TAG = "TvPushHandler";
    public static final String TV_MEDIA_DOWNLOAD_PAYLOAD_KEY = "json";
    public static final String TYPE_TV_MEDIA_DOWNLOAD = "tv_media_download";
    public static final C02870bf mOculusTvApp = C02880bg.A02(new HashSet(Collections.singleton(C02780bN.A0L)), new HashSet(Collections.singleton("com.oculus.tv")));
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public TvPushHandler(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    @Override // com.oculus.horizon.push.FbnsPushHandler
    public final Set<String> A3T() {
        return HANDLED_TYPES;
    }

    @Override // com.oculus.horizon.push.FbnsPushHandler
    public final void A6M(String str, JSONObject jSONObject) {
        if (str.hashCode() == 558331360 && str.equals(TYPE_TV_MEDIA_DOWNLOAD)) {
            try {
                Intent component = new Intent().setComponent(new ComponentName("com.oculus.tv", OMD_SERVICE));
                component.putExtra("json", jSONObject.toString());
                C02600ao.A00().A07(mOculusTvApp).A00(component, (Context) AnonymousClass0J2.A03(1, 294, this._UL_mInjectionContext));
            } catch (Exception e) {
                ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A97(TAG, "Unable to notify Oculus TV Downloader: ", e);
            }
        }
    }
}
