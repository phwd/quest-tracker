package com.oculus.panelapp.social;

import android.content.Context;
import android.util.Log;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.tablet.logging.ClickEventButtonId;
import javax.annotation.Nullable;

public final class SocialLogger {
    public static final String ALL_CHATS_CLICKED_EVENT = "oculus_messenger_quick_message_dialog_all_chats_clicked";
    public static final String CANNOT_MESSAGE_EVENT = "oculus_messenger_quick_message_dialog_cannot_message_user";
    public static final String ERROR_EVENT = "auiv2_people_tablet_error";
    public static final String NAME_FIELD = "name";
    public static final String SOURCE_FIELD = "source";
    public static final String SYSTEMUX_SOURCE = "systemux";
    public static final String THREAD_ID_FIELD = "thread_id";

    /* renamed from: com.oculus.panelapp.social.SocialLogger$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$tablet$logging$ClickEventButtonId = new int[ClickEventButtonId.values().length];
    }

    public static void logQuickMessageAllChatsClickedEvents(Context context, String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(ALL_CHATS_CLICKED_EVENT);
        setMessengerExtra(analyticsEvent, ALL_CHATS_CLICKED_EVENT, str);
        UnifiedTelemetryLogger.getInstance(context).reportEvent(analyticsEvent, false);
    }

    public static void logQuickMessageCannotMessageUserEvent(Context context, String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(CANNOT_MESSAGE_EVENT);
        setMessengerExtra(analyticsEvent, CANNOT_MESSAGE_EVENT, str);
        UnifiedTelemetryLogger.getInstance(context).reportEvent(analyticsEvent, false);
    }

    public static void setMessengerExtra(AnalyticsEvent analyticsEvent, String str, String str2) {
        analyticsEvent.setExtra("name", str);
        analyticsEvent.setExtra("source", SYSTEMUX_SOURCE);
        analyticsEvent.setExtra("thread_id", str2);
    }

    public static void logError(Context context, String str, String str2, String str3) {
        Log.e(str2, str3);
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(ERROR_EVENT);
        analyticsEvent.setExtra("action", str);
        analyticsEvent.setExtra("source", str2);
        analyticsEvent.setExtra("error_message", str3);
        UnifiedTelemetryLogger.getInstance(context).reportEvent(analyticsEvent, false);
    }

    @Nullable
    public static SocialPartyEvent buttonEventToPartyEvent(ClickEventButtonId clickEventButtonId) {
        return null;
    }

    public static void log(ClickEventButtonId clickEventButtonId, SocialPanelApp socialPanelApp) {
        socialPanelApp.logButtonClick(clickEventButtonId);
    }
}
