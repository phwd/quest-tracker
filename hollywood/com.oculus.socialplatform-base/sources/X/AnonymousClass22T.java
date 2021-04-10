package X;

import androidx.recyclerview.widget.RecyclerView;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.apache.commons.cli.HelpFormatter;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.22T  reason: invalid class name */
public final class AnonymousClass22T {
    public String A00;
    public String A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final int A05;
    public final int A06;
    public final int A07;
    public final int A08;
    public final int A09;
    public final AnonymousClass1QM<Long> A0A;
    public final AnonymousClass1Sk A0B;
    public final C141521z A0C;
    public final C143622y A0D;
    public final String A0E;
    @Nullable
    public final String A0F;
    @Nonnull
    public final List<SubscribeTopic> A0G;
    public final AtomicInteger A0H;
    public final boolean A0I;
    public final boolean A0J;
    public final boolean A0K;

    /* JADX WARN: Failed to parse method signature: (Ljava/lang/String;Ljava/lang/String;IIZLX/21z;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;LX/1Sk;Ljava/util/concurrent/atomic/AtomicInteger;IIIIIILX/1QM<Ljava/lang/Long;>;Ljava/util/List<Lcom/facebook/rti/mqtt/protocol/messages/SubscribeTopic;>;ZZZLjava/util/Map<Ljava/lang/String;Ljava/lang/String;>;ZLjava/lang/String;ZZIZZZLcom/facebook/rti/mqtt/credentials/TokenBindingAuthenticator;)V */
    public AnonymousClass22T(String str, String str2, int i, int i2, boolean z, C141521z r15, String str3, String str4, AnonymousClass1Sk r18, AtomicInteger atomicInteger, int i3, int i4, int i5, int i6, int i7, int i8, AnonymousClass1QM r26, List list, @Nonnull boolean z2, boolean z3) {
        String str5;
        int length;
        this.A00 = str;
        this.A01 = str2;
        this.A06 = i;
        this.A02 = i2;
        this.A0K = z;
        this.A0C = r15;
        this.A0D = new C143622y(str3, null, RecyclerView.FOREVER_NS);
        this.A0E = str4;
        this.A0B = r18;
        this.A0H = atomicInteger;
        this.A03 = i3;
        this.A08 = i4;
        this.A04 = i5;
        this.A07 = i6;
        this.A05 = i7;
        this.A09 = i8;
        this.A0A = r26;
        this.A0G = list;
        this.A0J = z2;
        this.A0I = z3;
        try {
            try {
                byte[] bytes = AnonymousClass006.A0D(r18.A01(), HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, (String) r15.second, HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, str3, HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR).getBytes("utf-8");
                try {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(bytes, 0, bytes.length);
                    byte[] digest = instance.digest();
                    if (digest == null || (length = digest.length) == 0) {
                        str5 = "";
                        this.A0F = str5;
                    }
                    char[] cArr = new char[(length << 1)];
                    int i9 = 0;
                    for (byte b : digest) {
                        int i10 = i9 + 1;
                        char[] cArr2 = AnonymousClass1Ic.A00;
                        cArr[i9] = cArr2[(b & 240) >>> 4];
                        i9 = i10 + 1;
                        cArr[i10] = cArr2[b & 15];
                    }
                    str5 = new String(cArr);
                    this.A0F = str5;
                } catch (NoSuchAlgorithmException unused) {
                    throw new C146123z();
                }
            } catch (UnsupportedEncodingException unused2) {
                throw new C146123z();
            }
        } catch (C146123z unused3) {
            str5 = null;
        }
    }
}
