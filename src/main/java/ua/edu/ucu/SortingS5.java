package ua.edu.ucu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SortingS5 {

    public static void sortBuiltIn(ArrayList<Person> students, String outputFile) {
        ArrayList<Person> sorted = new ArrayList<>(students);
        
        long start = System.nanoTime();
        Collections.sort(sorted, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.getM_group().compareTo(p2.getM_group());
            }
        });
        long end = System.nanoTime();
        
        System.out.println("Built-in sort: " + (end - start) / 1_000_000.0 + " ms");
        writeCSV(sorted, outputFile);
    }
    
    //quickSort
    public static void sortQuickSort(ArrayList<Person> students, String outputFile) {
        ArrayList<Person> sorted = new ArrayList<>(students);
        
        long start = System.nanoTime();
        quickSort(sorted, 0, sorted.size() - 1);
        long end = System.nanoTime();
        
        System.out.println("QuickSort (random pivot): " + (end - start) / 1_000_000.0 + " ms");
        writeCSV(sorted, outputFile);
    }
    
    private static Random random = new Random();
    
    private static void quickSort(ArrayList<Person> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(ArrayList<Person> arr, int low, int high) {
        int randomIndex = low + random.nextInt(high - low + 1);
        Person temp = arr.get(randomIndex);
        arr.set(randomIndex, arr.get(high));
        arr.set(high, temp);
        
        String pivot = arr.get(high).getM_group();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr.get(j).getM_group().compareTo(pivot) <= 0) {
                i++;
                Person t = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, t);
            }
        }
        
        Person t = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, t);
        
        return i + 1;
    }
    
    private static void writeCSV(ArrayList<Person> students, String file) {
        try {
            PrintWriter w = new PrintWriter(new FileWriter(file));
            w.println("m_name,m_surname,m_email,m_birth_year,m_birth_month,m_birth_day,m_group,m_rating,m_phone_number");
            for (Person p : students) {
                w.println(p.getM_name() + "," + p.getM_surname() + "," + p.getM_email() + "," +
                         p.getM_birth_year() + "," + p.getM_birth_month() + "," + p.getM_birth_day() + "," +
                         p.getM_group() + "," + p.getM_rating() + "," + p.getM_phone_number());
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Case1ArrayList db = new Case1ArrayList();
        db.readFile("students.csv");
        
        System.out.println("Students: " + db.allStudents.size());
        
        sortBuiltIn(db.allStudents, "sorted_builtin.csv");
        sortQuickSort(db.allStudents, "sorted_quicksort.csv");
    }
}
