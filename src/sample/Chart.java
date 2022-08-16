package sample;

public abstract class Chart {
    private String title;
    private String xAsisLabel;

    public Chart(String title,String xAsisLabel){
        this.title = title;
        this.xAsisLabel = xAsisLabel;
    }

    public Chart() {
    }
    public String setCaption(String title,String xAsis){
        String caption=Parser.title + "/" + Parser.xAxis;
        return caption;
    }
    public void reset(){}
}
