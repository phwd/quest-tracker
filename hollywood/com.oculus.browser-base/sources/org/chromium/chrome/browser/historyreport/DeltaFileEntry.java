package org.chromium.chrome.browser.historyreport;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DeltaFileEntry {

    /* renamed from: a  reason: collision with root package name */
    public final long f10681a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;

    public DeltaFileEntry(long j, String str, String str2, String str3, int i, String str4, String str5) {
        this.f10681a = j;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("DeltaFileEntry[");
        i.append(this.f10681a);
        i.append(", ");
        i.append(this.b);
        i.append(", ");
        i.append(this.c);
        i.append(", ");
        i.append(this.d);
        i.append(", ");
        return AbstractC2531fV.h(i, this.e, "]");
    }
}
