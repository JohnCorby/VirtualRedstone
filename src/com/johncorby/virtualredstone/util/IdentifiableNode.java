package com.johncorby.virtualredstone.util;

import java.util.*;

public class IdentifiableNode<I, P extends IdentifiableNode, C extends IdentifiableNode> extends Identifiable<I> {
    protected P parent = null;
    protected Set<C> children = new HashSet<>();

    public IdentifiableNode(I identity, P parent) {
        super(identity);
        create(identity, parent);
    }

//    public IdentifiableNode(I identity, IdentifiableNode... children) {
//        super(identity);
//        create(identity, null, children);
//    }

    public static IdentifiableNode get(Object identity, IdentifiableNode parent) {
        return (IdentifiableNode) get(IdentifiableNode.class, identity, parent);
    }

    protected static Identifiable get(java.lang.Class<? extends Identifiable> clazz,
                                      Object identity,
                                      IdentifiableNode parent) {
        for (Class c : classes) {
            if (!clazz.equals(c.getClass())) continue;
            IdentifiableNode i = (IdentifiableNode) c;
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
        if (exists()) return false;
        if (parent != null) {
            this.parent = parent;
            parent.children.add(this);
        }
        return super.create(identity);
    }

    @Override
    protected final boolean create(I identity) {
        return true;
    }

    @Override
    public boolean dispose() {
        if (!exists()) return false;
        parent.children.remove(this);
        children.forEach(IdentifiableNode::dispose);
        return super.dispose();
    }

//    @Override
//    protected boolean available() {
//        return identity != null && (parent != null || !children.isEmpty());
//    }

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

    // Make sure things work
    public void update() {
        //if (parent != null) parent.update();
        //children.forEach(IdentifiableNode::update);

        // Dispose if no children
        if (children.isEmpty()) dispose();

        // Dispose parent if it doesn't exist
        if (parent != null && !parent.exists()) dispose();

        // Dispose children if they don't exist
        for (IdentifiableNode c : children)
            if (c != null && !c.exists()) dispose();
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
        IdentifiableNode i = (IdentifiableNode) obj;
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
