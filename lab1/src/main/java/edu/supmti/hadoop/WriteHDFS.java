package edu.supmti.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

public class WriteHDFS {
    public static void main(String[] args) throws IOException {
        if(args.length < 1){
            System.out.println("Usage: WriteHDFS <HDFS destination path>");
            System.exit(1);
        }

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path nomcomplet = new Path(args[0]);

        if (!fs.exists(nomcomplet)) {
            FSDataOutputStream outStream = fs.create(nomcomplet);
            outStream.writeUTF("Bonjour tout le monde !");
            outStream.close();
        }

        fs.close();
        System.out.println("Fichier écrit avec succès dans HDFS : " + args[0]);
    }
}
