package X;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.annotation.DrawableRes;
import javax.annotation.Nullable;

/* renamed from: X.1le  reason: invalid class name and case insensitive filesystem */
public class C10221le extends C10231lf {
    public static AbstractC00750Ik<? extends AbstractC09911kL> A01;
    public AbstractC09911kL A00;

    public void setActualImageResource(@DrawableRes int i) {
        setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(i)).build(), (Object) null);
    }

    public AbstractC09911kL getControllerBuilder() {
        return this.A00;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.1kA */
    /* JADX WARN: Multi-variable type inference failed */
    public void setImageRequest(AnonymousClass1kA r3) {
        AbstractC09911kL r1 = this.A00;
        r1.A03 = r3;
        r1.A01 = super.A00.A00;
        setController(r1.A00());
    }

    /* JADX INFO: finally extract failed */
    private void A00(Context context, @Nullable AttributeSet attributeSet) {
        int resourceId;
        try {
            C01060Pq.A00();
            if (isInEditMode()) {
                getTopLevelDrawable().setVisible(true, false);
                getTopLevelDrawable().invalidateSelf();
            } else {
                C00740Ii.A02(A01, "SimpleDraweeView was not initialized!");
                this.A00 = (AbstractC09911kL) A01.get();
            }
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C10431mb.A01);
                try {
                    if (obtainStyledAttributes.hasValue(2)) {
                        setImageURI(Uri.parse(obtainStyledAttributes.getString(2)), (Object) null);
                    } else if (obtainStyledAttributes.hasValue(0) && (resourceId = obtainStyledAttributes.getResourceId(0, -1)) != -1) {
                        if (isInEditMode()) {
                            setImageResource(resourceId);
                        } else {
                            setActualImageResource(resourceId);
                        }
                    }
                } finally {
                    obtainStyledAttributes.recycle();
                }
            }
            C01060Pq.A00();
        } catch (Throwable th) {
            C01060Pq.A00();
            throw th;
        }
    }

    public C10221le(Context context) {
        super(context);
        A00(context, null);
    }

    public C10221le(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        A00(context, attributeSet);
    }

    public C10221le(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        A00(context, attributeSet);
    }

    @TargetApi(21)
    public C10221le(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        A00(context, attributeSet);
    }

    public C10221le(Context context, AnonymousClass1lX r3) {
        super(context, r3);
        A00(context, null);
    }

    @Override // X.C10241lg
    public void setImageURI(Uri uri) {
        setImageURI(uri, (Object) null);
    }

    public void setImageURI(Uri uri, @Nullable Object obj) {
        REQUEST request;
        AbstractC09911kL r2 = this.A00;
        r2.A02 = obj;
        if (uri == null) {
            request = null;
        } else {
            C09921kM r1 = new C09921kM();
            r1.A01 = uri;
            r1.A05 = AnonymousClass0PO.A03;
            request = (REQUEST) r1.A00();
        }
        r2.A03 = request;
        r2.A01 = super.A00.A00;
        setController(r2.A00());
    }

    public void setImageURI(@Nullable String str) {
        setImageURI(str, (Object) null);
    }

    public void setImageURI(@Nullable String str, @Nullable Object obj) {
        Uri uri;
        if (str != null) {
            uri = Uri.parse(str);
        } else {
            uri = null;
        }
        setImageURI(uri, obj);
    }
}
