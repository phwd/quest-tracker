package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* renamed from: eg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2388eg extends Drawable implements Runnable, Animatable, Handler.Callback {
    public static final byte[] F = "NETSCAPE2.0".getBytes();
    public static Paint G;
    public static Paint H;
    public static HandlerThreadC2218dg I;

    /* renamed from: J  reason: collision with root package name */
    public static Handler f9870J;
    public final Handler A0 = new Handler(Looper.getMainLooper(), this);
    public boolean B0;
    public boolean C0;
    public int D0 = 0;
    public int E0 = 0;
    public final Bitmap.Config F0;
    public boolean G0 = true;
    public final C2901hg K;
    public final byte[] L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public Bitmap R;
    public int[] S;
    public boolean T;
    public float U;
    public volatile boolean V;
    public volatile boolean W;
    public volatile boolean X = true;
    public int Y;
    public boolean Z;
    public int a0;
    public int[] b0;
    public int[] c0;
    public boolean d0;
    public int e0;
    public int f0;
    public int g0;
    public int h0;
    public int i0;
    public byte[] j0 = new byte[256];
    public int k0 = 2;
    public boolean l0;
    public int m0;
    public short[] n0 = new short[4096];
    public byte[] o0 = new byte[4096];
    public byte[] p0 = new byte[4097];
    public byte[] q0;
    public boolean r0;
    public int[] s0;
    public int t0;
    public long u0;
    public boolean v0;
    public int w0;
    public int x0;
    public boolean y0;
    public boolean z0 = true;

    public RunnableC2388eg(C2901hg hgVar, Bitmap.Config config) {
        this.F0 = config;
        if (I == null) {
            HandlerThreadC2218dg dgVar = new HandlerThreadC2218dg();
            I = dgVar;
            dgVar.start();
            f9870J = new Handler(I.getLooper(), I);
        }
        if (G == null) {
            G = new Paint(2);
            Paint paint = new Paint(2);
            H = paint;
            paint.setFilterBitmap(true);
        }
        this.K = hgVar;
        this.L = hgVar.b;
        this.M = hgVar.e;
        int i = hgVar.c;
        this.N = i;
        this.i0 = i;
        this.g0 = i;
        int i2 = hgVar.d;
        this.O = i2;
        this.h0 = i2;
        this.Y = hgVar.j;
        this.V = hgVar.g;
        if (!this.V) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(this.N, this.O, config);
                this.R = createBitmap;
                if (createBitmap != null) {
                    int i3 = this.N;
                    int i4 = this.O;
                    int i5 = i3 * i4;
                    this.S = new int[i5];
                    this.q0 = new byte[i5];
                    this.P = i4;
                    this.Q = i4;
                    Handler handler = f9870J;
                    handler.sendMessage(handler.obtainMessage(10, this));
                    return;
                }
                throw new OutOfMemoryError("Cannot allocate bitmap");
            } catch (OutOfMemoryError unused) {
                this.V = true;
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:221:0x0211 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:202:0x03e0 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [int, short] */
    /* JADX WARN: Type inference failed for: r3v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r17v3, types: [int] */
    /* JADX WARN: Type inference failed for: r6v31, types: [int] */
    /* JADX WARN: Type inference failed for: r4v33, types: [int] */
    /* JADX WARN: Type inference failed for: r9v31, types: [short[]] */
    /* JADX WARN: Type inference failed for: r0v39, types: [int[]] */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r3v75 */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02a5, code lost:
        if (r9 >= r0) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02a7, code lost:
        r28.q0[r9] = 0;
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x023c, code lost:
        r28.V = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0345  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0387 A[LOOP:14: B:180:0x0387->B:183:0x038f, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0341 A[EDGE_INSN: B:225:0x0341->B:159:0x0341 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(defpackage.RunnableC2388eg r28) {
        /*
        // Method dump skipped, instructions count: 1002
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.RunnableC2388eg.a(eg):void");
    }

    public final int b() {
        byte[] bArr = this.L;
        int i = this.M;
        int i2 = i + 1;
        this.M = i2;
        int i3 = bArr[i] & 255;
        if (i3 > 0) {
            System.arraycopy(bArr, i2, this.j0, 0, i3);
            this.M += i3;
        }
        return i3;
    }

    public final int c() {
        byte[] bArr = this.L;
        int i = this.M;
        int i2 = i + 1;
        this.M = i2;
        this.M = i2 + 1;
        return ((bArr[i2] & 255) << 8) | (bArr[i] & 255);
    }

    public final void d() {
        int i;
        do {
            byte[] bArr = this.L;
            int i2 = this.M;
            int i3 = i2 + 1;
            this.M = i3;
            i = bArr[i2] & 255;
            this.M = i3 + i;
        } while (i > 0);
    }

    public void draw(Canvas canvas) {
        if (!this.V && this.P != 0 && this.Q != 0 && this.B0) {
            if (this.T) {
                canvas.save();
                float f = this.U;
                canvas.scale(f, f, 0.0f, 0.0f);
                canvas.drawBitmap(this.R, 0.0f, 0.0f, H);
                canvas.restore();
            } else {
                canvas.drawBitmap(this.R, 0.0f, 0.0f, G);
            }
            if (this.v0) {
                if (!this.y0) {
                    long max = Math.max(this.u0 + ((long) this.w0), SystemClock.uptimeMillis() + 5);
                    this.u0 = max;
                    if (this.z0) {
                        super.scheduleSelf(this, max);
                        this.y0 = true;
                    }
                }
            } else if (!this.W) {
                start();
            } else {
                super.unscheduleSelf(this);
                this.v0 = false;
            }
        }
    }

    public int getIntrinsicHeight() {
        return this.O;
    }

    public int getIntrinsicWidth() {
        return this.N;
    }

    public int getOpacity() {
        return 0;
    }

    public boolean handleMessage(Message message) {
        if (message.what != 11) {
            return false;
        }
        this.w0 = message.arg1;
        Bitmap bitmap = this.R;
        if (bitmap != null) {
            int[] iArr = this.S;
            int i = this.N;
            bitmap.setPixels(iArr, 0, i, 0, 0, i, this.O);
            this.B0 = true;
            this.y0 = false;
            invalidateSelf();
        }
        return true;
    }

    public boolean isRunning() {
        return this.v0;
    }

    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.P = rect.width();
        int height = rect.height();
        this.Q = height;
        int i = this.P;
        int i2 = this.N;
        boolean z = (i == i2 || height == this.O) ? false : true;
        this.T = z;
        if (z) {
            this.U = Math.max(((float) i) / ((float) i2), ((float) height) / ((float) this.O));
        }
        if (!this.V) {
            Handler handler = f9870J;
            handler.sendMessage(handler.obtainMessage(12, this));
        }
    }

    public void run() {
        if (!this.W) {
            Handler handler = f9870J;
            handler.sendMessage(handler.obtainMessage(10, this));
        }
    }

    public void scheduleSelf(Runnable runnable, long j) {
        if (this.z0) {
            super.scheduleSelf(runnable, j);
            this.y0 = true;
        }
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            stop();
        } else if (visible || z2) {
            start();
        }
        return visible;
    }

    public void start() {
        if (!this.v0) {
            this.v0 = true;
            if (!this.X) {
                this.W = true;
            }
            this.u0 = SystemClock.uptimeMillis();
            run();
        }
    }

    public void stop() {
        if (this.v0) {
            super.unscheduleSelf(this);
            this.v0 = false;
        }
    }

    public void unscheduleSelf(Runnable runnable) {
        super.unscheduleSelf(runnable);
        this.v0 = false;
    }
}
