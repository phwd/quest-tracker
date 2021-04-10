package defpackage;

import android.content.res.Configuration;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/* renamed from: lx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3635lx {

    /* renamed from: a  reason: collision with root package name */
    public static SparseArray f10388a = new SparseArray();
    public static SparseArray b = new SparseArray();
    public static SparseArray c = new SparseArray();
    public static SparseArray d = new SparseArray();
    public static SparseArray e = new SparseArray();
    public static SparseArray f = new SparseArray();
    public static SparseArray g = new SparseArray();
    public static SparseArray h = new SparseArray();
    public static SparseArray i = new SparseArray();
    public static final HashMap j;

    static {
        Field[] fieldArr;
        IllegalArgumentException e2;
        IllegalAccessException e3;
        HashMap hashMap = new HashMap();
        j = hashMap;
        hashMap.put("HARDKEYBOARDHIDDEN_", f10388a);
        hashMap.put("KEYBOARD_", b);
        hashMap.put("KEYBOARDHIDDEN_", c);
        hashMap.put("NAVIGATION_", d);
        hashMap.put("NAVIGATIONHIDDEN_", e);
        hashMap.put("ORIENTATION_", f);
        hashMap.put("SCREENLAYOUT_", g);
        hashMap.put("TOUCHSCREEN_", h);
        hashMap.put("UI_MODE_", i);
        Field[] fields = Configuration.class.getFields();
        int length = fields.length;
        int i2 = 0;
        while (i2 < length) {
            Field field = fields[i2];
            if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers())) {
                fieldArr = fields;
            } else {
                String name = field.getName();
                try {
                    fieldArr = fields;
                    if (name.startsWith("HARDKEYBOARDHIDDEN_")) {
                        try {
                            f10388a.put(field.getInt(null), name);
                        } catch (IllegalArgumentException e4) {
                            e2 = e4;
                            Log.w(AbstractC1585a.f9392a, "Error while inspecting device configuration: ", e2);
                            i2++;
                            fields = fieldArr;
                        } catch (IllegalAccessException e5) {
                            e3 = e5;
                            Log.w(AbstractC1585a.f9392a, "Error while inspecting device configuration: ", e3);
                            i2++;
                            fields = fieldArr;
                        }
                    } else if (name.startsWith("KEYBOARD_")) {
                        b.put(field.getInt(null), name);
                    } else if (name.startsWith("KEYBOARDHIDDEN_")) {
                        c.put(field.getInt(null), name);
                    } else if (name.startsWith("NAVIGATION_")) {
                        d.put(field.getInt(null), name);
                    } else if (name.startsWith("NAVIGATIONHIDDEN_")) {
                        e.put(field.getInt(null), name);
                    } else if (name.startsWith("ORIENTATION_")) {
                        f.put(field.getInt(null), name);
                    } else if (name.startsWith("SCREENLAYOUT_")) {
                        g.put(field.getInt(null), name);
                    } else if (name.startsWith("TOUCHSCREEN_")) {
                        h.put(field.getInt(null), name);
                    } else if (name.startsWith("UI_MODE_")) {
                        i.put(field.getInt(null), name);
                    }
                } catch (IllegalArgumentException e6) {
                    e2 = e6;
                    fieldArr = fields;
                    Log.w(AbstractC1585a.f9392a, "Error while inspecting device configuration: ", e2);
                    i2++;
                    fields = fieldArr;
                } catch (IllegalAccessException e7) {
                    e3 = e7;
                    fieldArr = fields;
                    Log.w(AbstractC1585a.f9392a, "Error while inspecting device configuration: ", e3);
                    i2++;
                    fields = fieldArr;
                }
            }
            i2++;
            fields = fieldArr;
        }
    }

    public static String a(SparseArray sparseArray, int i2) {
        int i3;
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < sparseArray.size(); i4++) {
            int keyAt = sparseArray.keyAt(i4);
            if (((String) sparseArray.get(keyAt)).endsWith("_MASK") && (i3 = keyAt & i2) > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append((String) sparseArray.get(i3));
            }
        }
        return sb.toString();
    }

    public static String b(Configuration configuration, Field field) {
        String name = field.getName();
        if (name.equals("mcc") || name.equals("mnc")) {
            return Integer.toString(field.getInt(configuration));
        }
        if (name.equals("uiMode")) {
            return a((SparseArray) j.get("UI_MODE_"), field.getInt(configuration));
        }
        if (name.equals("screenLayout")) {
            return a((SparseArray) j.get("SCREENLAYOUT_"), field.getInt(configuration));
        }
        HashMap hashMap = j;
        SparseArray sparseArray = (SparseArray) hashMap.get(name.toUpperCase() + '_');
        if (sparseArray == null) {
            return Integer.toString(field.getInt(configuration));
        }
        String str = (String) sparseArray.get(field.getInt(configuration));
        return str == null ? Integer.toString(field.getInt(configuration)) : str;
    }
}
