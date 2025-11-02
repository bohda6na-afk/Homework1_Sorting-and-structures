package ua.edu.ucu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Case3TreeSet {
    ArrayList<Person> allStudents = new ArrayList<>();
    HashMap<String, Person> phoneToStudent = new HashMap<>();
    HashMap<String, TreeSet<Person>> groupToStudents = new HashMap<>();
    HashMap<String, HashSet<String>> surnameToGroups = new HashMap<>();

    void readFile(String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line1 = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                String m_name = parts[0];
                String m_surname = parts[1];
                String m_email = parts[2];
                int m_birth_year = Integer.parseInt(parts[3]);
                int m_birth_month = Integer.parseInt(parts[4]);
                int m_birth_day = Integer.parseInt(parts[5]);
                String m_group = parts[6];
                float m_rating = Float.parseFloat(parts[7]);
                String m_phone_number = parts[8];

                Person stud = new Person(m_name, m_surname, m_email, m_birth_year, m_birth_month, m_birth_day, m_group, m_rating, m_phone_number);
                allStudents.add(stud);

                phoneToStudent.put(stud.getM_phone_number(), stud);// add to map
                if (!groupToStudents.containsKey(stud.getM_group())){ //add into group
                    groupToStudents.put(stud.getM_group(), new TreeSet<>(new Comparator<Person>() {
                        public int compare(Person st1, Person st2){
                            int compare = st1.getM_surname().compareTo(st2.getM_surname());
                            if (compare == 0){
                                compare = st1.getM_name().compareTo(st2.getM_name());
                            }
                            if (compare == 0) {
                                compare = st1.getM_email().compareTo(st2.getM_name());
                            }
                            return compare;
                        }
                        
                    }));
                }
                groupToStudents.get(stud.getM_group()).add(stud);

                if (!surnameToGroups.containsKey(stud.getM_surname())){
                    surnameToGroups.put(stud.getM_surname(), new HashSet<>());
                }
                surnameToGroups.get(stud.getM_surname()).add(stud.getM_group());
            }
            reader.close();
        } 
        catch (Exception ex) {
            System.out.println("Error");
        }
    }

    void changeGroupByPhone(String phone, String newGroup) {
        Person st = phoneToStudent.get(phone);
        if (st != null) {
            String group = st.getM_group();
            groupToStudents.get(group).remove(st);
            st.setM_group(newGroup);
            
            if (!groupToStudents.containsKey(newGroup)) {
                groupToStudents.put(newGroup, new TreeSet<>(new Comparator<Person>() {
                    public int compare(Person st1, Person st2) {
                        int compare = st1.getM_surname().compareTo(st2.getM_surname());
                        if (compare == 0) compare = st1.getM_name().compareTo(st2.getM_name());
                        if (compare == 0) compare = st1.getM_email().compareTo(st2.getM_email());
                        return compare;
                    }
                }));
            }
            groupToStudents.get(newGroup).add(st);
            
            surnameToGroups.get(st.getM_surname()).remove(group);
            surnameToGroups.get(st.getM_surname()).add(newGroup);
        }
    }

    //find students of group and sort them
    ArrayList<Person> getStudentsOfGroup(String group){
        ArrayList<Person> res = new ArrayList<>();
                if (groupToStudents.containsKey(group)) {
                    res.addAll(groupToStudents.get(group));
                }
                return res;
    }

    // grops where student with surname is
    ArrayList<String> findGroupsBySurname(String surname){
        if (surnameToGroups.containsKey(surname)) {
            return new ArrayList<>(surnameToGroups.get(surname));
        }
        return new ArrayList<>();

    }

}
