package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewGroupComponent extends BaseComponent {
    /* access modifiers changed from: protected */
    public abstract ViewGroup.LayoutParams getComponentLayoutParams(BaseComponent baseComponent);

    /* access modifiers changed from: protected */
    public abstract ViewGroup getParentViewGroup();

    /* access modifiers changed from: protected */
    public abstract void onAddConstructedChildView(BaseComponent baseComponent);

    /* access modifiers changed from: protected */
    public abstract void onAddView(View view);

    /* access modifiers changed from: protected */
    public abstract void onClearComponents();

    /* access modifiers changed from: protected */
    public abstract void onParseChildComponent(JSONObject jSONObject, BaseComponent baseComponent) throws JSONException;

    public ViewGroupComponent(@NonNull Context context) {
        super(context);
    }

    public ViewGroupComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ViewGroupComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        super.onApplyJson(jSONObject);
        addComponents(jSONObject, this);
    }

    public void addComponents(JSONObject jSONObject) throws JSONException {
        addComponents(jSONObject, this);
    }

    public void clearComponents() {
        onClearComponents();
    }

    public void addComponents(JSONObject jSONObject, ActionClickListener actionClickListener) throws JSONException {
        if (jSONObject.has("components")) {
            addChildComponents(jSONObject.getJSONArray("components"), getParentViewGroup(), actionClickListener);
        }
    }

    public void addComponents(JSONArray jSONArray) throws JSONException {
        addChildComponents(jSONArray, getParentViewGroup(), this);
    }

    public void addComponents(JSONArray jSONArray, ActionClickListener actionClickListener) throws JSONException {
        addChildComponents(jSONArray, getParentViewGroup(), actionClickListener);
    }

    private void addChildComponents(JSONArray jSONArray, ViewGroup viewGroup, ActionClickListener actionClickListener) throws JSONException {
        int i = 0;
        while (jSONArray != null && i < jSONArray.length()) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            BaseComponent create = ComponentFactory.create(getSurface(), jSONObject, getContext());
            if (actionClickListener == null) {
                create.setActionClickListener(this);
            } else {
                create.setActionClickListener(actionClickListener);
            }
            if (jSONObject.has("data")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getComponentLayoutParams(create);
                if (jSONObject2.has(AssistantComponentContract.Margins.MARGINS)) {
                    Object obj = jSONObject2.get(AssistantComponentContract.Margins.MARGINS);
                    if (obj instanceof JSONObject) {
                        JSONObject jSONObject3 = (JSONObject) obj;
                        marginLayoutParams.leftMargin = optDimension(jSONObject3, "left", marginLayoutParams.leftMargin);
                        marginLayoutParams.rightMargin = optDimension(jSONObject3, "right", marginLayoutParams.rightMargin);
                        marginLayoutParams.topMargin = optDimension(jSONObject3, "top", marginLayoutParams.topMargin);
                        marginLayoutParams.bottomMargin = optDimension(jSONObject3, "bottom", marginLayoutParams.bottomMargin);
                    } else {
                        int dimension = getDimension(jSONObject2, AssistantComponentContract.Margins.MARGINS);
                        marginLayoutParams.leftMargin = dimension;
                        marginLayoutParams.rightMargin = dimension;
                        marginLayoutParams.topMargin = dimension;
                        marginLayoutParams.bottomMargin = dimension;
                    }
                    create.setLayoutParams(marginLayoutParams);
                }
            }
            onParseChildComponent(jSONObject, create);
            onAddConstructedChildView(create);
            postInvalidate();
            i++;
        }
    }

    public void addChildView(View view) {
        onAddView(view);
    }
}
