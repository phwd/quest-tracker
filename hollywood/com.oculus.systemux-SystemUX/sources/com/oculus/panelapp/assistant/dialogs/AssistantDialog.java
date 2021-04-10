package com.oculus.panelapp.assistant.dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AssistantUiLifecycleContract;
import com.oculus.assistant.service.api.panel.AttentionSystemContract;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.assistant.AssistantPanelApp;
import com.oculus.panelapp.assistant.R;
import com.oculus.panelapp.assistant.config.BroadcastConfig;
import com.oculus.panelapp.assistant.dialogs.components.BaseComponent;
import com.oculus.panelapp.assistant.dialogs.components.LinearLayoutComponent;
import com.oculus.panelapp.assistant.dialogs.components.RemoteImageViewComponent;
import com.oculus.panelapp.assistant.ui.HeroVideoView;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class AssistantDialog implements IAssistantDialog {
    private static final int DEFAULT_HEIGHT = -2;
    private static final int DEFAULT_WIDTH = 512;
    private static final String TAG = "AssistantDialog";
    private final View.OnClickListener mActionClick = new View.OnClickListener() {
        /* class com.oculus.panelapp.assistant.dialogs.$$Lambda$AssistantDialog$9uuR9Ln6gKiseyKUEc0WhSi88k */

        public final void onClick(View view) {
            AssistantDialog.this.lambda$new$28$AssistantDialog(view);
        }
    };
    protected final AssistantPanelApp mApp;
    private boolean mAutoClose;
    private boolean mAutoClosePrimary;
    private boolean mAutoCloseSecondary;
    private boolean mAutoCloseTertiary;
    private final RemoteImageViewComponent mBackButton;
    private BroadcastConfig mBroadcastConfig = new BroadcastConfig();
    private final View mButtonContainer;
    private final LinearLayoutComponent mContent;
    private final Context mContext;
    private final RemoteImageViewComponent mForwardButton;
    private int mHeight = -2;
    private final View mHeroContainer;
    private final ImageView mHeroImage;
    private String mId;
    private final OCButton mPrimary;
    private boolean mRemoveOnHide;
    private final View mScrollView;
    private final OCButton mSecondary;
    private final OCButton mTertiary;
    private final TextView mTitle;
    private final View mTitleContainer;
    private final HeroVideoView mVideoView;
    private View mView;
    private long mVisibleDuration;
    private int mWidth = 512;

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public String getSurfaceType() {
        return AssistantDialogContract.DialogTypes.ASSISTANT_SYSTEM_DIALOG;
    }

    public /* synthetic */ void lambda$new$28$AssistantDialog(View view) {
        sendActionClick("" + view.getTag());
    }

    public AssistantDialog(Context context, AssistantPanelApp assistantPanelApp, String str, String str2) {
        this.mApp = assistantPanelApp;
        this.mId = str;
        this.mContext = context;
        this.mView = onCreateView(context);
        this.mView.setClipToOutline(true);
        this.mContent = (LinearLayoutComponent) this.mView.findViewById(R.id.content);
        LinearLayoutComponent linearLayoutComponent = this.mContent;
        if (linearLayoutComponent != null) {
            linearLayoutComponent.setOrientation(1);
        }
        this.mScrollView = this.mView.findViewById(R.id.scrolling_content);
        this.mPrimary = (OCButton) this.mView.findViewById(R.id.primary);
        this.mPrimary.setOnClickListener(this.mActionClick);
        this.mPrimary.setEventHandler(assistantPanelApp);
        this.mSecondary = (OCButton) this.mView.findViewById(R.id.secondary);
        this.mSecondary.setOnClickListener(this.mActionClick);
        this.mSecondary.setEventHandler(assistantPanelApp);
        this.mTertiary = (OCButton) this.mView.findViewById(R.id.tertiary);
        this.mTertiary.setOnClickListener(this.mActionClick);
        this.mTertiary.setEventHandler(assistantPanelApp);
        this.mBackButton = (RemoteImageViewComponent) this.mView.findViewById(R.id.back);
        this.mBackButton.initialize(this);
        this.mBackButton.setTag("back");
        this.mBackButton.setOnClickListener(this.mActionClick);
        this.mBackButton.setImageResource(R.drawable.assistant_dialog_back_button_background);
        this.mBackButton.makeInteractive("back");
        this.mForwardButton = (RemoteImageViewComponent) this.mView.findViewById(R.id.forward);
        this.mForwardButton.initialize(this);
        this.mForwardButton.setTag("forward");
        this.mForwardButton.setOnClickListener(this.mActionClick);
        this.mForwardButton.setImageResource(R.drawable.assistant_dialog_forward_button_background);
        this.mForwardButton.makeInteractive("forward");
        this.mButtonContainer = this.mView.findViewById(R.id.button_container);
        this.mTitleContainer = findViewById(R.id.title_container);
        this.mTitle = (TextView) this.mView.findViewById(R.id.title);
        setTitle(str2);
        this.mHeroContainer = this.mView.findViewById(R.id.hero_slot);
        this.mHeroImage = (ImageView) this.mView.findViewById(R.id.hero_image);
        this.mVideoView = (HeroVideoView) this.mView.findViewById(R.id.hero_video);
        this.mVideoView.setErrorListener(new HeroVideoView.OnErrorListener() {
            /* class com.oculus.panelapp.assistant.dialogs.$$Lambda$AssistantDialog$bIl3KKG21LJuFnpllx5HpaSGyg */

            @Override // com.oculus.panelapp.assistant.ui.HeroVideoView.OnErrorListener
            public final void onError(Exception exc) {
                AssistantDialog.this.lambda$new$29$AssistantDialog(exc);
            }
        });
        this.mView.setLayoutParams(new FrameLayout.LayoutParams(this.mWidth, this.mHeight));
    }

    public /* synthetic */ void lambda$new$29$AssistantDialog(Exception exc) {
        this.mVideoView.setVisibility(4);
        String str = TAG;
        Log.e(str, "AssistantDialog: Could not play back video. " + exc.getMessage());
    }

    public static IAssistantDialog createDialog(Context context, AssistantPanelApp assistantPanelApp, JSONObject jSONObject) throws JSONException {
        AssistantDialog assistantDialog = new AssistantDialog(context, assistantPanelApp, jSONObject.optString("id", UUID.randomUUID().toString()), jSONObject.optString("title", ""));
        assistantDialog.applyJson(jSONObject);
        return assistantDialog;
    }

    /* access modifiers changed from: protected */
    public View onCreateView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.dialog, (ViewGroup) null, false);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public BroadcastConfig getBroadcastConfig() {
        return this.mBroadcastConfig;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public long getVisibleDuration() {
        return this.mVisibleDuration;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mTitle.setText(charSequence);
    }

    public CharSequence getTitle() {
        return this.mTitle.getText();
    }

    public void setPrimaryButton(String str, String str2) {
        this.mPrimary.setVisibility(0);
        this.mPrimary.setText(str2);
        this.mPrimary.setTag(str);
    }

    public void setSecondaryButton(String str, String str2) {
        this.mSecondary.setVisibility(0);
        this.mSecondary.setText(str2);
        this.mSecondary.setTag(str);
    }

    public void setTertiaryButton(String str, String str2) {
        this.mTertiary.setVisibility(0);
        this.mTertiary.setText(str2);
        this.mTertiary.setTag(str);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public String getSurfaceId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    /* access modifiers changed from: protected */
    public View getContentView() {
        return this.mContent;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void setBackButtonEnabled(boolean z) {
        this.mBackButton.setVisibility(z ? 0 : 8);
    }

    public void setForwardButtonEnabled(boolean z) {
        this.mForwardButton.setVisibility(z ? 0 : 8);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void show() {
        this.mApp.addView(this.mView);
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_SHOWN, this, this.mBroadcastConfig);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void hide(Runnable runnable) {
        onHiding();
        onHide(new Runnable(runnable) {
            /* class com.oculus.panelapp.assistant.dialogs.$$Lambda$AssistantDialog$8ssmpr6j19cmkJ2XBik61BhVGHg */
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantDialog.this.lambda$hide$30$AssistantDialog(this.f$1);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onHide(Runnable runnable) {
        this.mApp.removeView(this.mView);
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public void onHiding() {
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_HIDING, this, this.mBroadcastConfig);
    }

    /* access modifiers changed from: protected */
    /* renamed from: onHideCompleted */
    public void lambda$hide$30$AssistantDialog(Runnable runnable) {
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_HIDDEN, this, this.mBroadcastConfig);
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public void onSurfaceCreated() {
        this.mApp.sendLifecycleEvent(AssistantUiLifecycleContract.SurfaceEvents.SURFACE_CREATED, this, this.mBroadcastConfig);
    }

    public void closeDialog() {
        this.mApp.lambda$null$5$AssistantPanelApp(this);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public AssistantPanelApp getApp() {
        return this.mApp;
    }

    public void addView(View view) {
        LinearLayoutComponent linearLayoutComponent = this.mContent;
        if (linearLayoutComponent != null) {
            linearLayoutComponent.addChildView(view);
            return;
        }
        throw new IllegalStateException("Child controls not supported.");
    }

    public void removeView(View view) {
        LinearLayoutComponent linearLayoutComponent = this.mContent;
        if (linearLayoutComponent != null) {
            linearLayoutComponent.removeView(view);
        }
    }

    public Context getContext() {
        return this.mApp.getContext();
    }

    public void applyJson(JSONObject jSONObject) throws JSONException {
        this.mBroadcastConfig.applyJson(jSONObject);
        onApplyJson(jSONObject);
        onSurfaceCreated();
    }

    /* access modifiers changed from: protected */
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        boolean z;
        boolean z2;
        Context context = getContext();
        setId(jSONObject.optString("id", this.mId));
        this.mVisibleDuration = jSONObject.optLong(AttentionSystemContract.KEY_DURATION, 0);
        if (jSONObject.has("primary")) {
            setPrimaryButton("primary", jSONObject.getString("primary"));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("secondary")) {
            setSecondaryButton("secondary", jSONObject.getString("secondary"));
            z = true;
        }
        if (jSONObject.has(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY)) {
            setTertiaryButton(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY, jSONObject.getString(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY));
            z = true;
        }
        View view = this.mButtonContainer;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
        boolean optBoolean = jSONObject.optBoolean("back", false);
        setBackButtonEnabled(optBoolean);
        boolean optBoolean2 = jSONObject.optBoolean("forward", false);
        setForwardButtonEnabled(optBoolean2);
        boolean z3 = optBoolean || optBoolean2;
        setRemoveOnHide(jSONObject.optBoolean(AssistantDialogContract.Dialog.REMOVE_ON_HIDE, true));
        this.mWidth = BaseComponent.optDimension(context, jSONObject, "width", 512);
        this.mHeight = BaseComponent.optDimension(context, jSONObject, "height", -2);
        this.mView.setLayoutParams(new FrameLayout.LayoutParams(this.mWidth, this.mHeight));
        if (jSONObject.has(AssistantDialogContract.Dialog.HERO)) {
            Uri parse = Uri.parse(jSONObject.getString(AssistantDialogContract.Dialog.HERO));
            this.mHeroImage.setVisibility(0);
            this.mHeroImage.setImageBitmap(getBitmapFromAsset(parse.getAuthority(), parse.getLastPathSegment()));
            z2 = true;
        } else {
            z2 = false;
        }
        if (jSONObject.has(AssistantDialogContract.Dialog.VIDEO)) {
            this.mVideoView.setVisibility(0);
            this.mVideoView.play(jSONObject.getString(AssistantDialogContract.Dialog.VIDEO));
            z2 = true;
        }
        this.mHeroContainer.setVisibility(z2 ? 0 : 8);
        LinearLayoutComponent linearLayoutComponent = this.mContent;
        if (linearLayoutComponent != null) {
            linearLayoutComponent.initialize(this);
            this.mContent.clearComponents();
            this.mContent.addComponents(jSONObject);
        }
        if (!jSONObject.optBoolean(AssistantDialogContract.Container.USE_CONTENT_MARGINS, true)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mScrollView.getLayoutParams();
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
            this.mScrollView.setLayoutParams(layoutParams);
        }
        this.mAutoClose = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE, true);
        this.mAutoClosePrimary = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE_PRIMARY, this.mAutoClose);
        this.mAutoCloseSecondary = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE_SECONDARY, this.mAutoClose);
        this.mAutoCloseTertiary = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE_TERTIARY, this.mAutoClose);
        setTitle(jSONObject.optString("title", ""));
        if (this.mTitleContainer != null) {
            if (this.mTitle.getVisibility() == 0 || z2 || z3) {
                this.mTitleContainer.setVisibility(0);
            } else {
                this.mTitleContainer.setVisibility(8);
            }
        }
        if (jSONObject.has(AssistantDialogContract.Container.CONTAINER_SETTINGS)) {
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            JSONObject jSONObject2 = jSONObject.getJSONObject(AssistantDialogContract.Container.CONTAINER_SETTINGS);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(BaseComponent.optDimension(context, jSONObject2, "width", -2), BaseComponent.optDimension(context, jSONObject2, "height", -2)));
            if (jSONObject2.optBoolean(AssistantDialogContract.Container.CLOSE_ON_CLICK_OUTSIDE, true)) {
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.assistant.dialogs.$$Lambda$AssistantDialog$ncFNA9eYAs2LVC3PMWlapYxwJE */

                    public final void onClick(View view) {
                        AssistantDialog.this.lambda$onApplyJson$31$AssistantDialog(view);
                    }
                });
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mView.getLayoutParams();
            layoutParams2.gravity = BaseComponent.parseGravity(jSONObject2);
            this.mView.setLayoutParams(layoutParams2);
            frameLayout.addView(this.mView);
            this.mView = frameLayout;
        }
    }

    public /* synthetic */ void lambda$onApplyJson$31$AssistantDialog(View view) {
        closeDialog();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0025 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0068 A[SYNTHETIC, Splitter:B:25:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap getBitmapFromAsset(java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.dialogs.AssistantDialog.getBitmapFromAsset(java.lang.String, java.lang.String):android.graphics.Bitmap");
    }

    private void setRemoveOnHide(boolean z) {
        this.mRemoveOnHide = z;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public boolean shouldRemoveOnHide() {
        return this.mRemoveOnHide;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public int getWidth() {
        return this.mWidth;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public int getHeight() {
        return this.mHeight;
    }

    public View findViewById(int i) {
        return this.mView.findViewById(i);
    }

    public void sendActionClick(String str) {
        sendActionClick(str, null);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void sendActionClick(String str, Bundle bundle) {
        char c;
        switch (str.hashCode()) {
            case -1174796206:
                if (str.equals(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -817598092:
                if (str.equals("secondary")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -314765822:
                if (str.equals("primary")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 94756344:
                if (str.equals("close")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c == 3) {
                        closeDialog();
                    } else if (this.mAutoClose) {
                        closeDialog();
                    }
                } else if (this.mAutoCloseTertiary) {
                    closeDialog();
                }
            } else if (this.mAutoCloseSecondary) {
                closeDialog();
            }
        } else if (this.mAutoClosePrimary) {
            closeDialog();
        }
        this.mApp.onDialogResult(this, this.mBroadcastConfig, str, bundle);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void getResultData(Bundle bundle) {
        int i = 0;
        while (true) {
            LinearLayoutComponent linearLayoutComponent = this.mContent;
            if (linearLayoutComponent != null && i < linearLayoutComponent.getChildCount()) {
                View childAt = this.mContent.getChildAt(i);
                if (childAt instanceof BaseComponent) {
                    ((BaseComponent) childAt).addResultData(bundle);
                }
                i++;
            } else {
                return;
            }
        }
    }
}
