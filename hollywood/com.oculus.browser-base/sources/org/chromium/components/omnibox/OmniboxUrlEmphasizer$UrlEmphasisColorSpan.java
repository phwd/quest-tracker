package org.chromium.components.omnibox;

import android.text.style.ForegroundColorSpan;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OmniboxUrlEmphasizer$UrlEmphasisColorSpan extends ForegroundColorSpan implements AbstractC0168Cs0 {
    public int F;

    public OmniboxUrlEmphasizer$UrlEmphasisColorSpan(int i) {
        super(i);
        this.F = i;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof OmniboxUrlEmphasizer$UrlEmphasisColorSpan) && ((OmniboxUrlEmphasizer$UrlEmphasisColorSpan) obj).F == this.F) {
            return true;
        }
        return false;
    }

    public String toString() {
        return OmniboxUrlEmphasizer$UrlEmphasisColorSpan.class.getName() + ", color: " + this.F;
    }
}
