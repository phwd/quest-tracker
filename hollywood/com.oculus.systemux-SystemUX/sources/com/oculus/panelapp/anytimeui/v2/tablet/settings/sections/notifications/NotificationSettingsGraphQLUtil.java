package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.quickpromotion.QuickPromotionGraphQL;
import com.oculus.graphql.oc.GraphQLUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationSettingsGraphQLUtil {
    private static final String DEVELOPER_APPLICATION_PREFERENCES_QUERY = "query {viewer {user {id, developer_applications: notification_user_controls_developer_applications {description, developer_application_preferences {...Preference}}}}}fragment Preference on OCNotificationUserControlsDeveloperApplicationPreference {application {id,display_name,icon_image(size: \"48x48\", encode: PNG, scale: 2) {uri}cover_square_image(size: \"48x48\", encode: PNG, scale: 2) {uri}}enabled}";
    private static final String DISABLE_DEVELOPER_APPLICATION_PREFERENCE_MUTATION = "mutation ($data: DisableNotificationUserControlsDeveloperApplicationPreferenceData!) {disable_notification_user_controls_developer_application_preference(data: $data) {viewer {user {notification_user_controls_developer_applications {developer_application_preferences {application {id}}}id}}}}";
    private static final String DISABLE_USER_CONTROL_CATEGORY_MUTATION = "mutation ($data: DisableNotificationUserControlsCategoryPreferenceData!) {disable_notification_user_controls_category_preference(data: $data) {viewer {user {id}}}}";
    private static final String ENABLE_DEVELOPER_APPLICATION_PREFERENCE_MUTATION = "mutation ($data: EnableNotificationUserControlsDeveloperApplicationPreferenceData!) {enable_notification_user_controls_developer_application_preference(data: $data) {viewer {user {notification_user_controls_developer_applications {developer_application_preferences {application {id}}}id}}}}";
    private static final String ENABLE_USER_CONTROL_CATEGORY_MUTATION = "mutation SettingsNotificationUserControlsEnableCategoryMutation($data: EnableNotificationUserControlsCategoryPreferenceData!) {enable_notification_user_controls_category_preference(data: $data) {viewer {user {id}}}}";
    private static final String OPT_OUT_PREFERENCES_MUTATION = "mutation ($input: NotificationPreferenceChangeData!) {notification_preference_change(data: $input) {viewer {user {notification_opt_out_preferences_list {key}}}}}";
    private static final String OPT_OUT_PREFERENCES_QUERY = "query {viewer {user {notification_opt_out_preferences_list {key,opt_out_value,name,description}}}}";
    private static final String TAG = LoggingUtil.tag(NotificationSettingsGraphQLUtil.class);
    private static final String USER_CONTROL_CATEGORY_QUERY = "query {viewer {user {id,categories: notification_user_controls_categories {...SettingsNotificationUserControlsCategory_category}}}}fragment SettingsNotificationUserControlsCategory_category on OCNotificationUserControlsCategory {category,title,description,preferences: notification_user_controls_category_preferences {...SettingsNotificationUserControlsCategoryPreference_preference}}fragment SettingsNotificationUserControlsCategoryPreference_preference on OCNotificationUserControlsCategoryPreferences {title,description,medium,enabled,}";

    public interface DeveloperApplicationPreferencesHandler {
        void onResult(String str, String str2, List<DeveloperApplicationPreference> list);
    }

    public interface OptOutPreferencesHandler {
        void onResult(List<NotificationOptOutPreference> list);
    }

    public interface QueryErrorHandler {
        void onError(String str);
    }

    public interface UserControlCategoriesHandler {
        void onResult(String str, List<NotificationUserControlCategory> list);
    }

    public static void queryOptOutPreferences(OkHttpClient okHttpClient, String str, final OptOutPreferencesHandler optOutPreferencesHandler, final QueryErrorHandler queryErrorHandler) {
        GraphQLUtil.queryDoc(okHttpClient, OPT_OUT_PREFERENCES_QUERY, new JSONObject(), str, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.AnonymousClass1 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                queryErrorHandler.onError(str);
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONObject("viewer").getJSONObject("user").getJSONArray("notification_opt_out_preferences_list");
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        NotificationOptOutPreference fromJSON = NotificationOptOutPreference.fromJSON(jSONArray.getJSONObject(i));
                        if (fromJSON != null) {
                            arrayList.add(fromJSON);
                        }
                    }
                    optOutPreferencesHandler.onResult(arrayList);
                } catch (JSONException e) {
                    queryErrorHandler.onError(e.toString());
                }
            }
        });
    }

    public static void setOptOutPreference(OkHttpClient okHttpClient, String str, final NotificationOptOutPreference notificationOptOutPreference) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("medium", "PUSH");
            jSONObject2.put("is_off", notificationOptOutPreference.getOptOutValue());
            jSONObject2.put("type", notificationOptOutPreference.getKey());
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject.put(QuickPromotionGraphQL.ARGUMENT_MUTATION_INPUT, jSONObject2);
            GraphQLUtil.queryDoc(okHttpClient, OPT_OUT_PREFERENCES_MUTATION, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.AnonymousClass2 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = NotificationSettingsGraphQLUtil.TAG;
                    Log.d(str2, "Failed to update opt out pref for: " + notificationOptOutPreference.getKey());
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    String str = NotificationSettingsGraphQLUtil.TAG;
                    Log.d(str, "Successfully updated opt out pref for: " + notificationOptOutPreference.getKey());
                }
            });
        } catch (JSONException e) {
            Log.d(TAG, "Error building opt out preference mutation", e);
        }
    }

    public static void queryUserControlCategories(OkHttpClient okHttpClient, String str, final UserControlCategoriesHandler userControlCategoriesHandler, final QueryErrorHandler queryErrorHandler) {
        GraphQLUtil.queryDoc(okHttpClient, USER_CONTROL_CATEGORY_QUERY, new JSONObject(), str, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.AnonymousClass3 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                queryErrorHandler.onError(str);
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                try {
                    userControlCategoriesHandler.onResult(NotificationSettingsGraphQLUtil.parseUserControlCategoriesJSONResultUserId(jSONObject), NotificationSettingsGraphQLUtil.parseUserControlCategoriesResult(jSONObject));
                } catch (JSONException e) {
                    queryErrorHandler.onError(e.toString());
                }
            }
        });
    }

    @VisibleForTesting
    public static List<NotificationUserControlCategory> parseUserControlCategoriesResult(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONObject("viewer").getJSONObject("user").getJSONArray("categories");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            NotificationUserControlCategory fromJSON = NotificationUserControlCategory.fromJSON(jSONArray.getJSONObject(i));
            if (fromJSON != null) {
                arrayList.add(fromJSON);
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    public static String parseUserControlCategoriesJSONResultUserId(JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONObject("data").getJSONObject("viewer").getJSONObject("user").getString("id");
    }

    public static void setUserControlCategoryEnabled(OkHttpClient okHttpClient, String str, String str2, final NotificationUserControlCategory notificationUserControlCategory, final NotificationUserControlCategoryPreference notificationUserControlCategoryPreference) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("medium", notificationUserControlCategoryPreference.getMedium());
            jSONObject2.put("category", notificationUserControlCategory.getCategory());
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject2.put("actor_id", str2);
            jSONObject.put("data", jSONObject2);
            GraphQLUtil.queryDoc(okHttpClient, notificationUserControlCategoryPreference.isEnabled() ? ENABLE_USER_CONTROL_CATEGORY_MUTATION : DISABLE_USER_CONTROL_CATEGORY_MUTATION, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.AnonymousClass4 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = NotificationSettingsGraphQLUtil.TAG;
                    Log.d(str2, "Failed to set category preference for " + notificationUserControlCategory.getCategory() + " : " + notificationUserControlCategoryPreference.getMedium());
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    String str = NotificationSettingsGraphQLUtil.TAG;
                    Log.d(str, "Successfully set category preference for " + notificationUserControlCategory.getCategory() + " : " + notificationUserControlCategoryPreference.getMedium());
                }
            });
        } catch (JSONException e) {
            Log.d(TAG, "Error building user category mutation", e);
        }
    }

    public static void queryDeveloperApplicationPreferences(OkHttpClient okHttpClient, String str, final DeveloperApplicationPreferencesHandler developerApplicationPreferencesHandler, final QueryErrorHandler queryErrorHandler) {
        GraphQLUtil.queryDoc(okHttpClient, DEVELOPER_APPLICATION_PREFERENCES_QUERY, new JSONObject(), str, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.AnonymousClass5 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                queryErrorHandler.onError(str);
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data").getJSONObject("viewer").getJSONObject("user");
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("developer_applications");
                    JSONArray jSONArray = jSONObject3.getJSONArray("developer_application_preferences");
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        DeveloperApplicationPreference fromJSON = DeveloperApplicationPreference.fromJSON(jSONArray.getJSONObject(i));
                        if (fromJSON != null) {
                            arrayList.add(fromJSON);
                        }
                    }
                    developerApplicationPreferencesHandler.onResult(jSONObject2.getString("id"), jSONObject3.getString(AssistantDialogContract.Dialog.DESCRIPTION), arrayList);
                } catch (JSONException e) {
                    queryErrorHandler.onError(e.toString());
                }
            }
        });
    }

    public static void setDeveloperApplicationPreferenceEnabled(OkHttpClient okHttpClient, String str, String str2, final DeveloperApplicationPreference developerApplicationPreference) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_id", developerApplicationPreference.getId());
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject2.put("actor_id", str2);
            jSONObject.put("data", jSONObject2);
            GraphQLUtil.queryDoc(okHttpClient, developerApplicationPreference.isEnabled() ? ENABLE_DEVELOPER_APPLICATION_PREFERENCE_MUTATION : DISABLE_DEVELOPER_APPLICATION_PREFERENCE_MUTATION, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.AnonymousClass6 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = NotificationSettingsGraphQLUtil.TAG;
                    Log.d(str2, "Failed to set app preference: " + developerApplicationPreference.getDisplayName());
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    String str = NotificationSettingsGraphQLUtil.TAG;
                    Log.d(str, "Successfully set app preference: " + developerApplicationPreference.getDisplayName());
                }
            });
        } catch (JSONException e) {
            Log.d(TAG, "Error building app preference mutation", e);
        }
    }
}
