package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.assistant.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ToggleComponent extends BaseComponent {
    private OCToggle mToggle;

    public ToggleComponent(@NonNull Context context) {
        super(context);
    }

    public ToggleComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ToggleComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mToggle = (OCToggle) LayoutInflater.from(context).inflate(R.layout.remote_toggle, (ViewGroup) null);
        return this.mToggle;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        super.onApplyJson(jSONObject);
        this.mToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$ToggleComponent$kEpanWo2FmTHtbXcMxmhCNyro */

            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ToggleComponent.this.lambda$onApplyJson$41$ToggleComponent(compoundButton, z);
            }
        });
        this.mToggle.setEventHandler(getSurface().getApp());
        this.mToggle.setChecked(jSONObject.optBoolean("checked", false));
    }

    public /* synthetic */ void lambda$onApplyJson$41$ToggleComponent(CompoundButton compoundButton, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("checked", z);
        sendActionClick(getComponentId(), bundle);
    }
}
