package org.chromium.chrome.browser.history;

import J.N;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowsingHistoryBridge {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10678a;
    public boolean b;

    public static void createHistoryItemAndAddToList(List list, String str, String str2, String str3, long j, long[] jArr, boolean z) {
        list.add(new C4415qX(str, str2, str3, j, jArr, z));
    }

    public void a() {
        if (this.f10678a) {
            this.b = true;
            return;
        }
        this.f10678a = true;
        this.b = false;
        N.MVl9wW5M(0, this);
    }

    public void hasOtherFormsOfBrowsingData(boolean z) {
    }

    public void onHistoryDeleted() {
    }

    public void onQueryHistoryComplete(List list, boolean z) {
    }

    public void onRemoveComplete() {
        this.f10678a = false;
        if (this.b) {
            a();
        }
    }

    public void onRemoveFailed() {
        this.f10678a = false;
        if (this.b) {
            a();
        }
    }
}
