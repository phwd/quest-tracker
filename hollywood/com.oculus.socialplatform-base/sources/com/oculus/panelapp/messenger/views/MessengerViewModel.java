package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.api.models.DraftThread;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;

public class MessengerViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(MessengerViewModel.class);
    public MessengerAPIType mAPIType;
    public ComposeBarType mComposeBarType;
    public String mComposerBlockedViewText;
    public Context mContext;
    public String mCurrentThreadTitle;
    public boolean mIsDraftThreadParticipantListEmpty;
    public boolean mIsDraftingThread;
    public boolean mIsGroupThreadContainingBlockedViewShowing;
    public boolean mIsLeaveChatConfirmationShowing;
    public boolean mIsOfflineViewShowing;
    public boolean mIsOneOnOneBlockedThreadShowing;
    public boolean mIsOneOnOneThreadViewerCannotMessage;
    public boolean mIsPartyButtonEnabled;
    public boolean mIsPartyLinkLoading;
    public boolean mIsSelfThread;
    public boolean mIsThreadParticipantListEmpty;
    public Drawable mPartyButtonDrawable;
    public String mPartyButtonText;
    public boolean mPassesAudioInputGK;
    public boolean mPassesPartyButtonGK;

    public void setCurrentThreadTitle(String str) {
        this.mIsDraftingThread = false;
        this.mIsDraftThreadParticipantListEmpty = true;
        this.mCurrentThreadTitle = str;
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(161);
        notifyPropertyChanged(152);
        notifyPropertyChanged(150);
        notifyPropertyChanged(135);
        notifyPropertyChanged(125);
        notifyPropertyChanged(164);
        notifyPropertyChanged(111);
        notifyPropertyChanged(144);
    }

    public void setDraftThread(@Nullable DraftThread draftThread) {
        this.mCurrentThreadTitle = null;
        boolean z = false;
        boolean z2 = false;
        if (draftThread != null) {
            z2 = true;
        }
        this.mIsDraftingThread = z2;
        if (draftThread != null && draftThread.mParticipants.size() == 0) {
            z = true;
        }
        this.mIsDraftThreadParticipantListEmpty = z;
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(161);
        notifyPropertyChanged(152);
        notifyPropertyChanged(150);
        notifyPropertyChanged(135);
        notifyPropertyChanged(125);
        notifyPropertyChanged(164);
        notifyPropertyChanged(111);
    }

    /* renamed from: com.oculus.panelapp.messenger.views.MessengerViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$messenger$api$MessengerAPIType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.panelapp.messenger.api.MessengerAPIType[] r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.views.MessengerViewModel.AnonymousClass1.$SwitchMap$com$oculus$panelapp$messenger$api$MessengerAPIType = r2
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.FB_MSYS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.OC_CHATS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.TEST     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.MessengerViewModel.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean isComposeHidden() {
        if (this.mIsOfflineViewShowing || this.mIsOneOnOneThreadViewerCannotMessage || this.mIsOneOnOneBlockedThreadShowing || this.mIsGroupThreadContainingBlockedViewShowing || this.mIsLeaveChatConfirmationShowing || ((this.mIsDraftingThread && this.mIsDraftThreadParticipantListEmpty) || isEmptyThreadState())) {
            return true;
        }
        return false;
    }

    private boolean isEmptyThreadState() {
        if (this.mCurrentThreadTitle != null || this.mIsDraftingThread) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Bindable
    public int getAudioInputButtonVisibility() {
        if (!this.mPassesAudioInputGK) {
            return 8;
        }
        return getComposerVisibility();
    }

    @Bindable
    public ComposeBarType getComposeBarType() {
        return this.mComposeBarType;
    }

    @Bindable
    public String getComposerBlockedViewContent() {
        return this.mComposerBlockedViewText;
    }

    @Bindable
    public int getComposerBlockedViewVisibility() {
        if (this.mIsGroupThreadContainingBlockedViewShowing || this.mIsOneOnOneBlockedThreadShowing || !this.mIsOneOnOneThreadViewerCannotMessage) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public int getContextMenuVisibility() {
        if (this.mIsOfflineViewShowing || this.mIsDraftingThread || isEmptyThreadState() || this.mIsThreadParticipantListEmpty) {
            return 8;
        }
        if (this.mIsSelfThread) {
            return 4;
        }
        return 0;
    }

    @Bindable
    public int getCurrentThreadHeaderVisibility() {
        if (this.mIsOfflineViewShowing || isEmptyThreadState() || this.mIsDraftingThread) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public String getCurrentThreadTitle() {
        String str = this.mCurrentThreadTitle;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Bindable
    public int getDraftThreadHeaderVisibility() {
        if (this.mIsOfflineViewShowing || isEmptyThreadState() || !this.mIsDraftingThread) {
            return 8;
        }
        return 0;
    }

    @Bindable({"isPartyLinkLoading", "composerVisibility"})
    public boolean getIsPartyButtonEnabled() {
        if (!this.mIsPartyButtonEnabled || this.mIsPartyLinkLoading || isComposeHidden()) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getIsPartyLinkLoading() {
        return this.mIsPartyLinkLoading;
    }

    @Bindable
    public int getLeftbarViewVisibility() {
        if (this.mIsOfflineViewShowing) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public int getMessageListVisibility() {
        if (this.mIsOfflineViewShowing || this.mIsGroupThreadContainingBlockedViewShowing || this.mIsDraftingThread || this.mIsLeaveChatConfirmationShowing) {
            return 4;
        }
        return 0;
    }

    @Bindable
    public Drawable getPartyButtonDrawable() {
        return this.mPartyButtonDrawable;
    }

    @Bindable
    public String getPartyButtonText() {
        return this.mPartyButtonText;
    }

    @Bindable
    public int getPartyButtonVisibility() {
        if (!this.mPassesPartyButtonGK) {
            return 8;
        }
        return getCurrentThreadHeaderVisibility();
    }

    @Bindable
    public String getTabletTitleText() {
        int i;
        int ordinal = this.mAPIType.ordinal();
        Resources resources = this.mContext.getResources();
        switch (ordinal) {
            case 1:
                i = R.string.tablet_title_fb;
                break;
            case 2:
                i = R.string.tablet_title_oc;
                break;
            default:
                i = R.string.tablet_title_test;
                break;
        }
        return resources.getString(i);
    }

    public void setComposeBarType(ComposeBarType composeBarType) {
        this.mComposeBarType = composeBarType;
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(160);
    }

    public void setIsGroupThreadContainingBlockedViewShowing(boolean z) {
        this.mIsGroupThreadContainingBlockedViewShowing = z;
        notifyPropertyChanged(152);
        notifyPropertyChanged(133);
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(161);
        notifyPropertyChanged(111);
    }

    public void setIsLeaveThreadConfirmationShowing(boolean z) {
        this.mIsLeaveChatConfirmationShowing = z;
        notifyPropertyChanged(111);
        notifyPropertyChanged(161);
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(152);
    }

    public void setIsOfflineViewShowing(boolean z) {
        this.mIsOfflineViewShowing = z;
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(161);
        notifyPropertyChanged(152);
        notifyPropertyChanged(150);
        notifyPropertyChanged(135);
        notifyPropertyChanged(125);
        notifyPropertyChanged(164);
        notifyPropertyChanged(111);
        notifyPropertyChanged(157);
        notifyPropertyChanged(150);
    }

    public void setIsOneOnOneBlockedThreadShowing(boolean z) {
        this.mIsOneOnOneBlockedThreadShowing = z;
        notifyPropertyChanged(152);
        notifyPropertyChanged(133);
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(161);
    }

    public void setIsOneOnOneThreadViewerCannotMessage(boolean z, MessengerAPIType messengerAPIType) {
        Resources resources;
        int i;
        if (messengerAPIType == MessengerAPIType.OC_CHATS) {
            resources = this.mContext.getResources();
            i = R.string.anytime_tablet_messenger_thread_compose_blocked_view_oculus_chats;
        } else {
            resources = this.mContext.getResources();
            i = R.string.anytime_tablet_messenger_thread_compose_blocked_view_vr_messenger;
        }
        this.mComposerBlockedViewText = resources.getString(i);
        this.mIsOneOnOneThreadViewerCannotMessage = z;
        notifyPropertyChanged(152);
        notifyPropertyChanged(134);
        notifyPropertyChanged(133);
        notifyPropertyChanged(142);
        notifyPropertyChanged(127);
        notifyPropertyChanged(161);
    }

    public void setIsPartyActive(boolean z) {
        Context context;
        int i;
        if (z) {
            this.mPartyButtonText = this.mContext.getString(R.string.anytime_tablet_messenger_add_party_button_label);
            context = this.mContext;
            i = R.drawable.oc_icon_party_add_filled_24_d2d2d2;
        } else {
            this.mPartyButtonText = this.mContext.getString(R.string.anytime_tablet_messenger_start_party_button_label);
            context = this.mContext;
            i = R.drawable.oc_icon_parties_filled_24_d2d2d2;
        }
        this.mPartyButtonDrawable = context.getDrawable(i);
        notifyPropertyChanged(130);
        notifyPropertyChanged(138);
    }

    public void setIsPartyButtonEnabled(boolean z) {
        this.mIsPartyButtonEnabled = z;
        notifyPropertyChanged(116);
    }

    public void setIsPartyLinkLoading(boolean z) {
        if (this.mIsPartyLinkLoading != z) {
            this.mIsPartyLinkLoading = z;
            notifyPropertyChanged(129);
        }
    }

    public void setIsPassingAudioInputGK(boolean z) {
        this.mPassesAudioInputGK = z;
        notifyPropertyChanged(152);
    }

    public void setIsPassingPartyButtonGK(boolean z) {
        this.mPassesPartyButtonGK = z;
        notifyPropertyChanged(164);
    }

    public void setIsSelfThread(boolean z) {
        this.mIsSelfThread = z;
        notifyPropertyChanged(150);
    }

    public void setIsThreadParticipantListEmpty(boolean z) {
        this.mIsThreadParticipantListEmpty = z;
        notifyPropertyChanged(150);
    }

    public MessengerViewModel(Context context, MessengerAPIType messengerAPIType) {
        this.mContext = context;
        this.mAPIType = messengerAPIType;
    }

    @Bindable
    public int getComposerCollapsedInputVisibility() {
        if (isComposeHidden() || this.mComposeBarType != ComposeBarType.COLLAPSED) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public int getComposerExpandedInputVisibility() {
        if (isComposeHidden()) {
            return 8;
        }
        ComposeBarType composeBarType = this.mComposeBarType;
        if (composeBarType == ComposeBarType.TEXT_INPUT || composeBarType == ComposeBarType.AUDIO_INPUT) {
            return 0;
        }
        return 8;
    }

    @Bindable
    public int getComposerVisibility() {
        if (isComposeHidden()) {
            return 8;
        }
        return 0;
    }
}
