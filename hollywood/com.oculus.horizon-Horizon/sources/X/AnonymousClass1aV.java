package X;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.inject.ForAppContext;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
/* renamed from: X.1aV  reason: invalid class name */
public final class AnonymousClass1aV implements MobileConfigCxxChangeListener {
    public Map<Integer, String> A00;
    @ForAppContext
    public final Context A01;
    public final Object A02 = new Object();
    public final String A03;
    public final Map<String, Set<String>> A04;
    public final Set<MobileConfigCxxChangeListener> A05 = new HashSet();

    @Override // com.facebook.mobileconfig.MobileConfigCxxChangeListener
    public final synchronized void onConfigChanged(String[] strArr) {
        String[] strArr2;
        int length;
        if (strArr == null) {
            strArr2 = new String[0];
        } else {
            strArr2 = strArr;
        }
        for (MobileConfigCxxChangeListener mobileConfigCxxChangeListener : this.A05) {
            mobileConfigCxxChangeListener.onConfigChanged(strArr2);
        }
        if (!(strArr == null || (length = strArr.length) == 0)) {
            int i = 0;
            boolean z = false;
            while (true) {
                String str = null;
                if (i >= length) {
                    break;
                }
                String str2 = strArr[i];
                try {
                    if (Character.isDigit(str2.charAt(0))) {
                        int parseInt = Integer.parseInt(str2);
                        Map<Integer, String> map = this.A00;
                        Integer valueOf = Integer.valueOf(parseInt);
                        if (!map.containsKey(valueOf)) {
                            if (!z) {
                                String A012 = AnonymousClass1aW.A01(this.A01.getApplicationContext().getFilesDir());
                                if (!A012.isEmpty()) {
                                    for (AnonymousClass1am r6 : AnonymousClass1am.A01(A012, true)) {
                                        int i2 = r6.A01;
                                        if (i2 > 0 && i2 < 1048576) {
                                            Map<Integer, String> map2 = this.A00;
                                            Integer valueOf2 = Integer.valueOf(i2);
                                            if (!map2.containsKey(valueOf2)) {
                                                this.A00.put(valueOf2, r6.A06);
                                            }
                                        }
                                    }
                                }
                                z = true;
                            } else {
                                AnonymousClass0NO.A0E("MobileConfigChangeRegistry", "Could not map config key '%s' to name", valueOf);
                                strArr[i] = str;
                            }
                        }
                        str = this.A00.get(valueOf);
                        strArr[i] = str;
                    }
                } catch (Exception e) {
                    AnonymousClass0NO.A0K("MobileConfigChangeRegistry", e, "Failed to convert spec %s", str2);
                }
                i++;
            }
            synchronized (this.A02) {
                for (String str3 : strArr) {
                    if (str3 != null) {
                        try {
                            Set<String> set = this.A04.get(str3);
                            if (set != null) {
                                for (String str4 : set) {
                                    Uri withAppendedPath = Uri.withAppendedPath(Uri.withAppendedPath(AnonymousClass1aU.A00, str3), str4);
                                    withAppendedPath.toString();
                                    this.A01.getContentResolver().notifyChange(withAppendedPath, null);
                                }
                            }
                        } catch (Exception e2) {
                            AnonymousClass0NO.A0K("MobileConfigChangeRegistry", e2, "Failed to call change listener for config %s", str3);
                        }
                    }
                }
            }
        }
    }

    public static void A00(AnonymousClass1aV r5) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<String, Set<String>> map = r5.A04;
        for (String str : map.keySet()) {
            stringBuffer.append(str);
            stringBuffer.append(",");
            stringBuffer.append(TextUtils.join(",", map.get(str)));
            stringBuffer.append(":");
        }
        try {
            String obj = stringBuffer.toString();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(r5.A03)));
            try {
                bufferedWriter.write(obj);
                bufferedWriter.flush();
                bufferedWriter.close();
                bufferedWriter.close();
                return;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            AnonymousClass0NO.A0B("MobileConfigChangeRegistry", "Failed to save change listeners to disk", e);
        }
    }

    @Inject
    public AnonymousClass1aV(@ForAppContext Context context) {
        HashMap hashMap;
        this.A01 = context;
        this.A03 = AnonymousClass006.A05(context.getApplicationContext().getFilesDir().getAbsolutePath(), "/mobileconfig/change_listeners.txt");
        synchronized (this) {
            hashMap = new HashMap();
            try {
                String str = this.A03;
                if (new File(str).exists()) {
                    for (String str2 : AnonymousClass1aW.A04(str).split(":")) {
                        String[] split = str2.split(",");
                        if (split.length >= 2) {
                            String str3 = split[0];
                            hashMap.put(str3, new HashSet());
                            for (int i = 1; i < split.length; i++) {
                                ((Set) hashMap.get(str3)).add(split[i]);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                AnonymousClass0NO.A0B("MobileConfigChangeRegistry", "Could not read change listeners file from disk", e);
            }
        }
        this.A04 = hashMap;
        this.A00 = new HashMap();
    }
}
