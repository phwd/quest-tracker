package com.oculus.panelapp.androiddialog.dialogs.social;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialPartyPrivacyInfo;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel;
import com.oculus.panelapp.androiddialog.logging.social.SocialLogger;
import java.util.function.Function;

public class SocialPartyPrivacyViewModel extends SocialViewModel {
    private static final String TAG = LoggingUtil.tag(SocialPartyPrivacyViewModel.class);
    @Nullable
    private AsyncQueryHandle mActivatePartyLinkInviteAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mDeactivatePartyLinkInviteAsyncQueryHandle;
    private boolean mHasLinkInvite;
    private String mPartyId;
    private SocialPartyType mPartyType;
    private String mPartyUrl;
    private final Resources mResources;
    @Nullable
    private AsyncQueryHandle mSetPartyTypeAsyncQueryHandle;
    private final SocialLogger mSocialLogger;
    private SocialPartyPrivacyRequestFactory mSocialPartyPrivacyRequestFactory;

    @FunctionalInterface
    public interface OnErrorCallback {
        void onError(ErrorType errorType);
    }

    @FunctionalInterface
    public interface OnSuccessCallback {
        void onSuccess();
    }

    public SocialPartyPrivacyViewModel(Context context, SocialLogger socialLogger, SocialPartyPrivacyRequestFactory socialPartyPrivacyRequestFactory) {
        this.mResources = context.getResources();
        this.mSocialLogger = socialLogger;
        this.mSocialPartyPrivacyRequestFactory = socialPartyPrivacyRequestFactory;
    }

    public void fetch(Context context, String str, @Nullable OnSuccessCallback onSuccessCallback, OnErrorCallback onErrorCallback) {
        registerQueryHandle("partyInfo", fetchPartyPrivacyInfo(context, str, onSuccessCallback, onErrorCallback));
    }

    public void togglePartyType(Context context, OnErrorCallback onErrorCallback) {
        if (this.mPartyType == SocialPartyType.CLOSED) {
            setPartyJoinType(context, SocialPartyType.JOINABLE_BY_FRIENDS, onErrorCallback);
        } else {
            setPartyJoinType(context, SocialPartyType.CLOSED, onErrorCallback);
        }
    }

    public void toggleLinkInvite(Context context, OnErrorCallback onErrorCallback) {
        if (this.mHasLinkInvite) {
            deactivatePartyLinkInvite(context, onErrorCallback);
        } else {
            activatePartyLinkInvite(context, onErrorCallback);
        }
    }

