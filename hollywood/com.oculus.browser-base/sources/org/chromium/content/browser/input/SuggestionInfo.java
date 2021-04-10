package org.chromium.content.browser.input;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SuggestionInfo {

    /* renamed from: a  reason: collision with root package name */
    public final int f10928a;
    public final int b;
    public final String c;
    public final String d;
    public final String e;

    public SuggestionInfo(int i, int i2, String str, String str2, String str3) {
        this.f10928a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public static SuggestionInfo[] createArray(int i) {
        return new SuggestionInfo[i];
    }

    public static void createSuggestionInfoAndPutInArray(SuggestionInfo[] suggestionInfoArr, int i, int i2, int i3, String str, String str2, String str3) {
        suggestionInfoArr[i] = new SuggestionInfo(i2, i3, str, str2, str3);
    }
}
