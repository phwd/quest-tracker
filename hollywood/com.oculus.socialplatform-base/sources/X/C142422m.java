package X;

import android.content.Context;
import com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$O_Zp9F_wgej5LDIVLNVImUs2JG02;
import com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2;
import com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$fDCk4RJ2sVQX3AFy96f42X6u_uY2;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.22m  reason: invalid class name and case insensitive filesystem */
public final class C142422m {
    public final Context A00;
    @Nullable
    public final $$Lambda$VrMsysMqttClientCallbacks$O_Zp9F_wgej5LDIVLNVImUs2JG02 A01;
    @Nullable
    public final $$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2 A02;
    @Nullable
    public final $$Lambda$VrMsysMqttClientCallbacks$fDCk4RJ2sVQX3AFy96f42X6u_uY2 A03;
    public final String A04;
    public final String A05;
    @Nullable
    public final String A06;
    public final String A07;
    public final List<String> A08;
    public volatile C141521z A09;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/21z;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/facebook/mqtt/client/MqttPublishArrivedListener;Lcom/facebook/mqtt/client/MqttChannelStateListener;Lcom/facebook/mqtt/client/NetworkDataUsageListener;Ljava/util/List<Ljava/lang/String;>;Lcom/facebook/rti/mqtt/protocol/serialization/MessagePayloadEncoder;Lcom/facebook/analytics/structuredlogger/base/Logger;)V */
    public C142422m(Context context, C141521z r3, String str, String str2, String str3, $$Lambda$VrMsysMqttClientCallbacks$O_Zp9F_wgej5LDIVLNVImUs2JG02 r7, $$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2 r8, $$Lambda$VrMsysMqttClientCallbacks$fDCk4RJ2sVQX3AFy96f42X6u_uY2 r9, List list) {
        if (context == null) {
            throw null;
        } else if (str == null) {
            throw null;
        } else if (str3 != null) {
            this.A00 = context;
            this.A09 = r3;
            this.A04 = str;
            this.A06 = str2;
            this.A07 = str3;
            this.A05 = "";
            this.A01 = r7;
            this.A02 = r8;
            this.A03 = r9;
            this.A08 = list == null ? new ArrayList() : list;
        } else {
            throw null;
        }
    }
}
