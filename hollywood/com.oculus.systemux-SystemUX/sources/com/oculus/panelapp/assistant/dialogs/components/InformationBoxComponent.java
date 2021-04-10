package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class InformationBoxComponent extends BaseComponent {
    @Nullable
    private final AttributeSet mAttrSet;
    private OCInfo mInfoBox;

    public InformationBoxComponent(@NonNull Context context) {
        super(context);
        this.mAttrSet = null;
    }

    public InformationBoxComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAttrSet = attributeSet;
    }

    public InformationBoxComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAttrSet = attributeSet;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mInfoBox = new OCInfo(context, this.mAttrSet);
        return this.mInfoBox;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        super.onApplyJson(jSONObject);
        this.mInfoBox.setText(jSONObject.optString("text"));
    }
}
