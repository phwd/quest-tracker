package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: lu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3628lu1 {
    public static void a(View view, HI0 hi0) {
        if (!(view.getTag(R.id.highlight_state) != null ? ((Boolean) view.getTag(R.id.highlight_state)).booleanValue() : false)) {
            view.getContext().getResources();
            Drawable background = view.getBackground();
            if (background != null) {
                background = background.getConstantState().newDrawable();
            }
            view.setBackground(new LayerDrawable(background == null ? new Drawable[]{hi0} : new Drawable[]{background, hi0}));
            view.setTag(R.id.highlight_state, Boolean.TRUE);
            hi0.start();
        }
    }

    public static void b(View view) {
        if (view != null) {
            if (view.getTag(R.id.highlight_state) != null ? ((Boolean) view.getTag(R.id.highlight_state)).booleanValue() : false) {
                view.setTag(R.id.highlight_state, Boolean.FALSE);
                Resources resources = ContextUtils.getApplicationContext().getResources();
                Drawable background = view.getBackground();
                if (background instanceof LayerDrawable) {
                    LayerDrawable layerDrawable = (LayerDrawable) background;
                    if (layerDrawable.getNumberOfLayers() >= 2) {
                        view.setBackground(layerDrawable.getDrawable(0).getConstantState().newDrawable(resources));
                    } else {
                        view.setBackground(null);
                    }
                }
            }
        }
    }

    public static void c(View view) {
        if (view != null) {
            a(view, HI0.a(view.getContext()));
        }
    }

    public static void d(View view) {
        if (view != null) {
            a(view, HI0.c(view.getContext(), 0));
        }
    }
}
