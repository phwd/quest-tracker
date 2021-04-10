package com.facebook.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class DestructorThread {
    private static final DestructorList sDestructorList = new DestructorList();
    private static final DestructorStack sDestructorStack = new DestructorStack((byte) 0);
    private static final ReferenceQueue sReferenceQueue = new ReferenceQueue();
    private static final Thread sThread;

    public static abstract class Destructor extends PhantomReference<Object> {
        private Destructor next;
        private Destructor previous;

        /* access modifiers changed from: protected */
        public abstract void destruct();

        /* synthetic */ Destructor(byte b) {
            this();
        }

        public Destructor(Object obj) {
            super(obj, DestructorThread.sReferenceQueue);
            DestructorThread.sDestructorStack.push(this);
        }

        private Destructor() {
            super(null, DestructorThread.sReferenceQueue);
        }
    }

    static {
        AnonymousClass1 r0 = new Thread("HybridData DestructorThread") {
            /* class com.facebook.jni.DestructorThread.AnonymousClass1 */

            /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|(3:3|(1:5)|8)(1:7)|6) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
            /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:6:0x0046, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void run() {
                /*
                    r5 = this;
                L_0x0000:
                    java.lang.ref.ReferenceQueue r0 = com.facebook.jni.DestructorThread.access$000()     // Catch:{ InterruptedException -> 0x0000 }
                    java.lang.ref.Reference r0 = r0.remove()     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r0 = (com.facebook.jni.DestructorThread.Destructor) r0     // Catch:{ InterruptedException -> 0x0000 }
                    r0.destruct()     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r1 = com.facebook.jni.DestructorThread.Destructor.access$300(r0)     // Catch:{ InterruptedException -> 0x0000 }
                    if (r1 != 0) goto L_0x0046
                    com.facebook.jni.DestructorThread$DestructorStack r1 = com.facebook.jni.DestructorThread.access$100()     // Catch:{ InterruptedException -> 0x0000 }
                    java.util.concurrent.atomic.AtomicReference<com.facebook.jni.DestructorThread$Destructor> r1 = r1.mHead     // Catch:{ InterruptedException -> 0x0000 }
                    r2 = 0
                    java.lang.Object r1 = r1.getAndSet(r2)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r1 = (com.facebook.jni.DestructorThread.Destructor) r1     // Catch:{ InterruptedException -> 0x0000 }
                L_0x0020:
                    if (r1 == 0) goto L_0x0046
                    com.facebook.jni.DestructorThread$Destructor r2 = com.facebook.jni.DestructorThread.Destructor.access$600(r1)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$DestructorList r3 = com.facebook.jni.DestructorThread.access$700()     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r4 = r3.mHead     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r4 = com.facebook.jni.DestructorThread.Destructor.access$600(r4)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread.Destructor.access$602(r1, r4)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r4 = r3.mHead     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread.Destructor.access$602(r4, r1)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r4 = com.facebook.jni.DestructorThread.Destructor.access$600(r1)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread.Destructor.access$302(r4, r1)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r3 = r3.mHead     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread.Destructor.access$302(r1, r3)     // Catch:{ InterruptedException -> 0x0000 }
                    r1 = r2
                    goto L_0x0020
                L_0x0046:
                    com.facebook.jni.DestructorThread$Destructor r1 = com.facebook.jni.DestructorThread.Destructor.access$600(r0)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r2 = com.facebook.jni.DestructorThread.Destructor.access$300(r0)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread.Destructor.access$302(r1, r2)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r1 = com.facebook.jni.DestructorThread.Destructor.access$300(r0)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread$Destructor r0 = com.facebook.jni.DestructorThread.Destructor.access$600(r0)     // Catch:{ InterruptedException -> 0x0000 }
                    com.facebook.jni.DestructorThread.Destructor.access$602(r1, r0)     // Catch:{ InterruptedException -> 0x0000 }
                    goto L_0x0000
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.jni.DestructorThread.AnonymousClass1.run():void");
            }
        };
        sThread = r0;
        r0.start();
    }

    static class Terminus extends Destructor {
        private Terminus() {
            super((byte) 0);
        }

        /* synthetic */ Terminus(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.jni.DestructorThread.Destructor
        public final void destruct() {
            throw new IllegalStateException("Cannot destroy Terminus Destructor.");
        }
    }

    /* access modifiers changed from: package-private */
    public static class DestructorStack {
        final AtomicReference<Destructor> mHead;

        private DestructorStack() {
            this.mHead = new AtomicReference<>();
        }

        /* synthetic */ DestructorStack(byte b) {
            this();
        }

        public final void push(Destructor destructor) {
            Destructor destructor2;
            do {
                destructor2 = this.mHead.get();
                destructor.next = destructor2;
            } while (!this.mHead.compareAndSet(destructor2, destructor));
        }
    }

    /* access modifiers changed from: package-private */
    public static class DestructorList {
        final Destructor mHead = new Terminus((byte) 0);

        public DestructorList() {
            this.mHead.next = new Terminus((byte) 0);
            this.mHead.next.previous = this.mHead;
        }
    }
}
