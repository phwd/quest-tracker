package com.oculus.headlesshorizon.social;

import X.AbstractC06640p5;
import X.AbstractC07380s1;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.common.init.INeedInit;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.api.common.ClientPreference;
import com.oculus.horizon.api.common.user.User;
import com.oculus.horizon.api.profile.LinkedAccounts;
import com.oculus.horizon.api.profile.MyUserProfileRequest;
import com.oculus.horizon.api.profile.MyUserProfileResponse;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Dependencies({"_UL__ULSEP_com_oculus_headlesshorizon_social_SocialJobScheduler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class MeManager implements INeedInit, LoginHandler {
    public static final String EVENT_USER_DATA_FETCH = "oculus_mobile_user_fetch";
    public static final String KEY_RESULT = "result";
    public static final String KEY_SUCCESS = "success";
    public static final String TAG = "MeManager";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final SocialJobScheduler mSocialJobScheduler;

    public final AnonymousClass0DC<User> A00() {
        final AnonymousClass0DD r3 = new AnonymousClass0DD();
        ((ApiRequestManager) AnonymousClass0J2.A03(2, 407, this._UL_mInjectionContext)).post(new MyUserProfileRequest(null), new ApiCallback<MyUserProfileResponse>() {
            /* class com.oculus.headlesshorizon.social.MeManager.AnonymousClass2 */

            @Override // com.oculus.http.core.base.ApiCallback
            public final void onError(ApiError apiError) {
                AnonymousClass0NO.A0E(MeManager.TAG, "Failed to refresh current user information. %s", apiError.getMessage());
                Event A22 = ((EventManager) AnonymousClass0J2.A03(3, 242, MeManager.this._UL_mInjectionContext)).A22(MeManager.EVENT_USER_DATA_FETCH);
                A22.A16("success", false);
                A22.A15("result", apiError.getMessage());
                A22.A5L();
                r3.A01(apiError);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.http.core.base.ApiCallback
            public final void onResponse(MyUserProfileResponse myUserProfileResponse) {
                ImmutableList A0C;
                MyUserProfileResponse myUserProfileResponse2 = myUserProfileResponse;
                User user = myUserProfileResponse2.viewer.user;
                MeManager meManager = MeManager.this;
                LinkedAccounts linkedAccounts = myUserProfileResponse2.getLinkedAccounts();
                HashSet hashSet = new HashSet();
                List<ClientPreference> list = user.client_preference_data;
                if (list != null) {
                    for (ClientPreference clientPreference : list) {
                        hashSet.add(clientPreference.type);
                    }
                }
                ArrayList arrayList = new ArrayList();
                List<ClientPreference> list2 = UserProfileHelper.A02(((UserProfileHelper) AnonymousClass0J2.A03(0, 68, meManager._UL_mInjectionContext)).mPrefs).client_preference_data;
                if (list2 == null) {
                    A0C = ImmutableList.of();
                } else {
                    A0C = ImmutableList.A0C(list2);
                }
                AbstractC07380s1 A0K = A0C.iterator();
                while (A0K.hasNext()) {
                    ClientPreference clientPreference2 = (ClientPreference) A0K.next();
                    if (!hashSet.contains(clientPreference2.type)) {
                        arrayList.add(clientPreference2);
                    }
                }
                if (!arrayList.isEmpty()) {
                    List list3 = user.client_preference_data;
                    if (list3 == null) {
                        list3 = new ArrayList();
                        user.client_preference_data = list3;
                    }
                    list3.addAll(arrayList);
                }
                if (linkedAccounts != null) {
                    UserProfileHelper userProfileHelper = (UserProfileHelper) AnonymousClass0J2.A03(0, 68, meManager._UL_mInjectionContext);
                    UserProfileHelper.Editor editor = new UserProfileHelper.Editor(userProfileHelper.mPrefs);
                    editor.mUser = Optional.of(user);
                    editor.mLinkedAccounts = Optional.of(linkedAccounts);
                    UserProfileHelper.Editor.A00(editor).apply();
                    UserProfileHelper.Editor.A01(editor);
                }
                r3.A02(user);
            }
        });
        return r3.A00;
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        this.mSocialJobScheduler.A00();
    }

    @Inject
    public MeManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
        this.mSocialJobScheduler = (SocialJobScheduler) AnonymousClass117.A00(402, r3);
    }

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        return A00().A09(new AnonymousClass0D4<User, Void>() {
            /* class com.oculus.headlesshorizon.social.MeManager.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final Void then(AnonymousClass0DC<User> r4) throws Exception {
                if (!r4.A0K()) {
                    return null;
                }
                AnonymousClass0NO.A0H(MeManager.TAG, r4.A0F(), "Unable to refresh social user after login");
                return null;
            }
        });
    }
}
