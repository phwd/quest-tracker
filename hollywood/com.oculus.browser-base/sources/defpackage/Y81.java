package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: Y81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Y81 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9256a;
    public final ViewGroup b;

    public Y81(Context context, ViewGroup viewGroup) {
        this.f9256a = context;
        this.b = viewGroup;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        Context context = this.f9256a;
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) LayoutInflater.from(context).inflate(R.layout.f37330_resource_name_obfuscated_RES_2131624042, this.b, false);
        viewLookupCachingFrameLayout.setClickable(true);
        ImageView imageView = (ImageView) viewLookupCachingFrameLayout.d(R.id.end_button);
        imageView.setVisibility(0);
        Resources resources = viewLookupCachingFrameLayout.getResources();
        int dimension = (int) resources.getDimension(R.dimen.f25380_resource_name_obfuscated_RES_2131166157);
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, R.drawable.f28430_resource_name_obfuscated_RES_2131230883);
        Bitmap.createScaledBitmap(decodeResource, dimension, dimension, true);
        imageView.setImageBitmap(decodeResource);
        return viewLookupCachingFrameLayout;
    }
}
