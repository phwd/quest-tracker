package com.oculus.panelapp.assistant.attentionui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AssistantUiLifecycleContract;
import com.oculus.assistant.service.api.panel.AttentionSystemContract;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.assistant.AssistantPanelApp;
import com.oculus.panelapp.assistant.R;
import com.oculus.panelapp.assistant.animation.AnimationSetManager;
import com.oculus.panelapp.assistant.animation.listeners.AnimStartListener;
import com.oculus.panelapp.assistant.animation.listeners.AnimSuccessListener;
import com.oculus.panelapp.assistant.config.AttentionSystemConfig;
import com.oculus.panelapp.assistant.config.BroadcastConfig;
import com.oculus.panelapp.assistant.dialogs.ISurface;
import com.oculus.panelapp.assistant.dialogs.components.BaseComponent;
import com.oculus.panelapp.assistant.dialogs.components.LinearLayoutComponent;
import com.oculus.panelapp.assistant.dialogs.components.RemoteImageViewComponent;
import com.oculus.panelapp.assistant.dialogs.components.TextComponent;
import org.json.JSONException;
import org.json.JSONObject;

public class AssistantAttentionView extends FrameLayout implements ISurface {
    private final String TAG = AssistantAttentionView.class.getSimpleName();
    private Integer defaultIcon;
    private AnimationSetManager mAnimManager = new AnimationSetManager();
    private AssistantPanelApp mApp;
    private OCButton mClose;
    private OnCloseClickedListener mCloseListener;
    private FrameLayout mContentContainer;
    private boolean mHasShownImage;
    private OnHiddenListener mHiddenListener;
    private OnHidingListener mHidingListener;
    private RemoteImageViewComponent mIcon;
    private String mId;
    private AttentionSystemConfig mLastShownConfig;
    private OnShowMessageStartListener mOnShowMessageStartListener;
    private OnShownListener mOnShownListener;
    private AnimSuccessListener mOnShownListenerHandler = new AnimSuccessListener(new Runnable() {
        /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$tdw2SBvFgZ2LL5qKlGtMumqawWw */

        public final void run() {
            AssistantAttentionView.this.lambda$new$10$AssistantAttentionView();
        }
    });
    private View mTextContent;
    private TextComponent mTextContentMessage;
    private TextComponent mTextContentSubMessage;
    private View mViewContainer;

    public interface OnCloseClickedListener {
        void onCloseClicked();
    }

    public interface OnHiddenListener {
        void onHidden();
    }

    public interface OnHidingListener {
        void onHiding();
    }

    public interface OnShowMessageStartListener {
        void onShowMessageStart();
    }

