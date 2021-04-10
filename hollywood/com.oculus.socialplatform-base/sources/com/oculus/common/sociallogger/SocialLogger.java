package com.oculus.common.sociallogger;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;

public class SocialLogger {
    public final String EMPTY_ERROR_MESSAGE = "Failure logged without error message";
    @Nullable
    public String mFacebookUserId;
    public final TabletType mTabletType;
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    private void buildBasicParamsForAction(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, AnalyticsEvent analyticsEvent) {
        analyticsEvent.setExtra(LoggingConstants.TABLET, this.mTabletType.getTelemetryTabletType());
        analyticsEvent.setExtra(LoggingConstants.SURFACE, surfaceType.getTelemetrySurfaceType());
        analyticsEvent.setExtra(LoggingConstants.ACTION_ID, actionId.getTelemetryActionId());
        analyticsEvent.setExtra(LoggingConstants.BUTTON_ID, clickEventButtonId.getTelemetryButtonId());
        analyticsEvent.setExtra(LoggingConstants.CLIENT_TIME, Long.valueOf(System.currentTimeMillis()));
        String str = this.mFacebookUserId;
        if (str != null) {
            analyticsEvent.setExtra(LoggingConstants.FACEBOOK_USERID, str);
        }
    }

    private void buildBasicParamsForButtonClicks(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, AnalyticsEvent analyticsEvent) {
        analyticsEvent.setExtra(LoggingConstants.TABLET, this.mTabletType.getTelemetryTabletType());
        analyticsEvent.setExtra(LoggingConstants.SURFACE, surfaceType.getTelemetrySurfaceType());
        analyticsEvent.setExtra(LoggingConstants.BUTTON_ID, clickEventButtonId.getTelemetryButtonId());
        analyticsEvent.setExtra(LoggingConstants.CLIENT_TIME, Long.valueOf(System.currentTimeMillis()));
        String str = this.mFacebookUserId;
        if (str != null) {
            analyticsEvent.setExtra(LoggingConstants.FACEBOOK_USERID, str);
        }
    }

    private AnalyticsEvent createLogButtonClickEvent(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggingConstants.BUTTON_CLICK_LOG);
        buildBasicParamsForButtonClicks(clickEventButtonId, surfaceType, analyticsEvent);
        return analyticsEvent;
    }

    public static void mapArgsToAnalyticsEvent(Map<String, String> map, AnalyticsEvent analyticsEvent) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    analyticsEvent.setExtra(entry.getKey(), value);
                }
            }
        }
    }

    public static void varArgsToAnalyticsEvent(String[] strArr, AnalyticsEvent analyticsEvent) {
        if (strArr != null) {
            int length = strArr.length;
            if (length % 2 == 0) {
                for (int i = 0; i < length - 1; i += 2) {
                    String str = strArr[i + 1];
                    if (!TextUtils.isEmpty(str)) {
                        analyticsEvent.setExtra(strArr[i], str);
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Invalid count of pairs in SocialLogger.");
        }
    }

    public static Map<String, String> varArgsToMap(String[] strArr) {
        HashMap hashMap = new HashMap();
        if (strArr != null) {
            int length = strArr.length;
            if (length % 2 == 0) {
                for (int i = 0; i < length - 1; i += 2) {
                    hashMap.put(strArr[i], strArr[i + 1]);
                }
            }
        }
        return hashMap;
    }

    public UnifiedTelemetryLogger getUnifiedTelemetryLogger() {
        return this.mUnifiedTelemetryLogger;
    }

    public SocialLogger(Context context, TabletType tabletType) {
        this.mTabletType = tabletType;
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String validateErrorMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return "Failure logged without error message";
        }
        return str;
    }

    public void setFacebookUserID(String str) {
        this.mFacebookUserId = str;
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String str) {
        logActionFailure(actionId, clickEventButtonId, surfaceType, str, (String[]) null);
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String str, @Nullable Map<String, String> map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggingConstants.ACTION_LOG);
        buildBasicParamsForAction(actionId, clickEventButtonId, surfaceType, analyticsEvent);
        analyticsEvent.setExtra("error_message", validateErrorMessage(str));
        mapArgsToAnalyticsEvent(map, analyticsEvent);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String str, @Nullable String... strArr) {
        logActionFailure(actionId, clickEventButtonId, surfaceType, str, varArgsToMap(strArr));
    }

    public void logActionFailure(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, Throwable th, Map<String, String> map) {
        String message = th.getMessage();
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            message = String.format("%s\n  at %s", message, stackTraceElement);
        }
        logActionFailure(actionId, clickEventButtonId, surfaceType, message, map);
    }

    public void logActionSuccess(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        logActionSuccess(actionId, clickEventButtonId, surfaceType, (String[]) null);
    }

    public void logActionSuccess(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, @Nullable Map<String, String> map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggingConstants.ACTION_LOG);
        buildBasicParamsForAction(actionId, clickEventButtonId, surfaceType, analyticsEvent);
        mapArgsToAnalyticsEvent(map, analyticsEvent);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logActionSuccess(ActionId actionId, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, @Nullable String... strArr) {
        logActionSuccess(actionId, clickEventButtonId, surfaceType, varArgsToMap(strArr));
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        this.mUnifiedTelemetryLogger.reportEvent(createLogButtonClickEvent(clickEventButtonId, surfaceType), false);
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, @Nullable Map<String, String> map) {
        AnalyticsEvent createLogButtonClickEvent = createLogButtonClickEvent(clickEventButtonId, surfaceType);
        mapArgsToAnalyticsEvent(map, createLogButtonClickEvent);
        this.mUnifiedTelemetryLogger.reportEvent(createLogButtonClickEvent, false);
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, String... strArr) {
        AnalyticsEvent createLogButtonClickEvent = createLogButtonClickEvent(clickEventButtonId, surfaceType);
        varArgsToAnalyticsEvent(strArr, createLogButtonClickEvent);
        this.mUnifiedTelemetryLogger.reportEvent(createLogButtonClickEvent, false);
    }

    public void logImpression(SurfaceType surfaceType) {
        logImpression(surfaceType, null);
    }

    public void logImpression(SurfaceType surfaceType, @Nullable Map<String, String> map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggingConstants.IMPRESSION_LOG);
        analyticsEvent.setExtra(LoggingConstants.TABLET, this.mTabletType.getTelemetryTabletType());
        analyticsEvent.setExtra(LoggingConstants.SURFACE, surfaceType.getTelemetrySurfaceType());
        analyticsEvent.setExtra(LoggingConstants.CLIENT_TIME, Long.valueOf(System.currentTimeMillis()));
        String str = this.mFacebookUserId;
        if (str != null) {
            analyticsEvent.setExtra(LoggingConstants.FACEBOOK_USERID, str);
        }
        mapArgsToAnalyticsEvent(map, analyticsEvent);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logImpressionFailure(SurfaceType surfaceType, String str) {
        logImpression(surfaceType, new HashMap<String, String>(str) {
            /* class com.oculus.common.sociallogger.SocialLogger.AnonymousClass1 */
            public final /* synthetic */ String val$error;

            {
                this.val$error = r4;
                put("error_message", SocialLogger.this.validateErrorMessage(r4));
            }
        });
    }

    public void logImpressionFailure(SurfaceType surfaceType, String str, @Nullable Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        hashMap.put("error_message", validateErrorMessage(str));
        logImpression(surfaceType, hashMap);
    }
}
