public class CentroidPoint extends Point {

        public CentroidPoint(int dimension){
                super(dimension);
        }
    
        public CentroidPoint(int dimension, double[] coords) throws IllegalArgumentException {
                super(dimension,coords);
        }

        public CentroidPoint(ClusterPoint p) {
                super(p);
        }
}
