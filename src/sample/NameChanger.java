package sample;

import java.io.File;

public class NameChanger {

    private int counter, nr_files;
    private File folder;
    private File[] list_of_files;

    public NameChanger() {}

    public NameChanger(File f) {
        setFiles(f);
    }

    public void changeAll(String new_name) {

        for (File f : list_of_files) {
            String name_buff = f.getName();
            f.renameTo(new File(newName(name_buff, new_name)));
        }
    }

    public void changeAllWithIdentifier(String identifier, String new_name) {

        for (File f : list_of_files) {
            String name_buff = f.getName();
            if (name_buff.contains(identifier)) {
                f.renameTo(new File(newName(name_buff, new_name)));
            }
        }
    }

    public void deletePartOfName(String to_delete) {

        int begin = 0, end = 0;
        for (File f : list_of_files) {
            String name_buff = f.getName();

            if (name_buff.contains(to_delete)) {
                begin = name_buff.indexOf(to_delete);
                end = begin + to_delete.length();
                name_buff = folder.getAbsolutePath() + "\\" + name_buff.substring(0, begin) + name_buff.substring(end);
                f.renameTo(new File(name_buff));
            }
        }
    }

    public static String zeroPad(int max, int curr) {
        String zeros = "";
        if (max < 100) {
            zeros += curr < 10 ? "0" : "";
        }
        else if (max < 1000) {
            zeros += curr < 100 ? (curr < 10 ? "00" : "0") : "";
        }
        return zeros;
    }

    public String newName(String name_buff, String new_name) {
        String file_extension = name_buff.substring(name_buff.lastIndexOf("."));
        name_buff = folder.getAbsolutePath() + "\\" + new_name + zeroPad(nr_files, counter) + "" +counter + file_extension;
        ++counter;
        return name_buff;
    }

    public File[] getFiles() {
        setFiles(folder);
        return folder.listFiles();
    }

    private void setFiles(File f) {
        folder = f;
        list_of_files = new File[0];

        if (folder.exists()) {
            list_of_files = folder.listFiles();
        }

        nr_files = list_of_files.length;
    }
}
