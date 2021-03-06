package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build;

public final class BitmapCompat {
    static final BitmapImpl IMPL;

    interface BitmapImpl {
        int getAllocationByteCount(Bitmap bitmap);

        boolean hasMipMap(Bitmap bitmap);

        void setHasMipMap(Bitmap bitmap, boolean z);
    }

    static class BaseBitmapImpl implements BitmapImpl {
        @Override // android.support.v4.graphics.BitmapCompat.BitmapImpl
        public boolean hasMipMap(Bitmap bitmap) {
            return false;
        }

        @Override // android.support.v4.graphics.BitmapCompat.BitmapImpl
        public void setHasMipMap(Bitmap bitmap, boolean z) {
        }

        BaseBitmapImpl() {
        }

        @Override // android.support.v4.graphics.BitmapCompat.BitmapImpl
        public int getAllocationByteCount(Bitmap bitmap) {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    static class HcMr1BitmapCompatImpl extends BaseBitmapImpl {
        HcMr1BitmapCompatImpl() {
        }

        @Override // android.support.v4.graphics.BitmapCompat.BaseBitmapImpl, android.support.v4.graphics.BitmapCompat.BitmapImpl
        public int getAllocationByteCount(Bitmap bitmap) {
            return BitmapCompatHoneycombMr1.getAllocationByteCount(bitmap);
        }
    }

    static class JbMr2BitmapCompatImpl extends HcMr1BitmapCompatImpl {
        JbMr2BitmapCompatImpl() {
        }

        @Override // android.support.v4.graphics.BitmapCompat.BaseBitmapImpl, android.support.v4.graphics.BitmapCompat.BitmapImpl
        public boolean hasMipMap(Bitmap bitmap) {
            return BitmapCompatJellybeanMR2.hasMipMap(bitmap);
        }

        @Override // android.support.v4.graphics.BitmapCompat.BaseBitmapImpl, android.support.v4.graphics.BitmapCompat.BitmapImpl
        public void setHasMipMap(Bitmap bitmap, boolean z) {
            BitmapCompatJellybeanMR2.setHasMipMap(bitmap, z);
        }
    }

    static class KitKatBitmapCompatImpl extends JbMr2BitmapCompatImpl {
        KitKatBitmapCompatImpl() {
        }

        @Override // android.support.v4.graphics.BitmapCompat.BaseBitmapImpl, android.support.v4.graphics.BitmapCompat.BitmapImpl, android.support.v4.graphics.BitmapCompat.HcMr1BitmapCompatImpl
        public int getAllocationByteCount(Bitmap bitmap) {
            return BitmapCompatKitKat.getAllocationByteCount(bitmap);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            IMPL = new KitKatBitmapCompatImpl();
        } else if (i >= 18) {
            IMPL = new JbMr2BitmapCompatImpl();
        } else if (i >= 12) {
            IMPL = new HcMr1BitmapCompatImpl();
        } else {
            IMPL = new BaseBitmapImpl();
        }
    }

    public static boolean hasMipMap(Bitmap bitmap) {
        return IMPL.hasMipMap(bitmap);
    }

    public static void setHasMipMap(Bitmap bitmap, boolean z) {
        IMPL.setHasMipMap(bitmap, z);
    }

    public static int getAllocationByteCount(Bitmap bitmap) {
        return IMPL.getAllocationByteCount(bitmap);
    }

    private BitmapCompat() {
    }
}
