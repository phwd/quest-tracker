package com.oculus.panelapp.androiddialog.dialogs.social.join_party;

import android.content.Context;
import android.util.Log;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.google.common.base.Strings;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.apps.models.GroupLaunchDestination;
import com.oculus.horizoncontent.apps.models.PartyInviteInfo;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class PartyInviteViewModel extends SocialViewModel implements SocialUserViewModel.SocialViewModelDataObserver {
    public static final int PARTY_BAD_PERMISSIONS = 1357006;
    public static final int PARTY_FULL_ERROR = 1891174;
    public static final int PARTY_NO_LONGER_AVAILABLE_ERROR = 1891316;
    private static final String TAG = LoggingUtil.tag(PartyInviteViewModel.class);
    private List<SocialUserViewModel> mBlockedPartyUsers = Collections.emptyList();
    private final Context mContext;
    private String mCorrelationId;
    private String mCurrentPartyId;
    private String mDeeplinkTargetId;
    private GroupLaunchDestination mGroupLaunchDestination;
    private String mLinkNonce;
    private String mPartyId;
    private PartyInviteRequestFactory mPartyInviteRequestFactory;
    private SocialUserViewModel mPartyLeader;
    private PartyInviteInfo.PartyType mPartyType = PartyInviteInfo.PartyType.UNKNOWN;
    private List<SocialUserViewModel> mPartyUsers;
    private String mSource;
    private String mViewerAlias;
    private SocialUserRequestFactory socialUserRequestFactory;

    public static class PartyDataCallback {

        @FunctionalInterface
        public interface Error {
            void onError(ErrorType errorType);
        }

        @FunctionalInterface
        public interface Success {
            void onSuccess();
        }
    }

    public PartyInviteViewModel(Context context, PartyInviteRequestFactory partyInviteRequestFactory, SocialUserRequestFactory socialUserRequestFactory2) {
        this.mContext = context;
        this.mPartyInviteRequestFactory = partyInviteRequestFactory;
        this.mPartyLeader = new SocialUserViewModel(socialUserRequestFactory2);
        this.socialUserRequestFactory = socialUserRequestFactory2;
    }

    public void fetch(PartyDataCallback.Success success, PartyDataCallback.Error error) {
        if (hasInvalidInputs()) {
            error.onError(ErrorType.PARTY_GENERAL);
            return;
        }
        String str = this.mPartyId;
        if (str == null || "".equals(str)) {
            String str2 = TAG;
            Log.d(str2, "fetch: Fetching party invite info using deeplink id" + this.mDeeplinkTargetId);
            registerQueryHandle("partyInfo", fetchPartyInviteInfoWithNonce(this.mDeeplinkTargetId, this.mLinkNonce, success, error));
            return;
        }
        String str3 = TAG;
        Log.d(str3, "fetch: Fetching party invite info for " + this.mPartyId);
        registerQueryHandle("partyInfo", fetchPartyInviteInfo(this.mPartyId, success, error));
    }

    public void joinParty(Context context, PartyDataCallback.Success success, PartyDataCallback.Error error) {
        if (hasInvalidInputs()) {
            error.onError(ErrorType.PARTY_GENERAL);
        } else {
            registerQueryHandle(CommonSystemDialogActions.JOIN_PARTY, new Function(context, success, error) {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$PartyInviteViewModel$3fzOCQydKHzeCYs0ZBbcF5Iv3DA */
                private final /* synthetic */ Context f$1;
                private final /* synthetic */ PartyInviteViewModel.PartyDataCallback.Success f$2;
                private final /* synthetic */ PartyInviteViewModel.PartyDataCallback.Error f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PartyInviteViewModel.this.lambda$joinParty$107$PartyInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
                }
            });
        }
    }

    public /* synthetic */ AsyncQueryHandle lambda$joinParty$107$PartyInviteViewModel(Context context, final PartyDataCallback.Success success, final PartyDataCallback.Error error, final String str) {
        return this.mPartyInviteRequestFactory.joinParty(this.mPartyId, this.mLinkNonce, installedGroupLaunchApplicationId(context), new HorizonContentProviderHelper.SingleIDCallbackWithErrorCode() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallbackWithErrorCode
            public void onSuccess(String str) {
                String str2 = PartyInviteViewModel.TAG;
                Log.d(str2, "onSuccess: Joined party_id: " + str);
                success.onSuccess();
                PartyInviteViewModel.this.clearHandle(str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallbackWithErrorCode
            public void onError(int i) {
                String str = PartyInviteViewModel.TAG;
                Log.d(str, "onError: received error " + i + " while joining party " + PartyInviteViewModel.this.mPartyId);
                if (i != 1357006) {
                    if (i == 1891174) {
                        error.onError(ErrorType.PARTY_FULL);
                    } else if (i != 1891316) {
                        error.onError(ErrorType.PARTY_GENERAL);
                    }
                    PartyInviteViewModel.this.clearHandle(str);
                }
                error.onError(ErrorType.PARTY_NO_LONGER_AVAILABLE);
                PartyInviteViewModel.this.clearHandle(str);
            }
        });
    }

    private String installedGroupLaunchApplicationId(Context context) {
        GroupLaunchDestination groupLaunchDestination = this.mGroupLaunchDestination;
        if (!(groupLaunchDestination == null || groupLaunchDestination.getApplication() == null)) {
            String id = this.mGroupLaunchDestination.getApplication().getId();
            if (HorizonUtil.isApplicationInstalled(context, id)) {
                String str = TAG;
                Log.d(str, "installedGroupLaunchApplicationId: " + id);
                return id;
            }
        }
        return null;
    }

    private Function<String, AsyncQueryHandle> fetchPartyInviteInfo(String str, PartyDataCallback.Success success, PartyDataCallback.Error error) {
        return new Function(str, success, error) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$PartyInviteViewModel$GvWXJNwUIqajVvbpaSEt9ke7xqQ */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ PartyInviteViewModel.PartyDataCallback.Success f$2;
            private final /* synthetic */ PartyInviteViewModel.PartyDataCallback.Error f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PartyInviteViewModel.this.lambda$fetchPartyInviteInfo$108$PartyInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        };
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchPartyInviteInfo$108$PartyInviteViewModel(String str, PartyDataCallback.Success success, PartyDataCallback.Error error, String str2) {
        return this.mPartyInviteRequestFactory.fetchPartyInviteInfo(str, partyInviteInfoCallback(str2, success, error));
    }

    private Function<String, AsyncQueryHandle> fetchPartyInviteInfoWithNonce(String str, String str2, PartyDataCallback.Success success, PartyDataCallback.Error error) {
        return new Function(str, str2, success, error) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$PartyInviteViewModel$BxQ3hzVNPd6h684FYrzpZgIhSJw */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ PartyInviteViewModel.PartyDataCallback.Success f$3;
            private final /* synthetic */ PartyInviteViewModel.PartyDataCallback.Error f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PartyInviteViewModel.this.lambda$fetchPartyInviteInfoWithNonce$109$PartyInviteViewModel(this.f$1, this.f$2, this.f$3, this.f$4, (String) obj);
            }
        };
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchPartyInviteInfoWithNonce$109$PartyInviteViewModel(String str, String str2, PartyDataCallback.Success success, PartyDataCallback.Error error, String str3) {
        return this.mPartyInviteRequestFactory.fetchPartyInviteInfoWithNonce(str, str2, partyInviteInfoCallback(str3, success, error));
    }

    private HorizonContentProviderHelper.FetchPartyInviteInfoCallback partyInviteInfoCallback(final String str, final PartyDataCallback.Success success, final PartyDataCallback.Error error) {
        return new HorizonContentProviderHelper.FetchPartyInviteInfoCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPartyInviteInfoCallback
            public void onSuccess(PartyInviteInfo partyInviteInfo) {
                String str = PartyInviteViewModel.TAG;
                Log.d(str, "onSuccess: Received " + partyInviteInfo);
                PartyInviteViewModel.this.mPartyId = partyInviteInfo.getId();
                PartyInviteViewModel.this.mCurrentPartyId = partyInviteInfo.getCurrentPartyId();
                PartyInviteViewModel.this.mPartyType = partyInviteInfo.getPartyType();
                if (partyInviteInfo.getPartyLeader() != null) {
                    PartyInviteViewModel partyInviteViewModel = PartyInviteViewModel.this;
                    partyInviteViewModel.mPartyLeader = partyInviteViewModel.mPartyLeader.updateWith(partyInviteInfo.getPartyLeader());
                }
                PartyInviteViewModel.this.setPartyUsers(partyInviteInfo.getPartyUsers());
                PartyInviteViewModel.this.setBlockedUsers(partyInviteInfo.getBlockedPartyUsers());
                PartyInviteViewModel.this.mGroupLaunchDestination = partyInviteInfo.getGroupLaunchDestination();
                success.onSuccess();
                PartyInviteViewModel.this.notifyChange();
                PartyInviteViewModel.this.clearHandle(str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.d(PartyInviteViewModel.TAG, "onError: An error occurred while fetching party-invite info");
                error.onError(ErrorType.PARTY_GENERAL);
                PartyInviteViewModel.this.clearHandle(str);
            }
        };
    }

    @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel.SocialViewModelDataObserver
    public void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel) {
        if (socialUserViewModel.getUserType().equals(SocialUserViewModel.UserType.VIEWER)) {
            setViewerAlias(socialUserViewModel.getAlias());
        }
    }

    @Bindable
    public String getViewerAlias() {
        return this.mViewerAlias;
    }

    public void setViewerAlias(String str) {
        this.mViewerAlias = str;
        notifyPropertyChanged(BR.viewerAlias);
    }

    @Bindable
    public String getPartyId() {
        return this.mPartyId;
    }

    @Bindable
    public void setPartyId(String str) {
        this.mPartyId = str;
        notifyPropertyChanged(BR.partyId);
    }

    @Bindable
    public String getCurrentPartyId() {
        return this.mCurrentPartyId;
    }

    @Bindable
    public void setCurrentPartyId(String str) {
        this.mCurrentPartyId = str;
        notifyPropertyChanged(BR.currentPartyId);
    }

    @Bindable
    public PartyInviteInfo.PartyType getPartyType() {
        return this.mPartyType;
    }

    @Bindable
    public void setPartyType(PartyInviteInfo.PartyType partyType) {
        this.mPartyType = partyType;
        notifyPropertyChanged(BR.partyType);
    }

    @Bindable({"displayablePartyUsers"})
    public String getPartyTitle() {
        if (this.mPartyUsers == null) {
            return this.mContext.getString(R.string.social_join_party_dialog_privacy_default);
        }
        List<SocialUserViewModel> displayablePartyUsers = getDisplayablePartyUsers();
        int size = displayablePartyUsers.size();
        String str = null;
        String alias = size >= 1 ? displayablePartyUsers.get(0).getAlias() : null;
        if (size >= 2) {
            str = displayablePartyUsers.get(1).getAlias();
        }
        if (size == 0) {
            return this.mContext.getString(R.string.social_join_party_dialog_empty_party_description);
        }
        if (size == 1) {
            return this.mContext.getString(R.string.social_join_party_dialog_one_person_in_party_description, alias);
        } else if (size == 2) {
            return this.mContext.getString(R.string.social_join_party_dialog_two_people_in_party_description, alias, str);
        } else if (size != 3) {
            return this.mContext.getString(R.string.social_join_party_dialog_many_people_in_party_description, alias, str, Integer.valueOf(size - 2));
        } else {
            return this.mContext.getString(R.string.social_join_party_dialog_few_people_in_party_description, alias, str);
        }
    }

    @Bindable({"partyUsers", "viewerAlias"})
    public List<SocialUserViewModel> getDisplayablePartyUsers() {
        ArrayList arrayList = new ArrayList();
        List<SocialUserViewModel> list = this.mPartyUsers;
        if (list == null) {
            return arrayList;
        }
        for (SocialUserViewModel socialUserViewModel : list) {
            if (!socialUserViewModel.getAlias().equals(this.mViewerAlias)) {
                arrayList.add(socialUserViewModel);
            }
        }
        arrayList.addAll(this.mBlockedPartyUsers);
        return arrayList;
    }

    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType = new int[PartyInviteInfo.PartyType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.horizoncontent.apps.models.PartyInviteInfo$PartyType[] r0 = com.oculus.horizoncontent.apps.models.PartyInviteInfo.PartyType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.apps.models.PartyInviteInfo$PartyType r1 = com.oculus.horizoncontent.apps.models.PartyInviteInfo.PartyType.OPEN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.apps.models.PartyInviteInfo$PartyType r1 = com.oculus.horizoncontent.apps.models.PartyInviteInfo.PartyType.CLOSED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.horizoncontent.apps.models.PartyInviteInfo$PartyType r1 = com.oculus.horizoncontent.apps.models.PartyInviteInfo.PartyType.JOINABLE_BY_FRIENDS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.AnonymousClass3.<clinit>():void");
        }
    }

    @Bindable({"partyType"})
    public String getPartyTypeDescription() {
        int i = AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType[this.mPartyType.ordinal()];
        if (i == 1) {
            return this.mContext.getString(R.string.social_join_party_dialog_privacy_public);
        }
        if (i == 2) {
            return this.mContext.getString(R.string.social_join_party_dialog_privacy_invite_only);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getString(R.string.social_join_party_dialog_privacy_joinable_by_friends);
    }

    @Bindable({"partyType"})
    public int getPartyTypeIconImage() {
        int i = AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$apps$models$PartyInviteInfo$PartyType[this.mPartyType.ordinal()];
        if (i == 1) {
            return R.drawable.oc_icon_link_filled_24_d2d2d2;
        }
        if (i == 2) {
            return R.drawable.oc_icon_lock_filled_24_d2d2d2;
        }
        if (i != 3) {
            return R.drawable.oc_icon_info_filled_24_d2d2d2;
        }
        return R.drawable.oc_icon_friends_filled_24_d2d2d2;
    }

    @Bindable({"displayablePartyUsers"})
    public String getFirstPartyUserImageUrl() {
        return getProfilePictureFromPartyUser(0);
    }

    @Bindable({"displayablePartyUsers"})
    public String getSecondPartyUserImageUrl() {
        return getProfilePictureFromPartyUser(1);
    }

    private String getProfilePictureFromPartyUser(int i) {
        List<SocialUserViewModel> displayablePartyUsers = getDisplayablePartyUsers();
        if (displayablePartyUsers.size() > i) {
            return displayablePartyUsers.get(i).getProfilePhotoUrl();
        }
        return null;
    }

    @Bindable
    public SocialUserViewModel getPartyLeader() {
        return this.mPartyLeader;
    }

    @Bindable
    public void setPartyLeader(SocialUserViewModel socialUserViewModel) {
        this.mPartyLeader = socialUserViewModel;
        notifyPropertyChanged(BR.partyLeader);
    }

    @Bindable
    public void setGroupLaunchDestination(GroupLaunchDestination groupLaunchDestination) {
        this.mGroupLaunchDestination = groupLaunchDestination;
        notifyPropertyChanged(BR.groupLaunchDestination);
    }

    @Bindable({"groupLaunchDestination"})
    public String getGroupLaunchImageUrl() {
        GroupLaunchDestination groupLaunchDestination = this.mGroupLaunchDestination;
        if (groupLaunchDestination != null) {
            return groupLaunchDestination.getApplication().getImageUrl();
        }
        return null;
    }

    @Bindable({"groupLaunchDestination"})
    public String getGroupLaunchDestination() {
        GroupLaunchDestination groupLaunchDestination = this.mGroupLaunchDestination;
        if (groupLaunchDestination != null) {
            return groupLaunchDestination.getDisplayName();
        }
        return null;
    }

    @Bindable({"groupLaunchDestination"})
    public String getGroupLaunchApplication() {
        GroupLaunchDestination groupLaunchDestination = this.mGroupLaunchDestination;
        if (groupLaunchDestination != null) {
            return groupLaunchDestination.getApplication().getDisplayName();
        }
        return null;
    }

    @Bindable
    public List<SocialUserViewModel> getPartyUsers() {
        return this.mPartyUsers;
    }

    public void setPartyUsers(List<SocialUser> list) {
        this.mPartyUsers = new ArrayList();
        for (SocialUser socialUser : list) {
            this.mPartyUsers.add(new SocialUserViewModel(this.socialUserRequestFactory).updateWith(socialUser));
        }
        notifyPropertyChanged(BR.partyUsers);
    }

    @Bindable
    public List<SocialUserViewModel> getBlockedPartyUsers() {
        return this.mBlockedPartyUsers;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBlockedUsers(List<SocialUser> list) {
        this.mBlockedPartyUsers = new ArrayList();
        for (SocialUser socialUser : list) {
            this.mBlockedPartyUsers.add(new SocialUserViewModel(this.socialUserRequestFactory).updateWith(socialUser));
        }
        notifyPropertyChanged(BR.blockedPartyUsers);
    }

    @Bindable({"blockedPartyUsers"})
    public String getBlockedUsersWarning() {
        List<SocialUserViewModel> list = this.mBlockedPartyUsers;
        String str = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        int size = this.mBlockedPartyUsers.size();
        String alias = this.mBlockedPartyUsers.get(0).getAlias();
        if (size >= 2) {
            str = this.mBlockedPartyUsers.get(1).getAlias();
        }
        if (size == 1) {
            return this.mContext.getString(R.string.social_join_party_dialog_blocked_single_user_description, alias);
        } else if (size != 2) {
            return this.mContext.getString(R.string.social_join_party_dialog_blocked_many_user_description, alias, str, Integer.valueOf(size), Integer.valueOf(size - 2));
        } else {
            return this.mContext.getString(R.string.social_join_party_dialog_blocked_two_user_description, alias, str);
        }
    }

    private boolean hasInvalidInputs() {
        boolean z = !Strings.isNullOrEmpty(this.mPartyId);
        boolean z2 = !Strings.isNullOrEmpty(this.mDeeplinkTargetId) && !Strings.isNullOrEmpty(this.mLinkNonce);
        if (z || z2) {
            return false;
        }
        return true;
    }

    public void setDeeplinkTargetId(String str) {
        this.mDeeplinkTargetId = str;
    }

    public void setLinkNonce(String str) {
        this.mLinkNonce = str;
    }

    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    public void setCorrelationId(String str) {
        this.mCorrelationId = str;
    }

    public void setSource(String str) {
        this.mSource = str;
    }

    public String getSource() {
        return this.mSource;
    }
}
