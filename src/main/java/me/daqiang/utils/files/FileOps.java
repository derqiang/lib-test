package me.daqiang.utils.files;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Comparator;

/**
 * @author daqiang
 * @date 2020/4/15 6:07 下午
 **/
@Slf4j
public class FileOps {

    public static void writeFile(String value){
        String PATH = "/Users/dongfuqiang/Desktop/PAN/SpaceV/Labs/Test/Java/lib-test/";
        String directoryName = PATH.concat("FileOps");
        String fileName = Instant.now().toString() + ".txt";

        System.out.println("----> " + directoryName);
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(value);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Creates a File if the file does not exist, or returns a
     * reference to the File if it already exists.
     */
    private static File createOrRetrieve(final String target) throws IOException {

        final Path path = Paths.get(target);
        if(Files.notExists(path)){
            System.out.println("----");//Files.createDirectories(path)
            return Files.createFile(path).toFile();
        }
        Files.deleteIfExists(path);
        return path.toFile();
    }

    /**
     * Deletes the target if it exists then creates a new empty file.
     */
    private static File createOrReplaceFileAndDirectories(final String target) throws IOException{

        final Path path = Paths.get(target);
        // Create only if it does not exist already
        Files.walk(path)
                .filter(p -> Files.exists(p))
                .sorted(Comparator.reverseOrder())
                .peek(p -> LOG.info("Deleted existing file or directory \"" + p + "\"."))
                .forEach(p -> {
                    try{
                        Files.createFile(Files.createDirectories(p));
                    }
                    catch(IOException e){
                        throw new IllegalStateException(e);
                    }
                });

        LOG.info("Target file \"" + target + "\" will be created.");

        return Files.createFile(Files.createDirectories(path)).toFile();
    }


    private static void recursiveCreate(String tar) throws Exception {

        final Path path = Paths.get(tar);
        StringBuilder subPather = new StringBuilder().append("/");
        path.getParent().forEach(p -> {
            String curPath = subPather.append(p).append("/").toString();
            try {
                if (Files.notExists(Paths.get(curPath))) {
                    Files.createDirectory(Paths.get(curPath));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if (Files.notExists(path)) {
            try {
                if (Files.notExists(path)) {
                    Files.createFile(path);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        FileOps.writeFile("xxxx");
        String PATH = "/Users/dongfuqiang/Desktop/PAN/SpaceV/Labs/Test/Java/lib-test/FileOps/ddd/my.txt";
        FileOps.recursiveCreate(PATH);
        System.out.println(String.format("%s/%d", "111", 1));
    }
}
