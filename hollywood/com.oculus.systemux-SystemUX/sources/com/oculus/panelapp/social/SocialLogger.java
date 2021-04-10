package com.oculus.panelapp.social;

import android.util.Log;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.tablet.logging.ClickEventButtonId;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public final class SocialLogger {
    private static final String ALL_CHATS_CLICKED_EVENT = "oculus_messenger_quick_message_dialog_all_chats_clicked";
    private static final String CANNOT_MESSAGE_EVENT = "oculus_messenger_quick_message_dialog_cannot_message_user";
    private static final String ERROR_EVENT = "auiv2_people_tablet_error";
    private static final String NAME_FIELD = "name";
    private static final String SOURCE_FIELD = "source";
    private static final String SYSTEMUX_SOURCE = "systemux";
    private static final String THREAD_ID_FIELD = "thread_id";

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.social.SocialLogger$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$tablet$logging$ClickEventButtonId = new int[ClickEventButtonId.values().length];
    }

    public static void log(ClickEventButtonId clickEventButtonId, SocialPanelApp socialPanelApp) {
        socialPanelApp.logButtonClick(clickEventButtonId);
        SocialPartyEvent buttonEventToPartyEvent = buttonEventToPartyEvent(clickEventButtonId);
        if (buttonEventToPartyEvent != null) {
            socialPanelApp.logSocialPartyEvent(buttonEventToPartyEvent, new String[0]);
        }
    }

    @Nullable
    private static SocialPartyEvent buttonEventToPartyEvent(ClickEventButtonId clickEventButtonId) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$tablet$logging$ClickEventButtonId[clickEventButtonId.ordinal()];
        return null;
    }

    private static String[] createMessengerExtraData(String str, String str2) {
        return new String[]{"name", str, "source", SYSTEMUX_SOURCE, "thread_id", str2};
    }

    public static void logError(SocialPanelApp socialPanelApp, String str, String str2, String str3) {
        Log.e(str2, str3);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", str);
            jSONObject.put("source", str2);
            jSONObject.put("error_message", str3);
            socialPanelApp.rawLogJsonEvent(ERROR_EVENT, jSONObject.toString());
        } catch (JSONException e) {
            Log.e(str2, "Error logging error", e);
        }
    }

    public static void logQuickMessageCannotMessageUserEvent(String str, SocialPanelApp socialPanelApp) {
        socialPanelApp.rawLogEvent(CANNOT_MESSAGE_EVENT, createMessengerExtraData(CANNOT_MESSAGE_EVENT, str));
    }

    public static void logQuickMessageAllChatsClickedEvents(String str, SocialPanelApp socialPanelApp) {
        socialPanelApp.rawLogEvent(ALL_CHATS_CLICKED_EVENT, createMessengerExtraData(ALL_CHATS_CLICKED_EVENT, str));
    }
}
