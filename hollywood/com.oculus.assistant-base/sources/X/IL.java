package X;

public final class IL {
    public final IU A00;
    public final Ix A01 = new C0937pE();

    public static boolean A00(RunnableC0929p4 p4Var) {
        if (p4Var == null || !p4Var.A0H) {
            return false;
        }
        return true;
    }

    public final RunnableC0929p4 A01(int i) {
        IU iu = this.A00;
        iu.A00();
        try {
            return (RunnableC0929p4) this.A01.get(i);
        } finally {
            iu.unlock();
        }
    }

    public final RunnableC0929p4 A02(int i) {
        IU iu = this.A00;
        iu.lock();
        try {
            Ix ix = this.A01;
            RunnableC0929p4 p4Var = (RunnableC0929p4) ix.get(i);
            if (p4Var != null) {
                ix.remove(i);
            }
            return p4Var;
        } finally {
            iu.unlock();
        }
    }

    public final boolean A03(int i) {
        IU iu = this.A00;
        iu.A00();
        try {
            return A00((RunnableC0929p4) this.A01.get(i));
        } finally {
            iu.unlock();
        }
    }

    public IL(IU iu) {
        this.A00 = iu;
    }
}
