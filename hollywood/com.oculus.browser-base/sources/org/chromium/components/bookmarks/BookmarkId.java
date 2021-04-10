package org.chromium.components.bookmarks;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkId {

    /* renamed from: a  reason: collision with root package name */
    public final long f10812a;
    public final int b;

    public BookmarkId(long j, int i) {
        this.f10812a = j;
        this.b = i;
    }

    public static BookmarkId createBookmarkId(long j, int i) {
        return new BookmarkId(j, i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BookmarkId)) {
            return false;
        }
        BookmarkId bookmarkId = (BookmarkId) obj;
        if (bookmarkId.f10812a == this.f10812a && bookmarkId.b == this.b) {
            return true;
        }
        return false;
    }

    public long getId() {
        return this.f10812a;
    }

    public int getType() {
        return this.b;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = this.b;
        if (i == 1) {
            str = String.valueOf('p');
        } else if (i != 2) {
            str = "";
        } else {
            str = String.valueOf('r');
        }
        sb.append(str);
        sb.append(this.f10812a);
        return sb.toString();
    }
}
