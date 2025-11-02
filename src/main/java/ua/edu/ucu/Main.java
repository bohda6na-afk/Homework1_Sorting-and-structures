package ua.edu.ucu;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {
    static int A = 5;   
    static int B = 10;  
    static int C = 100; 
    
    static class Result {
        int size;
        int case1Ops, case2Ops, case3Ops;
        long case1Mem, case2Mem, case3Mem;
        long case1Init, case2Init, case3Init;
    }
    
    static ArrayList<Result> results = new ArrayList<>();
    
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        
        System.out.println("Variant V3, ratio A:B:C = " + A + ":" + B + ":" + C);
        
        for (int size : sizes) {
            System.out.println("\nDatabase size: " + size + " students\n");
            Result r = new Result();
            r.size = size;
            
            r.case1Ops = testCase1(size, r);
            System.out.println();
            r.case2Ops = testCase2(size, r);
            System.out.println();
            r.case3Ops = testCase3(size, r);
            
            results.add(r);
        }
        
        printResultsTable();
        createCharts();
    }
    
    static int getRandomOperation(Random rand) {
        int total = A + B + C;
        int r = rand.nextInt(total);
        if (r < A) return 0;
        else if (r < A + B) return 1;
        else return 2;
    }
    
    static int testCase1(int size, Result r) {
        System.out.println("Case 1: ArrayList");
        
        Case1ArrayList case1 = new Case1ArrayList();
        long initStart = System.currentTimeMillis();
        case1.readFile("students.csv");
        r.case1Init = System.currentTimeMillis() - initStart;
        
        Random rand = new Random();
        long start = System.currentTimeMillis();
        int totalOps = 0;
        int op1 = 0, op2 = 0, op3 = 0;
        
        while (System.currentTimeMillis() - start < 10000) {
            int operation = getRandomOperation(rand);
            
            if (operation == 0) {
                if (case1.allStudents.size() > 0) {
                    String phone = case1.allStudents.get(rand.nextInt(case1.allStudents.size())).getM_phone_number();
                    case1.changeGroup(phone, "TST-50");
                }
                op1++;
            } else if (operation == 1) {
                if (case1.allStudents.size() > 0) {
                    String group = case1.allStudents.get(rand.nextInt(case1.allStudents.size())).getM_group();
                    case1.getStudentsOfGroup(group);
                }
                op2++;
            } else {
                if (case1.allStudents.size() > 0) {
                    String surname = case1.allStudents.get(rand.nextInt(case1.allStudents.size())).getM_surname();
                    case1.findGroupsBySurname(surname);
                }
                op3++;
            }
            totalOps++;
        }
        
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        r.case1Mem = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        
        System.out.println("  Init: " + r.case1Init + " ms");
        System.out.println("  Total ops: " + totalOps);
        System.out.println("  Op1: " + op1 + ", Op2: " + op2 + ", Op3: " + op3);
        System.out.println("  Memory: " + r.case1Mem + " MB");
        
        return totalOps;
    }
    
    static int testCase2(int size, Result r) {
        System.out.println("CASE 2: HashMap");
        
        Case2HashMap case2 = new Case2HashMap();
        long initStart = System.currentTimeMillis();
        case2.readFile("students.csv");
        r.case2Init = System.currentTimeMillis() - initStart;
        
        Random rand = new Random();
        long start = System.currentTimeMillis();
        int totalOps = 0;
        int op1 = 0, op2 = 0, op3 = 0;
        
        while (System.currentTimeMillis() - start < 10000) {
            int operation = getRandomOperation(rand);
            
            if (operation == 0) {
                if (case2.allStudents.size() > 0) {
                    String phone = case2.allStudents.get(rand.nextInt(case2.allStudents.size())).getM_phone_number();
                    case2.changeGroupByPhone(phone, "TST-50");
                }
                op1++;
            } else if (operation == 1) {
                if (case2.allStudents.size() > 0) {
                    String group = case2.allStudents.get(rand.nextInt(case2.allStudents.size())).getM_group();
                    case2.getStudentsOfGroup(group);
                }
                op2++;
            } else {
                if (case2.allStudents.size() > 0) {
                    String surname = case2.allStudents.get(rand.nextInt(case2.allStudents.size())).getM_surname();
                    case2.findGroupsBySurname(surname);
                }
                op3++;
            }
            totalOps++;
        }
        
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        r.case2Mem = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        
        System.out.println("  Init: " + r.case2Init + " ms");
        System.out.println("  Total ops: " + totalOps);
        System.out.println("  Op1: " + op1 + ", Op2: " + op2 + ", Op3: " + op3);
        System.out.println("  Memory: " + r.case2Mem + " MB");
        
        return totalOps;
    }
    
    static int testCase3(int size, Result r) {
        System.out.println("CASE 3: TreeSet");
        
        Case3TreeSet case3 = new Case3TreeSet();
        long initStart = System.currentTimeMillis();
        case3.readFile("students.csv");
        r.case3Init = System.currentTimeMillis() - initStart;
        
        Random rand = new Random();
        long start = System.currentTimeMillis();
        int totalOps = 0;
        int op1 = 0, op2 = 0, op3 = 0;
        
        while (System.currentTimeMillis() - start < 10000) {
            int operation = getRandomOperation(rand);
            
            if (operation == 0) {
                if (case3.allStudents.size() > 0) {
                    String phone = case3.allStudents.get(rand.nextInt(case3.allStudents.size())).getM_phone_number();
                    case3.changeGroupByPhone(phone, "TST-50");
                }
                op1++;
            } else if (operation == 1) {
                if (case3.allStudents.size() > 0) {
                    String group = case3.allStudents.get(rand.nextInt(case3.allStudents.size())).getM_group();
                    case3.getStudentsOfGroup(group);
                }
                op2++;
            } else {
                if (case3.allStudents.size() > 0) {
                    String surname = case3.allStudents.get(rand.nextInt(case3.allStudents.size())).getM_surname();
                    case3.findGroupsBySurname(surname);
                }
                op3++;
            }
            totalOps++;
        }
        
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        r.case3Mem = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        
        System.out.println("  Init: " + r.case3Init + " ms");
        System.out.println("  Total ops: " + totalOps);
        System.out.println("  Op1: " + op1 + ", Op2: " + op2 + ", Op3: " + op3);
        System.out.println("  Memory: " + r.case3Mem + " MB");
        
        return totalOps;
    }
    
    static void printResultsTable() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("РЕЗУЛЬТАТИ ТЕСТУВАННЯ");
        System.out.println("=".repeat(80));
        System.out.println(String.format("%-10s %-15s %-15s %-15s", "Розмір", "Case1 (ops)", "Case2 (ops)", "Case3 (ops)"));
        System.out.println(String.format("%-10s %-15s %-15s %-15s", "", "Case1 (MB)", "Case2 (MB)", "Case3 (MB)"));
        System.out.println("-".repeat(80));
        
        for (Result r : results) {
            System.out.println(String.format("%-10d %-15d %-15d %-15d", 
                r.size, r.case1Ops, r.case2Ops, r.case3Ops));
            System.out.println(String.format("%-10s %-15d %-15d %-15d", 
                "", r.case1Mem, r.case2Mem, r.case3Mem));
            System.out.println();
        }
    }
    
    static void createCharts() {
        DefaultCategoryDataset opsDataset = new DefaultCategoryDataset();
        for (Result r : results) {
            opsDataset.addValue(r.case1Ops, "Case 1: ArrayList", String.valueOf(r.size));
            opsDataset.addValue(r.case2Ops, "Case 2: HashMap", String.valueOf(r.size));
            opsDataset.addValue(r.case3Ops, "Case 3: TreeSet", String.valueOf(r.size));
        }
        
        JFreeChart opsChart = ChartFactory.createLineChart(
            "Продуктивність структур даних (Варіант V3)",
            "Розмір бази даних (студентів)",
            "Кількість операцій за 10 секунд",
            opsDataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );
        DefaultCategoryDataset memDataset = new DefaultCategoryDataset();
        for (Result r : results) {
            memDataset.addValue(r.case1Mem, "Case 1: ArrayList", String.valueOf(r.size));
            memDataset.addValue(r.case2Mem, "Case 2: HashMap", String.valueOf(r.size));
            memDataset.addValue(r.case3Mem, "Case 3: TreeSet", String.valueOf(r.size));
        }
        
        JFreeChart memChart = ChartFactory.createBarChart(
            "Використання пам'яті (Варіант V3)",
            "Розмір бази даних (студентів)",
            "Пам'ять (МБ)",
            memDataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );
        
        DefaultCategoryDataset initDataset = new DefaultCategoryDataset();
        for (Result r : results) {
            initDataset.addValue(r.case1Init, "Case 1: ArrayList", String.valueOf(r.size));
            initDataset.addValue(r.case2Init, "Case 2: HashMap", String.valueOf(r.size));
            initDataset.addValue(r.case3Init, "Case 3: TreeSet", String.valueOf(r.size));
        }
        
        JFreeChart initChart = ChartFactory.createLineChart(
            "Час ініціалізації (Варіант V3)",
            "Розмір бази даних (студентів)",
            "Час (мс)",
            initDataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );
        
        showChart(opsChart, "Графік продуктивності");
        showChart(memChart, "Графік пам'яті");
        showChart(initChart, "Графік ініціалізації");
    }
    
    static void showChart(JFreeChart chart, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        frame.setContentPane(chartPanel);
        
        frame.pack();
        frame.setVisible(true);
    }
}
