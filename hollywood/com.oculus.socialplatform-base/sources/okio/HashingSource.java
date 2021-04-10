package okio;

import com.oculus.secure.trustedapp.HashHelper;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSource extends ForwardingSource {
    public final Mac mac;
    public final MessageDigest messageDigest;

    public static HashingSource hmacSha1(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA1");
    }

    public static HashingSource hmacSha256(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA256");
    }

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, HashHelper.SHA1);
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, "SHA-256");
    }

    public ByteString hash() {
        byte[] doFinal;
        MessageDigest messageDigest2 = this.messageDigest;
        if (messageDigest2 != null) {
            doFinal = messageDigest2.digest();
        } else {
            doFinal = this.mac.doFinal();
        }
        return ByteString.of(doFinal);
    }

    @Override // okio.Source, okio.ForwardingSource
    public long read(Buffer buffer, long j) throws IOException {
        long read = super.read(buffer, j);
        if (read != -1) {
            long j2 = buffer.size;
            long j3 = j2 - read;
            Segment segment = buffer.head;
            while (j2 > j3) {
                segment = segment.prev;
                j2 -= (long) (segment.limit - segment.pos);
            }
            while (j2 < buffer.size) {
                int i = (int) ((((long) segment.pos) + j3) - j2);
                MessageDigest messageDigest2 = this.messageDigest;
                if (messageDigest2 != null) {
                    messageDigest2.update(segment.data, i, segment.limit - i);
                } else {
                    this.mac.update(segment.data, i, segment.limit - i);
                }
                j3 = ((long) (segment.limit - segment.pos)) + j2;
                segment = segment.next;
                j2 = j3;
            }
        }
        return read;
    }

    public HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
            this.mac = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public HashingSource(Source source, ByteString byteString, String str) {
        super(source);
        try {
            Mac instance = Mac.getInstance(str);
            this.mac = instance;
            instance.init(new SecretKeySpec(byteString.toByteArray(), str));
            this.messageDigest = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
