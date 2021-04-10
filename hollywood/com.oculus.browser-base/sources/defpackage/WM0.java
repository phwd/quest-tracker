package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.url.GURL;

/* renamed from: WM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WM0 implements View.OnClickListener {
    public UH0 F;
    public Context G;
    public GURL H;

    public WM0(Context context, UH0 uh0, int i, ContextMenuParams contextMenuParams, Profile profile, ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl) {
        this.G = context;
        this.H = contextMenuParams.a();
        this.F = uh0;
        uh0.m(XM0.d, this);
        boolean z = contextMenuParams.j;
        if (z) {
            int dimensionPixelSize = this.G.getResources().getDimensionPixelSize(R.dimen.f24730_resource_name_obfuscated_RES_2131166092);
            UM0 um0 = new UM0(this);
            long j = contextMenuNativeDelegateImpl.b;
            if (j != 0) {
                N.MrTfYLQo(j, contextMenuNativeDelegateImpl, contextMenuNativeDelegateImpl.f10639a, um0, dimensionPixelSize, dimensionPixelSize);
            }
        } else if (!z && !contextMenuParams.k) {
            new X60(profile).b(this.H, context.getResources().getDimensionPixelSize(R.dimen.f18030_resource_name_obfuscated_RES_2131165422), new VM0(this));
        } else if (contextMenuParams.k) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            Bitmap decodeResource = BitmapFactory.decodeResource(this.G.getResources(), R.drawable.f32910_resource_name_obfuscated_RES_2131231331, options);
            Canvas canvas = new Canvas(decodeResource);
            Paint paint = new Paint();
            paint.setColorFilter(new PorterDuffColorFilter(this.G.getResources().getColor(R.color.f11220_resource_name_obfuscated_RES_2131099812), PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(decodeResource, new Matrix(), paint);
            a(decodeResource, false);
        }
        if (N.MvtoTww2() && contextMenuParams.i) {
            this.F.l(XM0.h, i);
        }
    }

    public final void a(Bitmap bitmap, boolean z) {
        if (z) {
            this.F.m(XM0.f, bitmap);
            return;
        }
        this.F.j(XM0.g, true);
        this.F.m(XM0.f, bitmap);
    }

    public void onClick(View view) {
        int i = 1;
        AbstractC3100ip1.f10165a.a("ContextMenu.URLClicked", true);
        UH0 uh0 = this.F;
        SH0 sh0 = XM0.e;
        if (uh0.f(sh0) == Integer.MAX_VALUE) {
            this.F.l(sh0, TextUtils.isEmpty((CharSequence) this.F.g(XM0.f9204a)) ? 2 : 1);
            boolean isEmpty = TextUtils.isEmpty((CharSequence) this.F.g(XM0.c));
            UH0 uh02 = this.F;
            SH0 sh02 = XM0.b;
            if (isEmpty) {
                i = 2;
            }
            uh02.l(sh02, i);
            return;
        }
        this.F.l(sh0, Integer.MAX_VALUE);
        this.F.l(XM0.b, Integer.MAX_VALUE);
    }
}
