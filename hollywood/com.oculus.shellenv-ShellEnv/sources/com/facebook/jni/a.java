package com.facebook.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class a {
    private static final b a = new b();
    private static final c b = new c((byte) 0);
    private static final ReferenceQueue c = new ReferenceQueue();
    private static final Thread d;

    /* renamed from: com.facebook.jni.a$a  reason: collision with other inner class name */
    public static abstract class AbstractC0000a extends PhantomReference {
        private AbstractC0000a a;
        private AbstractC0000a b;

        private AbstractC0000a() {
            super(null, a.c);
        }

        /* synthetic */ AbstractC0000a(byte b2) {
            this();
        }

        public AbstractC0000a(Object obj) {
            super(obj, a.c);
            a.b.a(this);
        }

        /* access modifiers changed from: protected */
        public abstract void a();
    }

    /* access modifiers changed from: package-private */
    public static class b {
        private final AbstractC0000a a = new d((byte) 0);

        public b() {
            this.a.a = new d((byte) 0);
            this.a.a.b = this.a;
        }

        static /* synthetic */ void b(AbstractC0000a aVar) {
            aVar.a.b = aVar.b;
            aVar.b.a = aVar.a;
        }

        public final void a(AbstractC0000a aVar) {
            aVar.a = this.a.a;
            this.a.a = aVar;
            aVar.a.b = aVar;
            aVar.b = this.a;
        }
    }

    /* access modifiers changed from: package-private */
    public static class c {
        private final AtomicReference a;

        private c() {
            this.a = new AtomicReference();
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final void a() {
            AbstractC0000a aVar = (AbstractC0000a) this.a.getAndSet(null);
            while (aVar != null) {
                AbstractC0000a aVar2 = aVar.a;
                a.a.a(aVar);
                aVar = aVar2;
            }
        }

        public final void a(AbstractC0000a aVar) {
            AbstractC0000a aVar2;
            do {
                aVar2 = (AbstractC0000a) this.a.get();
                aVar.a = aVar2;
            } while (!this.a.compareAndSet(aVar2, aVar));
        }
    }

    static class d extends AbstractC0000a {
        private d() {
            super((byte) 0);
        }

        /* synthetic */ d(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.jni.a.AbstractC0000a
        public final void a() {
            throw new IllegalStateException("Cannot destroy Terminus Destructor.");
        }
    }

    static {
        b bVar = new b("HybridData DestructorThread");
        d = bVar;
        bVar.start();
    }
}
