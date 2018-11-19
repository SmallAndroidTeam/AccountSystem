package of.account.bq.Bean;

import android.graphics.drawable.Drawable;

public class PersonInfo {
    private Drawable face;
    private String fingerId;
    private String name;

    public PersonInfo(Drawable face, String fingerId, String name) {
        this.face = face;
        this.fingerId = fingerId;
        this.name = name;
    }

    public PersonInfo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFingerId() {

        return fingerId;
    }

    public void setFingerId(String fingerId) {
        this.fingerId = fingerId;
    }

    public Drawable getFace() {

        return face;
    }

    public void setFace(Drawable face) {
        this.face = face;
    }
}
