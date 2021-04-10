package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import com.oculus.browser.R;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkItemRow extends AbstractC0816Ni implements LargeIconBridge$LargeIconCallback {
    public KN0 d0;
    public final int e0 = getResources().getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);

    public BookmarkItemRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getResources().getDimension(R.dimen.f18030_resource_name_obfuscated_RES_2131165422);
        this.d0 = AbstractC4055oO.a(context.getResources());
    }

    @Override // defpackage.VR0
    public void f() {
        throw null;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        this.a0 = AbstractC4055oO.c(bitmap, null, i, this.d0, getResources(), this.e0);
        j(false);
    }
}
