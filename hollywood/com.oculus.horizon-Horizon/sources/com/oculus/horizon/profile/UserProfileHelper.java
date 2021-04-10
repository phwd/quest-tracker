package com.oculus.horizon.profile;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0Jy;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C08780ya;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.errorreporting.ErrorReporter;
import com.oculus.horizon.api.common.ClientPreference;
import com.oculus.horizon.api.common.user.User;
import com.oculus.horizon.api.fbconnect.FBConnectAccount;
import com.oculus.horizon.api.profile.LinkedAccounts;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

@SuppressLint({"SharedPreferencesUse"})
@Dependencies({"_UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_horizon_profile_UserProfileSharedPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_horizon_profile_UserProfileUpdateListener_ULGT__ULSEP_BINDING_ID"})
@ApplicationScoped
public class UserProfileHelper {
    public static final C08780ya GSON = new C08780ya();
    public static final String KEY_AUTO_UPDATES_ENABLED = "key_auto_updates_enabled";
    public static final String KEY_CHECK_APP_VERSION_ROADBLOCK = "key_check_app_version_roadblock";
    public static final String KEY_EMAIL = "key_email";
    public static final String KEY_FACEBOOK_EMAIL = "key_facebook_email";
    public static final String KEY_FACEBOOK_USER_ID = "key_facebook_user_id";
    public static final String KEY_HAS_DOCKED = "has_docked";
    public static final String KEY_HAS_OPTED_OUT_FRIEND_ONLINE_BROADCAST_NOTIFS = "key_has_opted_out_friend_online_broadcast_notifs";
    public static final String KEY_HAS_OPTED_OUT_FRIEND_ONLINE_NOTIFS = "key_has_opted_out_friend_online_notifs";
    public static final String KEY_HOME_TUTORIAL_COMPLETED = "key_home_tutorial_completed";
    public static final String KEY_IS_PIN_SET = "key_is_pin_set";
    public static final String KEY_LINKED_ACCOUNTS_ENABLED = "key_linked_accounts_enabled";
    public static final String KEY_SEEN_ACTIVITY_PRIVACY_UPDATE_ROADBLOCK = "key_seen_activity_privacy_update_roadblock";
    public static final String KEY_SEEN_ADD_PROFILE_ICON = "key_seen_profile_icon";
    public static final String KEY_SEEN_PARTY_CALLS_NUX = "key_seen_party_calls_nux";
    public static final String KEY_SEEN_PAYMENT_ROADBLOCK = "key_seen_payment_roablock";
    public static final String KEY_SEEN_SOCIAL_NUX = "key_seen_social_nux";
    public static final String KEY_SEEN_SPECIAL_OFFER_ROADBLOCK = "key_seen_special_offer_roadblock";
    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_USER_ID = "key_user_id";
    public static final String KEY_USER_OBJECT = "key_user_object";
    public static final String KEY_USE_TEST_STORES = "key_use_test_stores";
    public static final String PREFERENCE_DEFAULT_BROWSER = "defaultBrowser";
    public static final String PREFERENCE_HAS_FINISHED_FULL_VR_NUX = "hasFinishedFullVrNux";
    public static final String PREFERENCE_HAS_FINISHED_IPD_ADJUST = "hasFinishedIPDAdjust";
    public static final String PREFERENCE_HAS_FINISHED_MONTEREY_NUX = "hasFinishedMontereyNux";
    public static final String PREFERENCE_HAS_FINISHED_NUX = "hasFinishedNux";
    public static final String PREFERENCE_HAS_OPTED_OUT_OF_HSW = "hasOptedOutOfHSW";
    public static final String PREFERENCE_HAS_OPTED_OUT_OF_MALIBU_RECENTER = "hasOptedOutOfMalibuRecenter";
    public static final String PREFERENCE_HAS_SEEN_CONFIRM_QUIT = "hasSeenConfirmQuit";
    public static final String PREFERENCE_HAS_SEEN_CONFIRM_QUIT_NOTIF = "hasSeenConfirmQuitNotif";
    public static final String PREFERENCE_HAS_SEEN_FOCUS = "hasSeenFocus";
    public static final String PREFERENCE_HAS_SEEN_HAND_TRACKING_NUX = "hasSeenHandTrackingNux";
    public static final String PREFERENCE_HAS_SEEN_HSW_VIDEO = "hasSeenHSWVideo";
    public static final String PREFERENCE_HAS_SEEN_INTERNET_BROWSER_PROMPT = "hasSeenInternetBrowserPrompt";
    public static final String PREFERENCE_HAS_SEEN_LONG_HSW = "hasSeenLongHSW";
    public static final String PREFERENCE_HAS_SEEN_NEW_LIBRARY = "hasSeenNewLibrary";
    public static final String PREFERENCE_HAS_SEEN_NUX = "hasSeenNux";
    public static final String PREFERENCE_HAS_SEEN_SAVED_PROMPT = "hasSeenSavedPrompt";
    public static final String PREFERENCE_HAS_SEEN_TUTORIAL_PROMPT = "hasSeenTutorial";
    public static final String PREFERENCE_HIGH_PRIORITY_APPS_DOWNLOAD_PACKAGES = "highPriorityAppsDownloadPackages";
    public static final String PREFERENCE_HIGH_PRIORITY_APPS_DOWNLOAD_STATUS = "highPriorityAppsDownloadStatus";
    public static final String PREFERENCE_HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME = "highPriorityAppsDownloadWaitTimeMilliSec";
    public static final String PREFERENCE_NUX_RESULT = "nuxResult";
    public static final String PREFERENCE_NUX_SEEN_COUNT = "nuxSeenCount";
    public static final String PREFERENCE_NUX_STATE = "nuxState";
    public static final String TAG = "UserProfileHelper";
    public static final String USER_PROFILE_SHARED_PREFERENCES = "user_profile_shared_preferences";
    public static volatile UserProfileHelper _UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    @UserProfileSharedPrefs
    public final SharedPreferences mPrefs;

