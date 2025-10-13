package application.promotion;

/**
 * Defines operations that concrete promotions must implement.
 */
public interface Promotion {
    String name();

    double apply(double amount);
}
