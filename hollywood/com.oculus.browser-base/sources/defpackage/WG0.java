package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.ProfileDownloader;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* renamed from: WG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WG0 extends AbstractC5779yZ implements XG0 {
    public final Context F;
    public final int G;
    public final UG0 H;
    public final Drawable I;

    /* renamed from: J  reason: collision with root package name */
    public final C1322Vq0 f9138J;
    public final Map K;
    public final ZG0 L;
    public final IdentityManager M;

    public WG0(Context context, int i) {
        this(context, i, null, AccountManagerFacadeProvider.getInstance().k());
    }

    public static WG0 V(Context context, int i) {
        Drawable drawable;
        ZG0 k = AccountManagerFacadeProvider.getInstance().k();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f26670_resource_name_obfuscated_RES_2131166286);
        Resources resources = context.getResources();
        if (i == 0) {
            drawable = null;
        } else {
            drawable = AbstractC5544x8.a(context, i);
        }
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.f16740_resource_name_obfuscated_RES_2131165293);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.f16750_resource_name_obfuscated_RES_2131165294);
        return new WG0(context, dimensionPixelSize, new UG0(drawable, resources.getDimensionPixelSize(R.dimen.f16760_resource_name_obfuscated_RES_2131165295), new Point(dimensionPixelOffset, dimensionPixelOffset2), resources.getDimensionPixelSize(R.dimen.f16730_resource_name_obfuscated_RES_2131165292)), k);
    }

    @Override // defpackage.AbstractC5779yZ
    public void S(AccountInfo accountInfo) {
        String email = accountInfo.getEmail();
        C3522lG lGVar = (C3522lG) this.K.get(email);
        if (lGVar != null && lGVar.b == this.I) {
            a0(new C3522lG(email, X(accountInfo.d, email), lGVar.c, lGVar.d));
        }
    }

    public void U(VG0 vg0) {
        Object obj = ThreadUtils.f10596a;
        if (this.f9138J.isEmpty()) {
            ZG0 zg0 = this.L;
            if (zg0 != null) {
                zg0.a(this);
                AccountManagerFacadeProvider.getInstance().g(new TG0(this));
            } else {
                ProfileDownloader.a().c.b(this);
            }
            this.M.b.b(this);
        }
        this.f9138J.b(vg0);
    }

    public C3522lG W(String str) {
        C3522lG lGVar = (C3522lG) this.K.get(str);
        return lGVar == null ? new C3522lG(str, this.I, null, null) : lGVar;
    }

    public final Drawable X(Bitmap bitmap, String str) {
        Drawable drawable;
        if (bitmap == null) {
            AccountInfo accountInfo = (AccountInfo) N.MRQQkZGI(this.M.f10894a, str);
            bitmap = accountInfo != null ? accountInfo.d : null;
        }
        if (bitmap != null) {
            drawable = AbstractC0256Ee.a(this.F.getResources(), bitmap, this.G);
        } else {
            drawable = this.I;
        }
        UG0 ug0 = this.H;
        if (ug0 == null || ug0.f9018a == null) {
            return drawable;
        }
        int i = ug0.b;
        Bitmap createBitmap = Bitmap.createBitmap(Math.max(ug0.c.x + i, this.G), Math.max(this.H.c.y + i, this.G), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int i2 = this.G;
        drawable.setBounds(0, 0, i2, i2);
        drawable.draw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        int i3 = i / 2;
        UG0 ug02 = this.H;
        Point point = ug02.c;
        canvas.drawCircle((float) (point.x + i3), (float) (point.y + i3), (float) (i3 + ug02.d), paint);
        UG0 ug03 = this.H;
        Drawable drawable2 = ug03.f9018a;
        Point point2 = ug03.c;
        int i4 = point2.x;
        int i5 = point2.y;
        drawable2.setBounds(i4, i5, i4 + i, i + i5);
        drawable2.draw(canvas);
        return new BitmapDrawable(this.F.getResources(), createBitmap);
    }

    public void Y(VG0 vg0) {
        Object obj = ThreadUtils.f10596a;
        this.f9138J.c(vg0);
        if (this.f9138J.isEmpty()) {
            ZG0 zg0 = this.L;
            if (zg0 != null) {
                zg0.c(this);
            } else {
                ProfileDownloader.a().c.c(this);
            }
            this.M.b.c(this);
        }
    }

    public void Z(List list) {
        Object obj = ThreadUtils.f10596a;
        if (this.L == null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!this.K.containsKey(str)) {
                    ProfileDownloader a2 = ProfileDownloader.a();
                    int i = this.G;
                    Objects.requireNonNull(a2);
                    Object obj2 = ThreadUtils.f10596a;
                    Profile b = Profile.b();
                    if (!C5949zZ.a().b(b).b()) {
                        if (C1638aH0.F == null) {
                            C1638aH0.F = new C1638aH0();
                            C5949zZ.a().b(Profile.b()).a(C1638aH0.F);
                        }
                        C1638aH0 ah0 = C1638aH0.F;
                        ah0.G.add(b);
                        ah0.H.add(str);
                        ah0.I.add(Integer.valueOf(i));
                    } else {
                        N.MSj9Fi5N(b, str, i);
                    }
                }
            }
        }
    }

    public final void a0(C3522lG lGVar) {
        this.K.put(lGVar.f10337a, lGVar);
        String str = lGVar.f10337a;
        Iterator it = this.f9138J.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((VG0) uq0.next()).D(str);
            } else {
                return;
            }
        }
    }

    public WG0(Context context, int i, UG0 ug0, ZG0 zg0) {
        this.f9138J = new C1322Vq0();
        this.K = new HashMap();
        this.F = context;
        this.G = i;
        this.H = ug0;
        Drawable a2 = AbstractC5544x8.a(context, R.drawable.f33530_resource_name_obfuscated_RES_2131231393);
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        a2.setBounds(0, 0, i, i);
        a2.draw(canvas);
        this.I = new BitmapDrawable(context.getResources(), createBitmap);
        this.L = zg0;
        this.M = C5949zZ.a().c(Profile.b());
    }
}
