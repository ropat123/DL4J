package org.deeplearning4j.examples.dataexamples;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * /**
 *  This code example is featured in this youtube video
 *
 *  http://www.youtube.com/watch?v=DRHIpeJpJDI
 *
 * This differs slightly from the Video Example,
 * The Video example had the data already downloaded
 * This example includes code that downloads the data
 *
 *  Data is downloaded from
 *
 *
 *  wget http://github.com/myleott/mnist_png/raw/master/mnist_png.tar.gz
 *  followed by tar xzvf mnist_png.tar.gz
 * The Data Directory mnist_png will have two child directories training and testing
 * The training and testing directories will have directories 0-9 with
 * 28 * 28 PNG images of handwritten images
 *
 *
 *
 *
 *
 *  This examples builds on the MnistImagePipelineExample
 *  by giving the user a file chooser to test an image of their choice
 *  against the Nueral Net, will the network think this cat is an 8 or a 1
 *  Seriously you can test anything, but obviously the network was trained on handwritten images
 *  0-9 white digit, black background, so it will work better with stuff closer to what it was
 *  designed for
 *
 */
public class HistologyFileTester extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(HistologyFileTester.class);


    /*
    Create a popup window to allow you to chose an image file to test against the
    trained Neural Network
    Chosen images will be automatically
    scaled to 28*28 grayscale
     */
    public static String fileChose(){
        JFileChooser fc = new JFileChooser();
        int ret = fc.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            String filename = file.getAbsolutePath();
            return filename;
        }
        else {
            return null;
        }
    }
    public static int height = 75;
    public static int width = 75;
    public static int channels = 3;

    public void doGet(HttpServletRequest request, HttpServlet response)
        throws ServletException,IOException{
        ArrayList<Probability> probList = new ArrayList<Probability>();
        ArrayList<Double> prob = new ArrayList<Double>();
        // recordReader.getLabels()
        // In this version Labels are always in order
        // So this is no longer needed
        //List<Integer> labelList = Arrays.asList(2,3,7,1,6,4,0,5,8,9);
        List<Integer> labelList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        // pop up file chooser
//        String filechose = fileChose().toString();

        //LOAD NEURAL NETWORK

        // Where to save model
        File locationToSave = new File("trained_mnist_model"+height+"x"+width+".zip");
        // Check for presence of saved model
        if(locationToSave.exists()){
            System.out.println("\n######Saved Model Found######\n");
        }else{
            System.out.println("\n\n#######File not found!#######");
            System.out.println("This example depends on running ");
            System.out.println("Pathology slides");
            System.out.println("Run that Example First");
            System.out.println("#############################\n\n");


            System.exit(0);
        }

        MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(locationToSave);

        log.info("*********TEST YOUR IMAGE AGAINST SAVED NETWORK********");

//        System.out.println(filechose);
        // FileChose is a string we will need a file

       /* File file = new File(filechose);

        // Use NativeImageLoader to convert to numerical matrix

        NativeImageLoader loader = new NativeImageLoader(height, width, channels);

        // Get the image into an INDarray

        INDArray image = loader.asMatrix(file);

        // 0-255
        // 0-1
        DataNormalization scaler = new ImagePreProcessingScaler(0,1);
        scaler.transform(image);
        // Pass through to neural Net

        INDArray output = model.output(image);

        log.info("## The FILE CHOSEN WAS " + filechose);
        log.info("## The Neural Nets Pediction ##");
        log.info("## list of probabilities per label ##");
        log.info("## List of Labels in Order## ");
        log.info(output.toString());
        log.info(labelList.toString());  */

        File file=new File("");
        for (int x=1;x<15;x++) {
            for (int y = 0; y < 70; y++) {

                //Run through all files
                if (x == 1) {
                    file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/1 Stratified squamous/R"+y+".png");
//                    System.out.println(file.toString());
                }
                else if (x == 2 && y < 68)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/2 connective tissue/s"+y+".png");
                else if (x == 3 && y < 2)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/3 Goblet cell/T"+y+".png");
                else if (x == 4 && y < 58)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/4 Neuron/U"+y+".png");
                else if (x == 5 && y < 7)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/5 Simple columnar/"+y+".png");
                else if (x == 6 && y < 1)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/6 Pseudostratified columnar/A"+y+".png");
                else if (x == 7 && y < 1)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/7 Golgi/B"+y+".png");
                else if (x == 8 && y < 3)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/8 Cuboidal/C"+y+".png");
                else if (x == 9 && y < 2)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/9 Adipocytes/D"+y+".png");
                else if (x == 10 && y < 44)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/10 Bone tissue/E"+y+".png");
                else if (x == 11 && y < 62)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/11 Hematology/F"+y+".png");
                else if (x == 12 && y < 24)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/12 Gland/G"+y+".png");
                else if (x == 13 && y < 20)
                     file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/13 Electron Microscopy/H"+y+".png");
                else if (x == 14 && y < 20){
                    file = new File("/Users/rohpatil/Documents/workspace/dl4j-examples/dl4j-examples/src/main/resources/UnusedMicroscopy/14 Muscle/I"+y+".png");
                }
                else break;

//                System.out.println(file.toString());

                // Use NativeImageLoader to convert to numerical matrix

                NativeImageLoader loader = new NativeImageLoader(height, width, channels);

                // Get the image into an INDarray

                INDArray image = loader.asMatrix(file);

                // 0-255
                // 0-1
                DataNormalization scaler = new ImagePreProcessingScaler(0, 1);
                scaler.transform(image);
                // Pass through to neural Net

                INDArray output = model.output(image);

//                Output of probabilities
//                log.info("## The FILE CHOSEN WAS " + filechose);
//                log.info("## The Neural Nets Pediction ##");
//                log.info("## list of probabilities per label ##");
//                log.info("## List of Labels in Order## ");
//                log.info(output.toString());
//                log.info(labelList.toString());

//                System.out.println(output.toString().length());
                String temp = "";
                prob.clear();

                // Add probabilities from the output to an arraylist
                for (int i = 1; i < output.toString().length(); i++) {

                    temp = (output.toString().substring(i, i + 4));
                    i += 5;
//            System.out.println(temp);
                    prob.add(Double.parseDouble(temp));
                    temp = "";
                }
//                System.out.println(x+","+output.toString());

//              Add the single image probabilities into the full list as the class Proportion
//             (String label, Double squamous, Double connective,Double neuron, Double bone,
//              Double heme, Double gland,Double electron, Double muscle)
                String fileName = file.toString().substring(100);
                probList.add(new Probability(fileName, prob.get(0), prob.get(1), prob.get(2), prob.get(3), prob.get(4),
                    prob.get(5), prob.get(6), prob.get(7)));

            }
            System.out.print(x+"...");
        }

        for (int x=0; x<probList.size(); x++){
            probList.get(x).printExcelSheet();
            if (x+1<probList.size() && !probList.get(x).getlabel().substring(0,2).equals(probList.get(x+1).getlabel().substring(0,2))){
                System.out.println(",,,,,,,,,,,");
                System.out.println(",,,,,,,,,,,");
            }
        }

//        try {
//            FileOutputStream fileOut = new FileOutputStream("/users/rohpatil/desktop/prob.csv");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//
//            for (int x=0; x<probList.size(); x++){
//                out.writeChars(probList.get(x).returnProb());
//                if (x+1<probList.size() && !probList.get(x).getlabel().substring(0,2).equals(probList.get(x+1).getlabel().substring(0,2))){
//                    out.writeChars(",,,,,,,,,,,");
//                    out.writeChars(",,,,,,,,,,,");
//                }
//            }
//            out.close();
//            fileOut.close();
//            System.out.println("File Written onto disk");
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Create and set up the window.
        JFrame frame = new JFrame("File Saved");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("File Saved");
        frame.add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

//        Print out result info
//        int a=0;
//        for(int x=0; x<probList.size();x++){
//            if (x < 70)
//                a=1;
//            else if (x < 138)
//                a=2;
//            else if (x < 140)
//                a=3;
//            else if (x < 198)
//                a=4;
//            else if (x < 205)
//                a=5;
//            else if (x < 206)
//                a=6;
//            else if (x < 207)
//                a=7;
//            else if (x < 210)
//                a=8;
//            else if (x < 212)
//                a=9;
//            else if (x < 256)
//                a=10;
//            else if (x < 318)
//                a=11;
//            else if (x < 342)
//                a=12;
//            else if (x < 362)
//                a=13;
//            else if (x < 382)
//                a=14;
//
//            System.out.print(a+",");
//            for (int y=0; y<probList.get(x).size();y++){
//                System.out.print(probList.get(x).get(y)+",");
//            }
//            System.out.println();
//        }

       /* System.out.println("Size of array: "+prob.size());
        Double num=0.0;

        for (int i=0; i<prob.size(); i++){
            System.out.print(prob.get(i)+", ");
            num+=prob.get(i);
        }

        int larger=0;
        for (int i=0; i<prob.size(); i++){
            if (prob.get(larger)<prob.get(i))
                larger=i;
        }

        System.out.println();
        System.out.println("Total Probability: "+num);
        System.out.println("Most likely "+ (larger+1));
        System.out.println("Probability: "+prob.get(larger));*/
    }

}
