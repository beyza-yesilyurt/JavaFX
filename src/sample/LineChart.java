package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LineChart extends Chart{

    public LineChart(String title, String xAxisLabel) {
        super(title, xAxisLabel);
    }
    public LineChart(){
    }

    @FXML
    private Button buton1;
    @FXML
    private Button buton2;
    @FXML
    private javafx.scene.chart.LineChart<String, Number> lineChart;
    @FXML
    private Label label;

    @Override
    public String setCaption(String title,String xAsis){
        String caption=Parser.title + " / " + Parser.xAxis;
        return caption;
    }

    public void Start(ActionEvent event) {
        lineChart.getData().clear();
        Map<String, XYChart.Series> linemap = new HashMap<String, XYChart.Series>(); //Aldığımız verileri en doğru şekilde map ile çizdirebildik.

        for (int i = 0; i < Parser.lines.size(); i++) {

            String title = setCaption(Parser.title, Parser.xAxis);
            label.setText(title);
            String name = Parser.lines.get(i).getName();
            int x = Parser.lines.get(i).getYear(); //x ve y eksenlerine atama yaptık
            int y = Parser.lines.get(i).getValue();
            if (linemap.containsKey(name)) {
                linemap.get(name).getData().add(new XYChart.Data<String, Number>(String.valueOf(x), y));
                linemap.get(name).getData().size();
            } else {
                XYChart.Series series = new XYChart.Series();
                series.setName(name);
                series.getData().add(new XYChart.Data<String, Number>(String.valueOf(x), y));
                linemap.put(name, series); //kategorilere göre ayrılmış isimler
            }
        }

        for (Map.Entry<String, XYChart.Series> entry : linemap.entrySet()) {
            lineChart.getData().add(entry.getValue());
        }
    }
    public void Stop(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}