package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* renamed from: uv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5165uv0 extends FadingEdgeScrollView {
    public final /* synthetic */ Context K;
    public final /* synthetic */ View L;
    public final /* synthetic */ C5505wv0 M;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5165uv0(C5505wv0 wv0, Context context, AttributeSet attributeSet, Context context2, View view) {
        super(context, null);
        this.M = wv0;
        this.K = context2;
        this.L = view;
        if (wv0.b != null) {
            setBackground(AbstractC5544x8.a(getContext(), R.drawable.f34390_resource_name_obfuscated_RES_2131231479));
            setPadding(0, 0, 0, (int) context2.getResources().getDimension(R.dimen.f23590_resource_name_obfuscated_RES_2131165978));
        }
    }

    public void onMeasure(int i, int i2) {
        View view = this.L;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(view != null ? (view.getHeight() * 90) / 100 : 0, Integer.MIN_VALUE));
    }
}
