package com.oculus.clay.app;

final class c implements Runnable {
    private /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        if (b.a(this.a).get()) {
            this.a.d();
        }
    }
}