    public interface OnShownListener {
        void onShown(String str);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public String getSurfaceType() {
        return AssistantDialogContract.DialogTypes.ATTENTION_SYSTEM_SURFACE;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public long getVisibleDuration() {
        return 0;
    }

    private AnimSuccessListener createHiddenHandler(BroadcastConfig broadcastConfig) {
        return new AnimSuccessListener(new Runnable(broadcastConfig) {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$rY6pdcr2OZHUe80m0pVXRRm1YD0 */
            private final /* synthetic */ BroadcastConfig f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantAttentionView.this.lambda$createHiddenHandler$8$AssistantAttentionView(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$createHiddenHandler$8$AssistantAttentionView(BroadcastConfig broadcastConfig) {
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_HIDDEN, this, broadcastConfig);
        OnHiddenListener onHiddenListener = this.mHiddenListener;
        if (onHiddenListener != null) {
            onHiddenListener.onHidden();
        }
    }

    private AnimStartListener createHidingHandler(BroadcastConfig broadcastConfig) {
        return new AnimStartListener(new Runnable(broadcastConfig) {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$3ysF9JY2yjFnJha5wUtzGbhyr4 */
            private final /* synthetic */ BroadcastConfig f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantAttentionView.this.lambda$createHidingHandler$9$AssistantAttentionView(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$createHidingHandler$9$AssistantAttentionView(BroadcastConfig broadcastConfig) {
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_HIDING, this, broadcastConfig);
        OnHidingListener onHidingListener = this.mHidingListener;
        if (onHidingListener != null) {
            onHidingListener.onHiding();
        }
    }

    public /* synthetic */ void lambda$new$10$AssistantAttentionView() {
        OnShownListener onShownListener = this.mOnShownListener;
        if (onShownListener != null) {
            onShownListener.onShown(this.mId);
        }
    }

    public AssistantAttentionView(Context context) {
        super(context);
        init(context);
    }

    public AssistantAttentionView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public AssistantAttentionView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public AssistantAttentionView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public void initialize(AssistantPanelApp assistantPanelApp) {
        this.mApp = assistantPanelApp;
        this.mClose.setEventHandler(this.mApp);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public String getSurfaceId() {
        return this.mId;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public AssistantPanelApp getApp() {
        return this.mApp;
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.attn_system_view, this);
        this.mViewContainer = findViewById(R.id.assistant_attention_view);
        this.mContentContainer = (FrameLayout) findViewById(R.id.content_container);
        this.mIcon = (RemoteImageViewComponent) findViewById(R.id.icon);
        this.mClose = (OCButton) findViewById(R.id.close);
        this.mClose.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$ugASdc46lsDtvmIjSLfJqw3CFlY */

            public final void onClick(View view) {
                AssistantAttentionView.this.lambda$init$11$AssistantAttentionView(view);
            }
        });
        this.mViewContainer.setScaleX(0.0f);
        this.mViewContainer.setScaleY(0.0f);
        this.mClose.setAlpha(0.0f);
        this.mClose.setVisibility(8);
        this.mContentContainer.setAlpha(0.0f);
    }

    public /* synthetic */ void lambda$init$11$AssistantAttentionView(View view) {
        OnCloseClickedListener onCloseClickedListener = this.mCloseListener;
        if (onCloseClickedListener != null) {
            onCloseClickedListener.onCloseClicked();
            hide();
        }
    }

    public void setOnShowMessageStartListener(OnShowMessageStartListener onShowMessageStartListener) {
        this.mOnShowMessageStartListener = onShowMessageStartListener;
    }

    public void setOnCloseClickedListener(OnCloseClickedListener onCloseClickedListener) {
        this.mCloseListener = onCloseClickedListener;
    }

    public void setOnHiddenListener(OnHiddenListener onHiddenListener) {
        this.mHiddenListener = onHiddenListener;
    }

    public void setOnHidingListener(OnHidingListener onHidingListener) {
        this.mHidingListener = onHidingListener;
    }

    private void applyJson(BaseComponent baseComponent, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                baseComponent.applyJson(this, jSONObject);
            } catch (JSONException e) {
                showJsonError(e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateIcon */
    public void lambda$enqueueShowContent$14$AssistantAttentionView(AttentionSystemConfig attentionSystemConfig) {
        if (attentionSystemConfig.getIcon() != null) {
            this.mHasShownImage = true;
            applyJson(this.mIcon, attentionSystemConfig.getIcon());
        } else if (!this.mHasShownImage) {
            this.mHasShownImage = true;
            applyDefaultIcon();
        }
    }

    public void applyDefaultIcon() {
        this.mIcon.setImageResourceFromProvider(AttentionSystemContract.DEFAULT_ICON_NAME);
    }

    private void enqueueShowContainer(AttentionSystemConfig attentionSystemConfig) {
        this.mAnimManager.enqueueContinueFadeAndScale(this.mViewContainer, true).addListener(new AnimStartListener(new Runnable(attentionSystemConfig) {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$I80huMB9lhXkacBctjjtZd9RDJ0 */
            private final /* synthetic */ AttentionSystemConfig f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantAttentionView.this.lambda$enqueueShowContainer$12$AssistantAttentionView(this.f$1);
            }
        }));
        this.mAnimManager.enqueueViewWidthRes(this.mViewContainer, R.dimen.attention_system_width, R.dimen.attention_system_width);
    }

    public /* synthetic */ void lambda$enqueueShowContainer$12$AssistantAttentionView(AttentionSystemConfig attentionSystemConfig) {
        this.mLastShownConfig = attentionSystemConfig;
        OnShowMessageStartListener onShowMessageStartListener = this.mOnShowMessageStartListener;
        if (onShowMessageStartListener != null) {
            onShowMessageStartListener.onShowMessageStart();
        }
    }

    private void enqueueHideContainer(long j, BroadcastConfig broadcastConfig) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(50L);
        ofFloat.addListener(createHidingHandler(broadcastConfig));
        this.mAnimManager.enqueue(ofFloat);
        ofFloat.setStartDelay(j);
        AnimationSetManager animationSetManager = this.mAnimManager;
        animationSetManager.enqueuePlayAnimationsTogether(animationSetManager.createContinueFade(this.mContentContainer, false), this.mAnimManager.createContinueFade(this.mClose, false));
        this.mAnimManager.enqueueViewWidthRes(this.mViewContainer, R.dimen.attention_system_collapsed_size, R.dimen.attention_system_width);
        this.mAnimManager.enqueueFadeAndScale(this.mViewContainer, false).addListener(createHiddenHandler(broadcastConfig));
    }

    private void enqueueShowContent(AttentionSystemConfig attentionSystemConfig) {
        Animator animator;
        if (!attentionSystemConfig.shouldAnimateContentUpdates() || this.mContentContainer.getChildCount() <= 0) {
            AnimationSetManager animationSetManager = this.mAnimManager;
            animator = animationSetManager.enqueuePlayAnimationsTogether(animationSetManager.createContinueFade(this.mContentContainer, true), this.mAnimManager.createContinueFade(this.mClose, attentionSystemConfig.isCloseVisible()));
        } else {
            this.mAnimManager.enqueueContinueFade(this.mContentContainer, false);
            AnimationSetManager animationSetManager2 = this.mAnimManager;
            animator = animationSetManager2.enqueuePlayAnimationsTogether(animationSetManager2.createFade(this.mContentContainer, true), this.mAnimManager.createFade(this.mClose, attentionSystemConfig.isCloseVisible()));
        }
        animator.addListener(this.mOnShownListenerHandler);
        animator.addListener(new AnimStartListener(new Runnable(attentionSystemConfig) {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$xMZbOKWt7avEHsXNEnc2gtQawZE */
            private final /* synthetic */ AttentionSystemConfig f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantAttentionView.this.lambda$enqueueShowContent$13$AssistantAttentionView(this.f$1);
            }
        }));
        animator.addListener(new AnimStartListener(new Runnable(attentionSystemConfig) {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$5T4RyCkPFfCYy1iqADhVniNuEWI */
            private final /* synthetic */ AttentionSystemConfig f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantAttentionView.this.lambda$enqueueShowContent$14$AssistantAttentionView(this.f$1);
            }
        }));
        animator.addListener(new AnimSuccessListener(new Runnable(attentionSystemConfig) {
            /* class com.oculus.panelapp.assistant.attentionui.$$Lambda$AssistantAttentionView$LgLJQsHn6gyo59aeWNKbvVNQXZg */
            private final /* synthetic */ AttentionSystemConfig f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantAttentionView.this.lambda$enqueueShowContent$15$AssistantAttentionView(this.f$1);
            }
        }));
    }

    public /* synthetic */ void lambda$enqueueShowContent$13$AssistantAttentionView(AttentionSystemConfig attentionSystemConfig) {
        try {
            onCreateContent(attentionSystemConfig);
        } catch (JSONException e) {
            showJsonError(e);
        }
    }

    public /* synthetic */ void lambda$enqueueShowContent$15$AssistantAttentionView(AttentionSystemConfig attentionSystemConfig) {
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_SHOWN, this, attentionSystemConfig.getBroadcastConfig());
    }

    private void onCreateContent(AttentionSystemConfig attentionSystemConfig) throws JSONException {
        this.mContentContainer.removeAllViews();
        this.mId = attentionSystemConfig.getId();
        if (attentionSystemConfig.hasTextContent()) {
            onCreateTextContent(attentionSystemConfig);
        } else {
            onCreateSurfaceContent(attentionSystemConfig);
        }
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_CREATED, this, attentionSystemConfig.getBroadcastConfig());
    }

    private void onCreateTextContent(AttentionSystemConfig attentionSystemConfig) {
        if (this.mTextContent == null) {
            this.mTextContent = LayoutInflater.from(getContext()).inflate(R.layout.attn_system_text, (ViewGroup) null);
            this.mTextContentMessage = (TextComponent) this.mTextContent.findViewById(R.id.label);
            this.mTextContentSubMessage = (TextComponent) this.mTextContent.findViewById(R.id.sub_label);
            this.mTextContentMessage.setTextStyle(R.style.attention_system_label);
            this.mTextContentSubMessage.setTextStyle(R.style.attention_system_sub_label);
        }
        updateMessage(this.mTextContentMessage, attentionSystemConfig.getMessage());
        updateMessage(this.mTextContentSubMessage, attentionSystemConfig.getSubMessage());
        this.mContentContainer.addView(this.mTextContent);
    }

    private void updateMessage(TextComponent textComponent, String str) {
        if (TextUtils.isEmpty(str)) {
            textComponent.setVisibility(8);
            return;
        }
        textComponent.setVisibility(0);
        textComponent.setText(str);
    }

    private void onCreateSurfaceContent(AttentionSystemConfig attentionSystemConfig) throws JSONException {
        LinearLayoutComponent linearLayoutComponent = (LinearLayoutComponent) LayoutInflater.from(getContext()).inflate(R.layout.layout_content, (ViewGroup) null);
        linearLayoutComponent.initialize(this);
        linearLayoutComponent.addComponents(attentionSystemConfig.getContent());
        this.mContentContainer.addView(linearLayoutComponent);
    }

    public void showAttentionSystem(JSONObject jSONObject) throws JSONException {
        AttentionSystemConfig create = AttentionSystemConfig.create(jSONObject);
        this.mApp.setAttentionSystemHitTesting(create.isHitTestable());
        enqueueShowContainer(create);
        enqueueShowContent(create);
        enqueueHideContainer((long) create.getDuration(), create.getBroadcastConfig());
        this.mAnimManager.playPendingAnimations("Show");
    }

    public void hide() {
        BroadcastConfig broadcastConfig;
        AttentionSystemConfig attentionSystemConfig = this.mLastShownConfig;
        if (attentionSystemConfig != null) {
            broadcastConfig = attentionSystemConfig.getBroadcastConfig();
        } else {
            broadcastConfig = new BroadcastConfig();
        }
        enqueueHideContainer(0, broadcastConfig);
        this.mAnimManager.playPendingAnimations("Hide");
    }

    public void destroy() {
        this.mAnimManager.cancel();
        this.mOnShowMessageStartListener = null;
        this.mCloseListener = null;
        this.mHiddenListener = null;
        this.mOnShownListener = null;
    }

    public void setDebuggingEnabled(boolean z) {
        this.mAnimManager.setDebuggingEnabled(z);
    }

    private void showJsonError(JSONException jSONException) {
        String str = this.TAG;
        Log.e(str, "Unable to show content: " + jSONException.getMessage());
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public BroadcastConfig getBroadcastConfig() {
        AttentionSystemConfig attentionSystemConfig = this.mLastShownConfig;
        return attentionSystemConfig != null ? attentionSystemConfig.getBroadcastConfig() : new BroadcastConfig();
    }
}
