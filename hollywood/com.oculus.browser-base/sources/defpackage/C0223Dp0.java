package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.C1725IconCompat;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: Dp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0223Dp0 {
    public String A;
    public boolean B;
    public Notification C;
    @Deprecated
    public ArrayList D;

    /* renamed from: a  reason: collision with root package name */
    public Context f7913a;
    public ArrayList b = new ArrayList();
    public ArrayList c = new ArrayList();
    public ArrayList d = new ArrayList();
    public CharSequence e;
    public CharSequence f;
    public PendingIntent g;
    public Bitmap h;
    public CharSequence i;
    public int j;
    public boolean k = true;
    public AbstractC0345Fp0 l;
    public CharSequence m;
    public int n;
    public int o;
    public boolean p;
    public String q;
    public boolean r;
    public boolean s = false;
    public String t;
    public Bundle u;
    public int v = 0;
    public int w = 0;
    public Notification x;
    public RemoteViews y;
    public RemoteViews z;

    public C0223Dp0(Context context, String str) {
        Notification notification = new Notification();
        this.C = notification;
        this.f7913a = context;
        this.A = str;
        notification.when = System.currentTimeMillis();
        this.C.audioStreamType = -1;
        this.j = 0;
        this.D = new ArrayList();
        this.B = true;
    }

    public static CharSequence c(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public C0223Dp0 a(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
        C1725IconCompat iconCompat;
        ArrayList arrayList = this.b;
        if (i2 == 0) {
            iconCompat = null;
        } else {
            iconCompat = C1725IconCompat.b(null, "", i2);
        }
        arrayList.add(new C0101Bp0(iconCompat, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true, false));
        return this;
    }

    public Notification b() {
        Notification notification;
        Bundle bundle;
        RemoteViews d2;
        C0406Gp0 gp0 = new C0406Gp0(this);
        AbstractC0345Fp0 fp0 = gp0.b.l;
        if (fp0 != null) {
            fp0.b(gp0);
        }
        RemoteViews e2 = fp0 != null ? fp0.e(gp0) : null;
        if (Build.VERSION.SDK_INT >= 26) {
            notification = gp0.f8113a.build();
        } else {
            notification = gp0.f8113a.build();
        }
        if (e2 != null) {
            notification.contentView = e2;
        } else {
            RemoteViews remoteViews = gp0.b.y;
            if (remoteViews != null) {
                notification.contentView = remoteViews;
            }
        }
        if (!(fp0 == null || (d2 = fp0.d(gp0)) == null)) {
            notification.bigContentView = d2;
        }
        if (fp0 != null) {
            Objects.requireNonNull(gp0.b.l);
        }
        if (!(fp0 == null || (bundle = notification.extras) == null)) {
            fp0.a(bundle);
        }
        return notification;
    }

    public C0223Dp0 d(CharSequence charSequence) {
        this.f = c(charSequence);
        return this;
    }

    public C0223Dp0 e(CharSequence charSequence) {
        this.e = c(charSequence);
        return this;
    }

    public final void f(int i2, boolean z2) {
        if (z2) {
            Notification notification = this.C;
            notification.flags = i2 | notification.flags;
            return;
        }
        Notification notification2 = this.C;
        notification2.flags = (~i2) & notification2.flags;
    }

    public C0223Dp0 g(Bitmap bitmap) {
        if (bitmap != null && Build.VERSION.SDK_INT < 27) {
            Resources resources = this.f7913a.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f17450_resource_name_obfuscated_RES_2131165364);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.f17440_resource_name_obfuscated_RES_2131165363);
            if (bitmap.getWidth() > dimensionPixelSize || bitmap.getHeight() > dimensionPixelSize2) {
                double min = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
                bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * min), (int) Math.ceil(((double) bitmap.getHeight()) * min), true);
            }
        }
        this.h = bitmap;
        return this;
    }
}