    public final class Editor {
        public Optional<Boolean> mCheckAppVersionRoadblock;
        public Optional<Boolean> mHasDocked;
        public Optional<Boolean> mHasFinishedFullVrNux;
        public Optional<Boolean> mHasOptedOutHSW;
        public Optional<Boolean> mHasSeenActivityPrivacyUpdateRoadblock;
        public Optional<Boolean> mHasSeenAddProfileIcon;
        public Optional<Boolean> mHasSeenHSWVideo;
        public Optional<Boolean> mHasSeenHandTrackingNux;
        public Optional<Boolean> mHasSeenPartyCallsNux;
        public Optional<Boolean> mHasSeenPaymentRoadblock;
        public Optional<Boolean> mHasSeenSocialNux;
        public Optional<LinkedAccounts> mLinkedAccounts;
        public final SharedPreferences mPrefs;
        public final List<String> mSpecialOfferIds = new ArrayList();
        public Optional<Boolean> mUseAutoUpdates;
        public Optional<Boolean> mUseTestStores;
        public Optional<User> mUser;

        public Editor(SharedPreferences sharedPreferences) {
            this.mPrefs = sharedPreferences;
            Absent<Object> absent = Absent.INSTANCE;
            this.mUser = absent;
            this.mHasSeenAddProfileIcon = absent;
            this.mHasSeenPaymentRoadblock = absent;
            this.mHasSeenSocialNux = absent;
            this.mHasSeenActivityPrivacyUpdateRoadblock = absent;
            this.mUseTestStores = absent;
            this.mCheckAppVersionRoadblock = absent;
            this.mUseAutoUpdates = absent;
            this.mHasDocked = absent;
            this.mHasSeenHSWVideo = absent;
            this.mHasSeenPartyCallsNux = absent;
            this.mHasOptedOutHSW = absent;
            this.mHasFinishedFullVrNux = absent;
            this.mHasSeenHandTrackingNux = absent;
            this.mLinkedAccounts = absent;
        }

