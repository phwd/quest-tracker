package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.TextPaint;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: qJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4387qJ0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4898tJ0 f11133a;

    public C4387qJ0(C4898tJ0 tj0) {
        this.f11133a = tj0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4898tJ0 tj0 = this.f11133a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Objects.requireNonNull(tj0);
        if (booleanValue) {
            tj0.a();
            Bitmap bitmap = (Bitmap) tj0.b.g(AbstractC5578xJ0.f11604a);
            String string = tj0.f11337a.getString(R.string.f59700_resource_name_obfuscated_RES_2131953287, String.valueOf(System.currentTimeMillis()));
            tj0.e = true;
            String str = tj0.f;
            int dimensionPixelSize = tj0.f11337a.getResources().getDimensionPixelSize(R.dimen.f24560_resource_name_obfuscated_RES_2131166075);
            int dimensionPixelSize2 = tj0.f11337a.getResources().getDimensionPixelSize(R.dimen.f25940_resource_name_obfuscated_RES_2131166213);
            int dimensionPixelSize3 = tj0.f11337a.getResources().getDimensionPixelSize(R.dimen.f25050_resource_name_obfuscated_RES_2131166124);
            int dimensionPixelSize4 = tj0.f11337a.getResources().getDimensionPixelSize(R.dimen.f26660_resource_name_obfuscated_RES_2131166285);
            int dimensionPixelSize5 = tj0.f11337a.getResources().getDimensionPixelSize(R.dimen.f26650_resource_name_obfuscated_RES_2131166284);
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setColor(-16777216);
            textPaint.setTextSize((float) dimensionPixelSize2);
            C4728sJ0 sj0 = new C4728sJ0(tj0, str, textPaint, dimensionPixelSize, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true, 2);
            Bitmap createBitmap = Bitmap.createBitmap((dimensionPixelSize3 * 2) + dimensionPixelSize, ((sj0.getHeight() + dimensionPixelSize4 + dimensionPixelSize5) * 2) + dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawColor(-1);
            canvas.translate((float) dimensionPixelSize3, (float) dimensionPixelSize4);
            sj0.draw(canvas);
            canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, dimensionPixelSize, dimensionPixelSize, false), 0.0f, (float) (sj0.getHeight() + dimensionPixelSize5), textPaint);
            N.MTm9IWhH(string, createBitmap);
            tj0.g.run();
        }
    }
}
