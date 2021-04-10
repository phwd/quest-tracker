package com.oculus.modules;

import X.AnonymousClass0MD;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public class OVRPlatformGenericDeeplinkModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "OVRPlatformGenericDeeplinkModule";
    public static final String TAG = "OVRPlatformGenericDeeplinkModule";
    public Context mContext = null;

    public void onDeeplinkResult(String str, String str2, String str3, String str4) {
        if (str2 != null) {
            try {
                if (!str2.isEmpty()) {
                    returnResultHelper(str, str2, str3, true, str4);
                }
            } catch (Exception e) {
                AnonymousClass0MD.A0E("OVRPlatformGenericDeeplinkModule", e, "onDeeplinkResult failed: resultJson[%s]", str4);
            }
        }
    }

    public void shutdownModule() {
    }

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("onDeeplinkResult", "ssss"));
        return arrayList;
    }

    private void returnResultHelper(String str, String str2, String str3, boolean z, String str4) {
        Intent intent = new Intent("com.oculus.generic_deeplink_result");
        intent.setPackage(str);
        try {
            intent.putExtra(AbuseReportModule.KEY_PLATFORM_MESSGAE_TYPE, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            AnonymousClass0MD.A0E("OVRPlatformGenericDeeplinkModule", e, "Cannot parse platformMessageType[%s]", str2);
        }
        try {
            intent.putExtra(AbuseReportModule.KEY_PLATFORM_REQUEST_ID, Long.parseLong(str3));
        } catch (NumberFormatException e2) {
            AnonymousClass0MD.A0E("OVRPlatformGenericDeeplinkModule", e2, "Cannot parse platformRequestId[%s]", str3);
        }
        intent.putExtra("is_successful", z);
        intent.putExtra("result_json", str4);
        this.mContext.sendBroadcast(intent);
    }

    public OVRPlatformGenericDeeplinkModule(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return "OVRPlatformGenericDeeplinkModule";
    }
}
