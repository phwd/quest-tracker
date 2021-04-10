package X;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;

/* renamed from: X.0Wq  reason: invalid class name and case insensitive filesystem */
public final class C01680Wq {
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
        Notification.InboxStyle summaryText = new Notification.InboxStyle().setBigContentTitle(AnonymousClass006.A07("[", this.A07, "]")).setSummaryText(this.A08);
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
                AnonymousClass0b4 r2 = new AnonymousClass0b4();
                r2.A0B = true;
                Notification.Builder contentText = smallIcon.setContentIntent(PendingIntent.getActivity(context, 0, AnonymousClass0b4.A01(r2, context), AnonymousClass0b4.A00(r2, 0))).setContentTitle(AnonymousClass006.A07("[", this.A07, "]")).setContentText(str);
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
                if (Build.VERSION.SDK_INT >= 24) {
                    Notification.Builder builder = this.A01;
                    int i = this.A00 + 1;
                    this.A00 = i;
                    builder.setSubText(String.valueOf(i));
                } else {
                    Notification.Builder builder2 = this.A01;
                    int i2 = this.A00 + 1;
                    this.A00 = i2;
                    builder2.setContentInfo(String.valueOf(i2));
                }
                this.A03.add(AnonymousClass006.A07(new SimpleDateFormat("h:mm:ss a").format(new Date()), " ", str));
                if (this.A03.size() > 8) {
                    this.A03.poll();
                }
                this.A01.setContentText(this.A02);
                this.A01.setStyle(A00());
                this.A06.notify("MqttDiagnosticNotification", this.A05, this.A01.getNotification());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    @android.annotation.TargetApi(26)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C01680Wq(android.content.Context r7, java.lang.String r8, boolean r9, X.C06510nV r10) {
        /*
        // Method dump skipped, instructions count: 161
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01680Wq.<init>(android.content.Context, java.lang.String, boolean, X.0nV):void");
    }
}
