package org.chromium.chrome.browser.webapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebApkUpdateDataFetcher extends WK {
    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        throw null;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        throw null;
    }

    public void onDataAvailable(String str, String str2, String str3, String str4, String str5, String str6, Bitmap bitmap, boolean z, String str7, String str8, Bitmap bitmap2, String[] strArr, int i, int i2, long j, long j2, String str9, String str10, String str11, boolean z2, boolean z3, String[] strArr2, String[][] strArr3, String[][] strArr4) {
        Context applicationContext = ContextUtils.getApplicationContext();
        HashMap hashMap = new HashMap();
        int length = strArr.length;
        int i3 = 0;
        while (true) {
            String str12 = null;
            if (i3 >= length) {
                break;
            }
            String str13 = strArr[i3];
            if (str13.equals(str5)) {
                str12 = str6;
            } else if (str13.equals(str7)) {
                str12 = str8;
            }
            hashMap.put(str13, str12);
            i3++;
        }
        ArrayList arrayList = new ArrayList();
        for (String[] strArr5 : strArr4) {
            arrayList.add(new Dw1(strArr5[0], strArr5[1], strArr5[2], strArr5[3], strArr5[4], new Zx1(strArr5[5], false)));
        }
        if (!TextUtils.isEmpty(str9)) {
            new C1758ax1(str9, str10, str11, z2, z3, strArr2, strArr3);
        }
        RY0.a(applicationContext);
        new Intent();
        throw null;
    }
}
