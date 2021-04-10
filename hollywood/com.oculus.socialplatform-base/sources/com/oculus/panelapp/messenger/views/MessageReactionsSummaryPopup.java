package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.MessageReactionsSummaryPopupBinding;
import com.oculus.socialplatform.R;
import java.util.Arrays;
import java.util.Optional;

public class MessageReactionsSummaryPopup extends PopupWindow {
    public static final String TAG = "MessageReactionsSummaryPopup";
    public final MessageReactionsSummaryPopupBinding mBinding;
    public MessengerPanelApp mPanelApp;
    public Resources mResources;
    public final MessengerReactionsSummaryViewModel mViewModel;

    private int getPopupViewHeight(int i) {
        if (i > 3) {
            return this.mResources.getDimensionPixelSize(R.dimen.messenger_reactions_summary_view_max_height);
        }
        return (this.mResources.getDimensionPixelSize(R.dimen.abc_floating_window_z) * i) + (this.mResources.getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material) << 1) + (this.mResources.getDimensionPixelSize(R.dimen.abc_action_bar_elevation_material) * (i - 1));
    }

    public void configure(Context context) {
        setContentView(this.mBinding.mRoot);
        setAnimationStyle(0);
        setHeight(-2);
        setWidth(this.mResources.getDimensionPixelSize(R.dimen.messenger_reactions_summary_view_width));
        setBackgroundDrawable(context.getDrawable(R.drawable.anytime_tablet_messenger_message_reactions_summary_popup));
    }

    public void show(View view, View view2, MessengerMessage messengerMessage) {
        if (this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_REACTIONS_READ_TOOLTIP)) {
            this.mPanelApp.getLogger().logImpression(SurfaceType.FB_MESSENGER_REACTIONS_SUMMARY, null);
            setTouchInterceptor(new View.OnTouchListener(view) {
                /* class com.oculus.panelapp.messenger.views.$$Lambda$MessageReactionsSummaryPopup$NXZ4QESpyipFIWeQPer4nuiAaIQ2 */
                public final /* synthetic */ View f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return MessageReactionsSummaryPopup.this.lambda$show$0$MessageReactionsSummaryPopup(this.f$1, view, motionEvent);
                }
            });
            Optional<MessengerReaction[]> reactions = messengerMessage.getReactions();
            if (reactions.isPresent() && reactions.get().length != 0) {
                this.mViewModel.setReactions(Arrays.asList(reactions.get()));
                positionAndShow(view, view2, reactions.get());
            }
        }
    }

    public MessageReactionsSummaryPopup(Context context, MessengerPanelApp messengerPanelApp) {
        super(context);
        this.mPanelApp = messengerPanelApp;
        this.mResources = context.getResources();
        this.mBinding = MessageReactionsSummaryPopupBinding.inflate(LayoutInflater.from(context), null, false);
        MessengerReactionsSummaryViewModel messengerReactionsSummaryViewModel = new MessengerReactionsSummaryViewModel(context, this.mPanelApp);
        this.mViewModel = messengerReactionsSummaryViewModel;
        this.mBinding.setViewModel(messengerReactionsSummaryViewModel);
        configure(context);
    }

    private void positionAndShow(View view, View view2, MessengerReaction[] messengerReactionArr) {
        View rootView = view.getRootView();
        int[] iArr = new int[2];
        rootView.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view.getLocationInWindow(iArr2);
        int[] iArr3 = new int[2];
        view2.getLocationInWindow(iArr3);
        int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.messenger_reactions_summary_view_width);
        int popupViewHeight = getPopupViewHeight(messengerReactionArr.length);
        int width = view.getWidth();
        int height = view.getHeight();
        int dimensionPixelSize2 = this.mResources.getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius) + this.mResources.getDimensionPixelSize(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter) + this.mResources.getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material);
        int dimensionPixelSize3 = this.mResources.getDimensionPixelSize(R.dimen.abc_action_bar_elevation_material);
        int round = Math.round((((float) dimensionPixelSize) / 2.0f) - (((float) width) / 2.0f));
        int i = (iArr2[0] - iArr3[0]) - dimensionPixelSize2;
        boolean z = true;
        boolean z2 = false;
        if (i > round) {
            z2 = true;
        }
        boolean z3 = false;
        if (((iArr[0] + rootView.getWidth()) - iArr2[0]) - width > round) {
            z3 = true;
        }
        int i2 = -dimensionPixelSize3;
        if (z2) {
            i2 = -round;
            if (!z3) {
                i2 = -((dimensionPixelSize - width) - dimensionPixelSize3);
            }
        }
        if (iArr2[1] - iArr[1] <= popupViewHeight) {
            z = false;
        }
        int dimensionPixelSize4 = this.mResources.getDimensionPixelSize(R.dimen.abc_control_corner_material);
        int i3 = -(height + popupViewHeight + dimensionPixelSize4);
        if (z) {
            dimensionPixelSize4 = i3;
        }
        showAsDropDown(view, i2, dimensionPixelSize4);
    }

    public /* synthetic */ boolean lambda$show$0$MessageReactionsSummaryPopup(View view, View view2, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        view.performClick();
        dismiss();
        return false;
    }
}
