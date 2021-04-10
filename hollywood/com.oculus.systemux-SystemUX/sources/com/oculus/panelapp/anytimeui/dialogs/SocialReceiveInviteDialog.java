package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.os.EnvironmentCompat;
import com.facebook.secure.logger.IntentLogger;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.horizon.PartyInfo;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialErrors;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.utils.ImageLoader;
import com.oculus.tablet.utils.PartiesBroadcastActions;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Objects;

public class SocialReceiveInviteDialog extends Dialog {
    public static final int DIALOG_ICON_IMAGE_DIAMETER = 72;
    public static final int DIALOG_ICON_IMAGE_PADDING = 4;
    private static final String FB_UPSELL_ACTION_PARAM = "accept_party_invite";
    private static final String FB_UPSELL_CONTAINER_PARAM = "system_ux_dialog";
    private static final String FB_UPSELL_MUST_INTERACT_PARAM = "true";
    private static final String FB_UPSELL_PRODUCT_PARAM = "party";
    private static final String FB_UPSELL_SOURCE_PARAM = "aui_party_dialog";
    private static final String TAG = LoggingUtil.tag(SocialReceiveInviteDialog.class);
    private String mActivityAppId;
    private String mActivityDeeplink;
    private String mActivityPackageName;
    private ConnectivityManager mConnection;
    private SystemDialogStandardLayout mDialogLayout;
    private boolean mFBLinked = false;
    @Nullable
    private AsyncQueryHandle mFetchLinkedAccountsInfoAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mFetchPartyInfoWithActivity;
    private SystemDialogStandardLayout mInternetErrorLayout;
    private String mInvitedPartyID;
    @Nullable
    private AsyncQueryHandle mJoinPartyAsyncQueryHandle;
    private final ProfilePictureHelper mProfilePictureHelper;
    private String mSource = EnvironmentCompat.MEDIA_UNKNOWN;
    private SystemDialogStandardLayout mUpsellLayout;

