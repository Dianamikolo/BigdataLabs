package edu.supmti.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HadoopFileStatus {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: HadoopFileStatus <chemin_fichier> <nom_fichier> <nouveau_nom_fichier>");
            System.exit(1);
        }

        String pathDir = args[0];           // ex: /user/root/input
        String filename = args[1];          // ex: purchases.txt
        String newFilename = args[2];       // ex: achats.txt

        Configuration conf = new Configuration();
        FileSystem fs;

        try {
            fs = FileSystem.get(conf);

            // Chemin complet du fichier
            Path filepath = new Path(pathDir, filename);

            if (!fs.exists(filepath)) {
                System.out.println("File does not exist: " + filepath.toString());
                System.exit(1);
            }

            FileStatus status = fs.getFileStatus(filepath);

            System.out.println("File Name: " + filepath.getName());
            System.out.println("File Size: " + status.getLen() + " bytes");
            System.out.println("File owner: " + status.getOwner());
            System.out.println("File permission: " + status.getPermission());
            System.out.println("File Replication: " + status.getReplication());
            System.out.println("File Block Size: " + status.getBlockSize());

            BlockLocation[] blockLocations = fs.getFileBlockLocations(status, 0, status.getLen());
            for (BlockLocation blockLocation : blockLocations) {
                System.out.println("Block offset: " + blockLocation.getOffset());
                System.out.println("Block length: " + blockLocation.getLength());
                System.out.print("Block hosts: ");

                for (String host : blockLocation.getHosts()) {
                    System.out.print(host + " ");
                }
                System.out.println();
            }

            // Renommage du fichier
            Path newPath = new Path(pathDir, newFilename);
            boolean renamed = fs.rename(filepath, newPath);

            if (renamed) {
                System.out.println("File renamed successfully to: " + newFilename);
            } else {
                System.out.println("Failed to rename file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
