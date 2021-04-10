package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Oz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0913Oz0 extends AbstractC4277pj {

    /* renamed from: a  reason: collision with root package name */
    public final View f8658a;
    public final FrameLayout b;
    public final View c;
    public final WebContents d;
    public final int e;
    public Runnable f;

    public C0913Oz0(Context context, WebContents webContents, View view, View view2) {
        this.d = webContents;
        this.f8658a = view;
        this.c = view2;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f25040_resource_name_obfuscated_RES_2131166123);
        this.e = dimensionPixelSize;
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.f40440_resource_name_obfuscated_RES_2131624353, (ViewGroup) null);
        this.b = frameLayout;
        frameLayout.setPadding(0, dimensionPixelSize, 0, 0);
        frameLayout.addView(view2, 0);
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.b;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return 0.9f;
    }

    @Override // defpackage.AbstractC4277pj
    public float i() {
        return 0.5f;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        return -2;
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f58230_resource_name_obfuscated_RES_2131953140;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f58240_resource_name_obfuscated_RES_2131953141;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f58250_resource_name_obfuscated_RES_2131953142;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f58260_resource_name_obfuscated_RES_2131953143;
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return this.f8658a;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        WebContents webContents = this.d;
        if (webContents == null) {
            return 0;
        }
        return ((WebContentsImpl) webContents).M.e();
    }

    @Override // defpackage.AbstractC4277pj
    public boolean r() {
        this.f.run();
        return true;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean s() {
        return true;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }
}
