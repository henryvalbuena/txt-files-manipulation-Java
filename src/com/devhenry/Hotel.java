/*
 * Copyright (c) 2018.
 */

package com.devhenry;


import java.util.Scanner;

public class Hotel{
    private FileWriter write;
    private FileReader read;

    public Hotel() {
        this.write = new FileWriter("HotelDB.txt");
        this.read = new FileReader("HotelDB.txt");
    }

    private void removeReservation(int roomNumber) {
        write.writeInDB(roomNumber);
    }

    private void checkAvailableRooms() {
        for (int i = 0; i < read.getList().size(); i++) {
            if (read.getList().get(i).toString().contains("Room Available")) System.out.println(read.getList().get(i));
        }
    }

    private void makeReservation(int roomNumber, String guest, int month, int day) {
        if (read.getList().get(roomNumber - 1).toString().contains("Room Available")) {
            write.writeInDB(roomNumber, guest, month, day);
            System.out.println("Reservation succeeded");
        } else System.out.println("Room already reserved. Choose another room");
    }

    private void printRoomsAndGuests() {
        for (int i = 0; i < read.getList().size(); i++) {
            System.out.println(read.getList().get(i));
        }
    }

    private boolean findGuest(String guestName) {
        for (int i = 0; i < read.getList().size(); i++) {
            if (read.getList().get(i).toString().contains(guestName)) {
                System.out.println("Guest " + read.getList().get(i).toString().split(" ")[1] +
                        " " + read.getList().get(i).toString().split(" ")[2] + " found in room: " +
                        read.getList().get(i).toString().split(" ")[0]);
                return true;
            }
        }
        return false;
    }

    public void runProgram () {
        Scanner input = new Scanner(System.in);
        String name, choice;
        int room, month, day;
        boolean menu = true;

        System.out.println("Welcome to Repenterus Hotel, press enter to continue");
        input.nextLine();
        while (menu) {
            System.out.println("***********************************");
            System.out.println("Check rooms available: a");
            System.out.println("Make a reservation: b");
            System.out.println("Update an existing reservation: c");
            System.out.println("Show room status and guests: d");
            System.out.println("Find a guest: e");
            System.out.println("Exit program: x");
            choice = input.nextLine();
            switch (choice) {
                case "a":
                    checkAvailableRooms();
                    break;
                case "b":
                    System.out.println("Please enter guest name");
                    name = input.nextLine();
                    System.out.println("Please enter checkout date: month and day");
                    month = Integer.parseInt(input.nextLine());
                    day = Integer.parseInt(input.nextLine());
                    System.out.println("Select a room");
                    checkAvailableRooms();
                    room = Integer.parseInt(input.nextLine());
                    makeReservation(room, name, month, day);
                    break;
                case "c":
                    printRoomsAndGuests();
                    System.out.println("Choose which reservation to update by selecting the room number");
                    room = Integer.parseInt(input.nextLine());
                    removeReservation(room);
                    System.out.println("Room Released");
                    break;
                case "d": printRoomsAndGuests(); break;
                case "e":
                    System.out.println("Guest name: ");
                    if (!findGuest(input.nextLine())) System.out.println("Guest not found...");
                    break;
                case "x": menu = false; break;
            }
        }
    }
}
