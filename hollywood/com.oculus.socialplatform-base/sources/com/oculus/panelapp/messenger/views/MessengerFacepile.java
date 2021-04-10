package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.MessengerFacepileBinding;

public class MessengerFacepile extends FrameLayout {
    public static final String TAG = "MessengerFacepile";
    public MessengerFacepileBinding mBinding;

    public void setFacepileBorderBackground(Drawable drawable) {
        this.mBinding.facepileLeftIconBorder.setBackground(drawable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        if (r6.getParticipantProfilePictureUrlsForThreadPicture() == null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setIcon(com.oculus.panelapp.messenger.MessengerPanelApp r5, com.oculus.messengervr.interfaces.MessengerThread r6) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.MessengerFacepile.setIcon(com.oculus.panelapp.messenger.MessengerPanelApp, com.oculus.messengervr.interfaces.MessengerThread):void");
    }

    public MessengerFacepile(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = MessengerFacepileBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void destroy(MessengerPanelApp messengerPanelApp) {
        messengerPanelApp.getImageHandler().unloadView(this.mBinding.facepileLeftIcon);
        messengerPanelApp.getImageHandler().unloadView(this.mBinding.facepileRightIcon);
    }

    public void setHovered(boolean z) {
        super.setHovered(z);
        this.mBinding.facepileLeftIconBorder.setHovered(z);
    }
}
