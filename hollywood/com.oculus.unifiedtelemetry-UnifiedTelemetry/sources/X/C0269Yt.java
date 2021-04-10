package X;

import android.content.Context;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.Yt  reason: case insensitive filesystem */
public final class C0269Yt implements H5<ByteArrayOutputStream> {
    public static final long A08 = TimeUnit.SECONDS.toMillis(60);
    @Nullable
    public ByteArrayOutputStream A00;
    @Nullable
    public HandlerC0083Gd A01;
    public boolean A02;
    public final Fw A03;
    public final G6 A04;
    public final AbstractC0090Hb A05;
    public final Context A06;
    public final HandlerThreadFactory A07;

    public C0269Yt(Context context, MG mg, G6 g6) {
        this.A06 = context;
        this.A04 = g6;
        try {
            this.A05 = G7.A00(context).A04(this.A04.A00.getName());
            this.A07 = G7.A00(this.A06).A03(this.A04.A01.getName());
            this.A03 = new Fw(this.A06, mg, this.A04.A03);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(AnonymousClass06.A04("Failed to create instance of ", this.A04.A00.getName()), e);
        }
    }

    private HandlerC0083Gd A00() {
        int i;
        String str;
        HandlerC0083Gd gd = this.A01;
        if (gd != null) {
            return gd;
        }
        if (this.A04.A02 == AnonymousClass07.A01) {
            i = 0;
            str = "Analytics-HighPri-InMemory-Scheduler";
        } else {
            i = 10;
            str = "Analytics-NormalPri-InMemory-Scheduler";
        }
        HandlerC0083Gd gd2 = new HandlerC0083Gd(this, this.A07.A1f(str, i).getLooper());
        this.A01 = gd2;
        return gd2;
    }

    @Override // X.H5
    public final void A3W() {
        if (this.A00 != null) {
            HandlerC0083Gd A002 = A00();
            ByteArrayOutputStream byteArrayOutputStream = this.A00;
            A002.removeMessages(1, byteArrayOutputStream);
            A002.sendMessage(A002.obtainMessage(1, byteArrayOutputStream));
            return;
        }
        throw new IllegalStateException("mByteArrayOutputStream is null");
    }

    @Override // X.H5
    public final void A3X() {
        if (this.A00 == null) {
            throw new IllegalStateException("mByteArrayOutputStream is null");
        } else if (!this.A02) {
            this.A02 = true;
            HandlerC0083Gd A002 = A00();
            ByteArrayOutputStream byteArrayOutputStream = this.A00;
            A002.sendMessageDelayed(A002.obtainMessage(1, byteArrayOutputStream), A08);
        }
    }

    @Override // X.H5
    public final void A3Z(@Nullable String str) {
        if (this.A00 != null) {
            HandlerC0083Gd A002 = A00();
            ByteArrayOutputStream byteArrayOutputStream = this.A00;
            A002.removeMessages(1, byteArrayOutputStream);
            A002.sendMessage(A002.obtainMessage(1, byteArrayOutputStream));
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.H5
    public final void A51(@Nullable ByteArrayOutputStream byteArrayOutputStream) {
        ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        if (this.A00 != byteArrayOutputStream2) {
            this.A00 = byteArrayOutputStream2;
            this.A02 = false;
        }
    }

    @Override // X.H5
    public final void A5V() {
        if (this.A00 != null) {
            HandlerC0083Gd A002 = A00();
            ByteArrayOutputStream byteArrayOutputStream = this.A00;
            A002.removeMessages(1, byteArrayOutputStream);
            A002.sendMessage(A002.obtainMessage(1, byteArrayOutputStream));
        }
    }
}
