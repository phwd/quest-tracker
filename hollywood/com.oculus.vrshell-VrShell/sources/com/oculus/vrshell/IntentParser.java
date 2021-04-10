package com.oculus.vrshell;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

final class IntentParser {
    private static final String TAG = LoggingUtil.tag(IntentParser.class);
    private static final HashSet<String> protectedKeys = new HashSet<>(Arrays.asList("intent_data"));
    private static final HashSet<String> restrictedKeys = new HashSet<>(Arrays.asList("intent_action", "intent_type"));

    IntentParser() {
    }

    static String[] ToEnvironment(Intent intent) {
        return ToEnvironment(intent, new HashMap());
    }

    static String[] ToEnvironment(Intent intent, Map<String, String> map) {
        Set<String> keySet;
        String str;
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            map.put("intent_action", action);
        }
        String type = intent.getType();
        if (!TextUtils.isEmpty(type)) {
            map.put("intent_type", type);
        }
        Uri data = intent.getData();
        if (data != null) {
            if ("apk".equals(data.getScheme())) {
                str = data.getAuthority();
            } else {
                str = data.toString();
            }
            if (!TextUtils.isEmpty(str)) {
                map.put("intent_data", str);
            }
        }
        Bundle extras = intent.getExtras();
        int i = 0;
        if (!(extras == null || (keySet = extras.keySet()) == null)) {
            for (String str2 : keySet) {
                if (restrictedKeys.contains(str2) || (protectedKeys.contains(str2) && map.containsKey(str2))) {
                    Log.w(TAG, String.format("Got intent with extra attempting to overwrite protected field \"%s\", skipping field", str2));
                } else {
                    Object obj = extras.get(str2);
                    if (obj == null || TextUtils.isEmpty(String.valueOf(obj))) {
                        Log.w(TAG, String.format("Got intent with null or empty value for key \"%s\"", String.valueOf(str2)));
                    } else {
                        map.put(str2, String.valueOf(obj));
                    }
                }
            }
        }
        upgradeSystemUtilitiesRoutes(map);
        convertSystemFailureIntentToRoute(map);
        ensureValidComponentNames(map);
        String[] strArr = new String[(map.size() * 2)];
        for (Map.Entry<String, String> entry : map.entrySet()) {
            int i2 = i + 1;
            strArr[i] = entry.getKey();
            i = i2 + 1;
            strArr[i2] = entry.getValue();
        }
        return strArr;
    }

    static void ensureValidComponentNames(Map<String, String> map) {
        if (map.containsKey(ShellApplication.INTENT_KEY_FROM_PKG)) {
            String str = map.get(ShellApplication.INTENT_KEY_FROM_PKG);
            if (TextUtils.isEmpty(str) || !str.matches("^[0-9A-Za-z_\\.]+$")) {
                Log.w(TAG, String.format("Dropping invalid value %s for intent_pkg", str));
                map.remove(ShellApplication.INTENT_KEY_FROM_PKG);
            }
        }
    }

    static void convertSystemFailureIntentToRoute(Map<String, String> map) {
        if (SystemUXRoute.SYSTEM_FAILURE_MESSAGE.path().equals(map.get("intent_data"))) {
            Uri.Builder buildUpon = Uri.parse(SystemUXRoute.SYSTEM_FAILURE_MESSAGE.path()).buildUpon();
            String str = map.get(ShellApplication.INTENT_KEY_FROM_PKG);
            if (!TextUtils.isEmpty(str)) {
                buildUpon.appendQueryParameter("return_component", str);
            }
            String str2 = map.get("intent_cmd");
            if (!TextUtils.isEmpty(str2)) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    buildUpon.appendQueryParameter("Command", jSONObject.optString("Command"));
                    buildUpon.appendQueryParameter("vrIntegrationVersion", jSONObject.optString("vrIntegrationVersion"));
                    JSONObject optJSONObject = jSONObject.optJSONObject("extraData");
                    if (optJSONObject != null) {
                        buildUpon.appendQueryParameter("Message", optJSONObject.optString("Message"));
                        buildUpon.appendQueryParameter("Reason", optJSONObject.optString("Reason"));
                        buildUpon.appendQueryParameter("RequiresUpdate", optJSONObject.optString("RequiresUpdate"));
                    }
                } catch (JSONException e) {
                    Log.w(TAG, "Failed to parse intent command message for system failure dialog.", e);
                }
            }
            map.put("intent_data", buildUpon.build().toString());
        }
    }

    static void upgradeSystemUtilitiesRoutes(Map<String, String> map) {
        String str = map.get("uri");
        String str2 = map.get("intent_data");
        if (str2 != null && str != null) {
            String str3 = map.get(ShellApplication.INTENT_KEY_FROM_PKG);
            if (str2.equals("com.oculus.vrshell.home/.SystemUtilitiesService") || str2.equals("com.oculus.systemutilities/.SettingsService") || str2.equals("com.oculus.vrshell.home/com.oculus.vrshell.home.SystemUtilitiesService") || str2.equals("com.oculus.systemutilities/com.oculus.systemutilities.SettingsService")) {
                String[] split = str.split("/");
                String str4 = split.length > 2 ? split[2] : "";
                if (str4.equals("globalMenu")) {
                    String substring = str.substring(("/system_utilities/" + str4 + "/").length());
                    map.put("intent_data", "systemux://settings");
                    if (!str3.isEmpty()) {
                        map.put(ShellApplication.INTENT_KEY_FROM_PKG, substring);
                    }
                    map.remove("uri");
                } else if (str4.equals("bugReport")) {
                    String substring2 = str.substring(28);
                    int indexOf = substring2.indexOf(47);
                    String substring3 = substring2.substring(0, indexOf);
                    String substring4 = substring2.substring(indexOf + 1);
                    map.put("intent_data", "systemux://bug_report");
                    map.put(ShellApplication.INTENT_KEY_FROM_PKG, substring3);
                    map.put("uri", "com.oculus.unknow/" + substring4);
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("user_block", "user_block");
                    hashMap.put("user_unblock", "user_unblock");
                    hashMap.put("user_report", "user_report");
                    hashMap.put("profile", "user_profile");
                    hashMap.put("inviteFriends", "invite_friends");
                    hashMap.put("share_modal", "share_media");
                    hashMap.put("user_friend_request", "user_friend_request");
                    if (hashMap.containsKey(str4)) {
                        String substring5 = str.substring(("/system_utilities/" + str4 + "/").length());
                        String substring6 = substring5.substring(0, substring5.indexOf(47));
                        map.put("intent_data", SystemUXRoute.PREFIX + ((String) hashMap.get(str4)));
                        map.put(ShellApplication.INTENT_KEY_FROM_PKG, substring6);
                    } else {
                        String str5 = TAG;
                        Log.w(str5, "Unknown sysutils uri: " + str);
                    }
                }
            } else if (str2.startsWith("com.oculus.vrshell.home")) {
                if (str.startsWith("/iap/")) {
                    map.put("intent_data", "systemux://launch_iap");
                    String str6 = map.get("component_name");
                    if (str6 != null && str6.length() > 15) {
                        String substring7 = str6.substring(14, str6.length() - 15);
                        int indexOf2 = substring7.indexOf(47);
                        if (indexOf2 > 0) {
                            map.put(ShellApplication.INTENT_KEY_FROM_PKG, substring7.substring(0, indexOf2));
                        } else {
                            map.put(ShellApplication.INTENT_KEY_FROM_PKG, substring7);
                        }
                    }
                } else if (str.equals("/returnToRoot")) {
                    map.put("intent_data", "systemux://home");
                    map.remove(ShellApplication.INTENT_KEY_FROM_PKG);
                } else if (str.startsWith("/item/")) {
                    map.put("intent_data", "systemux://store");
                } else if (str.startsWith("/fb-connect/")) {
                    map.put("intent_data", "systemux://fb-connect");
                } else if (str.startsWith("/social")) {
                    map.put("intent_data", "systemux://social");
                } else {
                    return;
                }
            } else if (str2.startsWith("com.oculus.browser")) {
                map.put("intent_data", "systemux://browser");
            } else {
                return;
            }
            String str7 = TAG;
            Log.d(str7, "Intent update " + str + " from (data,pkg)(" + str2 + "," + str3 + ") to (" + map.get("intent_data") + "," + map.get(ShellApplication.INTENT_KEY_FROM_PKG) + ")");
        }
    }
}
