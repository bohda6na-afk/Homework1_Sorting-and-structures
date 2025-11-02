package ua.edu.ucu;

public class Person
{
    private String m_name;
    private String m_surname;
    private String m_email;
    private int m_birth_year;
    private int m_birth_month;
    private int m_birth_day;
    private String m_group;
    private float m_rating;
    private String m_phone_number;

    public Person(String m_name, String m_surname, String m_email,int m_birth_year,
                  int m_birth_month,int m_birth_day,String m_group,float m_rating,String m_phone_number)
    {
        this.m_name = m_name;
        this.m_surname= m_surname;
        this.m_email = m_email;
        this.m_birth_year = m_birth_year;
        this.m_birth_month = m_birth_month;
        this.m_birth_day = m_birth_day;
        this.m_group = m_group;
        this.m_rating = m_rating;
        this.m_phone_number = m_phone_number;

    }
    
    public String getM_name() {
        return m_name;
    }
    public String getM_surname() {
        return m_surname;
    }
    public String getM_email() {
        return m_email;
    }
    public int getM_birth_year() {
        return m_birth_year;
    }
    public int getM_birth_month() {
        return m_birth_month;
    }
    public int getM_birth_day() {
        return m_birth_day;
    }
    public String getM_group() {
        return m_group;
    }
    public float getM_rating() {
        return m_rating;
    }
    public String getM_phone_number() {
        return m_phone_number;
    }

    public void setM_group(String m_group)
    {
        this.m_group = m_group;
    }

    @Override
    public String toString() {
        return m_name + "," + m_surname + "," + m_email + "," + m_birth_year + "," + 
               m_birth_month + "," + m_birth_day + "," + m_group + "," + m_rating + "," + m_phone_number;
    }

}
