package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.url.GURL;

/* renamed from: QD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QD0 implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public GestureDetector f8745a;
    public ScaleGestureDetector b;
    public boolean c;
    public RD0 d;
    public QD0 e;
    public float f;
    public float g;

    public QD0(Context context, boolean z, RD0 rd0) {
        this.f8745a = new GestureDetector(context, this);
        this.b = new ScaleGestureDetector(context, this);
        this.c = z;
        this.d = rd0;
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.c) {
            this.b.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            YD0 yd0 = this.d.b;
            C4372qE0 qe0 = yd0.f9263a;
            if (qe0 != null && yd0.b) {
                qe0.f11125a.d(true);
                yd0.b = false;
                yd0.c = 0.0f;
            }
            QD0 qd0 = this.e;
            if (qd0 != null) {
                qd0.a(motionEvent);
            }
        }
        return this.f8745a.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        boolean z;
        Runnable runnable;
        YD0 yd0 = this.d.b;
        if (!yd0.k) {
            z = false;
        } else {
            float c2 = yd0.f.c();
            int height = (int) (((float) yd0.g.getHeight()) * c2);
            yd0.d.forceFinished(true);
            Rect a2 = yd0.f.a();
            yd0.d.fling(a2.left, a2.top, (int) (-f2), (int) (-f3), 0, ((int) (((float) yd0.g.getWidth()) * c2)) - a2.width(), 0, height - a2.height());
            if (!yd0.d.isFinished() && (runnable = yd0.j) != null) {
                runnable.run();
            }
            yd0.e.post(new WD0(yd0));
            z = true;
        }
        if (z) {
            return true;
        }
        QD0 qd0 = this.e;
        if (qd0 != null) {
            return qd0.onFling(motionEvent, motionEvent2, f2, f3);
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        RD0 rd0 = this.d;
        motionEvent.getX();
        motionEvent.getY();
        C4713sE0 se0 = ((SD0) rd0.c).n.c;
        if (se0 != null) {
            se0.f11260a.run();
        }
        Map map = AbstractC4542rE0.f11192a;
        AbstractC3535lK0.a("PaintPreview.Player.LongPress");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008a, code lost:
        if (r5 <= 5.0f) goto L_0x008e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onScale(android.view.ScaleGestureDetector r14) {
        /*
        // Method dump skipped, instructions count: 322
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.QD0.onScale(android.view.ScaleGestureDetector):boolean");
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        RD0 rd0 = this.d;
        scaleGestureDetector.getScaleFactor();
        scaleGestureDetector.getFocusX();
        scaleGestureDetector.getFocusY();
        VD0 vd0 = rd0.f8817a;
        TD0 td0 = vd0.e;
        float c2 = vd0.b.c();
        SD0 sd0 = (SD0) td0;
        for (int i = 0; i < sd0.c.size(); i++) {
            ((SD0) sd0.e.get(i)).d(c2);
        }
        ((SD0) vd0.e).f(true);
        SD0 sd02 = (SD0) vd0.e;
        for (int i2 = 0; i2 < sd02.c.size(); i2++) {
            if (((View) sd02.c.get(i2)).getVisibility() == 0) {
                ((SD0) sd02.e.get(i2)).a();
            }
        }
        vd0.f9071a = 0.0f;
        Callback callback = vd0.f;
        if (callback != null) {
            callback.onResult(Boolean.TRUE);
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        Runnable runnable;
        YD0 yd0 = this.d.b;
        yd0.d.forceFinished(true);
        boolean b2 = yd0.b(f2, f3);
        if (b2 && (runnable = yd0.i) != null) {
            runnable.run();
        }
        if (b2) {
            this.f = 0.0f;
            this.g = 0.0f;
            return true;
        }
        float f4 = this.f + f2;
        this.f = f4;
        float f5 = this.g + f3;
        this.g = f5;
        QD0 qd0 = this.e;
        if (qd0 != null && qd0.onScroll(motionEvent, motionEvent2, f4, f5)) {
            return true;
        }
        this.f = 0.0f;
        this.g = 0.0f;
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        C2834hE0 he0;
        SD0 sd0 = (SD0) this.d.c;
        float c2 = sd0.i.c();
        AbstractC5900zD0 zd0 = sd0.h;
        UnguessableToken unguessableToken = sd0.f8884a;
        int round = Math.round((sd0.i.d() + ((float) ((int) motionEvent.getX()))) / c2);
        int round2 = Math.round((sd0.i.e() + ((float) ((int) motionEvent.getY()))) / c2);
        long j = ((PlayerCompositorDelegateImpl) zd0).b;
        GURL gurl = null;
        if (j != 0) {
            String MqJDIMXF = N.MqJDIMXF(j, unguessableToken, round, round2);
            if (!TextUtils.isEmpty(MqJDIMXF)) {
                gurl = new GURL(MqJDIMXF);
            }
        }
        C2151dE0 de0 = sd0.n;
        if (gurl == null || (he0 = de0.b) == null) {
            C4713sE0 se0 = de0.c;
            if (se0 != null) {
                se0.b.add(Long.valueOf(System.currentTimeMillis()));
                int size = se0.b.size() - 1;
                while (true) {
                    if (size <= 0) {
                        break;
                    }
                    int i = size - 1;
                    if (((Long) se0.b.get(size)).longValue() - ((Long) se0.b.get(i)).longValue() > 2000) {
                        se0.b.subList(0, size).clear();
                        break;
                    }
                    size = i;
                }
                if (se0.b.size() == 3) {
                    se0.f11260a.run();
                    se0.b.clear();
                }
            }
            Map map = AbstractC4542rE0.f11192a;
            AbstractC3535lK0.a("PaintPreview.Player.UnconsumedTap");
            return true;
        }
        he0.f10058a.b(gurl);
        Map map2 = AbstractC4542rE0.f11192a;
        AbstractC3535lK0.a("PaintPreview.Player.LinkClicked");
        return true;
    }
}
