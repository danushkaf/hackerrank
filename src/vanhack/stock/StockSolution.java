package vanhack.stock;

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import com.google.gson.*;

/**
 * Created by danushka on 9/29/19.
 */
public class StockSolution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String _firstDate;
        try {
            _firstDate = in.nextLine();
        } catch (Exception e) {
            _firstDate = null;
        }
        String _lastDate;
        try {
            _lastDate = in.nextLine();
        } catch (Exception e) {
            _lastDate = null;
        }
        String _weekDay;
        try {
            _weekDay = in.nextLine();
        } catch (Exception e) {
            _weekDay = null;
        }

        openAndClosePrices(_firstDate, _lastDate, _weekDay);
//        openAndClosePrices("1-January-2000", "22-February-2000", "Monday");
    }

    /*
     * Complete the function below.
     */
    static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date firstDateObj = null;
        Date lastDateObj = null;
        Calendar firstDateCalander = Calendar.getInstance();
        Calendar lastDateCalander = Calendar.getInstance();
        String firstDateYear = "";
        String firstDateMonth = "";
        String searchParam = "";
        try {
            if (!firstDate.equals(null)) {
                firstDateObj = formatter.parse(firstDate);
                firstDateCalander.setTime(firstDateObj);
                String[] values = firstDate.split("-");
                firstDateYear = values[2];
                firstDateMonth = values[1];
            }
        } catch (ParseException e) {
//            throw new RuntimeException(e);
        }

        try {
            if (!lastDate.equals(null)) {
                lastDateObj = formatter.parse(lastDate);
                lastDateCalander.setTime(lastDateObj);
                String[] values = firstDate.split("-");
            }
        } catch (ParseException e) {
//            throw new RuntimeException(e);
        }
        if (firstDateObj != null && lastDateObj != null) {
            if (firstDate.equals(lastDate)) {
                searchParam = firstDate;
            } else if (firstDateCalander.get(Calendar.YEAR) == lastDateCalander.get(Calendar.YEAR) &&
                    firstDateCalander.get(Calendar.MONTH) == lastDateCalander.get(Calendar.MONTH)) {
                searchParam = firstDateMonth + "-" + firstDateYear;
            } else if (firstDateCalander.get(Calendar.YEAR) == lastDateCalander.get(Calendar.YEAR)){
                searchParam = firstDateYear;
            }
        }
        int pageNo = 1;
        Gson gson = new Gson();
        Result result = new Result();
        result = getResult(searchParam, pageNo, gson, result);
        int noOfPages = result.total_pages;
        boolean isFilteringDone = filterByDay(result, firstDateObj, lastDateObj, weekDay);
        if (!isFilteringDone && noOfPages > 1) {
            for (int i = pageNo ; i <= noOfPages ; i++) {
                pageNo++;
                result = getResult(searchParam, pageNo, gson, result);
                isFilteringDone = filterByDay(result, firstDateObj, lastDateObj, weekDay);
                if (isFilteringDone) {
                    break;
                }
            }
        }

    }

    private static boolean filterByDay(Result result, Date firstDateObj, Date lastDateObj, String weekDay) {
        boolean isFilteringDone = false;
        for (Data data : result.data) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = null;
            Calendar dateCalander = Calendar.getInstance();
            try {
                date = format.parse(data.date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            dateCalander.setTime(date);
            String currentDay = getDayOfWeek(dateCalander.get(Calendar.DAY_OF_WEEK));
            if ( null != firstDateObj &&
                    null != lastDateObj &&
                    (date.after(firstDateObj) && date.before(lastDateObj)) &&
                    currentDay.equalsIgnoreCase(weekDay)) {
                System.out.println(data.date + " " + data.open + " " + data.close);
            } else if (null != lastDateObj && date.after(lastDateObj)) {
                isFilteringDone = true;
                break;
            } else if (null == firstDateObj && null == lastDateObj && currentDay.equalsIgnoreCase(weekDay)) {
                System.out.println(data.date + " " + data.open + " " + data.close);
            }
        }
        return isFilteringDone;
    }

    private static String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    private static Result getResult(String searchParam, int pageNo, Gson gson, Result result) {
        try {
            String response = getSearch(searchParam, pageNo);
            result = gson.fromJson(response, result.getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static class Result {
        protected Data[] data;
        protected int page;
        protected int per_page;
        protected int total;
        protected int total_pages;
    }

    public static class Data {
        protected String date;
        protected double open;
        protected double high;
        protected double close;
    }

    // HTTP GET request
    static String getSearch(String datePart, int pageNo) throws Exception {

        String url = "https://jsonmock.hackerrank.com/api/stocks/search?date=" + datePart + "&page=" + pageNo;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();

    }
}
