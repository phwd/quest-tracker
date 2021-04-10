package com.oculus.shellenv;

final class b implements Runnable {
    private /* synthetic */ ShellEnvActivity a;

    b(ShellEnvActivity shellEnvActivity) {
        this.a = shellEnvActivity;
    }

    public final void run() {
        ShellEnvActivity shellEnvActivity = this.a;
        shellEnvActivity.setIntent(ShellEnvActivity.a(shellEnvActivity));
        ShellEnvActivity.a(this.a, true);
    }
}
