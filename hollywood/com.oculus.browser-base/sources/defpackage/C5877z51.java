package defpackage;

import java.util.Comparator;

/* renamed from: z51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5877z51 implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        C51 c51 = (C51) obj;
        C51 c512 = (C51) obj2;
        int i = c51.c;
        int i2 = c512.c;
        return i == i2 ? (int) (c51.d - c512.d) : i - i2;
    }
}
