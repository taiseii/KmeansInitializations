
/**
 * Assignment 2.
 * 
 * Modified version that saves the output of different initializations in different subfolders 
 * (You need to create the corresponding subfolders in the folder "output" manually in the file system)
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class assignment2 {
    int k = 10;

    int n, d, minPts;
    double epsilon;
    ClusterPoint[] points;

    /**
     * Set to true before submitting. Don't forget to save.
     */
    static boolean submitting = false;

    public enum Initialization {
        FIRSTK, GONZALES, PLUSPLUS, UNIFORM, MYOWN
    };

    public static void main(String[] args) throws IOException {
        int i = 4;
        String path_in = String.format("/home/jh0iku/Documents/q4/JBIO40/w2/input/%02d.in", i);
        String path_out = String.format("/home/jh0iku/Documents/q4/JBIO40/w2/kpp_output/%02d.out", i);
        (new assignment2()).run(submitting, path_in, path_out);

        // for simplicity I removed these parts for now.
        // for (Initialization init: Initialization.values()){
        // for (int i = 2; i < 5; i++) {
        // String path_in =
        // String.format("/home/jh0iku/Documents/q4/JBIO40/w2/input/%02d.in", i);
        // String path_out =
        // String.format("/home/jh0iku/Documents/q4/JBIO40/w2/output/%02d.out", init,
        // i);
        // (new assignment2()).run(submitting, path_in, path_out, init);
        // }
        // }
    }

    public void run(boolean submitting, String path_in, String path_out/* , Initialization init */)
            throws IOException, FileNotFoundException {

        readInput(submitting, path_in);

        // use the first k points of the input as centroids
        // in order to avoid null pointer exception in the writer
        CentroidPoint[] centroids = new CentroidPoint[k];
        for (int i = 0; i < k; i++) {
            centroids[i] = new CentroidPoint(points[i]);
        }

        // initialize with gonzales
        // Gonzales Gs = new Gonzales(points, k);
        // centroids = Gs.gonzalesCentroid();

        // initialize with uniform
        // Uniform Um = new Uniform(k, points);
        // centroids = Um.uniformCentroid();

        // initialize with KnnPP
        KMeansPP KMp = new KMeansPP(points, k);
        centroids = KMp.KppCentroid();

        // initializing of Kmeans here
        KmeansClustering Kmean = new KmeansClustering(k);
        CentroidPoint[] newCentroids;
        boolean compute = true;
        int stepCounter = 0;
        while (compute) {
            stepCounter++;
            ArrayList<ClusterPoint> initialLabele = Kmean.ComputeLabels(points, centroids);
            newCentroids = Kmean.ComputeCentroid(initialLabele);

            // compare centroids
            int eCounter = 0;
            for (int i = 0; i < k; i++) {
                // System.out.println("newCentroids: " + newCentroids[i] + " :centroids " +
                // centroids[i]);
                if (newCentroids[i].getCoords()[0] == centroids[i].getCoords()[0]) {
                    if (newCentroids[i].getCoords()[1] == centroids[i].getCoords()[1]) {
                        eCounter++;
                    }
                }
            }

            if (eCounter == k) {
                compute = false;
            }

            // update centroids
            centroids = newCentroids;

        }

        System.out.println("performance: " + stepCounter);

        if (submitting) {
            printOutput(centroids);
        } else {
            writeOutput(centroids, path_out);
        }
        // System.err.println();
    }

    /**
     * 
     * @param stdIO if true, use System.in (USE FOR SUBMITTING)
     * @param path  if !stdIO read from this file (USE FOR TESTING)
     * @throws FileNotFoundException
     */
    private void readInput(boolean stdIO, String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc;
        if (stdIO) {
            sc = new Scanner(System.in);
        } else {
            sc = new Scanner(file).useLocale(Locale.US);
        }
        n = sc.nextInt();
        d = sc.nextInt();
        epsilon = sc.nextDouble();
        minPts = sc.nextInt();

        points = new ClusterPoint[n];
        // Read n points
        for (int i = 0; i < n; i++) {
            double[] coords = new double[d];

            // Read d coords
            for (int j = 0; j < d; j++) {
                coords[j] = sc.nextDouble();
            }

            // Add point to array
            ClusterPoint p = new ClusterPoint(d, coords);
            points[i] = p;
        }
    }

    private void printOutput(CentroidPoint[] centroids) {
        // Assignment number
        System.out.format("%d\n", 2);
        System.out.format("%d %d %d\n", n, d, centroids.length);
        for (int i = 0; i < n; i++) {
            ClusterPoint point = points[i];
            System.out.print(point);
        }
        for (int i = 0; i < k; i++) {
            CentroidPoint point = centroids[i];
            System.out.print(point);
        }
    }

    private void writeOutput(CentroidPoint[] centroids, String path_out)
            throws UnsupportedEncodingException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path_out), "utf-8"))) {
            writer.write(String.format("%d\n", 2));
            writer.write(String.format("%d %d %d\n", n, d, centroids.length));
            for (int i = 0; i < n; i++) {
                ClusterPoint point = points[i];
                writer.write(point.toString());
            }
            for (int i = 0; i < k; i++) {
                CentroidPoint point = centroids[i];
                writer.write(point.toString());
            }
        }
    }
}
