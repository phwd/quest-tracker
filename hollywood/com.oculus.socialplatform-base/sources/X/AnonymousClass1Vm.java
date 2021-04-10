package X;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.1Vm  reason: invalid class name */
public final class AnonymousClass1Vm {
    public int A00;
    public Notification.Builder A01;
    public String A02 = "";
    public Queue<String> A03;
    public final Context A04;
    public final int A05;
    public final NotificationManager A06;
    public final String A07;
    public final String A08;
    public final boolean A09;

    @TargetApi(16)
    private Notification.InboxStyle A00() {
        Notification.InboxStyle summaryText = new Notification.InboxStyle().setBigContentTitle(AnonymousClass006.A09("[", this.A07, "]")).setSummaryText(this.A08);
        for (String str : this.A03) {
            summaryText.addLine(str);
        }
        return summaryText;
    }

    @TargetApi(26)
    public final void A01(String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        if (!this.A09) {
            try {
                this.A06.cancel("MqttDiagnosticNotification", this.A05);
            } catch (RuntimeException unused) {
            }
        } else {
            synchronized (this) {
                this.A00 = 0;
                this.A02 = str;
                int i = -65536;
                int i2 = 17301608;
                if ("CONNECTED".equals(str)) {
                    i2 = 17301611;
                    i = -16711936;
                } else if ("CONNECTING".equals(str)) {
                    i2 = 17301607;
                    i = -256;
                }
                Context context = this.A04;
                Notification.Builder smallIcon = new Notification.Builder(context).setChannelId("debug_channel").setSmallIcon(i2);
                C02670jZ r1 = new C02670jZ();
                r1.A0B = true;
                Notification.Builder contentText = smallIcon.setContentIntent(r1.A02(context, 0)).setContentTitle(AnonymousClass006.A09("[", this.A07, "]")).setContentText(str);
                this.A01 = contentText;
                contentText.setColor(i);
                this.A01.setStyle(A00());
                this.A06.notify("MqttDiagnosticNotification", this.A05, this.A01.getNotification());
            }
        }
    }

    @TargetApi(16)
    public final void A02(String str) {
        if (!this.A09) {
            try {
                this.A06.cancel("MqttDiagnosticNotification", this.A05);
            } catch (RuntimeException unused) {
            }
        } else if (this.A01 != null) {
            synchronized (this) {
                Notification.Builder builder = this.A01;
                int i = this.A00 + 1;
                this.A00 = i;
                builder.setSubText(String.valueOf(i));
                this.A03.add(AnonymousClass006.A09(new SimpleDateFormat("h:mm:ss a").format(new Date()), HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, str));
                if (this.A03.size() > 8) {
                    this.A03.poll();
                }
                this.A01.setContentText(this.A02);
                this.A01.setStyle(A00());
                this.A06.notify("MqttDiagnosticNotification", this.A05, this.A01.getNotification());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a A[Catch:{ all -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b A[EDGE_INSN: B:32:0x005b->B:20:0x005b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @android.annotation.TargetApi(26)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass1Vm(android.content.Context r7, java.lang.String r8, boolean r9, X.AnonymousClass1PM r10) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Vm.<init>(android.content.Context, java.lang.String, boolean, X.1PM):void");
    }
}
