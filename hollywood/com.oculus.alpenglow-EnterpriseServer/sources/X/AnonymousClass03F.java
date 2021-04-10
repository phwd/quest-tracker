package X;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import androidx.annotation.StyleRes;

/* renamed from: X.03F  reason: invalid class name */
public final class AnonymousClass03F extends ContextWrapper {
    public int A00;
    public Configuration A01;
    public Resources A02;
    public Resources.Theme A03;
    public LayoutInflater A04;

    private void A00() {
        if (this.A03 == null) {
            this.A03 = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.A03.setTo(theme);
            }
        }
        this.A03.applyStyle(this.A00, true);
    }

    public final void A01(Configuration configuration) {
        String str;
        if (this.A02 != null) {
            str = "getResources() or getAssets() has already been called";
        } else if (this.A01 == null) {
            this.A01 = new Configuration(configuration);
            return;
        } else {
            str = "Override configuration has already been set";
        }
        throw new IllegalStateException(str);
    }

    public final Resources getResources() {
        Resources resources = this.A02;
        if (resources == null) {
            Configuration configuration = this.A01;
            if (configuration == null) {
                resources = super.getResources();
            } else {
                resources = createConfigurationContext(configuration).getResources();
            }
            this.A02 = resources;
        }
        return resources;
    }

    @Override // android.content.Context, android.content.ContextWrapper
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        LayoutInflater layoutInflater = this.A04;
        if (layoutInflater != null) {
            return layoutInflater;
        }
        LayoutInflater cloneInContext = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        this.A04 = cloneInContext;
        return cloneInContext;
    }

    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.A03;
        if (theme != null) {
            return theme;
        }
        if (this.A00 == 0) {
            this.A00 = 2131493003;
        }
        A00();
        return this.A03;
    }

    public final void setTheme(int i) {
        if (this.A00 != i) {
            this.A00 = i;
            A00();
        }
    }

    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public AnonymousClass03F() {
        super(null);
    }

    public AnonymousClass03F(Context context, @StyleRes int i) {
        super(context);
        this.A00 = i;
    }
}
