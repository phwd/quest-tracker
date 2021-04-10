package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClosableTabGridView extends ViewLookupCachingFrameLayout {
    public static WeakReference I;

    public ClosableTabGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) d(R.id.action_button);
        WeakReference weakReference = I;
        if (weakReference == null || weakReference.get() == null) {
            int dimension = (int) getResources().getDimension(R.dimen.f25380_resource_name_obfuscated_RES_2131166157);
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.f28430_resource_name_obfuscated_RES_2131230883);
            I = new WeakReference(Bitmap.createScaledBitmap(decodeResource, dimension, dimension, true));
            decodeResource.recycle();
        }
        imageView.setImageBitmap((Bitmap) I.get());
    }
}
