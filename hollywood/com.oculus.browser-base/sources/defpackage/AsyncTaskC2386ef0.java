package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import androidx.mediarouter.app.MediaRouteButton;

/* renamed from: ef0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AsyncTaskC2386ef0 extends AsyncTask {

    /* renamed from: a  reason: collision with root package name */
    public final int f9869a;
    public final Context b;
    public final /* synthetic */ MediaRouteButton c;

    public AsyncTaskC2386ef0(MediaRouteButton mediaRouteButton, int i, Context context) {
        this.c = mediaRouteButton;
        this.f9869a = i;
        this.b = context;
    }

    @Override // android.os.AsyncTask
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        if (((Drawable.ConstantState) MediaRouteButton.G.get(this.f9869a)) == null) {
            return this.b.getResources().getDrawable(this.f9869a);
        }
        return null;
    }

    @Override // android.os.AsyncTask
    public void onCancelled(Object obj) {
        Drawable drawable = (Drawable) obj;
        if (drawable != null) {
            MediaRouteButton.G.put(this.f9869a, drawable.getConstantState());
        }
        this.c.P = null;
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(Object obj) {
        Drawable drawable = (Drawable) obj;
        if (drawable != null) {
            MediaRouteButton.G.put(this.f9869a, drawable.getConstantState());
            this.c.P = null;
        } else {
            Drawable.ConstantState constantState = (Drawable.ConstantState) MediaRouteButton.G.get(this.f9869a);
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            this.c.P = null;
        }
        this.c.d(drawable);
    }
}
