package com.devhenry;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class FileReader{
    private String fileName;
    private Scanner read;
    private LinkedList temp;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public LinkedList getList() {
        LoadDB();
        return temp;
    }

    private void LoadDB() {
        openToRead();
        temp = new LinkedList<>();

        while (read.hasNext()) {
            temp.add(read.nextLine());
        }
        read.close();
    }

    private void openToRead() {
        try {
            // pathname should be set according to your local system
            read = new Scanner(new File("PathName" + this.fileName));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
