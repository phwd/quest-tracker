package org.chromium.chrome.browser.offlinepages;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClientId {

    /* renamed from: a  reason: collision with root package name */
    public String f10716a;
    public String b;

    public ClientId(String str, String str2) {
        this.f10716a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClientId)) {
            return false;
        }
        ClientId clientId = (ClientId) obj;
        if (!clientId.f10716a.equals(this.f10716a) || !clientId.b.equals(this.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f10716a + ":" + this.b).hashCode();
    }
}
