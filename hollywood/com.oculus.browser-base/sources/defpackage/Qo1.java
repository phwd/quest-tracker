package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: Qo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Qo1 extends To1 {
    @Override // defpackage.To1
    public Typeface a(Context context, FR fr, Resources resources, int i) {
        try {
            GR[] grArr = fr.f8016a;
            int length = grArr.length;
            int i2 = 0;
            FontFamily.Builder builder = null;
            int i3 = 0;
            while (true) {
                int i4 = 1;
                if (i3 >= length) {
                    break;
                }
                GR gr = grArr[i3];
                try {
                    Font.Builder weight = new Font.Builder(resources, gr.f).setWeight(gr.b);
                    if (!gr.c) {
                        i4 = 0;
                    }
                    Font build = weight.setSlant(i4).setTtcIndex(gr.e).setFontVariationSettings(gr.d).build();
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
            int i5 = (i & 1) != 0 ? 700 : 400;
            if ((i & 2) != 0) {
                i2 = 1;
            }
            return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i5, i2)).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // defpackage.To1
    public Typeface b(Context context, CancellationSignal cancellationSignal, PR[] prArr, int i) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int length = prArr.length;
            int i2 = 0;
            FontFamily.Builder builder = null;
            int i3 = 0;
            while (true) {
                int i4 = 1;
                if (i3 >= length) {
                    break;
                }
                PR pr = prArr[i3];
                try {
                    ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(pr.f8691a, "r", cancellationSignal);
                    if (openFileDescriptor != null) {
                        try {
                            Font.Builder weight = new Font.Builder(openFileDescriptor).setWeight(pr.c);
                            if (!pr.d) {
                                i4 = 0;
                            }
                            Font build = weight.setSlant(i4).setTtcIndex(pr.b).build();
                            if (builder == null) {
                                builder = new FontFamily.Builder(build);
                            } else {
                                builder.addFont(build);
                            }
                        } catch (Throwable th) {
                            AbstractC0754Mh1.f8495a.a(th, th);
                        }
                    } else if (openFileDescriptor == null) {
                        i3++;
                    }
                    openFileDescriptor.close();
                } catch (IOException unused) {
                }
                i3++;
            }
            if (builder == null) {
                return null;
            }
            int i5 = (i & 1) != 0 ? 700 : 400;
            if ((i & 2) != 0) {
                i2 = 1;
            }
            return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i5, i2)).build();
            throw th;
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // defpackage.To1
    public Typeface c(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // defpackage.To1
    public Typeface d(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font build = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }
}
