package com.oculus.panelapp.people.views.actions;

import androidx.annotation.Nullable;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.panelapp.people.FBLinkedStatus;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.util.FBLinkingUpsellLauncher;

public abstract class PeopleUserAction {
    public boolean equals(Object obj) {
        if (this != obj) {
            return obj != null && getClass() == obj.getClass() && getType() == ((PeopleUserAction) obj).getType();
        }
        return true;
    }

    public abstract ClickEventButtonId getButtonId();

    public PeopleUserActionHandler getCallback(final PeopleTabletPanelApp peopleTabletPanelApp, @Nullable final PeopleUserActionHandler peopleUserActionHandler, final long j) {
        return new PeopleUserActionHandler() {
            /* class com.oculus.panelapp.people.views.actions.PeopleUserAction.AnonymousClass1 */

            @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
            public void onError(String str) {
                PeopleUserAction.this.logFailure(peopleTabletPanelApp, Long.toString(System.currentTimeMillis() - j), str);
                PeopleUserActionHandler peopleUserActionHandler = peopleUserActionHandler;
                if (peopleUserActionHandler != null) {
                    peopleUserActionHandler.onError(str);
                }
            }

            @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
            public void onSuccess() {
                PeopleUserAction.this.logSuccess(peopleTabletPanelApp, Long.toString(System.currentTimeMillis() - j));
                PeopleUserActionHandler peopleUserActionHandler = peopleUserActionHandler;
                if (peopleUserActionHandler != null) {
                    peopleUserActionHandler.onSuccess();
                }
            }
        };
    }

    public abstract String getTargetUserId();

    public abstract PeopleUserActionType getType();

    public abstract boolean isRelevant();

    /* renamed from: com.oculus.panelapp.people.views.actions.PeopleUserAction$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$actions$PeopleUserActionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.panelapp.people.views.actions.PeopleUserActionType[] r0 = com.oculus.panelapp.people.views.actions.PeopleUserActionType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.actions.PeopleUserAction.AnonymousClass2.$SwitchMap$com$oculus$panelapp$people$views$actions$PeopleUserActionType = r2
                com.oculus.panelapp.people.views.actions.PeopleUserActionType r0 = com.oculus.panelapp.people.views.actions.PeopleUserActionType.JOIN_PARTY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.actions.PeopleUserActionType r0 = com.oculus.panelapp.people.views.actions.PeopleUserActionType.CREATE_PARTY_WITH     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.actions.PeopleUserActionType r0 = com.oculus.panelapp.people.views.actions.PeopleUserActionType.CHAT     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.actions.PeopleUserActionType r0 = com.oculus.panelapp.people.views.actions.PeopleUserActionType.ADD_FRIEND     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.panelapp.people.views.actions.PeopleUserActionType r0 = com.oculus.panelapp.people.views.actions.PeopleUserActionType.ACCEPT_FRIEND_REQUEST     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.actions.PeopleUserAction.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logFailure(PeopleTabletPanelApp peopleTabletPanelApp, String str, String str2) {
        ActionId actionLoggingId = getType().getActionLoggingId();
        if (actionLoggingId != null) {
            peopleTabletPanelApp.getLogger().logActionFailure(actionLoggingId, getButtonId(), peopleTabletPanelApp.getCurrentViewType().getLoggingSurfaceType(), str2, LoggingConstants.TARGET_USERID, getTargetUserId(), LoggingConstants.TIME_TO_COMPLETE_MS, str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logSuccess(PeopleTabletPanelApp peopleTabletPanelApp, String str) {
        ActionId actionLoggingId = getType().getActionLoggingId();
        if (actionLoggingId != null) {
            peopleTabletPanelApp.getLogger().logActionSuccess(actionLoggingId, getButtonId(), peopleTabletPanelApp.getCurrentViewType().getLoggingSurfaceType(), LoggingConstants.TARGET_USERID, getTargetUserId(), LoggingConstants.TIME_TO_COMPLETE_MS, str);
        }
    }

    public void logButtonClick(PeopleTabletPanelApp peopleTabletPanelApp) {
        peopleTabletPanelApp.logButtonClick(getButtonId(), LoggingConstants.TARGET_USERID, getTargetUserId());
    }

    public boolean maybeLaunchFbLinkingUpsell(PeopleTabletPanelApp peopleTabletPanelApp, PeopleUserActionHandler peopleUserActionHandler) {
        FBLinkedStatus fBLinkedStatus = peopleTabletPanelApp.getFBLinkedStatus();
        if (fBLinkedStatus == FBLinkedStatus.ERROR || fBLinkedStatus == FBLinkedStatus.NOT_READY) {
            peopleUserActionHandler.onError("FB link status is errored or not ready");
            return true;
        }
        if (fBLinkedStatus != FBLinkedStatus.LINKED) {
            PeopleUserActionType type = getType();
            switch (type.ordinal()) {
                case 0:
                case 1:
                case 4:
                case 5:
                case 10:
                    FBLinkingUpsellLauncher.showUpsell(peopleTabletPanelApp, type.name(), getButtonId().getTelemetryButtonId());
                    return true;
            }
        }
        return false;
    }
}
