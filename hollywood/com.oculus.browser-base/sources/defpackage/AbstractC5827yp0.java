package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Icon;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: yp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5827yp0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11701a;
    public final int b;
    public final KN0 c;
    public CharSequence d;
    public CharSequence e;
    public CharSequence f;
    public String g;
    public CharSequence h;
    public Bitmap i;
    public int j;
    public Bitmap k;
    public Bitmap l;
    public CB0 m;
    public CB0 n;
    public List o = new ArrayList(2);
    public C5657xp0 p;
    public int q;
    public long[] r;
    public long s;
    public boolean t;
    public Bitmap u;

    public AbstractC5827yp0(Resources resources) {
        this.f11701a = resources.getDimensionPixelSize(17104901);
        this.b = resources.getDimensionPixelSize(17104902);
        int dimensionPixelSize = resources.getDimensionPixelSize(17104901);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(17104902);
        this.c = new KN0(dimensionPixelSize, dimensionPixelSize2, Math.min(dimensionPixelSize, dimensionPixelSize2) / 2, -6908266, resources.getDisplayMetrics().density * 28.0f);
    }

    public static void a(AbstractC3615lq0 lq0, C5657xp0 xp0) {
        Notification.Action.Builder builder;
        Bitmap bitmap = xp0.b;
        if (bitmap != null) {
            builder = new Notification.Action.Builder(Icon.createWithBitmap(bitmap), xp0.c, xp0.d);
        } else {
            builder = new Notification.Action.Builder(xp0.f11637a, xp0.c, xp0.d);
        }
        if (xp0.e == 1) {
            builder.addRemoteInput(new RemoteInput.Builder("key_text_reply").setLabel(xp0.g).build());
        }
        if (xp0.f == -1) {
            lq0.y(builder.build());
        } else {
            lq0.i(builder.build(), 134217728, xp0.f);
        }
    }

    public static void c(Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
        new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, paint);
    }

    public static CharSequence i(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public static void j(AbstractC3615lq0 lq0, CharSequence charSequence) {
        if (charSequence != null) {
            lq0.r("Web:" + ((Object) charSequence));
        }
    }

    public final void b(Bitmap bitmap, CharSequence charSequence, PendingIntent pendingIntent, int i2, String str) {
        if (this.o.size() != 2) {
            if (bitmap != null) {
                c(bitmap);
            }
            this.o.add(new C5657xp0(bitmap, i(charSequence), pendingIntent, i2, str));
            return;
        }
        throw new IllegalStateException("Cannot add more than 2 actions.");
    }

    public abstract C3444kq0 d(C0832Np0 np0);

    public Notification e(Context context) {
        AbstractC3615lq0 A = AbstractC3786mq0.a(false, this.g).F(context.getString(R.string.f56170_resource_name_obfuscated_RES_2131952934)).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017);
        A.g(this.f);
        Bitmap bitmap = this.k;
        if (bitmap != null) {
            A.p(Icon.createWithBitmap(bitmap.copy(bitmap.getConfig(), true)));
        }
        return A.c();
    }

    public Bitmap f() {
        Bitmap bitmap = this.u;
        CharSequence charSequence = this.f;
        if (bitmap == null || bitmap.getWidth() == 0) {
            if (charSequence != null) {
                return this.c.c(charSequence.toString(), true);
            }
            return null;
        } else if (bitmap.getWidth() > this.f11701a || bitmap.getHeight() > this.b) {
            return Bitmap.createScaledBitmap(bitmap, this.f11701a, this.b, false);
        } else {
            return bitmap;
        }
    }

    public boolean g() {
        return this.l != null;
    }

    public boolean h() {
        return this.k != null;
    }

    public AbstractC5827yp0 k(Bitmap bitmap) {
        Bitmap bitmap2;
        if (bitmap != null) {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
            c(bitmap2);
        } else {
            bitmap2 = null;
        }
        this.l = bitmap2;
        return this;
    }

    public AbstractC5827yp0 l(Bitmap bitmap) {
        Bitmap bitmap2;
        if (bitmap != null) {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
            c(bitmap2);
        } else {
            bitmap2 = null;
        }
        this.k = bitmap2;
        return this;
    }
}
