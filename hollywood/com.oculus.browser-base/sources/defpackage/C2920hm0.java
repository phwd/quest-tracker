package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: hm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2920hm0 implements H91 {

    /* renamed from: a  reason: collision with root package name */
    public final TabContentManager f10099a;
    public final AbstractC0124Ca1 b;
    public final AbstractC0612Ka1 c;
    public final float d;
    public final float e;
    public final int f;
    public final int g;
    public final Paint h;
    public final Paint i;
    public final Paint j;
    public final Paint k;
    public final int l;
    public final List m = new ArrayList(4);
    public final List n;
    public final List o;
    public C2307e91 p;

    public C2920hm0(Context context, TabContentManager tabContentManager, AbstractC0124Ca1 ca1) {
        ArrayList arrayList = new ArrayList(4);
        this.n = arrayList;
        this.o = new ArrayList(4);
        Resources resources = context.getResources();
        float b2 = AbstractC4089od0.b((float) AbstractC4772sd1.c.c(), 0.5f, 2.0f);
        int dimension = (int) resources.getDimension(R.dimen.f25500_resource_name_obfuscated_RES_2131166169);
        this.f = dimension;
        int i2 = (int) (((float) dimension) / b2);
        this.g = i2;
        this.f10099a = tabContentManager;
        this.b = ca1;
        this.d = resources.getDimension(R.dimen.f25640_resource_name_obfuscated_RES_2131166183);
        this.e = resources.getDimension(R.dimen.f25520_resource_name_obfuscated_RES_2131166171);
        this.p = new C2307e91(context, false);
        Paint paint = new Paint();
        this.h = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(resources.getColor(R.color.f15260_resource_name_obfuscated_RES_2131100216));
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.i = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(resources.getDimension(R.dimen.f25630_resource_name_obfuscated_RES_2131166182));
        paint2.setColor(resources.getColor(R.color.f12150_resource_name_obfuscated_RES_2131099905));
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.j = paint3;
        paint3.setTextSize(resources.getDimension(R.dimen.f17480_resource_name_obfuscated_RES_2131165367));
        paint3.setFakeBoldText(true);
        paint3.setAntiAlias(true);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setColor(resources.getColor(R.color.f11450_resource_name_obfuscated_RES_2131099835));
        int color = resources.getColor(R.color.f12250_resource_name_obfuscated_RES_2131099915);
        this.l = color;
        Paint paint4 = new Paint();
        this.k = paint4;
        paint4.setAntiAlias(true);
        paint4.setColor(color);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setShadowLayer(resources.getDimension(R.dimen.f25530_resource_name_obfuscated_RES_2131166172), 0.0f, resources.getDimension(R.dimen.f25510_resource_name_obfuscated_RES_2131166170), resources.getColor(R.color.f13550_resource_name_obfuscated_RES_2131100045));
        float dimension2 = resources.getDimension(R.dimen.f25610_resource_name_obfuscated_RES_2131166180);
        float f2 = dimension2 / b2;
        float dimension3 = resources.getDimension(R.dimen.f25540_resource_name_obfuscated_RES_2131166173);
        float dimension4 = resources.getDimension(R.dimen.f25550_resource_name_obfuscated_RES_2131166174);
        float f3 = ((float) dimension) * 0.5f;
        float f4 = ((float) i2) * 0.5f;
        float f5 = dimension2 / 2.0f;
        float f6 = f2 / 2.0f;
        float f7 = f3 - f5;
        float f8 = f4 - f6;
        arrayList.add(new RectF(dimension2, f2, f7, f8));
        float f9 = f3 + f5;
        arrayList.add(new RectF(f9, f2, ((float) dimension) - dimension2, f8));
        float f10 = f4 + f6;
        arrayList.add(new RectF(dimension2, f10, f7, ((float) i2) - f2));
        arrayList.add(new RectF(f9, f10, ((float) dimension) - dimension2, ((float) i2) - f2));
        float width = (((RectF) arrayList.get(0)).width() / 2.0f) - dimension3;
        for (int i3 = 0; i3 < 4; i3++) {
            RectF rectF = (RectF) this.n.get(i3);
            float centerX = rectF.centerX();
            float centerY = rectF.centerY();
            RectF rectF2 = new RectF(centerX, centerY, centerX, centerY);
            float f11 = -width;
            rectF2.inset(f11, f11);
            this.o.add(rectF2);
            RectF rectF3 = new RectF(rectF2);
            rectF3.inset(dimension4, dimension4);
            Rect rect = new Rect();
            rectF3.roundOut(rect);
            this.m.add(rect);
        }
        C2237dm0 dm0 = new C2237dm0(this, context);
        this.c = dm0;
        ((AbstractC0246Ea1) this.b).c(dm0);
    }

    @Override // defpackage.H91
    public void a(int i2, Callback callback, boolean z, boolean z2) {
        C4384qI0 b2 = C4384qI0.b(i2);
        if (C4384qI0.e(b2, this.b).size() == 1) {
            this.f10099a.f(i2, callback, z, z2);
            return;
        }
        C2749gm0 gm0 = new C2749gm0(this, b2, callback, z, z2);
        C4384qI0 qi0 = gm0.f10018a;
        gm0.h = Bitmap.createBitmap(this.f, this.g, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(gm0.h);
        gm0.g = canvas;
        int i3 = 0;
        canvas.drawColor(0);
        List e2 = C4384qI0.e(qi0, this.b);
        if (e2.size() <= 4) {
            gm0.f.set(e2.size());
            gm0.e.add(qi0);
            e2.remove(qi0);
            int i4 = 0;
            while (i4 < 3) {
                gm0.e.add(i4 < e2.size() ? (C4384qI0) e2.get(i4) : null);
                i4++;
            }
        } else {
            StringBuilder i5 = AbstractC2531fV.i("+");
            i5.append(e2.size() - 3);
            gm0.i = i5.toString();
            gm0.f.set(3);
            gm0.e.add(qi0);
            e2.remove(qi0);
            gm0.e.add((C4384qI0) e2.get(0));
            gm0.e.add((C4384qI0) e2.get(1));
            gm0.e.add(null);
        }
        while (i3 < 4) {
            if (gm0.e.get(i3) != null) {
                gm0.j.f10099a.f(((C4384qI0) gm0.e.get(i3)).c(), new C2407em0(gm0, i3, new AtomicReference(), ((C4384qI0) gm0.e.get(i3)).k(), ((C4384qI0) gm0.e.get(i3)).m()), gm0.c && i3 == 0, gm0.d && i3 == 0);
            } else {
                gm0.b(null, i3);
                String str = gm0.i;
                if (str != null && i3 == 3) {
                    gm0.g.drawText(str, (((RectF) gm0.j.n.get(i3)).left + ((RectF) gm0.j.n.get(i3)).right) / 2.0f, ((((RectF) gm0.j.n.get(i3)).top + ((RectF) gm0.j.n.get(i3)).bottom) / 2.0f) - ((gm0.j.j.ascent() + gm0.j.j.descent()) / 2.0f), gm0.j.j);
                }
            }
            i3++;
        }
    }
}
