package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.panelapp.assistant.R;
import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackComponent extends BaseComponent {
    public FeedbackComponent(@NonNull Context context) {
        super(context);
    }

    public FeedbackComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FeedbackComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.feedback, (ViewGroup) null, false);
    }

    private void applyIcon(JSONObject jSONObject, int i, String str) throws JSONException {
        RemoteImageViewComponent remoteImageViewComponent = (RemoteImageViewComponent) findViewById(i);
        if (jSONObject.has(str)) {
            remoteImageViewComponent.applyJson(getSurface(), jSONObject.getJSONObject(str));
        } else {
            remoteImageViewComponent.setVisibility(8);
        }
        remoteImageViewComponent.setActionClickListener(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        super.onApplyJson(jSONObject);
        applyIcon(jSONObject, R.id.left_icon1, AssistantComponentContract.Components.FeedbackComponent.LEFT_ICON_1);
        applyIcon(jSONObject, R.id.left_icon2, AssistantComponentContract.Components.FeedbackComponent.LEFT_ICON_2);
        applyIcon(jSONObject, R.id.right_icon1, AssistantComponentContract.Components.FeedbackComponent.RIGHT_ICON_1);
        applyIcon(jSONObject, R.id.right_icon2, AssistantComponentContract.Components.FeedbackComponent.LEFT_ICON_2);
        TextComponent textComponent = (TextComponent) findViewById(R.id.text);
        if (jSONObject.has("text")) {
            textComponent.applyJson(getSurface(), jSONObject.getJSONObject("text"));
        }
    }
}
