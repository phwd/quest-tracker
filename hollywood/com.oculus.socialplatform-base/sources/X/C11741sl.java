package X;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;

/* renamed from: X.1sl  reason: invalid class name and case insensitive filesystem */
public class C11741sl {
    public int A00 = -1;
    public DialogInterface.OnClickListener A01;
    public DialogInterface.OnKeyListener A02;
    public Drawable A03;
    public View A04;
    public ListAdapter A05;
    public CharSequence A06;
    public boolean A07;
    public final Context A08;
    public final LayoutInflater A09;

    public final void A00(C11731sk r6) {
        int i;
        View view = this.A04;
        if (view != null) {
            r6.A0E = view;
        } else {
            CharSequence charSequence = this.A06;
            if (charSequence != null) {
                r6.A0R = charSequence;
                TextView textView = r6.A0M;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = this.A03;
            if (drawable != null) {
                r6.A0D = drawable;
                r6.A06 = 0;
                ImageView imageView = r6.A0I;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    r6.A0I.setImageDrawable(drawable);
                }
            }
        }
        if (this.A05 != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) this.A09.inflate(r6.A08, (ViewGroup) null);
            if (this.A07) {
                i = r6.A09;
            } else {
                i = r6.A07;
            }
            ListAdapter listAdapter = this.A05;
            if (listAdapter == null) {
                listAdapter = new C11981th(this.A08, i);
            }
            r6.A0J = listAdapter;
            r6.A05 = this.A00;
            if (this.A01 != null) {
                alertController$RecycleListView.setOnItemClickListener(new AnonymousClass1sx(this, r6));
            }
            if (this.A07) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            r6.A0K = alertController$RecycleListView;
        }
    }

    public C11741sl(Context context) {
        this.A08 = context;
        this.A09 = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
