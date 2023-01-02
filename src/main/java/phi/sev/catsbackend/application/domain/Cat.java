package phi.sev.catsbackend.application.domain;

import java.util.UUID;

public class Cat {

    UUID id;
    String name;

    CatStatus status;

    public Cat(UUID id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = CatStatus.valueOf(status);
    }

    public void pet() {
        this.status = CatStatus.HAPPY;
    }

    public void feed() {
        this.status = CatStatus.SATURATED;
    }

    public void entertain() {
        this.status = CatStatus.HUNGRY;
    }

    public void ignore() {
        this.status = CatStatus.BORED;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CatStatus getStatus() {
        return status;
    }
}
