package animalpackage;

public class Animal {
    private AnimalType type;
    private String name;

    public Animal() {

    }

    public Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
