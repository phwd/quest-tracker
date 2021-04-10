package defpackage;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: L6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L6 extends AbstractC5327vs1 implements E6 {
    public J6 G;
    public Context H;
    public ArgbEvaluator I = null;

    /* renamed from: J  reason: collision with root package name */
    public Animator.AnimatorListener f8405J = null;
    public ArrayList K = null;
    public final Drawable.Callback L;

    public L6(Context context, J6 j6, Resources resources) {
        H6 h6 = new H6(this);
        this.L = h6;
        this.H = context;
        this.G = new J6(null, h6, null);
    }

    public static L6 a(Context context, int i) {
        L6 l6 = new L6(context, null, null);
        Drawable drawable = context.getResources().getDrawable(i, context.getTheme());
        l6.F = drawable;
        drawable.setCallback(l6.L);
        new K6(l6.F.getConstantState());
        return l6;
    }

    public static void c(Drawable drawable, D6 d6) {
        if (drawable != null && d6 != null && (drawable instanceof Animatable)) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
            if (d6.f7864a == null) {
                d6.f7864a = new C6(d6);
            }
            animatedVectorDrawable.registerAnimationCallback(d6.f7864a);
        }
    }

    public static boolean d(Drawable drawable, D6 d6) {
        if (drawable == null || d6 == null || !(drawable instanceof Animatable)) {
            return false;
        }
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
        if (d6.f7864a == null) {
            d6.f7864a = new C6(d6);
        }
        return animatedVectorDrawable.unregisterAnimationCallback(d6.f7864a);
    }

    @Override // defpackage.AbstractC5327vs1
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.applyTheme(theme);
        }
    }

    public void b(D6 d6) {
        Drawable drawable = this.F;
        if (drawable != null) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
            if (d6.f7864a == null) {
                d6.f7864a = new C6(d6);
            }
            animatedVectorDrawable.registerAnimationCallback(d6.f7864a);
        } else if (d6 != null) {
            if (this.K == null) {
                this.K = new ArrayList();
            }
            if (!this.K.contains(d6)) {
                this.K.add(d6);
                if (this.f8405J == null) {
                    this.f8405J = new I6(this);
                }
                this.G.c.addListener(this.f8405J);
            }
        }
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.canApplyTheme();
        }
        return false;
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.G.b.draw(canvas);
        if (this.G.c.isStarted()) {
            invalidateSelf();
        }
    }

    public int getAlpha() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getAlpha();
        }
        return this.G.b.getAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.G.f8269a;
    }

    public ColorFilter getColorFilter() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getColorFilter();
        }
        return this.G.b.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        if (this.F != null) {
            return new K6(this.F.getConstantState());
        }
        return null;
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return this.G.b.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return this.G.b.getIntrinsicWidth();
    }

    public int getOpacity() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return this.G.b.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray g = Ko1.g(resources, theme, attributeSet, AbstractC4176p6.e);
                    int resourceId = g.getResourceId(0, 0);
                    if (resourceId != 0) {
                        Fs1 a2 = Fs1.a(resources, resourceId, theme);
                        a2.L = false;
                        a2.setCallback(this.L);
                        Fs1 fs1 = this.G.b;
                        if (fs1 != null) {
                            fs1.setCallback(null);
                        }
                        this.G.b = a2;
                    }
                    g.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, AbstractC4176p6.f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.H;
                        if (context != null) {
                            Animator loadAnimator = AnimatorInflater.loadAnimator(context, resourceId2);
                            loadAnimator.setTarget(this.G.b.H.b.q.getOrDefault(string, null));
                            J6 j6 = this.G;
                            if (j6.d == null) {
                                j6.d = new ArrayList();
                                this.G.e = new C4931ta();
                            }
                            this.G.d.add(loadAnimator);
                            this.G.e.put(loadAnimator, string);
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        J6 j62 = this.G;
        if (j62.c == null) {
            j62.c = new AnimatorSet();
        }
        j62.c.playTogether(j62.d);
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.isAutoMirrored();
        }
        return this.G.b.isAutoMirrored();
    }

    public boolean isRunning() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return ((AnimatedVectorDrawable) drawable).isRunning();
        }
        return this.G.c.isRunning();
    }

    public boolean isStateful() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return this.G.b.isStateful();
    }

    public Drawable mutate() {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.G.b.setBounds(rect);
        }
    }

    @Override // defpackage.AbstractC5327vs1
    public boolean onLevelChange(int i) {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        return this.G.b.setLevel(i);
    }

    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        return this.G.b.setState(iArr);
    }

    public void setAlpha(int i) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.G.b.setAlpha(i);
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
            return;
        }
        Fs1 fs1 = this.G.b;
        Drawable drawable2 = fs1.F;
        if (drawable2 != null) {
            drawable2.setAutoMirrored(z);
        } else {
            fs1.H.e = z;
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        Fs1 fs1 = this.G.b;
        Drawable drawable2 = fs1.F;
        if (drawable2 != null) {
            drawable2.setColorFilter(colorFilter);
            return;
        }
        fs1.f8045J = colorFilter;
        fs1.invalidateSelf();
    }

    public void setTint(int i) {
        Drawable drawable = this.F;
        if (drawable != null) {
            VI.a(drawable, i);
        } else {
            this.G.b.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.F;
        if (drawable != null) {
            VI.b(drawable, colorStateList);
        } else {
            this.G.b.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.F;
        if (drawable != null) {
            VI.c(drawable, mode);
        } else {
            this.G.b.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.G.b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public void start() {
        Drawable drawable = this.F;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else if (!this.G.c.isStarted()) {
            this.G.c.start();
            invalidateSelf();
        }
    }

    public void stop() {
        Drawable drawable = this.F;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.G.c.end();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
