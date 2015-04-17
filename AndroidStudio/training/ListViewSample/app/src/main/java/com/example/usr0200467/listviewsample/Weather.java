package com.example.usr0200467.listviewsample;

import java.util.List;

public class Weather {
    private static final String TAG = Weather.class.getSimpleName();
    private final Weather self = this;

    List<Detail> list;

    public List<Detail> getList() {
        return list;
    }

    public void setList(List<Detail> list) {
        this.list = list;
    }

    public class Detail {

        String dt;
        Temp temp;
        List<WeatherDetail> weather;

        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public List<WeatherDetail> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherDetail> weather) {
            this.weather = weather;
        }

        public class Temp{

            String max;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }
        }

        public class WeatherDetail{
            String icon;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

    }

}
