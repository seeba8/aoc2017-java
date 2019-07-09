package day9;

public class Garbage implements Component {
    private StringBuilder content = new StringBuilder();
    @Override
    public int getScore(int level) {
        return 0;
    }

    @Override
    public int getGarbageSize() {
        return content.length();
    }

    @Override
    public int buildStructure(char[] chars, int i) {
        while (++i < chars.length) {
            char c = chars[i];
            switch (c) {
                case '!':
                    i+= 1;
                    continue;
                case '>':
                    return i;
                default: // do nothing
                    content.append(c);
                    break;
            }
        }
        return i;

    }
}
