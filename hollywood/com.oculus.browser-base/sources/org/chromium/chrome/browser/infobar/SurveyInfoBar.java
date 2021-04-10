package org.chromium.chrome.browser.infobar;

import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SurveyInfoBar extends InfoBar {
    public final SurveyInfoBarDelegate I;

    public SurveyInfoBar(String str, boolean z, int i, SurveyInfoBarDelegate surveyInfoBarDelegate) {
        super(i, 0, null, null);
        this.I = surveyInfoBarDelegate;
    }

    public static SurveyInfoBar create(String str, boolean z, int i, SurveyInfoBarDelegate surveyInfoBarDelegate) {
        return new SurveyInfoBar(str, z, i, surveyInfoBarDelegate);
    }
}
