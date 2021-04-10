package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.download.DownloadBroadcastManager;

/* renamed from: tI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4894tI {
    public static Intent a(Context context, String str, C0788My my, boolean z) {
        ComponentName componentName = new ComponentName(context.getPackageName(), DownloadBroadcastManager.class.getName());
        Intent intent = new Intent(str);
        intent.setComponent(componentName);
        String str2 = "";
        intent.putExtra("org.chromium.chrome.browser.download.DownloadContentId_Id", my != null ? my.b : str2);
        if (my != null) {
            str2 = my.f8514a;
        }
        intent.putExtra("org.chromium.chrome.browser.download.DownloadContentId_Namespace", str2);
        intent.putExtra("org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", z);
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02d1  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x02d8  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0309  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x028a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.app.Notification b(android.content.Context r16, int r17, defpackage.NI r18, int r19) {
        /*
        // Method dump skipped, instructions count: 1075
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC4894tI.b(android.content.Context, int, NI, int):android.app.Notification");
    }

    public static void c(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
