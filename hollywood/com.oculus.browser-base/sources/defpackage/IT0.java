package defpackage;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: IT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IT0 {
    public static void a(WindowAndroid windowAndroid, Intent intent, Ky1 ky1) {
        if (ky1 != null) {
            windowAndroid.F0(intent, ky1, null);
        } else {
            ((Activity) windowAndroid.s0().get()).startActivity(intent);
        }
    }

    public static Intent b() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(524288);
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", "");
        intent.setType("text/plain");
        return intent;
    }

    public static Intent c(C2189dU0 du0) {
        ArrayList arrayList = du0.f;
        boolean z = arrayList != null;
        boolean z2 = z && arrayList.size() > 1;
        Intent intent = new Intent(z2 ? "android.intent.action.SEND_MULTIPLE" : "android.intent.action.SEND");
        intent.addFlags(319291392);
        intent.putExtra("org.chromium.chrome.extra.TASK_ID", ((Activity) du0.f9785a.s0().get()).getTaskId());
        Uri uri = du0.h;
        if (uri != null) {
            intent.addFlags(1);
            intent.setClipData(ClipData.newRawUri("", uri));
            intent.putExtra("share_screenshot_as_stream", uri);
        }
        if (du0.g != null) {
            intent.putExtra("android.intent.extra.SUBJECT", du0.b);
            intent.addFlags(134217728);
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.STREAM", du0.g);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setType("multipart/related");
        } else {
            if (!TextUtils.equals(du0.a(), du0.b)) {
                intent.putExtra("android.intent.extra.SUBJECT", du0.b);
            }
            intent.putExtra("android.intent.extra.TEXT", du0.a());
            if (z) {
                intent.setType(du0.e);
                intent.addFlags(1);
                if (z2) {
                    intent.putParcelableArrayListExtra("android.intent.extra.STREAM", du0.f);
                } else {
                    intent.putExtra("android.intent.extra.STREAM", (Parcelable) du0.f.get(0));
                }
            } else {
                intent.setType("text/plain");
            }
        }
        return intent;
    }

    public static Drawable d(ResolveInfo resolveInfo, PackageManager packageManager) {
        try {
            int iconResource = resolveInfo.getIconResource();
            if (iconResource != 0) {
                return AbstractC3153j7.c(packageManager.getResourcesForApplication(resolveInfo.activityInfo.packageName), iconResource);
            }
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
        }
        return resolveInfo.loadIcon(packageManager);
    }
}
