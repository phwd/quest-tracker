package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import com.oculus.socialplatform.R;

/* renamed from: X.1sk  reason: invalid class name and case insensitive filesystem */
public final class C11731sk {
    public Handler A00;
    public final Message A01;
    public final Message A02;
    public final Message A03;
    public int A04;
    public int A05 = -1;
    public int A06 = 0;
    public int A07;
    public int A08;
    public int A09;
    public final Drawable A0A;
    public final Drawable A0B;
    public final Drawable A0C;
    public Drawable A0D;
    public View A0E;
    public Button A0F;
    public Button A0G;
    public Button A0H;
    public ImageView A0I;
    public ListAdapter A0J;
    public ListView A0K;
    public TextView A0L;
    public TextView A0M;
    public NestedScrollView A0N;
    public final CharSequence A0O;
    public final CharSequence A0P;
    public final CharSequence A0Q;
    public CharSequence A0R;
    public boolean A0S;
    public final int A0T;
    public final Context A0U;
    public final View.OnClickListener A0V = new View$OnClickListenerC11861sy(this);
    public final Window A0W;
    public final DialogC11221rS A0X;

    @Nullable
    public static ViewGroup A00(@Nullable View view, @Nullable View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public C11731sk(Context context, DialogC11221rS r6, Window window) {
        this.A0U = context;
        this.A0X = r6;
        this.A0W = window;
        this.A00 = new AnonymousClass1t2(r6);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, C11081qa.A04, R.attr.alertDialogStyle, 0);
        this.A04 = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.getResourceId(2, 0);
        this.A08 = obtainStyledAttributes.getResourceId(4, 0);
        obtainStyledAttributes.getResourceId(5, 0);
        this.A09 = obtainStyledAttributes.getResourceId(7, 0);
        this.A07 = obtainStyledAttributes.getResourceId(3, 0);
        this.A0S = obtainStyledAttributes.getBoolean(6, true);
        this.A0T = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        DialogC11221rS.A00(r6).A0M(1);
    }
}
