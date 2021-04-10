package com.oculus.panelapp.socialandroidbackpanel.views.join_party;

import X.AnonymousClass006;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.databinding.Bindable;
import com.google.common.base.Strings;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GroupLaunch;
import com.oculus.panelapp.socialandroidbackpanel.graphql.JoinPartyQueryHelper;
import com.oculus.panelapp.socialandroidbackpanel.graphql.Party;
import com.oculus.panelapp.socialandroidbackpanel.graphql.User;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JoinPartyViewModel extends SocialViewModel implements ViewModelLifecycle {
    public static final int DEFAULT_ERROR_CODE = 0;
    public static final int PARTY_BAD_PERMISSIONS = 1357006;
    public static final int PARTY_FULL_ERROR = 1891174;
    public static final int PARTY_NO_LONGER_AVAILABLE_ERROR = 1891316;
    public static final String TAG = LoggingUtil.tag(JoinPartyViewModel.class);
    public final Context mContext;
    public String mCorrelationId;
    public String mCurrentPartyId;
    public String mDeeplinkTargetId;
    public JoinPartyRequestFactory mJoinPartyRequestFactory;
    public String mLinkNonce;
    public Party mParty;
    public String mPartyId;
    public String mSource;
    public User mViewer;
    public final Handler mainHandler = new Handler(Looper.getMainLooper());

    @FunctionalInterface
    public interface Callback {
        void execute();
    }

    @FunctionalInterface
    public interface Error {
        void onError(ErrorType errorType);
    }

    public void fetch(GraphQLService graphQLService, Callback callback, Error error) {
        if (!hasValidInputs()) {
            error.onError(ErrorType.PARTY_GENERAL);
        } else if (!TextUtils.isEmpty(this.mPartyId)) {
            JoinPartyQueryHelper.queryPartyInviteInfoWithId(graphQLService, partyQueryCallback(callback, error), this.mPartyId);
        } else if (!TextUtils.isEmpty(this.mDeeplinkTargetId) && !TextUtils.isEmpty(this.mLinkNonce)) {
            fetchPartyInviteInfoWithNonce(graphQLService, this.mDeeplinkTargetId, this.mLinkNonce, callback, error);
        }
    }

    @Bindable
    public String getFirstPartyUserImageUrl() {
        return getProfilePictureFromPartyUser(0);
    }

    @Bindable
    public String getSecondPartyUserImageUrl() {
        return getProfilePictureFromPartyUser(1);
    }

    public void joinPartyAndStartVoip(Context context, Callback callback, Error error) {
        if (!hasValidInputs()) {
            error.onError(ErrorType.PARTY_GENERAL);
            return;
        }
        String str = this.mPartyId;
        if (str == null) {
            str = this.mDeeplinkTargetId;
        }
        registerQueryHandle("joinParty", new Function(str, context, callback, error) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyViewModel$wbCA2M5ZnX4khjK6tggj_kC134I2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ Context f$2;
            public final /* synthetic */ JoinPartyViewModel.Callback f$3;
            public final /* synthetic */ JoinPartyViewModel.Error f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return JoinPartyViewModel.this.lambda$joinPartyAndStartVoip$0$JoinPartyViewModel(this.f$1, this.f$2, this.f$3, this.f$4, (String) obj);
            }
        });
    }

    public /* synthetic */ AsyncQueryHandle lambda$joinPartyAndStartVoip$0$JoinPartyViewModel(final String str, Context context, final Callback callback, final Error error, final String str2) {
        return this.mJoinPartyRequestFactory.joinParty(str, this.mLinkNonce, installedGroupLaunchApplicationId(context), new HorizonContentProviderHelper.SingleIDCallbackWithErrorCode() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallbackWithErrorCode
            public void onSuccess(String str) {
                callback.execute();
                JoinPartyViewModel.this.clearHandle(str2);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallbackWithErrorCode
            public void onError(int i) {
                Error error;
                ErrorType errorType;
                if (i != 1357006) {
                    if (i == 1891174) {
                        error = error;
                        errorType = ErrorType.PARTY_FULL;
                    } else if (i != 1891316) {
                        error = error;
                        errorType = ErrorType.PARTY_GENERAL;
                    }
                    error.onError(errorType);
                    JoinPartyViewModel.this.clearHandle(str2);
                }
                error = error;
                errorType = ErrorType.PARTY_NO_LONGER_AVAILABLE;
                error.onError(errorType);
                JoinPartyViewModel.this.clearHandle(str2);
            }
        });
    }

    private List<User> getDisplayablePartyUsers() {
        ArrayList arrayList = new ArrayList();
        Party party = this.mParty;
        if (party != null) {
            for (User user : party.partyUsers) {
                if (!user.alias.equals(getViewerAlias())) {
                    arrayList.add(user);
                }
            }
            arrayList.addAll(this.mParty.partyBlockedUsers);
        }
        return arrayList;
    }

    private boolean hasValidInputs() {
        if (!Strings.isNullOrEmpty(this.mPartyId) || (!Strings.isNullOrEmpty(this.mDeeplinkTargetId) && !Strings.isNullOrEmpty(this.mLinkNonce))) {
            return true;
        }
        return false;
    }

    private String installedGroupLaunchApplicationId(Context context) {
        GroupLaunch groupLaunch;
        Party party = this.mParty;
        if (party == null || (groupLaunch = party.partyGroupLaunch) == null) {
            return null;
        }
        String str = groupLaunch.destination.application.id;
        if (HorizonUtil.isApplicationInstalled(context, str)) {
            return str;
        }
        return null;
    }

    private JoinPartyQueryHelper.PartyQueryCallback partyQueryCallback(final Callback callback, final Error error) {
        return new JoinPartyQueryHelper.PartyQueryCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel.AnonymousClass2 */

            public /* synthetic */ void lambda$onSuccess$0$JoinPartyViewModel$2(Callback callback) {
                JoinPartyViewModel.this.notifyChange();
                callback.execute();
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.JoinPartyQueryHelper.PartyQueryCallback
            public void onFailure(String str) {
                Log.e(JoinPartyViewModel.TAG, AnonymousClass006.A07("onError: An error occurred while fetching party-invite info: ", str));
                JoinPartyViewModel.this.mainHandler.post(new Runnable() {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyViewModel$2$FYtkNrCh3EAFY3KR_L3DrURKlfo2 */

                    public final void run() {
                        JoinPartyViewModel.Error.this.onError(ErrorType.PARTY_GENERAL);
                    }
                });
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.JoinPartyQueryHelper.PartyQueryCallback
            public void onSuccess(String str, User user, Party party) {
                JoinPartyViewModel joinPartyViewModel = JoinPartyViewModel.this;
                joinPartyViewModel.mCurrentPartyId = str;
                joinPartyViewModel.mViewer = user;
                joinPartyViewModel.mParty = party;
                joinPartyViewModel.mainHandler.post(new Runnable(callback) {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyViewModel$2$VEywE1khiQ0_5GXa5NVDtN3PX82 */
                    public final /* synthetic */ JoinPartyViewModel.Callback f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        JoinPartyViewModel.AnonymousClass2.this.lambda$onSuccess$0$JoinPartyViewModel$2(this.f$1);
                    }
                });
            }
        };
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialViewModel, com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Bindable
    public String getBlockedUsersWarning() {
        Context context;
        int i;
        Object[] objArr;
        Party party = this.mParty;
        String str = null;
        if (party == null || party.partyBlockedUsers.isEmpty()) {
            return null;
        }
        int size = this.mParty.partyBlockedUsers.size();
        String str2 = this.mParty.partyBlockedUsers.get(0).alias;
        if (size >= 2) {
            str = this.mParty.partyBlockedUsers.get(1).alias;
        }
        if (size == 1) {
            context = this.mContext;
            i = R.string.join_party_blocked_single_user_description;
            objArr = new Object[]{str2};
        } else if (size != 2) {
            return this.mContext.getString(R.string.join_party_blocked_many_user_description, str2, str, Integer.valueOf(size), Integer.valueOf(size - 2));
        } else {
            context = this.mContext;
            i = R.string.join_party_blocked_two_user_description;
            objArr = new Object[]{str2, str};
        }
        return context.getString(i, objArr);
    }

    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    @Bindable
    public String getCurrentPartyId() {
        return this.mCurrentPartyId;
    }

    public String getDeeplinkTargetId() {
        return this.mDeeplinkTargetId;
    }

    @Bindable
    public String getGroupLaunchApplication() {
        GroupLaunch groupLaunch;
        Party party = this.mParty;
        if (party == null || (groupLaunch = party.partyGroupLaunch) == null) {
            return null;
        }
        return groupLaunch.destination.application.displayName;
    }

    @Bindable
    public String getGroupLaunchDestination() {
        GroupLaunch groupLaunch;
        Party party = this.mParty;
        if (party == null || (groupLaunch = party.partyGroupLaunch) == null) {
            return null;
        }
        return groupLaunch.destination.displayName;
    }

    @Bindable
    public String getGroupLaunchImageUrl() {
        GroupLaunch groupLaunch;
        Party party = this.mParty;
        if (party == null || (groupLaunch = party.partyGroupLaunch) == null) {
            return null;
        }
        return groupLaunch.destination.application.imageUri;
    }

    public String getPartyId() {
        return this.mPartyId;
    }

    @Bindable
    public String getPartyTitle() {
        Context context;
        int i;
        String str;
        Context context2;
        int i2;
        Object[] objArr;
        if (this.mParty != null) {
            List<User> displayablePartyUsers = getDisplayablePartyUsers();
            int size = displayablePartyUsers.size();
            String str2 = null;
            if (size >= 1) {
                str = displayablePartyUsers.get(0).alias;
            } else {
                str = null;
            }
            if (size >= 2) {
                str2 = displayablePartyUsers.get(1).alias;
            }
            if (size != 0) {
                if (size != 1) {
                    if (size == 2) {
                        context2 = this.mContext;
                        i2 = R.string.join_party_two_people_in_party_description;
                    } else if (size != 3) {
                        context2 = this.mContext;
                        i2 = R.string.join_party_many_people_in_party_description;
                        objArr = new Object[]{str, str2, Integer.valueOf(size - 2)};
                    } else {
                        context2 = this.mContext;
                        i2 = R.string.join_party_few_people_in_party_description;
                    }
                    objArr = new Object[]{str, str2};
                } else {
                    context2 = this.mContext;
                    i2 = R.string.join_party_one_person_in_party_description;
                    objArr = new Object[]{str};
                }
                return context2.getString(i2, objArr);
            }
            context = this.mContext;
            i = R.string.join_party_empty_party_description;
        } else {
            context = this.mContext;
            i = R.string.join_party_privacy_default;
        }
        return context.getString(i);
    }

    @Bindable
    public String getPartyType() {
        Party party = this.mParty;
        if (party != null) {
            return party.partyType;
        }
        return null;
    }

    @Bindable
    public String getPartyTypeDescription() {
        Context context;
        int i;
        Party party = this.mParty;
        if (party != null) {
            String str = party.partyType;
            int hashCode = str.hashCode();
            if (hashCode != 2432586) {
                if (hashCode != 536935208) {
                    if (hashCode == 1990776172 && str.equals("CLOSED")) {
                        context = this.mContext;
                        i = R.string.join_party_privacy_invite_only;
                        return context.getString(i);
                    }
                } else if (str.equals("JOINABLE_BY_FRIENDS")) {
                    context = this.mContext;
                    i = R.string.join_party_privacy_joinable_by_friends;
                    return context.getString(i);
                }
            } else if (str.equals("OPEN")) {
                context = this.mContext;
                i = R.string.join_party_privacy_public;
                return context.getString(i);
            }
        }
        context = this.mContext;
        i = R.string.join_party_privacy_default;
        return context.getString(i);
    }

    @Bindable
    public int getPartyTypeIconImage() {
        boolean equals;
        int i;
        Party party = this.mParty;
        if (party != null) {
            String str = party.partyType;
            int hashCode = str.hashCode();
            if (hashCode == 2432586) {
                equals = str.equals("OPEN");
                i = R.drawable.oc_icon_link_filled_24_d2d2d2;
            } else if (hashCode == 536935208) {
                equals = str.equals("JOINABLE_BY_FRIENDS");
                i = R.drawable.oc_icon_friends_filled_24_d2d2d2;
            } else if (hashCode == 1990776172) {
                equals = str.equals("CLOSED");
                i = R.drawable.oc_icon_lock_filled_24_d2d2d2;
            }
            if (!equals) {
                return R.drawable.oc_icon_info_filled_24_d2d2d2;
            }
            return i;
        }
        return R.drawable.oc_icon_info_filled_24_d2d2d2;
    }

    public String getSource() {
        return this.mSource;
    }

    @Bindable
    public String getViewerAlias() {
        User user = this.mViewer;
        if (user != null) {
            return user.alias;
        }
        return null;
    }

    @Bindable
    public String getViewerProfilePhotoUrl() {
        User user = this.mViewer;
        if (user != null) {
            return user.profilePhotoURL;
        }
        return null;
    }

    public JoinPartyViewModel(Context context) {
        this.mContext = context;
    }

    private void fetchPartyInviteInfoWithId(GraphQLService graphQLService, String str, Callback callback, Error error) {
        JoinPartyQueryHelper.queryPartyInviteInfoWithId(graphQLService, partyQueryCallback(callback, error), str);
    }

    private void fetchPartyInviteInfoWithNonce(GraphQLService graphQLService, String str, String str2, Callback callback, Error error) {
        JoinPartyQueryHelper.queryPartyInviteInfoWithNonce(graphQLService, partyQueryCallback(callback, error), str, str2);
    }

    private String getProfilePictureFromPartyUser(int i) {
        List<User> displayablePartyUsers = getDisplayablePartyUsers();
        if (displayablePartyUsers.size() > i) {
            return displayablePartyUsers.get(i).profilePhotoURL;
        }
        return null;
    }

    public void setCorrelationId(String str) {
        this.mCorrelationId = str;
    }

    public void setDeeplinkTargetId(String str) {
        this.mDeeplinkTargetId = str;
    }

    public void setLinkNonce(String str) {
        this.mLinkNonce = str;
    }

    public void setPartyId(String str) {
        this.mPartyId = str;
    }

    public void setRequestFactory(JoinPartyRequestFactory joinPartyRequestFactory) {
        this.mJoinPartyRequestFactory = joinPartyRequestFactory;
    }

    public void setSource(String str) {
        this.mSource = str;
    }
}
