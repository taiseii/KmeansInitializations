import java.util.*;

public class KmeansClustering {
    int k;

    KmeansClustering( int k) {
        this.k = k;
    }

    // Compute labels
    public ArrayList<ClusterPoint> ComputeLabels(ClusterPoint[] points, CentroidPoint[] centroids) {
        ArrayList<ClusterPoint> labels = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            double minDist = Double.MAX_VALUE;
            for (int j = 0; j < centroids.length; j++) {
                double currDist = points[i].sqDistanceTo(centroids[j]);
                if (currDist < minDist) {
                    minDist = currDist;
                    points[i].setCluster(j);
                }

            }
            // System.out.println(points[i].getCluster());
            labels.add(points[i]);
        }
        return labels;
    }

    //computer new cntrodis
    public CentroidPoint[] ComputeCentroid(ArrayList<ClusterPoint> labels) {
        CentroidPoint[] centroids = new CentroidPoint[k];

        for (int i = 0; i < k; i++) {
            int counter = 0;
            double sumX = 0;
            double sumY = 0;
            for (int j = 0; j < labels.size(); j++) {
                if (labels.get(j).getCluster() == i) {
                    counter++;
                    sumX += labels.get(j).getCoords()[0];
                    sumY += labels.get(j).getCoords()[1];
                }
            }
            double[] centroidCoords = new double[2];
            centroidCoords[0] = sumX / counter;
            centroidCoords[1] = sumY / counter;

            CentroidPoint newCP = new CentroidPoint(2, centroidCoords);
            //System.out.println(newCP);
            centroids[i] = newCP;

        }

        return centroids;
    }

}   