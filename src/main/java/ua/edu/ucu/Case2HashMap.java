package ua.edu.ucu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Case2HashMap {
    ArrayList<Person> allStudents = new ArrayList<>();
    HashMap<String, Person> phoneToStudent = new HashMap<>(); //phone student
    HashMap<String, ArrayList<Person>> groupToStudents = new HashMap<>(); //group, list of student
    HashMap<String, ArrayList<String>> surnameToGroups = new HashMap<>();// surname group

    void readFile(String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line1 = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
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

                phoneToStudent.put(stud.getM_phone_number(), stud);
                
                if (!groupToStudents.containsKey(stud.getM_group())){
                    groupToStudents.put(stud.getM_group(), new ArrayList<>());
                }
                groupToStudents.get(stud.getM_group()).add(stud);

                if (!surnameToGroups.containsKey(stud.getM_surname())){
                    surnameToGroups.put(stud.getM_surname(), new ArrayList<>());
                }
                if (!surnameToGroups.get(stud.getM_surname()).contains(stud.getM_group())){
                    surnameToGroups.get(stud.getM_surname()).add(stud.getM_group());
                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    void changeGroupByPhone(String phone, String newGroup){
        Person st = phoneToStudent.get(phone);
        if (st != null){
            String oldGroup = st.getM_group();
            groupToStudents.get(oldGroup).remove(st);
            st.setM_group(newGroup);
            
            if (!groupToStudents.containsKey(newGroup)) {
                groupToStudents.put(newGroup, new ArrayList<>());
            }
            groupToStudents.get(newGroup).add(st);
            
            surnameToGroups.get(st.getM_surname()).remove(oldGroup);
            if (!surnameToGroups.get(st.getM_surname()).contains(newGroup)){
                surnameToGroups.get(st.getM_surname()).add(newGroup);
            }
        }
    }

    ArrayList<Person> getStudentsOfGroup(String group){
        ArrayList<Person> res = new ArrayList<>();
        if (groupToStudents.containsKey(group)){
            res.addAll(groupToStudents.get(group));
        }
        for (int i = 0; i < res.size(); i++) {
            for (int j = i + 1; j < res.size(); j++) {
                Person s1 = res.get(i);
                Person s2 = res.get(j);
                if (s1.getM_surname().compareTo(s2.getM_surname()) > 0) {
                    res.set(i, s2);
                    res.set(j, s1);
                }else if (s1.getM_surname().equals(s2.getM_surname())) {
                    if (s1.getM_name().compareTo(s2.getM_name()) > 0) {
                        res.set(i, s2);
                        res.set(j, s1);
                    }
                }
            }
        }
        return res;
    }

    ArrayList<String> findGroupsBySurname(String surname){
        if (surnameToGroups.containsKey(surname)) {
            return new ArrayList<>(surnameToGroups.get(surname));
        }
        return new ArrayList<>();
    }
}