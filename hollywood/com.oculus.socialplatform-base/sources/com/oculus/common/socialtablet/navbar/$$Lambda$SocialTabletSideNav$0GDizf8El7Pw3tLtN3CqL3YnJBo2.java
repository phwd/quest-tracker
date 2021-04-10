package com.oculus.common.socialtablet.navbar;

import com.oculus.common.socialtablet.util.EducationTooltipUtil;
import com.oculus.ocui.OCTooltip;

/* renamed from: com.oculus.common.socialtablet.navbar.-$$Lambda$SocialTabletSideNav$0GDizf8El7Pw3tLtN3CqL3YnJBo2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SocialTabletSideNav$0GDizf8El7Pw3tLtN3CqL3YnJBo2 implements OCTooltip.OCTooltipDismissHandler {
    public static final /* synthetic */ $$Lambda$SocialTabletSideNav$0GDizf8El7Pw3tLtN3CqL3YnJBo2 INSTANCE = new $$Lambda$SocialTabletSideNav$0GDizf8El7Pw3tLtN3CqL3YnJBo2();

    @Override // com.oculus.ocui.OCTooltip.OCTooltipDismissHandler
    public final void onDismissTooltip() {
        EducationTooltipUtil.setTooltipPrefKey(EducationTooltipUtil.EducationTooltipType.PROFILE_SWITCHER, true);
    }
}
