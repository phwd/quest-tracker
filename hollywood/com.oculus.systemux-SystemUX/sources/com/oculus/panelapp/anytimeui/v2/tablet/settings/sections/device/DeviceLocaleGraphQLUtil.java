package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.device;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.quickpromotion.QuickPromotionGraphQL;
import com.oculus.devicelocale.DeviceLocale;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.provider.OculusContent;
import java.util.UUID;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public final class DeviceLocaleGraphQLUtil {
    private static final String LOCALE_EDIT_MUTATION = "mutation UserEditMutation($input: UserEditData!) {\n    user_edit(data: $input) {\n      user {\n        id\n        locale\n      }\n    }\n  }";
    private static String TAG = LoggingUtil.tag(DeviceLocaleGraphQLUtil.class);

    public static void setLocale(OkHttpClient okHttpClient, String str, DeviceLocale deviceLocale) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(OculusContent.Profile.LOCALE, deviceLocale.getLocaleName());
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject.put(QuickPromotionGraphQL.ARGUMENT_MUTATION_INPUT, jSONObject2);
            GraphQLUtil.queryDoc(okHttpClient, LOCALE_EDIT_MUTATION, jSONObject, str, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.device.DeviceLocaleGraphQLUtil.AnonymousClass1 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = DeviceLocaleGraphQLUtil.TAG;
                    Log.d(str2, "Failed to set locale: " + str);
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    Log.d(DeviceLocaleGraphQLUtil.TAG, "Successfully set locale");
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Error building arguments to set locale", e);
        }
    }
}
