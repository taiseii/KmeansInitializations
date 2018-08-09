public class Gonzales {

    ClusterPoint[] clusterPoints;
    CentroidPoint[] gCentroids;
    int k;
    

    Gonzales(ClusterPoint[] clusterPoints ,int k){
        this.clusterPoints = clusterPoints;
        this.k = k;
        this.gCentroids = new CentroidPoint[k];
    }



    public CentroidPoint[] gonzalesCentroid(){
        
        CentroidPoint firstG = new CentroidPoint(clusterPoints[0]);
        gCentroids[0] = firstG;


        for(int i=0; i<clusterPoints.length; i++){
            clusterPoints[i].setDistance(Double.MAX_VALUE);
        }
    
        for(int j=1;j<gCentroids.length; j++){
    
            for(int l=0; l<clusterPoints.length; l++){
                double x = clusterPoints[l].sqDistanceTo(gCentroids[j-1]);
                if(clusterPoints[l].getDistance() > x){
                    clusterPoints[l].setDistance(x);
                }
            }
    
            double maxDist = 0;
            ClusterPoint farthest = null ;

            for(int l=0; l<clusterPoints.length; l++){
                if(maxDist < clusterPoints[l].getDistance()){
                    maxDist = clusterPoints[l].getDistance();
                    farthest = clusterPoints[l];
                }
            }
    
            CentroidPoint gCP = new CentroidPoint(farthest);
            gCentroids[j] = gCP;

        }

        return gCentroids;

    }



}
