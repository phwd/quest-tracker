package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.utils.PartyUtils;
import com.oculus.panelapp.parties.views.PartiesViewModel;
import com.oculus.panelapp.parties.views.actions.OCInviteToParty;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OCInviteToParty extends PartyAction {
    @Nullable
    public AsyncQueryHandle mFetchSocialFriendsAsyncQueryHandle;
    public PartiesTabletPanelApp mPanelApp;
    public SocialParty mParty;
    public DialogDefinitionBase mPartyInviteDialog;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialFriendsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchSocialFriendsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchSocialFriendsAsyncQueryHandle = null;
        }
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.INVITE_TO_PARTY;
    }

    public /* synthetic */ boolean lambda$performAction$0$OCInviteToParty(Context context, final PartyActionHandler partyActionHandler, DialogResult dialogResult) {
        final String str = this.mParty.mID;
        String str2 = dialogResult.mDialogAction;
        if (str2.equals(SocialPartyDialogs.INVITE_ACTION)) {
            this.mPanelApp.mSocialLogger.logButtonClick(ClickEventButtonId.PARTIES_TAB_OC_ADD_TO_PARTY_INVITE_BUTTON, SurfaceType.PARTY_OC_ADD_TO_PARTY, ImmutableMap.A05("party_id", str));
            List<String> list = dialogResult.mDialogSelectedListItemIds;
            if (list != null && !list.isEmpty()) {
                PartyUtils.inviteUsersToParty(context, this.mPanelApp, list, str, new PartyActionHandler() {
                    /* class com.oculus.panelapp.parties.views.actions.OCInviteToParty.AnonymousClass1 */

                    @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                    public void onError() {
                        OCInviteToParty.this.mPanelApp.mSocialLogger.logActionFailure(ActionId.PARTY_SEND_INVITE, ClickEventButtonId.PARTIES_TAB_OC_ADD_TO_PARTY_INVITE_BUTTON, SurfaceType.PARTY_OC_ADD_TO_PARTY, "Error inviting users to party", "party_id", str);
                        PartyActionHandler partyActionHandler = partyActionHandler;
                        if (partyActionHandler != null) {
                            partyActionHandler.onError();
                        }
                    }

                    @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                    public void onSuccess() {
                        PartiesViewModel partiesViewModel = OCInviteToParty.this.mPanelApp.mPartiesViewModel;
                        if (partiesViewModel != null) {
                            partiesViewModel.loadPartyData();
                        }
                        OCInviteToParty.this.mPanelApp.mSocialLogger.logActionSuccess(ActionId.PARTY_SEND_INVITE, ClickEventButtonId.PARTIES_TAB_OC_ADD_TO_PARTY_INVITE_BUTTON, SurfaceType.PARTY_OC_ADD_TO_PARTY, "party_id", str);
                        PartyActionHandler partyActionHandler = partyActionHandler;
                        if (partyActionHandler != null) {
                            partyActionHandler.onSuccess();
                        }
                    }
                });
            }
        } else if (str2.equals("cancel")) {
            this.mPanelApp.mSocialLogger.logButtonClick(ClickEventButtonId.PARTIES_TAB_OC_ADD_TO_PARTY_CANCEL_BUTTON, SurfaceType.PARTY_OC_ADD_TO_PARTY, ImmutableMap.A05("party_id", str));
            if (partyActionHandler != null) {
                partyActionHandler.onSuccess();
                return true;
            }
        } else if (partyActionHandler == null) {
            return false;
        } else {
            partyActionHandler.onError();
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, @Nullable PartyActionHandler partyActionHandler) {
        if (this.mParty != null) {
            showInviteToPartyDialog(context, new DialogResultHandler(context, partyActionHandler) {
                /* class com.oculus.panelapp.parties.views.actions.$$Lambda$OCInviteToParty$ZTogFqzjLt6qnR33NwTUWRHcR3w2 */
                public final /* synthetic */ Context f$1;
                public final /* synthetic */ PartyActionHandler f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return OCInviteToParty.this.lambda$performAction$0$OCInviteToParty(this.f$1, this.f$2, dialogResult);
                }
            });
        } else if (partyActionHandler != null) {
            partyActionHandler.onError();
        }
    }

    public OCInviteToParty(PartiesTabletPanelApp partiesTabletPanelApp, SocialParty socialParty) {
        this.mPanelApp = partiesTabletPanelApp;
        this.mParty = socialParty;
    }

    @VisibleForTesting
    public AsyncQueryHandle fetchSocialFriends(Context context, @Nullable String[] strArr, @Nullable Integer num, HorizonContentProviderHelper.FetchSocialFriendsCallback fetchSocialFriendsCallback) {
        return HorizonContentProviderHelper.fetchSocialFriends(context, strArr, num, fetchSocialFriendsCallback);
    }

    @VisibleForTesting
    public DialogDefinitionBase getPartyInviteDialog(Resources resources, List<SocialUser> list) {
        return SocialPartyDialogs.getPartyInviteDialog(resources, list);
    }

    @VisibleForTesting
    public void showInviteToPartyDialog(final Context context, final DialogResultHandler dialogResultHandler) {
        clearFetchSocialFriendsAsyncQueryHandle();
        this.mFetchSocialFriendsAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialFriends(context, null, null, new HorizonContentProviderHelper.FetchSocialFriendsCallback() {
            /* class com.oculus.panelapp.parties.views.actions.OCInviteToParty.AnonymousClass2 */

            public /* synthetic */ boolean lambda$onSuccess$0$OCInviteToParty$2(SocialUser socialUser) {
                if (socialUser == null || OCInviteToParty.this.mParty.getUserPartyMembership(socialUser) != SocialParty.PartyMembership.NONE) {
                    return false;
                }
                return true;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onError(Exception exc) {
                OCInviteToParty.this.clearFetchSocialFriendsAsyncQueryHandle();
                OCInviteToParty.this.mPartyInviteDialog = SocialPartyDialogs.getPartyInviteDialog(context.getResources(), null);
                OCInviteToParty oCInviteToParty = OCInviteToParty.this;
                DialogDefinitionBase dialogDefinitionBase = oCInviteToParty.mPartyInviteDialog;
                dialogDefinitionBase.mDialogResultHandler = dialogResultHandler;
                oCInviteToParty.mPanelApp.mDialogManager.showDialog(dialogDefinitionBase);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onSuccess(List<SocialUser> list) {
                OCInviteToParty.this.clearFetchSocialFriendsAsyncQueryHandle();
                OCInviteToParty.this.mPartyInviteDialog = SocialPartyDialogs.getPartyInviteDialog(context.getResources(), (List) list.stream().filter(new Predicate() {
                    /* class com.oculus.panelapp.parties.views.actions.$$Lambda$OCInviteToParty$2$lHRpsdNztHxnR8TISDtnD8FAqDI2 */

                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return OCInviteToParty.AnonymousClass2.this.lambda$onSuccess$0$OCInviteToParty$2((SocialUser) obj);
                    }
                }).collect(Collectors.toList()));
                OCInviteToParty oCInviteToParty = OCInviteToParty.this;
                DialogDefinitionBase dialogDefinitionBase = oCInviteToParty.mPartyInviteDialog;
                dialogDefinitionBase.mDialogResultHandler = dialogResultHandler;
                oCInviteToParty.mPanelApp.mDialogManager.showDialog(dialogDefinitionBase);
            }
        });
    }

    @VisibleForTesting
    public void inviteUsersToParty(Context context, PartiesTabletPanelApp partiesTabletPanelApp, List<String> list, String str, PartyActionHandler partyActionHandler) {
        PartyUtils.inviteUsersToParty(context, partiesTabletPanelApp, list, str, partyActionHandler);
    }
}
