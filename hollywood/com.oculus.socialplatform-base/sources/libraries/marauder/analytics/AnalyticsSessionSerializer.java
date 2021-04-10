package libraries.marauder.analytics;

import com.facebook.acra.ACRA;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleParams;
import com.oculus.messenger.service.MessengerService;
import java.io.IOException;
import libraries.marauder.analytics.utils.json.JsonArray;
import libraries.marauder.analytics.utils.json.JsonMap;
import libraries.marauder.analytics.utils.json.JsonType;

public class AnalyticsSessionSerializer {
    public static JsonMap serialize(AnalyticsSession analyticsSession) throws IOException {
        JsonMap jsonMap = new JsonMap();
        jsonMap.addEntry("seq", (long) analyticsSession.mSequence);
        jsonMap.addEntry(AnalyticsEventBase.TIME_KEY, AnalyticsUtil.formatServerTime(analyticsSession.mTime));
        jsonMap.addEntry(MessengerService.InitParamKeys.APP_ID, analyticsSession.mAppId);
        jsonMap.addEntry("app_ver", analyticsSession.mAppVersion);
        jsonMap.addEntry("build_num", analyticsSession.mBuildNumber);
        jsonMap.addEntry(GraphQLVRLifecycleParams.ARGUMENT_DEVICE_ID, analyticsSession.mDeviceId);
        jsonMap.addEntry(ACRA.SESSION_ID_KEY, analyticsSession.getSessionId().toString());
        jsonMap.addEntry("uid", analyticsSession.mFacebookUserId);
        jsonMap.addEntry("cuid", analyticsSession.mCustomUserId);
        JsonArray jsonArray = new JsonArray();
        for (IAnalyticsEvent iAnalyticsEvent : analyticsSession.mCurrentBatch) {
            jsonArray.addEntry(iAnalyticsEvent.toString());
        }
        jsonMap.addEntry("data", (JsonType) jsonArray);
        jsonMap.addEntry("log_type", "client_event");
        return jsonMap;
    }
}
