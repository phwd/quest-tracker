package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.profile.FbFriendPrimaryProfile;
import com.oculus.horizoncontent.profile.ProfileContent;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.horizoncontent.profile.SelfVRProfileContent;
import com.oculus.horizoncontent.profile.VRProfileContent;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfileViewModel extends BaseObservable implements ViewModelLifecycle, AndroidPanelApp.PanelFrameCallback {
    private static final long PROFILE_DATA_UPDATE_RATE_MILLIS = 120000;
    private static final String TAG = LoggingUtil.tag(ProfileViewModel.class);
    private boolean mCanShowUserSwitcher;
    private ConnectivityManager mConnection;
    private Context mContext;
    private boolean mEditMode;
    private String mFbFriendPrimaryProfileId;
    @Nullable
    private AsyncQueryHandle mFetchProfileContentAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mFetchVRProfileAsyncQueryHandler;
    @Nullable
    private AsyncQueryHandle mGetFbFriendPrimaryProfileAsyncQueryHandle;
    private boolean mHasInternetConnection;
    private boolean mIsSavingChanges;
    private long mLastServerUpdateTimeMillis;
    private boolean mLoadingError;
    private boolean mLoadingProfile;
    private String mLocalUserId;
    private ArrayList<ProfileDataObserver> mObservers;
    private boolean mOverflowButtonLoading;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private boolean mPrimaryButtonLoading;
    @Nullable
    private ProfileContent mProfileContent;
    private ProfileType mProfileType = ProfileType.SELF;
    @Nullable
    private String mReturnRoute;
    @Nullable
    private String mReturnUri;
    private boolean mShowFbPartyButton;
    private boolean mShowFbPresenceString;
    private boolean mShowMessengerActions;
    private boolean mShowOcPartyButton;
    private String mVRUserId;

    public enum ProfileType {
        SELF,
        VR_USER,
        FB_FRIEND
    }

    public ProfileViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        boolean z = false;
        this.mEditMode = false;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mLocalUserId = str;
        this.mConnection = (ConnectivityManager) context.getSystemService("connectivity");
        ConnectivityManager connectivityManager = this.mConnection;
        if (!(connectivityManager == null || connectivityManager.getActiveNetwork() == null)) {
            z = true;
        }
        this.mHasInternetConnection = z;
        this.mObservers = new ArrayList<>();
        this.mShowFbPartyButton = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_FB_PROFILE_PARTY_BUTTON);
        this.mShowOcPartyButton = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_OC_PROFILE_PARTY_BUTTON);
        this.mShowFbPresenceString = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_FB_PROFILE_PRESENCE);
        this.mShowMessengerActions = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_PROFILE_MESSENGER_ACTIONS);
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
        this.mObservers.clear();
        clearAllAsyncHandlers();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long timeMillis = inputFrame.getTimeMillis();
        if (timeMillis - this.mLastServerUpdateTimeMillis > PROFILE_DATA_UPDATE_RATE_MILLIS) {
            this.mLastServerUpdateTimeMillis = timeMillis;
            refreshData();
        }
    }

    public void setFbFriendPrimaryProfileView(String str) {
        this.mEditMode = false;
        this.mProfileType = ProfileType.FB_FRIEND;
        this.mFbFriendPrimaryProfileId = str;
        this.mProfileContent = null;
        notifyChange();
    }

    public void setSelfView() {
        if (this.mProfileType != ProfileType.SELF) {
            this.mProfileType = ProfileType.SELF;
            this.mVRUserId = this.mLocalUserId;
            this.mProfileContent = null;
            notifyChange();
        }
    }

    public void setVRUserView(String str) {
        this.mEditMode = false;
        this.mProfileType = ProfileType.VR_USER;
        this.mVRUserId = str;
        this.mProfileContent = null;
        notifyChange();
    }

    public void setOfflineView() {
        this.mHasInternetConnection = false;
        this.mProfileContent = null;
        notifyChange();
    }

    public void setTestProfileContent(ProfileContent profileContent) {
        this.mProfileContent = profileContent;
        if (profileContent instanceof VRProfileContent) {
            this.mProfileType = ProfileType.VR_USER;
        } else if (profileContent instanceof FbFriendPrimaryProfile) {
            this.mProfileType = ProfileType.FB_FRIEND;
        } else {
            this.mProfileType = ProfileType.SELF;
        }
    }

    public void setReturnRouteInfo(String str, String str2) {
        this.mReturnRoute = str;
        this.mReturnUri = str2;
    }

    @Nullable
    public String getReturnRoute() {
        return this.mReturnRoute;
    }

    @Nullable
    public String getReturnUri() {
        return this.mReturnUri;
    }

    @Nullable
    public ConnectivityManager getConnection() {
        return this.mConnection;
    }

    public void clearReturnInfo() {
        this.mReturnRoute = null;
        this.mReturnUri = null;
    }

    public void registerObserver(ProfileDataObserver profileDataObserver) {
        if (!this.mObservers.contains(profileDataObserver)) {
            this.mObservers.add(profileDataObserver);
        }
    }

    public void removeObserver(ProfileDataObserver profileDataObserver) {
        if (this.mObservers.contains(profileDataObserver)) {
            this.mObservers.remove(profileDataObserver);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateObservers() {
        Iterator<ProfileDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().updateProfile();
        }
    }

    public void refreshData() {
        clearAllAsyncHandlers();
        if ((getConnection() == null || getConnection().getActiveNetwork() == null) ? false : true) {
            this.mHasInternetConnection = true;
            int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$ProfileViewModel$ProfileType[this.mProfileType.ordinal()];
            if (i == 1) {
                refreshSelfProfileData();
            } else if (i == 2) {
                refreshVRProfile();
            } else if (i == 3) {
                refreshFbFriendProfileData();
            }
        } else {
            setOfflineView();
        }
    }

    private void refreshSelfProfileData() {
        setLoadingProfile(true);
        this.mFetchProfileContentAsyncQueryHandle = HorizonContentProviderHelper.fetchProfileContent(this.mContext, new HorizonContentProviderHelper.FetchProfileContentCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchProfileContentCallback
            public void onSuccess(SelfVRProfileContent selfVRProfileContent) {
                ProfileViewModel.this.mProfileContent = selfVRProfileContent;
                ProfileViewModel.this.updateObservers();
                ProfileViewModel.this.completeProfileFetch();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileViewModel.this.mPanelApp, "refresh_self_profile_data", ProfileViewModel.TAG, "Failed to fetch self profile content");
                ProfileViewModel.this.completeProfileFetch();
            }
        });
    }

    private void refreshVRProfile() {
        setLoadingProfile(true);
        String str = TAG;
        Log.d(str, "refreshVRProfile() for user: " + this.mVRUserId);
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchVRProfileAsyncQueryHandler = HorizonContentProviderHelper.fetchVRProfileContent(this.mContext, this.mVRUserId, new HorizonContentProviderHelper.FetchVRProfileContentCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchVRProfileContentCallback
            public void onSuccess(VRProfileContent vRProfileContent) {
                String str = ProfileViewModel.TAG;
                Log.d(str, "loadVRProfile() - Fetched vr profile content, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                ProfileViewModel.this.mProfileContent = vRProfileContent;
                ProfileViewModel.this.updateObservers();
                ProfileViewModel.this.completeProfileFetch();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = ProfileViewModel.this.mPanelApp;
                String str = ProfileViewModel.TAG;
                SocialLogger.logError(anytimeUIAndroidPanelAppV2, "load_vr_profile_content", str, "Failed to fetch vr profile content, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                ProfileViewModel.this.completeProfileFetch();
            }
        });
    }

    private void refreshFbFriendProfileData() {
        setLoadingProfile(true);
        this.mGetFbFriendPrimaryProfileAsyncQueryHandle = HorizonContentProviderHelper.getFbFriendPrimaryProfile(this.mContext, this.mFbFriendPrimaryProfileId, new HorizonContentProviderHelper.GetFbFriendPrimaryProfileCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.GetFbFriendPrimaryProfileCallback
            public void onSuccess(FbFriendPrimaryProfile fbFriendPrimaryProfile) {
                ProfileViewModel.this.mProfileContent = fbFriendPrimaryProfile;
                ProfileViewModel.this.updateObservers();
                ProfileViewModel.this.completeProfileFetch();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(ProfileViewModel.this.mPanelApp, "refresh_fb_friend_profile_data", ProfileViewModel.TAG, "Failed to fetch fb friend profile content");
                ProfileViewModel.this.completeProfileFetch();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void completeProfileFetch() {
        this.mLoadingError = this.mProfileContent == null;
        setLoadingProfile(false);
        notifyChange();
        clearAllAsyncHandlers();
    }

    private void clearAllAsyncHandlers() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchProfileContentAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchProfileContentAsyncQueryHandle = null;
        }
        AsyncQueryHandle asyncQueryHandle2 = this.mFetchVRProfileAsyncQueryHandler;
        if (asyncQueryHandle2 != null) {
            asyncQueryHandle2.destroy();
            this.mFetchVRProfileAsyncQueryHandler = null;
        }
        AsyncQueryHandle asyncQueryHandle3 = this.mGetFbFriendPrimaryProfileAsyncQueryHandle;
        if (asyncQueryHandle3 != null) {
            asyncQueryHandle3.destroy();
            this.mGetFbFriendPrimaryProfileAsyncQueryHandle = null;
        }
    }

    public String getLocalUserId() {
        return this.mLocalUserId;
    }

    public String getUserId() {
        int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$ProfileViewModel$ProfileType[this.mProfileType.ordinal()];
        if (i == 1) {
            return this.mLocalUserId;
        }
        if (i == 2) {
            return this.mVRUserId;
        }
        if (i != 3) {
            return null;
        }
        return this.mFbFriendPrimaryProfileId;
    }

    @Nullable
    public String getLinkedOculusUserId() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof FbFriendPrimaryProfile) {
            return ((FbFriendPrimaryProfile) profileContent).getLinkedOculusUserId();
        }
        return null;
    }

    @Nullable
    public String getViewerFbId() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof FbFriendPrimaryProfile) {
            return ((FbFriendPrimaryProfile) profileContent).getViewerFbId();
        }
        return null;
    }

    @Nullable
    public LinkedAccountsInfo getLinkedAccountsInfo() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof VRProfileContent) {
            return ((VRProfileContent) profileContent).getLinkedAccountsInfo();
        }
        return null;
    }

    @Bindable
    public String getAlias() {
        ProfileContent profileContent = this.mProfileContent;
        return profileContent != null ? profileContent.getAlias() : "";
    }

    @Bindable
    public String getRealName() {
        ProfileContent profileContent = this.mProfileContent;
        return profileContent != null ? profileContent.getRealName() : "";
    }

    @Bindable
    public String getBio() {
        ProfileContent profileContent = this.mProfileContent;
        return profileContent != null ? profileContent.getBiography() : "";
    }

    @Bindable
    public boolean getShowBiography() {
        return getShowProfileContent() && !getEditMode();
    }

    public String getProfilePictureUri() {
        ProfileContent profileContent = this.mProfileContent;
        return profileContent != null ? profileContent.getProfilePictureUri() : "";
    }

    public String getAvatarUrl() {
        ProfileContent profileContent = this.mProfileContent;
        return profileContent != null ? profileContent.getAvatarUrl() : "";
    }

    @Bindable
    public boolean getUserHasAvatar() {
        return !getAvatarUrl().equals("");
    }

    public boolean hasPresenceString() {
        if (this.mProfileType == ProfileType.FB_FRIEND) {
            return getPresenceString() != "";
        }
        return getIsUserActive();
    }

    @Bindable
    public String getPresenceString() {
        if (this.mProfileContent == null) {
            return "";
        }
        int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$ProfileViewModel$ProfileType[this.mProfileType.ordinal()];
        if (i == 1 || i == 2) {
            return this.mProfileContent.getPresence(this.mContext.getResources()).getPresenceString();
        }
        if (i == 3 && this.mShowFbPresenceString) {
            return this.mProfileContent.getPresence(this.mContext.getResources()).getPresenceString();
        }
        return "";
    }

    @Bindable
    public boolean getShowPresenceString() {
        return getShowProfileContent() && !this.mEditMode && this.mProfileContent.getPresence(this.mContext.getResources()).getPresenceType() != ProfilePresenceType.NONE;
    }

    @Bindable
    public boolean getShowPresenceIcon() {
        ProfileContent profileContent;
        return getShowProfileContent() && !this.mEditMode && (profileContent = this.mProfileContent) != null && profileContent.getIsUserActive().booleanValue() && this.mProfileContent.getPresence(this.mContext.getResources()).getPresenceType() != ProfilePresenceType.NONE;
    }

    @Bindable
    public Drawable getPresenceIcon() {
        int i = R.drawable.oc_icon_headset_filled_16_d2d2d2;
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent != null && profileContent.getPresence(this.mContext.getResources()).getPresenceType() == ProfilePresenceType.MESSENGER) {
            i = R.drawable.oc_icon_mobile_filled_16_d2d2d2;
        }
        return this.mContext.getDrawable(i);
    }

    @Bindable
    public int getPresenceStringColor() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent == null || !profileContent.getIsUserActive().booleanValue()) {
            return this.mContext.getResources().getColor(R.color.octypography_secondary_color);
        }
        return this.mContext.getResources().getColor(R.color.octypography_positive_color);
    }

    @Bindable
    public boolean getIsUserActive() {
        ProfileContent profileContent = this.mProfileContent;
        return profileContent != null && profileContent.getIsUserActive().booleanValue();
    }

    private boolean isUserBlocked() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof VRProfileContent) {
            return ((VRProfileContent) profileContent).getIsUserBlocked().booleanValue();
        }
        return false;
    }

    @Nullable
    private SocialUserFriendshipStatus getFriendshipStatusInVr() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent != null) {
            return profileContent.getFriendshipStatusInVr();
        }
        return null;
    }

    public int getFriendCount() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent != null) {
            return profileContent.getFriendCount();
        }
        return 0;
    }

    @Bindable
    public boolean getShowFriendCount() {
        return getShowProfileContent() && this.mProfileType == ProfileType.SELF && !this.mEditMode;
    }

    @Nullable
    public String getViewerCurrentPartyId() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof VRProfileContent) {
            return ((VRProfileContent) profileContent).getViewerCurrentPartyId();
        }
        if (profileContent instanceof FbFriendPrimaryProfile) {
            return ((FbFriendPrimaryProfile) profileContent).getViewerCurrentPartyId();
        }
        return null;
    }

    @Nullable
    public String getJoinablePartyId() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof VRProfileContent) {
            return ((VRProfileContent) profileContent).getJoinablePartyId();
        }
        if (profileContent instanceof FbFriendPrimaryProfile) {
            return ((FbFriendPrimaryProfile) profileContent).getJoinablePartyId();
        }
        return null;
    }

    private boolean getCanInviteUserToParty() {
        ProfileContent profileContent = this.mProfileContent;
        if (profileContent instanceof VRProfileContent) {
            return ((VRProfileContent) profileContent).getCanInviteUserToParty().booleanValue();
        }
        if (profileContent instanceof FbFriendPrimaryProfile) {
            return ((FbFriendPrimaryProfile) profileContent).getCanInviteUserToParty().booleanValue();
        }
        return false;
    }

    public void setEditMode(boolean z) {
        this.mEditMode = z;
        notifyChange();
    }

    @Bindable
    public boolean getEditMode() {
        return this.mEditMode;
    }

    @Bindable
    public boolean getShowEditControls() {
        return getShowProfileContent() && getEditMode();
    }

    public void setIsSavingChanges(boolean z) {
        this.mIsSavingChanges = z;
        notifyPropertyChanged(BR.isSavingChanges);
    }

    @Bindable
    public boolean getIsSavingChanges() {
        if (this.mProfileType == ProfileType.SELF) {
            return this.mIsSavingChanges;
        }
        return false;
    }

    @Bindable
    public boolean getShowSavingChangesSpinner() {
        return !getLoadingProfile() && getIsSavingChanges();
    }

    private void setLoadingProfile(boolean z) {
        this.mLoadingProfile = z;
    }

    @Bindable
    public boolean getLoadingProfile() {
        return this.mLoadingProfile;
    }

    @Bindable
    public boolean getShowProfileContent() {
        return !getLoadingProfile() && getHasInternetConnection() && !getShowLoadingError();
    }

    @Bindable
    public boolean getHasInternetConnection() {
        return this.mHasInternetConnection;
    }

    public ProfileType getProfileType() {
        return this.mProfileType;
    }

    public boolean isFBProfile() {
        return this.mProfileType == ProfileType.FB_FRIEND;
    }

    public boolean isSelfProfile() {
        return this.mProfileType == ProfileType.SELF;
    }

    @Bindable
    public boolean getShowHeader() {
        return this.mEditMode;
    }

    @Bindable
    public boolean getShowBackButton() {
        return !getLoadingProfile() && this.mReturnRoute != null;
    }

    @Bindable
    public boolean getHasCompleteProfile() {
        if (this.mProfileType != ProfileType.SELF) {
            return true;
        }
        if (getProfilePictureUri().equals("") || !getUserHasAvatar()) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean isCompleteProfileButtonVisible() {
        return getShowProfileContent() && !getEditMode() && !getHasCompleteProfile();
    }

    public void setCanShowUserSwitcher(boolean z) {
        this.mCanShowUserSwitcher = z;
    }

    public boolean shouldShowUserSwitcher() {
        return this.mCanShowUserSwitcher && this.mProfileType == ProfileType.SELF && !this.mEditMode;
    }

    @Bindable
    public boolean getShowPrimaryButton() {
        return getShowProfileContent() && !this.mEditMode && getPrimaryButtonType() != ProfilePrimaryButtonType.DEFAULT;
    }

    @Bindable
    public boolean getShowLoadingError() {
        return !getLoadingProfile() && this.mLoadingError && this.mHasInternetConnection;
    }

    @Bindable
    public ProfilePrimaryButtonType getPrimaryButtonType() {
        SocialUserFriendshipStatus friendshipStatusInVr = getFriendshipStatusInVr();
        if (this.mProfileType == ProfileType.SELF) {
            return getHasCompleteProfile() ? ProfilePrimaryButtonType.EDIT : ProfilePrimaryButtonType.COMPLETE_PROFILE;
        }
        if (this.mProfileType == ProfileType.VR_USER && friendshipStatusInVr != null) {
            switch (friendshipStatusInVr) {
                case ARE_FRIENDS:
                    if (!this.mShowOcPartyButton) {
                        return ProfilePrimaryButtonType.DEFAULT;
                    }
                    if (getCanInviteUserToParty() && getViewerCurrentPartyId() != null) {
                        return ProfilePrimaryButtonType.VR_INVITE_TO_PARTY;
                    }
                    if (getJoinablePartyId() != null) {
                        return ProfilePrimaryButtonType.VR_JOIN_PARTY;
                    }
                    if (!getCanInviteUserToParty() || getViewerCurrentPartyId() != null) {
                        return ProfilePrimaryButtonType.DEFAULT;
                    }
                    return ProfilePrimaryButtonType.VR_CREATE_PARTY;
                case CAN_REQUEST:
                    return ProfilePrimaryButtonType.VR_USER;
                case INCOMING_REQUEST:
                    return ProfilePrimaryButtonType.INCOMING_FRIEND_REQUEST;
                case OUTGOING_REQUEST:
                    return ProfilePrimaryButtonType.VR_USER_OUTGOING_REQUEST;
                case CANNOT_REQUEST:
                    return this.mShowMessengerActions ? ProfilePrimaryButtonType.CHAT : ProfilePrimaryButtonType.DEFAULT;
                case BLOCKED:
                case UNKNOWN:
                    return ProfilePrimaryButtonType.DEFAULT;
                default:
                    SocialLogger.logError(this.mPanelApp, "get_primary_button_type", TAG, "Profile primary button received unexpected friendship status");
                    return ProfilePrimaryButtonType.DEFAULT;
            }
        } else if (this.mProfileType != ProfileType.FB_FRIEND) {
            return ProfilePrimaryButtonType.DEFAULT;
        } else {
            if (this.mShowFbPartyButton && friendshipStatusInVr == SocialUserFriendshipStatus.ARE_FRIENDS && getLinkedOculusUserId() != null) {
                if (getCanInviteUserToParty() && getViewerCurrentPartyId() != null) {
                    return ProfilePrimaryButtonType.FB_INVITE_TO_PARTY;
                }
                if (getJoinablePartyId() != null) {
                    return ProfilePrimaryButtonType.FB_JOIN_PARTY;
                }
                if (getCanInviteUserToParty() && getViewerCurrentPartyId() == null) {
                    return ProfilePrimaryButtonType.FB_CREATE_PARTY;
                }
            }
            return this.mShowMessengerActions ? ProfilePrimaryButtonType.FB_CHAT : ProfilePrimaryButtonType.DEFAULT;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$ProfileViewModel$ProfileType = new int[ProfileType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|28) */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0069 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0073 */
        static {
            /*
            // Method dump skipped, instructions count: 126
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel.AnonymousClass4.<clinit>():void");
        }
    }

    @Bindable
    public Drawable getPrimaryButtonDrawable() {
        return this.mContext.getDrawable(getPrimaryButtonType().getIconId());
    }

    @Bindable
    public boolean getIsPrimaryButtonLoading() {
        return this.mPrimaryButtonLoading;
    }

    public void setIsPrimaryButtonLoading(boolean z) {
        this.mPrimaryButtonLoading = z;
        notifyPropertyChanged(BR.isPrimaryButtonLoading);
    }

    @Bindable
    public boolean getShowSecondaryButton() {
        return getShowProfileContent() && !this.mEditMode && getSecondaryButtonType() != ProfileSecondaryButtonType.DEFAULT;
    }

    @Bindable
    public ProfileSecondaryButtonType getSecondaryButtonType() {
        getFriendshipStatusInVr();
        if (this.mProfileType == ProfileType.VR_USER) {
            if (SocialUserFriendshipStatus.CANNOT_REQUEST.equals(getFriendshipStatusInVr())) {
                return ProfileSecondaryButtonType.ADD_FRIEND;
            }
            if (!isUserBlocked()) {
                return this.mShowMessengerActions ? ProfileSecondaryButtonType.CHAT : ProfileSecondaryButtonType.DEFAULT;
            }
        } else if (this.mProfileType == ProfileType.FB_FRIEND && getPrimaryButtonType() != ProfilePrimaryButtonType.FB_CHAT) {
            return this.mShowMessengerActions ? ProfileSecondaryButtonType.FB_CHAT : ProfileSecondaryButtonType.DEFAULT;
        }
        return ProfileSecondaryButtonType.DEFAULT;
    }

    @Bindable
    public boolean getSecondaryButtonEnabled() {
        return this.mProfileType != ProfileType.VR_USER || !SocialUserFriendshipStatus.CANNOT_REQUEST.equals(getFriendshipStatusInVr());
    }

    @Bindable
    public Drawable getSecondaryButtonDrawable() {
        return this.mContext.getDrawable(getSecondaryButtonType().getIconId());
    }

    @Bindable
    public boolean getShowContextMenu() {
        return getShowProfileContent() && !this.mEditMode && getContextMenuItems().size() > 0;
    }

    public List<ProfileContextMenuItem> getContextMenuItems() {
        ArrayList arrayList = new ArrayList();
        if (this.mProfileType != ProfileType.FB_FRIEND && !isUserBlocked()) {
            arrayList.add(ProfileContextMenuItem.VIEW_FULL_PROFILE);
        }
        if (this.mProfileType == ProfileType.FB_FRIEND) {
            arrayList.add(ProfileContextMenuItem.FB_BLOCK);
            arrayList.add(ProfileContextMenuItem.FB_REPORT);
        } else if (this.mProfileType == ProfileType.VR_USER) {
            if (getFriendshipStatusInVr() == SocialUserFriendshipStatus.ARE_FRIENDS) {
                arrayList.add(ProfileContextMenuItem.UNFRIEND_USER);
            } else if (getFriendshipStatusInVr() == SocialUserFriendshipStatus.INCOMING_REQUEST) {
                arrayList.add(ProfileContextMenuItem.REJECT_FRIEND_REQUEST);
            }
            if (isUserBlocked()) {
                arrayList.add(ProfileContextMenuItem.VR_UNBLOCK);
            } else {
                arrayList.add(ProfileContextMenuItem.VR_BLOCK);
            }
            arrayList.add(ProfileContextMenuItem.VR_REPORT);
        } else {
            if (this.mPanelApp.isGKEnabled(Gatekeeper.PROFILE_SHARING)) {
                arrayList.add(ProfileContextMenuItem.SHARE);
            }
            arrayList.add(ProfileContextMenuItem.PRIVACY);
        }
        return arrayList;
    }

    @Bindable
    public boolean getIsOverflowButtonLoading() {
        return this.mOverflowButtonLoading;
    }

    @Bindable
    public void setIsOverflowButtonLoading(boolean z) {
        this.mOverflowButtonLoading = z;
        notifyPropertyChanged(BR.isOverflowButtonLoading);
    }
}
