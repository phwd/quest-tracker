package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.panelapp.assistant.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ListItemComponent extends BaseComponent {
    private ImageView mChevron;
    private RemoteImageViewComponent mIcon;
    private String mId;
    private View mListItemComponent;
    private TextView mTextPrimary;
    private TextView mTextSecondary;

    public ListItemComponent(@NonNull Context context) {
        super(context);
    }

    public ListItemComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ListItemComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mListItemComponent = LayoutInflater.from(context).inflate(R.layout.list_item_component, (ViewGroup) null, false);
        this.mTextPrimary = (TextView) this.mListItemComponent.findViewById(R.id.text_primary);
        this.mTextSecondary = (TextView) this.mListItemComponent.findViewById(R.id.text_secondary);
        this.mIcon = (RemoteImageViewComponent) this.mListItemComponent.findViewById(R.id.icon);
        this.mChevron = (ImageView) this.mListItemComponent.findViewById(R.id.chevron);
        return this.mListItemComponent;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        super.onApplyJson(jSONObject);
        this.mId = jSONObject.optString("id", null);
        setPrimaryText(jSONObject.optString("primary", ""));
        setSecondaryText(jSONObject.optString("secondary", ""));
        int i = 8;
        if (jSONObject.has("icon")) {
            this.mIcon.setVisibility(0);
            this.mIcon.applyJson(getSurface(), jSONObject.getJSONObject("icon").getJSONObject("data"));
        } else {
            this.mIcon.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.mId)) {
            this.mListItemComponent.setBackgroundResource(0);
        } else {
            this.mListItemComponent.setBackgroundResource(R.drawable.ocbutton_borderless);
            addOculusInteractions(this.mListItemComponent, new View.OnClickListener() {
                /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$ListItemComponent$AurO6Ru2ogjEe9mKB4YlhnTrEE */

                public final void onClick(View view) {
                    ListItemComponent.this.lambda$onApplyJson$40$ListItemComponent(view);
                }
            });
        }
        ImageView imageView = this.mChevron;
        if (jSONObject.optBoolean(AssistantComponentContract.Components.ListItemComponent.CHEVRON, false)) {
            i = 0;
        }
        imageView.setVisibility(i);
    }

    public /* synthetic */ void lambda$onApplyJson$40$ListItemComponent(View view) {
        sendActionClick(this.mId);
    }

    private void setPrimaryText(String str) {
        this.mTextPrimary.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        this.mTextPrimary.setText(str);
    }

    private void setSecondaryText(String str) {
        this.mTextSecondary.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        this.mTextSecondary.setText(str);
    }
}
