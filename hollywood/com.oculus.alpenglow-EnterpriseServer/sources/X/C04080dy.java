package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.oculus.alpenglow.constants.Constants;
import java.lang.reflect.Method;

/* renamed from: X.0dy  reason: invalid class name and case insensitive filesystem */
public class C04080dy implements AbstractC000903e {
    public static Method A0Q;
    public static Method A0R;
    public int A00 = Integer.MAX_VALUE;
    public int A01 = 0;
    public int A02 = -2;
    public int A03;
    public int A04 = -2;
    public Context A05;
    public Rect A06;
    public View A07;
    public AdapterView.OnItemClickListener A08;
    public ListAdapter A09;
    public PopupWindow A0A;
    public C003004g A0B;
    public Runnable A0C;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public int A0H;
    public int A0I = Constants.OTA_SCHEDULE_WINDOW_START_JOB_ID;
    public DataSetObserver A0J;
    public final Handler A0K;
    public final RunnableC004204w A0L = new RunnableC004204w(this);
    public final Rect A0M = new Rect();
    public final C004004u A0N = new C004004u(this);
    public final RunnableC003804s A0O = new RunnableC003804s(this);
    public final View$OnTouchListenerC004104v A0P = new View$OnTouchListenerC004104v(this);

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                A0Q = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                A0R = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
    }

    @NonNull
    public C003004g A00(Context context, boolean z) {
        return new C003004g(context, z);
    }

    public final void A01(int i) {
        Drawable background = this.A0A.getBackground();
        if (background != null) {
            Rect rect = this.A0M;
            background.getPadding(rect);
            this.A04 = rect.left + rect.right + i;
            return;
        }
        this.A04 = i;
    }

    @Nullable
    public final Drawable A33() {
        return this.A0A.getBackground();
    }

    public final int A3g() {
        return this.A0H;
    }

    @Override // X.AbstractC000903e
    @Nullable
    public final ListView A3u() {
        return this.A0B;
    }

    public final int A4r() {
        if (!this.A0D) {
            return 0;
        }
        return this.A03;
    }

    @Override // X.AbstractC000903e
    public final boolean A5a() {
        return this.A0A.isShowing();
    }

    public void A7j(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.A0J;
        if (dataSetObserver == null) {
            this.A0J = new C003904t(this);
        } else {
            ListAdapter listAdapter2 = this.A09;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.A09 = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.A0J);
        }
        C003004g r1 = this.A0B;
        if (r1 != null) {
            r1.setAdapter(this.A09);
        }
    }

    public final void A7l(@Nullable Drawable drawable) {
        this.A0A.setBackgroundDrawable(drawable);
    }

    public final void A8E(int i) {
        this.A03 = i;
        this.A0D = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:93:0x01b8  */
    @Override // X.AbstractC000903e
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A8P() {
        /*
        // Method dump skipped, instructions count: 497
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04080dy.A8P():void");
    }

    @Override // X.AbstractC000903e
    public final void dismiss() {
        this.A0A.dismiss();
        this.A0A.setContentView(null);
        this.A0B = null;
        this.A0K.removeCallbacks(this.A0L);
    }

    public C04080dy(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this.A05 = context;
        this.A0K = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass18N.A0E, i, i2);
        this.A0H = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.A03 = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.A0D = true;
        }
        obtainStyledAttributes.recycle();
        AnonymousClass04H r0 = new AnonymousClass04H(context, attributeSet, i, i2);
        this.A0A = r0;
        r0.setInputMethodMode(1);
    }

    public final void A7u(int i) {
        this.A0H = i;
    }
}
