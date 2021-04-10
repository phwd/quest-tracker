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
import bolts.WebViewAppLinkResolver;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"HexColorValueUsage"})
/* renamed from: X.0jZ  reason: invalid class name and case insensitive filesystem */
public class C02670jZ {
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
    public AbstractC02660jW A08;
    @Nullable
    public String A09 = null;
    @Nullable
    public String A0A = null;
    public boolean A0B = false;
    public Integer A0C = A0F;
    @Nullable
    public String A0D = null;
    public final Set<String> A0E = new HashSet();

    @SuppressLint({"BadMethodUse-android.app.PendingIntent.getActivity"})
    public final PendingIntent A02(Context context, int i) {
        Intent A002 = A00(context);
        int i2 = 67108864 | i;
        if ((8 & this.A01) != 0) {
            i2 = -67108865 & i;
        }
        return PendingIntent.getActivity(context, 0, A002, i2);
    }

    @VisibleForTesting
    private final Intent A00(Context context) {
        String packageName;
        Intent intent = new Intent();
        intent.setComponent(this.A03);
        intent.setFlags(this.A00);
        if (this.A0B) {
            intent.setComponent(new ComponentName(context, "com.facebook.invalid_class.f4c3b00c"));
            packageName = context.getPackageName();
        } else {
            intent.setAction(this.A09);
            intent.setDataAndType(this.A06, this.A0A);
            intent.setSourceBounds(this.A05);
            intent.setSelector(this.A04);
            intent.setClipData(this.A02);
            for (String str : this.A0E) {
                intent.addCategory(str);
            }
            if (this.A07 != null) {
                intent.setExtrasClassLoader(context.getClassLoader());
                intent.putExtras(this.A07);
            }
            long j = this.A01;
            if ((1 & j) != 0) {
                if ((4 & j) == 0) {
                    String str2 = this.A0D;
                    if (str2 == null) {
                        str2 = context.getPackageName();
                        this.A0D = str2;
                    }
                    intent.setPackage(str2);
                    if ((2 & this.A01) != 0) {
                        if (!this.A0D.equals(context.getPackageName())) {
                            A01("SecurePendingIntent is configured to allow only implicit intent going to the same app, but detected intent for a different app.");
                        }
                    }
                    if (!(intent.getAction() == null || intent.getAction().startsWith(WebViewAppLinkResolver.KEY_ANDROID))) {
                        return intent;
                    }
                    if (!(intent.getCategories() == null || intent.getCategories().isEmpty())) {
                        return intent;
                    }
                    A01("SecurePendingIntent is configured to allow implicit intent with either customized action or categories");
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

    private void A01(String str) {
        if (this.A0C != AnonymousClass007.A01) {
            AbstractC02660jW r0 = this.A08;
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
        Intent A002 = A00(context);
        int i2 = 201326592;
        if ((8 & this.A01) != 0) {
            i2 = 134217728;
        }
        return PendingIntent.getBroadcast(context, i, A002, i2);
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

    public C02670jZ() {
    }

    public C02670jZ(@Nullable ComponentName componentName) {
        this.A03 = componentName;
    }
}
