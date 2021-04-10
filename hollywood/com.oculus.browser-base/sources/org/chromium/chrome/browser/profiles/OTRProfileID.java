package org.chromium.chrome.browser.profiles;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OTRProfileID {

    /* renamed from: a  reason: collision with root package name */
    public final String f10751a;

    public OTRProfileID(String str) {
        this.f10751a = str;
    }

    public static OTRProfileID a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        OTRProfileID oTRProfileID = new OTRProfileID(str.substring(13, str.length() - 1));
        Profile b = Profile.b();
        if (N.MQioXkwA(b.b, b, oTRProfileID)) {
            return oTRProfileID;
        }
        throw new IllegalStateException("The OTR profile should exist for otr profile id.");
    }

    public static String serialize(OTRProfileID oTRProfileID) {
        if (oTRProfileID == null) {
            return null;
        }
        return oTRProfileID.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OTRProfileID)) {
            return false;
        }
        return this.f10751a.equals(((OTRProfileID) obj).f10751a);
    }

    public final String getProfileID() {
        return this.f10751a;
    }

    public int hashCode() {
        return this.f10751a.hashCode();
    }

    public String toString() {
        return String.format("OTRProfileID{%s}", this.f10751a);
    }
}
