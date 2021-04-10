package com.oculus.panelapp.people.views.holders;

import X.AnonymousClass1Ah;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.panelapp.people.FBLinkedStatus;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleTabletUpsellCardBinding;
import com.oculus.panelapp.people.model.IViewerSocialParty;
import com.oculus.panelapp.people.util.FBLinkingUpsellLauncher;
import com.oculus.panelapp.people.views.PeopleUpsellAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewType;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.UUID;

public class PeopleUpsellViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(PeopleUpsellViewHolder.class);
    public final PeopleTabletUpsellCardBinding mBinding;
    public PeopleTabletPanelApp mPanelApp;

    /* renamed from: com.oculus.panelapp.people.views.holders.PeopleUpsellViewHolder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleUpsellAdapterItemType;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType[] r0 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.holders.PeopleUpsellViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$views$PeopleUpsellAdapterItemType = r2
                com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.CREATE_PARTY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.FIND_FRIENDS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.ALL_FRIENDS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.ALL_NEARBY     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.holders.PeopleUpsellViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    public PeopleUpsellViewHolder(PeopleTabletUpsellCardBinding peopleTabletUpsellCardBinding, PeopleTabletPanelApp peopleTabletPanelApp) {
        super(peopleTabletUpsellCardBinding.mRoot);
        this.mBinding = peopleTabletUpsellCardBinding;
        this.mPanelApp = peopleTabletPanelApp;
        peopleTabletUpsellCardBinding.ctaButton.mEventHandler = peopleTabletPanelApp;
    }

    private void bindCtaButton(PeopleUpsellAdapterItem peopleUpsellAdapterItem) {
        this.mBinding.ctaButton.setOnClickListener(new View.OnClickListener(peopleUpsellAdapterItem) {
            /* class com.oculus.panelapp.people.views.holders.$$Lambda$PeopleUpsellViewHolder$Ig0RrU5M9PvWell8eH00Isn_TM42 */
            public final /* synthetic */ PeopleUpsellAdapterItem f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                PeopleUpsellViewHolder.this.lambda$bindCtaButton$0$PeopleUpsellViewHolder(this.f$1, view);
            }
        });
    }

    private boolean maybeLaunchFbLinkingUpsell() {
        FBLinkedStatus fBLinkedStatus = this.mPanelApp.getFBLinkedStatus();
        if (fBLinkedStatus == FBLinkedStatus.ERROR || fBLinkedStatus == FBLinkedStatus.NOT_READY) {
            Log.e(TAG, "FB link status is errored or not ready.");
            return true;
        } else if (fBLinkedStatus == FBLinkedStatus.LINKED) {
            return false;
        } else {
            FBLinkingUpsellLauncher.showUpsell(this.mPanelApp, PeopleUserActionType.CREATE_PARTY_WITH.name(), ClickEventButtonId.PEOPLE_TAB_UPSELL_CREATE_PARTY.getTelemetryButtonId());
            return true;
        }
    }

    public void bindData(PeopleUpsellAdapterItem peopleUpsellAdapterItem) {
        this.mBinding.setTitle(peopleUpsellAdapterItem.mTitle);
        this.mBinding.icon.setBackground(peopleUpsellAdapterItem.mIcon);
        bindCtaButton(peopleUpsellAdapterItem);
    }

    public /* synthetic */ void lambda$bindCtaButton$0$PeopleUpsellViewHolder(PeopleUpsellAdapterItem peopleUpsellAdapterItem, View view) {
        PeopleTabletPanelApp peopleTabletPanelApp;
        PeopleViewType peopleViewType;
        switch (peopleUpsellAdapterItem.mType.ordinal()) {
            case 0:
                this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_UPSELL_CREATE_PARTY, new String[0]);
                IViewerSocialParty viewerSocialParty = this.mPanelApp.getCurrentPartyFetcher().getViewerSocialParty();
                if (viewerSocialParty != null && viewerSocialParty.getID() != null) {
                    PartyNotificationHelper.getSingleton().sendViewerInPartyToast(this.mPanelApp.mContext, "oc_viewer_already_in_party");
                    return;
                } else if (!maybeLaunchFbLinkingUpsell()) {
                    this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.PEOPLE_TABLET_UPSELL_CTA).build().toString());
                    return;
                } else {
                    return;
                }
            case 1:
                this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_UPSELL_ADD_FRIENDS, new String[0]);
                peopleTabletPanelApp = this.mPanelApp;
                peopleViewType = PeopleViewType.SEARCH;
                break;
            case 2:
                this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_UPSELL_ALL_FRIENDS, new String[0]);
                peopleTabletPanelApp = this.mPanelApp;
                peopleViewType = PeopleViewType.ALL_CONNECTIONS;
                break;
            case 3:
                peopleTabletPanelApp = this.mPanelApp;
                peopleViewType = PeopleViewType.ALL_NEARBY;
                break;
            default:
                return;
        }
        peopleTabletPanelApp.updatePeopleViewType(peopleViewType);
    }
}
