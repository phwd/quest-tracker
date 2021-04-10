package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCDropdown;
import com.oculus.ocui.OCEmptyLayout;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletProfileViewBinding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.profile.ProfileUtils;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.tablet.utils.ImageLoader;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.telemetry.LoggingApi;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class ProfileView extends BaseView {
    private static final String NO_OCULUS_USER_ID = "0";
    private static final String TAG = LoggingUtil.tag(ProfileView.class);
    private static final int USER_ID_SEGMENT = 1;
    @Nullable
    private AsyncQueryHandle mAcceptFriendRequestAsyncQueryHandle;
    private AnytimeTabletProfileViewBinding mBinding;
    @Nullable
    private AsyncQueryHandle mBlockVRUserAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mCancelFriendRequestAsyncQueryHandle;
    private Context mContext;
    @Nullable
    private AsyncQueryHandle mGetBlockedUserIdAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mInviteToPartyAsyncQueryHandle;
    private LoggingApi mLoggingApi;
    private MultiUserHelper mMultiUserHelper;
    private View.OnHoverListener mOnHoverListener;
    private OCDropdown<ProfileContextMenuItem> mOverflowDropdown;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private ProfileDataObserver mProfileDataObserver;
    private ProfilePictureHelper mProfilePictureHelper;
    @Nullable
    private AsyncQueryHandle mRejectFriendRequestAsyncQueryHandle;
    private Resources mResources;
    @Nullable
    private AsyncQueryHandle mSendFriendRequestAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mSetUserBioAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mUnblockVRUserAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mUnfriendAsyncQueryHandle;
    private ProfileViewModel mViewModel;

    public ProfileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing ProfileView");
        this.mContext = context;
        this.mResources = context.getResources();
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing ProfileView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        initializeLogging(this.mPanelApp);
        this.mBinding = (AnytimeTabletProfileViewBinding) viewDataBinding;
        this.mViewModel = anytimeUIAndroidPanelAppV2.acquireProfileViewModel();
        this.mMultiUserHelper = anytimeUIAndroidPanelAppV2.getMultiUserHelper();
        this.mViewModel.setCanShowUserSwitcher(this.mMultiUserHelper.canShowUserSwitcher());
        this.mProfilePictureHelper = anytimeUIAndroidPanelAppV2.getProfilePictureHelper();
        this.mBinding.setViewModel(this.mViewModel);
        this.mBinding.setResources(this.mResources);
        initializeHaptics();
        initializeSwitchAccountButton(this.mBinding.buttonSwitchAccount);
        initializeOverflowButton(this.mBinding.profileOverflow);
        initializeConfirmEditButton(this.mBinding.profileConfirmEdit);
        initializeEditAvatarButton(this.mBinding.editAvatar);
        initializeEditBioText(this.mBinding.editBiography);
        initializePrimaryButton(this.mBinding.profilePrimaryButton);
        initializeSecondaryButton(this.mBinding.profileSecondaryButton);
        initializeBackButton(this.mBinding.profileBackButton);
        initializeWifiConnectButton(this.mBinding.offlineState);
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.PROFILE_TABLET);
        this.mProfileDataObserver = new ProfileDataObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass1 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileDataObserver
            public void updateProfile() {
                ProfileView profileView = ProfileView.this;
                profileView.updateProfilePhoto(profileView.mBinding.profilePicture);
                ProfileView profileView2 = ProfileView.this;
                profileView2.updateAvatarImage(profileView2.mBinding.avatar);
                ProfileView.this.updateContextMenuItems();
                ProfileView.this.updateName();
            }
        };
        this.mViewModel.registerObserver(this.mProfileDataObserver);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateName() {
        int ellipsisCount;
        this.mBinding.profilePrimaryName.setLineSpacing(4.0f, 1.0f);
        String realName = this.mViewModel.isFBProfile() ? this.mViewModel.getRealName() : this.mViewModel.getAlias();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(realName);
        if (!this.mViewModel.isFBProfile() && !this.mViewModel.getRealName().isEmpty()) {
            spannableStringBuilder.append((CharSequence) " (").append((CharSequence) this.mViewModel.getRealName()).append((CharSequence) ")");
        }
        spannableStringBuilder.setSpan(new StyleSpan(1), 0, realName.length(), 18);
        this.mBinding.profilePrimaryName.setText(spannableStringBuilder);
        if (!this.mViewModel.isFBProfile() && this.mBinding.profilePrimaryName.getLayout() != null && (ellipsisCount = this.mBinding.profilePrimaryName.getLayout().getEllipsisCount(1)) > 0) {
            spannableStringBuilder.replace((spannableStringBuilder.length() - ellipsisCount) - 3, spannableStringBuilder.length(), (CharSequence) "...)");
            this.mBinding.profilePrimaryName.setText(spannableStringBuilder);
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying ProfileView");
        this.mPanelApp.releaseProfileViewModel();
        this.mMultiUserHelper.close();
        this.mViewModel.removeObserver(this.mProfileDataObserver);
        clearSetUserBioAsyncHandler();
        clearAcceptFriendRequestAsyncHandler();
        clearSendFriendRequestAsyncQueryHandler();
        clearUnfriendAsyncQueryHandler();
        clearRejectFriendRequestAsyncQueryHandler();
        clearCancelFriendRequestAsyncQueryHandler();
        clearBlockVRUserAsyncQueryHandler();
        clearUnblockVRUserAsyncQueryHandler();
        clearGetBlockedUserIdAsyncQueryHandler();
        clearInviteToPartyAsyncQueryHandler();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Uri parse = Uri.parse(str);
        List<String> pathSegments = parse.getPathSegments();
        String str2 = "";
        String str3 = (pathSegments == null || pathSegments.size() <= 1) ? str2 : parse.getPathSegments().get(1);
        String queryParameter = parse.getQueryParameter("returnRoute");
        String queryParameter2 = parse.getQueryParameter("returnUri");
        String queryParameter3 = parse.getQueryParameter("entrypoint");
        if (queryParameter == null || queryParameter2 != null) {
            str2 = queryParameter2;
        }
        boolean equals = this.mViewModel.getLocalUserId().equals(str3);
        if (TabletDeepLinkingUriUtil.isVRProfileTabletDeeplinkUri(str) && !equals) {
            String str4 = TAG;
            Log.d(str4, "Showing ProfileView VR Profile id: " + str3);
            this.mViewModel.setVRUserView(str3);
        } else if (TabletDeepLinkingUriUtil.isFBProfileTabletDeeplinkUri(str)) {
            String str5 = TAG;
            Log.d(str5, "Showing ProfileView Primary Profile id: " + str3);
            this.mViewModel.setFbFriendPrimaryProfileView(str3);
        } else {
            Log.d(TAG, "Showing ProfileView Self VR Profile");
            this.mViewModel.setSelfView();
        }
        logImpression(queryParameter3);
        this.mViewModel.setReturnRouteInfo(queryParameter, str2);
        this.mViewModel.refreshData();
        initializeProfilePicture(this.mBinding.profilePicture, this.mBinding.profilePictureEditOverlay);
        updateAvatarImage(this.mBinding.avatar);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding ProfileView");
        OCDropdown<ProfileContextMenuItem> oCDropdown = this.mOverflowDropdown;
        if (oCDropdown != null) {
            oCDropdown.dismiss();
            this.mOverflowDropdown = null;
        }
        this.mViewModel.clearReturnInfo();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void logImpression(String str) {
        this.mLoggingApi.rawLogEvent(ProfileLoggingConstants.PROFILE_EVENT_NAME, ProfileLoggingConstants.PROFILE_TYPE, this.mViewModel.getProfileType().toString(), ProfileLoggingConstants.ENTRY_POINT, str, ProfileLoggingConstants.EVENT_TYPE, ProfileLoggingConstants.IMPRESSION);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void logButtonClick(ClickEventButtonId clickEventButtonId) {
        this.mLoggingApi.rawLogEvent(ProfileLoggingConstants.PROFILE_EVENT_NAME, ProfileLoggingConstants.PROFILE_TYPE, this.mViewModel.getProfileType().toString(), "button_id", clickEventButtonId.getTelemetryButtonId(), ProfileLoggingConstants.EVENT_TYPE, ProfileLoggingConstants.BUTTON_CLICK);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initializeLogging(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mLoggingApi = new LoggingApi(anytimeUIAndroidPanelAppV2);
    }

    private void initializeBackButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.setEventHandler(this.mPanelApp);
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$Omfdfe7Vr4fzBni9BiGmRSOZi8 */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeBackButton$220$ProfileView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeBackButton$220$ProfileView(View view) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_BACK);
        if (this.mViewModel.getReturnRoute() != null) {
            this.mPanelApp.actionNavigate(this.mViewModel.getReturnRoute(), this.mViewModel.getReturnUri());
        } else {
            SocialLogger.logError(this.mPanelApp, "profile_back_button_error", TAG, "Return route is null");
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initializeWifiConnectButton(OCEmptyLayout oCEmptyLayout) {
        if (oCEmptyLayout != null && oCEmptyLayout.getOCButton() != null) {
            oCEmptyLayout.getOCButton().setEventHandler(this.mPanelApp);
            oCEmptyLayout.getOCButton().setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$HMmPdROsRKICTAs8PPew8PFC1o */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeWifiConnectButton$221$ProfileView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeWifiConnectButton$221$ProfileView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.WIFI, "");
    }

    private void initializeProfilePicture(ImageView imageView, ImageView imageView2) {
        if (imageView != null && imageView2 != null) {
            Log.d(TAG, "Initializing profile picture");
            updateProfilePhoto(imageView);
            imageView2.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$Nh9uamoPJigAbCcnAH4SySY750 */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeProfilePicture$222$ProfileView(view);
                }
            });
            imageView2.setOnHoverListener(this.mOnHoverListener);
            Log.d(TAG, "Initialized profile picture");
        }
    }

    public /* synthetic */ void lambda$initializeProfilePicture$222$ProfileView(View view) {
        if (this.mViewModel.getEditMode()) {
            this.mPanelApp.onButtonClick();
            logButtonClick(ClickEventButtonId.AUIV2_PROFILE_PHOTO_EDITOR);
            this.mPanelApp.actionNavigate(SystemUXRoute.PROFILE_PHOTO_EDITOR, "");
        }
    }

    public void updateProfilePhoto(ImageView imageView) {
        if (imageView != null) {
            this.mProfilePictureHelper.setImageViewFromUrl(imageView, this.mViewModel.getProfilePictureUri(), this.mViewModel.getIsUserActive(), ProfilePictureHelper.RenderFlags.V2, true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateAvatarImage(final ImageView imageView) {
        if (imageView != null) {
            Log.d(TAG, "Initializing avatar");
            imageView.setImageDrawable(this.mContext.getDrawable(R.drawable.anytime_tablet_social_default_avatar_image));
            if (this.mViewModel.getUserHasAvatar()) {
                ImageLoader.getInstance(getContext()).loadImage(this.mViewModel.getAvatarUrl(), new ImageLoader.ImageCallbacks() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass2 */

                    @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                    public void onSuccess(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                    public void onFailure(Throwable th) {
                        String str = ProfileView.TAG;
                        Log.e(str, "Could not load avatar picture with url: " + ProfileView.this.mViewModel.getAvatarUrl());
                    }
                });
            }
            Log.d(TAG, "Initialized avatar");
        }
    }

    private void initializeHaptics() {
        this.mOnHoverListener = new View.OnHoverListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$4ulraNuTpajYX4uyBFQPaLT1dgI */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return ProfileView.this.lambda$initializeHaptics$223$ProfileView(view, motionEvent);
            }
        };
    }

    public /* synthetic */ boolean lambda$initializeHaptics$223$ProfileView(View view, MotionEvent motionEvent) {
        if (!this.mViewModel.getEditMode() || motionEvent.getActionMasked() != 9) {
            return false;
        }
        this.mPanelApp.onButtonEnter();
        return false;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initializePrimaryButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.setEventHandler(this.mPanelApp);
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$Nn5ECfxSSFnahCNM_3kqf1lHqaw */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializePrimaryButton$224$ProfileView(view);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView$13  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$ProfileSecondaryButtonType = new int[ProfileSecondaryButtonType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(50:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|40|(2:41|42)|43|45|46|47|48|(2:49|50)|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|(3:71|72|74)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(52:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|40|(2:41|42)|43|45|46|47|48|(2:49|50)|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Can't wrap try/catch for region: R(53:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|(2:33|34)|35|37|38|39|40|(2:41|42)|43|45|46|47|48|(2:49|50)|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Can't wrap try/catch for region: R(55:0|(2:1|2)|3|5|6|7|(2:9|10)|11|(2:13|14)|15|17|18|19|(2:21|22)|23|(2:25|26)|27|29|30|31|(2:33|34)|35|37|38|39|40|(2:41|42)|43|45|46|47|48|(2:49|50)|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Can't wrap try/catch for region: R(57:0|(2:1|2)|3|5|6|7|(2:9|10)|11|(2:13|14)|15|17|18|19|(2:21|22)|23|25|26|27|29|30|31|(2:33|34)|35|37|38|39|40|(2:41|42)|43|45|46|47|48|49|50|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Can't wrap try/catch for region: R(59:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|(2:21|22)|23|25|26|27|29|30|31|(2:33|34)|35|37|38|39|40|(2:41|42)|43|45|46|47|48|49|50|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Can't wrap try/catch for region: R(60:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|(2:21|22)|23|25|26|27|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Can't wrap try/catch for region: R(62:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|40|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00a5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00af */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00d6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00e0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00ea */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x00f4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0108 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0112 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x011c */
        static {
            /*
            // Method dump skipped, instructions count: 295
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass13.<clinit>():void");
        }
    }

    public /* synthetic */ void lambda$initializePrimaryButton$224$ProfileView(View view) {
        switch (this.mViewModel.getPrimaryButtonType()) {
            case EDIT:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_EDIT_PROFILE);
                setEditMode(true);
                return;
            case COMPLETE_PROFILE:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_COMPLETE_PROFILE);
                setEditMode(true);
                return;
            case VR_CREATE_PARTY:
                vrCreateParty();
                return;
            case VR_INVITE_TO_PARTY:
                vrInviteToParty(this.mBinding.profilePrimaryButton);
                return;
            case VR_JOIN_PARTY:
                vrJoinParty();
                return;
            case VR_USER:
                sendFriendRequest(this.mBinding.profilePrimaryButton);
                return;
            case INCOMING_FRIEND_REQUEST:
                acceptFriendRequest(this.mBinding.profilePrimaryButton);
                return;
            case VR_USER_OUTGOING_REQUEST:
                cancelFriendRequest(this.mBinding.profilePrimaryButton);
                return;
            case FB_CHAT:
                navigateToFbMessengerThread();
                return;
            case FB_CREATE_PARTY:
                fbProfileCreateParty();
                return;
            case FB_INVITE_TO_PARTY:
                fbProfileInviteToParty(this.mBinding.profilePrimaryButton);
                return;
            case FB_JOIN_PARTY:
                fbProfileJoinParty();
                return;
            default:
                Log.e(TAG, "Unexpected profile primary button");
                return;
        }
    }

    private void initializeSecondaryButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.setEventHandler(this.mPanelApp);
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$SiseQ4OvV9Bv1UN_VhZFmEunOlo */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeSecondaryButton$225$ProfileView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$225$ProfileView(View view) {
        int i = AnonymousClass13.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$ProfileSecondaryButtonType[this.mViewModel.getSecondaryButtonType().ordinal()];
        if (i == 1) {
            Log.e(TAG, "This button (secondary button of ADD_FRIEND on OC profile) should always be disabled.");
        } else if (i == 2) {
            navigateToOCChatThread();
        } else if (i != 3) {
            Log.e(TAG, "Unexpected profile secondary button");
        } else {
            navigateToFbMessengerThread();
        }
    }

    private void initializeSwitchAccountButton(OCButton oCButton) {
        if (oCButton != null) {
            Log.d(TAG, "Initializing switch account button");
            this.mMultiUserHelper.initSwitchUserButton(oCButton);
            oCButton.setEventHandler(this.mPanelApp);
            Log.d(TAG, "Initialized switch account button");
        }
    }

    private void initializeOverflowButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.setEventHandler(this.mPanelApp);
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$uFFNO8xEJVUg_6uspprL98bLhUY */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeOverflowButton$226$ProfileView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeOverflowButton$226$ProfileView(View view) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_OVERFLOW_MENU);
        if (this.mOverflowDropdown == null) {
            this.mOverflowDropdown = new OCDropdown<>(this.mContext);
            this.mOverflowDropdown.setWidth(230);
            updateContextMenuItems();
        }
        this.mOverflowDropdown.toggle(view);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateContextMenuItems() {
        if (this.mOverflowDropdown != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mViewModel.getContextMenuItems());
            this.mOverflowDropdown.setItems(arrayList);
            this.mOverflowDropdown.setTitleMap(ProfileContextMenuItem.getTitleMap());
            this.mOverflowDropdown.setIconMap(ProfileContextMenuItem.getIconMap());
            this.mOverflowDropdown.setOnItemClick(new Function() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$zifGvqGUEJ61LNCwJhLgfTq7nYA */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ProfileView.this.lambda$updateContextMenuItems$227$ProfileView((ProfileContextMenuItem) obj);
                }
            });
            this.mOverflowDropdown.setEventHandler(this.mPanelApp);
        }
    }

    public /* synthetic */ Object lambda$updateContextMenuItems$227$ProfileView(ProfileContextMenuItem profileContextMenuItem) {
        performContextMenuAction(profileContextMenuItem);
        return null;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void performContextMenuAction(ProfileContextMenuItem profileContextMenuItem) {
        switch (profileContextMenuItem) {
            case SHARE:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_SHARE_PROFILE);
                ProfileUtils.navigateToProfileShareSheet(this.mContext, this.mPanelApp, this.mViewModel.getAlias(), this.mViewModel.getProfilePictureUri());
                return;
            case PRIVACY:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_PRIVACY_SETTINGS);
                this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL, "privacy_settings");
                return;
            case FB_BLOCK:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_BLOCK_FB_USER);
                this.mPanelApp.actionNavigate(SystemUXRoute.FACEBOOK_BLOCK, new Uri.Builder().encodedPath("").appendQueryParameter("user_fbid", this.mViewModel.getViewerFbId()).appendQueryParameter("target_fbid", this.mViewModel.getUserId()).appendQueryParameter("target_name", this.mViewModel.getRealName()).build().toString());
                return;
            case FB_REPORT:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_REPORT_FB_USER);
                this.mPanelApp.actionNavigate(SystemUXRoute.USER_REPORT, new Uri.Builder().encodedPath("system_utilities").appendPath("user_report").appendPath(this.mBinding.getRoot().getContext().getPackageName()).appendPath(NO_OCULUS_USER_ID).appendPath("fb_profile").appendQueryParameter("reportee_fbid", this.mViewModel.getUserId()).appendQueryParameter("reportee_name", this.mViewModel.getRealName()).appendQueryParameter("reporter_fbid", this.mViewModel.getViewerFbId()).build().toString());
                return;
            case UNFRIEND_USER:
                unfriendUser(this.mBinding.profileOverflow);
                return;
            case REJECT_FRIEND_REQUEST:
                rejectFriendRequest(this.mBinding.profileOverflow);
                return;
            case VR_BLOCK:
                blockVRUser(this.mBinding.profileOverflow);
                return;
            case VR_UNBLOCK:
                unblockVRUser(this.mBinding.profileOverflow);
                return;
            case VR_REPORT:
                reportVRUser();
                return;
            case VIEW_FULL_PROFILE:
                logButtonClick(ClickEventButtonId.AUIV2_PROFILE_VIEW_PROFILE);
                this.mPanelApp.actionNavigate(SystemUXRoute.PROFILE, new Uri.Builder().encodedPath("").appendPath(this.mViewModel.getUserId()).appendPath("/anytime_ui").build().toString());
                return;
            default:
                Log.e(TAG, "Received unexpected context menu item");
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetUserBioAsyncHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mSetUserBioAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSetUserBioAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAcceptFriendRequestAsyncHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mAcceptFriendRequestAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mAcceptFriendRequestAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSendFriendRequestAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mSendFriendRequestAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSendFriendRequestAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUnfriendAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mUnfriendAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mUnfriendAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRejectFriendRequestAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mRejectFriendRequestAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mRejectFriendRequestAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCancelFriendRequestAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mCancelFriendRequestAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mCancelFriendRequestAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBlockVRUserAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mBlockVRUserAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mBlockVRUserAsyncQueryHandle = null;
        }
    }

    private void clearGetBlockedUserIdAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mGetBlockedUserIdAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mGetBlockedUserIdAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUnblockVRUserAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mUnblockVRUserAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mUnblockVRUserAsyncQueryHandle = null;
        }
        clearGetBlockedUserIdAsyncQueryHandler();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInviteToPartyAsyncQueryHandler() {
        AsyncQueryHandle asyncQueryHandle = this.mInviteToPartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mInviteToPartyAsyncQueryHandle = null;
        }
    }

    private void initializeEditBioText(ProfileEditText profileEditText) {
        if (profileEditText != null) {
            profileEditText.addTextChangedListener(new TextWatcher() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass3 */

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    ProfileView.this.mBinding.editBioCharacterCount.setText(String.format(ProfileView.this.mResources.getString(R.string.anytime_tablet_profile_bio_character_count), Integer.valueOf(editable.length())));
                }
            });
            profileEditText.setOnHoverListener(this.mOnHoverListener);
        }
    }

    private void initializeConfirmEditButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.setEventHandler(this.mPanelApp);
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$_Np4tjDOSS6RoCHjrNXuHTNV4o */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeConfirmEditButton$228$ProfileView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeConfirmEditButton$228$ProfileView(View view) {
        saveChangesToBio();
    }

    private void saveChangesToBio() {
        String obj = this.mBinding.editBiography.getText().toString();
        if (obj.equals(this.mViewModel.getBio())) {
            logButtonClick(ClickEventButtonId.AUIV2_PROFILE_CONFIRM_EDIT);
            setEditMode(false);
        } else if (!this.mViewModel.getIsSavingChanges()) {
            logButtonClick(ClickEventButtonId.AUIV2_PROFILE_CONFIRM_EDIT_BIO_CHANGED);
            this.mViewModel.setIsSavingChanges(true);
            clearSetUserBioAsyncHandler();
            this.mSetUserBioAsyncQueryHandle = HorizonContentProviderHelper.setUserBio(this.mContext, this.mViewModel.getUserId(), obj, new HorizonContentProviderHelper.SingleIDCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass4 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
                public void onSuccess(String str) {
                    ProfileView.this.mViewModel.refreshData();
                    ProfileView.this.mViewModel.setIsSavingChanges(false);
                    ProfileView.this.setEditMode(false);
                    ProfileView.this.clearSetUserBioAsyncHandler();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    ProfileView.this.mViewModel.setIsSavingChanges(false);
                    SocialLogger.logError(ProfileView.this.mPanelApp, "save_changes_to_bio", ProfileView.TAG, "Failed to save changes to bio");
                    ProfileView.this.showBioUpdateFailedDialog();
                    ProfileView.this.clearSetUserBioAsyncHandler();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showBioUpdateFailedDialog() {
        DialogDefinitionBase bioUpdateFailedDialog = ProfileDialogs.getBioUpdateFailedDialog(this.mResources);
        bioUpdateFailedDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$mjpcvdhpaX9N_pLsllQQ_HmPGNo */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return ProfileView.this.lambda$showBioUpdateFailedDialog$229$ProfileView(dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(bioUpdateFailedDialog);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$showBioUpdateFailedDialog$229$ProfileView(com.oculus.systemdialog.DialogResult r5) {
        /*
            r4 = this;
            java.lang.String r5 = r5.getDialogAction()
            int r0 = r5.hashCode()
            r1 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001f
            r1 = 108405416(0x67622a8, float:4.629292E-35)
            if (r0 == r1) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r0 = "retry"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0029
            r5 = r2
            goto L_0x002a
        L_0x001f:
            java.lang.String r0 = "cancel"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0029
            r5 = r3
            goto L_0x002a
        L_0x0029:
            r5 = -1
        L_0x002a:
            if (r5 == 0) goto L_0x0038
            if (r5 == r3) goto L_0x002f
            goto L_0x0040
        L_0x002f:
            com.oculus.tablet.logging.ClickEventButtonId r5 = com.oculus.tablet.logging.ClickEventButtonId.AUIV2_PROFILE_CONFIRM_EDIT_FAIL_CANCEL
            r4.logButtonClick(r5)
            r4.setEditMode(r2)
            return r2
        L_0x0038:
            com.oculus.tablet.logging.ClickEventButtonId r5 = com.oculus.tablet.logging.ClickEventButtonId.AUIV2_PROFILE_CONFIRM_EDIT_FAIL_RETRY
            r4.logButtonClick(r5)
            r4.saveChangesToBio()
        L_0x0040:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.lambda$showBioUpdateFailedDialog$229$ProfileView(com.oculus.systemdialog.DialogResult):boolean");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initializeEditAvatarButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.setEventHandler(this.mPanelApp);
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$wpOlBy_1lagEeGxNKOzlUX4TfoQ */

                public final void onClick(View view) {
                    ProfileView.this.lambda$initializeEditAvatarButton$230$ProfileView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeEditAvatarButton$230$ProfileView(View view) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_AVATAR_EDITOR);
        if (this.mViewModel.getHasInternetConnection()) {
            this.mPanelApp.actionNavigate("systemux://avatareditor", "/");
        } else {
            this.mPanelApp.getDialogManager().showDialog(ProfileDialogs.getOfflineDialog(this.mResources));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEditMode(boolean z) {
        this.mViewModel.setEditMode(z);
        if (z) {
            this.mBinding.editBiography.setText(this.mViewModel.getBio());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setButtonLoading(boolean z, OCButton oCButton) {
        if (oCButton == this.mBinding.profilePrimaryButton) {
            this.mViewModel.setIsPrimaryButtonLoading(z);
        } else if (oCButton == this.mBinding.profileOverflow) {
            this.mViewModel.setIsOverflowButtonLoading(z);
        } else {
            return;
        }
        Drawable[] compoundDrawables = oCButton.getCompoundDrawables();
        if (compoundDrawables.length > 0) {
            int i = 0;
            if (compoundDrawables[0] != null) {
                Drawable drawable = compoundDrawables[0];
                if (!z) {
                    i = 255;
                }
                drawable.setAlpha(i);
            }
        }
    }

    private void navigateToFbMessengerThread() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_CHAT_IN_MESSENGER);
        if (this.mViewModel.getViewerFbId() == null) {
            Log.d(TAG, "Viewer fb id is null");
            return;
        }
        AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = this.mPanelApp;
        SystemUXRoute systemUXRoute = SystemUXRoute.AUI_MESSENGER;
        anytimeUIAndroidPanelAppV2.actionNavigate(systemUXRoute, "/mailbox/" + this.mViewModel.getViewerFbId() + "/thread/" + this.mViewModel.getUserId());
    }

    private void navigateToOCChatThread() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_OC_CHAT);
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_CHATS, new Uri.Builder().encodedPath("").appendPath("mailbox").appendPath(this.mViewModel.getLocalUserId()).appendPath("thread").appendPath(this.mViewModel.getUserId()).build().toString());
    }

    private void acceptFriendRequest(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_ACCEPT_FRIEND_REQUEST);
        clearAcceptFriendRequestAsyncHandler();
        if (this.mViewModel.getLinkedAccountsInfo() != null) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mViewModel.getLinkedAccountsInfo(), new Runnable(oCButton) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$SgOKxex0_UROs8mFD8f2bWZ7v_U */
                private final /* synthetic */ OCButton f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ProfileView.this.lambda$acceptFriendRequest$231$ProfileView(this.f$1);
                }
            }, new UpsellLoggingParameters("aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_ACCEPT_FRIEND_REQUEST, "aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_ACCEPT_FRIEND_REQUEST_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_FRIEND_PRODUCT));
        }
    }

    public /* synthetic */ void lambda$acceptFriendRequest$231$ProfileView(final OCButton oCButton) {
        setButtonLoading(true, oCButton);
        this.mAcceptFriendRequestAsyncQueryHandle = HorizonContentProviderHelper.acceptFriendRequest(this.mContext, this.mViewModel.getUserId(), "PROFILE", new HorizonContentProviderHelper.AcceptFriendRequestCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.AcceptFriendRequestCallback
            public void onSuccess(String str) {
                ProfileView.this.mViewModel.refreshData();
                ProfileView.this.clearAcceptFriendRequestAsyncHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileView.this.mPanelApp, "profile_accept_friend_request", ProfileView.TAG, "Failed to accept friend request");
                ProfileView.this.clearAcceptFriendRequestAsyncHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }
        });
    }

    private void sendFriendRequest(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_ADD_FRIEND);
        clearSendFriendRequestAsyncQueryHandler();
        if (this.mViewModel.getLinkedAccountsInfo() != null) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mViewModel.getLinkedAccountsInfo(), new Runnable(oCButton) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$qRvrrB_0w267LfH32qOVg4xgfEk */
                private final /* synthetic */ OCButton f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ProfileView.this.lambda$sendFriendRequest$232$ProfileView(this.f$1);
                }
            }, new UpsellLoggingParameters("aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_SEND_FRIEND_REQUEST, "aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_SEND_FRIEND_REQUEST_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_FRIEND_PRODUCT));
        }
    }

    public /* synthetic */ void lambda$sendFriendRequest$232$ProfileView(final OCButton oCButton) {
        setButtonLoading(true, oCButton);
        this.mSendFriendRequestAsyncQueryHandle = HorizonContentProviderHelper.sendFriendRequest(this.mContext, this.mViewModel.getUserId(), "PROFILE", new HorizonContentProviderHelper.SendFriendRequestCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass6 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SendFriendRequestCallback
            public void onSuccess(String str) {
                ProfileView.this.mViewModel.refreshData();
                ProfileView.this.clearSendFriendRequestAsyncQueryHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileView.this.mPanelApp, "profile_send_friend_request", ProfileView.TAG, "Failed to send friend request");
                ProfileView.this.clearSendFriendRequestAsyncQueryHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }
        });
    }

    private void unfriendUser(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_UNFRIEND_USER);
        clearUnfriendAsyncQueryHandler();
        DialogDefinitionBase unfriendDialog = ProfileDialogs.getUnfriendDialog(this.mContext.getResources(), this.mViewModel.getAlias());
        unfriendDialog.setDialogResultHandler(new DialogResultHandler(oCButton) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$QnUFIcFbFMKXy02iuEaRJ94xWZ4 */
            private final /* synthetic */ OCButton f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return ProfileView.this.lambda$unfriendUser$233$ProfileView(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(unfriendDialog);
    }

    public /* synthetic */ boolean lambda$unfriendUser$233$ProfileView(final OCButton oCButton, DialogResult dialogResult) {
        if (ProfileDialogs.CONFIRM_ACTION.equals(dialogResult.getDialogAction())) {
            setButtonLoading(true, oCButton);
            this.mUnfriendAsyncQueryHandle = HorizonContentProviderHelper.unfriendUser(this.mContext, this.mViewModel.getUserId(), new HorizonContentProviderHelper.UnfriendUserCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass7 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.UnfriendUserCallback
                public void onSuccess(String str) {
                    ProfileView.this.mViewModel.refreshData();
                    ProfileView.this.clearUnfriendAsyncQueryHandler();
                    ProfileView.this.setButtonLoading(false, oCButton);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(ProfileView.this.mPanelApp, "profile_unfriend_user", ProfileView.TAG, "Failed to unfriend user");
                    ProfileView.this.clearUnfriendAsyncQueryHandler();
                    ProfileView.this.setButtonLoading(false, oCButton);
                }
            });
            return true;
        } else if ("cancel".equals(dialogResult.getDialogAction())) {
            return true;
        } else {
            return false;
        }
    }

    private void rejectFriendRequest(final OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_REJECT_FRIEND_REQUEST);
        clearRejectFriendRequestAsyncQueryHandler();
        setButtonLoading(true, oCButton);
        this.mRejectFriendRequestAsyncQueryHandle = HorizonContentProviderHelper.rejectFriendRequest(this.mContext, this.mViewModel.getUserId(), new HorizonContentProviderHelper.RejectFriendRequestCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass8 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.RejectFriendRequestCallback
            public void onSuccess(String str) {
                ProfileView.this.mViewModel.refreshData();
                ProfileView.this.clearRejectFriendRequestAsyncQueryHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileView.this.mPanelApp, "profile_reject_friend_request", ProfileView.TAG, "Failed to reject friend request");
                ProfileView.this.clearRejectFriendRequestAsyncQueryHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }
        });
    }

    private void cancelFriendRequest(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_CANCEL_FRIEND_REQUEST);
        clearCancelFriendRequestAsyncQueryHandler();
        if (this.mViewModel.getLinkedAccountsInfo() != null) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mViewModel.getLinkedAccountsInfo(), new Runnable(oCButton) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$pxAuMuGQd39awLyvuXX4OEQc */
                private final /* synthetic */ OCButton f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ProfileView.this.lambda$cancelFriendRequest$235$ProfileView(this.f$1);
                }
            }, new UpsellLoggingParameters("aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_CANCEL_FRIEND_REQUEST, "aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_CANCEL_FRIEND_REQUEST_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_FRIEND_PRODUCT));
        }
    }

    public /* synthetic */ void lambda$cancelFriendRequest$235$ProfileView(OCButton oCButton) {
        DialogDefinitionBase cancelFriendRequestDialog = ProfileDialogs.getCancelFriendRequestDialog(this.mContext.getResources());
        cancelFriendRequestDialog.setDialogResultHandler(new DialogResultHandler(oCButton) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$wNgEYGrsXVnPFR0YH7nkBj9XjIU */
            private final /* synthetic */ OCButton f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return ProfileView.this.lambda$null$234$ProfileView(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(cancelFriendRequestDialog);
    }

    public /* synthetic */ boolean lambda$null$234$ProfileView(final OCButton oCButton, DialogResult dialogResult) {
        if (ProfileDialogs.CONFIRM_ACTION.equals(dialogResult.getDialogAction())) {
            setButtonLoading(true, oCButton);
            this.mCancelFriendRequestAsyncQueryHandle = HorizonContentProviderHelper.cancelFriendRequest(this.mContext, this.mViewModel.getUserId(), "PROFILE", new HorizonContentProviderHelper.CancelFriendRequestCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass9 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.CancelFriendRequestCallback
                public void onSuccess(String str) {
                    ProfileView.this.mViewModel.refreshData();
                    ProfileView.this.clearCancelFriendRequestAsyncQueryHandler();
                    ProfileView.this.setButtonLoading(false, oCButton);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(ProfileView.this.mPanelApp, "profile_cancel_friend_request", ProfileView.TAG, "Failed to cancel friend request");
                    ProfileView.this.clearCancelFriendRequestAsyncQueryHandler();
                    ProfileView.this.setButtonLoading(false, oCButton);
                }
            });
            return true;
        } else if ("cancel".equals(dialogResult.getDialogAction())) {
            return true;
        } else {
            return false;
        }
    }

    private void blockVRUser(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_BLOCK_VR_USER);
        clearBlockVRUserAsyncQueryHandler();
        DialogDefinitionBase blockDialog = ProfileDialogs.getBlockDialog(this.mContext.getResources(), this.mViewModel.getAlias());
        blockDialog.setDialogResultHandler(new DialogResultHandler(oCButton) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$MDRwJR8kiE_UwF0a57UH07eIBZc */
            private final /* synthetic */ OCButton f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return ProfileView.this.lambda$blockVRUser$236$ProfileView(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(blockDialog);
    }

    public /* synthetic */ boolean lambda$blockVRUser$236$ProfileView(final OCButton oCButton, DialogResult dialogResult) {
        if (ProfileDialogs.CONFIRM_ACTION.equals(dialogResult.getDialogAction())) {
            setButtonLoading(true, oCButton);
            this.mBlockVRUserAsyncQueryHandle = HorizonContentProviderHelper.blockUser(this.mContext, this.mViewModel.getUserId(), new HorizonContentProviderHelper.BlockUserCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass10 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BlockUserCallback
                public void onSuccess(String str) {
                    ProfileView.this.mViewModel.refreshData();
                    ProfileView.this.clearBlockVRUserAsyncQueryHandler();
                    ProfileView.this.setButtonLoading(false, oCButton);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(ProfileView.this.mPanelApp, "profile_block_vr_user", ProfileView.TAG, "Failed to block vr user");
                    ProfileView.this.clearBlockVRUserAsyncQueryHandler();
                    ProfileView.this.setButtonLoading(false, oCButton);
                }
            });
            return true;
        } else if ("cancel".equals(dialogResult.getDialogAction())) {
            return true;
        } else {
            return false;
        }
    }

    private void unblockVRUser(final OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_UNBLOCK_VR_USER);
        clearUnblockVRUserAsyncQueryHandler();
        this.mGetBlockedUserIdAsyncQueryHandle = HorizonContentProviderHelper.getBlockedUserId(this.mContext, this.mViewModel.getUserId(), new HorizonContentProviderHelper.GetBlockedUserIdCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass11 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.GetBlockedUserIdCallback
            public void onSuccess(String str) {
                ProfileView.this.setButtonLoading(true, oCButton);
                ProfileView profileView = ProfileView.this;
                profileView.mUnblockVRUserAsyncQueryHandle = HorizonContentProviderHelper.unblockUser(profileView.mContext, str, new HorizonContentProviderHelper.UnblockUserCallback() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass11.AnonymousClass1 */

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.UnblockUserCallback
                    public void onSuccess(String str) {
                        ProfileView.this.mViewModel.refreshData();
                        ProfileView.this.clearUnblockVRUserAsyncQueryHandler();
                        ProfileView.this.setButtonLoading(false, oCButton);
                    }

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                    public void onError() {
                        SocialLogger.logError(ProfileView.this.mPanelApp, "profile_unblock_vr_user", ProfileView.TAG, "Failed to unblock vr user");
                        ProfileView.this.clearUnblockVRUserAsyncQueryHandler();
                        ProfileView.this.setButtonLoading(false, oCButton);
                    }
                });
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileView.this.mPanelApp, "profile_unblock_vr_user", ProfileView.TAG, "Failed to unblock vr user");
                ProfileView.this.clearUnblockVRUserAsyncQueryHandler();
            }
        });
    }

    private void reportVRUser() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_REPORT_VR_USER);
        this.mPanelApp.actionNavigate(SystemUXRoute.USER_REPORT, new Uri.Builder().encodedPath("system_utilities").appendPath("user_report").appendPath(this.mBinding.getRoot().getContext().getPackageName()).appendPath(this.mViewModel.getUserId()).appendPath("profile").build().toString());
    }

    private void fbProfileCreateParty() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_FB_CREATE_PARTY);
        createParty(this.mViewModel.getUserId());
    }

    private void vrCreateParty() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_CREATE_PARTY);
        if (this.mViewModel.getLinkedAccountsInfo() != null) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mViewModel.getLinkedAccountsInfo(), new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$XZlXQ8qoA46ysWL5tpTuE3hndbA */

                public final void run() {
                    ProfileView.this.lambda$vrCreateParty$237$ProfileView();
                }
            }, new UpsellLoggingParameters("aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE, "aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_START_PARTY_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT));
        }
    }

    public /* synthetic */ void lambda$vrCreateParty$237$ProfileView() {
        createParty(this.mViewModel.getUserId());
    }

    private void createParty(String str) {
        if (str != null) {
            this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter("thread_key", str).appendQueryParameter(LoggingConstants.CORRELATION_ID, UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.AUI_PROFILE_CREATE_PARTY).build().toString());
        }
    }

    private void fbProfileInviteToParty(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_FB_INVITE_TO_PARTY);
        clearInviteToPartyAsyncQueryHandler();
        if (this.mViewModel.getViewerCurrentPartyId() != null) {
            inviteToParty(this.mViewModel.getLinkedOculusUserId(), oCButton);
        }
    }

    private void vrInviteToParty(OCButton oCButton) {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_PARTY_INVITE);
        clearInviteToPartyAsyncQueryHandler();
        if (this.mViewModel.getViewerCurrentPartyId() != null && this.mViewModel.getLinkedAccountsInfo() != null) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mViewModel.getLinkedAccountsInfo(), new Runnable(oCButton) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$U2meUG8yF8DRHSSmO2vm7RAIcHI */
                private final /* synthetic */ OCButton f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ProfileView.this.lambda$vrInviteToParty$238$ProfileView(this.f$1);
                }
            }, new UpsellLoggingParameters("aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE, "aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT));
        }
    }

    public /* synthetic */ void lambda$vrInviteToParty$238$ProfileView(OCButton oCButton) {
        inviteToParty(this.mViewModel.getUserId(), oCButton);
    }

    private void inviteToParty(String str, final OCButton oCButton) {
        setButtonLoading(true, oCButton);
        this.mInviteToPartyAsyncQueryHandle = HorizonContentProviderHelper.inviteUsersToParty(this.mContext, Arrays.asList(str), this.mViewModel.getViewerCurrentPartyId(), new HorizonContentProviderHelper.MultipleIDCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView.AnonymousClass12 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
            public void onSuccess(List<String> list) {
                String str;
                Context context = ProfileView.this.mContext;
                Resources resources = ProfileView.this.mContext.getResources();
                int i = R.string.anytime_tablet_profile_party_invite_request_success;
                Object[] objArr = new Object[1];
                if (ProfileView.this.mViewModel.isFBProfile()) {
                    str = ProfileView.this.mViewModel.getRealName();
                } else {
                    str = ProfileView.this.mViewModel.getAlias();
                }
                objArr[0] = str;
                ProfileViewToaster.showToast(context, "anytime_tablet_profile_party_invite_request_success", resources.getString(i, objArr), "", R.drawable.oc_icon_party_invite_sent_filled_24_d2d2d2, ProfileView.TAG);
                ProfileView.this.mViewModel.refreshData();
                ProfileView.this.clearInviteToPartyAsyncQueryHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileView.this.mPanelApp, "profile_party_invite", ProfileView.TAG, "Failed to invite user to party");
                ProfileView.this.clearInviteToPartyAsyncQueryHandler();
                ProfileView.this.setButtonLoading(false, oCButton);
            }
        });
    }

    private void fbProfileJoinParty() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_FB_JOIN_PARTY);
        if (this.mViewModel.getJoinablePartyId() != null) {
            lambda$vrJoinParty$239$ProfileView();
        }
    }

    private void vrJoinParty() {
        logButtonClick(ClickEventButtonId.AUIV2_PROFILE_PARTY_JOIN);
        if (this.mViewModel.getJoinablePartyId() != null && this.mViewModel.getLinkedAccountsInfo() != null) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mViewModel.getLinkedAccountsInfo(), new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$ProfileView$lmjkI1wOI1Fm3aKfq4KX6ut8Ar0 */

                public final void run() {
                    ProfileView.this.lambda$vrJoinParty$239$ProfileView();
                }
            }, new UpsellLoggingParameters("aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_ACCEPT_PARTY_INVITE, "aui_v2_profile_panel", SocialBundleConstants.FB_UPSELL_ACCEPT_PARTY_INVITE_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: joinParty */
    public void lambda$vrJoinParty$239$ProfileView() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CONFIRM_JOIN_PARTY, new Uri.Builder().encodedPath("").appendQueryParameter("party_id", this.mViewModel.getJoinablePartyId()).appendQueryParameter(LoggingConstants.CORRELATION_ID, UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.AUI_PROFILE_JOIN_PARTY).build().toString());
    }
}
