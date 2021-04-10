package X;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1g5  reason: invalid class name */
public final class AnonymousClass1g5 {
    public int A00;
    public int A01;
    public int A02;
    public Bitmap A03;
    public AnonymousClass1g1<Bitmap> A04;
    public AnonymousClass1gV A05;
    public AnonymousClass1gV A06;
    public AnonymousClass1gV A07;
    public boolean A08;
    public boolean A09;
    public AnonymousClass1eU<Bitmap> A0A;
    public boolean A0B;
    public final Handler A0C;
    public final AnonymousClass1g0 A0D;
    public final AnonymousClass1gD A0E;
    public final AbstractC07941di A0F;
    public final List<AnonymousClass1hK> A0G = new ArrayList();

    @VisibleForTesting
    public final void A02(AnonymousClass1gV r6) {
        this.A0B = false;
        if (this.A08) {
            this.A0C.obtainMessage(2, r6).sendToTarget();
        } else if (!this.A09) {
            this.A07 = r6;
        } else {
            if (r6.A00 != null) {
                Bitmap bitmap = this.A03;
                if (bitmap != null) {
                    this.A0F.A8l(bitmap);
                    this.A03 = null;
                }
                AnonymousClass1gV r3 = this.A05;
                this.A05 = r6;
                List<AnonymousClass1hK> list = this.A0G;
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    list.get(size).A78();
                }
                if (r3 != null) {
                    this.A0C.obtainMessage(2, r3).sendToTarget();
                }
            }
            A00(this);
        }
    }

    public AnonymousClass1g5(ComponentCallbacks2C07631cl r6, GifDecoder gifDecoder, int i, int i2, AnonymousClass1eU<Bitmap> r10, Bitmap bitmap) {
        AbstractC07941di r4 = r6.A02;
        C08731fB r3 = r6.A00;
        Context baseContext = r3.getBaseContext();
        AnonymousClass1g0 A062 = ComponentCallbacks2C07631cl.A02(baseContext).A06(baseContext);
        Context baseContext2 = r3.getBaseContext();
        AnonymousClass1g1<Bitmap> apply = ComponentCallbacks2C07631cl.A02(baseContext2).A06(baseContext2).asBitmap().apply(AnonymousClass1g7.diskCacheStrategyOf(AbstractC08841fc.A03).useAnimationPool(true).skipMemoryCache(true).override(i, i2));
        this.A0D = A062;
        Handler handler = new Handler(Looper.getMainLooper(), new AnonymousClass1gh(this));
        this.A0F = r4;
        this.A0C = handler;
        this.A04 = apply;
        this.A0E = gifDecoder;
        A01(r10, bitmap);
    }

    public static void A00(AnonymousClass1g5 r6) {
        int i;
        int i2;
        if (r6.A09 && !r6.A0B) {
            AnonymousClass1gV r1 = r6.A07;
            if (r1 != null) {
                r6.A07 = null;
                r6.A02(r1);
                return;
            }
            r6.A0B = true;
            AnonymousClass1gD r4 = r6.A0E;
            AnonymousClass1gT r2 = r4.A07;
            int i3 = r2.A02;
            if (i3 <= 0 || (i2 = r4.A02) < 0) {
                i = 0;
            } else if (i2 < i3) {
                i = r2.A0A.get(i2).A01;
            } else {
                i = -1;
            }
            long uptimeMillis = SystemClock.uptimeMillis() + ((long) i);
            int i4 = (r4.A02 + 1) % r4.A07.A02;
            r4.A02 = i4;
            r6.A06 = new AnonymousClass1gV(r6.A0C, i4, uptimeMillis);
            AnonymousClass1g1<Bitmap> apply = r6.A04.apply((AnonymousClass1g2<?>) AnonymousClass1g7.signatureOf(new AnonymousClass1S3(Double.valueOf(Math.random()))));
            apply.load((Object) r4);
            apply.into(r6.A06);
        }
    }

    public final void A01(AnonymousClass1eU<Bitmap> r3, Bitmap bitmap) {
        AnonymousClass1S2.A00(r3);
        this.A0A = r3;
        AnonymousClass1S2.A00(bitmap);
        this.A03 = bitmap;
        this.A04 = this.A04.apply(new AnonymousClass1g7().transform(r3));
        this.A00 = C08381eW.A01(bitmap);
        this.A02 = bitmap.getWidth();
        this.A01 = bitmap.getHeight();
    }
}
