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

        //Create files
        File inputText = new File("test.txt"); //File to be compressed

        File codeFile = new File("codes.txt"); //File to store Huffman codes
        codeFile.createNewFile();

        File compressedFile = new File("compressed.txt"); //Final compressed file
        compressedFile.createNewFile();

        //Read contents of inputText file into a string
        StringBuilder sb = new StringBuilder();
        String line;

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

        //Pass string to CodingTree (generate map of codes)
        CodingTree a = new CodingTree(sb.toString());

        //Output codes to a text file

        //Write Huffman codes
        FileWriter writer = new FileWriter(codeFile);
        writer.write(a.codes.toString());

        writer.flush();
        writer.close();

        //Write compressed file
        writer = new FileWriter(compressedFile);
        writer.write(a.bits);

        writer.flush();
        writer.close();

        //Display the size of the compressed text, compression, and run time statistics
        System.out.println(compressedFile.length());
        System.out.println(codeFile.length());
        System.out.println(inputText.length());


    }
}
