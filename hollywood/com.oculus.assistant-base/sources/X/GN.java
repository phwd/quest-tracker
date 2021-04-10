package X;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import java.util.HashSet;
import java.util.Set;

public final class GN extends ContentObserver {
    public final C1421zD A00;
    public final String A01;

    public GN(String str, C1421zD zDVar, Context context) {
        super(new Handler(context.getMainLooper()));
        this.A01 = str;
        this.A00 = zDVar;
    }

    public final void onChange(boolean z, Uri uri) {
        boolean addAll;
        String str;
        super.onChange(z);
        C1421zD zDVar = this.A00;
        String str2 = this.A01;
        C0139Dd.A0F("DeviceConfigClient", "Config '%s' updated.", str2);
        ZP zp = zDVar.A00;
        Set<Object> set = (Set) zp.A0G.get(str2);
        if (set != null) {
            int size = set.size();
            String[] strArr = new String[size];
            int i = 0;
            for (Object obj : set) {
                strArr[i] = obj;
                i++;
            }
            try {
                HashSet hashSet = new HashSet();
                for (int i2 = 0; i2 < size; i2++) {
                    String str3 = strArr[i2];
                    GH gh = (GH) zp.A05.get(str3);
                    if (gh != null) {
                        str = ZP.A02(zp, str3, gh.A01);
                    } else {
                        ZT.A08(zp.A0A, "Expected to fail with getMultiple()", str3);
                        str = str3;
                    }
                    hashSet.add(str == 1 ? 1 : 0);
                }
                synchronized (zp.A0F) {
                    addAll = zp.A06.addAll(hashSet);
                }
                if (addAll) {
                    ZP.A03(zp);
                }
            } catch (Exception e) {
                ZT.A05(zp.A0A, e);
            }
        } else {
            ZT.A07(zp.A0A, "Config could not be found in params_map.txt", str2);
        }
    }
}
