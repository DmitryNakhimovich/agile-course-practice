package ru.unn.agile.threeDimShapesArea.Model;


public class Parallelepiped implements Shape {

    private final double height;
    private final double width;
    private final double depth;

    public Parallelepiped(final double height, final double width, final double depth) {
        if (height <= 0 || width <= 0 || depth <= 0) {
            throw new IllegalArgumentException(ShapesAreaConstants.PARAMETERS_CAN_NOT_BE_NEGATIVE);
        }

        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    @Override
    public double getArea() {
        return 2 * (height * width + width * depth + height * depth);
    }
}
