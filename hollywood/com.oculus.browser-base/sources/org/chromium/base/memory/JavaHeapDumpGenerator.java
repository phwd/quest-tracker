package org.chromium.base.memory;

import android.os.Debug;
import java.io.IOException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JavaHeapDumpGenerator {
    public static boolean generateHprof(String str) {
        try {
            Debug.dumpHprofData(str);
            return true;
        } catch (IOException e) {
            StringBuilder k = AbstractC2531fV.k("Error writing to file ", str, ". Error: ");
            k.append(e.getMessage());
            AbstractC1220Ua0.d("JavaHprofGenerator", k.toString(), new Object[0]);
            return false;
        }
    }
}
