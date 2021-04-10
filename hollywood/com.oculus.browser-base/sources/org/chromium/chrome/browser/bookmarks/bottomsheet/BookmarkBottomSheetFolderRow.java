package org.chromium.chrome.browser.bookmarks.bottomsheet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkBottomSheetFolderRow extends AbstractC0816Ni {
    public Runnable d0;
    public int e0 = R.color.f11390_resource_name_obfuscated_RES_2131099829;

    public BookmarkBottomSheetFolderRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.VR0
    public void f() {
    }

    @Override // defpackage.TR0
    public ColorStateList k() {
        Context context = getContext();
        int i = this.e0;
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        return context.getColorStateList(i);
    }

    @Override // defpackage.VR0, defpackage.AbstractC0816Ni
    public void onClick(View view) {
        this.d0.run();
    }

    @Override // defpackage.TR0, defpackage.VR0, defpackage.AbstractC0816Ni
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b0.setVisibility(8);
    }

    @Override // defpackage.VR0, defpackage.AbstractC0816Ni
    public boolean onLongClick(View view) {
        return false;
    }
}
