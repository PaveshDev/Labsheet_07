package application.model;

/**
 * Base class for promotions. Subclasses implement specific discount logic.
 */
public abstract class Promotion {
    private final String name;

    protected Promotion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double apply(double basePrice);
}
