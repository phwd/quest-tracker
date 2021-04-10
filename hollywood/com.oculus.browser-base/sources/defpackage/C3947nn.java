package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: nn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3947nn extends ArrayAdapter {
    public final /* synthetic */ C4118on F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3947nn(C4118on onVar, Context context, int i, List list) {
        super(context, i, list);
        this.F = onVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) super.getView(i, view, viewGroup);
        int i2 = this.F.G;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        textView.setPaddingRelative(i2, i2, i2 * 2, i2);
        return textView;
    }
}
