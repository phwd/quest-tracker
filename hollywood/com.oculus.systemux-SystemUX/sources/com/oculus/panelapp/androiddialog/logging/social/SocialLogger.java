package com.oculus.panelapp.androiddialog.logging.social;

import com.oculus.common.sociallogger.TabletType;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;

public class SocialLogger extends com.oculus.common.sociallogger.SocialLogger {
    public SocialLogger(AndroidDialogPanelApp androidDialogPanelApp) {
        super(androidDialogPanelApp.getLogger(), TabletType.DIALOG);
    }
}
