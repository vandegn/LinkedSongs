import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class that works with the
 * user options and does diff
 * operations depending on selection
 * save, add, delete, shuffle, etc.
 *
 * @version 1.0.
 * @author Nick Vandegriff.
 */
public class Main {

    /**
     * displays the menu..
     */
    public static void menu() {
        System.out.println("1. List songs sequentially");
        System.out.println("2. Shuffle");
        System.out.println("3. Remove song");
        System.out.println("4. Add to end");
        System.out.println("5. Save");
        System.out.println("6. Exit");
    }

    /**
     * shuffles the list.
     *
     * @param list LinkedList of songs.
     */
    public static <T> void option2(LinkedList<Song> list) {
        // make a generic array
        // to shuffle
        Object[] arr = new Object[list.getSize()];
        T[] arrGeneric = (T[]) new Object[list.getSize()];
        for (int i = 0; i < list.getSize(); i++) {
            arrGeneric[i] = (T) list.getAt(i);
        }
        for (int i = 0; i < list.getSize(); i++) {
            // random int from 0-i(size)
            int rand = (int) (Math.random() * list.getSize());
            // save to prevent dupes.
            T temp = arrGeneric[i];
            // swap
            arrGeneric[i] = arrGeneric[rand];
            arrGeneric[rand] = temp;
        }
        // reset the list
        list.emptyList();
        for (T t : arrGeneric) {
            list.insertLast((Song) t);
        }
    }

    /**
     * removes a song from the list.
     *
     * @param list LinkedList of songs.
     * @param name String name of song to remove.
     */
    public static void option3(LinkedList<Song> list, String name) {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        for (int i = 0; i < list.getSize(); i++) {
            Song song = (Song) list.getAt(i);
            if (song.getName().equals(name)) {
                list.removeAt(i);
                System.out.println("Removing: " + song.toString());
                return;
            }
        }
        System.out.println("Song not found.");
    }

    /**
     * reads the file and adds the songs to the LinkedList.
     *
     * @param filename String filename to read from.
     * @param list LinkedList of songs.
     */
    public static void readFile(String filename, LinkedList<Song> list) {
        try {
            Scanner inFile = new Scanner(new File(filename));
            while (inFile.hasNextLine()) {
                Song song = new Song(inFile.nextLine(), inFile.nextLine());
                list.insertLast(song);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
    }

    /**
     * main method.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        // the linked list derives from the
        // generic type class but we want it
        // to hold songs.
        LinkedList<Song> list = new LinkedList<Song>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter song file: ");
        String filename = in.next();
        readFile(filename, list);

        int choice;
        try {
            do {
                menu();
                choice = in.nextInt();
                if (choice == 1) {
                    System.out.println(list.toString());
                } else if (choice == 2) {
                    option2(list);
                } else if (choice == 3) {
                    // flush the buffer
                    in.nextLine();
                    System.out.println("Enter name of song to remove: ");
                    String name = in.nextLine();
                    option3(list, name);
                } else if (choice == 4) {
                    System.out.println("Enter name of song to add: ");
                    in.nextLine();
                    String name = in.nextLine();
                    // flush the buffer
                    // and allow spaces
                    System.out.println("Enter artist of that song: ");
                    String artist = in.nextLine();
                    Song song = new Song(name, artist);
                    list.insertLast(song);
                } else if (choice == 5) {
                    System.out.println("Saving to songs");
                    try {
                        PrintWriter out = new PrintWriter("songs.txt");
                        for (int i = 0; i < list.getSize(); i++) {
                            Song song = (Song) list.getAt(i);
                            out.println(song.getName());
                            out.println(song.getArtist());
                        }
                        // close scanner
                        out.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                }
                if (choice > 6 || choice < 1) {
                    System.out.println("Enter a number. 1-6");
                }
            } while (choice != 6);
        } catch (InputMismatchException e) {
            System.out.println("Enter a number. 1-6");
        }
    }
}
