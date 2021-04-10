package X;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import com.oculus.socialplatform.R;
import java.util.ArrayList;

/* renamed from: X.03m  reason: invalid class name */
public class AnonymousClass03m {
    public Bitmap A00;
    public String A01;
    public ArrayList<AnonymousClass03i> A02 = new ArrayList<>();
    public boolean A03;
    public int A04 = 0;
    public int A05;
    public Notification A06;
    public PendingIntent A07;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public Context A08;
    public Bundle A09;
    public AnonymousClass03u A0A;
    public CharSequence A0B;
    public CharSequence A0C;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ArrayList<AnonymousClass03i> A0D = new ArrayList<>();
    @Deprecated
    public ArrayList<String> A0E;
    public boolean A0F = true;

    public static CharSequence A00(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 5120) {
            return charSequence;
        }
        return charSequence.subSequence(0, 5120);
    }

    public final Notification A01() {
        RemoteViews remoteViews;
        AnonymousClass0wL r5 = new AnonymousClass0wL(this);
        AnonymousClass03m r4 = r5.A01;
        AnonymousClass03u r3 = r4.A0A;
        if (r3 != null) {
            r3.apply(r5);
            remoteViews = r3.makeContentView(r5);
        } else {
            remoteViews = null;
        }
        Notification build = r5.A00.build();
        if (remoteViews != null) {
            build.contentView = remoteViews;
        }
        if (r3 != null) {
            RemoteViews makeBigContentView = r3.makeBigContentView(r5);
            if (makeBigContentView != null) {
                build.bigContentView = makeBigContentView;
            }
            RemoteViews makeHeadsUpContentView = r4.A0A.makeHeadsUpContentView(r5);
            if (makeHeadsUpContentView != null) {
                build.headsUpContentView = makeHeadsUpContentView;
            }
            Bundle bundle = build.extras;
            if (bundle != null) {
                r3.addCompatExtras(bundle);
            }
        }
        return build;
    }

    public final void A02(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.A0D.add(new AnonymousClass03i(IconCompat.A06(null, "", i), charSequence, pendingIntent, new Bundle(), null, true, 0, true, false));
    }

    public final void A03(Bitmap bitmap) {
        if (bitmap != null && Build.VERSION.SDK_INT < 27) {
            Resources resources = this.A08.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
            if (bitmap.getWidth() > dimensionPixelSize || bitmap.getHeight() > dimensionPixelSize2) {
                double min = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
                bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * min), (int) Math.ceil(((double) bitmap.getHeight()) * min), true);
            }
        }
        this.A00 = bitmap;
    }

    public AnonymousClass03m(@NonNull Context context, @NonNull String str) {
        Notification notification = new Notification();
        this.A06 = notification;
        this.A08 = context;
        this.A01 = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.A05 = 0;
        this.A0E = new ArrayList<>();
        this.A03 = true;
    }
}
