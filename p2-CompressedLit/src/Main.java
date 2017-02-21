import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by todc on 2/18/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //Fields
        long sTime;
        long eTime;
        long tTime;

        //Create files
        File inputText = new File("test.txt"); //File to be compressed

        File codeFile = new File("codes.txt"); //File to store Huffman codes
        codeFile.createNewFile();

        File compressedFile = new File("compressed.txt"); //Final compressed file
        compressedFile.createNewFile();

        //Read contents of inputText file into a string
        StringBuilder sb = new StringBuilder();
        String line;

        sTime = System.currentTimeMillis();
        try {
            //Fields
            FileReader fr = new FileReader(inputText);
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append((char) 10); //Account for lines
            }
            br.close();
        } finally{}

        eTime = System.currentTimeMillis();
        tTime = eTime - sTime;
        System.out.println("Read time: " + tTime + " ms");


        //Pass string to CodingTree (generate map of codes)
        sTime = System.currentTimeMillis();

        CodingTree a = new CodingTree(sb.toString());

        eTime = System.currentTimeMillis();
        tTime = eTime - sTime;
        System.out.println("Encode time: " + tTime + " ms");

        //Output codes to a text file

        //Write Huffman codes
        sTime = System.currentTimeMillis();

        FileWriter writer = new FileWriter(codeFile);
        writer.write(a.codes.toString());

        writer.flush();
        writer.close();

        //Write compressed file
        writer = new FileWriter(compressedFile);
        writer.write(a.bits);

        writer.flush();
        writer.close();

        eTime = System.currentTimeMillis();
        tTime = eTime - sTime;
        System.out.println("File write time: " + tTime + " ms");

        //Display the size of the compressed text, compression, and run time statistics
        System.out.println("Original file size: " + inputText.length() + " bytes");
        System.out.println("Compressed file size: " + compressedFile.length() + " bytes");
        long compress = (compressedFile.length() / inputText.length()) * 100;
        System.out.println("Compression: " + compress + "%");
        //System.out.println(codeFile.length());



    }
}
