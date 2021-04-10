package X;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.io.OutputStream;
import javax.annotation.Nullable;

/* renamed from: X.1i9  reason: invalid class name */
public final class AnonymousClass1i9 implements AbstractC01070Pt {
    public final int A00;
    public final boolean A01;

    @Override // X.AbstractC01070Pt
    public final String getIdentifier() {
        return "SimpleImageTranscoder";
    }

    @Override // X.AbstractC01070Pt
    public final boolean canResize(AnonymousClass0PZ r3, @Nullable AnonymousClass0PO r4, @Nullable AnonymousClass0PL r5) {
        if (!this.A01 || AnonymousClass0Pr.A00(r5, r3, this.A00) <= 1) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC01070Pt
    public final boolean canTranscode(AnonymousClass0Oj r3) {
        if (r3 == AnonymousClass0Oi.A03 || r3 == AnonymousClass0Oi.A05) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01070Pt
    public final AnonymousClass0Ps transcode(AnonymousClass0PZ r17, OutputStream outputStream, @Nullable AnonymousClass0PO r19, @Nullable AnonymousClass0PL r20, @Nullable AnonymousClass0Oj r21, @Nullable Integer num) {
        int A002;
        Bitmap bitmap;
        Throwable th;
        AnonymousClass0Ps r0;
        OutOfMemoryError e;
        Bitmap.CompressFormat compressFormat;
        float f;
        AnonymousClass0PO r6 = r19;
        if (num == null) {
            num = 85;
        }
        if (r19 == null) {
            r6 = AnonymousClass0PO.A02;
        }
        if (!this.A01) {
            A002 = 1;
        } else {
            A002 = AnonymousClass0Pr.A00(r20, r17, this.A00);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = A002;
        try {
            Matrix matrix = null;
            Bitmap decodeStream = BitmapFactory.decodeStream(r17.A0A(), null, options);
            if (decodeStream == null) {
                AnonymousClass0J6 r1 = AnonymousClass0J5.A00;
                if (r1.A64(6)) {
                    r1.A2h("SimpleImageTranscoder", "Couldn't decode the EncodedImage InputStream ! ");
                }
                return new AnonymousClass0Ps(2);
            }
            C00680Ic<Integer> r12 = C01090Pv.A00;
            AnonymousClass0PZ.A06(r17);
            if (r12.contains(Integer.valueOf(r17.A00))) {
                int A012 = C01090Pv.A01(r6, r17);
                Matrix matrix2 = new Matrix();
                if (A012 != 2) {
                    if (A012 == 7) {
                        f = -90.0f;
                    } else if (A012 == 4) {
                        f = 180.0f;
                    } else if (A012 == 5) {
                        f = 90.0f;
                    }
                    matrix2.setRotate(f);
                    matrix2.postScale(-1.0f, 1.0f);
                } else {
                    matrix2.setScale(-1.0f, 1.0f);
                }
                matrix = matrix2;
            } else {
                int A02 = C01090Pv.A02(r6, r17);
                if (A02 != 0) {
                    matrix = new Matrix();
                    matrix.setRotate((float) A02);
                }
            }
            if (matrix != null) {
                try {
                    bitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, false);
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    bitmap = decodeStream;
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = decodeStream;
                    bitmap.recycle();
                    decodeStream.recycle();
                    throw th;
                }
            } else {
                bitmap = decodeStream;
            }
            if (r21 == null) {
                compressFormat = Bitmap.CompressFormat.JPEG;
            } else {
                try {
                    if (r21 == AnonymousClass0Oi.A05) {
                        compressFormat = Bitmap.CompressFormat.JPEG;
                    } else if (r21 == AnonymousClass0Oi.A06) {
                        compressFormat = Bitmap.CompressFormat.PNG;
                    } else if (AnonymousClass0Oi.A00(r21)) {
                        compressFormat = Bitmap.CompressFormat.WEBP;
                    } else {
                        compressFormat = Bitmap.CompressFormat.JPEG;
                    }
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    try {
                        AnonymousClass0J5.A04("SimpleImageTranscoder", "Out-Of-Memory during transcode", e);
                        r0 = new AnonymousClass0Ps(2);
                        bitmap.recycle();
                        decodeStream.recycle();
                        return r0;
                    } catch (Throwable th3) {
                        th = th3;
                        bitmap.recycle();
                        decodeStream.recycle();
                        throw th;
                    }
                }
            }
            bitmap.compress(compressFormat, num.intValue(), outputStream);
            int i = 1;
            if (A002 > 1) {
                i = 0;
            }
            r0 = new AnonymousClass0Ps(i);
            bitmap.recycle();
            decodeStream.recycle();
            return r0;
        } catch (OutOfMemoryError e4) {
            AnonymousClass0J5.A04("SimpleImageTranscoder", "Out-Of-Memory during transcode", e4);
            return new AnonymousClass0Ps(2);
        }
    }

    public AnonymousClass1i9(boolean z, int i) {
        this.A01 = z;
        this.A00 = i;
    }
}
