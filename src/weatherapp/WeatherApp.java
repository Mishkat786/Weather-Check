
package weatherapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.*;


public class WeatherApp extends JFrame implements ActionListener {
    
    ImageIcon basic;
    JLabel label1;
    JFrame frame;
    JLabel label;
    JLabel cityName;
    JLabel lastBuild;
    JLabel humidityTitle;
    JLabel humidityValue;
    JTextField textfield;
    JButton button;
    
    public static void main(String[] args) {
       
        WeatherApp gui = new WeatherApp();
        gui.setBounds(500, 100, 320, 480);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
       
    }
    
    public WeatherApp() {

        setLayout(new FlowLayout());
        label = new JLabel("Welcome! Please Enter your location");
        add(label);

        textfield = new JTextField(15);
        add(textfield);
        


        button = new JButton("Check weather");
        button.addActionListener(this);
        add(button);

        cityName = new JLabel("");
        add(cityName);
        cityName.setFont(new Font("Serif", Font.BOLD, 16));
        lastBuild = new JLabel("");
        add(lastBuild);
        humidityTitle = new JLabel("");
        add(humidityTitle);
        humidityTitle.setVisible(false);
        humidityValue = new JLabel("");
        add(humidityValue);

       
        label1 = new JLabel(basic);
        add(label1);

           
    }
    
    @Override
    public void actionPerformed(ActionEvent e){

        JButton button = (JButton) e.getSource();
        if (e.getSource() == button){
            String data = textfield.getText();
           
            WeatherAppApi weather = new WeatherAppApi(data);
        
        JSONObject rootObj = new JSONObject(WeatherAppApi.theWeatherJson);
        JSONObject queryObj = rootObj.getJSONObject("query");
        JSONObject resultsObj = queryObj.getJSONObject("results").getJSONObject("channel");
        JSONObject locationObj = queryObj.getJSONObject("results").getJSONObject("channel").getJSONObject("location");
        JSONObject atmosphereObj = queryObj.getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere");
        ShowWeather sh = new ShowWeather();
        sh.setVisible(true);
        sh.parseWeather(resultsObj);
        
        }

    }
    
}


class showResult extends JFrame {
    
}