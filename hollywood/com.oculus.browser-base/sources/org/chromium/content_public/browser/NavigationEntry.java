package org.chromium.content_public.browser;

import android.graphics.Bitmap;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationEntry {

    /* renamed from: a  reason: collision with root package name */
    public final int f10939a;
    public final GURL b;
    public final GURL c;
    public final GURL d;
    public final GURL e;
    public final String f;
    public Bitmap g;
    public long h;

    public NavigationEntry(int i, GURL gurl, GURL gurl2, GURL gurl3, GURL gurl4, String str, Bitmap bitmap, int i2, long j) {
        this.f10939a = i;
        this.b = gurl;
        this.d = gurl2;
        this.c = gurl3;
        this.e = gurl4;
        this.f = str;
        this.g = bitmap;
        this.h = j;
    }
}
