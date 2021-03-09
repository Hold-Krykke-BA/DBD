import Pets.Cat;
import Pets.Dog;
import Pets.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    final static String runi = "jdbc:postgresql://localhost:5433/assignment1";
    final static String camilla = "jdbc:postgresql://localhost:5432/soft2021";
    final static String asger = "";

    public static void main(String[] args) throws SQLException {
        Pet testPet = new Pet("java_interface_pet", 99, 1);
        Cat testCat = new Cat("interface_cat", 88, 2, 5);
        Dog testDog = new Dog("interface_dog", 77, 1, 8);
        insertPet(testPet,camilla);
        insertPet(testCat,camilla);
        insertPet(testDog,camilla);

        printPets("CAT");
    }

    private static void printPets(String type) throws SQLException {
        List<Pet> pets;
        String petsql = "";
        switch(type) {
            case "CAT":
                petsql = "select * from vet.cats;";
                break;
            case "DOG":
                petsql = "select * from vet.dogs;";
                break;
            default:
                petsql = "select * from vet.pets;";
        }

        pets = getPets(petsql,camilla, type);
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    private static List<Pet> getPets(String sql, String conStr, String type) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String url = conStr;
        Properties props = new Properties();
        props.setProperty("user", "designated");
        props.setProperty("password", "designated");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    switch(type) {
                        case "CAT":
                            Pet cat = new Cat(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(4));
                            pets.add(cat);
                            break;
                        case "DOG":
                            Pet dog = new Dog(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(4));
                            pets.add(dog);
                            break;
                        default:
                            Pet pet = new Pet(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
                            pets.add(pet);
                    }

                }
            }
        }
        return pets;
    }

    private static void insertPet(Pet pet, String conStr) throws SQLException {
        String url = conStr;
        Properties props = new Properties();
        props.setProperty("user", "designated");
        props.setProperty("password", "designated");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            //CallableStatement stmt = conn.prepareCall("CALL vet.insert_pet(?,?, ?, ?, ?)"); //Workaround for using CallableStatement: https://github.com/pgjdbc/pgjdbc/issues/1413
            PreparedStatement stmt = conn.prepareStatement("call vet.insert_pet(?,?, ?, ?, ?)");
            stmt.setString(1, pet.getPet_name()); //pet name
            stmt.setInt(2, pet.getPet_age()); //pet age
            stmt.setInt(3, pet.getVet_id()); //vet id
            stmt.setObject(4, null, Types.INTEGER);
            stmt.setObject(5, null, Types.INTEGER);
            stmt.setObject(4, (pet instanceof Dog ? ((Dog) pet).getBark_pitch() : null), Types.INTEGER);;
            stmt.setObject(5, (pet instanceof Cat ? ((Cat) pet).getLife_count() : null), Types.INTEGER);;
            stmt.execute();
        }catch(Exception ex){
            System.out.println("\n\n" + ex.getMessage());
            System.out.println("\n\n" + ex.toString());
        }
    }
}
