package java.nio.file.attribute;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public final class AclEntry {
    private final Set<AclEntryFlag> flags;
    private volatile int hash;
    private final Set<AclEntryPermission> perms;
    private final AclEntryType type;
    private final UserPrincipal who;

    private AclEntry(AclEntryType type2, UserPrincipal who2, Set<AclEntryPermission> perms2, Set<AclEntryFlag> flags2) {
        this.type = type2;
        this.who = who2;
        this.perms = perms2;
        this.flags = flags2;
    }

    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Set<AclEntryFlag> flags;
        private Set<AclEntryPermission> perms;
        private AclEntryType type;
        private UserPrincipal who;

        private Builder(AclEntryType type2, UserPrincipal who2, Set<AclEntryPermission> perms2, Set<AclEntryFlag> flags2) {
            this.type = type2;
            this.who = who2;
            this.perms = perms2;
            this.flags = flags2;
        }

        public AclEntry build() {
            AclEntryType aclEntryType = this.type;
            if (aclEntryType != null) {
                UserPrincipal userPrincipal = this.who;
                if (userPrincipal != null) {
                    return new AclEntry(aclEntryType, userPrincipal, this.perms, this.flags);
                }
                throw new IllegalStateException("Missing who component");
            }
            throw new IllegalStateException("Missing type component");
        }

        public Builder setType(AclEntryType type2) {
            if (type2 != null) {
                this.type = type2;
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPrincipal(UserPrincipal who2) {
            if (who2 != null) {
                this.who = who2;
                return this;
            }
            throw new NullPointerException();
        }

        private static void checkSet(Set<?> set, Class<?> type2) {
            for (Object e : set) {
                if (e != null) {
                    type2.cast(e);
                } else {
                    throw new NullPointerException();
                }
            }
        }

        public Builder setPermissions(Set<AclEntryPermission> perms2) {
            Set<AclEntryPermission> perms3;
            if (perms2.isEmpty()) {
                perms3 = Collections.emptySet();
            } else {
                perms3 = EnumSet.copyOf(perms2);
                checkSet(perms3, AclEntryPermission.class);
            }
            this.perms = perms3;
            return this;
        }

        public Builder setPermissions(AclEntryPermission... perms2) {
            Set<AclEntryPermission> set = EnumSet.noneOf(AclEntryPermission.class);
            for (AclEntryPermission p : perms2) {
                if (p != null) {
                    set.add(p);
                } else {
                    throw new NullPointerException();
                }
            }
            this.perms = set;
            return this;
        }

        public Builder setFlags(Set<AclEntryFlag> flags2) {
            Set<AclEntryFlag> flags3;
            if (flags2.isEmpty()) {
                flags3 = Collections.emptySet();
            } else {
                flags3 = EnumSet.copyOf(flags2);
                checkSet(flags3, AclEntryFlag.class);
            }
            this.flags = flags3;
            return this;
        }

        public Builder setFlags(AclEntryFlag... flags2) {
            Set<AclEntryFlag> set = EnumSet.noneOf(AclEntryFlag.class);
            for (AclEntryFlag f : flags2) {
                if (f != null) {
                    set.add(f);
                } else {
                    throw new NullPointerException();
                }
            }
            this.flags = set;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder(null, null, Collections.emptySet(), Collections.emptySet());
    }

    public static Builder newBuilder(AclEntry entry) {
        return new Builder(entry.type, entry.who, entry.perms, entry.flags);
    }

    public AclEntryType type() {
        return this.type;
    }

    public UserPrincipal principal() {
        return this.who;
    }

    public Set<AclEntryPermission> permissions() {
        return new HashSet(this.perms);
    }

    public Set<AclEntryFlag> flags() {
        return new HashSet(this.flags);
    }

    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }
        if (ob == null || !(ob instanceof AclEntry)) {
            return false;
        }
        AclEntry other = (AclEntry) ob;
        if (this.type == other.type && this.who.equals(other.who) && this.perms.equals(other.perms) && this.flags.equals(other.flags)) {
            return true;
        }
        return false;
    }

    private static int hash(int h, Object o) {
        return (h * 127) + o.hashCode();
    }

    public int hashCode() {
        if (this.hash != 0) {
            return this.hash;
        }
        this.hash = hash(hash(hash(this.type.hashCode(), this.who), this.perms), this.flags);
        return this.hash;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.who.getName());
        sb.append(':');
        for (AclEntryPermission perm : this.perms) {
            sb.append(perm.name());
            sb.append('/');
        }
        sb.setLength(sb.length() - 1);
        sb.append(':');
        if (!this.flags.isEmpty()) {
            for (AclEntryFlag flag : this.flags) {
                sb.append(flag.name());
                sb.append('/');
            }
            sb.setLength(sb.length() - 1);
            sb.append(':');
        }
        sb.append(this.type.name());
        return sb.toString();
    }
}
