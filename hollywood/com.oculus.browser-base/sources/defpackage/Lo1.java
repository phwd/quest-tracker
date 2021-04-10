package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: Lo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Lo1 {

    /* renamed from: a  reason: collision with root package name */
    public static final To1 f8441a;
    public static final C4595rb0 b = new C4595rb0(16);

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f8441a = new Qo1();
        } else if (i >= 28) {
            f8441a = new Po1();
        } else if (i >= 26) {
            f8441a = new Oo1();
        } else {
            Method method = No1.d;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                f8441a = new No1();
            } else {
                f8441a = new Mo1();
            }
        }
    }

    public static Typeface a(Context context, ER er, Resources resources, int i, int i2, AbstractC5414wM0 wm0, Handler handler, boolean z) {
        Typeface typeface;
        KR kr;
        if (er instanceof HR) {
            HR hr = (HR) er;
            boolean z2 = true;
            if (!z ? wm0 != null : hr.c != 0) {
                z2 = false;
            }
            int i3 = z ? hr.b : -1;
            DR dr = hr.f8158a;
            C4595rb0 rb0 = RR.f8830a;
            String str = dr.f + "-" + i2;
            typeface = (Typeface) RR.f8830a.b(str);
            if (typeface != null) {
                if (wm0 != null) {
                    wm0.d(typeface);
                }
            } else if (!z2 || i3 != -1) {
                JR jr = new JR(context, dr, i2, str);
                typeface = null;
                if (z2) {
                    try {
                        typeface = ((QR) RR.b.b(jr, i3)).f8760a;
                    } catch (InterruptedException unused) {
                    }
                } else {
                    if (wm0 == null) {
                        kr = null;
                    } else {
                        kr = new KR(wm0, handler);
                    }
                    synchronized (RR.c) {
                        BW0 bw0 = RR.d;
                        ArrayList arrayList = (ArrayList) bw0.getOrDefault(str, null);
                        if (arrayList == null) {
                            if (kr != null) {
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(kr);
                                bw0.put(str, arrayList2);
                            }
                            C5766yS0 ys0 = RR.b;
                            MR mr = new MR(str);
                            Objects.requireNonNull(ys0);
                            ys0.a(new RunnableC5256vS0(ys0, jr, new Handler(), mr));
                        } else if (kr != null) {
                            arrayList.add(kr);
                        }
                    }
                }
            } else {
                QR b2 = RR.b(context, dr, i2);
                if (wm0 != null) {
                    int i4 = b2.b;
                    if (i4 == 0) {
                        wm0.b(b2.f8760a, handler);
                    } else {
                        wm0.a(i4, handler);
                    }
                }
                typeface = b2.f8760a;
            }
        } else {
            typeface = f8441a.a(context, (FR) er, resources, i2);
            if (wm0 != null) {
                if (typeface != null) {
                    wm0.b(typeface, handler);
                } else {
                    wm0.a(-3, handler);
                }
            }
        }
        if (typeface != null) {
            b.c(c(resources, i, i2), typeface);
        }
        return typeface;
    }

    public static Typeface b(Context context, Resources resources, int i, String str, int i2) {
        Typeface d = f8441a.d(context, resources, i, str, i2);
        if (d != null) {
            b.c(c(resources, i, i2), d);
        }
        return d;
    }

    public static String c(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
