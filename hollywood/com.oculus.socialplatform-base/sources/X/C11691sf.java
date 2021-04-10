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
import java.lang.reflect.Method;
import okhttp3.internal.ws.WebSocketProtocol;

/* renamed from: X.1sf  reason: invalid class name and case insensitive filesystem */
public class C11691sf implements AnonymousClass1FA {
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
    public AnonymousClass1F5 A0B;
    public Runnable A0C;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public int A0H;
    public int A0I = WebSocketProtocol.CLOSE_PROTOCOL_EXCEPTION;
    public DataSetObserver A0J;
    public final Handler A0K;
    public final AnonymousClass1tF A0L = new AnonymousClass1tF(this);
    public final Rect A0M = new Rect();
    public final AnonymousClass1t9 A0N = new AnonymousClass1t9(this);
    public final AnonymousClass1tY A0O = new AnonymousClass1tY(this);
    public final View$OnTouchListenerC11821st A0P = new View$OnTouchListenerC11821st(this);

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

    public final void A00(int i) {
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
    public final Drawable A3Q() {
        return this.A0A.getBackground();
    }

    public final int A47() {
        return this.A0H;
    }

    @Override // X.AnonymousClass1FA
    @Nullable
    public final ListView A4G() {
        return this.A0B;
    }

    public final int A5H() {
        if (!this.A0D) {
            return 0;
        }
        return this.A03;
    }

    @Override // X.AnonymousClass1FA
    public final boolean A6B() {
        return this.A0A.isShowing();
    }

    public void A9e(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.A0J;
        if (dataSetObserver == null) {
            this.A0J = new AnonymousClass1tZ(this);
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
        AnonymousClass1F5 r1 = this.A0B;
        if (r1 != null) {
            r1.setAdapter(this.A09);
        }
    }

    public final void A9f(@Nullable Drawable drawable) {
        this.A0A.setBackgroundDrawable(drawable);
    }

    public final void AAG(int i) {
        this.A03 = i;
        this.A0D = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v34, types: [X.1F5] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01be  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass1FA
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAO() {
        /*
        // Method dump skipped, instructions count: 514
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11691sf.AAO():void");
    }

    @Override // X.AnonymousClass1FA
    public final void dismiss() {
        this.A0A.dismiss();
        this.A0A.setContentView(null);
        this.A0B = null;
        this.A0K.removeCallbacks(this.A0L);
    }

    public C11691sf(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this.A05 = context;
        this.A0K = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11081qa.A0E, i, i2);
        this.A0H = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.A03 = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.A0D = true;
        }
        obtainStyledAttributes.recycle();
        C11771so r0 = new C11771so(context, attributeSet, i, i2);
        this.A0A = r0;
        r0.setInputMethodMode(1);
    }

    public final void A9u(int i) {
        this.A0H = i;
    }
}
