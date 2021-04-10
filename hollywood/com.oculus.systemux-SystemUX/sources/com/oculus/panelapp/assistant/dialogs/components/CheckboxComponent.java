package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCCheckbox;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckboxComponent extends BaseComponent {
    @Nullable
    private final AttributeSet mAttrSet;
    private OCCheckbox mCheckbox;

    public CheckboxComponent(@NonNull Context context) {
        super(context);
        this.mAttrSet = null;
    }

    public CheckboxComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAttrSet = attributeSet;
    }

    public CheckboxComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAttrSet = attributeSet;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mCheckbox = new OCCheckbox(context, this.mAttrSet);
        return this.mCheckbox;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        super.onApplyJson(jSONObject);
        this.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$CheckboxComponent$KyvmgpikVjSQZIIjDClISMkWBk */

            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CheckboxComponent.this.lambda$onApplyJson$39$CheckboxComponent(compoundButton, z);
            }
        });
        this.mCheckbox.setEventHandler(getSurface().getApp());
        this.mCheckbox.setChecked(jSONObject.optBoolean("checked", false));
    }

    public /* synthetic */ void lambda$onApplyJson$39$CheckboxComponent(CompoundButton compoundButton, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("checked", z);
        sendActionClick(getComponentId(), bundle);
    }
}
