package com.oculus.panelapp.messenger.views;

import X.AnonymousClass006;
import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import X.AnonymousClass2NC;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MailboxListener;
import com.oculus.panelapp.messenger.api.MessengerActionCallback;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerTabletOfflineViewBinding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2Binding;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;

public class MessengerView extends BaseView {
    public static final String TAG = LoggingUtil.tag(MessengerView.class);
    public AnytimeTabletMessengerViewV2Binding mBinding;
    public Context mContext;
    public LeftbarView mLeftBarView;
    public MailboxListener mMailboxListener;
    public MessengerViewModel mMessengerViewModel;
    public AnytimeTabletMessengerTabletOfflineViewBinding mOfflineViewBinding;
    public MessengerPanelApp mPanelApp;
    public ThreadView mThreadView;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    private void hideOfflineMessengerView() {
        View view = this.mBinding.messengerTabletOfflineViewStub.A00;
        if (view != null && view.getVisibility() != 8) {
            this.mBinding.messengerTabletOfflineViewStub.A00.setVisibility(8);
        }
    }

    private void onDeepLinkThread(String str) {
        String[] split = str.split("/");
        if (split.length == 5 && "mailbox".equals(split[1])) {
            try {
                long parseLong = Long.parseLong(split[4]);
                Long.parseLong(split[2]);
                final long currentTimeMillis = System.currentTimeMillis();
                this.mPanelApp.getAPIManager().mCurrentAPI.updateCurrentThread(parseLong, new MessengerActionCallback() {
                    /* class com.oculus.panelapp.messenger.views.MessengerView.AnonymousClass1 */

                    @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                    public void onError(String str, String str2) {
                        Log.e(MessengerView.TAG, "Update current thread failed (from deeplink)");
                        MessengerView.this.mPanelApp.getLogger().logActionFailure(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.DEEPLINKED_THREAD, str2, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
                    }

                    @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                    public void onSuccess(String str) {
                        MessengerView.this.mPanelApp.getLogger().logActionSuccess(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.DEEPLINKED_THREAD, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
                    }
                });
            } catch (NumberFormatException unused) {
                Log.e(TAG, AnonymousClass006.A07("Invalid deeplink ", str));
            }
        }
    }

    private void showOfflineMessengerView() {
        AnonymousClass2NC r2 = this.mBinding.messengerTabletOfflineViewStub;
        View view = r2.A00;
        if (view != null) {
            view.setVisibility(0);
            return;
        }
        $$Lambda$MessengerView$z57fRF8aSxS3djaAyLSng6rT6As2 r1 = new ViewStub.OnInflateListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$MessengerView$z57fRF8aSxS3djaAyLSng6rT6As2 */

            public final void onInflate(ViewStub viewStub, View view) {
                MessengerView.this.lambda$showOfflineMessengerView$1$MessengerView(viewStub, view);
            }
        };
        ViewStub viewStub = r2.A02;
        if (viewStub != null) {
            r2.A01 = r1;
        }
        viewStub.inflate();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        ThreadView threadView = this.mThreadView;
        if (threadView != null) {
            threadView.destroy();
        }
        LeftbarView leftbarView = this.mLeftBarView;
        if (leftbarView != null) {
            leftbarView.destroy();
        }
        if (this.mMailboxListener != null) {
            this.mPanelApp.getAPIManager().removeMailboxListener(this.mMailboxListener);
            this.mMailboxListener = null;
        }
        AnytimeTabletMessengerTabletOfflineViewBinding anytimeTabletMessengerTabletOfflineViewBinding = this.mOfflineViewBinding;
        if (anytimeTabletMessengerTabletOfflineViewBinding != null) {
            anytimeTabletMessengerTabletOfflineViewBinding.messengerTabletOfflineButton.setOnClickListener(null);
        }
    }

    public /* synthetic */ void lambda$null$0$MessengerView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.WIFI, "");
    }

    public void onInternetConnectionStatusChanged(boolean z) {
        this.mMessengerViewModel.setIsOfflineViewShowing(!z);
        if (!z) {
            showOfflineMessengerView();
        } else {
            hideOfflineMessengerView();
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        if (str != null && !str.isEmpty()) {
            onDeepLinkThread(str);
        }
    }

    public MessengerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void initialize(MessengerPanelApp messengerPanelApp, AnonymousClass1uW r6) {
        super.initialize(messengerPanelApp.getAndroidPanelApp(), r6);
        this.mPanelApp = messengerPanelApp;
        this.mBinding = (AnytimeTabletMessengerViewV2Binding) r6;
        messengerPanelApp.logSectionEntry(SectionTrackerEvent.MESSENGER_TABLET);
        MessengerViewModel messengerViewModel = new MessengerViewModel(this.mContext, messengerPanelApp.getAPIManager().mCurrentAPI.getType());
        this.mMessengerViewModel = messengerViewModel;
        this.mBinding.setMessengerViewModel(messengerViewModel);
        this.mLeftBarView = new LeftbarView(messengerPanelApp, this.mBinding, this.mContext, this.mMessengerViewModel);
        this.mThreadView = new ThreadView(messengerPanelApp, this.mBinding, this.mMessengerViewModel);
    }

    public /* synthetic */ void lambda$showOfflineMessengerView$1$MessengerView(ViewStub viewStub, View view) {
        AnytimeTabletMessengerTabletOfflineViewBinding anytimeTabletMessengerTabletOfflineViewBinding = (AnytimeTabletMessengerTabletOfflineViewBinding) AnonymousClass1uU.A01(view);
        this.mOfflineViewBinding = anytimeTabletMessengerTabletOfflineViewBinding;
        anytimeTabletMessengerTabletOfflineViewBinding.messengerTabletOfflineButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$MessengerView$CWbwmKBPJb0QpPjVrnR0cw7qVhE2 */

            public final void onClick(View view) {
                MessengerView.this.lambda$null$0$MessengerView(view);
            }
        });
    }
}
