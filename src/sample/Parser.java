package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static String a=null;
    public static String b=null;
    public static String c=null;
    public static String d=null;
    public static String e=null;

    public static String title= null;
    public static String xAxis;
    public static List<Bar> bars = new ArrayList<Bar>();
    public static List<Line> lines = new ArrayList<Line>();


    public void ReadFile(File file) { //alınan dosyayı xml ve txt olarak ayrıştırma işlemi
        String fileType = null;
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            fileType = file.getName().substring(i + 1);
        }
        if (fileType.equals("txt")) {
            //System.out.println("txt içinde");
            txtParser(file);
        } else if (fileType.equals("xml")) {
            //System.out.println("xml içinde");
            System.out.println(xmlTitleParser(file));//Bu fonksiyon title'ı belgede arar bulur döndürür.
            title=xmlTitleParser(file);
            System.out.println(xmlxAxisParser(file));//Bu fonksiyon xAxis'i belgede arar bulur döndürür.
            xAxis=xmlxAxisParser(file);
            xmlParser(file);
        }
    }
    //SEÇİLEN TXT DOSYASININ OKUNUP SAKLANMA AŞAMASI, TİTLE VE XAXİS BURDA BULUNUR
    public void txtParser(File file) {
        try {
            Scanner scan = new Scanner(file);


            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(file));
            int i=0;
            String satir = reader.readLine();
            while (satir!=null) {
                i++;

                if(i==1)
                {
                    title = satir;
                    System.out.println(title);
                }
                if(i==2)
                {
                    xAxis = satir;
                    System.out.println(xAxis);
                }
                satir = reader.readLine();
            }
            while (scan.hasNextLine()) {

                String parts[] = scan.nextLine().split(",");
                if (parts.length != 1) {
                    Line line = new Line();
                    Bar bar = new Bar();

                    bar.setYear(Integer.valueOf(parts[0].substring(0,4)));
                    bar.setName(parts[1]);
                    bar.setCountry(parts[2]);
                    bar.setValue(Integer.valueOf(parts[3]));
                    bar.setCategory(parts[4]);

                    bars.add(bar);

                    line.setYear(Integer.valueOf(parts[0].substring(0,4)));
                    line.setName(parts[1]);
                    line.setCountry(parts[2]);
                    line.setValue(Integer.valueOf(parts[3]));
                    line.setCategory(parts[4]);
                    lines.add(line);
                }
            /*
                for (String part : parts) {

                    System.out.println(part);              //splitli olarak listeyi yazdırıyor bu kısım
                }
            */
            }

           /* for (int j=0;j<bars.size();j++) {
                System.out.println(bars.get(j));        //bar olarak listeyi okuyor bu kısım
            }*/

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //SEÇİLEN XML DOSYASININ DOMPARSER İLE OKUNMASI
    public void xmlParser(File file) {
        try {
            //Get Document Builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Build Document
            Document document = builder.parse(file);

            //Normalize the XML Structure
            document.getDocumentElement().normalize();

            //Here comes the root node
            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());

            //Get all employees
            NodeList nList = document.getElementsByTagName("field");
            System.out.println("============================");
            visitChildNodes(nList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //OKUNAN XML DOSYASININ AYRIŞMASI VE SAKLANMASI
    private static void visitChildNodes(NodeList nList)
    {
        int i=0;
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Bar bar = new Bar();
                Line line = new Line();
                if(i==0)
                {
                    a=node.getTextContent();
                    i=1;
                }
                else if(i==1) {
                    b = node.getTextContent();
                    i=2;
                }
                else if(i==2) {
                    c = node.getTextContent();
                    i=3;
                }
                else if(i==3) {
                    d = node.getTextContent();
                    i=4;
                }
                else if(i==4) {
                    e = node.getTextContent();
                    bar.setName(a);
                    bar.setYear(Integer.valueOf(c));
                    bar.setCountry(b);
                    bar.setValue(Integer.valueOf(d));
                    bar.setCategory(e);

                    bars.add(bar);

                    line.setName(a);
                    line.setYear(Integer.valueOf(c));
                    line.setCountry(b);
                    line.setValue(Integer.valueOf(d));
                    line.setCategory(e);

                    lines.add(line);
                    //Bar.addElements(a,b,c,d,e);

                    i=0;
                }

                else {
                }
            }
            if (node.hasChildNodes()) {
            }
        }
    }
    //XML DOSYASINDAN TİTLE ÇEKME
    public String xmlTitleParser(File file) {
        try {
            //Get Document Builder
            DocumentBuilderFactory factory3 = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder3 = factory3.newDocumentBuilder();

            //Build Document
            Document document3 = builder3.parse(file);

            //Normalize the XML Structure
            document3.getDocumentElement().normalize();

            //Get all employees
            NodeList titleList = document3.getElementsByTagName("title");


            String title = visitTitleAndXAxisNodes(titleList);
            return title;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //XML DOSYASINDAN XAXİS ÇEKME
    public String xmlxAxisParser(File file) {
        try {
            //Get Document Builder

            DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder2 = factory2.newDocumentBuilder();

            //Build Document
            Document document2 = builder2.parse(file);

            //Normalize the XML Structure
            document2.getDocumentElement().normalize();

            NodeList xLabelList = document2.getElementsByTagName("xlabel");


            String xAxis = visitTitleAndXAxisNodes(xLabelList);
            return xAxis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String visitTitleAndXAxisNodes(NodeList nList)
    {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                String Value=node.getTextContent();
                return Value;
            }

            return null;
        }

        return null;
    }
}