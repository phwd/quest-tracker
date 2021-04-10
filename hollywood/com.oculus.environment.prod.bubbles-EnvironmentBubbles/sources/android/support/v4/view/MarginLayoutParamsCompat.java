package android.support.v4.view;

import android.os.Build;
import android.view.ViewGroup;

public final class MarginLayoutParamsCompat {
    static final MarginLayoutParamsCompatImpl IMPL;

    interface MarginLayoutParamsCompatImpl {
        int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams);

        int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams);

        int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams);

        boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams);

        void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i);

        void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i);

        void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i);

        void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i);
    }

    static class MarginLayoutParamsCompatImplBase implements MarginLayoutParamsCompatImpl {
        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return false;
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        }

        MarginLayoutParamsCompatImplBase() {
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.leftMargin;
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.rightMargin;
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
            marginLayoutParams.leftMargin = i;
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
            marginLayoutParams.rightMargin = i;
        }
    }

    static class MarginLayoutParamsCompatImplJbMr1 implements MarginLayoutParamsCompatImpl {
        MarginLayoutParamsCompatImplJbMr1() {
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.getMarginStart(marginLayoutParams);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.getMarginEnd(marginLayoutParams);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
            MarginLayoutParamsCompatJellybeanMr1.setMarginStart(marginLayoutParams, i);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
            MarginLayoutParamsCompatJellybeanMr1.setMarginEnd(marginLayoutParams, i);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.isMarginRelative(marginLayoutParams);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.getLayoutDirection(marginLayoutParams);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
            MarginLayoutParamsCompatJellybeanMr1.setLayoutDirection(marginLayoutParams, i);
        }

        @Override // android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl
        public void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
            MarginLayoutParamsCompatJellybeanMr1.resolveLayoutDirection(marginLayoutParams, i);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            IMPL = new MarginLayoutParamsCompatImplJbMr1();
        } else {
            IMPL = new MarginLayoutParamsCompatImplBase();
        }
    }

    public static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return IMPL.getMarginStart(marginLayoutParams);
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return IMPL.getMarginEnd(marginLayoutParams);
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        IMPL.setMarginStart(marginLayoutParams, i);
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        IMPL.setMarginEnd(marginLayoutParams, i);
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return IMPL.isMarginRelative(marginLayoutParams);
    }

    public static int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
        int layoutDirection = IMPL.getLayoutDirection(marginLayoutParams);
        if (layoutDirection == 0 || layoutDirection == 1) {
            return layoutDirection;
        }
        return 0;
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        IMPL.setLayoutDirection(marginLayoutParams, i);
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        IMPL.resolveLayoutDirection(marginLayoutParams, i);
    }

    private MarginLayoutParamsCompat() {
    }
}
