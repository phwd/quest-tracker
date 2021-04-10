package dalvik.system;

import java.util.Objects;

public final class BlockGuard {
    public static final Policy LAX_POLICY = new Policy() {
        /* class dalvik.system.BlockGuard.AnonymousClass1 */

        public String toString() {
            return "LAX_POLICY";
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onWriteToDisk() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onReadFromDisk() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onNetwork() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onUnbufferedIO() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public void onExplicitGc() {
        }

        @Override // dalvik.system.BlockGuard.Policy
        public int getPolicyMask() {
            return 0;
        }
    };
    public static final VmPolicy LAX_VM_POLICY = new VmPolicy() {
        /* class dalvik.system.BlockGuard.AnonymousClass2 */

        public String toString() {
            return "LAX_VM_POLICY";
        }

        @Override // dalvik.system.BlockGuard.VmPolicy
        public void onPathAccess(String path) {
        }
    };
    private static ThreadLocal<Policy> threadPolicy = new ThreadLocal<Policy>() {
        /* class dalvik.system.BlockGuard.AnonymousClass3 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Policy initialValue() {
            return BlockGuard.LAX_POLICY;
        }
    };
    private static volatile VmPolicy vmPolicy = LAX_VM_POLICY;

    public interface Policy {
        int getPolicyMask();

        void onExplicitGc();

        void onNetwork();

        void onReadFromDisk();

        void onUnbufferedIO();

        void onWriteToDisk();
    }

    public interface VmPolicy {
        void onPathAccess(String str);
    }

    @Deprecated
    public static class BlockGuardPolicyException extends RuntimeException {
        private final String mMessage;
        private final int mPolicyState;
        private final int mPolicyViolated;

        public BlockGuardPolicyException(int policyState, int policyViolated) {
            this(policyState, policyViolated, null);
        }

        public BlockGuardPolicyException(int policyState, int policyViolated, String message) {
            this.mPolicyState = policyState;
            this.mPolicyViolated = policyViolated;
            this.mMessage = message;
            fillInStackTrace();
        }

        public int getPolicy() {
            return this.mPolicyState;
        }

        public int getPolicyViolation() {
            return this.mPolicyViolated;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("policy=");
            sb.append(this.mPolicyState);
            sb.append(" violation=");
            sb.append(this.mPolicyViolated);
            if (this.mMessage == null) {
                str = "";
            } else {
                str = " msg=" + this.mMessage;
            }
            sb.append(str);
            return sb.toString();
        }
    }

    public static Policy getThreadPolicy() {
        return threadPolicy.get();
    }

    public static void setThreadPolicy(Policy policy) {
        threadPolicy.set((Policy) Objects.requireNonNull(policy));
    }

    public static VmPolicy getVmPolicy() {
        return vmPolicy;
    }

    public static void setVmPolicy(VmPolicy policy) {
        vmPolicy = (VmPolicy) Objects.requireNonNull(policy);
    }

    private BlockGuard() {
    }
}
