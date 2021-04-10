package defpackage;

import android.content.Context;
import android.widget.ArrayAdapter;

/* renamed from: c4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1949c4 extends ArrayAdapter {
    public C1949c4(Context context, int i, int i2, CharSequence[] charSequenceArr) {
        super(context, i, i2, charSequenceArr);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return true;
    }
}
