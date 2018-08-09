import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class Uniform{
    int k;
    ClusterPoint[] clusterPoints;

    Uniform(int k, ClusterPoint[] clusterPoints){
        this.k = k;
        this.clusterPoints = clusterPoints;
    }

    public CentroidPoint[] uniformCentroid(){
        CentroidPoint[] uCentroids = new CentroidPoint[k];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<clusterPoints.length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<k; i++) {
            System.out.println(list.get(i));
            CentroidPoint uCP = new CentroidPoint(clusterPoints[i]);
            uCentroids[i] = uCP;
        }



        return uCentroids;
    }
}