package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import com.oculus.browser.R;

/* renamed from: dm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2236dm extends View {
    public final int F = getResources().getDimensionPixelSize(R.dimen.f23550_resource_name_obfuscated_RES_2131165974);
    public final int G = getResources().getDimensionPixelSize(R.dimen.f23470_resource_name_obfuscated_RES_2131165966);
    public final Paint H;
    public final Paint I;

    /* renamed from: J  reason: collision with root package name */
    public final TextPaint f9804J;
    public final int K = getResources().getDimensionPixelSize(R.dimen.f23560_resource_name_obfuscated_RES_2131165975);
    public final StaticLayout L;
    public Rect M;

    public C2236dm(Context context) {
        super(context);
        a();
        Paint paint = new Paint();
        this.I = paint;
        paint.setColor(getResources().getColor(R.color.f14480_resource_name_obfuscated_RES_2131100138));
        Paint paint2 = new Paint();
        this.H = paint2;
        paint2.setColor(getResources().getColor(17170443));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth((float) getResources().getDimensionPixelSize(R.dimen.f23480_resource_name_obfuscated_RES_2131165967));
        TextPaint textPaint = new TextPaint();
        this.f9804J = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setColor(getResources().getColor(17170443));
        textPaint.setTextSize((float) getResources().getDimensionPixelSize(R.dimen.f25940_resource_name_obfuscated_RES_2131166213));
        this.L = new StaticLayout(getResources().getString(R.string.f59650_resource_name_obfuscated_RES_2131953282), textPaint, this.M.width(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
    }

    public final void a() {
        int width = getWidth();
        int height = getHeight();
        int i = this.F;
        this.M = new Rect((width - i) / 2, (height - i) / 2, ((width - i) / 2) + i, ((height - i) / 2) + i);
    }

    public void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float f = (float) width;
        canvas.drawRect(0.0f, 0.0f, f, (float) this.M.top, this.I);
        Rect rect = this.M;
        canvas.drawRect(0.0f, (float) rect.top, (float) rect.left, (float) (rect.bottom + 1), this.I);
        Rect rect2 = this.M;
        canvas.drawRect((float) (rect2.right + 1), (float) rect2.top, f, (float) (rect2.bottom + 1), this.I);
        canvas.drawRect(0.0f, (float) (this.M.bottom + 1), f, (float) height, this.I);
        Path path = new Path();
        Rect rect3 = this.M;
        path.moveTo((float) rect3.left, (float) (rect3.top + this.G));
        Rect rect4 = this.M;
        path.lineTo((float) rect4.left, (float) rect4.top);
        Rect rect5 = this.M;
        path.lineTo((float) (rect5.left + this.G), (float) rect5.top);
        canvas.drawPath(path, this.H);
        Rect rect6 = this.M;
        path.moveTo((float) rect6.right, (float) (rect6.top + this.G));
        Rect rect7 = this.M;
        path.lineTo((float) rect7.right, (float) rect7.top);
        Rect rect8 = this.M;
        path.lineTo((float) (rect8.right - this.G), (float) rect8.top);
        canvas.drawPath(path, this.H);
        Rect rect9 = this.M;
        path.moveTo((float) rect9.right, (float) (rect9.bottom - this.G));
        Rect rect10 = this.M;
        path.lineTo((float) rect10.right, (float) rect10.bottom);
        Rect rect11 = this.M;
        path.lineTo((float) (rect11.right - this.G), (float) rect11.bottom);
        canvas.drawPath(path, this.H);
        Rect rect12 = this.M;
        path.moveTo((float) rect12.left, (float) (rect12.bottom - this.G));
        Rect rect13 = this.M;
        path.lineTo((float) rect13.left, (float) rect13.bottom);
        Rect rect14 = this.M;
        path.lineTo((float) (rect14.left + this.G), (float) rect14.bottom);
        canvas.drawPath(path, this.H);
        canvas.save();
        Rect rect15 = this.M;
        canvas.translate((float) rect15.left, (float) (rect15.bottom + this.K));
        this.L.draw(canvas);
        canvas.restore();
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        a();
    }
}
