package fi.basse.shamery.domain;

/**
 * Model for card pair data imported from json.
 */
public class PairData {
    int type;
    String name;

    public PairData(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
