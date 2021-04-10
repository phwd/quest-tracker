package defpackage;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* renamed from: JE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class JE {
    public static String a() {
        try {
            try {
                return (String) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getCurrentInstructionSet", new Class[0]).invoke(null, new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.w("DexOptimzer", "Failed to call dalvik.system.VMRuntime#getCurrentInstructionSet", e);
                throw new NoSuchMethodException("dalvik.system.VMRuntime#getCurrentInstructionSet could not be found.");
            }
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.w("DexOptimzer", "dalvik.system.VMRuntime#getCurrentInstructionSet is unsupported.", e2);
            throw new NoSuchMethodException("dalvik.system.VMRuntime#getCurrentInstructionSet could not be found.");
        }
    }
}
