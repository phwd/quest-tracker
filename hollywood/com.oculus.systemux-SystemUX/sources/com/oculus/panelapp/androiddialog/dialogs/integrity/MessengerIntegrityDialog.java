package com.oculus.panelapp.androiddialog.dialogs.integrity;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
import com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.BlockListsGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockListGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbUnblockGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerBlockGraphQL;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerThreadParticipantsGraphQL;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class MessengerIntegrityDialog extends ConstraintLayout implements Dialog {
    private static final String ACTION_TYPE_PARAM = "action_type";
    private static final String ONLY_SHOW_BLOCKED_PARAM = "only_show_blocked";
    private static final String START_WITH_SELECTOR_PARAM = "start_with_selector";
    private static final String TAG = LoggingUtil.tag(MessengerIntegrityDialog.class);
    private static final String TARGET_ID_PARAM = "target_fbid";
    private static final String THREAD_KEY_PARAM = "thread_key";
    private static final String USER_ID_PARAM = "user_fbid";
    private String mAccessToken;
    private ActionType mActionType;
    private MessengerIntegrityDialogBinding mBinding;
    private final Context mContext;
    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
    private boolean mOnlyShowBlocked;
    private AndroidDialogPanelApp mPanelApp;
    private SocialLogger mSocialLogger;
    private boolean mStartWithSelector;
    private String mTargetFBID;
    private String mThreadKey;
    private String mUserFBID;
    private final MessengerIntegrityDialogViewModel mViewModel;

    public MessengerIntegrityDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mViewModel = new MessengerIntegrityDialogViewModel(context.getResources());
        Log.i(TAG, "Constructed MessengerBlockDialog.");
    }

    public void initialize(Application application, AndroidDialogPanelApp androidDialogPanelApp, MessengerIntegrityDialogBinding messengerIntegrityDialogBinding, SocialLogger socialLogger, JSONObject jSONObject) {
        this.mPanelApp = androidDialogPanelApp;
        this.mBinding = messengerIntegrityDialogBinding;
        this.mSocialLogger = socialLogger;
        this.mBinding.setViewModel(this.mViewModel);
        this.mBinding.setResources(getContext().getResources());
        FBAuthManager.initialize(application, AppIDs.MESSENGER_VR_FB_APP_ID, AppIDs.MESSENGER_VR_OC_APP_ID);
        initializeParams(jSONObject);
        try {
            this.mAccessToken = FBAuthManager.generateAccessToken();
            if (this.mStartWithSelector) {
                initializeParticipantList();
            } else if (this.mTargetFBID != null) {
                showBlockUnblockView();
                fetchName();
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch access token:", e);
        }
        initializeButtons();
        initializeBackButton();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        FBAuthManager.destroy();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        goBack();
        return true;
    }

    private void goBack() {
        if (!this.mStartWithSelector || this.mViewModel.getSelectorVisible()) {
            this.mPanelApp.closeDialog();
            return;
        }
        initializeParticipantList();
        this.mViewModel.setIsSelectorVisible(true);
    }

    private void initializeParams(JSONObject jSONObject) {
        String str = TAG;
        Log.d(str, "Initializing parameters: " + jSONObject.toString());
        try {
            if (jSONObject.has(START_WITH_SELECTOR_PARAM)) {
                this.mStartWithSelector = Boolean.valueOf(jSONObject.getString(START_WITH_SELECTOR_PARAM)).booleanValue();
                this.mViewModel.setIsSelectorVisible(this.mStartWithSelector);
            } else {
                Log.d(TAG, "Parameters do not include selector flag");
            }
            if (jSONObject.has("action_type")) {
                this.mActionType = ActionType.valueOf(jSONObject.getString("action_type"));
                this.mViewModel.setActionType(this.mActionType);
            } else {
                Log.d(TAG, "Parameters do not include action type flag");
            }
            if (jSONObject.has(ONLY_SHOW_BLOCKED_PARAM)) {
                this.mOnlyShowBlocked = Boolean.valueOf(jSONObject.getString(ONLY_SHOW_BLOCKED_PARAM)).booleanValue();
                this.mViewModel.setOnlyShowBlocked(this.mOnlyShowBlocked);
            } else {
                Log.d(TAG, "Parameters do not include only show blocked flag");
            }
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
            if (jSONObject.has(THREAD_KEY_PARAM)) {
                this.mThreadKey = jSONObject.getString(THREAD_KEY_PARAM);
            } else {
                Log.d(TAG, "Parameters do not include thread key");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error initializing parameters", e);
        }
    }

    private void initializeButtons() {
        this.mBinding.fbSectionButton.setEventHandler(this.mPanelApp);
        this.mBinding.messengerSectionButton.setEventHandler(this.mPanelApp);
    }

    private void initializeParticipantList() {
        MessengerThreadParticipantsGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, this.mThreadKey, new MessengerThreadParticipantsGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$pgiz7vLkroqmuqFrDi7uG8Na9dg */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerThreadParticipantsGraphQL.SuccessCallback
            public final void run(List list) {
                MessengerIntegrityDialog.this.lambda$initializeParticipantList$23$MessengerIntegrityDialog(list);
            }
        });
    }

    public /* synthetic */ void lambda$initializeParticipantList$23$MessengerIntegrityDialog(List list) {
        if (this.mOnlyShowBlocked) {
            BlockListsGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, new BlockListsGraphQL.SuccessCallback(list) {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$0wuDbQQ4OnNnwHnlFX2prsOEbRI */
                private final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.BlockListsGraphQL.SuccessCallback
                public final void callback(HashSet hashSet, HashSet hashSet2) {
                    MessengerIntegrityDialog.this.lambda$null$21$MessengerIntegrityDialog(this.f$1, hashSet, hashSet2);
                }
            });
        } else {
            UiThreadExecutor.getInstance().execute(new Runnable(list) {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$2jOWFjZR7XHQB8ZniDd9Oaa4lVw */
                private final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    MessengerIntegrityDialog.this.lambda$null$22$MessengerIntegrityDialog(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$null$21$MessengerIntegrityDialog(List list, HashSet hashSet, HashSet hashSet2) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MessengerThreadParticipant messengerThreadParticipant = (MessengerThreadParticipant) it.next();
            if (hashSet.contains(messengerThreadParticipant.getId()) || hashSet2.contains(messengerThreadParticipant.getId())) {
                arrayList.add(messengerThreadParticipant);
            }
        }
        UiThreadExecutor.getInstance().execute(new Runnable(arrayList) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$wjttt94A7fAHm7aJZbavxRXfZrI */
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MessengerIntegrityDialog.this.lambda$null$20$MessengerIntegrityDialog(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: initializeRecyclerView */
    public void lambda$null$22$MessengerIntegrityDialog(List<MessengerThreadParticipant> list) {
        this.mBinding.participantList.setAdapter(new ParticipantListAdapter(this.mContext, this.mPanelApp, this.mSocialLogger, this.mActionType, this.mThreadKey, this.mUserFBID, list, new ParticipantViewHolder.BlockSelectorClickCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$hRwedl2BMtpWYTHJvxuu0yPjSc */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder.BlockSelectorClickCallback
            public final void run(MessengerThreadParticipant messengerThreadParticipant) {
                MessengerIntegrityDialog.this.lambda$initializeRecyclerView$24$MessengerIntegrityDialog(messengerThreadParticipant);
            }
        }));
    }

    public /* synthetic */ void lambda$initializeRecyclerView$24$MessengerIntegrityDialog(MessengerThreadParticipant messengerThreadParticipant) {
        this.mTargetFBID = messengerThreadParticipant.getId();
        this.mViewModel.setTargetName(messengerThreadParticipant.getName());
        showBlockUnblockView();
    }

    private void initializeBackButton() {
        this.mBinding.backButton.setEventHandler(this.mPanelApp);
        this.mBinding.backButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$AXSn2SsTIshAxpZ9saaKtmMQ6J0 */

            public final void onClick(View view) {
                MessengerIntegrityDialog.this.lambda$initializeBackButton$25$MessengerIntegrityDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeBackButton$25$MessengerIntegrityDialog(View view) {
        goBack();
        this.mSocialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_BACK, SurfaceType.MESSENGER_INTEGRITY);
    }

    private void fetchName() {
        FetchNameGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, this.mTargetFBID, new FetchNameGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$isA5zxR02iORIwilOUddIdUNt8g */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL.SuccessCallback
            public final void callback(String str) {
                MessengerIntegrityDialog.this.lambda$fetchName$27$MessengerIntegrityDialog(str);
            }
        }, new FetchNameGraphQL.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$0NULURIRR46bl1xYd8bMJEzEeM */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL.FailureCallback
            public final void callback() {
                MessengerIntegrityDialog.this.lambda$fetchName$31$MessengerIntegrityDialog();
            }
        });
    }

    public /* synthetic */ void lambda$fetchName$27$MessengerIntegrityDialog(String str) {
        UiThreadExecutor.getInstance().execute(new Runnable(str) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$7F35HGEy_OyEhZIHcJSwqX6slrE */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MessengerIntegrityDialog.this.lambda$null$26$MessengerIntegrityDialog(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$26$MessengerIntegrityDialog(String str) {
        this.mViewModel.setTargetName(str);
    }

    public /* synthetic */ void lambda$fetchName$31$MessengerIntegrityDialog() {
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$EF0FmgG6YmIpgRyE9STI5A4c2o */

            public final void run() {
                MessengerIntegrityDialog.this.lambda$null$30$MessengerIntegrityDialog();
            }
        });
    }

    public /* synthetic */ void lambda$null$30$MessengerIntegrityDialog() {
        FbBlockListGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, new FbBlockListGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$VND1mjaIJuwGEfEO77zM68d6lM */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockListGraphQL.SuccessCallback
            public final void callback(HashMap hashMap) {
                MessengerIntegrityDialog.this.lambda$null$29$MessengerIntegrityDialog(hashMap);
            }
        });
    }

    public /* synthetic */ void lambda$null$29$MessengerIntegrityDialog(HashMap hashMap) {
        UiThreadExecutor.getInstance().execute(new Runnable(hashMap) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$ACcVvxcVYq72JW93gopWyiWZbkk */
            private final /* synthetic */ HashMap f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MessengerIntegrityDialog.this.lambda$null$28$MessengerIntegrityDialog(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$28$MessengerIntegrityDialog(HashMap hashMap) {
        if (hashMap.containsKey(this.mTargetFBID)) {
            this.mViewModel.setTargetName((String) hashMap.get(this.mTargetFBID));
        } else {
            this.mViewModel.setTargetName(getResources().getString(R.string.block_dialog_placeholder_name));
        }
    }

    private void showBlockUnblockView() {
        this.mViewModel.setIsBlockUnblockViewLoading(true);
        BlockListsGraphQL.fetch(this.mOkHttpClient, this.mAccessToken, new BlockListsGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$l3sdyVWJYun9oG_0dZIZl8dBgc0 */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.BlockListsGraphQL.SuccessCallback
            public final void callback(HashSet hashSet, HashSet hashSet2) {
                MessengerIntegrityDialog.this.lambda$showBlockUnblockView$32$MessengerIntegrityDialog(hashSet, hashSet2);
            }
        });
        initializeFbSectionButton();
        initializeMessengerSectionButton();
        this.mViewModel.setIsSelectorVisible(false);
    }

    public /* synthetic */ void lambda$showBlockUnblockView$32$MessengerIntegrityDialog(HashSet hashSet, HashSet hashSet2) {
        setTargetFbBlocked(hashSet.contains(this.mTargetFBID));
        this.mViewModel.setTargetMessengerBlocked(hashSet2.contains(this.mTargetFBID));
        this.mViewModel.setIsBlockUnblockViewLoading(false);
    }

    private void initializeFbSectionButton() {
        if (this.mAccessToken == null || this.mUserFBID == null || this.mTargetFBID == null) {
            Log.e(TAG, "Error initializing fb section button due to null params");
        } else {
            this.mBinding.fbSectionButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$mQf44EGpQyURfZPtFpHAVI7Krs */

                public final void onClick(View view) {
                    MessengerIntegrityDialog.this.lambda$initializeFbSectionButton$37$MessengerIntegrityDialog(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeFbSectionButton$37$MessengerIntegrityDialog(View view) {
        if (this.mViewModel.getIsTargetFbBlocked()) {
            this.mSocialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK, SurfaceType.MESSENGER_INTEGRITY);
            this.mViewModel.setFbUnblockingInProgress(true);
            FbUnblockGraphQL.unblock(this.mOkHttpClient, this.mAccessToken, this.mUserFBID, this.mTargetFBID, new FbUnblockGraphQL.SuccessCallback() {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$IdZ42yiKNxVQb7jdrrb5LQbhuY */

                @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbUnblockGraphQL.SuccessCallback
                public final void run() {
                    MessengerIntegrityDialog.this.lambda$null$33$MessengerIntegrityDialog();
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$U35YSoNJ4WmTU2_PxcyaixnuRJM */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    MessengerIntegrityDialog.this.lambda$null$34$MessengerIntegrityDialog(th);
                }
            });
            return;
        }
        this.mSocialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK, SurfaceType.MESSENGER_INTEGRITY);
        this.mViewModel.setFbBlockingInProgress(true);
        FbBlockGraphQL.block(this.mOkHttpClient, this.mAccessToken, this.mUserFBID, this.mTargetFBID, new FbBlockGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$LxnW5AlSj5pm9SoWh3FyfAh6xD0 */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockGraphQL.SuccessCallback
            public final void run() {
                MessengerIntegrityDialog.this.lambda$null$35$MessengerIntegrityDialog();
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$V2NfHY4eMhnfRm7dUeIyMybym0 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                MessengerIntegrityDialog.this.lambda$null$36$MessengerIntegrityDialog(th);
            }
        });
    }

    public /* synthetic */ void lambda$null$33$MessengerIntegrityDialog() {
        this.mSocialLogger.logActionSuccess(ActionId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK, SurfaceType.MESSENGER_INTEGRITY);
        this.mViewModel.setFbUnblockingInProgress(false);
        setTargetFbBlocked(false);
    }

    public /* synthetic */ void lambda$null$34$MessengerIntegrityDialog(Throwable th) {
        this.mSocialLogger.logActionFailure(ActionId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK, SurfaceType.MESSENGER_INTEGRITY, th.getMessage());
        this.mViewModel.setFbUnblockingInProgress(false);
        this.mViewModel.setFailedToFbUnblock(true);
    }

    public /* synthetic */ void lambda$null$35$MessengerIntegrityDialog() {
        this.mSocialLogger.logActionSuccess(ActionId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK, SurfaceType.MESSENGER_INTEGRITY);
        this.mViewModel.setFbBlockingInProgress(false);
        setTargetFbBlocked(true);
    }

    public /* synthetic */ void lambda$null$36$MessengerIntegrityDialog(Throwable th) {
        this.mSocialLogger.logActionFailure(ActionId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK, SurfaceType.MESSENGER_INTEGRITY, th.getMessage());
        this.mViewModel.setFbBlockingInProgress(false);
        this.mViewModel.setFailedToFbBlock(true);
    }

    private void initializeMessengerSectionButton() {
        if (this.mAccessToken == null || this.mTargetFBID == null) {
            Log.e(TAG, "Error initializing messenger section button due to null params");
        } else {
            this.mBinding.messengerSectionButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$UIi2PdVVC4afJNYvd9xkyeud644 */

                public final void onClick(View view) {
                    MessengerIntegrityDialog.this.lambda$initializeMessengerSectionButton$42$MessengerIntegrityDialog(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeMessengerSectionButton$42$MessengerIntegrityDialog(View view) {
        if (this.mViewModel.getIsTargetMessengerBlocked()) {
            this.mSocialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK, SurfaceType.MESSENGER_INTEGRITY);
            this.mViewModel.setMessengerUnblockingInProgress(true);
            MessengerBlockGraphQL.unBlock(this.mOkHttpClient, this.mAccessToken, this.mTargetFBID, new MessengerBlockGraphQL.SuccessCallback() {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$ss2jYc6o0P1iizIrIiYjqL5Imd0 */

                @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerBlockGraphQL.SuccessCallback
                public final void callback(boolean z) {
                    MessengerIntegrityDialog.this.lambda$null$38$MessengerIntegrityDialog(z);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$RxPVkDFJCw1X4rY3bgCOQbhnNH0 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    MessengerIntegrityDialog.this.lambda$null$39$MessengerIntegrityDialog(th);
                }
            });
            return;
        }
        this.mSocialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK, SurfaceType.MESSENGER_INTEGRITY);
        this.mViewModel.setMessengerBlockingInProgress(true);
        MessengerBlockGraphQL.block(this.mOkHttpClient, this.mAccessToken, this.mTargetFBID, new MessengerBlockGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$jNBzsSyvoAdmw5SBuIKm9VGBhzA */

            @Override // com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerBlockGraphQL.SuccessCallback
            public final void callback(boolean z) {
                MessengerIntegrityDialog.this.lambda$null$40$MessengerIntegrityDialog(z);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$5Rd00XTNodeKA41nNcOe0RWlsE */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                MessengerIntegrityDialog.this.lambda$null$41$MessengerIntegrityDialog(th);
            }
        });
    }

    public /* synthetic */ void lambda$null$38$MessengerIntegrityDialog(boolean z) {
        this.mSocialLogger.logActionSuccess(ActionId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK, SurfaceType.MESSENGER_INTEGRITY);
        this.mViewModel.setMessengerUnblockingInProgress(false);
        this.mViewModel.setTargetMessengerBlocked(z);
    }

    public /* synthetic */ void lambda$null$39$MessengerIntegrityDialog(Throwable th) {
        this.mSocialLogger.logActionFailure(ActionId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK, SurfaceType.MESSENGER_INTEGRITY, th.getMessage());
        this.mViewModel.setMessengerUnblockingInProgress(false);
        this.mViewModel.setFailedToMessengerUnblock(true);
    }

    public /* synthetic */ void lambda$null$40$MessengerIntegrityDialog(boolean z) {
        this.mSocialLogger.logActionSuccess(ActionId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK, SurfaceType.MESSENGER_INTEGRITY);
        this.mViewModel.setMessengerBlockingInProgress(false);
        this.mViewModel.setTargetMessengerBlocked(z);
    }

    public /* synthetic */ void lambda$null$41$MessengerIntegrityDialog(Throwable th) {
        this.mSocialLogger.logActionFailure(ActionId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK, ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK, SurfaceType.MESSENGER_INTEGRITY, th.getMessage());
        this.mViewModel.setMessengerBlockingInProgress(false);
        this.mViewModel.setFailedToMessengerBlock(true);
    }

    private void setTargetFbBlocked(boolean z) {
        UiThreadExecutor.getInstance().execute(new Runnable(z) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$MessengerIntegrityDialog$Q9KUHYDRgi0SBwgsrPaCEsQdFY */
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MessengerIntegrityDialog.this.lambda$setTargetFbBlocked$43$MessengerIntegrityDialog(this.f$1);
            }
        });
        this.mViewModel.setTargetFbBlocked(z);
    }

    public /* synthetic */ void lambda$setTargetFbBlocked$43$MessengerIntegrityDialog(boolean z) {
        if (z) {
            getTargetFbBlockedConstraintSet().applyTo(this.mBinding.view);
        } else {
            getTargetNotFbBlockedConstraintSet().applyTo(this.mBinding.view);
        }
    }

    private ConstraintSet getTargetFbBlockedConstraintSet() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mBinding.view);
        constraintSet.connect(R.id.fb_section_button, 3, R.id.fb_unblock_messenger_still_blocked_explanation, 4);
        return constraintSet;
    }

    private ConstraintSet getTargetNotFbBlockedConstraintSet() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mBinding.view);
        constraintSet.connect(R.id.fb_section_button, 3, R.id.fb_block_messenger_also_blocked_explanation, 4);
        return constraintSet;
    }
}
