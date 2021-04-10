package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.facebook.debug.log.BLog;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public class OVRPlatformGenericDeeplinkModule extends RCTBaseJavaModule {
    private static String MODULE_NAME = OVRPlatformGenericDeeplinkModule.class.getSimpleName();
    private static final String TAG = MODULE_NAME;
    private Context mContext = null;

    public OVRPlatformGenericDeeplinkModule(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void onDeeplinkResult(String fromPkg, String platformMessageType, String platformRequestID, String resultJson) {
        if (platformMessageType != null) {
            try {
                if (!platformMessageType.isEmpty()) {
                    returnResultHelper(fromPkg, platformMessageType, platformRequestID, true, resultJson);
                }
            } catch (Exception e) {
                BLog.e(TAG, e, "onDeeplinkResult failed: resultJson[%s]", resultJson);
            }
        }
    }

    private void returnResultHelper(String fromPkg, String platformMessageType, String platformRequestID, boolean success, String resultJson) {
        BLog.i(TAG, "Returning generic deeplink result success=[%s] resultJson=[%s] fromPkg=[%s] platformMessageType=[%s] platformRequestID=[%s]", Boolean.toString(success), resultJson, fromPkg, platformMessageType, platformRequestID);
        Intent intent = new Intent("com.oculus.generic_deeplink_result");
        intent.setPackage(fromPkg);
        try {
            intent.putExtra("platform_message_type", Integer.parseInt(platformMessageType));
        } catch (NumberFormatException e) {
            BLog.e(TAG, e, "Cannot parse platformMessageType[%s]", platformMessageType);
        }
        try {
            intent.putExtra("platform_request_id", Long.parseLong(platformRequestID));
        } catch (NumberFormatException e2) {
            BLog.e(TAG, e2, "Cannot parse platformRequestId[%s]", platformRequestID);
        }
        intent.putExtra("is_successful", success);
        intent.putExtra("result_json", resultJson);
        this.mContext.sendBroadcast(intent);
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("onDeeplinkResult", "ssss"));
        return list;
    }

    public void shutdownModule() {
    }
}
