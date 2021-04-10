package com.oculus.panelapp.assistant;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AttentionSystemContract;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.assistant.animation.AnimationSetManager;
import com.oculus.panelapp.assistant.animation.listeners.AnimSuccessListener;
import com.oculus.panelapp.assistant.dialogs.AssistantDialog;
import com.oculus.panelapp.assistant.dialogs.IAssistantDialog;
import com.oculus.panelapp.assistant.dialogs.components.RemoteImageViewComponent;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpandedAttentionSystem extends AssistantDialog {
    private static final int FADE_IN_DURATION = 500;
    private static final String TAG = "ExpAttnSystem";
    private AnimationSetManager mAnimManager = new AnimationSetManager();
    private OCButton mClose;
    private TextView mLabel;
    private RemoteImageViewComponent mStateIcon;
    private TextView mSubLabel;
    private View mView;

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface, com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public String getSurfaceType() {
        return AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM;
    }

    public ExpandedAttentionSystem(Context context, AssistantPanelApp assistantPanelApp, String str, String str2) {
        super(context, assistantPanelApp, str, str2);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public View onCreateView(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.expanded_attn_view, (ViewGroup) null);
        this.mLabel = (TextView) this.mView.findViewById(R.id.label);
        this.mSubLabel = (TextView) this.mView.findViewById(R.id.sub_label);
        this.mStateIcon = (RemoteImageViewComponent) this.mView.findViewById(R.id.icon);
        this.mClose = (OCButton) this.mView.findViewById(R.id.close);
        this.mClose.setEventHandler(this.mApp);
        this.mClose.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.assistant.$$Lambda$ExpandedAttentionSystem$TB6e030nVBkjlzZXn09W3oNz5k */

            public final void onClick(View view) {
                ExpandedAttentionSystem.this.lambda$onCreateView$32$ExpandedAttentionSystem(view);
            }
        });
        this.mView.setAlpha(0.0f);
        this.mView.animate().alpha(1.0f).setDuration(500).start();
        try {
            this.mStateIcon.setImageResource("com.oculus.assistant", AttentionSystemContract.DEFAULT_ICON_NAME);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "Unable to set default icon. Default icon package could not be found");
            this.mStateIcon.setImageResource(0);
        }
        return this.mView;
    }

    public /* synthetic */ void lambda$onCreateView$32$ExpandedAttentionSystem(View view) {
        sendActionClick("close");
    }

    public static IAssistantDialog createDialog(Context context, AssistantPanelApp assistantPanelApp, JSONObject jSONObject) throws JSONException {
        ExpandedAttentionSystem expandedAttentionSystem = new ExpandedAttentionSystem(context, assistantPanelApp, null, jSONObject.optString("title", ""));
        expandedAttentionSystem.applyJson(jSONObject);
        return expandedAttentionSystem;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        this.mClose.setVisibility(jSONObject.optBoolean(AssistantDialogContract.Dialog.CLOSE_BUTTON_VISIBLE, this.mClose.getVisibility() == 0) ? 0 : 8);
        applyAttentionsSystemData(jSONObject);
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            String optString = jSONObject2.optString("id", UUID.randomUUID().toString());
            String surfaceId = getSurfaceId();
            if (TextUtils.isEmpty(surfaceId) || surfaceId.equals(optString)) {
                super.onApplyJson(jSONObject2);
                return;
            }
            View contentView = getContentView();
            this.mAnimManager.clearPendingAnimations();
            this.mAnimManager.enqueueContinueFade(contentView, false).addListener(new AnimSuccessListener(new Runnable(jSONObject2) {
                /* class com.oculus.panelapp.assistant.$$Lambda$ExpandedAttentionSystem$ztZc0s3Cz6WNYEchA8RwQeukBY */
                private final /* synthetic */ JSONObject f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ExpandedAttentionSystem.this.lambda$onApplyJson$33$ExpandedAttentionSystem(this.f$1);
                }
            }));
            this.mAnimManager.enqueueFade(contentView, true);
            this.mAnimManager.playPendingAnimations("Change Content Exp Attn System");
        }
    }

    public /* synthetic */ void lambda$onApplyJson$33$ExpandedAttentionSystem(JSONObject jSONObject) {
        try {
            super.onApplyJson(jSONObject);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse: " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public void onHide(Runnable runnable) {
        this.mAnimManager.clearPendingAnimations();
        this.mAnimManager.enqueueFade(this.mView, false).addListener(new AnimSuccessListener(new Runnable(runnable) {
            /* class com.oculus.panelapp.assistant.$$Lambda$ExpandedAttentionSystem$5ETdRseLWM22dnP6NrKvVujIwAA */
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ExpandedAttentionSystem.this.lambda$onHide$34$ExpandedAttentionSystem(this.f$1);
            }
        }));
        this.mAnimManager.playPendingAnimations("Hide Exp Attn System");
    }

    public /* synthetic */ void lambda$onHide$34$ExpandedAttentionSystem(Runnable runnable) {
        super.onHide(runnable);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog, com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public void show() {
        this.mAnimManager.cancel();
        super.show();
    }

    public void show(JSONObject jSONObject) throws JSONException {
        showAttentionSystem(jSONObject);
        applyJson(jSONObject);
    }

    private void updateLabel(TextView textView, JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            String string = jSONObject.getString(str);
            textView.setVisibility(TextUtils.isEmpty(string) ? 8 : 0);
            textView.setAlpha(1.0f);
            textView.setText(string);
        }
    }

    public void setStateIcon(JSONObject jSONObject) throws JSONException {
        this.mStateIcon.applyJson(this, jSONObject);
    }

    public void setStateIconPadding(int i) {
        this.mStateIcon.setPadding(i, i, i, i);
    }

    private void applyAttentionsSystemData(JSONObject jSONObject) throws JSONException {
        updateLabel(this.mLabel, jSONObject, "message");
        updateLabel(this.mSubLabel, jSONObject, AttentionSystemContract.KEY_SUBMESSAGE);
        setStateIconPadding(jSONObject.optInt("state-icon-padding", getContext().getResources().getDimensionPixelSize(R.dimen.state_icon_padding)));
        if (jSONObject.has("state-icon")) {
            setStateIcon(jSONObject.getJSONObject("state-icon"));
        }
    }

    public void showAttentionSystem(JSONObject jSONObject) throws JSONException {
        applyAttentionsSystemData(jSONObject);
    }
}
