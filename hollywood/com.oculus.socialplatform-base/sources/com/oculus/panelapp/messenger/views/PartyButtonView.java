package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.IMessengerAPI;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.fetchers.PartiesFetcher;
import com.oculus.panelapp.messenger.util.PartyMutator;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PartyButtonView implements PartiesFetcher.PartiesDataObserver {
    public static final String TAG = LoggingUtil.tag(PartyButtonView.class);
    public final OCButton mButton;
    public final Context mContext;
    public boolean mIsPartyDataStale = true;
    public final MessengerViewModel mMessengerViewModel;
    public final MessengerPanelApp mPanelApp;
    public PartiesFetcher mPartiesFetcher;
    public PartyMutator mPartyMutator;

    private void sendPartyLinkToThread(long j) {
        final String str = this.mPartiesFetcher.mPartyID;
        IMessengerAPI iMessengerAPI = this.mPanelApp.getAPIManager().mCurrentAPI;
        final List<MessengerParticipant> threadParticipants = iMessengerAPI.getThreadParticipants();
        final MessengerThread currentThread = iMessengerAPI.getCurrentThread();
        final MessengerAPIType type = iMessengerAPI.getType();
        this.mPartyMutator.sendPartyLinkToThread(j, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.messenger.views.PartyButtonView.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                PartyButtonView.this.refresh();
                PartyButtonView.this.showGenericErrorToast();
                PartyButtonView.this.mPanelApp.getLogger().logActionFailure(ActionId.PARTY_SEND_LINK_TO_THREAD, ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY, SurfaceType.THREAD_VIEW, "Error sending party link to thread", "party_id", str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyButtonView partyButtonView = PartyButtonView.this;
                partyButtonView.mIsPartyDataStale = true;
                partyButtonView.refresh();
                PartyButtonView.this.showPartyInviteSentToast(currentThread, threadParticipants, type);
                PartyButtonView.this.mPanelApp.getLogger().logActionSuccess(ActionId.PARTY_SEND_LINK_TO_THREAD, ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY, SurfaceType.THREAD_VIEW, "party_id", str);
            }
        });
        refresh();
    }

    private void showPartyInviteSentToastMoreThanTwoPeople(List<MessengerParticipant> list, boolean z) {
        PartyNotificationHelper singleton = PartyNotificationHelper.getSingleton();
        Context context = this.mContext;
        String telemetryButtonId = ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY.getTelemetryButtonId();
        String name = list.get(0).getName();
        int size = list.size() - 1;
        if (z) {
            singleton.sendOCInviteSentConfirmationToastMoreThanTwoPeople(context, telemetryButtonId, name, size);
        } else {
            singleton.sendFBInviteSharedConfirmationToastMoreThanTwoPeople(context, telemetryButtonId, name, size);
        }
    }

    private void showPartyInviteSentToastOnePerson(List<MessengerParticipant> list, boolean z) {
        PartyNotificationHelper singleton = PartyNotificationHelper.getSingleton();
        Context context = this.mContext;
        String telemetryButtonId = ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY.getTelemetryButtonId();
        String name = list.get(0).getName();
        if (z) {
            singleton.sendOCInviteSentConfirmationToast(context, telemetryButtonId, name);
        } else {
            singleton.sendFBInviteSharedConfirmationToast(context, telemetryButtonId, name);
        }
    }

    private void showPartyInviteSentToastTwoPeople(List<MessengerParticipant> list, boolean z) {
        PartyNotificationHelper singleton = PartyNotificationHelper.getSingleton();
        Context context = this.mContext;
        String telemetryButtonId = ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY.getTelemetryButtonId();
        String name = list.get(0).getName();
        String name2 = list.get(1).getName();
        if (z) {
            singleton.sendOCInviteSentConfirmationToastTwoPeople(context, telemetryButtonId, name, name2);
        } else {
            singleton.sendFBInviteSharedConfirmationToastTwoPeople(context, telemetryButtonId, name, name2);
        }
    }

    @Override // com.oculus.panelapp.messenger.fetchers.PartiesFetcher.PartiesDataObserver
    public void onPartiesDataUpdated() {
        this.mIsPartyDataStale = false;
        refresh();
    }

    private boolean hasSomeoneToInvite() {
        Set<String> set = this.mPartiesFetcher.mAllPartyUsers;
        if (set != null) {
            for (MessengerParticipant messengerParticipant : this.mPanelApp.getAPIManager().mCurrentAPI.getThreadParticipants()) {
                if (!set.contains(String.valueOf(messengerParticipant.getParticipantId()))) {
                }
            }
            return false;
        }
        return true;
    }

    private boolean isValidThread() {
        MessengerThread currentThread = this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread();
        if (currentThread == null || currentThread.getThreadKey() == 0) {
            return false;
        }
        return true;
    }

    private void onClick(boolean z, boolean z2, long j) {
        if (!z2) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_START_PARTY, SurfaceType.THREAD_VIEW);
            launchCreateVrInviteDialog(j);
            return;
        }
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY, SurfaceType.THREAD_VIEW);
        if (z) {
            sendPartyLinkToThread(j);
        } else {
            showPartyLinkSharingOffToast();
        }
    }

    public void destroy() {
        OCButton oCButton = this.mButton;
        if (oCButton != null) {
            oCButton.mEventHandler = null;
            oCButton.setOnClickListener(null);
            this.mPanelApp.getAndroidPanelApp().removePanelFrameCallback(this.mPartiesFetcher);
            this.mPartiesFetcher.removeObserver(this);
            this.mPartiesFetcher.destroy();
            this.mPartiesFetcher = null;
            this.mPartyMutator.destroy();
            this.mPartyMutator = null;
        }
    }

    public void initialize() {
        this.mPartiesFetcher.registerObserver(this);
        this.mPanelApp.getAndroidPanelApp().addPanelFrameCallback(this.mPartiesFetcher);
        this.mMessengerViewModel.setIsPartyActive(this.mPartiesFetcher.mIsInParty);
        OCButton oCButton = this.mButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$PartyButtonView$etTfPv04Dcl86P4oqahaQWTMuUo2 */

            public final void onClick(View view) {
                PartyButtonView.this.lambda$initialize$0$PartyButtonView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$PartyButtonView(View view) {
        MessengerThread currentThread = this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread();
        if (currentThread != null) {
            PartiesFetcher partiesFetcher = this.mPartiesFetcher;
            onClick(partiesFetcher.mHasLinkSharing, partiesFetcher.mIsInParty, currentThread.getThreadKey());
        }
    }

    @VisibleForTesting
    public void launchCreateVrInviteDialog(long j) {
        Uri build = new Uri.Builder().appendQueryParameter("thread_key", String.valueOf(j)).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.MESSENGER_START_PARTY).build();
        build.toString();
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, build.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0025, code lost:
        if (hasSomeoneToInvite() == false) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void refresh() {
        /*
            r3 = this;
            com.oculus.panelapp.messenger.views.MessengerViewModel r1 = r3.mMessengerViewModel
            com.oculus.panelapp.messenger.fetchers.PartiesFetcher r0 = r3.mPartiesFetcher
            boolean r0 = r0.mIsInParty
            r1.setIsPartyActive(r0)
            com.oculus.panelapp.messenger.views.MessengerViewModel r1 = r3.mMessengerViewModel
            com.oculus.panelapp.messenger.util.PartyMutator r0 = r3.mPartyMutator
            boolean r0 = r0.isSendPartyLinkLoading()
            r1.setIsPartyLinkLoading(r0)
            com.oculus.panelapp.messenger.views.MessengerViewModel r2 = r3.mMessengerViewModel
            boolean r0 = r3.mIsPartyDataStale
            if (r0 != 0) goto L_0x0027
            boolean r0 = r3.isValidThread()
            if (r0 == 0) goto L_0x0027
            boolean r1 = r3.hasSomeoneToInvite()
            r0 = 1
            if (r1 != 0) goto L_0x0028
        L_0x0027:
            r0 = 0
        L_0x0028:
            r2.setIsPartyButtonEnabled(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.PartyButtonView.refresh():void");
    }

    @VisibleForTesting
    public void showPartyInviteSentToast(MessengerThread messengerThread, List<MessengerParticipant> list, MessengerAPIType messengerAPIType) {
        if (messengerThread == null) {
            return;
        }
        if (messengerAPIType == MessengerAPIType.FB_MSYS || messengerAPIType == MessengerAPIType.OC_CHATS) {
            boolean z = false;
            if (messengerAPIType == MessengerAPIType.OC_CHATS) {
                z = true;
            }
            try {
                List<MessengerParticipant> list2 = (List) list.stream().filter(new Predicate(Long.valueOf(Long.parseLong(this.mPanelApp.getAPIManager().mCurrentAPI.getUserID()))) {
                    /* class com.oculus.panelapp.messenger.views.$$Lambda$PartyButtonView$ZRLCxujUAa3B6mbNbRJtf_cuY42 */
                    public final /* synthetic */ Long f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return PartyButtonView.lambda$showPartyInviteSentToast$1(this.f$0, (MessengerParticipant) obj);
                    }
                }).collect(Collectors.toList());
                if (list2.size() == 0) {
                    return;
                }
                if (list2.size() == 1) {
                    showPartyInviteSentToastOnePerson(list2, z);
                } else if (list2.size() == 2) {
                    showPartyInviteSentToastTwoPeople(list2, z);
                } else if (list2.size() <= 7) {
                    showPartyInviteSentToastMoreThanTwoPeople(list2, z);
                } else {
                    showPartyInviteSentToastGroupGeneric(z);
                }
            } catch (NumberFormatException e) {
                Log.e(TAG, "Unexpected User ID", e);
            }
        }
    }

    public PartyButtonView(OCButton oCButton, Context context, MessengerPanelApp messengerPanelApp, MessengerViewModel messengerViewModel, PartiesFetcher partiesFetcher, PartyMutator partyMutator) {
        this.mButton = oCButton;
        this.mContext = context;
        this.mPanelApp = messengerPanelApp;
        this.mMessengerViewModel = messengerViewModel;
        this.mPartiesFetcher = partiesFetcher;
        this.mPartyMutator = partyMutator;
    }

    public static /* synthetic */ boolean lambda$showPartyInviteSentToast$1(Long l, MessengerParticipant messengerParticipant) {
        if (messengerParticipant.getParticipantId() == l.longValue() || messengerParticipant.getName() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showGenericErrorToast() {
        PartyNotificationHelper.getSingleton().sendGenericErrorToast(this.mContext, ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY.getTelemetryButtonId());
    }

    private void showPartyInviteSentToastGroupGeneric(boolean z) {
        PartyNotificationHelper singleton = PartyNotificationHelper.getSingleton();
        Context context = this.mContext;
        String telemetryButtonId = ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY.getTelemetryButtonId();
        if (z) {
            singleton.sendOCInviteSentConfirmationToastGroupGeneric(context, telemetryButtonId);
        } else {
            singleton.sendFBInviteSharedConfirmationToastGroupGeneric(context, telemetryButtonId);
        }
    }

    private void showPartyLinkSharingOffToast() {
        PartyNotificationHelper.getSingleton().sendLinkSharingOffToast(this.mContext, ClickEventButtonId.AUIV2_MESSENGER_THREAD_ADD_TO_PARTY.getTelemetryButtonId());
    }

    public PartyNotificationHelper getPartyNotificationHelper() {
        return PartyNotificationHelper.getSingleton();
    }
}
