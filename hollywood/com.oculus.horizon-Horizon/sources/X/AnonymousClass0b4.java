package X;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"HexColorValueUsage"})
/* renamed from: X.0b4  reason: invalid class name */
public class AnonymousClass0b4 {
    public static final Integer A0F = AnonymousClass007.A00;
    public int A00 = 0;
    public long A01 = 0;
    @Nullable
    public ClipData A02 = null;
    @Nullable
    public ComponentName A03 = null;
    @Nullable
    public Intent A04 = null;
    @Nullable
    public Rect A05 = null;
    @Nullable
    public Uri A06 = null;
    @Nullable
    public Bundle A07 = null;
    @Nullable
    public AnonymousClass0b1 A08;
    @Nullable
    public String A09 = null;
    @Nullable
    public String A0A = null;
    public boolean A0B = false;
    public Integer A0C = A0F;
    @Nullable
    public String A0D = null;
    public final Set<String> A0E = new HashSet();

    public static int A00(AnonymousClass0b4 r5, int i) {
        if ((8 & r5.A01) != 0) {
            return i & -67108865;
        }
        return i | 67108864;
    }

    @VisibleForTesting
    public static final Intent A01(AnonymousClass0b4 r9, Context context) {
        String packageName;
        Intent intent = new Intent();
        intent.setComponent(r9.A03);
        intent.setFlags(r9.A00);
        if (r9.A0B) {
            intent.setComponent(new ComponentName(context, "com.facebook.invalid_class.f4c3b00c"));
            packageName = context.getPackageName();
        } else {
            intent.setAction(r9.A09);
            intent.setDataAndType(r9.A06, r9.A0A);
            intent.setSourceBounds(r9.A05);
            intent.setSelector(r9.A04);
            intent.setClipData(r9.A02);
            for (String str : r9.A0E) {
                intent.addCategory(str);
            }
            if (r9.A07 != null) {
                intent.setExtrasClassLoader(context.getClassLoader());
                intent.putExtras(r9.A07);
            }
            long j = r9.A01;
            if ((1 & j) != 0) {
                if ((4 & j) == 0) {
                    String str2 = r9.A0D;
                    if (str2 == null) {
                        str2 = context.getPackageName();
                        r9.A0D = str2;
                    }
                    intent.setPackage(str2);
                    if ((2 & r9.A01) != 0) {
                        if (!r9.A0D.equals(context.getPackageName())) {
                            r9.A02("SecurePendingIntent is configured to allow only implicit intent going to the same app, but detected intent for a different app.");
                        }
                    }
                    if (!(intent.getAction() == null || intent.getAction().startsWith("android"))) {
                        return intent;
                    }
                    if (!(intent.getCategories() == null || intent.getCategories().isEmpty())) {
                        return intent;
                    }
                    r9.A02("SecurePendingIntent is configured to allow implicit intent with either customized action or categories");
                    return intent;
                }
                return intent;
            } else if (intent.getComponent() != null) {
                packageName = intent.getComponent().getPackageName();
            } else {
                throw new SecurityException("Must generate PendingIntent based on an explicit intent.");
            }
        }
        intent.setPackage(packageName);
        return intent;
    }

    private void A02(String str) {
        if (this.A0C != AnonymousClass007.A01) {
            AnonymousClass0b1 r0 = this.A08;
            if (r0 != null) {
                r0.report(str);
                return;
            }
            throw new IllegalArgumentException("Please set reporter for SecurePendingIntent library");
        }
        throw new SecurityException(str);
    }

    @SuppressLint({"BadMethodUse-android.app.PendingIntent.getBroadcast"})
    public final PendingIntent A03(Context context, int i) {
        return PendingIntent.getBroadcast(context, i, A01(this, context), A00(this, 134217728));
    }

    public final void A04(Intent intent, @Nullable ClassLoader classLoader) {
        this.A03 = intent.getComponent();
        this.A09 = intent.getAction();
        this.A06 = intent.getData();
        this.A0A = intent.getType();
        this.A05 = intent.getSourceBounds();
        this.A04 = intent.getSelector();
        this.A02 = intent.getClipData();
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            this.A0E.addAll(categories);
        }
        this.A00 = intent.getFlags();
        if (intent.getExtras() != null) {
            if (classLoader != null) {
                intent.setExtrasClassLoader(classLoader);
            }
            Bundle extras = intent.getExtras();
            Bundle bundle = this.A07;
            if (bundle == null) {
                bundle = new Bundle();
                this.A07 = bundle;
            }
            if (classLoader != null) {
                bundle.setClassLoader(classLoader);
            }
            this.A07.putAll(extras);
        }
    }
}
