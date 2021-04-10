package X;

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.datasource.DataSource;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import javax.annotation.Nonnull;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1ro  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC10251ro<T> implements AnonymousClass1tB<T> {
    @Override // X.AnonymousClass1tB
    public final void A69(@Nonnull DataSource<T> dataSource) {
        try {
            NotificationBuilder.AnonymousClass1 r4 = (NotificationBuilder.AnonymousClass1) this;
            ((NotificationManager) AnonymousClass0J2.A03(1, 39, NotificationBuilder.this._UL_mInjectionContext)).notify(r22, r23, r24.A01());
            AnonymousClass0NO.A08(NotificationBuilder.TAG, "Failed to load picture for notification");
        } finally {
            dataSource.A04();
        }
    }

    @Override // X.AnonymousClass1tB
    public final void A6K(@Nonnull DataSource<T> dataSource) {
        int width;
        int i;
        boolean A05 = dataSource.A05();
        try {
            AnonymousClass1qJ r5 = (AnonymousClass1qJ) this;
            if (dataSource.A05()) {
                AnonymousClass1qa r4 = (AnonymousClass1qa) dataSource.A02();
                Bitmap bitmap = null;
                if (r4 != null && (r4.A04() instanceof AnonymousClass1t1)) {
                    bitmap = ((AnonymousClass1qH) ((AnonymousClass1t1) r4.A04())).A04;
                }
                try {
                    NotificationBuilder.AnonymousClass1 r52 = (NotificationBuilder.AnonymousClass1) r5;
                    if (bitmap != null) {
                        AnonymousClass03h r7 = r24;
                        if (r26) {
                            if (((float) bitmap.getWidth()) / ((float) bitmap.getHeight()) > 1.0f) {
                                i = bitmap.getHeight();
                                width = (int) (((float) i) * 1.0f);
                            } else {
                                width = bitmap.getWidth();
                                i = (int) (((float) width) / 1.0f);
                            }
                            float f = ((float) width) / ((float) i);
                            float width2 = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
                            Matrix matrix = new Matrix();
                            if (width2 > f) {
                                matrix.setTranslate((float) ((-(bitmap.getWidth() - ((int) (((float) bitmap.getHeight()) * f)))) >> 1), 0.0f);
                            } else {
                                matrix.setTranslate(0.0f, (float) ((-(bitmap.getHeight() - ((int) (((float) bitmap.getWidth()) / f)))) >> 1));
                            }
                            Paint paint = new Paint();
                            paint.setFilterBitmap(true);
                            Bitmap.Config config = Bitmap.Config.ARGB_8888;
                            Bitmap createBitmap = Bitmap.createBitmap(width, i, config);
                            new Canvas(createBitmap).drawBitmap(bitmap, matrix, paint);
                            bitmap = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getWidth(), config);
                            Paint paint2 = new Paint();
                            paint2.setAntiAlias(true);
                            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                            paint2.setShader(new BitmapShader(createBitmap, tileMode, tileMode));
                            RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
                            Path path = new Path();
                            path.addOval(rectF, Path.Direction.CW);
                            new Canvas(bitmap).drawPath(path, paint2);
                            createBitmap.recycle();
                        }
                        r7.A04(bitmap);
                    }
                    ((NotificationManager) AnonymousClass0J2.A03(1, 39, NotificationBuilder.this._UL_mInjectionContext)).notify(r22, r23, r24.A01());
                } finally {
                    if (r4 != null) {
                        r4.close();
                    }
                }
            }
        } finally {
            if (A05) {
                dataSource.A04();
            }
        }
    }
}
