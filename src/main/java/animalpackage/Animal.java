package animalpackage;

public class Animal {
    private final AnimalType animalType;
    private final String name;

    public Animal(AnimalType animalType, String name) {
        this.animalType = animalType;
        this.name = name;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }
}
