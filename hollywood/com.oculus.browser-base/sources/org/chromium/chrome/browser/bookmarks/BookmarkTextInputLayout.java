package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkTextInputLayout extends TextInputLayout {
    public String l1;

    public BookmarkTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.s);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.l1 = context.getResources().getString(resourceId);
        }
        obtainStyledAttributes.recycle();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f9696J.addTextChangedListener(new C0877Oi(this));
    }
}
