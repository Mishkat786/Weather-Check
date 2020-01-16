
package weatherapp;
import java.net.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.io.*;


public class WeatherAppApi {
    
    static String theWeatherJson;
    static String theCity;
    
   
    public WeatherAppApi(String city)
    {
        theCity = city;
        theWeatherJson = getWeatherAsJson(city);
        
    }
    
    
    
   
    
    String getWeatherAsJson(String city)
    {
        try{
            
           
            URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C%20ak%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
            URLConnection con = url.openConnection();
            Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
            Matcher m = p.matcher(con.getContentType());
            
            String charset = m.matches() ? m.group(1) : "ISO-8859-1";
            Reader r = new InputStreamReader(con.getInputStream(), charset);
            StringBuilder buf = new StringBuilder();
            while (true) {
              int ch = r.read();
              if (ch < 0)
                break;
              buf.append((char) ch);
            }
            String str = buf.toString();
            return(str);
        }
        catch(Exception e) {System.err.println("Weather API Exception: "+e);}
        return null;
    }
}
