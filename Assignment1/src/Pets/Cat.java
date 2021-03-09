package Pets;

import java.util.Objects;

public class Cat extends Pet {
    int life_count;

    public Cat(int pet_id, String pet_name, int pet_age, int vet_id, int life_count) {
        super(pet_id, pet_name, pet_age, vet_id);
        this.life_count = life_count;
    }

    public Cat(String pet_name, int pet_age, int vet_id, int life_count) {
        super(pet_name, pet_age, vet_id);
        this.life_count = life_count;
    }

    public int getLife_count() {
        return life_count;
    }

    public void setLife_count(int life_count) {
        this.life_count = life_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return getLife_count() == cat.getLife_count();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLife_count());
    }

    @Override
    public String toString() {
        return "Cat\n" +
                "Pet ID = " + pet_id +
                ", Pet Name ='" + pet_name + '\'' +
                ", Pet Age = " + pet_age +
                ", Life Count = " + life_count +
                ", Vet ID = " + vet_id + "\n";
    }
}
