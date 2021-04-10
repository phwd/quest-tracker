package defpackage;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: sU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4750sU0 implements LargeIconBridge$LargeIconCallback {
    public final C5090uU0 F;

    public C4750sU0(C5090uU0 uu0) {
        this.F = uu0;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        C5090uU0 uu0 = this.F;
        if (bitmap == null) {
            uu0.B(AbstractC5544x8.a(uu0.F, R.drawable.f29320_resource_name_obfuscated_RES_2131230972));
            AbstractC3535lK0.a("SharingHubAndroid.GenericFaviconShown");
            return;
        }
        int dimensionPixelSize = uu0.F.getResources().getDimensionPixelSize(R.dimen.f25030_resource_name_obfuscated_RES_2131166122);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, dimensionPixelSize, dimensionPixelSize, true);
        ImageView imageView = (ImageView) uu0.I.findViewById(R.id.image_preview);
        imageView.setImageBitmap(createScaledBitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int dimensionPixelSize2 = uu0.F.getResources().getDimensionPixelSize(R.dimen.f25000_resource_name_obfuscated_RES_2131166119);
        imageView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
        AbstractC3535lK0.a("SharingHubAndroid.LinkFaviconShown");
    }
}
