package com.oculus.panelapp.parties.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.parties.views.actions.PartyActionHandler;
import com.oculus.socialplatform.R;

public class PartyTravelNetworkHelper {
    public static final String TAG = LoggingUtil.tag(PartyTravelNetworkHelper.class);
    @Nullable
    public AsyncQueryHandle mLaunchGroupLaunchAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mLaunchGroupLaunchSoloAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mPrepareGroupLaunchAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mSetGroupLaunchResponseAsyncQueryHandle;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLaunchGroupLaunchAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLaunchGroupLaunchAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLaunchGroupLaunchAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLaunchGroupLaunchSoloAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLaunchGroupLaunchSoloAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLaunchGroupLaunchSoloAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrepareGroupLaunchAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mPrepareGroupLaunchAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mPrepareGroupLaunchAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetGroupLaunchResponseAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mSetGroupLaunchResponseAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSetGroupLaunchResponseAsyncQueryHandle = null;
        }
    }

    public void destroy() {
        clearLaunchGroupLaunchSoloAsyncQueryHandle();
        clearSetGroupLaunchResponseAsyncQueryHandle();
        clearLaunchGroupLaunchAsyncQueryHandle();
        clearPrepareGroupLaunchAsyncQueryHandle();
    }

    public void launchGroupLaunch(@NonNull String str, @NonNull final Context context, @Nullable final PartyActionHandler partyActionHandler) {
        clearLaunchGroupLaunchAsyncQueryHandle();
        this.mLaunchGroupLaunchAsyncQueryHandle = HorizonContentProviderHelper.launchGroupLaunch(context, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyTravelNetworkHelper.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_party_launch_group_launch_error", context.getResources().getString(R.string.parties_tablet_app_launch_failed), PartyTravelNetworkHelper.TAG);
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onError();
                }
                PartyTravelNetworkHelper.this.clearLaunchGroupLaunchAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                }
                PartyTravelNetworkHelper.this.clearLaunchGroupLaunchAsyncQueryHandle();
            }
        });
    }

    public void launchGroupLaunchSolo(@NonNull String str, @NonNull final Context context, @Nullable final PartyActionHandler partyActionHandler) {
        clearLaunchGroupLaunchSoloAsyncQueryHandle();
        this.mLaunchGroupLaunchSoloAsyncQueryHandle = HorizonContentProviderHelper.launchGroupLaunchSolo(context, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyTravelNetworkHelper.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_party_app_launch_error", context.getResources().getString(R.string.parties_tablet_app_launch_failed), PartyTravelNetworkHelper.TAG);
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onError();
                }
                PartyTravelNetworkHelper.this.clearLaunchGroupLaunchSoloAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                }
                PartyTravelNetworkHelper.this.clearLaunchGroupLaunchSoloAsyncQueryHandle();
            }
        });
    }

    public void prepareGroupLaunch(@NonNull String str, @NonNull final Context context, @Nullable final PartyActionHandler partyActionHandler) {
        clearPrepareGroupLaunchAsyncQueryHandle();
        this.mPrepareGroupLaunchAsyncQueryHandle = HorizonContentProviderHelper.prepareGroupLaunch(context, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyTravelNetworkHelper.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_party_prepare_group_launch_error", context.getResources().getString(R.string.parties_tablet_prepare_group_launch_failed), PartyTravelNetworkHelper.TAG);
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onError();
                }
                PartyTravelNetworkHelper.this.clearPrepareGroupLaunchAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                }
                PartyTravelNetworkHelper.this.clearPrepareGroupLaunchAsyncQueryHandle();
            }
        });
    }

    public void setGroupLaunchUserResponse(@NonNull String str, @NonNull SocialGroupLaunchResponse socialGroupLaunchResponse, @NonNull final Context context, @Nullable final PartyActionHandler partyActionHandler) {
        clearSetGroupLaunchResponseAsyncQueryHandle();
        this.mSetGroupLaunchResponseAsyncQueryHandle = HorizonContentProviderHelper.setGroupLaunchResponse(context, str, socialGroupLaunchResponse, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyTravelNetworkHelper.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_party_set_group_launch_user_response_error", context.getResources().getString(R.string.parties_tablet_group_launch_set_user_response_failed), PartyTravelNetworkHelper.TAG);
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onError();
                }
                PartyTravelNetworkHelper.this.clearSetGroupLaunchResponseAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                }
                PartyTravelNetworkHelper.this.clearSetGroupLaunchResponseAsyncQueryHandle();
            }
        });
    }
}
