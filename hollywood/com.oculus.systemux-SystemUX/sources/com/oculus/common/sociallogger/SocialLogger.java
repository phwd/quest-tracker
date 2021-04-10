package com.oculus.common.sociallogger;

import androidx.annotation.Nullable;
import com.oculus.vrshell.panels.telemetry.LoggingApi;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SocialLogger {
    @Nullable
    private String mFacebookUserId;
    private LoggingApi mLoggingApi;
    private TabletType mTabletType;

    public SocialLogger(LoggingApi loggingApi, TabletType tabletType) {
        this.mLoggingApi = loggingApi;
        this.mTabletType = tabletType;
    }

    public void destroy() {
        this.mLoggingApi = null;
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String... strArr) {
        logButtonClick(clickEventButtonId, surfaceType, varArgsToMap(strArr));
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, @Nullable Map<String, String> map) {
        if (this.mLoggingApi != null) {
            Map<String, String> buildBasicParamsForButtonClicks = buildBasicParamsForButtonClicks(clickEventButtonId, surfaceType);
            if (map != null) {
                buildBasicParamsForButtonClicks.putAll(map);
            }
            this.mLoggingApi.rawLogEvent("auiv2_messenger_vr_button_click", buildBasicParamsForButtonClicks);
        }
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        if (this.mLoggingApi != null) {
            this.mLoggingApi.rawLogEvent("auiv2_messenger_vr_button_click", buildBasicParamsForButtonClicks(clickEventButtonId, surfaceType));
        }
    }

    public void logImpression(SurfaceType surfaceType) {
        logImpression(surfaceType, null);
    }

    public void logImpressionFailure(SurfaceType surfaceType, final String str) {
        logImpression(surfaceType, new HashMap<String, String>() {
            /* class com.oculus.common.sociallogger.SocialLogger.AnonymousClass1 */

            {
                String str;
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    str = URLEncoder.encode("Failure logged without error message");
                } else {
                    str = URLEncoder.encode(str);
                }
                put("error_message", str);
            }
        });
    }

    public void logImpressionFailure(SurfaceType surfaceType, String str, @Nullable Map<String, String> map) {
        String str2;
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        if (str == null || str.length() == 0) {
            str2 = URLEncoder.encode("Failure logged without error message");
        } else {
            str2 = URLEncoder.encode(str);
        }
        hashMap.put("error_message", str2);
        logImpression(surfaceType, hashMap);
    }

    public void logImpression(SurfaceType surfaceType, @Nullable Map<String, String> map) {
        if (this.mLoggingApi != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(LoggingConstants.TABLET, this.mTabletType.getTelemetryTabletType());
            hashMap.put(LoggingConstants.SURFACE, surfaceType.getTelemetrySurfaceType());
            hashMap.put(LoggingConstants.CLIENT_TIME, Long.toString(System.currentTimeMillis()));
            String str = this.mFacebookUserId;
            if (str != null) {
                hashMap.put(LoggingConstants.FACEBOOK_USERID, str);
            }
            if (map != null) {
                hashMap.putAll(map);
            }
            this.mLoggingApi.rawLogEvent(LoggingConstants.IMPRESSION_LOG, hashMap);
        }
    }

    public void logActionSuccess(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        logActionSuccess(actionId, clickEventButtonId, surfaceType, (String[]) null);
    }

    public void logActionSuccess(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, @Nullable String... strArr) {
        logActionSuccess(actionId, clickEventButtonId, surfaceType, varArgsToMap(strArr));
    }

    public void logActionSuccess(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, @Nullable Map<String, String> map) {
        if (this.mLoggingApi != null) {
            Map<String, String> buildBasicParamsForAction = buildBasicParamsForAction(actionId, clickEventButtonId, surfaceType);
            if (map != null) {
                buildBasicParamsForAction.putAll(map);
            }
            this.mLoggingApi.rawLogEvent(LoggingConstants.ACTION_LOG, buildBasicParamsForAction);
        }
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String str) {
        logActionFailure(actionId, clickEventButtonId, surfaceType, str, (String[]) null);
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String str, @Nullable Map<String, String> map) {
        String str2;
        if (this.mLoggingApi != null) {
            Map<String, String> buildBasicParamsForAction = buildBasicParamsForAction(actionId, clickEventButtonId, surfaceType);
            if (str == null || str.length() == 0) {
                str2 = URLEncoder.encode("Failure logged without error message");
            } else {
                str2 = URLEncoder.encode(str);
            }
            buildBasicParamsForAction.put("error_message", str2);
            if (map != null) {
                buildBasicParamsForAction.putAll(map);
            }
            this.mLoggingApi.rawLogEvent(LoggingConstants.ACTION_LOG, buildBasicParamsForAction);
        }
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, Throwable th, Map<String, String> map) {
        String message = th.getMessage();
        String str = message;
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            str = String.format("%s\n  at %s", str, stackTraceElement);
        }
        logActionFailure(actionId, clickEventButtonId, surfaceType, str, map);
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String str, @Nullable String... strArr) {
        logActionFailure(actionId, clickEventButtonId, surfaceType, str, varArgsToMap(strArr));
    }

    public void setFacebookUserID(String str) {
        this.mFacebookUserId = str;
    }

    private Map<String, String> buildBasicParamsForButtonClicks(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        HashMap hashMap = new HashMap();
        hashMap.put(LoggingConstants.TABLET, this.mTabletType.getTelemetryTabletType());
        hashMap.put(LoggingConstants.SURFACE, surfaceType.getTelemetrySurfaceType());
        hashMap.put("button_id", clickEventButtonId.getTelemetryButtonId());
        hashMap.put(LoggingConstants.CLIENT_TIME, Long.toString(System.currentTimeMillis()));
        String str = this.mFacebookUserId;
        if (str != null) {
            hashMap.put(LoggingConstants.FACEBOOK_USERID, str);
        }
        return hashMap;
    }

    private Map<String, String> buildBasicParamsForAction(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        HashMap hashMap = new HashMap();
        hashMap.put(LoggingConstants.TABLET, this.mTabletType.getTelemetryTabletType());
        hashMap.put(LoggingConstants.SURFACE, surfaceType.getTelemetrySurfaceType());
        hashMap.put(LoggingConstants.ACTION_ID, actionId.getTelemetryActionId());
        hashMap.put("button_id", clickEventButtonId.getTelemetryButtonId());
        hashMap.put(LoggingConstants.CLIENT_TIME, Long.toString(System.currentTimeMillis()));
        String str = this.mFacebookUserId;
        if (str != null) {
            hashMap.put(LoggingConstants.FACEBOOK_USERID, str);
        }
        return hashMap;
    }

    private static Map<String, String> varArgsToMap(String[] strArr) {
        HashMap hashMap = new HashMap();
        if (strArr != null && strArr.length % 2 == 0) {
            for (int i = 0; i < strArr.length - 1; i += 2) {
                hashMap.put(strArr[i], strArr[i + 1]);
            }
        }
        return hashMap;
    }
}
