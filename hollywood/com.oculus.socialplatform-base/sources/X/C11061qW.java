package X;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/* renamed from: X.1qW  reason: invalid class name and case insensitive filesystem */
public class C11061qW extends AbstractC001704s {
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ AnonymousClass1qE A02;
    public final /* synthetic */ WeakReference A03;

    public C11061qW(AnonymousClass1qE r1, int i, int i2, WeakReference weakReference) {
        this.A02 = r1;
        this.A00 = i;
        this.A01 = i2;
        this.A03 = weakReference;
    }

    @Override // X.AbstractC001704s
    public final void A02(@NonNull Typeface typeface) {
        int i;
        if (Build.VERSION.SDK_INT >= 28 && (i = this.A00) != -1) {
            boolean z = false;
            if ((this.A01 & 2) != 0) {
                z = true;
            }
            typeface = Typeface.create(typeface, i, z);
        }
        AnonymousClass1qE r2 = this.A02;
        WeakReference weakReference = this.A03;
        if (r2.A09) {
            r2.A01 = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, r2.A00);
            }
        }
    }
}