    public SocialReceiveInviteDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "SocialReceiveInviteDialog dialog constructed");
        this.mProfilePictureHelper = new ProfilePictureHelper(context);
        this.mConnection = (ConnectivityManager) context.getSystemService("connectivity");
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void initialize() {
        this.mDialogLayout = (SystemDialogStandardLayout) findViewById(R.id.social_receive_invite_dialog_layout);
        this.mUpsellLayout = (SystemDialogStandardLayout) findViewById(R.id.social_fb_link_upsell_dialog_layout);
        this.mInternetErrorLayout = (SystemDialogStandardLayout) findViewById(R.id.social_no_internet_connection_dialog_layout);
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void configure(String str) {
        if (!(this.mConnection.getActiveNetwork() != null)) {
            configureNoInternetContent();
        } else if (getPanelApp().isGKEnabled(Gatekeeper.AUI_PARTY_FB_UPSELL)) {
            configureBasedOnFBUpsellStatus(str);
        } else {
            configureDefaultContent(str);
        }
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void destroy() {
        clearJoinPartyAsyncQueryHandle();
        clearFetchPartyInfoWithActivity();
        clearFetchLinkedAccountsInfoAsyncQueryHandle();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJoinPartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mJoinPartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mJoinPartyAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchPartyInfoWithActivity() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchPartyInfoWithActivity;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchPartyInfoWithActivity = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchLinkedAccountsInfoAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchLinkedAccountsInfoAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchLinkedAccountsInfoAsyncQueryHandle = null;
        }
    }

    private void configureNoInternetContent() {
        this.mInternetErrorLayout.setVisibility(0);
        this.mInternetErrorLayout.setOnCloseListener(new SystemDialogStandardLayout.OnButtonClickedListener() {
            /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass1 */

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onAccept() {
                SocialReceiveInviteDialog.this.closeDialog();
            }

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onCancel() {
                SocialReceiveInviteDialog.this.closeDialog();
            }
        });
    }

    private void configureBasedOnFBUpsellStatus(final String str) {
        clearFetchLinkedAccountsInfoAsyncQueryHandle();
        this.mFetchLinkedAccountsInfoAsyncQueryHandle = HorizonContentProviderHelper.fetchLinkedAccountsInfoForViewer(getContext(), new HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback() {
            /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback
            public void onSuccess(LinkedAccountsInfo linkedAccountsInfo) {
                if (linkedAccountsInfo.isFbLinked()) {
                    SocialReceiveInviteDialog.this.configureDefaultContent(str);
                } else if (linkedAccountsInfo.isInsufficientTerms()) {
                    SocialReceiveInviteDialog.this.getPanelApp().actionNavigate(SystemUXRoute.PARTIES, "");
                } else {
                    SocialReceiveInviteDialog.this.configureFBUpsellContent();
                }
                SocialReceiveInviteDialog.this.clearFetchLinkedAccountsInfoAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialReceiveInviteDialog.this.getPanelApp().actionNavigate(SystemUXRoute.PARTIES, "");
                SocialReceiveInviteDialog.this.clearFetchLinkedAccountsInfoAsyncQueryHandle();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void configureFBUpsellContent() {
        logFBUpsellEvent(SocialPartyEvent.FB_LINK_UPSELL_DIALOG_OPEN);
        this.mUpsellLayout.setVisibility(0);
        this.mUpsellLayout.setOnCloseListener(new SystemDialogStandardLayout.OnButtonClickedListener() {
            /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass3 */

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onAccept() {
                SocialReceiveInviteDialog.this.logFBUpsellEvent(SocialPartyEvent.FB_LINK_UPSELL_LINK_CLICK);
                SocialBundleUtils.ShowUpsell(SocialReceiveInviteDialog.this.getPanelApp(), new UpsellLoggingParameters(SocialReceiveInviteDialog.FB_UPSELL_SOURCE_PARAM, "accept_party_invite", SocialReceiveInviteDialog.FB_UPSELL_CONTAINER_PARAM, SocialReceiveInviteDialog.this.mSource, "true", SocialReceiveInviteDialog.FB_UPSELL_PRODUCT_PARAM));
            }

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onCancel() {
                SocialReceiveInviteDialog.this.closeDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void configureDefaultContent(String str) {
        this.mDialogLayout.setOnCloseListener(new SystemDialogStandardLayout.OnButtonClickedListener() {
            /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass4 */

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onAccept() {
                SocialReceiveInviteDialog socialReceiveInviteDialog = SocialReceiveInviteDialog.this;
                socialReceiveInviteDialog.joinParty(socialReceiveInviteDialog.shouldLaunchActivityAsPrimaryCTA());
            }

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onMiddle() {
                SocialReceiveInviteDialog.this.joinParty(false);
            }

            @Override // com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.OnButtonClickedListener
            public void onCancel() {
                SocialReceiveInviteDialog.this.closeDialog();
            }
        });
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter("source");
        if (!TextUtils.isEmpty(queryParameter)) {
            this.mSource = queryParameter;
        }
        this.mInvitedPartyID = parse.getQueryParameter("party_id");
        String queryParameter2 = parse.getQueryParameter("sender_name");
        if (TextUtils.isEmpty(queryParameter2) || Objects.equals(queryParameter2, com.facebook.debug.log.LoggingUtil.NO_HASHCODE)) {
            queryParameter2 = parse.getQueryParameter("sender_alias");
        }
        setDialogSubtitle(queryParameter2);
        String queryParameter3 = parse.getQueryParameter("sender_photo");
        if (!TextUtils.isEmpty(queryParameter3) && !Objects.equals(queryParameter3, com.facebook.debug.log.LoggingUtil.NO_HASHCODE)) {
            setDialogIcon(queryParameter3);
        }
        fetchPartyData(this.mInvitedPartyID);
    }

    private void fetchPartyData(final String str) {
        clearFetchPartyInfoWithActivity();
        this.mFetchPartyInfoWithActivity = HorizonContentProviderHelper.fetchPartyInfoWithActivity(getContext(), str, new HorizonContentProviderHelper.FetchPartyInfoWithActivityCallback() {
            /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPartyInfoWithActivityCallback
            public void onSuccess(PartyInfo partyInfo) {
                SocialReceiveInviteDialog.this.mActivityDeeplink = partyInfo.inviteActivityDeeplink;
                SocialReceiveInviteDialog.this.mActivityPackageName = partyInfo.inviteActivityPackageName;
                SocialReceiveInviteDialog.this.mActivityAppId = partyInfo.inviteActivityAppId;
                SocialReceiveInviteDialog.this.updateDialogWithPartyInfo(partyInfo);
                SocialReceiveInviteDialog.this.mDialogLayout.setVisibility(0);
                SocialReceiveInviteDialog.this.clearFetchPartyInfoWithActivity();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                String str = SocialReceiveInviteDialog.TAG;
                Log.i(str, "Failed to receive party info for partyID: " + str);
                SocialReceiveInviteDialog.this.clearFetchPartyInfoWithActivity();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateDialogWithPartyInfo(final PartyInfo partyInfo) {
        setPartyMemberCountText(partyInfo.currentMembersCount + partyInfo.blockedCurrentMembersCount);
        setInvitedCountText((partyInfo.invitedUsersCount + partyInfo.blockedInvitedUsersCount) - 1);
        if (partyInfo.blockedCurrentMembersCount > 0) {
            ((TextView) findViewById(R.id.social_receive_invite_dialog_party_blocked_users_warning)).setVisibility(0);
        }
        if (shouldLaunchActivityAsPrimaryCTA()) {
            if (!TextUtils.isEmpty(partyInfo.inviteActivityTitle)) {
                TextView textView = (TextView) findViewById(R.id.social_receive_invite_dialog_activity_title);
                textView.setText(partyInfo.inviteActivityTitle);
                textView.setVisibility(0);
            }
            if (!TextUtils.isEmpty(partyInfo.inviteActivitySubtitle)) {
                TextView textView2 = (TextView) findViewById(R.id.social_receive_invite_dialog_activity_subtitle);
                textView2.setText(partyInfo.inviteActivitySubtitle);
                textView2.setVisibility(0);
            }
            if (!TextUtils.isEmpty(partyInfo.inviteActivityImageUri)) {
                final ImageView imageView = (ImageView) findViewById(R.id.social_receive_invite_dialog_activity_image);
                ImageLoader.getInstance(getContext()).loadImage(partyInfo.inviteActivityImageUri, new ImageLoader.ImageCallbacks() {
                    /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass6 */

                    @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                    public void onSuccess(Bitmap bitmap) {
                        imageView.setBackgroundDrawable(new BitmapDrawable(SocialReceiveInviteDialog.this.getResources(), bitmap));
                        imageView.getLayoutParams().height = bitmap.getHeight();
                        imageView.getLayoutParams().width = bitmap.getWidth();
                        imageView.setVisibility(0);
                    }

                    @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                    public void onFailure(Throwable th) {
                        String str = SocialReceiveInviteDialog.TAG;
                        Log.e(str, "Could not load image: " + partyInfo.inviteActivityImageUri);
                    }
                });
            }
            if (shouldLaunchActivityAsPrimaryCTA()) {
                findViewById(R.id.system_dialog_standard_layout_button_middle).setVisibility(0);
            }
        }
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.panelapp.anytimeui.dialogs.Dialog
    public boolean onBackButton() {
        closeDialog();
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void closeDialog() {
        if (this.mUpsellLayout.getVisibility() == 0 || this.mInternetErrorLayout.getVisibility() == 0) {
            closeUpsellDialog();
        } else if (this.mDialogLayout.getVisibility() == 0) {
            dismissInvite();
        }
    }

    private void closeUpsellDialog() {
        AnytimeUIAndroidPanelAppV2 panelApp = getPanelApp();
        logFBUpsellEvent(SocialPartyEvent.FB_LINK_UPSELL_DIALOG_CLOSE);
        panelApp.actionDialogResult("close");
    }

    private void dismissInvite() {
        AnytimeUIAndroidPanelAppV2 panelApp = getPanelApp();
        if (TextUtils.isEmpty(this.mInvitedPartyID)) {
            panelApp.logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_DISMISS, "source", this.mSource);
        } else {
            panelApp.logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_DISMISS, "party_id", this.mInvitedPartyID, "source", this.mSource);
        }
        panelApp.actionDialogResult(IntentLogger.Status.DENY);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void joinParty(final boolean z) {
        final AnytimeUIAndroidPanelAppV2 panelApp = getPanelApp();
        if (TextUtils.isEmpty(this.mInvitedPartyID)) {
            showPartyJoinError(0);
            panelApp.actionDialogResult("close");
            return;
        }
        String str = TAG;
        Log.i(str, "Try to join party: " + this.mInvitedPartyID);
        if (!TextUtils.isEmpty(this.mActivityAppId)) {
            panelApp.logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_JOIN, "activity_id", this.mActivityAppId, "party_id", this.mInvitedPartyID, "source", this.mSource);
        } else {
            panelApp.logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_JOIN, "party_id", this.mInvitedPartyID, "source", this.mSource);
        }
        clearJoinPartyAsyncQueryHandle();
        this.mJoinPartyAsyncQueryHandle = HorizonContentProviderHelper.joinParty(getContext(), this.mInvitedPartyID, new HorizonContentProviderHelper.SingleIDCallbackWithErrorCode() {
            /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass7 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallbackWithErrorCode
            public void onSuccess(String str) {
                panelApp.logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_JOIN_SUCCESS, "party_id", SocialReceiveInviteDialog.this.mInvitedPartyID, "source", SocialReceiveInviteDialog.this.mSource);
                panelApp.actionDialogResult("join");
                if (z) {
                    String str2 = SocialReceiveInviteDialog.TAG;
                    Log.i(str2, "Try to launch party activity: " + SocialReceiveInviteDialog.this.mActivityPackageName + "; Deeplink Options: " + SocialReceiveInviteDialog.this.mActivityDeeplink);
                    panelApp.actionNavigate(SocialReceiveInviteDialog.this.mActivityPackageName, SocialReceiveInviteDialog.this.mActivityDeeplink);
                    panelApp.logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_APP_LAUNCH, "launched_app_id", SocialReceiveInviteDialog.this.mActivityAppId, "launched_app_package_name", SocialReceiveInviteDialog.this.mActivityPackageName, "source", SocialReceiveInviteDialog.this.mSource);
                    return;
                }
                SocialReceiveInviteDialog.this.getContext().sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_JOINED));
                if (SocialReceiveInviteDialog.this.getPanelApp().isGKEnabled(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
                    panelApp.actionNavigate(SystemUXRoute.AUI_PARTIES, "");
                } else {
                    panelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL, "");
                }
                SocialReceiveInviteDialog.this.clearJoinPartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallbackWithErrorCode
            public void onError(int i) {
                SocialReceiveInviteDialog.this.showPartyJoinError(i);
                SocialReceiveInviteDialog.this.clearJoinPartyAsyncQueryHandle();
            }
        });
    }

    private void setInvitedCountText(int i) {
        if (i > 0) {
            TextView textView = (TextView) findViewById(R.id.social_receive_invite_dialog_invited_users_count);
            textView.setText(getResources().getString(R.string.social_receive_invite_dialog_invited_users_text, Integer.valueOf(i)));
            textView.setVisibility(0);
        }
    }

    private void setPartyMemberCountText(int i) {
        if (i > 0) {
            TextView textView = (TextView) findViewById(R.id.social_receive_invite_dialog_party_members_count);
            textView.setText(getResources().getString(R.string.social_receive_invite_dialog_party_members_count_text, Integer.valueOf(i)));
            textView.setVisibility(0);
        }
    }

    private void setDialogSubtitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.social_receive_invite_dialog_subtitle)).setText(getResources().getString(R.string.social_receive_invite_dialog_description_with_sender_name, str));
        }
    }

    private void setDialogIcon(final String str) {
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.getInstance(getContext()).loadImage(str, new ImageLoader.ImageCallbacks() {
                /* class com.oculus.panelapp.anytimeui.dialogs.SocialReceiveInviteDialog.AnonymousClass8 */

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onSuccess(Bitmap bitmap) {
                    SocialReceiveInviteDialog.this.mDialogLayout.setIcon(new BitmapDrawable(SocialReceiveInviteDialog.this.getResources(), SocialReceiveInviteDialog.this.mProfilePictureHelper.getRoundedImageBitmap(bitmap, 72, 4)));
                    SocialReceiveInviteDialog.this.mDialogLayout.invalidate();
                }

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onFailure(Throwable th) {
                    String str = SocialReceiveInviteDialog.TAG;
                    Log.e(str, "Could not load profile picture with url: " + str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showPartyJoinError(int i) {
        if (!TextUtils.isEmpty(this.mActivityAppId)) {
            getPanelApp().logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_JOIN_FAIL, "activity_id", this.mActivityAppId, "party_id", this.mInvitedPartyID, "source", this.mSource);
        } else {
            getPanelApp().logSocialPartyEvent(SocialPartyEvent.JOIN_DIALOG_JOIN_FAIL, "party_id", this.mInvitedPartyID, "source", this.mSource);
        }
        String str = TAG;
        Log.i(str, "Failed to join party: " + this.mInvitedPartyID);
        SocialErrors.handlePartyError(i, TAG, getContext());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean shouldLaunchActivityAsPrimaryCTA() {
        return !TextUtils.isEmpty(this.mActivityDeeplink) || !TextUtils.isEmpty(this.mActivityPackageName);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logFBUpsellEvent(SocialPartyEvent socialPartyEvent) {
        getPanelApp().rawLogEvent(socialPartyEvent.toString(), "action", "accept_party_invite", "container", FB_UPSELL_CONTAINER_PARAM, "entrypoint", this.mSource, "product", SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT);
    }
}
