package sun.net.ftp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class FtpDirEntry {
    private Date created;
    private HashMap<String, String> facts;
    private String group;
    private Date lastModified;
    private final String name;
    private boolean[][] permissions;
    private long size;
    private Type type;
    private String user;

    public enum Type {
        FILE,
        DIR,
        PDIR,
        CDIR,
        LINK
    }

    public enum Permission {
        USER(0),
        GROUP(1),
        OTHERS(2);
        
        int value;

        private Permission(int v) {
            this.value = v;
        }
    }

    private FtpDirEntry() {
        this.user = null;
        this.group = null;
        this.size = -1;
        this.created = null;
        this.lastModified = null;
        this.type = Type.FILE;
        this.permissions = null;
        this.facts = new HashMap<>();
        this.name = null;
    }

    public FtpDirEntry(String name2) {
        this.user = null;
        this.group = null;
        this.size = -1;
        this.created = null;
        this.lastModified = null;
        this.type = Type.FILE;
        this.permissions = null;
        this.facts = new HashMap<>();
        this.name = name2;
    }

    public String getName() {
        return this.name;
    }

    public String getUser() {
        return this.user;
    }

    public FtpDirEntry setUser(String user2) {
        this.user = user2;
        return this;
    }

    public String getGroup() {
        return this.group;
    }

    public FtpDirEntry setGroup(String group2) {
        this.group = group2;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public FtpDirEntry setSize(long size2) {
        this.size = size2;
        return this;
    }

    public Type getType() {
        return this.type;
    }

    public FtpDirEntry setType(Type type2) {
        this.type = type2;
        return this;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public FtpDirEntry setLastModified(Date lastModified2) {
        this.lastModified = lastModified2;
        return this;
    }

    public boolean canRead(Permission p) {
        boolean[][] zArr = this.permissions;
        if (zArr != null) {
            return zArr[p.value][0];
        }
        return false;
    }

    public boolean canWrite(Permission p) {
        boolean[][] zArr = this.permissions;
        if (zArr != null) {
            return zArr[p.value][1];
        }
        return false;
    }

    public boolean canExexcute(Permission p) {
        boolean[][] zArr = this.permissions;
        if (zArr != null) {
            return zArr[p.value][2];
        }
        return false;
    }

    public FtpDirEntry setPermissions(boolean[][] permissions2) {
        this.permissions = permissions2;
        return this;
    }

    public FtpDirEntry addFact(String fact, String value) {
        this.facts.put(fact.toLowerCase(), value);
        return this;
    }

    public String getFact(String fact) {
        return this.facts.get(fact.toLowerCase());
    }

    public Date getCreated() {
        return this.created;
    }

    public FtpDirEntry setCreated(Date created2) {
        this.created = created2;
        return this;
    }

    public String toString() {
        if (this.lastModified == null) {
            return this.name + " [" + ((Object) this.type) + "] (" + this.user + " / " + this.group + ") " + this.size;
        }
        return this.name + " [" + ((Object) this.type) + "] (" + this.user + " / " + this.group + ") {" + this.size + "} " + DateFormat.getDateInstance().format(this.lastModified);
    }
}
