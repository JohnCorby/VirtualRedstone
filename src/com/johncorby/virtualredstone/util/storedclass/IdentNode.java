package com.johncorby.virtualredstone.util.storedclass;

import java.util.*;

public abstract class IdentNode<I, P extends IdentNode, C extends IdentNode> extends Identifiable<I> {
    protected P parent = null;
    protected Set<C> children = new HashSet<>();

    public IdentNode(I identity, P parent) {
        super(identity);
        create(identity, parent);
    }

//    public IdentNode(I identity, IdentNode... children) {
//        super(identity);
//        create(identity, null, children);
//    }

    protected static Identifiable get(java.lang.Class<? extends Identifiable> clazz,
                                      Object identity,
                                      IdentNode parent) {
        for (StoredClass c : classes) {
            if (!clazz.equals(c.getClass())) continue;
            IdentNode i = (IdentNode) c;
            if (Objects.equals(identity, i.identity) &&
                    Objects.equals(parent, i.parent)) return i;
        }
        //throw new IllegalStateException(String.format("%s<%s, %s> doesn't exist",
        //        clazz.getSimpleName(),
        //        identity,
        //        parent));
        return null;
    }

    protected boolean create(I identity, P parent) {
        this.parent = parent;
        if (!super.create(identity)) return false;
        if (parent != null) parent.children.add(this);
        return true;
    }

    @Override
    protected final boolean create(I identity) {
        return true;
    }

    @Override
    public boolean dispose() {
        if (!exists()) return false;
        if (parent != null) parent.children.remove(this);
        children.forEach(IdentNode::dispose);
        return super.dispose();
    }

    public P getParent() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        return parent;
    }

    public Set<C> getChildren() {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        return children;
    }

    @Override
    public String toString() {
        return String.format("%s<%s, %s>",
                getClass().getSimpleName(),
                identity,
                parent);
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) return false;
        IdentNode i = (IdentNode) obj;
        return Objects.equals(identity, i.identity) &&
                Objects.equals(parent, i.parent);
    }

    @Override
    public List<String> getDebug() {
        List<String> r = new ArrayList<>();
        r.add(toString());
        r.add(Arrays.toString(children.toArray()));
        return r;
    }
}
