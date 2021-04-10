package X;

import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import java.util.List;
import java.util.Map;

@RequiresApi(21)
/* renamed from: X.3q  reason: invalid class name */
public class AnonymousClass3q extends SharedElementCallback {
    public final AbstractC00124w A00;

    public final View onCreateSnapshotView(Context context, Parcelable parcelable) {
        ImageView imageView = null;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Bitmap bitmap = (Bitmap) bundle.getParcelable("sharedElement:snapshot:bitmap");
            if (bitmap != null) {
                imageView = new ImageView(context);
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
                if (imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
                    float[] floatArray = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
                    Matrix matrix = new Matrix();
                    matrix.setValues(floatArray);
                    imageView.setImageMatrix(matrix);
                }
            }
        } else if (parcelable instanceof Bitmap) {
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageBitmap((Bitmap) parcelable);
            return imageView2;
        }
        return imageView;
    }

    @Override // android.app.SharedElementCallback
    @RequiresApi(23)
    public final void onSharedElementsArrived(List<String> list, List<View> list2, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        new U9(this, onSharedElementsReadyListener).A2e();
    }

    public AnonymousClass3q(AbstractC00124w r1) {
        this.A00 = r1;
    }

    @Override // android.app.SharedElementCallback
    public final void onMapSharedElements(List<String> list, Map<String, View> map) {
    }

    @Override // android.app.SharedElementCallback
    public final void onRejectSharedElements(List<View> list) {
    }

    public final Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
        throw null;
    }

    @Override // android.app.SharedElementCallback
    public final void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
    }

    @Override // android.app.SharedElementCallback
    public final void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
    }
}
