package org.chromium.chrome.browser.compositor;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.resources.ResourceManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LayerTitleCache implements AbstractC0757Mi1 {

    /* renamed from: a  reason: collision with root package name */
    public static int f10634a = 1;
    public final Context b;
    public AbstractC0124Ca1 c;
    public final SparseArray d = new SparseArray();
    public final int e;
    public long f;
    public ResourceManager g;
    public C3542lO h;
    public C3713mO i;
    public C0697Li1 j;
    public C0697Li1 k;

    public LayerTitleCache(Context context, ResourceManager resourceManager) {
        this.b = context;
        this.g = resourceManager;
        Resources resources = context.getResources();
        this.f = N.MTbG5FQ5(this, resources.getDimensionPixelOffset(R.dimen.f16840_resource_name_obfuscated_RES_2131165303), resources.getDimensionPixelSize(R.dimen.f25790_resource_name_obfuscated_RES_2131166198), resources.getDimensionPixelSize(R.dimen.f25780_resource_name_obfuscated_RES_2131166197), R.drawable.f35030_resource_name_obfuscated_RES_2131231543, R.drawable.f35040_resource_name_obfuscated_RES_2131231544, this.g);
        this.e = resources.getDimensionPixelSize(R.dimen.f17470_resource_name_obfuscated_RES_2131165366);
        this.j = new C0697Li1(context, false);
        this.k = new C0697Li1(context, true);
        this.i = new C3713mO();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:54:? */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:58:? */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.graphics.Paint] */
    /* JADX WARN: Type inference failed for: r11v2, types: [android.graphics.Canvas] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ba A[Catch:{ OutOfMemoryError -> 0x0124, InflateException -> 0x011c }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c7 A[Catch:{ OutOfMemoryError -> 0x0124, InflateException -> 0x011c }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e1 A[Catch:{ OutOfMemoryError -> 0x0124, InflateException -> 0x011c }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0141 A[Catch:{ OutOfMemoryError -> 0x018a, InflateException -> 0x0184 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01ce  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(org.chromium.chrome.browser.tab.Tab r23, java.lang.String r24) {
        /*
        // Method dump skipped, instructions count: 511
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.LayerTitleCache.a(org.chromium.chrome.browser.tab.Tab, java.lang.String):java.lang.String");
    }

    public void b(int i2) {
        C2130d70 d70 = (C2130d70) this.d.get(i2);
        if (d70 != null) {
            d70.a();
            this.d.remove(i2);
            long j2 = this.f;
            if (j2 != 0) {
                N.Mn77UQtR(j2, this, i2, -1, -1, false, false);
            }
        }
    }

    public final void buildUpdatedTitle(int i2) {
        Tab o;
        AbstractC0124Ca1 ca1 = this.c;
        if (ca1 != null && (o = ((AbstractC0246Ea1) ca1).o(i2)) != null) {
            a(o, "");
        }
    }

    public final long getNativePtr() {
        return this.f;
    }
}
