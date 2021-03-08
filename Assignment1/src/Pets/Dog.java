package Pets;

import java.util.Objects;

public class Dog extends Pet {
    int bark_pitch;

    public Dog(int pet_id, String pet_name, int pet_age, int vet_id, int bark_pitch) {
        super(pet_id, pet_name, pet_age, vet_id);
        this.bark_pitch = bark_pitch;
    }

    public Dog(String pet_name, int pet_age, int vet_id, int bark_pitch) {
        super(pet_name, pet_age, vet_id);
        this.bark_pitch = bark_pitch;
    }

    public int getBark_pitch() {
        return bark_pitch;
    }

    public void setBark_pitch(int bark_pitch) {
        this.bark_pitch = bark_pitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return getBark_pitch() == dog.getBark_pitch();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBark_pitch());
    }

    @Override
    public String toString() {
        return "Dog{" +
                "bark_pitch=" + bark_pitch +
                ", pet_id=" + pet_id +
                ", pet_name='" + pet_name + '\'' +
                ", pet_age=" + pet_age +
                ", vet_id=" + vet_id +
                '}';
    }
}
