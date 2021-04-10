package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.mediarouter.app.OverlayListView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* renamed from: xf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC5627xf0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ DialogC0504If0 G;

    public ViewTreeObserver$OnGlobalLayoutListenerC5627xf0(DialogC0504If0 if0, boolean z) {
        this.G = if0;
        this.F = z;
    }

    public void onGlobalLayout() {
        int i;
        HashMap hashMap;
        HashMap hashMap2;
        Bitmap bitmap;
        this.G.X.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        DialogC0504If0 if0 = this.G;
        if (if0.J0) {
            if0.K0 = true;
            return;
        }
        boolean z = this.F;
        int k = DialogC0504If0.k(if0.e0);
        DialogC0504If0.q(if0.e0, -1);
        if0.y(if0.f());
        View decorView = if0.getWindow().getDecorView();
        decorView.measure(View.MeasureSpec.makeMeasureSpec(if0.getWindow().getAttributes().width, 1073741824), 0);
        DialogC0504If0.q(if0.e0, k);
        if (!(if0.Z.getDrawable() instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) if0.Z.getDrawable()).getBitmap()) == null) {
            i = 0;
        } else {
            i = if0.j(bitmap.getWidth(), bitmap.getHeight());
            if0.Z.setScaleType(bitmap.getWidth() >= bitmap.getHeight() ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.FIT_CENTER);
        }
        int l = if0.l(if0.f());
        int size = if0.k0.size();
        int size2 = if0.L.f() ? if0.L.c().size() * if0.s0 : 0;
        if (size > 0) {
            size2 += if0.u0;
        }
        int min = Math.min(size2, if0.t0);
        if (!if0.I0) {
            min = 0;
        }
        int max = Math.max(i, min) + l;
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int height = rect.height() - (if0.W.getMeasuredHeight() - if0.X.getMeasuredHeight());
        if (i <= 0 || max > height) {
            if (if0.e0.getMeasuredHeight() + DialogC0504If0.k(if0.i0) >= if0.X.getMeasuredHeight()) {
                if0.Z.setVisibility(8);
            }
            max = min + l;
            i = 0;
        } else {
            if0.Z.setVisibility(0);
            DialogC0504If0.q(if0.Z, i);
        }
        if (!if0.f() || max > height) {
            if0.f0.setVisibility(8);
        } else {
            if0.f0.setVisibility(0);
        }
        if0.y(if0.f0.getVisibility() == 0);
        int l2 = if0.l(if0.f0.getVisibility() == 0);
        int max2 = Math.max(i, min) + l2;
        if (max2 > height) {
            min -= max2 - height;
        } else {
            height = max2;
        }
        if0.e0.clearAnimation();
        if0.i0.clearAnimation();
        if0.X.clearAnimation();
        if (z) {
            if0.e(if0.e0, l2);
            if0.e(if0.i0, min);
            if0.e(if0.X, height);
        } else {
            DialogC0504If0.q(if0.e0, l2);
            DialogC0504If0.q(if0.i0, min);
            DialogC0504If0.q(if0.X, height);
        }
        DialogC0504If0.q(if0.V, rect.height());
        List c = if0.L.c();
        if (c.isEmpty()) {
            if0.k0.clear();
            if0.j0.notifyDataSetChanged();
        } else if (new HashSet(if0.k0).equals(new HashSet(c))) {
            if0.j0.notifyDataSetChanged();
        } else {
            if (z) {
                OverlayListView overlayListView = if0.i0;
                C0443Hf0 hf0 = if0.j0;
                hashMap = new HashMap();
                int firstVisiblePosition = overlayListView.getFirstVisiblePosition();
                for (int i2 = 0; i2 < overlayListView.getChildCount(); i2++) {
                    Object item = hf0.getItem(firstVisiblePosition + i2);
                    View childAt = overlayListView.getChildAt(i2);
                    hashMap.put(item, new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom()));
                }
            } else {
                hashMap = null;
            }
            if (z) {
                Context context = if0.M;
                OverlayListView overlayListView2 = if0.i0;
                C0443Hf0 hf02 = if0.j0;
                hashMap2 = new HashMap();
                int firstVisiblePosition2 = overlayListView2.getFirstVisiblePosition();
                for (int i3 = 0; i3 < overlayListView2.getChildCount(); i3++) {
                    Object item2 = hf02.getItem(firstVisiblePosition2 + i3);
                    View childAt2 = overlayListView2.getChildAt(i3);
                    Bitmap createBitmap = Bitmap.createBitmap(childAt2.getWidth(), childAt2.getHeight(), Bitmap.Config.ARGB_8888);
                    childAt2.draw(new Canvas(createBitmap));
                    hashMap2.put(item2, new BitmapDrawable(context.getResources(), createBitmap));
                }
            } else {
                hashMap2 = null;
            }
            List list = if0.k0;
            HashSet hashSet = new HashSet(c);
            hashSet.removeAll(list);
            if0.l0 = hashSet;
            HashSet hashSet2 = new HashSet(if0.k0);
            hashSet2.removeAll(c);
            if0.m0 = hashSet2;
            if0.k0.addAll(0, if0.l0);
            if0.k0.removeAll(if0.m0);
            if0.j0.notifyDataSetChanged();
            if (z && if0.I0) {
                if (if0.m0.size() + if0.l0.size() > 0) {
                    if0.i0.setEnabled(false);
                    if0.i0.requestLayout();
                    if0.J0 = true;
                    if0.i0.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC5967zf0(if0, hashMap, hashMap2));
                    return;
                }
            }
            if0.l0 = null;
            if0.m0 = null;
        }
    }
}
