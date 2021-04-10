package org.chromium.content.browser.picker;

import android.text.TextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DateTimeSuggestion {

    /* renamed from: a  reason: collision with root package name */
    public final double f10930a;
    public final String b;
    public final String c;

    public DateTimeSuggestion(double d, String str, String str2) {
        this.f10930a = d;
        this.b = str;
        this.c = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DateTimeSuggestion)) {
            return false;
        }
        DateTimeSuggestion dateTimeSuggestion = (DateTimeSuggestion) obj;
        if (this.f10930a != dateTimeSuggestion.f10930a || !TextUtils.equals(this.b, dateTimeSuggestion.b) || !TextUtils.equals(this.c, dateTimeSuggestion.c)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        return this.c.hashCode() + ((hashCode + ((1147 + ((int) this.f10930a)) * 37)) * 37);
    }
}
