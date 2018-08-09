import java.util.Random;

public class KMeansPP{
    ClusterPoint[] points;
    int k;
    CentroidPoint[] kpp_centroid;

    KMeansPP(ClusterPoint[] points, int k){
        this.points = points;
        this.k = k;
        this.kpp_centroid = new CentroidPoint[k];

    }

    public CentroidPoint[] KppCentroid(){
        Random random = new Random();
        double x = (0 + (9-0)*random.nextDouble() )/10;
        int sampleIndex = (int)Math.ceil(x*points.length);

        //testing
        System.out.println("x:"+x);
        System.out.println("sampleIndex:"+sampleIndex);

        CentroidPoint firstCentroid = new CentroidPoint(points[sampleIndex]);
        kpp_centroid[0] = firstCentroid;

        for(int i=0; i<points.length; i++){
            points[i].setDistance(Double.MAX_VALUE);
        }

        double[] cumulative = new double[points.length];

        for(int j=1; j<k; j++){
            for(int i=0; i<points.length; i++){
                double d = points[i].sqDistanceTo(kpp_centroid[j-1]);
                if(points[i].getDistance() > d){
                    points[i].setDistance(d);
                }
            }

            cumulative[0] = points[0].getDistance();
            for(int i=1; i<points.length; i++){
                cumulative[i] = cumulative[i-1] + points[i].getDistance();
            }

            x = (0 + (9-0)*random.nextDouble() )/10;
            double x2 = x*cumulative[cumulative.length-1];
            if( x2 <= cumulative[0] ){
                sampleIndex = 0;
            } else {
                for(int i=1; i<points.length; i++){
                    if(x2 > cumulative[i-1] && x2 <= cumulative[i]){
                        sampleIndex = i;
                    }
                }
            }

            CentroidPoint kpp_cd = new CentroidPoint( points[sampleIndex] );
            kpp_centroid[j] = kpp_cd;

        }


        return kpp_centroid;
    }



}