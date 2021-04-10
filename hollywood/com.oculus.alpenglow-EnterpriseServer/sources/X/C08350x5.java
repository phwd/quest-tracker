package X;

import android.content.Context;
import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.push.service.FbnsService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0x5  reason: invalid class name and case insensitive filesystem */
public final class C08350x5 implements AbstractC09550ze {
    @Override // X.AbstractC09550ze
    public final Bundle A2A(FbnsService fbnsService, Bundle bundle) {
        String valueOf;
        String valueOf2;
        HashSet hashSet;
        ArrayList<String> arrayList = new ArrayList<>();
        C08340x4 r7 = fbnsService.A02;
        C09580zh r0 = r7.A01;
        if (r0 != null) {
            AbstractServiceC08280wx r5 = r0.A00;
            HashMap hashMap = new HashMap();
            hashMap.put("is_mqtt_direct", "false");
            long j = r5.A09.A00;
            if (j > 0) {
                valueOf = new Date(j).toString();
            } else {
                valueOf = String.valueOf(j);
            }
            hashMap.put("last_connection_time", valueOf);
            long j2 = r5.A09.A01;
            if (j2 > 0) {
                valueOf2 = new Date(j2).toString();
            } else {
                valueOf2 = String.valueOf(j2);
            }
            hashMap.put("last_network_changed_time", valueOf2);
            C08290wy r02 = r5.A09;
            synchronized (r02.A0S) {
                hashSet = new HashSet(r02.A0S.keySet());
            }
            hashMap.put("subscribed_topics", hashSet.toString());
            hashMap.put("mqtt_health_stats", AbstractServiceC08280wx.A07(r5));
            r7.A5h("DumpSys", hashMap);
        } else {
            r7.log("SystemDumper not connected");
        }
        try {
            r7.A06.submit(new RunnableC09560zf(r7)).get();
        } catch (InterruptedException | ExecutionException unused) {
        }
        ArrayList<File> arrayList2 = new ArrayList();
        int i = 0;
        if (r7.A00 == 0) {
            i = 1;
        }
        Context context = r7.A04;
        File file = new File(context.getCacheDir(), AnonymousClass006.A01("fbnslite_log", i));
        if (file.exists()) {
            arrayList2.add(file);
        }
        File file2 = new File(context.getCacheDir(), AnonymousClass006.A01("fbnslite_log", r7.A00));
        if (file2.exists()) {
            arrayList2.add(file2);
        }
        for (File file3 : arrayList2) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file3));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        arrayList.add(readLine);
                    } catch (Throwable unused2) {
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                arrayList.add(AnonymousClass006.A08("Error reading file ", file3.getName(), " - ", e.toString()));
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("flytrap", arrayList);
        return bundle2;
        throw th;
    }

    @Override // X.AbstractC09550ze
    public final void A2B(FbnsService fbnsService, Bundle bundle) {
        throw new IllegalArgumentException("not implemented for FlytrapReportFetcher");
    }
}
