package com.oculus.panelapp.socialreauth.views;

import X.AbstractC12271xB;
import X.AbstractC12361xL;
import X.AbstractC12761yH;
import X.AbstractC12851yS;
import X.AbstractC13031yl;
import X.AnonymousClass1y2;
import X.AnonymousClass1zJ;
import X.AnonymousClass219;
import X.AnonymousClass2a4;
import X.C13011yj;
import X.C13401zX;
import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.fbauth.ExecutorUtil;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.securedactions.SecuredActions;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.fetchers.FBProfileFetcher;
import com.oculus.common.socialtablet.navbar.ProfileType;
import com.oculus.common.socialtablet.navbar.SocialTabletType;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialreauth.SocialReauthTabletPanelApp;
import com.oculus.panelapp.socialreauth.databinding.SocialReauthTabletMainBinding;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.KeyboardHandler;

public class SocialReauthView extends BaseView {
    public static final String TAG = LoggingUtil.tag(SocialReauthView.class);
    public SocialReauthTabletMainBinding mBinding;
    public FBProfileFetcher mFBProfileFetcher;
    public SocialReauthTabletPanelApp mPanelApp;
    @Nullable
    public AbstractC12271xB mTokenDisposable;
    public SocialReauthViewModel mViewModel = new SocialReauthViewModel(getResources());

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    private void initializePasswordInput(SocialReauthTabletPanelApp socialReauthTabletPanelApp) {
        PasswordInput passwordInput = this.mBinding.passwordInput;
        passwordInput.mEventHandler = socialReauthTabletPanelApp;
        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$FeXWkOhiKSKBanMOD6W0XRYNZuQ2 */

            public final void onFocusChange(View view, boolean z) {
                SocialReauthView.lambda$initializePasswordInput$3(SocialReauthTabletPanelApp.this, view, z);
            }
        });
        this.mBinding.passwordInput.addTextChangedListener(new TextWatcher() {
            /* class com.oculus.panelapp.socialreauth.views.SocialReauthView.AnonymousClass1 */

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mBinding.passwordInput.mCallback = new KeyboardHandler.KeyboardListener(socialReauthTabletPanelApp) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$B6Gv3o4sbop_5Vtd6olfYTBl5802 */
            public final /* synthetic */ SocialReauthTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
            public final void onKeyboardActionKey() {
                SocialReauthView.this.lambda$initializePasswordInput$4$SocialReauthView(this.f$1);
            }
        };
    }

    public static /* synthetic */ void lambda$initializePasswordInput$3(SocialReauthTabletPanelApp socialReauthTabletPanelApp, View view, boolean z) {
        if (z) {
            socialReauthTabletPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_COMPOSE_TEXT_INPUT, SurfaceType.REAUTH);
        }
    }

    private void submit(ClickEventButtonId clickEventButtonId) {
        this.mViewModel.setIsAuthenticationInProgress(true);
        validatePassword(this.mBinding.passwordInput.getText().toString(), clickEventButtonId);
    }

    /* access modifiers changed from: private */
    /* renamed from: validatePasswordOnFailure */
    public void lambda$validatePassword$7$SocialReauthView(Throwable th, ClickEventButtonId clickEventButtonId) {
        Log.e(TAG, "validatePassword error: ", th);
        this.mPanelApp.getLogger().logActionFailure(ActionId.REAUTH_VALIDATE_PASSWORD, clickEventButtonId, SurfaceType.REAUTH, "Error validating password, onFailure called");
        this.mViewModel.setIsAuthenticationInProgress(false);
    }

    /* access modifiers changed from: private */
    /* renamed from: validatePasswordOnSuccess */
    public void lambda$validatePassword$6$SocialReauthView(SecuredActions.Result result, ClickEventButtonId clickEventButtonId) {
        ActionId actionId;
        this.mViewModel.setIsAuthenticationInProgress(false);
        if (result.mIsSuccessful) {
            this.mPanelApp.getLogger().logActionSuccess(ActionId.REAUTH_VALIDATE_PASSWORD, clickEventButtonId, SurfaceType.REAUTH);
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL_V2, "");
            return;
        }
        this.mViewModel.setHasEncounteredError(result.mErrorSubcode);
        Integer num = result.mErrorSubcode;
        int intValue = num.intValue();
        if (intValue == 1348092) {
            actionId = ActionId.REAUTH_VALIDATE_PASSWORD_INCORRECT_PASSWORD;
        } else if (intValue != 1647001) {
            actionId = ActionId.REAUTH_VALIDATE_PASSWORD_ERROR_UNKNOWN;
        } else {
            actionId = ActionId.REAUTH_VALIDATE_PASSWORD_TOO_MANY_ATTEMPTS;
        }
        this.mPanelApp.getLogger().logActionSuccess(actionId, clickEventButtonId, SurfaceType.REAUTH, "error_code", String.valueOf(num));
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mViewModel.destroy();
        this.mViewModel = null;
        this.mFBProfileFetcher.destroy();
        this.mFBProfileFetcher = null;
        AbstractC12271xB r0 = this.mTokenDisposable;
        if (r0 != null) {
            r0.dispose();
            this.mTokenDisposable = null;
        }
        this.mBinding.socialTabletSideNav.destroy();
        SocialReauthTabletMainBinding socialReauthTabletMainBinding = this.mBinding;
        OCButton oCButton = socialReauthTabletMainBinding.continueBtn;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        OCButton oCButton2 = socialReauthTabletMainBinding.togglePasswordVisibilityBtn;
        oCButton2.mEventHandler = null;
        oCButton2.setOnClickListener(null);
        PasswordInput passwordInput = socialReauthTabletMainBinding.passwordInput;
        passwordInput.mEventHandler = null;
        passwordInput.setOnFocusChangeListener(null);
        this.mBinding.passwordInput.destroy();
    }

    public /* synthetic */ void lambda$initialize$0$SocialReauthView(SocialReauthTabletPanelApp socialReauthTabletPanelApp, View view) {
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.SOCIAL_REAUTH_CONTINUE;
        socialReauthTabletPanelApp.logButtonClick(clickEventButtonId, SurfaceType.REAUTH);
        submit(clickEventButtonId);
    }

    public /* synthetic */ void lambda$initialize$1$SocialReauthView(String str, String str2) {
        this.mViewModel.setUserName(str);
    }

    public /* synthetic */ void lambda$initialize$2$SocialReauthView(SocialReauthTabletPanelApp socialReauthTabletPanelApp, View view) {
        socialReauthTabletPanelApp.logButtonClick(ClickEventButtonId.SOCIAL_REAUTH_PASSWORD_VISIBILITY_TOGGLE, SurfaceType.REAUTH);
        this.mViewModel.togglePasswordShown();
    }

    public /* synthetic */ void lambda$initializePasswordInput$4$SocialReauthView(SocialReauthTabletPanelApp socialReauthTabletPanelApp) {
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.SOCIAL_REAUTH_KEYBOARD_GO;
        socialReauthTabletPanelApp.logButtonClick(clickEventButtonId, SurfaceType.REAUTH);
        submit(clickEventButtonId);
    }

    public /* synthetic */ AbstractC12761yH lambda$validatePassword$5$SocialReauthView(String str, String str2) throws Exception {
        return SecuredActions.createPasswordSecuredActionSingle(this.mPanelApp.mOkHttpClient, str2, str);
    }

    public SocialReauthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFBProfileFetcher = new FBProfileFetcher(context);
    }

    private void validatePassword(String str, ClickEventButtonId clickEventButtonId) {
        AnonymousClass1zJ r3 = new AnonymousClass1zJ(new C13401zX(FBAuthManager.queryAccessToken().A03(AnonymousClass1y2.A00()), new AbstractC13031yl(str) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$FWf73SwNTrbrdg42ap4BD4WVdxI2 */
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return SocialReauthView.this.lambda$validatePassword$5$SocialReauthView(this.f$1, (String) obj);
            }
        }), new AbstractC12851yS() {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$trhkYYujFPmCaOrAimFRpIHxCs2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                SocialReauthView.this.validatePasswordAsyncOnSuccess((SecuredActions.Result) obj);
            }
        });
        AbstractC12361xL r2 = ExecutorUtil.SCHEDULER;
        AnonymousClass219.A01(r2, "scheduler is null");
        this.mTokenDisposable = new C13011yj(r3, r2).A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(clickEventButtonId) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$aEEz0njDf1IJ6GqPl_MCFj29Ek2 */
            public final /* synthetic */ ClickEventButtonId f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                SocialReauthView.this.lambda$validatePassword$6$SocialReauthView(this.f$1, (SecuredActions.Result) obj);
            }
        }, new AbstractC12851yS(clickEventButtonId) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$S0Cgvd_VcZWWnirybI_zmqyiEA2 */
            public final /* synthetic */ ClickEventButtonId f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                SocialReauthView.this.lambda$validatePassword$7$SocialReauthView(this.f$1, (Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public void validatePasswordAsyncOnSuccess(SecuredActions.Result result) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("Calling validatePasswordAsyncOnSuccess on the main thread");
        } else if (result.mIsSuccessful) {
            MessengerVrAccountsContentProviderClient.setIsMessengerAuthenticated(this.mPanelApp.mContext, true);
        }
    }

    public void initialize(SocialReauthTabletPanelApp socialReauthTabletPanelApp, SocialReauthTabletMainBinding socialReauthTabletMainBinding, boolean z) {
        super.initialize(socialReauthTabletPanelApp, socialReauthTabletMainBinding);
        this.mPanelApp = socialReauthTabletPanelApp;
        this.mBinding = socialReauthTabletMainBinding;
        socialReauthTabletMainBinding.setViewModel(this.mViewModel);
        this.mViewModel.setIsShowingEducationInfo(z);
        if (z) {
            AnonymousClass2a4 r2 = new AnonymousClass2a4();
            r2.A0A(this.mBinding.socialReauthView);
            Resources resources = getResources();
            AnonymousClass2a4.A02(r2, R.id.header).A02.A0k = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width);
            AnonymousClass2a4.A02(r2, R.id.body_scroll_view).A02.A0k = resources.getDimensionPixelSize(R.dimen.abc_floating_window_z);
            AnonymousClass2a4.A02(r2, R.id.password_input_label).A02.A0k = resources.getDimensionPixelSize(R.dimen.abc_floating_window_z);
            AnonymousClass2a4.A02(r2, R.id.footer).A02.A0k = resources.getDimensionPixelSize(R.dimen.abc_floating_window_z);
            r2.A09(this.mBinding.socialReauthView);
        }
        this.mBinding.socialTabletSideNav.initialize(socialReauthTabletPanelApp, SocialTabletType.REAUTH, ProfileType.FACEBOOK);
        SocialReauthTabletMainBinding socialReauthTabletMainBinding2 = this.mBinding;
        OCButton oCButton = socialReauthTabletMainBinding2.continueBtn;
        oCButton.mEventHandler = socialReauthTabletPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(socialReauthTabletPanelApp) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$dxRDdZT7IQeInqf3brs9EojbAFc2 */
            public final /* synthetic */ SocialReauthTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialReauthView.this.lambda$initialize$0$SocialReauthView(this.f$1, view);
            }
        });
        socialReauthTabletMainBinding2.togglePasswordVisibilityBtn.mEventHandler = socialReauthTabletPanelApp;
        this.mFBProfileFetcher.fetchFBProfile(new FBProfileFetcher.FetchCallback() {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$VAtYw438xXnFIFSO5aJQ67fpIZA2 */

            @Override // com.oculus.common.socialtablet.fetchers.FBProfileFetcher.FetchCallback
            public final void onFBProfileFetched(String str, String str2) {
                SocialReauthView.this.lambda$initialize$1$SocialReauthView(str, str2);
            }
        });
        this.mBinding.togglePasswordVisibilityBtn.setOnClickListener(new View.OnClickListener(socialReauthTabletPanelApp) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthView$_Unooq5aGDLnukiDPzZ0Nq6RWGI2 */
            public final /* synthetic */ SocialReauthTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialReauthView.this.lambda$initialize$2$SocialReauthView(this.f$1, view);
            }
        });
        initializePasswordInput(socialReauthTabletPanelApp);
    }
}
