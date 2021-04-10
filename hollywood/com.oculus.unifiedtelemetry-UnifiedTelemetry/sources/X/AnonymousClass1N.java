package X;

import androidx.activity.ComponentActivity;

/* renamed from: X.1N  reason: invalid class name */
public class AnonymousClass1N implements Runnable {
    public static final String __redex_internal_original_name = "androidx.activity.ComponentActivity$1";
    public final /* synthetic */ ComponentActivity A00;

    public AnonymousClass1N(ComponentActivity componentActivity) {
        this.A00 = componentActivity;
    }

    public final void run() {
        AnonymousClass1N.super.onBackPressed();
    }
}
