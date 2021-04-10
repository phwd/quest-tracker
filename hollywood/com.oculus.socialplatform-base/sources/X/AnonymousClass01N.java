package X;

import androidx.activity.ComponentActivity;

/* renamed from: X.01N  reason: invalid class name */
public class AnonymousClass01N implements Runnable {
    public static final String __redex_internal_original_name = "androidx.activity.ComponentActivity$1";
    public final /* synthetic */ ComponentActivity A00;

    public AnonymousClass01N(ComponentActivity componentActivity) {
        this.A00 = componentActivity;
    }

    public final void run() {
        AnonymousClass01N.super.onBackPressed();
    }
}
