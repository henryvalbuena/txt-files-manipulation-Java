package com.devhenry;

import java.io.File;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Scanner;

public class FileWriter{
    private String fileName;
    private Scanner read;
    private Formatter write;
    private LinkedList<Format> temp;

    public FileWriter(String fileName) {
        this.fileName = fileName;
    }

    public final void writeInDB(int room, String guest, int month, int day) {
        openFileToRead();
        tempCopyFile();
        openFileToWrite();
        temp.set(room - 1, new Format(room, guest, month, day));
        writeToFile();
        closeAllFiles();
    }

    public final void writeInDB(int room) {
        openFileToRead();
        tempCopyFile();
        openFileToWrite();
        temp.set(room - 1, new Format(room));
        writeToFile();
        closeAllFiles();
    }

    private void openFileToWrite() {
        try {
            // pathname should be set according to your local system
            write = new Formatter("PathName" + this.fileName);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private void openFileToRead() {
        try {
            // pathname should be set according to your local system
            read = new Scanner(new File("PathName" + this.fileName));
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private void tempCopyFile() {
        temp = new LinkedList();
        while (read.hasNext()) {
            temp.add(new Format(Integer.parseInt(read.next()),
                    read.next() + " " + read.next(),
                    Integer.parseInt(read.next()),
                    Integer.parseInt(read.next())));
        }
    }

    private void writeToFile() {
        for (int i = 0; i < temp.size(); i++) {
            write.format("%s ", temp.get(i).getRoom());
            write.format("%s ", temp.get(i).getGuest());
            write.format("%s ", temp.get(i).getMonth());
            write.format("%s\n", temp.get(i).getDay());
        }

    }

    private void closeAllFiles() {
        read.close();
        write.close();
    }
}

class Format {
    private int room;
    private String guestName;
    private int month;
    private int day;

    public Format(int room, String guest, int month, int day) {
        this.room = room;
        this.guestName = guest;
        this.month = month;
        this.day = day;
    }

    public Format(int room) {
        this.room = room;
        this.guestName = "Room Available";
        this.month = 0;
        this.day = 0;
    }

    public int getRoom() {
        return room;
    }

    public String getGuest() {
        return guestName;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}