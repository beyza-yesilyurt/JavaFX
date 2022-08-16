package sample;

import java.util.ArrayList;

public class Line  {
        public static ArrayList<Line> arraylist=new ArrayList<>();
        String name;
        String country;
        String year;
        String value;
        String category;

        Line(String name, String country, String year, String value, String category)
        {
            this.name = name;
            this.country = country;
            this.year = year;
            this.value = value;
            this.category = category;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public Integer getYear() {
            return Integer.valueOf(year);
        }
        public void setYear(Integer year) {
            this.year = String.valueOf(year);
        }
        public Integer getValue() {
            return Integer.valueOf(value);
        }
        public void setValue(Integer value) {
            this.value = String.valueOf(value);
        }
        public String getCategory() {
            return category;
        }
        public void setCategory(String category) {
            this.category = category;
        }

        public Line() {

        }

        public int nextValue(){
            return 1;
        }

        public static void addElements(String name, String country, String year, String value,  String category)
        {
            arraylist.add(new Line (name, country, year, value, category));
            for(int i=0;i< arraylist.size();i++){
                System.out.println(arraylist.get(i));}
        }
        @Override
        public String toString()
        {
            return "Line [name= " + name + ", counrty= " + country + ", year= " + year + ", value= " + value + ", category= " + category + "]";
        }
    }

