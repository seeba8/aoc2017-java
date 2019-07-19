package day12;

import java.util.HashSet;
import java.util.Set;

public class Programme {
    Set<Programme> children = new HashSet<>();

    public void addChild(Programme p) {
        children.add(p);
    }

    public int countChildren() {
        Set<Programme> childrenRecursive = getChildrenRecursive();
        childrenRecursive.add(this);
        return childrenRecursive.size();
    }
    public Set<Programme> getChildrenRecursive() {
        Set<Programme> uniqueChildren = new HashSet<>();
        return getChildrenRecursive(uniqueChildren);
    }
    private Set<Programme> getChildrenRecursive(Set<Programme> uniqueChildren) {
        for(Programme child : children) {
            if(!uniqueChildren.contains(child)) {
                uniqueChildren.add(child);
                uniqueChildren.addAll(child.getChildrenRecursive(uniqueChildren));
            }

        }
        return uniqueChildren;
    }
}
