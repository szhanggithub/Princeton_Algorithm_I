import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class BruteCollinearPoints {

    private int numSeg;
    private LineSegment[] ls;

// finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException("Brute force constructor argument == null");
        for (Point tp : points) {
             if (tp == null) {
                throw new NullPointerException("Brute force constructor point == null");
             }
         } 

        numSeg = 0;
        // StdOut.println(points.length);
        Arrays.sort(points);

        ls = new LineSegment[points.length - 1];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY)
                    throw new IllegalArgumentException("repeated point");
                for (int k = j + 1; k < points.length; k++ ) {
                    if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
                        for (int l = k + 1; l < points.length; l++ ) {
                            if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])) {
                                ls[numSeg++] = new LineSegment(points[i], points[l]);
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numSeg;
    }
// the line segments
    public LineSegment[] segments() {
        LineSegment[] lsout = new LineSegment[numSeg];
        for (int i = 0; i < numSeg; i++ ) {
            lsout[i] = ls[i];
        }
        return lsout;
    }

    // client
    public static void main(String[] args) {

    // read the N points from a file
    In in = new In(args[0]);

    int N = in.readInt();
    // StdOut.println(N);

    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
        // StdOut.println("x" + x + "y" + y);

    }

    // draw the points
    StdDraw.show(0);
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
}

}
