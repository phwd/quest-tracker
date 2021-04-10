package com.oculus.panelapp.assistant.config;

import android.text.TextUtils;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AttentionSystemContract;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AttentionSystemConfig {
    public static final int ANIMATION_DURATION = 500;
    private static final int ATTENTION_SYSTEM_HIDE_DURATION_MS = 3000;
    private static final int BUBBLE_ANIMATION_DURATION = 240;
    private static final int CONTAINER_EXPAND_DURATION = 370;
    private static final int FADE_DURATION = 200;
    public static final int POST_ACTION_HIDE_DELAY_MS = 7000;
    private static final int SUBTEXT_SHOW_DELAY = 600;
    private boolean mAnimate;
    private BroadcastConfig mBroadcastConfig;
    private boolean mCloseVisible;
    private JSONArray mContent;
    private int mDuration;
    private boolean mHitTestable;
    private JSONObject mIcon;
    private String mId = UUID.randomUUID().toString();
    private JSONObject mJson;
    private String mMessage;
    private String mSubMessage;

    private AttentionSystemConfig() {
    }

    public static AttentionSystemConfig create(JSONObject jSONObject) throws JSONException {
        AttentionSystemConfig attentionSystemConfig = new AttentionSystemConfig();
        attentionSystemConfig.mBroadcastConfig = new BroadcastConfig();
        attentionSystemConfig.mBroadcastConfig.applyJson(jSONObject);
        attentionSystemConfig.mJson = jSONObject;
        attentionSystemConfig.mDuration = jSONObject.optInt(AttentionSystemContract.KEY_DURATION, 3000);
        attentionSystemConfig.mAnimate = jSONObject.optBoolean(AttentionSystemContract.KEY_ANIMATE, true);
        attentionSystemConfig.mHitTestable = jSONObject.optBoolean("hit-testable", false);
        attentionSystemConfig.mCloseVisible = jSONObject.optBoolean(AssistantDialogContract.Dialog.CLOSE_BUTTON_VISIBLE, attentionSystemConfig.mHitTestable);
        if (jSONObject.has("state-icon")) {
            attentionSystemConfig.mIcon = jSONObject.getJSONObject("state-icon");
        }
        attentionSystemConfig.mMessage = jSONObject.optString("message", null);
        attentionSystemConfig.mSubMessage = jSONObject.optString(AttentionSystemContract.KEY_SUBMESSAGE, null);
        attentionSystemConfig.mId = jSONObject.optString("id", attentionSystemConfig.mId);
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.has("components")) {
                attentionSystemConfig.mContent = jSONObject2.getJSONArray("components");
            }
            attentionSystemConfig.mId = jSONObject2.optString("id", attentionSystemConfig.mId);
        }
        return attentionSystemConfig;
    }

    public JSONObject getJson() {
        return this.mJson;
    }

    public BroadcastConfig getBroadcastConfig() {
        return this.mBroadcastConfig;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public boolean shouldAnimateContentUpdates() {
        return this.mAnimate;
    }

    public boolean isHitTestable() {
        return this.mHitTestable;
    }

    public boolean isCloseVisible() {
        return this.mCloseVisible;
    }

    public JSONObject getIcon() {
        return this.mIcon;
    }

    public String getId() {
        return this.mId;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getSubMessage() {
        return this.mSubMessage;
    }

    public JSONArray getContent() {
        return this.mContent;
    }

    public boolean hasTextContent() {
        return !TextUtils.isEmpty(this.mMessage) || !TextUtils.isEmpty(this.mSubMessage);
    }
}