        public static SharedPreferences.Editor A00(Editor editor) {
            FBConnectAccount fBConnectAccount;
            SharedPreferences.Editor edit = editor.mPrefs.edit();
            Optional<User> optional = editor.mUser;
            if (optional.isPresent()) {
                edit.putString("key_user_object", UserProfileHelper.GSON.A06(optional.get()));
            }
            Optional<LinkedAccounts> optional2 = editor.mLinkedAccounts;
            if (optional2.isPresent() && (fBConnectAccount = optional2.get().facebook_account) != null) {
                edit.putString(UserProfileHelper.KEY_FACEBOOK_USER_ID, fBConnectAccount.facebook_id);
                edit.putString(UserProfileHelper.KEY_FACEBOOK_EMAIL, fBConnectAccount.facebook_email);
            }
            Optional<Boolean> optional3 = editor.mHasSeenPaymentRoadblock;
            if (optional3.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_SEEN_PAYMENT_ROADBLOCK, optional3.get().booleanValue());
            }
            List<String> list = editor.mSpecialOfferIds;
            if (list != null && !list.isEmpty()) {
                for (String str : editor.mSpecialOfferIds) {
                    if (!Strings.isNullOrEmpty(str)) {
                        edit.putBoolean(UserProfileHelper.KEY_SEEN_SPECIAL_OFFER_ROADBLOCK.concat(str), true);
                    }
                }
            }
            Optional<Boolean> optional4 = editor.mHasSeenAddProfileIcon;
            if (optional4.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_SEEN_ADD_PROFILE_ICON, optional4.get().booleanValue());
            }
            Optional<Boolean> optional5 = editor.mHasSeenSocialNux;
            if (optional5.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_SEEN_SOCIAL_NUX, optional5.get().booleanValue());
            }
            Optional<Boolean> optional6 = editor.mHasSeenActivityPrivacyUpdateRoadblock;
            if (optional6.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_SEEN_ACTIVITY_PRIVACY_UPDATE_ROADBLOCK, optional6.get().booleanValue());
            }
            Optional<Boolean> optional7 = editor.mUseTestStores;
            if (optional7.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_USE_TEST_STORES, optional7.get().booleanValue());
            }
            Optional<Boolean> optional8 = editor.mCheckAppVersionRoadblock;
            if (optional8.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_CHECK_APP_VERSION_ROADBLOCK, optional8.get().booleanValue());
            }
            Optional<Boolean> optional9 = editor.mUseAutoUpdates;
            if (optional9.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_AUTO_UPDATES_ENABLED, optional9.get().booleanValue());
            }
            Optional<Boolean> optional10 = editor.mHasDocked;
            if (optional10.isPresent()) {
                edit.putBoolean("has_docked", optional10.get().booleanValue());
            }
            Optional<Boolean> optional11 = editor.mHasSeenHSWVideo;
            if (optional11.isPresent()) {
                edit.putBoolean(UserProfileHelper.PREFERENCE_HAS_SEEN_HSW_VIDEO, optional11.get().booleanValue());
            }
            Optional<Boolean> optional12 = editor.mHasSeenPartyCallsNux;
            if (optional12.isPresent()) {
                edit.putBoolean(UserProfileHelper.KEY_SEEN_PARTY_CALLS_NUX, optional12.get().booleanValue());
            }
            Optional<Boolean> optional13 = editor.mHasOptedOutHSW;
            if (optional13.isPresent()) {
                edit.putBoolean(UserProfileHelper.PREFERENCE_HAS_OPTED_OUT_OF_HSW, optional13.get().booleanValue());
            }
            Optional<Boolean> optional14 = editor.mHasFinishedFullVrNux;
            if (optional14.isPresent()) {
                edit.putBoolean(UserProfileHelper.PREFERENCE_HAS_FINISHED_FULL_VR_NUX, optional14.get().booleanValue());
            }
            Optional<Boolean> optional15 = editor.mHasSeenHandTrackingNux;
            if (optional15.isPresent()) {
                edit.putBoolean(UserProfileHelper.PREFERENCE_HAS_SEEN_HAND_TRACKING_NUX, optional15.get().booleanValue());
            }
            return edit;
        }

        public static void A01(Editor editor) {
            if (editor.mUser.isPresent()) {
                for (UserProfileUpdateListener userProfileUpdateListener : (Set) AnonymousClass0J2.A03(0, 318, UserProfileHelper.this._UL_mInjectionContext)) {
                    userProfileUpdateListener.A79();
                }
            }
        }

        public static void A02(Editor editor, String str, String str2) {
            Optional<User> optional = editor.mUser;
            if (!optional.isPresent()) {
                optional = Optional.of(UserProfileHelper.A02(editor.mPrefs));
                editor.mUser = optional;
            }
            User user = optional.get();
            List<ClientPreference> list = user.client_preference_data;
            if (list == null) {
                list = new ArrayList();
            }
            boolean z = false;
            ArrayList arrayList = new ArrayList(list);
            for (ClientPreference clientPreference : list) {
                if (str.equals(clientPreference.type)) {
                    arrayList.remove(clientPreference);
                    arrayList.add(new ClientPreference(str, str2));
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(new ClientPreference(str, str2));
            }
            user.client_preference_data = arrayList;
        }
    }

    public static int A00(UserProfileHelper userProfileHelper, String str) {
        List<ClientPreference> list = A02(userProfileHelper.mPrefs).client_preference_data;
        if (list != null) {
            for (ClientPreference clientPreference : list) {
                if (str.equals(clientPreference.type)) {
                    return Integer.parseInt(clientPreference.value);
                }
            }
        }
        return 0;
    }

    @Deprecated
    public static SharedPreferences A01(@ForAppContext Context context) {
        try {
            Context createPackageContext = context.createPackageContext("com.oculus.horizon", 1);
            if (createPackageContext != null) {
                return createPackageContext.getSharedPreferences(USER_PROFILE_SHARED_PREFERENCES, 4);
            }
            throw new NullPointerException(Strings.lenientFormat("Could not create context from: %s", context));
        } catch (Throwable th) {
            ErrorReporter A01 = ErrorReporter.A01(null);
            A01.mErrorReporter.A9A(new AnonymousClass0Jy(ErrorReporter.A00(TAG, "Failed to create Horizon context", th, false)));
            throw new RuntimeException(th);
        }
    }

    public static User A02(SharedPreferences sharedPreferences) {
        if (!sharedPreferences.contains("key_user_object")) {
            User user = new User();
            user.email = sharedPreferences.getString(KEY_EMAIL, null);
            user.alias = sharedPreferences.getString(KEY_USERNAME, null);
            user.id = sharedPreferences.getString("key_user_id", null);
            user.is_pin_set = Boolean.valueOf(sharedPreferences.getBoolean(KEY_IS_PIN_SET, false));
            sharedPreferences.edit().putString("key_user_object", GSON.A06(user)).apply();
        }
        return (User) GSON.A05(sharedPreferences.getString("key_user_object", null), User.class);
    }

    public static String A03(UserProfileHelper userProfileHelper, String str) {
        List<ClientPreference> list = A02(userProfileHelper.mPrefs).client_preference_data;
        if (list != null) {
            for (ClientPreference clientPreference : list) {
                if (str.equals(clientPreference.type)) {
                    return clientPreference.value;
                }
            }
        }
        return "";
    }

    @Inject
    public UserProfileHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mPrefs = (SharedPreferences) AnonymousClass117.A00(55, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
    }

    public static boolean A04(SharedPreferences sharedPreferences, String str) {
        List<ClientPreference> list = A02(sharedPreferences).client_preference_data;
        if (list != null) {
            for (ClientPreference clientPreference : list) {
                if (str.equals(clientPreference.type) && "true".equals(clientPreference.value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
