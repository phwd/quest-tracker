package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: Hm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0461Hm0 extends AbstractC5750yK0 implements AbstractC3391kY {
    public final Context I;

    /* renamed from: J  reason: collision with root package name */
    public J80 f8180J;
    public RecyclerView K;

    public C0461Hm0(Context context) {
        this.I = context;
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.f8180J.a();
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        if (!((AbstractC5417wO) ((C5757yO) this.f8180J).f11677a.get(i)).a()) {
            return -1;
        }
        C5757yO yOVar = (C5757yO) this.f8180J;
        C5587xO xOVar = (C5587xO) yOVar.f11677a.get(i);
        if (xOVar.d == 0) {
            int i2 = yOVar.d + 1;
            yOVar.d = i2;
            xOVar.d = i2;
        }
        return xOVar.d;
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        C0400Gm0 gm0 = (C0400Gm0) xk0;
        if (((AbstractC5417wO) ((C5757yO) this.f8180J).f11677a.get(i)).a()) {
            J80 j80 = this.f8180J;
            View view = gm0.G;
            Objects.requireNonNull((C5757yO) j80);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: android.widget.FrameLayout */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        TextView textView;
        C5587xO xOVar;
        if (i >= 0) {
            C5757yO yOVar = (C5757yO) this.f8180J;
            int i2 = 0;
            while (true) {
                if (i2 >= yOVar.f11677a.size()) {
                    xOVar = null;
                    break;
                }
                AbstractC5417wO wOVar = (AbstractC5417wO) yOVar.f11677a.get(i2);
                if (wOVar.a()) {
                    xOVar = (C5587xO) wOVar;
                    if (xOVar.d == i) {
                        break;
                    }
                }
                i2++;
            }
            Objects.requireNonNull(xOVar);
            Context context = viewGroup.getContext();
            if (xOVar.b == null) {
                xOVar.b = LayoutInflater.from(context).inflate(xOVar.c, viewGroup, false);
            }
            AbstractC2417ep1.k(xOVar.b);
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(new ViewGroup.LayoutParams(-1, -2)));
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f23080_resource_name_obfuscated_RES_2131165927);
            frameLayout.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
            frameLayout.setClipToPadding(false);
            frameLayout.setClipChildren(false);
            frameLayout.addView(xOVar.b);
            textView = frameLayout;
        } else {
            TextView textView2 = new TextView(ContextUtils.getApplicationContext());
            textView2.setText("Unable to render external view");
            textView = textView2;
        }
        return new C0400Gm0(textView);
    }
}
