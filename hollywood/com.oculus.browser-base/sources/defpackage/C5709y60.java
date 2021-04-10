package defpackage;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: y60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5709y60 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView f11663a;
    public ImageView b;
    public ImageView c;

    public C5709y60(A60 a60, RecyclerView recyclerView, ImageView imageView, ImageView imageView2) {
        this.f11663a = recyclerView;
        this.b = imageView;
        this.c = imageView2;
        recyclerView.R0 = this;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        if (this.f11663a.canScrollVertically(-1)) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        if (this.f11663a.canScrollVertically(1)) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }
}
