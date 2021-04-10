package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1Zq  reason: invalid class name and case insensitive filesystem */
public class C06391Zq implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ AnonymousClass1Zb A01;
    public final /* synthetic */ Number A02;
    public final /* synthetic */ Number A03;
    public final /* synthetic */ Number A04;
    public final /* synthetic */ Number A05;
    public final /* synthetic */ Number A06;
    public final /* synthetic */ Number A07;
    public final /* synthetic */ Number A08;
    public final /* synthetic */ String A09;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidSetMailboxSyncParamsNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new C06381Zp(this));
        this.A01.A04(A002, A003);
        MailboxCoreJNI.dispatchVOOOOOOOOOO(0, mailbox2, this.A05, this.A04, this.A03, this.A02, this.A06, this.A08, this.A07, this.A09, A003);
    }

    public C06391Zq(C06461Zy r1, AnonymousClass1Zb r2, Number number, Number number2, Number number3, Number number4, Number number5, Number number6, Number number7, String str) {
        this.A00 = r1;
        this.A01 = r2;
        this.A05 = number;
        this.A04 = number2;
        this.A03 = number3;
        this.A02 = number4;
        this.A06 = number5;
        this.A08 = number6;
        this.A07 = number7;
        this.A09 = str;
    }
}
