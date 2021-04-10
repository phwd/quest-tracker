package dalvik.system;

public final class BlockGuard {
    public static final Policy LAX_POLICY = new Policy() {
        /* class dalvik.system.BlockGuard.AnonymousClass1 */

        @Override // dalvik.system.BlockGuard.Policy
        public void onExplicitGc() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onNetwork() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onReadFromDisk() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onUnbufferedIO() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onWriteToDisk() {
        }

        public String toString() {
            return "LAX_POLICY";
        }
    };
    public static final VmPolicy LAX_VM_POLICY = new VmPolicy() {
        /* class dalvik.system.BlockGuard.AnonymousClass2 */

        @Override // dalvik.system.BlockGuard.VmPolicy
        public void onPathAccess(String str) {
        }

        public String toString() {
            return "LAX_VM_POLICY";
        }
    };
    private static ThreadLocal threadPolicy = new ThreadLocal() {
        /* class dalvik.system.BlockGuard.AnonymousClass3 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Policy initialValue() {
            return BlockGuard.LAX_POLICY;
        }
    };
    private static volatile VmPolicy vmPolicy = LAX_VM_POLICY;

    public interface Policy {
        void onExplicitGc();

        void onNetwork();

        void onReadFromDisk();

        void onUnbufferedIO();

        void onWriteToDisk();
    }

    public interface VmPolicy {
        void onPathAccess(String str);
    }

    public static Policy getThreadPolicy() {
        return (Policy) threadPolicy.get();
    }

    public static VmPolicy getVmPolicy() {
        return vmPolicy;
    }
}
