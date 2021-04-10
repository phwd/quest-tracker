package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class FrameLayoutComponent extends ViewGroupComponent {
    private FrameLayout mFrameLayout;

    public FrameLayoutComponent(@NonNull Context context) {
        super(context);
    }

    public FrameLayoutComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FrameLayoutComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mFrameLayout = new FrameLayout(context);
        return this.mFrameLayout;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onClearComponents() {
        this.mFrameLayout.removeAllViews();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onParseChildComponent(JSONObject jSONObject, BaseComponent baseComponent) throws JSONException {
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.has("layout-gravity")) {
                int parseGravity = parseGravity(jSONObject2, "layout-gravity");
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getComponentLayoutParams(baseComponent);
                layoutParams.gravity = parseGravity;
                baseComponent.setLayoutParams(layoutParams);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onAddConstructedChildView(BaseComponent baseComponent) {
        this.mFrameLayout.addView(baseComponent);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public void onAddView(View view) {
        this.mFrameLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public ViewGroup.LayoutParams getComponentLayoutParams(BaseComponent baseComponent) {
        ViewGroup.LayoutParams layoutParams = baseComponent.getLayoutParams();
        if (layoutParams == null) {
            return new FrameLayout.LayoutParams(-2, -2);
        }
        return !(layoutParams instanceof FrameLayout.LayoutParams) ? new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height) : layoutParams;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.ViewGroupComponent
    public ViewGroup getParentViewGroup() {
        return this.mFrameLayout;
    }
}
