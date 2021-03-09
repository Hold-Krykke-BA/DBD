package Pets;

import java.util.Objects;

public class Pet {
    int pet_id;
    String pet_name;
    int pet_age;
    int vet_id;

    public Pet(int pet_id, String pet_name, int pet_age, int vet_id) {
        this.pet_id = pet_id;
        this.pet_name = pet_name;
        this.pet_age = pet_age;
        this.vet_id = vet_id;
    }

    public Pet(String pet_name, int pet_age, int vet_id) {
        this.pet_name = pet_name;
        this.pet_age = pet_age;
        this.vet_id = vet_id;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public int getPet_age() {
        return pet_age;
    }

    public void setPet_age(int pet_age) {
        this.pet_age = pet_age;
    }

    public int getVet_id() {
        return vet_id;
    }

    public void setVet_id(int vet_id) {
        this.vet_id = vet_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getPet_id() == pet.getPet_id() && getPet_age() == pet.getPet_age() && getVet_id() == pet.getVet_id() && Objects.equals(getPet_name(), pet.getPet_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPet_id(), getPet_name(), getPet_age(), getVet_id());
    }

    @Override
    public String toString() {
        return "Other Pet Type\n" +
                "Pet ID = " + pet_id +
                ", Pet Name ='" + pet_name + '\'' +
                ", Pet Age = " + pet_age +
                ", Vet ID = " + vet_id + "\n";
    }
}