    private Function<String, AsyncQueryHandle> fetchPartyPrivacyInfo(Context context, String str, @Nullable OnSuccessCallback onSuccessCallback, OnErrorCallback onErrorCallback) {
        return new Function(str, onSuccessCallback, onErrorCallback) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyViewModel$8SrpzUD5TN4Rpfg2JYk0JjTn24 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ SocialPartyPrivacyViewModel.OnSuccessCallback f$2;
            private final /* synthetic */ SocialPartyPrivacyViewModel.OnErrorCallback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialPartyPrivacyViewModel.this.lambda$fetchPartyPrivacyInfo$85$SocialPartyPrivacyViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        };
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchPartyPrivacyInfo$85$SocialPartyPrivacyViewModel(String str, @Nullable final OnSuccessCallback onSuccessCallback, final OnErrorCallback onErrorCallback, final String str2) {
        return this.mSocialPartyPrivacyRequestFactory.fetchPartyPrivacyInfo(str, new HorizonContentProviderHelper.FetchPartyPrivacyInfoCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPartyPrivacyInfoCallback
            public void onSuccess(SocialPartyPrivacyInfo socialPartyPrivacyInfo) {
                SocialPartyPrivacyViewModel.this.mPartyId = socialPartyPrivacyInfo.getId();
                SocialPartyPrivacyViewModel.this.mPartyType = SocialPartyType.fromPartyType(socialPartyPrivacyInfo.getPartyType());
                SocialPartyPrivacyViewModel.this.mHasLinkInvite = socialPartyPrivacyInfo.getHasLinkInvite();
                SocialPartyPrivacyViewModel.this.mPartyUrl = socialPartyPrivacyInfo.getPartyUrl();
                SocialPartyPrivacyViewModel.this.notifyChange();
                OnSuccessCallback onSuccessCallback = onSuccessCallback;
                if (onSuccessCallback != null) {
                    onSuccessCallback.onSuccess();
                }
                SocialPartyPrivacyViewModel.this.clearHandle(str2);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.d(SocialPartyPrivacyViewModel.TAG, "onError: An error occurred while fetching party privacy info");
                onErrorCallback.onError(ErrorType.PARTY_PRIVACY);
                SocialPartyPrivacyViewModel.this.clearHandle(str2);
            }
        });
    }

    private void activatePartyLinkInvite(final Context context, final OnErrorCallback onErrorCallback) {
        clearActivatePartyLinkInviteAsyncQueryHandle();
        this.mActivatePartyLinkInviteAsyncQueryHandle = this.mSocialPartyPrivacyRequestFactory.activatePartyLinkInvite(this.mPartyId, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyPrivacyViewModel.this.setHasLinkInvite(true);
                SocialPartyPrivacyViewModel.this.clearActivatePartyLinkInviteAsyncQueryHandle();
                SocialPartyPrivacyViewModel.this.fetch(context, str, null, onErrorCallback);
                SocialPartyPrivacyViewModel.this.mSocialLogger.logActionSuccess(ActionId.PARTY_TOGGLE_LINK_INVITE_ON, ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, "party_id", SocialPartyPrivacyViewModel.this.mPartyId);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyPrivacyViewModel.this.clearActivatePartyLinkInviteAsyncQueryHandle();
                SocialPartyPrivacyViewModel.this.mSocialLogger.logActionFailure(ActionId.PARTY_TOGGLE_LINK_INVITE_ON, ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, "Error activating party link invite", "party_id", SocialPartyPrivacyViewModel.this.mPartyId);
                onErrorCallback.onError(ErrorType.PARTY_PRIVACY);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivatePartyLinkInviteAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mActivatePartyLinkInviteAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mActivatePartyLinkInviteAsyncQueryHandle = null;
        }
    }

    private void deactivatePartyLinkInvite(final Context context, final OnErrorCallback onErrorCallback) {
        clearDeactivatePartyLinkInviteAsyncQueryHandle();
        this.mDeactivatePartyLinkInviteAsyncQueryHandle = this.mSocialPartyPrivacyRequestFactory.deactivatePartyLinkInvite(this.mPartyId, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyPrivacyViewModel.this.setHasLinkInvite(false);
                SocialPartyPrivacyViewModel.this.clearDeactivatePartyLinkInviteAsyncQueryHandle();
                SocialPartyPrivacyViewModel.this.fetch(context, str, null, onErrorCallback);
                SocialPartyPrivacyViewModel.this.mSocialLogger.logActionSuccess(ActionId.PARTY_TOGGLE_LINK_INVITE_OFF, ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, "party_id", SocialPartyPrivacyViewModel.this.mPartyId);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyPrivacyViewModel.this.clearDeactivatePartyLinkInviteAsyncQueryHandle();
                SocialPartyPrivacyViewModel.this.mSocialLogger.logActionFailure(ActionId.PARTY_TOGGLE_LINK_INVITE_OFF, ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, "Error deactivating party link invite", "party_id", SocialPartyPrivacyViewModel.this.mPartyId);
                onErrorCallback.onError(ErrorType.PARTY_PRIVACY);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeactivatePartyLinkInviteAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mDeactivatePartyLinkInviteAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mDeactivatePartyLinkInviteAsyncQueryHandle = null;
        }
    }

    private void setPartyJoinType(Context context, final SocialPartyType socialPartyType, final OnErrorCallback onErrorCallback) {
        clearSetPartyTypeAsyncQueryHandle();
        this.mSetPartyTypeAsyncQueryHandle = this.mSocialPartyPrivacyRequestFactory.setPartyType(this.mPartyId, socialPartyType, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyPrivacyViewModel.this.setPartyType(socialPartyType);
                SocialPartyPrivacyViewModel.this.clearSetPartyTypeAsyncQueryHandle();
                SocialPartyPrivacyViewModel.this.mSocialLogger.logActionSuccess(SocialPartyPrivacyViewModel.this.partyTypeToLoggingAction(socialPartyType), ClickEventButtonId.PARTY_PRIVACY_PARTY_TYPE, SurfaceType.PARTY_PRIVACY, "party_id", SocialPartyPrivacyViewModel.this.mPartyId);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyPrivacyViewModel.this.clearSetPartyTypeAsyncQueryHandle();
                SocialPartyPrivacyViewModel.this.mSocialLogger.logActionFailure(SocialPartyPrivacyViewModel.this.partyTypeToLoggingAction(socialPartyType), ClickEventButtonId.PARTY_PRIVACY_PARTY_TYPE, SurfaceType.PARTY_PRIVACY, String.format("Error setting party join type to %s", socialPartyType.toString()), "party_id", SocialPartyPrivacyViewModel.this.mPartyId);
                onErrorCallback.onError(ErrorType.PARTY_PRIVACY);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetPartyTypeAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mActivatePartyLinkInviteAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mActivatePartyLinkInviteAsyncQueryHandle = null;
        }
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
    public SocialPartyType getPartyType() {
        return this.mPartyType;
    }

    @Bindable({"partyType"})
    public boolean getIsInviteOnly() {
        SocialPartyType socialPartyType = this.mPartyType;
        return socialPartyType == null || socialPartyType == SocialPartyType.CLOSED;
    }

    @Bindable
    public void setPartyType(SocialPartyType socialPartyType) {
        this.mPartyType = socialPartyType;
        notifyPropertyChanged(BR.partyType);
    }

    @Bindable
    public boolean getHasLinkInvite() {
        return this.mHasLinkInvite;
    }

    @Bindable
    public void setHasLinkInvite(boolean z) {
        this.mHasLinkInvite = z;
        notifyPropertyChanged(BR.hasLinkInvite);
    }

    @Bindable
    public String getPartyUrl() {
        return this.mPartyUrl;
    }

    @Bindable
    public void setPartyUrl(String str) {
        this.mPartyUrl = str;
        notifyPropertyChanged(BR.partyUrl);
    }

    @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialViewModel
    public void destroy() {
        clearActivatePartyLinkInviteAsyncQueryHandle();
        clearDeactivatePartyLinkInviteAsyncQueryHandle();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialPartyType = new int[SocialPartyType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.horizoncontent.social.SocialPartyType[] r0 = com.oculus.horizoncontent.social.SocialPartyType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass5.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass5.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.social.SocialPartyType r1 = com.oculus.horizoncontent.social.SocialPartyType.CLOSED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass5.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.social.SocialPartyType r1 = com.oculus.horizoncontent.social.SocialPartyType.JOINABLE_BY_FRIENDS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.AnonymousClass5.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ActionId partyTypeToLoggingAction(SocialPartyType socialPartyType) {
        int i = AnonymousClass5.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType[socialPartyType.ordinal()];
        if (i == 1) {
            return ActionId.PARTY_SET_JOIN_TYPE_INVITE_ONLY;
        }
        if (i != 2) {
            return ActionId.PARTY_SET_JOIN_TYPE_UNKNOWN;
        }
        return ActionId.PARTY_SET_JOIN_TYPE_FRIENDS_OF_PARTICIPANTS;
    }
}
