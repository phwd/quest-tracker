package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: pQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4230pQ extends View {
    public static Comparator F = new C3717mQ();
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f11064J;
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public final int R;
    public final int S;
    public C3546lQ T;
    public final WindowAndroid U;
    public int V = -1;
    public RectF[] W = new RectF[0];
    public RectF a0;
    public ArrayList b0 = new ArrayList(0);
    public int c0 = -1;
    public Animator d0;
    public boolean e0;
    public final Paint f0;
    public final Paint g0;
    public boolean h0;

    public C4230pQ(Context context, FrameLayout frameLayout, WindowAndroid windowAndroid, C3546lQ lQVar) {
        super(context);
        Resources resources = context.getResources();
        this.G = resources.getColor(R.color.f12490_resource_name_obfuscated_RES_2131099939);
        this.H = resources.getColor(R.color.f12480_resource_name_obfuscated_RES_2131099938);
        this.I = resources.getColor(R.color.f12530_resource_name_obfuscated_RES_2131099943);
        this.f11064J = resources.getColor(R.color.f12520_resource_name_obfuscated_RES_2131099942);
        this.K = resources.getColor(R.color.f12470_resource_name_obfuscated_RES_2131099937);
        this.L = resources.getColor(R.color.f12460_resource_name_obfuscated_RES_2131099936);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f19530_resource_name_obfuscated_RES_2131165572);
        this.M = dimensionPixelSize;
        this.N = resources.getDimensionPixelSize(R.dimen.f19510_resource_name_obfuscated_RES_2131165570) + resources.getDimensionPixelSize(R.dimen.f19480_resource_name_obfuscated_RES_2131165567);
        this.O = resources.getDimensionPixelSize(R.dimen.f19500_resource_name_obfuscated_RES_2131165569);
        this.P = resources.getDimensionPixelSize(R.dimen.f19470_resource_name_obfuscated_RES_2131165566);
        this.Q = resources.getDimensionPixelSize(R.dimen.f19540_resource_name_obfuscated_RES_2131165573);
        this.R = resources.getDimensionPixelSize(R.dimen.f19490_resource_name_obfuscated_RES_2131165568);
        this.S = resources.getDimensionPixelSize(R.dimen.f19520_resource_name_obfuscated_RES_2131165571);
        Paint paint = new Paint();
        this.f0 = paint;
        Paint paint2 = new Paint();
        this.g0 = paint2;
        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(1.0f);
        this.T = lQVar;
        frameLayout.addView(this, new FrameLayout.LayoutParams(dimensionPixelSize, -1, 8388613));
        setTranslationX((float) (LocalizationUtils.isLayoutRtl() ? -dimensionPixelSize : dimensionPixelSize));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0.0f);
        this.d0 = ofFloat;
        ofFloat.setDuration(200L);
        this.d0.setInterpolator(animation.InterpolatorC5286vf.g);
        this.U = windowAndroid;
        if (windowAndroid != null) {
            windowAndroid.I0(this.d0);
            return;
        }
        throw new IllegalArgumentException("WindowAndroid must be non null.");
    }

    public final C4059oQ a(C4059oQ oQVar, boolean z) {
        int i = z ? this.P : this.O;
        float f = oQVar.G;
        float f2 = oQVar.F;
        float f3 = ((float) i) - (f - f2);
        if (f3 <= 0.0f) {
            return oQVar;
        }
        float f4 = f3 / 2.0f;
        return new C4059oQ(this, f2 - f4, f + f4);
    }

    public void b(int i, RectF[] rectFArr, RectF rectF) {
        if (this.V != i) {
            this.V = i;
            this.W = rectFArr;
            this.b0.clear();
            Arrays.sort(this.W, F);
            this.c0 = -1;
        }
        this.a0 = rectF;
        invalidate();
    }

    public final C4059oQ c(RectF rectF, boolean z) {
        int i = this.c0;
        int i2 = this.Q;
        float f = (float) (i - (i2 * 2));
        return a(new C4059oQ(this, (rectF.top * f) + ((float) i2), (rectF.bottom * f) + ((float) i2)), z);
    }

    public void onDraw(Canvas canvas) {
        int binarySearch;
        float f;
        super.onDraw(canvas);
        boolean z = false;
        int width = LocalizationUtils.isLayoutRtl() ? 0 : getWidth() - this.N;
        this.f0.setColor(this.G);
        this.g0.setColor(this.H);
        float f2 = (float) width;
        canvas.drawRect(f2, 0.0f, (float) (this.N + width), (float) getHeight(), this.f0);
        float f3 = LocalizationUtils.isLayoutRtl() ? ((float) (width + this.N)) - 0.5f : f2 + 0.5f;
        canvas.drawLine(f3, 0.0f, f3, (float) getHeight(), this.g0);
        if (this.W.length != 0) {
            if (this.c0 != getHeight()) {
                this.c0 = getHeight();
                this.b0 = new ArrayList(this.W.length);
                C4059oQ c = c(this.W[0], false);
                float f4 = (float) (-this.R);
                int i = 0;
                while (i < this.W.length) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(c);
                    while (true) {
                        i++;
                        RectF[] rectFArr = this.W;
                        if (i >= rectFArr.length) {
                            break;
                        }
                        c = c(rectFArr[i], z);
                        if (c.F > ((C4059oQ) arrayList.get(arrayList.size() - 1)).G + ((float) this.R)) {
                            break;
                        }
                        arrayList.add(c);
                    }
                    int size = arrayList.size();
                    float f5 = f4 + ((float) this.R);
                    int i2 = size - 1;
                    float f6 = ((C4059oQ) arrayList.get(i2)).G;
                    float f7 = (f6 - ((float) (this.S * i2))) - ((float) this.O);
                    int i3 = z ? 1 : 0;
                    int i4 = z ? 1 : 0;
                    int i5 = z ? 1 : 0;
                    int i6 = z ? 1 : 0;
                    float round = (float) Math.round(AbstractC4089od0.b(f7, f5, ((C4059oQ) arrayList.get(i3)).F));
                    float f8 = round >= f7 ? 1.0f : (f6 - round) / (f6 - f7);
                    if (size == 1) {
                        f = 0.0f;
                    } else {
                        f = ((f6 - round) - (((float) this.O) * f8)) / ((float) i2);
                    }
                    int i7 = z ? 1 : 0;
                    Object[] objArr = z ? 1 : 0;
                    Object[] objArr2 = z ? 1 : 0;
                    Object[] objArr3 = z ? 1 : 0;
                    int i8 = i7;
                    while (i8 < size) {
                        C4059oQ oQVar = (C4059oQ) arrayList.get(i8);
                        float f9 = (((float) i8) * f) + round;
                        oQVar.F = f9;
                        if (i8 != i2) {
                            oQVar.G = (((float) this.O) * f8) + f9;
                        }
                        this.b0.add(oQVar);
                        i8++;
                        z = false;
                    }
                    f4 = f6;
                }
            }
            this.f0.setColor(this.I);
            this.g0.setColor(this.f11064J);
            Iterator it = this.b0.iterator();
            while (it.hasNext()) {
                RectF b = ((C4059oQ) it.next()).b();
                canvas.drawRoundRect(b, 2.0f, 2.0f, this.f0);
                canvas.drawRoundRect(b, 2.0f, 2.0f, this.g0);
            }
            RectF rectF = this.a0;
            if (rectF != null && !rectF.isEmpty() && (binarySearch = Arrays.binarySearch(this.W, this.a0, F)) >= 0) {
                RectF b2 = a((C4059oQ) this.b0.get(binarySearch), true).b();
                this.f0.setColor(this.K);
                this.g0.setColor(this.L);
                canvas.drawRoundRect(b2, 2.0f, 2.0f, this.f0);
                canvas.drawRoundRect(b2, 2.0f, 2.0f, this.g0);
            }
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!this.e0 && this.W.length > 0) {
            C3546lQ lQVar = this.T;
            N.M4m8QCn$(lQVar.b, lQVar, this.V);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.e0 && this.b0.size() > 0 && this.b0.size() == this.W.length && !this.h0 && motionEvent.getAction() != 3) {
            C3493l60.F.d(this);
            int binarySearch = Collections.binarySearch(this.b0, new C4059oQ(this, motionEvent.getY(), motionEvent.getY()));
            int i = 0;
            if (binarySearch < 0) {
                int i2 = -1 - binarySearch;
                if (i2 == 0) {
                    binarySearch = 0;
                } else if (i2 == this.b0.size()) {
                    binarySearch = this.b0.size() - 1;
                } else {
                    if (Math.abs(motionEvent.getY() - ((C4059oQ) this.b0.get(i2 - 1)).a()) <= Math.abs(motionEvent.getY() - ((C4059oQ) this.b0.get(i2)).a())) {
                        i = 1;
                    }
                    binarySearch = i2 - i;
                }
            }
            this.h0 = true;
            C3546lQ lQVar = this.T;
            N.MqpwqIAC(lQVar.b, lQVar, this.W[binarySearch].centerX(), this.W[binarySearch].centerY());
        }
        return true;
    }
}
