package okhttp3.internal;

import java.util.Locale;

public abstract class NamedRunnable implements Runnable {
    public final String name;

    public abstract void execute();

    public NamedRunnable(String str, Object... objArr) {
        this.name = String.format(Locale.US, str, objArr);
    }

    public final void run() {
        Thread currentThread = Thread.currentThread();
        String name2 = currentThread.getName();
        currentThread.setName(this.name);
        try {
            execute();
        } finally {
            currentThread.setName(name2);
        }
    }
}
