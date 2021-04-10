package com.oculus.common.quickpromotion;

import com.oculus.ocui.OCEventHandler;
import com.oculus.tablet.logging.ClickEventButtonId;

public interface QuickPromotionEventHandler extends OCEventHandler {
    void logButtonClick(ClickEventButtonId clickEventButtonId);
}
