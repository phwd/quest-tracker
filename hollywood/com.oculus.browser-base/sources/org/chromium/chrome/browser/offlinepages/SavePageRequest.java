package org.chromium.chrome.browser.offlinepages;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SavePageRequest {
    public SavePageRequest(int i, long j, String str, ClientId clientId, C0593Jr0 jr0, int i2) {
    }

    public static SavePageRequest create(int i, long j, String str, String str2, String str3, String str4, int i2) {
        return new SavePageRequest(i, j, str, new ClientId(str2, str3), new C0593Jr0(str4), i2);
    }
}
