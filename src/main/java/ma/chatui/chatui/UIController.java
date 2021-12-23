package ma.chatui.chatui;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import org.springframework.stereotype.Component;

@Component
public class UIController {
    @FXML
    public LineChart<String, Double> Chart;
}
