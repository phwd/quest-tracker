package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import org.json.JSONException;
import org.json.JSONObject;

public class LinearLayoutComponent extends ViewGroupComponent {
    private LinearLayout mLinearLayout;

    public LinearLayoutComponent(@NonNull Context context) {
        super(context);
    }

    public LinearLayoutComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LinearLayoutComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mLinearLayout = new LinearLayout(context);
        return this.mLinearLayout;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent, com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(AssistantComponentContract.Components.LinearLayoutComponent.ORIENTATION)) {
            this.mLinearLayout.setOrientation(jSONObject.getInt(AssistantComponentContract.Components.LinearLayoutComponent.ORIENTATION));
        }
        super.onApplyJson(jSONObject);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onClearComponents() {
        this.mLinearLayout.removeAllViews();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onParseChildComponent(JSONObject jSONObject, BaseComponent baseComponent) throws JSONException {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getComponentLayoutParams(baseComponent);
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.has("layout-gravity")) {
                layoutParams.gravity = parseGravity(jSONObject2, "layout-gravity");
                baseComponent.setLayoutParams(layoutParams);
            }
            if (jSONObject2.has(AssistantComponentContract.Components.LinearLayoutComponent.WEIGHT)) {
                layoutParams.weight = (float) jSONObject2.getDouble(AssistantComponentContract.Components.LinearLayoutComponent.WEIGHT);
                if (this.mLinearLayout.getOrientation() == 0) {
                    layoutParams.width = 0;
                } else {
                    layoutParams.height = 0;
                }
                baseComponent.setLayoutParams(layoutParams);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onAddConstructedChildView(BaseComponent baseComponent) {
        this.mLinearLayout.addView(baseComponent);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onAddView(View view) {
        this.mLinearLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public ViewGroup.LayoutParams getComponentLayoutParams(BaseComponent baseComponent) {
        ViewGroup.LayoutParams layoutParams = baseComponent.getLayoutParams();
        if (layoutParams == null) {
            return new LinearLayout.LayoutParams(-2, -2);
        }
        return !(layoutParams instanceof LinearLayout.LayoutParams) ? new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height) : layoutParams;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public ViewGroup getParentViewGroup() {
        return this.mLinearLayout;
    }

    public void setOrientation(int i) {
        this.mLinearLayout.setOrientation(i);
    }
}
