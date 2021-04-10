package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.widget.TextView;

public final class TextViewCompat {
    static final TextViewCompatImpl IMPL;

    /* access modifiers changed from: package-private */
    public interface TextViewCompatImpl {
        Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView);

        int getMaxLines(TextView textView);

        int getMinLines(TextView textView);

        void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4);

        void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4);

        void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4);

        void setTextAppearance(@NonNull TextView textView, @StyleRes int i);
    }

    private TextViewCompat() {
    }

    static class BaseTextViewCompatImpl implements TextViewCompatImpl {
        BaseTextViewCompatImpl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            textView.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
            textView.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public int getMaxLines(TextView textView) {
            return TextViewCompatGingerbread.getMaxLines(textView);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public int getMinLines(TextView textView) {
            return TextViewCompatGingerbread.getMinLines(textView);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public void setTextAppearance(TextView textView, @StyleRes int i) {
            TextViewCompatGingerbread.setTextAppearance(textView, i);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl
        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
            return TextViewCompatGingerbread.getCompoundDrawablesRelative(textView);
        }
    }

    static class JbTextViewCompatImpl extends BaseTextViewCompatImpl {
        JbTextViewCompatImpl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public int getMaxLines(TextView textView) {
            return TextViewCompatJb.getMaxLines(textView);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public int getMinLines(TextView textView) {
            return TextViewCompatJb.getMinLines(textView);
        }
    }

    static class JbMr1TextViewCompatImpl extends JbTextViewCompatImpl {
        JbMr1TextViewCompatImpl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            TextViewCompatJbMr1.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, i, i2, i3, i4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
            return TextViewCompatJbMr1.getCompoundDrawablesRelative(textView);
        }
    }

    static class JbMr2TextViewCompatImpl extends JbMr1TextViewCompatImpl {
        JbMr2TextViewCompatImpl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl, android.support.v4.widget.TextViewCompat.JbMr1TextViewCompatImpl
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            TextViewCompatJbMr2.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl, android.support.v4.widget.TextViewCompat.JbMr1TextViewCompatImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl, android.support.v4.widget.TextViewCompat.JbMr1TextViewCompatImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, i, i2, i3, i4);
        }
    }

    static class Api23TextViewCompatImpl extends JbMr2TextViewCompatImpl {
        Api23TextViewCompatImpl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatImpl, android.support.v4.widget.TextViewCompat.BaseTextViewCompatImpl
        public void setTextAppearance(@NonNull TextView textView, @StyleRes int i) {
            TextViewCompatApi23.setTextAppearance(textView, i);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            IMPL = new Api23TextViewCompatImpl();
        } else if (i >= 18) {
            IMPL = new JbMr2TextViewCompatImpl();
        } else if (i >= 17) {
            IMPL = new JbMr1TextViewCompatImpl();
        } else if (i >= 16) {
            IMPL = new JbTextViewCompatImpl();
        } else {
            IMPL = new BaseTextViewCompatImpl();
        }
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        IMPL.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, i, i2, i3, i4);
    }

    public static int getMaxLines(@NonNull TextView textView) {
        return IMPL.getMaxLines(textView);
    }

    public static int getMinLines(@NonNull TextView textView) {
        return IMPL.getMinLines(textView);
    }

    public static void setTextAppearance(@NonNull TextView textView, @StyleRes int i) {
        IMPL.setTextAppearance(textView, i);
    }

    public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
        return IMPL.getCompoundDrawablesRelative(textView);
    }
}
