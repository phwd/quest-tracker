package com.oculus.panelapp.androiddialog.dialogs.integrity;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityParticipantSelectItemBinding;
import com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder;
import com.oculus.vrshell.SystemUXRoute;

public class ParticipantViewHolder extends RecyclerView.ViewHolder {
    private static final String NO_OCULUS_USER_ID = "0";
    private final ActionType mActionType;
    private final MessengerIntegrityParticipantSelectItemBinding mBinding;
    private final BlockSelectorClickCallback mBlockCallback;
    private final AndroidDialogPanelApp mPanelApp;
    private final SocialLogger mSocialLogger;
    private final String mThreadKey;
    private final String mUserFBID;

    @FunctionalInterface
    public interface BlockSelectorClickCallback {
        void run(MessengerThreadParticipant messengerThreadParticipant);
    }

    ParticipantViewHolder(MessengerIntegrityParticipantSelectItemBinding messengerIntegrityParticipantSelectItemBinding, AndroidDialogPanelApp androidDialogPanelApp, SocialLogger socialLogger, ActionType actionType, String str, String str2, BlockSelectorClickCallback blockSelectorClickCallback) {
        super(messengerIntegrityParticipantSelectItemBinding.getRoot());
        this.mBinding = messengerIntegrityParticipantSelectItemBinding;
        this.mPanelApp = androidDialogPanelApp;
        this.mSocialLogger = socialLogger;
        this.mActionType = actionType;
        this.mThreadKey = str;
        this.mUserFBID = str2;
        this.mBlockCallback = blockSelectorClickCallback;
    }

    public void setParticipant(ParticipantThumbnailLoader participantThumbnailLoader, MessengerThreadParticipant messengerThreadParticipant) {
        this.mBinding.setParticipant(messengerThreadParticipant);
        setParticipant(this.mPanelApp, this.mSocialLogger, participantThumbnailLoader, this.mBinding.participantPhoto, this.mBinding.participantItem, messengerThreadParticipant, this.mActionType, this.mBlockCallback, this.mPanelApp.getContext().getPackageName(), this.mUserFBID, this.mThreadKey, Uri.parse(messengerThreadParticipant.getProfilePictureUri()));
    }

    @VisibleForTesting
    static void setParticipant(AndroidDialogPanelApp androidDialogPanelApp, SocialLogger socialLogger, ParticipantThumbnailLoader participantThumbnailLoader, ImageView imageView, View view, MessengerThreadParticipant messengerThreadParticipant, ActionType actionType, BlockSelectorClickCallback blockSelectorClickCallback, String str, String str2, String str3, Uri uri) {
        participantThumbnailLoader.load(uri, imageView);
        view.setOnClickListener(new View.OnClickListener(socialLogger, blockSelectorClickCallback, messengerThreadParticipant, str, str2, str3, androidDialogPanelApp) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.$$Lambda$ParticipantViewHolder$MYKcDMib4B1lpvS4III9oCa8ao */
            private final /* synthetic */ SocialLogger f$1;
            private final /* synthetic */ ParticipantViewHolder.BlockSelectorClickCallback f$2;
            private final /* synthetic */ MessengerThreadParticipant f$3;
            private final /* synthetic */ String f$4;
            private final /* synthetic */ String f$5;
            private final /* synthetic */ String f$6;
            private final /* synthetic */ AndroidDialogPanelApp f$7;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
                this.f$7 = r8;
            }

            public final void onClick(View view) {
                ParticipantViewHolder.lambda$setParticipant$44(ActionType.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, view);
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$androiddialog$dialogs$integrity$ActionType = new int[ActionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.panelapp.androiddialog.dialogs.integrity.ActionType[] r0 = com.oculus.panelapp.androiddialog.dialogs.integrity.ActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$integrity$ActionType = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$integrity$ActionType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.androiddialog.dialogs.integrity.ActionType r1 = com.oculus.panelapp.androiddialog.dialogs.integrity.ActionType.Block     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$integrity$ActionType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.androiddialog.dialogs.integrity.ActionType r1 = com.oculus.panelapp.androiddialog.dialogs.integrity.ActionType.Report     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    static /* synthetic */ void lambda$setParticipant$44(ActionType actionType, SocialLogger socialLogger, BlockSelectorClickCallback blockSelectorClickCallback, MessengerThreadParticipant messengerThreadParticipant, String str, String str2, String str3, AndroidDialogPanelApp androidDialogPanelApp, View view) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$integrity$ActionType[actionType.ordinal()];
        if (i == 1) {
            socialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_SELECTOR_BLOCK, SurfaceType.MESSENGER_INTEGRITY);
            blockSelectorClickCallback.run(messengerThreadParticipant);
        } else if (i == 2) {
            socialLogger.logButtonClick(ClickEventButtonId.MESSENGER_INTEGRITY_DIALOG_SELECTOR_REPORT, SurfaceType.MESSENGER_INTEGRITY);
            androidDialogPanelApp.actionNavigate(SystemUXRoute.USER_REPORT, new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(str).appendPath(NO_OCULUS_USER_ID).appendPath("messenger").appendQueryParameter("reportee_fbid", messengerThreadParticipant.getId()).appendQueryParameter("reportee_name", messengerThreadParticipant.getName()).appendQueryParameter("reporter_fbid", str2).appendQueryParameter("thread_key", str3).build().toString());
        }
    }
}
