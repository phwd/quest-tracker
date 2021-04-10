package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.assistant.R;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class ButtonComponent extends BaseComponent {
    private OCButton mButton;
    private String mId;

    public ButtonComponent(@NonNull Context context) {
        super(context);
    }

    public ButtonComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ButtonComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mButton = (OCButton) LayoutInflater.from(getContext()).inflate(R.layout.remote_button_secondary, (ViewGroup) null);
        return this.mButton;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(AssistantComponentContract.Components.ButtonComponent.BUTTON_STYLE)) {
            String string = jSONObject.getString(AssistantComponentContract.Components.ButtonComponent.BUTTON_STYLE);
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != -314765822) {
                if (hashCode == 1825644485 && string.equals("borderless")) {
                    c = 1;
                }
            } else if (string.equals("primary")) {
                c = 0;
            }
            if (c == 0) {
                this.mButton.setBackgroundResource(R.drawable.ocbutton_primary);
            } else if (c != 1) {
                this.mButton.setBackgroundResource(R.drawable.ocbutton_secondary);
            } else {
                this.mButton.setBackgroundResource(R.drawable.ocbutton_borderless);
            }
        }
        super.onApplyJson(jSONObject);
        this.mId = jSONObject.optString("id", UUID.randomUUID().toString());
        this.mButton.setText(jSONObject.optString("text", ""));
        this.mButton.setEventHandler(getSurface().getApp());
        this.mButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$ButtonComponent$n1P0NEbg8ltj3QZpu661mgCjyo */

            public final void onClick(View view) {
                ButtonComponent.this.lambda$onApplyJson$38$ButtonComponent(view);
            }
        });
    }

    public /* synthetic */ void lambda$onApplyJson$38$ButtonComponent(View view) {
        sendActionClick(this.mId);
    }
}
