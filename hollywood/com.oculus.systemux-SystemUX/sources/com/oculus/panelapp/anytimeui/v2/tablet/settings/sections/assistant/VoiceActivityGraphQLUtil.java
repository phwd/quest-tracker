package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.quickpromotion.QuickPromotionGraphQL;
import com.oculus.graphql.model.GraphQLPageInfo;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HardwareIds"})
public final class VoiceActivityGraphQLUtil {
    private static final String ACTIVITY_LOG_ID_MUTATION_ARGUMENT = "activity_log_id";
    private static final String CURSOR_ARGUMENT = "cursor";
    private static final String DEVICE_ID_ARGUMENT = "deviceId";
    private static final String DEVICE_ID_MUTATION_ARGUMENT = "device_unique_id";
    private static final String DEVICE_SERIAL_NUMBER_ARGUMENT = "deviceSerialNumber";
    private static final String DEVICE_SERIAL_NUMBER_MUTATION_ARGUMENT = "device_serial_number";
    private static final String TAG = LoggingUtil.tag(VoiceActivityGraphQLUtil.class);
    private static String VOICE_ACTIVITY_DELETE_ALL_MUTATION = "mutation VoiceActivityDeleteAllMutation(  $input: TAssistantActivityLogDeleteInput!) @fb_owner(oncall: \"assistant_privacy_integrity\") {  assistant_activity_log_queue_delete_all_for_oculus(data: $input)}";
    private static String VOICE_ACTIVITY_DELETE_BY_ID_MUTATION = "mutation VoiceActivityDeleteByIdMutation(  $input: TAssistantActivityLogDeleteInput!) @fb_owner(oncall: \"assistant_privacy_integrity\") {  assistant_activity_log_delete_item_for_oculus(data: $input)}";
    private static String VOICE_ACTIVITY_HISTORY_QUERY = "query SettingsVoiceActivitySectionQuery($deviceSerialNumber: ID,$deviceId: ID,$cursor: ID,) @fb_owner(oncall: \"assistant_privacy_integrity\") {histories: assistant_activity_log {...SettingsVoiceActivityView_histories}}fragment SettingsVoiceActivityView_histories on AssistantActivityLog {items(last: 5, device_serial_number: $deviceSerialNumber, device_unique_id: $deviceId, before: $cursor) {edges {node {...SettingsVoiceActivityItem_item}}page_info {has_previous_page,start_cursor,}} }fragment SettingsVoiceActivityItem_item on AssistantActivityLogItem {log_id, timestamp, utterance, utterance_url,  is_false_wake,}";

    public interface OnDeleteAllResult {
        void onDeleted();
    }

    public interface OnDeleteByIdResult {
        void onDeleted(String str);
    }

    public interface OnVoiceActivityResult {
        void onResult(List<AssistantActivityLogItem> list, GraphQLPageInfo graphQLPageInfo);
    }

    public static void queryVoiceActivity(Context context, OkHttpClient okHttpClient, String str, GraphQLPageInfo graphQLPageInfo, final OnVoiceActivityResult onVoiceActivityResult, final SettingsLogger settingsLogger) {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DEVICE_SERIAL_NUMBER_ARGUMENT, Build.SERIAL);
            jSONObject.put(DEVICE_ID_ARGUMENT, Settings.Secure.getString(context.getContentResolver(), "android_id"));
            if (graphQLPageInfo.hasPreviousPage()) {
                jSONObject.put("cursor", graphQLPageInfo.getStartCursor());
            }
            GraphQLUtil.queryDoc(okHttpClient, VOICE_ACTIVITY_HISTORY_QUERY, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.AnonymousClass1 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    Log.e(VoiceActivityGraphQLUtil.TAG, str);
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.LOAD_MORE, System.currentTimeMillis() - currentTimeMillis);
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data").getJSONObject("histories").getJSONObject(AssistantDialogContract.MultiselectionDialog.Section.ITEMS);
                        JSONArray jSONArray = jSONObject2.getJSONArray("edges");
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            AssistantActivityLogItem fromJSON = AssistantActivityLogItem.fromJSON(jSONArray.getJSONObject(i).getJSONObject("node"));
                            if (fromJSON != null) {
                                arrayList.add(fromJSON);
                            }
                        }
                        onVoiceActivityResult.onResult(arrayList, VoiceActivityGraphQLUtil.makePageInfoFromJSON(jSONObject2.getJSONObject("page_info")));
                    } catch (JSONException e) {
                        Log.e(VoiceActivityGraphQLUtil.TAG, "Failed to parse voice history result", e);
                    }
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Failed to build arguments", e);
        }
    }

    public static void deleteAllVoiceActivity(Context context, OkHttpClient okHttpClient, String str, final OnDeleteAllResult onDeleteAllResult, final SettingsLogger settingsLogger) {
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DEVICE_SERIAL_NUMBER_MUTATION_ARGUMENT, Build.SERIAL);
            jSONObject2.put(DEVICE_ID_MUTATION_ARGUMENT, Settings.Secure.getString(context.getContentResolver(), "android_id"));
            jSONObject.put(QuickPromotionGraphQL.ARGUMENT_MUTATION_INPUT, jSONObject2);
            GraphQLUtil.queryDoc(okHttpClient, VOICE_ACTIVITY_DELETE_ALL_MUTATION, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.AnonymousClass2 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = VoiceActivityGraphQLUtil.TAG;
                    Log.e(str2, "Delete failure " + str);
                    settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_ALL_REQUEST_ERROR, System.currentTimeMillis() - currentTimeMillis);
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_ALL_REQUEST_SUCCESS, System.currentTimeMillis() - currentTimeMillis);
                    onDeleteAllResult.onDeleted();
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Failed to build arguments", e);
            settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_ALL_REQUEST_ERROR, System.currentTimeMillis() - currentTimeMillis);
        }
    }

    public static void deleteVoiceActivityById(Context context, OkHttpClient okHttpClient, String str, final String str2, final OnDeleteByIdResult onDeleteByIdResult, final SettingsLogger settingsLogger) {
        final long currentTimeMillis = System.currentTimeMillis();
        settingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_HISTORY_REQUEST);
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DEVICE_SERIAL_NUMBER_MUTATION_ARGUMENT, Build.SERIAL);
            jSONObject2.put(DEVICE_ID_MUTATION_ARGUMENT, Settings.Secure.getString(context.getContentResolver(), "android_id"));
            jSONObject2.put(ACTIVITY_LOG_ID_MUTATION_ARGUMENT, str2);
            jSONObject.put(QuickPromotionGraphQL.ARGUMENT_MUTATION_INPUT, jSONObject2);
            GraphQLUtil.queryDoc(okHttpClient, VOICE_ACTIVITY_DELETE_BY_ID_MUTATION, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.AnonymousClass3 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = VoiceActivityGraphQLUtil.TAG;
                    Log.e(str2, "Delete failure " + str);
                    settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_HISTORY_REQUEST_ERROR, System.currentTimeMillis() - currentTimeMillis);
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_HISTORY_REQUEST_SUCCESS, System.currentTimeMillis() - currentTimeMillis);
                    onDeleteByIdResult.onDeleted(str2);
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Failed to build arguments", e);
            settingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_HISTORY_REQUEST_ERROR, System.currentTimeMillis() - currentTimeMillis);
        }
    }

    /* access modifiers changed from: private */
    public static GraphQLPageInfo makePageInfoFromJSON(JSONObject jSONObject) {
        return new GraphQLPageInfo.Builder().setStartCursor(jSONObject.optString("start_cursor", null)).setHasPreviousPage(jSONObject.optBoolean("has_previous_page", false)).build();
    }
}
