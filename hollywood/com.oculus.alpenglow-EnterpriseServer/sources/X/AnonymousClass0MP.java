package X;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: X.0MP  reason: invalid class name */
public abstract class AnonymousClass0MP extends AbstractC03680cx {
    public int A00;
    public int A01;
    public LayoutInflater A02;

    @Override // X.AbstractC03680cx
    public final View A02(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.A02.inflate(this.A00, viewGroup, false);
    }

    @Override // X.AbstractC03680cx
    public View A03(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.A02.inflate(this.A01, viewGroup, false);
    }

    @Deprecated
    public AnonymousClass0MP(Context context, int i) {
        super(context);
        this.A00 = i;
        this.A01 = i;
        this.A02 = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
