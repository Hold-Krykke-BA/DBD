import Pets.Cat;
import Pets.Dog;
import Pets.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {
        //String sql = "select * from vet.dogs;";
        //String sql = "select * from vet.cats;";
//        String petsql = "select * from vet.pets;";
//        List<Pet> pets = getPets(petsql);
//        for (Pet pet : pets) {
//            System.out.println(pet);
//        }
//        int pet_id,
//        String pet_name,
//        int pet_age,
//        int vet_id)
        Pet testPet = new Pet("java_interface_pet", 99, 1);
        Cat testCat = new Cat("interface_cat", 88, 2, 5);
        Dog testDog = new Dog("interface_dog", 77, 1, 8);
        insertPet(testPet);
        insertPet(testCat);
        insertPet(testDog);
    }

    private static List<Pet> getPets(String sql) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5433/assignment1";
        Properties props = new Properties();
        props.setProperty("user", "designated");
        props.setProperty("password", "designated");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Pet pet = new Pet(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
                    pets.add(pet);
                }
            }
        }
        return pets;
    }

    private static List<Pet> getCats(String sql) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5433/assignment1";
        Properties props = new Properties();
        props.setProperty("user", "designated");
        props.setProperty("password", "designated");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Pet pet = new Pet(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
                    pets.add(pet);
                }
            }
        }
        return pets;
    }

    private static void insertPet(Pet pet) throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/assignment1";
        Properties props = new Properties();
        props.setProperty("user", "designated");
        props.setProperty("password", "designated");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            CallableStatement stmt = conn.prepareCall("{call insert_pet(?,?, ?, ?, ?)}");
            stmt.setString(1, pet.getPet_name()); //pet name
            stmt.setInt(2, pet.getPet_age()); //pet age
            stmt.setInt(3, pet.getVet_id()); //vet id
            stmt.setNull(4, pet instanceof Dog ? ((Dog)  pet).getBark_pitch() : null);
            stmt.setInt(5, pet instanceof Cat ? ((Cat) pet).getLife_count() : null);
            stmt.execute();
        }
    }
}
