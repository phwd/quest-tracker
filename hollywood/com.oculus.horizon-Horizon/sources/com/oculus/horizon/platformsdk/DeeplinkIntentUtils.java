package com.oculus.horizon.platformsdk;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass1Di;
import X.AnonymousClass1MZ;
import X.C02600ao;
import X.C02710bE;
import android.content.Context;
import android.content.Intent;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_util_launch_PackageLauncherUtils_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DeeplinkIntentUtils {
    public static final String HOME_LINK_PDP = "/item/";
    public static final String INTENT_KEY_COMMAND = "intent_cmd";
    public static final String INTENT_KEY_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String INTENT_KEY_LAUNCH_SOURCE = "launch_source";
    public static final String INTENT_KEY_LAUNCH_SOURCE_OTHER_APP = "OTHER_APP";
    public static final String INTENT_KEY_PACKAGE = "intent_pkg";
    public static final String INTENT_KEY_SOCIAL_LAUNCH = "ovr_social_launch";
    public static final String INTENT_KEY_TYPE = "type";
    public static final String INTENT_KEY_TYPE_DEEPLINK = "DEEPLINK";
    public static final String PARAM_APP_ID = "target_app_id";
    public static final String PARAM_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String PARAM_DEEPLINK_OPTIONS = "deeplink_options";
    public static final String PARAM_INTENT_DATA = "intent_data";
    public static final String PARAM_URI = "uri";
    public static final String TAG = "DeeplinkIntentUtils";
    public static final String URL_QUERY_INTENT_CMD = "intent_cmd=";
    public static volatile DeeplinkIntentUtils _UL__ULSEP_com_oculus_horizon_platformsdk_DeeplinkIntentUtils_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;

    @Nullable
    public final Intent A01(String str, String str2) {
        String str3;
        if (str2 != null) {
            try {
                if (!str2.isEmpty()) {
                    JSONObject jSONObject = new JSONObject(str2);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("ovr_social_launch", jSONObject);
                    str3 = jSONObject2.toString();
                    return A02(str, str3);
                }
            } catch (JSONException e) {
                AnonymousClass0NO.A0B(TAG, "Failed to parse arguments", e);
                return null;
            }
        }
        str3 = A00(null);
        return A02(str, str3);
    }

    public static String A00(@Nullable String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", INTENT_KEY_TYPE_DEEPLINK);
        jSONObject.put(INTENT_KEY_LAUNCH_SOURCE, INTENT_KEY_LAUNCH_SOURCE_OTHER_APP);
        if (str == null) {
            str = "";
        }
        jSONObject.put("deeplink_message", str);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("ovr_social_launch", jSONObject);
        return jSONObject2.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007b, code lost:
        if (com.oculus.util.vr.VRUtils.METADATA_VR_MODE_DUAL.equals(r1) == false) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0092, code lost:
        if (r6 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002a, code lost:
        if (r1.getInt(com.oculus.horizon.util.launch.PackageLauncherUtils.SHELL_META_LAUNCHER_CAPABILITY, 0) <= 0) goto L_0x002c;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent A02(java.lang.String r8, java.lang.String r9) {
        /*
        // Method dump skipped, instructions count: 238
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.platformsdk.DeeplinkIntentUtils.A02(java.lang.String, java.lang.String):android.content.Intent");
    }

    @Inject
    public DeeplinkIntentUtils(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }

    public final boolean A03(Intent intent, Context context) {
        AnonymousClass1Di r2;
        intent.getPackage();
        if (C02710bE.A00.contains(intent.getPackage())) {
            return AnonymousClass1MZ.A00().A02(intent, context);
        }
        C02600ao A00 = C02600ao.A00();
        synchronized (A00) {
            r2 = A00.A01;
            if (r2 == null) {
                r2 = new AnonymousClass1Di(C02600ao.A04(A00), A00.A09);
                A00.A01 = r2;
            }
        }
        return r2.A02(intent, context);
    }
}
