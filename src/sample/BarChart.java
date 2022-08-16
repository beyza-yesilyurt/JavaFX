package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BarChart extends Chart {

    public BarChart(String title, String xAsisLabel) {
        super(title, xAsisLabel);
    }
    public BarChart(){}


    @FXML
    private javafx.scene.chart.BarChart<String, Number> barChart;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;
    @FXML
    private Label label;

    @Override
    public String setCaption(String title,String xAsis){
        String caption=Parser.title + " / " + Parser.xAxis;
        return caption;
    }

    public void Start(ActionEvent event){
        barChart.getData().clear();
        Map<String, XYChart.Series> linemap=new HashMap<String,XYChart.Series>();

        for (int i=0;i<Parser.bars.size();i++){

            String title =setCaption(Parser.title,Parser.xAxis);
            label.setText(title);
            String name=Parser.bars.get(i).getCategory();
            String x=Parser.bars.get(i).getName(); //X VE Y EKSENLERİNE ATAMA İŞLEMİ GERÇEKLEŞTİ
            int y=Parser.bars.get(i).getValue();

            if(linemap.containsKey(name)){
                linemap.get(name).getData().add(new XYChart.Data<String,Number>( x,y));
                linemap.get(name).getData().size();
            }
            else{
                XYChart.Series series = new XYChart.Series();
                series.setName(name);
                series.getData().add(new XYChart.Data<String,Number>( x,y));
                linemap.put(name,series); //KATEGORİLER BELİRLENİYOR
            }
        }

        for (Map.Entry<String, XYChart.Series> entry : linemap.entrySet()) {
            barChart.getData().add(entry.getValue());
        }

    }

    public void Stop(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}

