public class Point {

    private final int dimension;
    private final double[] coords;
    
    public Point(int dimension) {
        this.dimension = dimension;
        coords = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            coords[i] = 0;
        }
    }

    public Point(int dimension, double[] coords) throws IllegalArgumentException {
        // Check if arguments are okay
        if (coords.length != dimension) {
            throw new IllegalArgumentException("Point dimension and coords size do not match.");
        }

        // Store parameters
        this.dimension = dimension;
        this.coords = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            this.coords[i] = coords[i];
            
        }
    }

    public Point(ClusterPoint p) {
        this.dimension = p.getDimension();
        this.coords = p.getCoords();
    }
    
    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder("");
        for (int i = 0; i < this.dimension; i++) {
            strBuilder.append(String.format("%.0f", coords[i]));
            strBuilder.append(" ");
            
        }
        strBuilder.delete(2*(this.dimension-1), 2*(this.dimension-1));
        strBuilder.append("\n");
        return strBuilder.toString();
    }

    public long sqDistanceTo(Point other) throws IllegalArgumentException {
        verifyDimMatch(other);
        
        // We store the square of the distance to save on Math.sqrt calls.
        long sq_dist = 0;
        double[] other_coords = other.getCoords();
        
        for (int i = 0; i < this.dimension; i++) {
            sq_dist += (coords[i] - other_coords[i])*(coords[i] - other_coords[i]);
        }
        
        // Return squared distance
        return sq_dist;
    }
    
    public void add(Point other) throws IllegalArgumentException {
        verifyDimMatch(other);
        
        double[] other_coords = other.getCoords();
        
        for (int i = 0; i < dimension; i++) {
            this.coords[i] += other_coords[i];
        }
    }
    
    public void verifyDimMatch(Point other) throws IllegalArgumentException {
        if (dimension != other.getDimension()) {
            throw new IllegalArgumentException("Dimension mismatch.");
        }
    }

    public int getDimension() {
        return this.dimension;
    }

    public double[] getCoords() {
        return this.coords;
    }
    
    public void setCoords(double[] other) {
        for (int i = 0; i < this.dimension; i++) {
            this.coords[i] = other[i];
        }
    }

}
