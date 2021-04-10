package X;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import com.oculus.horizon.R;
import java.util.ArrayList;

/* renamed from: X.03h  reason: invalid class name */
public class AnonymousClass03h {
    public Bitmap A00;
    public AnonymousClass03p A01;
    public String A02;
    public ArrayList<AnonymousClass03d> A03 = new ArrayList<>();
    public boolean A04;
    public int A05 = 0;
    public int A06;
    public int A07;
    public int A08;
    public Notification A09;
    public PendingIntent A0A;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public Context A0B;
    public Bundle A0C;
    public CharSequence A0D;
    public CharSequence A0E;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ArrayList<AnonymousClass03d> A0F = new ArrayList<>();
    @Deprecated
    public ArrayList<String> A0G;
    public boolean A0H = true;

    public static CharSequence A00(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 5120) {
            return charSequence;
        }
        return charSequence.subSequence(0, 5120);
    }

    public final Notification A01() {
        return new C07430sa(this).A00();
    }

    public final void A02() {
        Notification notification = this.A09;
        notification.flags = 16 | notification.flags;
    }

    public final void A03(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.A0F.add(new AnonymousClass03d(IconCompat.A07(null, "", i), charSequence, pendingIntent, new Bundle(), null, true, 0, true, false));
    }

    public final void A04(Bitmap bitmap) {
        if (bitmap != null && Build.VERSION.SDK_INT < 27) {
            Resources resources = this.A0B.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            if (bitmap.getWidth() > dimensionPixelSize || bitmap.getHeight() > dimensionPixelSize2) {
                double min = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
                bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * min), (int) Math.ceil(((double) bitmap.getHeight()) * min), true);
            }
        }
        this.A00 = bitmap;
    }

    public final void A05(Bundle bundle) {
        Bundle bundle2 = this.A0C;
        if (bundle2 == null) {
            this.A0C = new Bundle(bundle);
        } else {
            bundle2.putAll(bundle);
        }
    }

    public final void A06(AnonymousClass03p r2) {
        if (this.A01 != r2) {
            this.A01 = r2;
            r2.setBuilder(this);
        }
    }

    public final void A07(CharSequence charSequence) {
        this.A09.tickerText = A00(charSequence);
    }

    public AnonymousClass03h(@NonNull Context context, @NonNull String str) {
        Notification notification = new Notification();
        this.A09 = notification;
        this.A0B = context;
        this.A02 = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.A06 = 0;
        this.A0G = new ArrayList<>();
        this.A04 = true;
    }
}
