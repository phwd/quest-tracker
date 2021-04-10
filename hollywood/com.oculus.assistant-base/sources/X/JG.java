package X;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

public final class JG {
    public int A00 = 0;
    public ClipData A01 = null;
    public ComponentName A02 = null;
    public Intent A03 = null;
    public Rect A04 = null;
    public Uri A05 = null;
    public Bundle A06 = null;
    public String A07 = null;
    public String A08 = null;
    public final Set A09 = new HashSet();

    public static final Intent A00(JG jg, Context context) {
        Intent intent = new Intent();
        intent.setComponent(jg.A02);
        intent.setFlags(jg.A00);
        intent.setAction(jg.A07);
        intent.setDataAndType(jg.A05, jg.A08);
        intent.setSourceBounds(jg.A04);
        intent.setSelector(jg.A03);
        intent.setClipData(jg.A01);
        for (String str : jg.A09) {
            intent.addCategory(str);
        }
        if (jg.A06 != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
            intent.putExtras(jg.A06);
        }
        if (intent.getComponent() != null) {
            intent.setPackage(intent.getComponent().getPackageName());
            return intent;
        }
        throw new SecurityException("Must generate PendingIntent based on an explicit intent.");
    }
}
