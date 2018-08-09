public class ClusterPoint extends Point{
    private int cluster_label;
    double distance;
    
    public ClusterPoint(int dimension, double[] coords) throws IllegalArgumentException {
        super(dimension, coords);
        
        this.cluster_label = -1;
    }
    
    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder("");
        int dimension = this.getDimension();
        double[] coords = this.getCoords();
        
        for (int i = 0; i < dimension; i++) {
            strBuilder.append(String.format("%.0f", coords[i]));
            strBuilder.append(" ");
            
        }
        strBuilder.append(this.cluster_label);
        strBuilder.append("\n");
        return strBuilder.toString();
    }
    
    public boolean isLabeled(){
        return this.cluster_label != -1;
    }

    public int getCluster() {
        return cluster_label;
    }

    public void setCluster(int cluster_label) {
        this.cluster_label = cluster_label;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public double getDistance(){
        return this.distance;
    }
}
