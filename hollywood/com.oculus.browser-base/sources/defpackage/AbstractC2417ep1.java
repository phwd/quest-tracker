package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: ep1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2417ep1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f9884a;
    public static Boolean b;

    static {
        HashMap hashMap = new HashMap();
        f9884a = hashMap;
        hashMap.put("xiaomi", 24);
        hashMap.put("htc", 26);
    }

    public static int a(ListAdapter listAdapter) {
        return b(listAdapter, null);
    }

    public static int b(ListAdapter listAdapter, ViewGroup viewGroup) {
        View view;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-2, -2);
        View[] viewArr = new View[listAdapter.getViewTypeCount()];
        int i = 0;
        for (int i2 = 0; i2 < listAdapter.getCount(); i2++) {
            int itemViewType = listAdapter.getItemViewType(i2);
            if (itemViewType < 0) {
                view = listAdapter.getView(i2, null, viewGroup);
            } else {
                viewArr[itemViewType] = listAdapter.getView(i2, viewArr[itemViewType], viewGroup);
                view = viewArr[itemViewType];
            }
            view.setLayoutParams(layoutParams);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view.getMeasuredWidth());
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008b, code lost:
        if (r0 == false) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008d, code lost:
        r13.setDrawingCacheEnabled(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0090, code lost:
        j(r13, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009a, code lost:
        if (r0 != false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009d, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap c(android.view.View r13, int r14, android.graphics.Bitmap.Config r15) {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC2417ep1.c(android.view.View, int, android.graphics.Bitmap$Config):android.graphics.Bitmap");
    }

    public static File d(Context context) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            File file = new File(context.getFilesDir(), "images");
            if (!file.exists()) {
                if (!file.mkdir()) {
                    throw new IOException("Folder cannot be created.");
                }
            }
            return file;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static Drawable e(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (typedArray == null || (resourceId = typedArray.getResourceId(i, -1)) == -1) {
            return null;
        }
        return AbstractC5544x8.a(context, resourceId);
    }

    public static Drawable f(Context context, int i, int i2) {
        Drawable mutate = AbstractC5544x8.a(context, i).mutate();
        mutate.setTintList(context.getColorStateList(i2));
        return mutate;
    }

    public static int g(ViewGroup viewGroup, View view, View view2) {
        return h(viewGroup, view, view2, true);
    }

    public static int h(ViewGroup viewGroup, View view, View view2, boolean z) {
        int indexOfChild = viewGroup.indexOfChild(view);
        if (indexOfChild >= 0) {
            return indexOfChild;
        }
        int indexOfChild2 = viewGroup.indexOfChild(view2);
        if (indexOfChild2 < 0) {
            return -1;
        }
        if (z) {
            indexOfChild2++;
        }
        viewGroup.addView(view, indexOfChild2);
        return indexOfChild2;
    }

    public static boolean i() {
        if (b == null) {
            b = Boolean.FALSE;
            Map map = f9884a;
            String str = Build.MANUFACTURER;
            Locale locale = Locale.US;
            if (map.containsKey(str.toLowerCase(locale))) {
                b = Boolean.valueOf(Build.VERSION.SDK_INT < ((Integer) map.get(str.toLowerCase(locale))).intValue());
            }
        }
        return b.booleanValue();
    }

    public static void j(View view, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                j(viewGroup.getChildAt(i), z);
            }
        } else if (view instanceof SurfaceView) {
            view.setWillNotDraw(!z);
        }
    }

    public static void k(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    public static void l(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            int systemUiVisibility = view.getSystemUiVisibility();
            view.setSystemUiVisibility(z ? systemUiVisibility | 16 : systemUiVisibility & -17);
        }
    }
}
