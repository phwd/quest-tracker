package X;

import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import bolts.WebViewAppLinkResolver;
import com.oculus.socialplatform.R;

/* renamed from: X.03u  reason: invalid class name */
public abstract class AnonymousClass03u {
    public CharSequence mBigContentTitle;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public AnonymousClass03m mBuilder;
    public CharSequence mSummaryText;
    public boolean mSummaryTextSet = false;

    public static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void addCompatExtras(Bundle bundle) {
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AbstractC000803d r1) {
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
        hideNormalContent(remoteViews);
        remoteViews.removeAllViews(R.id.notification_main_column);
        remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
        remoteViews.setViewVisibility(R.id.notification_main_column, 0);
        remoteViews.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AbstractC000803d r2) {
        return null;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AbstractC000803d r2) {
        return null;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AbstractC000803d r2) {
        return null;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void restoreFromCompatExtras(Bundle bundle) {
    }

    private int calculateTopPadding() {
        Resources resources = this.mBuilder.A08.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.abc_edit_text_inset_top_material);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
        float constrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
        return Math.round(((1.0f - constrain) * ((float) dimensionPixelSize)) + (constrain * ((float) dimensionPixelSize2)));
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews applyStandardTemplate(boolean z, int i, boolean z2) {
        long j;
        Resources resources = this.mBuilder.A08.getResources();
        RemoteViews remoteViews = new RemoteViews(this.mBuilder.A08.getPackageName(), i);
        AnonymousClass03m r2 = this.mBuilder;
        int i2 = 0;
        if (r2.A00 != null) {
            remoteViews.setViewVisibility(R.id.icon, 0);
            remoteViews.setImageViewBitmap(R.id.icon, this.mBuilder.A00);
            if (z && this.mBuilder.A06.icon != 0) {
                int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.abc_floating_window_z);
                int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding) << 1);
                AnonymousClass03m r22 = this.mBuilder;
                remoteViews.setImageViewBitmap(R.id.right_icon, createIconWithBackground(r22.A06.icon, dimensionPixelSize, dimensionPixelSize2, r22.A04));
                remoteViews.setViewVisibility(R.id.right_icon, 0);
            }
        } else if (z && r2.A06.icon != 0) {
            remoteViews.setViewVisibility(R.id.icon, 0);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.abc_list_item_height_material) - resources.getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius);
            int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.abc_dialog_padding_material);
            AnonymousClass03m r23 = this.mBuilder;
            remoteViews.setImageViewBitmap(R.id.icon, createIconWithBackground(r23.A06.icon, dimensionPixelSize3, dimensionPixelSize4, r23.A04));
        }
        CharSequence charSequence = this.mBuilder.A0C;
        if (charSequence != null) {
            remoteViews.setTextViewText(R.id.title, charSequence);
        }
        CharSequence charSequence2 = this.mBuilder.A0B;
        boolean z3 = false;
        if (charSequence2 != null) {
            remoteViews.setTextViewText(R.id.text, charSequence2);
            z3 = true;
        }
        boolean z4 = false;
        remoteViews.setViewVisibility(R.id.info, 8);
        AnonymousClass03m r1 = this.mBuilder;
        if (r1.A0F && r1.A06.when != 0) {
            remoteViews.setViewVisibility(R.id.time, 0);
            AnonymousClass03m r12 = this.mBuilder;
            if (r12.A0F) {
                j = r12.A06.when;
            } else {
                j = 0;
            }
            remoteViews.setLong(R.id.time, "setTime", j);
            z4 = true;
        }
        int i3 = 8;
        if (z4) {
            i3 = 0;
        }
        remoteViews.setViewVisibility(R.id.right_side, i3);
        if (!z3) {
            i2 = 8;
        }
        remoteViews.setViewVisibility(R.id.line3, i2);
        return remoteViews;
    }

    public Notification build() {
        AnonymousClass03m r0 = this.mBuilder;
        if (r0 != null) {
            return r0.A01();
        }
        return null;
    }

    public void setBuilder(AnonymousClass03m r2) {
        if (this.mBuilder != r2) {
            this.mBuilder = r2;
            if (r2 != null && r2.A0A != this) {
                r2.A0A = this;
                setBuilder(r2);
            }
        }
    }

    private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
        if (i4 == 0) {
            i4 = 0;
        }
        Bitmap createColoredBitmap = createColoredBitmap(R.drawable.notification_icon_background, i4, i2);
        Canvas canvas = new Canvas(createColoredBitmap);
        Drawable mutate = this.mBuilder.A08.getResources().getDrawable(i).mutate();
        mutate.setFilterBitmap(true);
        int i5 = (i2 - i3) >> 1;
        int i6 = i3 + i5;
        mutate.setBounds(i5, i5, i6, i6);
        mutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
        mutate.draw(canvas);
        return createColoredBitmap;
    }

    private void hideNormalContent(RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.title, 8);
        remoteViews.setViewVisibility(R.id.text2, 8);
        remoteViews.setViewVisibility(R.id.text, 8);
    }

    private Bitmap createColoredBitmap(int i, int i2, int i3) {
        Context context = this.mBuilder.A08;
        if (context != null) {
            return createColoredBitmap(IconCompat.A06(context.getResources(), context.getPackageName(), i), i2, i3);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private Bitmap createColoredBitmap(IconCompat iconCompat, int i, int i2) {
        int i3;
        Resources resources;
        Context context = this.mBuilder.A08;
        if (iconCompat.A02 == 2) {
            String str = (String) iconCompat.A06;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split("/", -1)[0];
                String str4 = str2.split("/", -1)[1];
                String str5 = str.split(":", -1)[0];
                if (WebViewAppLinkResolver.KEY_ANDROID.equals(str5)) {
                    resources = Resources.getSystem();
                } else {
                    PackageManager packageManager = context.getPackageManager();
                    resources = null;
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str5, 8192);
                        if (applicationInfo != null) {
                            resources = packageManager.getResourcesForApplication(applicationInfo);
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", str5), e);
                    }
                }
                int identifier = resources.getIdentifier(str4, str3, str5);
                if (iconCompat.A00 != identifier) {
                    iconCompat.A00 = identifier;
                }
            }
        }
        Drawable loadDrawable = IconCompat.A03(iconCompat, context).loadDrawable(context);
        if (i2 == 0) {
            i3 = loadDrawable.getIntrinsicWidth();
            i2 = loadDrawable.getIntrinsicHeight();
        } else {
            i3 = i2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_8888);
        loadDrawable.setBounds(0, 0, i3, i2);
        if (i != 0) {
            loadDrawable.mutate().setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        }
        loadDrawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public Bitmap createColoredBitmap(int i, int i2) {
        return createColoredBitmap(i, i2, 0);
    }

    public Bitmap createColoredBitmap(IconCompat iconCompat, int i) {
        return createColoredBitmap(iconCompat, i, 0);
    }
}
