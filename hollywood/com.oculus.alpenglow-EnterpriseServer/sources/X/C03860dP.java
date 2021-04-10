package X;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(29)
@RestrictTo({AnonymousClass02D.LIBRARY_GROUP})
/* renamed from: X.0dP  reason: invalid class name and case insensitive filesystem */
public final class C03860dP extends AnonymousClass08Y {
    @Override // X.AnonymousClass08Y
    @Nullable
    public final Typeface A02(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font build = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // X.AnonymousClass08Y
    public final Typeface A03(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // X.AnonymousClass08Y
    public final AnonymousClass09U A04(AnonymousClass09U[] r3, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // X.AnonymousClass08Y
    @Nullable
    public final Typeface A06(Context context, C03900dT r12, Resources resources, int i) {
        AnonymousClass084[] r6 = r12.A00;
        int length = r6.length;
        int i2 = 0;
        FontFamily.Builder builder = null;
        int i3 = 0;
        while (true) {
            int i4 = 1;
            if (i3 >= length) {
                break;
            }
            AnonymousClass084 r7 = r6[i3];
            try {
                Font.Builder weight = new Font.Builder(resources, r7.A00).setWeight(r7.A02);
                if (!r7.A04) {
                    i4 = 0;
                }
                Font build = weight.setSlant(i4).setTtcIndex(r7.A01).setFontVariationSettings(r7.A03).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(build);
                } else {
                    builder.addFont(build);
                }
            } catch (IOException unused) {
            }
            i3++;
        }
        if (builder == null) {
            return null;
        }
        int i5 = 400;
        if ((i & 1) != 0) {
            i5 = 700;
        }
        if ((i & 2) != 0) {
            i2 = 1;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i5, i2)).build();
    }

    @Override // X.AnonymousClass08Y
    @Nullable
    public final Typeface A05(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull AnonymousClass09U[] r14, int i) {
        ContentResolver contentResolver = context.getContentResolver();
        int length = r14.length;
        int i2 = 0;
        FontFamily.Builder builder = null;
        int i3 = 0;
        while (true) {
            int i4 = 1;
            if (i3 >= length) {
                break;
            }
            AnonymousClass09U r8 = r14[i3];
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(r8.A03, "r", cancellationSignal);
                if (openFileDescriptor != null) {
                    try {
                        Font.Builder weight = new Font.Builder(openFileDescriptor).setWeight(r8.A02);
                        if (!r8.A04) {
                            i4 = 0;
                        }
                        Font build = weight.setSlant(i4).setTtcIndex(r8.A01).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(build);
                        } else {
                            builder.addFont(build);
                        }
                        openFileDescriptor.close();
                    } catch (Throwable unused) {
                    }
                }
            } catch (IOException unused2) {
            }
            i3++;
        }
        if (builder == null) {
            return null;
        }
        int i5 = 400;
        if ((i & 1) != 0) {
            i5 = 700;
        }
        if ((i & 2) != 0) {
            i2 = 1;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i5, i2)).build();
        throw th;
    }
}
