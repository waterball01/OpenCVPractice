import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

import static org.opencv.core.Core.countNonZero;

public class OpenCVtrial {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture capture = new VideoCapture(0);
        Mat matrix = new Mat();
        capture.read(matrix);
        Rect rectCrop = new Rect(270, 190, 200, 200);
        Mat croppedImage = matrix.submat(rectCrop);
        Mat ColorMat = new Mat();
        Mat mask = new Mat();
        Mat hsv = croppedImage.clone();
//    Imgproc.cvtColor(croppedImage, hsv, Imgproc.COLOR_BGR2HSV);

        while (true) {

            capture.read(matrix);


            Imgproc.cvtColor(croppedImage, hsv, Imgproc.COLOR_BGR2HSV);
            Core.inRange(hsv, new Scalar(1, 82, 125), new Scalar(50, 255, 255), hsv);
//        Imgproc.cvtColor(croppedImage, hsv, Imgproc.COLOR_BGR2HSV);
            Mat colored = new Mat();

            //Core.inRange(croppedImage, new Scalar(63, 92, 131), new Scalar(0, 255, 255), ColorMat);
            Core.add(croppedImage,croppedImage,colored,hsv);
            int numPixels = Core.countNonZero(hsv);
            if (numPixels>200*200/4) {
                System.out.println("Yeep!");
            }

            //HighGui.imshow("Image", matrix);
            HighGui.imshow("crop", croppedImage);
            //HighGui.imshow("ColorMat", ColorMat);
            HighGui.imshow("Colored", colored);
            HighGui.imshow("mask", hsv);
            HighGui.waitKey(1);
        }
    }
}

