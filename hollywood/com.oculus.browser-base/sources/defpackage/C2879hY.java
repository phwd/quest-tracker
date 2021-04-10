package defpackage;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/* renamed from: hY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2879hY implements AbstractC2025cY {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Map f10076a;

    public C2879hY(Map map) {
        this.f10076a = map;
    }

    @Override // defpackage.AbstractC2025cY
    public void a(OutputStream outputStream) {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        Yq1 yq1 = new Yq1(bufferedWriter);
        boolean z = true;
        for (Map.Entry entry : this.f10076a.entrySet()) {
            Object key = entry.getKey();
            if (!z) {
                bufferedWriter.append('&');
            }
            Object value = entry.getValue();
            if (value == null) {
                value = "";
            }
            yq1.write(key.toString());
            bufferedWriter.write(61);
            yq1.write(value.toString());
            z = false;
        }
        bufferedWriter.flush();
    }
}
