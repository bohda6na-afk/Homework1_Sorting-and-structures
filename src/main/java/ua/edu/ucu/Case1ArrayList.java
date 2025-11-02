package ua.edu.ucu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class Case1ArrayList {
    ArrayList<Person> allStudents = new ArrayList<>();

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
            }
            reader.close();
        } 
        catch (Exception ex) {
            System.out.println("Error");
        }
    }
    //change student's group by phone
    void changeGroup(String phone, String newGroup) {
        for (int i = 0; i<allStudents.size(); i++){
            Person s = allStudents.get(i);
            if (s.getM_phone_number().equals(phone)){
                s.setM_group(newGroup);
                return;
            }
        }
    }
    //find students of group and sort them
    ArrayList<Person> getStudentsOfGroup(String group){
        ArrayList<Person> res = new ArrayList<>();

        for (int i=0; i<allStudents.size(); i++){
            if (allStudents.get(i).getM_group().equals(group)){
                res.add(allStudents.get(i));
            }
        }

        for (int i=0; i<res.size(); i++){
            for (int j =i+1; j<res.size(); j++){
                Person st1 = res.get(i);
                Person st2 = res.get(j);
                
                if (st1.getM_surname().compareTo(st2.getM_surname())> 0){
                    res.set(i, st2);
                    res.set(j, st1);
                }else if(
                    st1.getM_surname().equals(st2.getM_surname())) {
                        if (st1.getM_name().compareTo(st2.getM_name())> 0) {
                            res.set(i, st2);
                            res.set(j, st1);
                        }
                    }
                }
            }
            return res;
        }
        
    
// grops where student with surname is
ArrayList<String> findGroupsBySurname(String surname){
    ArrayList<String> groupList = new ArrayList<>();
    for (int i=0; i<allStudents.size(); i++){
        Person st = allStudents.get(i);

        if (st.getM_surname().equals(surname)){
            boolean groupInList = false;
            for(String gr : groupList){
                if (gr.equals(st.getM_group())){
                    groupInList = true;
                    break;
                }
            }
            if (!groupInList){
                groupList.add(st.getM_group());
            }
        }
    }
    return groupList;

}
}
