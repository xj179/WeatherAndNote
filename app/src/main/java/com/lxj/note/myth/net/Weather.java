package com.lxj.note.myth.net;

import java.util.List;

/**
 * Created by castl on 2016/4/23.
 */
public class Weather {


    /**
     * reason : 查询成功
     * result : {"data":{"realtime":{"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}},"weather":[{"date":"2014-10-15","info":{"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]},"week":"三","nongli":"九月廿二"},{"date":"2014-10-16","info":{"dawn":["0","晴","13","东北风","3-4 级"],"day":["0","晴","25","东北风","3-4 级"],"night":["1","多云","15","东北风","3-4 级"]},"week":"四","nongli":"九月廿三"},{"date":"2014-10-17","info":{"dawn":["1","多云","15","东北风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["1","多云","16","东北风","3-4 级"]},"week":"五","nongli":"九月廿四"},{"date":"2014-10-18","info":{"dawn":["1","多云","16","东北风","3-4 级"],"day":["1","多云","26","东风","3-4 级"],"night":["1","多云","18","东风","3-4 级"]},"week":"六","nongli":"九月廿五"},{"date":"2014-10-19","info":{"dawn":["1","多云","18","东风","3-4 级"],"day":["1","多云","27","东风","3-4 级"],"night":["1","多云","19","东南风","3-4 级"]},"week":"日","nongli":"九月廿六"},{"date":"2014-10-20","info":{"dawn":["1","多云","19","东南风","3-4 级"],"day":["1","多云","27","东南风","3-4 级"],"night":["2","阴","18","南风","3-4 级"]},"week":"一","nongli":"九月廿七"},{"date":"2014-10-21","info":{"dawn":["2","阴","18","南风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["2","阴","17","","微风"]},"week":"二","nongli":"九月廿八"}],"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2014年10月15日09时","cityName":"温州"},"date":null,"isForeign":0}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"realtime":{"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}},"weather":[{"date":"2014-10-15","info":{"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]},"week":"三","nongli":"九月廿二"},{"date":"2014-10-16","info":{"dawn":["0","晴","13","东北风","3-4 级"],"day":["0","晴","25","东北风","3-4 级"],"night":["1","多云","15","东北风","3-4 级"]},"week":"四","nongli":"九月廿三"},{"date":"2014-10-17","info":{"dawn":["1","多云","15","东北风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["1","多云","16","东北风","3-4 级"]},"week":"五","nongli":"九月廿四"},{"date":"2014-10-18","info":{"dawn":["1","多云","16","东北风","3-4 级"],"day":["1","多云","26","东风","3-4 级"],"night":["1","多云","18","东风","3-4 级"]},"week":"六","nongli":"九月廿五"},{"date":"2014-10-19","info":{"dawn":["1","多云","18","东风","3-4 级"],"day":["1","多云","27","东风","3-4 级"],"night":["1","多云","19","东南风","3-4 级"]},"week":"日","nongli":"九月廿六"},{"date":"2014-10-20","info":{"dawn":["1","多云","19","东南风","3-4 级"],"day":["1","多云","27","东南风","3-4 级"],"night":["2","阴","18","南风","3-4 级"]},"week":"一","nongli":"九月廿七"},{"date":"2014-10-21","info":{"dawn":["2","阴","18","南风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["2","阴","17","","微风"]},"week":"二","nongli":"九月廿八"}],"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2014年10月15日09时","cityName":"温州"},"date":null,"isForeign":0}
     */

    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * realtime : {"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}}
         * life : {"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}}
         * weather : [{"date":"2014-10-15","info":{"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]},"week":"三","nongli":"九月廿二"},{"date":"2014-10-16","info":{"dawn":["0","晴","13","东北风","3-4 级"],"day":["0","晴","25","东北风","3-4 级"],"night":["1","多云","15","东北风","3-4 级"]},"week":"四","nongli":"九月廿三"},{"date":"2014-10-17","info":{"dawn":["1","多云","15","东北风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["1","多云","16","东北风","3-4 级"]},"week":"五","nongli":"九月廿四"},{"date":"2014-10-18","info":{"dawn":["1","多云","16","东北风","3-4 级"],"day":["1","多云","26","东风","3-4 级"],"night":["1","多云","18","东风","3-4 级"]},"week":"六","nongli":"九月廿五"},{"date":"2014-10-19","info":{"dawn":["1","多云","18","东风","3-4 级"],"day":["1","多云","27","东风","3-4 级"],"night":["1","多云","19","东南风","3-4 级"]},"week":"日","nongli":"九月廿六"},{"date":"2014-10-20","info":{"dawn":["1","多云","19","东南风","3-4 级"],"day":["1","多云","27","东南风","3-4 级"],"night":["2","阴","18","南风","3-4 级"]},"week":"一","nongli":"九月廿七"},{"date":"2014-10-21","info":{"dawn":["2","阴","18","南风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["2","阴","17","","微风"]},"week":"二","nongli":"九月廿八"}]
         * pm25 : {"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2014年10月15日09时","cityName":"温州"}
         * date : null
         * isForeign : 0
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * city_code : 101210701
             * city_name : 温州
             * date : 2014-10-15
             * time : 09:00:00
             * week : 3
             * moon : 九月廿二
             * dataUptime : 1413337811
             * weather : {"temperature":"19","humidity":"54","info":"雾","img":"18"}
             * wind : {"direct":"北风","power":"1级","offset":null,"windspeed":null}
             */

            private RealtimeBean realtime;
            private Object date;
            private int isForeign;
            /**
             * date : 2014-10-15
             * info : {"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]}
             * week : 三
             * nongli : 九月廿二
             */

            private List<WeatherBean> weather;

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public Object getDate() {
                return date;
            }

            public void setDate(Object date) {
                this.date = date;
            }

            public int getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(int isForeign) {
                this.isForeign = isForeign;
            }

            public List<WeatherBean> getWeather() {
                return weather;
            }

            public void setWeather(List<WeatherBean> weather) {
                this.weather = weather;
            }

            public static class RealtimeBean {
                private String city_code;
                private String city_name;
                private String date;
                private String time;
                private int week;
                private String moon;
                private int dataUptime;
                /**
                 * temperature : 19
                 * humidity : 54
                 * info : 雾
                 * img : 18
                 */

                private WeatherBean weather;
                /**
                 * direct : 北风
                 * power : 1级
                 * offset : null
                 * windspeed : null
                 */

                private WindBean wind;

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public static class WeatherBean {
                    private String temperature;
                    private String humidity;
                    private String info;
                    private String img;

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }

                public static class WindBean {
                    private String direct;
                    private String power;
                    private Object offset;
                    private Object windspeed;

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }

                    public Object getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(Object windspeed) {
                        this.windspeed = windspeed;
                    }
                }
            }

            public static class WeatherBean {
                private String date;
                private InfoBean info;
                private String week;
                private String nongli;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public static class InfoBean {
                    private List<String> day;
                    private List<String> night;

                    public List<String> getDay() {
                        return day;
                    }

                    public void setDay(List<String> day) {
                        this.day = day;
                    }

                    public List<String> getNight() {
                        return night;
                    }

                    public void setNight(List<String> night) {
                        this.night = night;
                    }
                }
            }
        }
    }
}