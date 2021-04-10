package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.oculus.browser.R;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: wQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5422wQ0 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11545a;
    public final String b;
    public final Resources c;

    public C5422wQ0(Callback callback, String str, Resources resources) {
        this.f11545a = callback;
        this.b = str;
        this.c = resources;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        int i;
        Callback callback = this.f11545a;
        String str2 = this.b;
        Resources resources = this.c;
        if (bitmap == null) {
            callback.onResult(bitmap);
            AbstractC5762yQ0.f(3);
            return;
        }
        int b2 = AbstractC5762yQ0.b(resources);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, AbstractC5762yQ0.b(resources), AbstractC5762yQ0.b(resources), true);
        if (AbstractC5762yQ0.d()) {
            if (AbstractC5762yQ0.f == 0) {
                AbstractC5762yQ0.f = resources.getDimensionPixelSize(R.dimen.f23160_resource_name_obfuscated_RES_2131165935);
            }
            int i2 = AbstractC5762yQ0.f;
            if (AbstractC5762yQ0.d == null) {
                AbstractC5762yQ0.d = new KN0(i2, i2, i2, 0, 0.0f);
            }
            int i3 = 0;
            if (!(bitmap.getWidth() == 0 || bitmap.getHeight() == 0)) {
                HashMap hashMap = new HashMap();
                int i4 = 0;
                while (true) {
                    i = -1;
                    if (i4 >= bitmap.getWidth()) {
                        break;
                    }
                    int pixel = bitmap.getPixel(i4, 0);
                    if (!hashMap.containsKey(Integer.valueOf(pixel))) {
                        hashMap.put(Integer.valueOf(pixel), 0);
                    }
                    hashMap.put(Integer.valueOf(pixel), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(pixel))).intValue() + 1));
                    int pixel2 = bitmap.getPixel(i4, bitmap.getHeight() - 1);
                    if (!hashMap.containsKey(Integer.valueOf(pixel2))) {
                        hashMap.put(Integer.valueOf(pixel2), 0);
                    }
                    hashMap.put(Integer.valueOf(pixel2), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(pixel2))).intValue() + 1));
                    if (i4 > 0 && i4 < bitmap.getWidth() - 1) {
                        int pixel3 = bitmap.getPixel(0, i4);
                        if (!hashMap.containsKey(Integer.valueOf(pixel3))) {
                            hashMap.put(Integer.valueOf(pixel3), 0);
                        }
                        hashMap.put(Integer.valueOf(pixel3), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(pixel3))).intValue() + 1));
                        int pixel4 = bitmap.getPixel(bitmap.getWidth() - 1, i4);
                        if (!hashMap.containsKey(Integer.valueOf(pixel4))) {
                            hashMap.put(Integer.valueOf(pixel4), 0);
                        }
                        hashMap.put(Integer.valueOf(pixel4), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(pixel4))).intValue() + 1));
                    }
                    i4++;
                }
                for (Integer num : hashMap.keySet()) {
                    int intValue = num.intValue();
                    int intValue2 = ((Integer) hashMap.get(Integer.valueOf(intValue))).intValue();
                    if (intValue2 > i) {
                        i3 = intValue;
                        i = intValue2;
                    }
                }
            }
            AbstractC5762yQ0.d.e.setColor(i3);
            Bitmap a2 = AbstractC5762yQ0.d.a("");
            float f = (float) ((i2 - b2) / 2);
            new Canvas(a2).drawBitmap(createScaledBitmap, f, f, (Paint) null);
            createScaledBitmap = a2;
        }
        AbstractC5762yQ0.f11679a = createScaledBitmap;
        AbstractC5762yQ0.b = str2;
        callback.onResult(createScaledBitmap);
        AbstractC5762yQ0.f(5);
    }
}
