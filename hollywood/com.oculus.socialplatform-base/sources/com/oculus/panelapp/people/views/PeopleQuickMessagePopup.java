package com.oculus.panelapp.people.views;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.FBLinkedStatus;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.panelapp.people.databinding.PeopleQuickMessagePopupBinding;
import com.oculus.panelapp.people.util.FBLinkingUpsellLauncher;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;

public class PeopleQuickMessagePopup extends PopupWindow {
    public static final long SUCCESS_TOAST_LENGTH_MS = 1500;
    public static final String TAG = LoggingUtil.tag(PeopleQuickMessagePopup.class);
    public PeopleQuickMessagePopupBinding mBinding;
    public Context mContext;
    public Handler mHandler;
    public PeopleTabletPanelApp mPanelApp;
    public List<OCButton> mQuickMessagesList;
    public String mUserId;
    public PeopleQuickMessagePopupViewModel mViewModel;

    private void bindQuickMessageButtons() {
        for (OCButton oCButton : this.mQuickMessagesList) {
            oCButton.setEnabled(true);
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener(oCButton) {
                /* class com.oculus.panelapp.people.views.$$Lambda$PeopleQuickMessagePopup$KFJRdQN6WWeunYq4VwOFuuAjh882 */
                public final /* synthetic */ OCButton f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PeopleQuickMessagePopup.this.lambda$bindQuickMessageButtons$1$PeopleQuickMessagePopup(this.f$1, view);
                }
            });
        }
    }

    private void clearQuickMessageButtons() {
        for (OCButton oCButton : this.mQuickMessagesList) {
            oCButton.mEventHandler = null;
            oCButton.setOnClickListener(null);
        }
    }

    private void initializeOpenChatCTA() {
        OCButton oCButton = this.mBinding.ctaButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleQuickMessagePopup$mu34COpC4H6qRNtWuXSRgZAlf8o2 */

            public final void onClick(View view) {
                PeopleQuickMessagePopup.this.lambda$initializeOpenChatCTA$0$PeopleQuickMessagePopup(view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void renderSuccess() {
        this.mViewModel.setIsSuccess(true);
        this.mHandler.postDelayed(new Runnable() {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleQuickMessagePopup$jvyhJTUvO7c6MtPEkWFPdKLehs2 */

            public final void run() {
                PeopleQuickMessagePopup.this.lambda$renderSuccess$2$PeopleQuickMessagePopup();
            }
        }, SUCCESS_TOAST_LENGTH_MS);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabledStateForAllQuickMessageButtons(boolean z) {
        for (OCButton oCButton : this.mQuickMessagesList) {
            oCButton.setEnabled(z);
        }
    }

    public void destroy() {
        OCButton oCButton = this.mBinding.ctaButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        clearQuickMessageButtons();
    }

    public /* synthetic */ void lambda$bindQuickMessageButtons$1$PeopleQuickMessagePopup(OCButton oCButton, View view) {
        PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_QUICK_MESSAGE_POPUP_QUICK_REPLY_SEND;
        String str = this.mUserId;
        peopleTabletPanelApp.logButtonClick(clickEventButtonId, LoggingConstants.TARGET_USERID, str, "thread_id", str);
        if (!maybeLaunchFbLinkingUpsell(this.mPanelApp, clickEventButtonId)) {
            this.mViewModel.setIsSuccess(false);
            this.mHandler.removeCallbacksAndMessages(null);
            this.mViewModel.setIsLoading(true);
            setEnabledStateForAllQuickMessageButtons(false);
            final long currentTimeMillis = System.currentTimeMillis();
            this.mPanelApp.sendMessage(this.mUserId, oCButton.getText().toString(), new PeopleUserActionHandler() {
                /* class com.oculus.panelapp.people.views.PeopleQuickMessagePopup.AnonymousClass1 */

                @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
                public void onError(String str) {
                    String l = Long.toString(System.currentTimeMillis() - currentTimeMillis);
                    Context context = PeopleQuickMessagePopup.this.mContext;
                    PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_send_quick_message_error", context.getResources().getString(R.string.people_tablet_send_quick_message_failed), PeopleQuickMessagePopup.TAG);
                    PeopleQuickMessagePopup.this.mViewModel.setIsLoading(false);
                    PeopleQuickMessagePopup.this.setEnabledStateForAllQuickMessageButtons(true);
                    PeopleTabletPanelApp peopleTabletPanelApp = PeopleQuickMessagePopup.this.mPanelApp;
                    SocialLogger logger = peopleTabletPanelApp.getLogger();
                    ActionId actionId = ActionId.MESSAGE_SEND;
                    ClickEventButtonId clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_QUICK_MESSAGE_POPUP_QUICK_REPLY_SEND;
                    SurfaceType loggingSurfaceType = peopleTabletPanelApp.getCurrentViewType().getLoggingSurfaceType();
                    String str2 = PeopleQuickMessagePopup.this.mUserId;
                    logger.logActionFailure(actionId, clickEventButtonId, loggingSurfaceType, "Failed to send quick reply from people tab", LoggingConstants.TARGET_USERID, str2, "thread_id", str2, LoggingConstants.TIME_TO_COMPLETE_MS, l);
                }

                @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
                public void onSuccess() {
                    String l = Long.toString(System.currentTimeMillis() - currentTimeMillis);
                    PeopleQuickMessagePopup.this.mViewModel.setIsLoading(false);
                    PeopleQuickMessagePopup.this.setEnabledStateForAllQuickMessageButtons(true);
                    PeopleQuickMessagePopup.this.renderSuccess();
                    PeopleTabletPanelApp peopleTabletPanelApp = PeopleQuickMessagePopup.this.mPanelApp;
                    SocialLogger logger = peopleTabletPanelApp.getLogger();
                    ActionId actionId = ActionId.MESSAGE_SEND;
                    ClickEventButtonId clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_QUICK_MESSAGE_POPUP_QUICK_REPLY_SEND;
                    SurfaceType loggingSurfaceType = peopleTabletPanelApp.getCurrentViewType().getLoggingSurfaceType();
                    String str = PeopleQuickMessagePopup.this.mUserId;
                    logger.logActionSuccess(actionId, clickEventButtonId, loggingSurfaceType, LoggingConstants.TARGET_USERID, str, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, l);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeOpenChatCTA$0$PeopleQuickMessagePopup(View view) {
        SystemUXRoute systemUXRoute;
        PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_QUICK_MESSAGE_POPUP_OPEN_CHAT;
        String str = this.mUserId;
        peopleTabletPanelApp.logButtonClick(clickEventButtonId, LoggingConstants.TARGET_USERID, str, "thread_id", str);
        if (!maybeLaunchFbLinkingUpsell(this.mPanelApp, clickEventButtonId)) {
            Uri.Builder appendPath = new Uri.Builder().encodedPath("").appendPath("mailbox").appendPath(this.mPanelApp.getViewerID()).appendPath("thread").appendPath(this.mUserId);
            PeopleTabletPanelApp peopleTabletPanelApp2 = this.mPanelApp;
            PeopleTabletType peopleTabletType = peopleTabletPanelApp2.getPeopleTabletType();
            if (peopleTabletType == PeopleTabletType.OCULUS) {
                systemUXRoute = SystemUXRoute.AUI_CHATS;
            } else if (peopleTabletType == PeopleTabletType.FACEBOOK) {
                systemUXRoute = SystemUXRoute.AUI_MESSENGER;
            } else {
                return;
            }
            peopleTabletPanelApp2.actionNavigate(systemUXRoute, appendPath.toString());
        }
    }

    public /* synthetic */ void lambda$renderSuccess$2$PeopleQuickMessagePopup() {
        this.mViewModel.setIsSuccess(false);
    }

    public void toggle(String str, View view) {
        String str2 = this.mUserId;
        if (str2 == null || !str2.equals(str)) {
            this.mUserId = str;
            this.mViewModel.setIsLoading(false);
            this.mViewModel.setIsSuccess(false);
            this.mHandler.removeCallbacksAndMessages(null);
            setEnabledStateForAllQuickMessageButtons(true);
        }
        showAsDropDown(view, this.mContext.getResources().getDimensionPixelOffset(R.dimen.people_tablet_quick_message_popup_x_offset), this.mContext.getResources().getDimensionPixelOffset(R.dimen.people_tablet_quick_message_popup_y_offset));
    }

    public PeopleQuickMessagePopup(Context context, PeopleTabletPanelApp peopleTabletPanelApp) {
        PeopleQuickMessagePopupBinding inflate = PeopleQuickMessagePopupBinding.inflate(LayoutInflater.from(context), null, false);
        this.mBinding = inflate;
        this.mPanelApp = peopleTabletPanelApp;
        PeopleQuickMessagePopupViewModel peopleQuickMessagePopupViewModel = new PeopleQuickMessagePopupViewModel();
        this.mViewModel = peopleQuickMessagePopupViewModel;
        inflate.setViewModel(peopleQuickMessagePopupViewModel);
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        setContentView(this.mBinding.mRoot);
        setAnimationStyle(0);
        setHeight(-2);
        setWidth((int) context.getResources().getDimension(R.dimen.people_tablet_quick_message_popup_width));
        initializeOpenChatCTA();
        PeopleQuickMessagePopupBinding peopleQuickMessagePopupBinding = this.mBinding;
        this.mQuickMessagesList = ImmutableList.A09(peopleQuickMessagePopupBinding.quickMessage1, peopleQuickMessagePopupBinding.quickMessage2, peopleQuickMessagePopupBinding.quickMessage3, peopleQuickMessagePopupBinding.quickMessage4);
        bindQuickMessageButtons();
    }

    public boolean maybeLaunchFbLinkingUpsell(PeopleTabletPanelApp peopleTabletPanelApp, ClickEventButtonId clickEventButtonId) {
        FBLinkedStatus fBLinkedStatus = peopleTabletPanelApp.getFBLinkedStatus();
        if (!(fBLinkedStatus == FBLinkedStatus.ERROR || fBLinkedStatus == FBLinkedStatus.NOT_READY)) {
            if (fBLinkedStatus == FBLinkedStatus.LINKED) {
                return false;
            }
            FBLinkingUpsellLauncher.showUpsell(peopleTabletPanelApp, PeopleUserActionType.CHAT.name(), clickEventButtonId.getTelemetryButtonId());
        }
        return true;
    }
}
