package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkFolderRow extends AbstractC0816Ni {
    public BookmarkFolderRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.VR0
    public void f() {
        throw null;
    }

    @Override // defpackage.TR0
    public ColorStateList k() {
        Context context = getContext();
        int d = AbstractC1243Ui.d(0);
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        return context.getColorStateList(d);
    }

    @Override // defpackage.TR0, defpackage.VR0, defpackage.AbstractC0816Ni
    public void onFinishInflate() {
        super.onFinishInflate();
        this.a0 = AbstractC1243Ui.c(getContext(), 0);
        j(false);
    }
}
