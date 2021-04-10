package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import javax.annotation.Nullable;

public abstract class SocialUserAction {

    public enum Source {
        USER_CARD_MENU,
        USER_ROW_MENU
    }

    /* access modifiers changed from: package-private */
    public abstract ClickEventButtonId getButtonId(Source source);

    public abstract SocialUserActionType getType();

    public abstract boolean isRelevant();

    /* access modifiers changed from: package-private */
    /* renamed from: preformAction */
    public abstract void lambda$execute$7$SocialUserAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler);

    /* access modifiers changed from: package-private */
    public abstract UpsellLoggingParameters upsellParams();

    public void execute(SocialPanelApp socialPanelApp, Context context, @Nullable final SocialUserActionActionHandler socialUserActionActionHandler, @Nullable Source source) {
        ClickEventButtonId buttonId = getButtonId(source);
        if (buttonId != null) {
            SocialLogger.log(buttonId, socialPanelApp);
        }
        AnonymousClass1 r5 = new SocialUserActionActionHandler() {
            /* class com.oculus.panelapp.social.actions.SocialUserAction.AnonymousClass1 */

            @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
            public void onSuccess() {
                SocialUserActionActionHandler socialUserActionActionHandler = socialUserActionActionHandler;
                if (socialUserActionActionHandler != null) {
                    socialUserActionActionHandler.onSuccess();
                }
            }

            @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
            public void onError() {
                SocialUserActionActionHandler socialUserActionActionHandler = socialUserActionActionHandler;
                if (socialUserActionActionHandler != null) {
                    socialUserActionActionHandler.onError();
                }
            }
        };
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        if (upsellParams() != null) {
            SocialBundleUtils.PerformActionIfLinked(socialPanelApp, acquireSocialViewModel.getLinkedAccountsInfo(), new Runnable(socialPanelApp, context, r5) {
                /* class com.oculus.panelapp.social.actions.$$Lambda$SocialUserAction$R4_NlPgT4VzFHpnpNr1nci2dVVI */
                private final /* synthetic */ SocialPanelApp f$1;
                private final /* synthetic */ Context f$2;
                private final /* synthetic */ SocialUserActionActionHandler f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    SocialUserAction.this.lambda$execute$7$SocialUserAction(this.f$1, this.f$2, this.f$3);
                }
            }, upsellParams());
        } else {
            lambda$execute$7$SocialUserAction(socialPanelApp, context, r5);
        }
    }
}
