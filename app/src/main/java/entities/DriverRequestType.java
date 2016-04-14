package entities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum DriverRequestType {
    DAYOFF(1) {
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public String getDescription() {
            return "Day Off";
        }

        @Override
        public int getEarliestDayToSubmitRequest() {

            return 60;
        }

        @Override
        public int getLatestDayToSubmitRequest() {
            return 0;
        }
    },
    WORK(2){
        @Override
        public int getId() {
            return 2;
        }

        @Override
        public String getDescription() {
            return "Work";
        }

        @Override
        public int getEarliestDayToSubmitRequest() {
            return 60;
        }

        @Override
        public int getLatestDayToSubmitRequest() {
            return 7;
        }
    };
    private int value;

    public abstract int getId();
    public abstract String getDescription();
    public abstract int getEarliestDayToSubmitRequest();
    public abstract int getLatestDayToSubmitRequest();

    private DriverRequestType(int n) {
        this.value = n;
    }

    public Date getLatestDateToSubmitRequest(Date requestDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(requestDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH) - this.getLatestDayToSubmitRequest();
        Date latestDate = new GregorianCalendar(year, month, day).getTime();
        return latestDate;
    }

    public Date getEarliestDateToSubmitRequest(Date requestDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(requestDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH) - this.getEarliestDayToSubmitRequest();
        Date earliestDate = new GregorianCalendar(year, month, day).getTime();
        return earliestDate;
    }

    public boolean isWithinDateBounds(Date requestDate) {
        try {
            Date today = new Date();
            Date latestDate = this.getLatestDateToSubmitRequest(requestDate);
            Date earliestDate = this.getEarliestDateToSubmitRequest(requestDate);
            if (today.getTime() <= latestDate.getTime() &&
                    today.getTime() >= earliestDate.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}