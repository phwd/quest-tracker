package X;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;

/* renamed from: X.031  reason: invalid class name */
public final class AnonymousClass031 {
    public static Class<?> A00;
    public static Field A01;
    public static Field A02;
    public static Field A03;
    public static boolean A04;
    public static boolean A05;
    public static boolean A06;
    public static boolean A07;

    public static void A00(@NonNull Resources resources) {
        IllegalAccessException e;
        String str;
        if (Build.VERSION.SDK_INT < 28) {
            if (!A05) {
                try {
                    Field declaredField = Resources.class.getDeclaredField("mResourcesImpl");
                    A02 = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e2);
                }
                A05 = true;
            }
            Field field = A02;
            if (field != null) {
                try {
                    Object obj = field.get(resources);
                    if (obj != null) {
                        if (!A04) {
                            try {
                                Field declaredField2 = obj.getClass().getDeclaredField("mDrawableCache");
                                A01 = declaredField2;
                                declaredField2.setAccessible(true);
                            } catch (NoSuchFieldException e3) {
                                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e3);
                            }
                            A04 = true;
                        }
                        Field field2 = A01;
                        if (field2 != null) {
                            try {
                                Object obj2 = field2.get(obj);
                                if (obj2 != null) {
                                    if (!A06) {
                                        try {
                                            A00 = Class.forName("android.content.res.ThemedResourceCache");
                                        } catch (ClassNotFoundException e4) {
                                            Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e4);
                                        }
                                        A06 = true;
                                    }
                                    Class<?> cls = A00;
                                    if (cls != null) {
                                        if (!A07) {
                                            try {
                                                Field declaredField3 = cls.getDeclaredField("mUnthemedEntries");
                                                A03 = declaredField3;
                                                declaredField3.setAccessible(true);
                                            } catch (NoSuchFieldException e5) {
                                                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e5);
                                            }
                                            A07 = true;
                                        }
                                        Field field3 = A03;
                                        if (field3 != null) {
                                            try {
                                                LongSparseArray longSparseArray = (LongSparseArray) field3.get(obj2);
                                                if (longSparseArray != null) {
                                                    longSparseArray.clear();
                                                }
                                            } catch (IllegalAccessException e6) {
                                                Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e6);
                                            }
                                        }
                                    }
                                }
                            } catch (IllegalAccessException e7) {
                                e = e7;
                                str = "Could not retrieve value from ResourcesImpl#mDrawableCache";
                                Log.e("ResourcesFlusher", str, e);
                            }
                        }
                    }
                } catch (IllegalAccessException e8) {
                    e = e8;
                    str = "Could not retrieve value from Resources#mResourcesImpl";
                    Log.e("ResourcesFlusher", str, e);
                }
            }
        }
    }
}
