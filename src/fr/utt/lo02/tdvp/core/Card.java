package fr.utt.lo02.tdvp.core;

class Card {
    /**
     * The possible shapes of a card
     */
    public enum Shape {
        SQUARE,
        TRIANGLE,
        CIRCLE,
    }

    /**
     * The possible colors of a card
     */
    public enum Color {
        RED,
        GREEN,
        BLUE,
    }

    private Shape shape;
    private Color color;
    private boolean filled;

    /**
     * Instantiate a card
     * @param shape the shape of the card
     * @param color the color of the card
     * @param filled the filling of the card
     */
    public Card(Shape shape, Color color, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.filled = filled;
    }

    /**
     * Get the shape of the card
     * @return the shape of the card
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Get the color of the card
     * @return the color of the card
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get the filling of the card
     * @return the filling of the card
     */
    public boolean getFilled() {
        return this.filled;
    }

    /**
     * Parse the card to a string defining the card
     * @return a string defining the card
     */
    public String toString() {
        String shape, color, filled;

        // Shape
        switch (this.shape) {
            case SQUARE: shape = "carré";
                break;
            case TRIANGLE: shape = "triangle";
                break;
            case CIRCLE: shape = "cercle";
                break;
            default: shape = this.shape.toString();
        }

        // Color
        switch (this.color) {
            case RED: color = "rouge";
                break;
            case GREEN: color = "vert";
                break;
            case BLUE: color = "bleu";
                break;
            default: color = this.color.toString();
        }

        // Filled
        if (this.filled) filled = "rempli";
        else filled = "non rempli";

        return shape + ", " + color + ", " + filled;
    }
}
