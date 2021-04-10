package org.chromium.chrome.browser.usage_stats;

import J.N;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UsageStatsBridge {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f10795a;
    public long b;

    public UsageStatsBridge(Profile profile, Jr1 jr1) {
        this.b = N.MZTYueAb(this, profile);
        this.f10795a = jr1;
    }

    public static void createEventListAndRunCallback(byte[][] bArr, Callback callback) {
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            try {
                arrayList.add((C4324py1) AbstractC2360eV.k(C4324py1.e, bArr2));
            } catch (L30 unused) {
            }
        }
        callback.onResult(arrayList);
    }

    public static void createMapAndRunCallback(String[] strArr, String[] strArr2, Callback callback) {
        HashMap hashMap = new HashMap(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            hashMap.put(strArr[i], strArr2[i]);
        }
        callback.onResult(hashMap);
    }

    public final void onAllHistoryDeleted() {
        Jr1 jr1 = this.f10795a;
        Objects.requireNonNull(jr1);
        Object obj = ThreadUtils.f10596a;
        AbstractC6004zr1.a(7);
        Objects.requireNonNull(jr1.i);
        C5232vH0.c(null);
        C3538lM lMVar = jr1.c;
        Objects.requireNonNull(lMVar);
        C5232vH0 vh0 = new C5232vH0();
        C5232vH0 vh02 = lMVar.b;
        C2684gM gMVar = new C2684gM(lMVar, vh0);
        C2855hM hMVar = new C2855hM();
        vh02.h(gMVar);
        vh02.a(hMVar);
        vh0.a(new Dr1(jr1));
    }

    public final void onHistoryDeletedForDomains(String[] strArr) {
        Jr1 jr1 = this.f10795a;
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        Objects.requireNonNull(jr1);
        Object obj = ThreadUtils.f10596a;
        AbstractC6004zr1.a(9);
        Objects.requireNonNull(jr1.i);
        C5232vH0.c(null);
        C3538lM lMVar = jr1.c;
        Objects.requireNonNull(lMVar);
        C5232vH0 vh0 = new C5232vH0();
        C5232vH0 vh02 = lMVar.b;
        C3367kM kMVar = new C3367kM(lMVar, arrayList, vh0);
        YL yl = new YL();
        vh02.h(kMVar);
        vh02.a(yl);
        vh0.a(new Fr1(jr1, arrayList));
    }

    public final void onHistoryDeletedInRange(long j, long j2) {
        Jr1 jr1 = this.f10795a;
        Objects.requireNonNull(jr1);
        Object obj = ThreadUtils.f10596a;
        AbstractC6004zr1.a(8);
        long min = Math.min(j2, System.currentTimeMillis());
        Objects.requireNonNull(jr1.i);
        C5232vH0.c(null);
        jr1.c.a(j, min).a(new Er1(jr1, j, j2));
    }
}
