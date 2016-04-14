package entities;


import java.util.Date;
import java.util.List;



public class DriverSchedule {


    private long id;
    private String driverNum;
    private String scheduleDate;
    private String startTime;
    private String endTime;

    public DriverSchedule() {}

    public DriverSchedule(long id, String driverNum, String scheduleDate,
                          String startTime, String endTime) {
        this.setId(id);
        this.setDriverNum(driverNum);
        this.setScheduleDate(scheduleDate);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDriverNum() {
        return driverNum;
    }

    public void setDriverNum(String driverNum) {
        this.driverNum = driverNum;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return String.format("Date: %s \nShift: %s - %s",
                scheduleDate, startTime, endTime);
    }
}