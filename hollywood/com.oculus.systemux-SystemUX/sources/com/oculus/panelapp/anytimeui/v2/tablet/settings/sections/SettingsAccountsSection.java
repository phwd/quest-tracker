package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.oculus.authapi.AuthError;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.OVRAuthHelper;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAccountsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.CompanionServerHelper;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.userserver.api.sharing.SharingManager;
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class SettingsAccountsSection extends SettingsSection {
    private static final String ADD_LOCK_PATTERN_URI = String.format("&flow=add_user&return_uri=%s", SystemUXRoute.SETTINGS.path());
    private static final String APP_SHARING_RESET_URI = "https://support.oculus.com/263086061243965/#faq_839867313014442";
    private static final String APP_SHARING_UPSELL_KEY = "app_sharing_upsell_key";
    public static final String MULTIUSER_LEARN_MORE_URI = "https://support.oculus.com/1832656196884488/";
    @VisibleForTesting
    public static final String MULTI_USER_ACCOUNTS_NUX_PREF_KEY = "multi_user_accounts_nux_pref_key";
    private static final String MULTI_USER_LOCK_PATTERN_PREF_KEY = "multi_user_lock_pattern_prompt_seen_key";
    private static final String TAG = LoggingUtil.tag(SettingsAccountsSection.class);
    private final AndroidSettingsViewModel mAndroidSettingsViewModel;
    private final Context mContext;
    private OculusUser mCurrentUser;
    private boolean mIsLockPatternSet;
    private OculusUserManager mOculusUserManager;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private final Runnable mRefreshView;
    private final SettingsViewModel mSettingsViewModel;
    private final SharingManager mSharingManager;
    private final StatusViewModel mStatusViewModel;

    /* access modifiers changed from: private */
    public interface EnableLibrarySharingResult {
        void run(boolean z);
    }

    public SettingsAccountsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_accounts_section_title), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        this.mSettingsViewModel = this.mPanelApp.acquireSettingsViewModel();
        this.mStatusViewModel = this.mPanelApp.acquireStatusViewModel();
        this.mAndroidSettingsViewModel = this.mPanelApp.acquireAndroidSettingsViewModel();
        this.mSharingManager = new SharingManager(this.mContext);
        initializeIsLockPatternSet();
        maybeShowPendingAddUserConfirmationDialog();
        checkAndShowInternetDialog();
        ThreadExecutor.getInstance().execute(new Callable(context) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$xYaWmSCMlS1UdwzJWKVhGTzdJhg */
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SettingsAccountsSection.this.lambda$new$297$SettingsAccountsSection(this.f$1);
            }
        });
    }

    public /* synthetic */ Object lambda$new$297$SettingsAccountsSection(Context context) throws Exception {
        try {
            this.mOculusUserManager = new OculusUserManager(context);
            this.mCurrentUser = this.mOculusUserManager.getSelf();
            UiThreadExecutor.getInstance().execute(new Runnable(this.mOculusUserManager.getUsers(), this.mOculusUserManager.canAddMoreUsers()) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$_1IWUw2gozpO1FutQeLIkJHRdr4 */
                private final /* synthetic */ List f$1;
                private final /* synthetic */ boolean f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SettingsAccountsSection.this.lambda$null$296$SettingsAccountsSection(this.f$1, this.f$2);
                }
            });
            initializeMultiUserNux(this.mCurrentUser, this.mPreferencesManager);
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error initializing OculusUserManager", e);
            return null;
        }
    }

    public /* synthetic */ void lambda$null$296$SettingsAccountsSection(List list, boolean z) {
        initializeSection(this.mCurrentUser, list, z);
    }

    private void initializeIsLockPatternSet() {
        CompanionServerHelper.isPinSet(this.mContext, new CompanionServerHelper.OnIsPinSet() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$Rd_2S4N3G123AW46TkLQ60a0Z3g */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.util.CompanionServerHelper.OnIsPinSet
            public final void onResult(boolean z) {
                SettingsAccountsSection.this.lambda$initializeIsLockPatternSet$298$SettingsAccountsSection(z);
            }
        });
    }

    public /* synthetic */ void lambda$initializeIsLockPatternSet$298$SettingsAccountsSection(boolean z) {
        this.mIsLockPatternSet = z;
    }

    @VisibleForTesting
    public boolean checkAndShowInternetDialog() {
        if (this.mStatusViewModel.isNetworkConnectedWithInternet()) {
            return false;
        }
        this.mPanelApp.getDialogManager().showDialog(AccountsDialogHelper.getInternetRequiredDialog(this.mContext));
        return true;
    }

    @VisibleForTesting
    public void initializeMultiUserNux(OculusUser oculusUser, PreferencesManager preferencesManager) {
        Pair<Boolean, Boolean> pair = preferencesManager.getBoolean(MULTI_USER_ACCOUNTS_NUX_PREF_KEY);
        if (isDeviceOwner(oculusUser) && ((Boolean) pair.first).booleanValue() && !((Boolean) pair.second).booleanValue()) {
            this.mPanelApp.getDialogManager().showDialog(AccountsDialogHelper.getEducationNuxDialog(this.mContext));
            preferencesManager.set(MULTI_USER_ACCOUNTS_NUX_PREF_KEY, true);
        }
    }

    @VisibleForTesting
    public void initializeSection(OculusUser oculusUser, List<OculusUser> list, boolean z) {
        List<OculusUser> list2 = (List) list.stream().filter(new Predicate() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$bNpOmlc0qV5IqCLMuLl98Br5RI */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SettingsAccountsSection.lambda$initializeSection$299(OculusUser.this, (OculusUser) obj);
            }
        }).collect(Collectors.toList());
        ArrayList arrayList = new ArrayList();
        boolean isDeviceOwner = isDeviceOwner(oculusUser);
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withBody(R.string.settings_multi_user_description).withBodyUri(MULTIUSER_LEARN_MORE_URI).visibilityFetcher(new Supplier(isDeviceOwner) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$enRyEDtfEw43kCLTdmGmy8V66xc */
            private final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsAccountsSection.lambda$initializeSection$300(this.f$0);
            }
        }));
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withHeader(R.string.settings_current_account_title));
        arrayList.add(getUserAccountItem(oculusUser, oculusUser));
        arrayList.add(getOtherUsersHeader(oculusUser, list2, z));
        for (OculusUser oculusUser2 : list2) {
            arrayList.add(getUserAccountItem(oculusUser, oculusUser2));
        }
        SettingsItem.Builder withVisibilityCondition = new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("library_sharing").withTitle(R.string.settings_multi_user_app_sharing_title).withSubtitle(R.string.settings_multi_user_app_sharing_subtitle).withIcon(R.drawable.oc_icon_apps_filled_24_d2d2d2).withVisibilityCondition(new Supplier(isDeviceOwner) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$qgIVgz8261FVfq4bHODeSa8w0Q */
            private final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsAccountsSection.lambda$initializeSection$301(this.f$0);
            }
        });
        SettingsToggleActionType.Builder builder = new SettingsToggleActionType.Builder();
        SharingManager sharingManager = this.mSharingManager;
        sharingManager.getClass();
        arrayList.add(withVisibilityCondition.withSettingsActionType(builder.withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$7FxZ4BSdZVXEPoWSzX5mCZkR_dE */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SharingManager.this.getLibrarySharingEnabled();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$9oiyjZa6Rq_qVrGVye4dEulWC4 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsAccountsSection.this.lambda$initializeSection$303$SettingsAccountsSection(z, settingsToggleActionType);
            }
        })));
        clearSettingsItems();
        addSettingsItems(arrayList);
        this.mRefreshView.run();
    }

    static /* synthetic */ boolean lambda$initializeSection$299(OculusUser oculusUser, OculusUser oculusUser2) {
        return oculusUser2.getUserId() != oculusUser.getUserId();
    }

    public /* synthetic */ void lambda$initializeSection$303$SettingsAccountsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        enableLibrarySharing(z, new EnableLibrarySharingResult() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$lLDSPcZ5YhBF2yLRIRQPmydCqbI */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAccountsSection.EnableLibrarySharingResult
            public final void run(boolean z) {
                SettingsAccountsSection.lambda$null$302(SettingsToggleActionType.this, z);
            }
        });
    }

    static /* synthetic */ void lambda$null$302(SettingsToggleActionType settingsToggleActionType, boolean z) {
        if (!z) {
            settingsToggleActionType.refreshToggle();
        }
    }

    private SettingsDescriptiveText.Builder getOtherUsersHeader(OculusUser oculusUser, List<OculusUser> list, boolean z) {
        SettingsDescriptiveText.Builder visibilityFetcher = new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withSettingName("add_user").withHeader(R.string.settings_multi_user_other_acccounts_header).visibilityFetcher(new Supplier(list, z) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$O3IMciynUpf9r2SWuExzSfPExT8 */
            private final /* synthetic */ List f$0;
            private final /* synthetic */ boolean f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsAccountsSection.lambda$getOtherUsersHeader$304(this.f$0, this.f$1);
            }
        });
        if (isDeviceOwner(oculusUser)) {
            visibilityFetcher.withButton(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_multi_user_add_account_button).withEnabled(new Supplier(z) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$c84JWoLp7AMgvLVsdkTl6VpWpnU */
                private final /* synthetic */ boolean f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Supplier
                public final Object get() {
                    return SettingsAccountsSection.lambda$getOtherUsersHeader$305(this.f$0);
                }
            }).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$YO6lAuKn2dTpVf5OKiMt6Nog_4M */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
                public final void onClick() {
                    SettingsAccountsSection.this.lambda$getOtherUsersHeader$306$SettingsAccountsSection();
                }
            }));
        }
        return visibilityFetcher;
    }

    static /* synthetic */ Boolean lambda$getOtherUsersHeader$304(List list, boolean z) {
        return Boolean.valueOf(!list.isEmpty() || z);
    }

    public /* synthetic */ void lambda$getOtherUsersHeader$306$SettingsAccountsSection() {
        addAccount(this.mSettingsViewModel, shouldShowAppSharingUpsell(this.mPreferencesManager, this.mSharingManager));
    }

    private boolean shouldShowAppSharingUpsell(PreferencesManager preferencesManager, SharingManager sharingManager) {
        Pair<Boolean, Boolean> pair = preferencesManager.getBoolean(APP_SHARING_UPSELL_KEY);
        return ((Boolean) pair.first).booleanValue() && !((Boolean) pair.second).booleanValue() && !sharingManager.getLibrarySharingEnabled();
    }

    @VisibleForTesting
    public void addAccount(SettingsViewModel settingsViewModel, boolean z) {
        if (settingsViewModel.getIsTrackingIn3DOFMode() || settingsViewModel.getGuardianDisabled() || settingsViewModel.getGuardianPaused()) {
            this.mPanelApp.getDialogManager().showDialog(AccountsDialogHelper.getGuardianRequiredDialog(this.mContext));
        } else if (z) {
            DialogDefinitionCustom librarySharingUpsellDialog = AccountsDialogHelper.getLibrarySharingUpsellDialog(this.mContext);
            librarySharingUpsellDialog.setDialogResultHandler(new DialogResultHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$k9AFEUWbH6UDCvilSrvCDaT_PEw */

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SettingsAccountsSection.this.lambda$addAccount$308$SettingsAccountsSection(dialogResult);
                }
            });
            this.mPanelApp.getDialogManager().showDialog(librarySharingUpsellDialog);
            this.mPreferencesManager.set(APP_SHARING_UPSELL_KEY, true);
        } else {
            addAccountEnsureDeviceOwnership();
        }
    }

    public /* synthetic */ boolean lambda$addAccount$308$SettingsAccountsSection(DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            enableLibrarySharing(true, new EnableLibrarySharingResult() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$scCS2BYeWb9YmYuNDEGRPiygJEA */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAccountsSection.EnableLibrarySharingResult
                public final void run(boolean z) {
                    SettingsAccountsSection.this.lambda$null$307$SettingsAccountsSection(z);
                }
            });
        } else {
            addAccountEnsureDeviceOwnership();
        }
        return true;
    }

    public /* synthetic */ void lambda$null$307$SettingsAccountsSection(boolean z) {
        addAccountEnsureDeviceOwnership();
        this.mRefreshView.run();
    }

    private void addAccountEnsureDeviceOwnership() {
        this.mPanelApp.getOVRAuthHelper().ensureDeviceOwnership(new OVRAuthHelper.OvrAuthDeviceOwnershipCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAccountsSection.AnonymousClass1 */

            @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthDeviceOwnershipCallback
            public void onError(AuthError authError) {
                SettingsAccountsSection.this.mPanelApp.getDialogManager().showDialog(AccountsDialogHelper.getDeviceOwnershipErrorDialog(SettingsAccountsSection.this.mContext, authError, SettingsAccountsSection.this.mPanelApp.isGKEnabled(Gatekeeper.TRUSTED_USER)));
            }

            @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthDeviceOwnershipCallback
            public void onSuccess() {
                SettingsAccountsSection.this.addAccountLockPatternUpsell();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccountLockPatternUpsell() {
        Pair<Boolean, Boolean> pair = this.mPreferencesManager.getBoolean(MULTI_USER_LOCK_PATTERN_PREF_KEY);
        boolean z = ((Boolean) pair.first).booleanValue() && !((Boolean) pair.second).booleanValue();
        if (this.mIsLockPatternSet || !z) {
            addAccountGetConfirmation();
            return;
        }
        this.mPanelApp.actionNavigate(SystemUXRoute.LOCKPATTERN, ADD_LOCK_PATTERN_URI);
        this.mPreferencesManager.set(MULTI_USER_LOCK_PATTERN_PREF_KEY, true);
        this.mAndroidSettingsViewModel.setPendingCreateNewUserConfirmationDialog(true);
    }

    private void addAccountGetConfirmation() {
        this.mAndroidSettingsViewModel.setPendingCreateNewUserConfirmationDialog(false);
        DialogDefinitionCustom addUserConfirmationDialog = AccountsDialogHelper.getAddUserConfirmationDialog(this.mContext);
        addUserConfirmationDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$ytJAC5A1jEIZgMUZoECsz9gLZIo */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsAccountsSection.this.lambda$addAccountGetConfirmation$310$SettingsAccountsSection(dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(addUserConfirmationDialog);
    }

    public /* synthetic */ boolean lambda$addAccountGetConfirmation$310$SettingsAccountsSection(DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$e7UAPqZZdLKUy7mCWRjNKnc3I */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SettingsAccountsSection.this.lambda$null$309$SettingsAccountsSection();
            }
        });
        return true;
    }

    public /* synthetic */ Object lambda$null$309$SettingsAccountsSection() throws Exception {
        try {
            this.mOculusUserManager.createUserAndSwitch();
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error creating user", e);
            return null;
        }
    }

    private void maybeShowPendingAddUserConfirmationDialog() {
        if (this.mAndroidSettingsViewModel.isPendingCreateNewUserConfirmationDialog()) {
            addAccountGetConfirmation();
        }
    }

    private SettingsItem.Builder getUserAccountItem(OculusUser oculusUser, OculusUser oculusUser2) {
        boolean z = true;
        SettingsItem.Builder withPlaceholderImage = new SettingsItem.Builder(this.mContext, this.mPanelApp).withTitle(oculusUser2.getUserName()).withImageUri(Uri.parse(oculusUser2.getPictureUri())).withIsImageCircular(true).withPlaceholderImage(R.drawable.oc_icon_profile_circle_filled_48_d2d2d2);
        if (isDeviceOwner(oculusUser2)) {
            withPlaceholderImage.withSubtitle(R.string.settings_current_account_admin_subtitle);
        } else {
            withPlaceholderImage.withSubtitle(this.mContext.getResources().getString(R.string.settings_current_account_secondary_subtitle, formatDateCreated(oculusUser2.getCreationTime())));
            if (oculusUser.getUserId() != oculusUser2.getUserId()) {
                z = false;
            }
            if (isDeviceOwner(oculusUser) || z) {
                withPlaceholderImage.withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_remove_account_button).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler(oculusUser2, z) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$ESONpfAjzlsUtyR_xmdyD09lL4 */
                    private final /* synthetic */ OculusUser f$1;
                    private final /* synthetic */ boolean f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
                    public final void onClick() {
                        SettingsAccountsSection.this.lambda$getUserAccountItem$311$SettingsAccountsSection(this.f$1, this.f$2);
                    }
                }));
            }
        }
        return withPlaceholderImage;
    }

    public /* synthetic */ void lambda$getUserAccountItem$311$SettingsAccountsSection(OculusUser oculusUser, boolean z) {
        this.mPanelApp.getDialogManager().showDialog(getRemoveUserDialog(oculusUser, z));
    }

    private boolean isDeviceOwner(OculusUser oculusUser) {
        return oculusUser.getUserId() == 0;
    }

    private String formatDateCreated(long j) {
        if (j <= 0) {
            return "";
        }
        return DateFormat.getDateInstance(2).format(Long.valueOf(j));
    }

    @VisibleForTesting
    public DialogDefinitionCustom getRemoveUserDialog(OculusUser oculusUser, boolean z) {
        DialogDefinitionCustom removeUserDialog = AccountsDialogHelper.getRemoveUserDialog(this.mContext, oculusUser, z);
        removeUserDialog.setDialogResultHandler(new DialogResultHandler(z, oculusUser) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$JuXNBVRG9qbXRWHc4wOBi4l96_o */
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ OculusUser f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsAccountsSection.this.lambda$getRemoveUserDialog$314$SettingsAccountsSection(this.f$1, this.f$2, dialogResult);
            }
        });
        return removeUserDialog;
    }

    public /* synthetic */ boolean lambda$getRemoveUserDialog$314$SettingsAccountsSection(boolean z, OculusUser oculusUser, DialogResult dialogResult) {
        String dialogAction = dialogResult.getDialogAction();
        if (MULTIUSER_LEARN_MORE_URI.equals(dialogResult.getDialogAction())) {
            this.mPanelApp.actionNavigate(SystemUXRoute.DEFAULT_BROWSER, String.format("ovrweb://webtask?uri=%s", Uri.encode(MULTIUSER_LEARN_MORE_URI)));
            this.mPanelApp.getDialogManager().hideDialog();
        } else if (CommonSystemDialogActions.CONTINUE.equals(dialogAction)) {
            ThreadExecutor.getInstance().execute(new Callable(z, oculusUser) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$uGa7KxzbskbyDP2rdQQDhMeZNCw */
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ OculusUser f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return SettingsAccountsSection.this.lambda$null$313$SettingsAccountsSection(this.f$1, this.f$2);
                }
            });
        }
        return true;
    }

    public /* synthetic */ Object lambda$null$313$SettingsAccountsSection(boolean z, OculusUser oculusUser) throws Exception {
        if (z) {
            try {
                this.mOculusUserManager.removeSelf();
                return null;
            } catch (Exception e) {
                Log.e(TAG, "Error removing user", e);
                this.mPanelApp.getDialogManager().showDialog(AccountsDialogHelper.getErrorDialog(this.mContext));
                return null;
            }
        } else {
            this.mOculusUserManager.removeUser(oculusUser.getUserId());
            UiThreadExecutor.getInstance().execute(new Runnable(this.mOculusUserManager.getUsers(), this.mOculusUserManager.canAddMoreUsers()) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$ynQ2rJz0Sa1D7ppDXhKzBDnRMeA */
                private final /* synthetic */ List f$1;
                private final /* synthetic */ boolean f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SettingsAccountsSection.this.lambda$null$312$SettingsAccountsSection(this.f$1, this.f$2);
                }
            });
            return null;
        }
    }

    public /* synthetic */ void lambda$null$312$SettingsAccountsSection(List list, boolean z) {
        initializeSection(this.mCurrentUser, list, z);
    }

    private void enableLibrarySharing(boolean z, EnableLibrarySharingResult enableLibrarySharingResult) {
        if (!checkAndShowInternetDialog()) {
            ThreadExecutor.getInstance().execute(new Callable(z, enableLibrarySharingResult) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$gEl8gk5BLAAMDKWi2tKns4brixQ */
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ SettingsAccountsSection.EnableLibrarySharingResult f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return SettingsAccountsSection.this.lambda$enableLibrarySharing$318$SettingsAccountsSection(this.f$1, this.f$2);
                }
            });
        }
    }

    public /* synthetic */ Object lambda$enableLibrarySharing$318$SettingsAccountsSection(boolean z, EnableLibrarySharingResult enableLibrarySharingResult) throws Exception {
        try {
            this.mSharingManager.setLibrarySharingEnabled(z);
            UiThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$uNGieEE1qlsXVHktR_3QichTQw */

                public final void run() {
                    SettingsAccountsSection.lambda$null$315(SettingsAccountsSection.EnableLibrarySharingResult.this);
                }
            });
            return null;
        } catch (SharingManager.SharingManagerException e) {
            Log.e(TAG, "Error updating library sharing", e);
            DialogDefinitionCustom librarySharingErrorDialog = AccountsDialogHelper.getLibrarySharingErrorDialog(this.mContext, e.getCode());
            librarySharingErrorDialog.setDialogResultHandler(new DialogResultHandler(enableLibrarySharingResult) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$JJPK0xtdheKfSXylvIi4fD_AOjo */
                private final /* synthetic */ SettingsAccountsSection.EnableLibrarySharingResult f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SettingsAccountsSection.this.lambda$null$317$SettingsAccountsSection(this.f$1, dialogResult);
                }
            });
            this.mPanelApp.getDialogManager().showDialog(librarySharingErrorDialog);
            return null;
        }
    }

    public /* synthetic */ boolean lambda$null$317$SettingsAccountsSection(EnableLibrarySharingResult enableLibrarySharingResult, DialogResult dialogResult) {
        Log.d(TAG, dialogResult.getDialogAction());
        if (AccountsDialogHelper.LIBRARY_SHARING_ERROR_DIALOG_SUPPORT_BUTTON.equals(dialogResult.getDialogAction())) {
            this.mPanelApp.actionNavigate(SystemUXRoute.DEFAULT_BROWSER, String.format("ovrweb://webtask?uri=%s", APP_SHARING_RESET_URI));
        } else {
            UiThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAccountsSection$ncbD_L0BvPpqMu8pB6rEsVL5Ccs */

                public final void run() {
                    SettingsAccountsSection.lambda$null$316(SettingsAccountsSection.EnableLibrarySharingResult.this);
                }
            });
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        this.mPanelApp.releaseSettingsViewModel();
        this.mPanelApp.releaseStatusViewModel();
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }
}
