package com.oculus.panelapp.social.actions;

import android.content.Context;
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

    public abstract ClickEventButtonId getButtonId(Source source);

    public abstract SocialUserActionType getType();

    public abstract boolean isRelevant();

    /* renamed from: preformAction */
    public abstract void lambda$execute$0$SocialUserAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler);

    public abstract UpsellLoggingParameters upsellParams();

    public void execute(SocialPanelApp socialPanelApp, Context context, @Nullable final SocialUserActionActionHandler socialUserActionActionHandler, @Nullable Source source) {
        ClickEventButtonId buttonId = getButtonId(source);
        if (buttonId != null) {
            socialPanelApp.logButtonClick(buttonId);
        }
        AnonymousClass1 r3 = new SocialUserActionActionHandler() {
            /* class com.oculus.panelapp.social.actions.SocialUserAction.AnonymousClass1 */

            @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
            public void onError() {
                SocialUserActionActionHandler socialUserActionActionHandler = socialUserActionActionHandler;
                if (socialUserActionActionHandler != null) {
                    socialUserActionActionHandler.onError();
                }
            }

            @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
            public void onSuccess() {
                SocialUserActionActionHandler socialUserActionActionHandler = socialUserActionActionHandler;
                if (socialUserActionActionHandler != null) {
                    socialUserActionActionHandler.onSuccess();
                }
            }
        };
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        if (upsellParams() != null) {
            SocialBundleUtils.PerformActionIfLinked(socialPanelApp, acquireSocialViewModel.mLinkedAccountsInfo, new Runnable(socialPanelApp, context, r3) {
                /* class com.oculus.panelapp.social.actions.$$Lambda$SocialUserAction$aNyM014mRHul8wibakPelRUDmQ2 */
                public final /* synthetic */ SocialPanelApp f$1;
                public final /* synthetic */ Context f$2;
                public final /* synthetic */ SocialUserActionActionHandler f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    SocialUserAction.this.lambda$execute$0$SocialUserAction(this.f$1, this.f$2, this.f$3);
                }
            }, upsellParams());
        } else {
            lambda$execute$0$SocialUserAction(socialPanelApp, context, r3);
        }
    }
}
