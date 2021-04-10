package defpackage;

import android.text.TextUtils;
import android.webkit.URLUtil;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* renamed from: kz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3470kz {
    public static String a(ContextMenuParams contextMenuParams) {
        if (!TextUtils.isEmpty(contextMenuParams.e)) {
            return contextMenuParams.e;
        }
        if (!TextUtils.isEmpty(contextMenuParams.d)) {
            return contextMenuParams.d;
        }
        if (contextMenuParams.j || contextMenuParams.k || contextMenuParams.b()) {
            return URLUtil.guessFileName(contextMenuParams.g.h(), null, null);
        }
        return "";
    }
}
