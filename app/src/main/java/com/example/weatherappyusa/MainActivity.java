package com.example.weatherappyusa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText editCity;
    private Button btnSearch;
    private TextView cityName, temperature, feelsLikeTemperature, windSpeed, humidity, pressure, visibility, uvIndex;
    private ImageView weatherIcon;
    private final String apiKey = "YOUR_API_KEY_HERE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editCity = findViewById(R.id.editCity);
        btnSearch = findViewById(R.id.btnSearch);
        cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        feelsLikeTemperature = findViewById(R.id.feelsLikeTemperature);
        windSpeed = findViewById(R.id.windSpeed);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        visibility = findViewById(R.id.visibility);
        uvIndex = findViewById(R.id.uvIndex);
        weatherIcon = findViewById(R.id.weatherIcon);

        btnSearch.setOnClickListener(v -> {
            String cityInput = editCity.getText().toString().trim();
            if (!cityInput.isEmpty()) {
                fetchWeatherData(removeTurkishChars(cityInput));
            } else {
                Toast.makeText(this, "Lütfen bir şehir adı girin", Toast.LENGTH_SHORT).show();
            }
        });

        fetchWeatherData("Erzincan");

    }

    private void fetchWeatherData(String cityNameStr) {
        String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey +
                "&q=" + cityNameStr + "&aqi=no";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject location = response.getJSONObject("location");
                        JSONObject current = response.getJSONObject("current");

                        String city = location.getString("name");
                        String temp = current.getString("temp_c");
                        String feelsLikeTemp = current.getString("feelslike_c");
                        String wind = current.getString("wind_mph");
                        String humidityValue = current.getString("humidity");
                        String pressureValue = current.getString("pressure_mb");
                        String visibilityValue = current.getString("vis_km");
                        String uvValue = current.getString("uv");
                        String iconUrl = "https:" + current.getJSONObject("condition").getString("icon");

                        cityName.setText(city);
                        temperature.setText("Sıcaklık: " + temp + "°C");
                        feelsLikeTemperature.setText("Hissedilen: " + feelsLikeTemp + "°C");
                        windSpeed.setText("Rüzgar Hızı: " + wind + " mph");
                        humidity.setText("Nem: " + humidityValue + "%");
                        pressure.setText("Basınç: " + pressureValue + " mb");
                        visibility.setText("Görünürlük: " + visibilityValue + " km");
                        uvIndex.setText("UV İndeksi: " + uvValue);

                        Picasso.get().load(iconUrl).into(weatherIcon);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Veri çözümlenemedi.", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(MainActivity.this, "Hava durumu alınamadı.", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    private String removeTurkishChars(String input) {
        return input.replace("ç", "c").replace("Ç", "C")
                .replace("ğ", "g").replace("Ğ", "G")
                .replace("ı", "i").replace("İ", "I")
                .replace("ö", "o").replace("Ö", "O")
                .replace("ş", "s").replace("Ş", "S")
                .replace("ü", "u").replace("Ü", "U");
    }
}
