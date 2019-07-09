package day9;

import java.util.ArrayList;
import java.util.List;

public class Group implements Component {
    private List<Component> children;
    public void add(Component child) {
        children.add(child);
    }

    public Group() {
        children = new ArrayList<>();
    }

    @Override
    public int getScore(int level) {
        return level + children.stream().mapToInt(c -> c.getScore(level+1)).sum();
    }

    @Override
    public int getGarbageSize() {
        return children.stream().mapToInt(Component::getGarbageSize).sum();
    }

    public int getScore() {
        return getScore(1);
    }

    @Override
    public int buildStructure(char[] chars, int i) {
        while (++i < chars.length) {
            char c = chars[i];
            switch (c) {
                case '{':
                    Group g = new Group();
                    this.add(g);
                    i = g.buildStructure(chars, i);
                    break;
                case '}':
                    return i;
                case '<':
                    Garbage garb = new Garbage();
                    this.add(garb);
                    i = garb.buildStructure(chars, i);
                default: // do nothing
                    break;
            }
        }
        return i;
    }
}
