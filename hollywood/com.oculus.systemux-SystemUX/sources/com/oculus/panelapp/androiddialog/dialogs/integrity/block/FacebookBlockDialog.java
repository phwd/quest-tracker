package com.oculus.panelapp.androiddialog.dialogs.integrity.block;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.fbauth.constants.AppIDs;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.FacebookBlockDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.BlockListsGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockListGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbUnblockGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookBlockDialog extends ConstraintLayout implements Dialog {
    private static final String TAG = LoggingUtil.tag(FacebookBlockDialog.class);
    private static final String TARGET_ID_PARAM = "target_fbid";
    private static final String TARGET_NAME_PARAM = "target_name";
    private static final String USER_ID_PARAM = "user_fbid";
    private String mAccessToken;
    private FacebookBlockDialogBinding mBinding;
    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
    private AndroidDialogPanelApp mPanelApp;
    private SocialLogger mSocialLogger;
    private String mTargetFBID;
    private String mUserFBID;
    private final FacebookBlockDialogViewModel mViewModel = new FacebookBlockDialogViewModel(getContext().getResources());

    public FacebookBlockDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "Constructed FacebookBlockDialog.");
    }

    public void initialize(Application application, AndroidDialogPanelApp androidDialogPanelApp, FacebookBlockDialogBinding facebookBlockDialogBinding, SocialLogger socialLogger, JSONObject jSONObject) {
        this.mPanelApp = androidDialogPanelApp;
        this.mSocialLogger = socialLogger;
        this.mBinding = facebookBlockDialogBinding;
        this.mBinding.setViewModel(this.mViewModel);
        initializeParams(jSONObject);
        FBAuthManager.initialize(application, AppIDs.MESSENGER_VR_FB_APP_ID, AppIDs.MESSENGER_VR_OC_APP_ID);
        if (this.mTargetFBID != null) {
            try {
                this.mAccessToken = FBAuthManager.generateAccessToken();
                fetchName();
                fetchBlockStatus();
                initializeFbBlockButton();
                initializeFbUnblockButton();
            } catch (IOException e) {
                Log.e(TAG, "Failed to fetch access token:", e);
            }
        }
        initializeBackButton();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        this.mViewModel.destroy();
        FBAuthManager.destroy();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        this.mPanelApp.closeDialog();
        return true;
    }

    private void initializeBackButton() {
        this.mBinding.backButton.setEventHandler(this.mPanelApp);
        this.mBinding.backButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$xW43akRjqEvYl6REZkySbHqW494 */

            public final void onClick(View view) {
                FacebookBlockDialog.this.lambda$initializeBackButton$45$FacebookBlockDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeBackButton$45$FacebookBlockDialog(View view) {
        this.mPanelApp.closeDialog();
        this.mSocialLogger.logButtonClick(ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_BACK, SurfaceType.FACEBOOK_BLOCK);
    }

    private void initializeParams(JSONObject jSONObject) {
        String str = TAG;
        Log.d(str, "Initializing parameters: " + jSONObject.toString());
        try {
            if (jSONObject.has(USER_ID_PARAM)) {
                this.mUserFBID = jSONObject.getString(USER_ID_PARAM);
            } else {
                Log.d(TAG, "Parameters do not include user fbid");
            }
            if (jSONObject.has(TARGET_ID_PARAM)) {
                this.mTargetFBID = jSONObject.getString(TARGET_ID_PARAM);
            } else {
                Log.d(TAG, "Parameters do not include target fbid");
            }
            if (jSONObject.has(TARGET_NAME_PARAM)) {
                this.mViewModel.setTargetName(jSONObject.getString(TARGET_NAME_PARAM));
            } else {
                Log.d(TAG, "Parameters do not include target name");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error initializing parameters", e);
        }
    }

    private void fetchBlockStatus() {
        BlockListsGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, new BlockListsGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$yPFWyNaBXkL1bNlG8E90tiAE78c */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.BlockListsGraphQL.SuccessCallback
            public final void callback(HashSet hashSet, HashSet hashSet2) {
                FacebookBlockDialog.this.lambda$fetchBlockStatus$47$FacebookBlockDialog(hashSet, hashSet2);
            }
        });
    }

    public /* synthetic */ void lambda$fetchBlockStatus$47$FacebookBlockDialog(HashSet hashSet, HashSet hashSet2) {
        UiThreadExecutor.getInstance().execute(new Runnable(hashSet) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$DqqpkiuWdkhNuOcm2LZd50Xgr3k */
            private final /* synthetic */ HashSet f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                FacebookBlockDialog.this.lambda$null$46$FacebookBlockDialog(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$46$FacebookBlockDialog(HashSet hashSet) {
        this.mViewModel.setIsTargetBlocked(hashSet.contains(this.mTargetFBID));
    }

    private void fetchName() {
        FetchNameGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, this.mTargetFBID, new FetchNameGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$d2aartxlWwkxDm83pgHI2SXSdvY */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL.SuccessCallback
            public final void callback(String str) {
                FacebookBlockDialog.this.lambda$fetchName$49$FacebookBlockDialog(str);
            }
        }, new FetchNameGraphQL.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$iasHfFf2PGqE1bgdaRYtuHR8eYs */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL.FailureCallback
            public final void callback() {
                FacebookBlockDialog.this.lambda$fetchName$53$FacebookBlockDialog();
            }
        });
    }

    public /* synthetic */ void lambda$fetchName$49$FacebookBlockDialog(String str) {
        UiThreadExecutor.getInstance().execute(new Runnable(str) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$O3N_7s2u_gwksG9HxzTQU7L5aDg */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                FacebookBlockDialog.this.lambda$null$48$FacebookBlockDialog(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$48$FacebookBlockDialog(String str) {
        this.mViewModel.setTargetName(str);
    }

    public /* synthetic */ void lambda$fetchName$53$FacebookBlockDialog() {
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$HabwJVJ20y0ms5y2Dhz7zbC2Q4 */

            public final void run() {
                FacebookBlockDialog.this.lambda$null$52$FacebookBlockDialog();
            }
        });
    }

    public /* synthetic */ void lambda$null$52$FacebookBlockDialog() {
        FbBlockListGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, new FbBlockListGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$WQw0DIXD8_0sz713sJKtJXYZdNg */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockListGraphQL.SuccessCallback
            public final void callback(HashMap hashMap) {
                FacebookBlockDialog.this.lambda$null$51$FacebookBlockDialog(hashMap);
            }
        });
    }

    public /* synthetic */ void lambda$null$51$FacebookBlockDialog(HashMap hashMap) {
        UiThreadExecutor.getInstance().execute(new Runnable(hashMap) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$0TQw8kPUyIMh3ZMzt0CYJ7qD8b8 */
            private final /* synthetic */ HashMap f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                FacebookBlockDialog.this.lambda$null$50$FacebookBlockDialog(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$50$FacebookBlockDialog(HashMap hashMap) {
        if (hashMap.containsKey(this.mTargetFBID)) {
            this.mViewModel.setTargetName((String) hashMap.get(this.mTargetFBID));
        } else {
            this.mViewModel.setTargetName(getResources().getString(R.string.block_dialog_placeholder_name));
        }
    }

    private void initializeFbBlockButton() {
        if (this.mAccessToken == null || this.mUserFBID == null || this.mTargetFBID == null) {
            Log.e(TAG, "Error initializing fb block button due to null params");
            return;
        }
        this.mBinding.fbBlockButtonActionSpace.actionSpaceSecondaryButton.setEventHandler(this.mPanelApp);
        this.mBinding.fbBlockButtonActionSpace.actionSpaceSecondaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$poBmQwMM9G_Ys0B_1fJpzonptg */

            public final void onClick(View view) {
                FacebookBlockDialog.this.lambda$initializeFbBlockButton$56$FacebookBlockDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeFbBlockButton$56$FacebookBlockDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_BLOCK, SurfaceType.FACEBOOK_BLOCK);
        this.mViewModel.setIsBlockingInProgress(true);
        FbBlockGraphQL.block(this.mOkHttpClient, this.mAccessToken, this.mUserFBID, this.mTargetFBID, new FbBlockGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$NcWjK3ZH3NZ0vOTSBdyaxs8IuFM */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockGraphQL.SuccessCallback
            public final void run() {
                FacebookBlockDialog.this.lambda$null$54$FacebookBlockDialog();
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$RFaMdm4V8Cri6WQjadH7kw11nAQ */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FacebookBlockDialog.this.lambda$null$55$FacebookBlockDialog(th);
            }
        });
    }

    public /* synthetic */ void lambda$null$54$FacebookBlockDialog() {
        this.mSocialLogger.logActionSuccess(ActionId.FACEBOOK_BLOCK_DIALOG_BLOCK, ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_BLOCK, SurfaceType.FACEBOOK_BLOCK);
        this.mViewModel.setIsBlockingInProgress(false);
        this.mViewModel.setIsTargetBlocked(true);
    }

    public /* synthetic */ void lambda$null$55$FacebookBlockDialog(Throwable th) {
        this.mSocialLogger.logActionFailure(ActionId.FACEBOOK_BLOCK_DIALOG_BLOCK, ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_BLOCK, SurfaceType.FACEBOOK_BLOCK, th.getMessage());
        this.mViewModel.setBlockFailed();
    }

    private void initializeFbUnblockButton() {
        if (this.mAccessToken == null || this.mUserFBID == null || this.mTargetFBID == null) {
            Log.e(TAG, "Error initializing fb unblock button due to null params");
            return;
        }
        this.mBinding.fbUnblockButtonActionSpace.actionSpaceSecondaryButton.setEventHandler(this.mPanelApp);
        this.mBinding.fbUnblockButtonActionSpace.actionSpaceSecondaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$1WX3FxktgqIJACyzmrA2389a02I */

            public final void onClick(View view) {
                FacebookBlockDialog.this.lambda$initializeFbUnblockButton$59$FacebookBlockDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeFbUnblockButton$59$FacebookBlockDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_UNBLOCK, SurfaceType.FACEBOOK_BLOCK);
        this.mViewModel.setIsUnblockingInProgress(true);
        FbUnblockGraphQL.unblock(this.mOkHttpClient, this.mAccessToken, this.mUserFBID, this.mTargetFBID, new FbUnblockGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$AI5Sazs8J2x0maWX9nkgMK63lU */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbUnblockGraphQL.SuccessCallback
            public final void run() {
                FacebookBlockDialog.this.lambda$null$57$FacebookBlockDialog();
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.block.$$Lambda$FacebookBlockDialog$43nde43T_UFRkIEWXRjDxkqYs */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FacebookBlockDialog.this.lambda$null$58$FacebookBlockDialog(th);
            }
        });
    }

    public /* synthetic */ void lambda$null$57$FacebookBlockDialog() {
        this.mSocialLogger.logActionSuccess(ActionId.FACEBOOK_BLOCK_DIALOG_UNBLOCK, ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_UNBLOCK, SurfaceType.FACEBOOK_BLOCK);
        this.mViewModel.setIsUnblockingInProgress(false);
        this.mViewModel.setIsTargetBlocked(false);
    }

    public /* synthetic */ void lambda$null$58$FacebookBlockDialog(Throwable th) {
        this.mSocialLogger.logActionFailure(ActionId.FACEBOOK_BLOCK_DIALOG_UNBLOCK, ClickEventButtonId.FACEBOOK_BLOCK_DIALOG_UNBLOCK, SurfaceType.FACEBOOK_BLOCK, th.getMessage());
        this.mViewModel.setIsUnblockingInProgress(false);
        this.mViewModel.setUnblockFailed();
    }
}
