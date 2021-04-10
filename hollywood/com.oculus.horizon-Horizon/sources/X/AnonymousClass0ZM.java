package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.internal.Utility;
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
/* renamed from: X.0ZM  reason: invalid class name */
public final class AnonymousClass0ZM {
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
    public final AnonymousClass0WY<Long> A0A;
    public final AnonymousClass0Wh A0B;
    public final AnonymousClass0Y2 A0C;
    public final AnonymousClass0Y3 A0D;
    public final AnonymousClass0Y6 A0E;
    public final String A0F = "567310203415052";
    @Nullable
    public final String A0G;
    @Nonnull
    public final List<SubscribeTopic> A0H;
    public final AtomicInteger A0I;
    public final boolean A0J;
    public final boolean A0K;

    /* JADX WARN: Failed to parse method signature: (Ljava/lang/String;Ljava/lang/String;IIZLX/0Y2;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;LX/0Wh;Ljava/util/concurrent/atomic/AtomicInteger;IIIIIILX/0WY<Ljava/lang/Long;>;Ljava/util/List<Lcom/facebook/rti/mqtt/protocol/messages/SubscribeTopic;>;ZZZLjava/util/Map<Ljava/lang/String;Ljava/lang/String;>;ZLjava/lang/String;ZZIZZZLX/0Y6;)V */
    public AnonymousClass0ZM(String str, String str2, int i, int i2, boolean z, AnonymousClass0Y2 r17, String str3, String str4, AnonymousClass0Wh r20, AtomicInteger atomicInteger, int i3, int i4, int i5, int i6, int i7, int i8, AnonymousClass0WY r28, List list, @Nonnull boolean z2, AnonymousClass0Y6 r31) {
        String str5;
        this.A00 = str;
        this.A01 = str2;
        this.A06 = i;
        this.A02 = i2;
        this.A0K = z;
        this.A0C = r17;
        this.A0E = r31;
        this.A0D = new AnonymousClass0Y3(str3, str4, Long.MAX_VALUE);
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
                    MessageDigest instance = MessageDigest.getInstance(Utility.HASH_ALGORITHM_MD5);
                    instance.update(bytes, 0, bytes.length);
                    str5 = C01910Xz.A01(instance.digest());
                    this.A0G = str5;
                } catch (NoSuchAlgorithmException unused) {
                    throw new AnonymousClass0ZL();
                }
            } catch (UnsupportedEncodingException unused2) {
                throw new AnonymousClass0ZL();
            }
        } catch (AnonymousClass0ZL unused3) {
            str5 = null;
        }
    }
}
