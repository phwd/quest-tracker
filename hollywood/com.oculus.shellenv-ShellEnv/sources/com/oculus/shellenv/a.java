package com.oculus.shellenv;

final class a implements Runnable {
    private /* synthetic */ ShellEnvActivity a;

    a(ShellEnvActivity shellEnvActivity) {
        this.a = shellEnvActivity;
    }

    public final void run() {
        ShellEnvActivity shellEnvActivity = this.a;
        shellEnvActivity.setIntent(ShellEnvActivity.a(shellEnvActivity));
        this.a.getIntent().putExtra("EXTRA_SWITCHED_FROM_MIN_OVERLAY", true);
        ShellEnvActivity.b(this.a).h();
    }
}
