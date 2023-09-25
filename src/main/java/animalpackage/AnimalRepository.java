package animalpackage;

import java.util.ArrayList;

public class AnimalRepository {
    private static ArrayList<Animal> animals = new ArrayList<>();

    public static void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }
}
