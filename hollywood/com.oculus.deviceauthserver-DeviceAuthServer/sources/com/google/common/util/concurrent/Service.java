package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public interface Service {

    @Beta
    public enum State {
        NEW {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        STARTING {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        RUNNING {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        STOPPING {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        TERMINATED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        },
        FAILED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        };

        /* access modifiers changed from: package-private */
        public abstract boolean isTerminal();
    }

    void addListener(Listener listener, Executor executor);

    void awaitRunning();

    void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException;

    void awaitTerminated();

    void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException;

    Throwable failureCause();

    boolean isRunning();

    Service startAsync();

    State state();

    Service stopAsync();

    @Beta
    public static abstract class Listener {
        public void starting() {
        }

        public void running() {
        }

        public void stopping(State from) {
        }

        public void terminated(State from) {
        }

        public void failed(State from, Throwable failure) {
        }
    }
}
