package X;

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

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.0x9  reason: invalid class name */
public final class AnonymousClass0x9 {
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
    public final AbstractC09610zk<Long> A0A;
    public final C08390xA A0B;
    public final C07760vx A0C;
    public final C07920wE A0D;
    public final AnonymousClass0wH A0E;
    public final String A0F = "567310203415052";
    @Nullable
    public final String A0G;
    @Nonnull
    public final List<SubscribeTopic> A0H;
    public final AtomicInteger A0I;
    public final boolean A0J;
    public final boolean A0K;

    /* JADX WARN: Failed to parse method signature: (Ljava/lang/String;Ljava/lang/String;IIZLX/0vx;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;LX/0xA;Ljava/util/concurrent/atomic/AtomicInteger;IIIIIILX/0zk<Ljava/lang/Long;>;Ljava/util/List<Lcom/facebook/rti/mqtt/protocol/messages/SubscribeTopic;>;ZZZLjava/util/Map<Ljava/lang/String;Ljava/lang/String;>;ZLjava/lang/String;ZZIZZZLX/0wH;)V */
    public AnonymousClass0x9(String str, String str2, int i, int i2, boolean z, C07760vx r17, String str3, String str4, C08390xA r20, AtomicInteger atomicInteger, int i3, int i4, int i5, int i6, int i7, int i8, AbstractC09610zk r28, List list, @Nonnull boolean z2, AnonymousClass0wH r31) {
        String str5;
        this.A00 = str;
        this.A01 = str2;
        this.A06 = i;
        this.A02 = i2;
        this.A0K = z;
        this.A0C = r17;
        this.A0E = r31;
        this.A0D = new C07920wE(str3, str4, Long.MAX_VALUE);
        this.A0B = r20;
        this.A0I = atomicInteger;
        this.A03 = i3;
        this.A08 = i4;
        this.A04 = i5;
        this.A07 = i6;
        this.A05 = i7;
        this.A09 = i8;
        this.A0A = r28;
        this.A0H = list;
        this.A0J = z2;
        try {
            try {
                byte[] bytes = AnonymousClass006.A0A(r20.A01(), " ", (String) r17.second, " ", str3, " ").getBytes("utf-8");
                try {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(bytes, 0, bytes.length);
                    str5 = AnonymousClass0yX.A01(instance.digest());
                } catch (NoSuchAlgorithmException unused) {
                    throw new C09630zp();
                }
            } catch (UnsupportedEncodingException unused2) {
                throw new C09630zp();
            }
        } catch (C09630zp unused3) {
            str5 = null;
        }
        this.A0G = str5;
    }
}
