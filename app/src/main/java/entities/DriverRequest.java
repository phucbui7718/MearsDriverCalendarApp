package entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DriverRequest {

    private String driverNum;
    private DriverRequestType requestType;
    private String requestDate;
    private String reason;

    public DriverRequest() {
    }


    public DriverRequest(String driverNum, DriverRequestType requestType,
                         String requestDate, String reason) {
        this.setDriverNum(driverNum);
        this.setRequestType(requestType);
        this.setRequestDate(requestDate);
        this.setReason(reason);
    }

    public String getDriverNum() {
        return driverNum;
    }

    public void setDriverNum(String driverNum) {
        this.driverNum = driverNum;
    }

    public void setRequestType(DriverRequestType requestType) {
        this.requestType = requestType;
    }

    public DriverRequestType getRequestType() {
        return requestType;
    }

    public String getRequestDateString() {
        return requestDate;
    }

    public String getFormattedRequestDateString() {
        return requestDate.replace("-", "/");
    }

    public Date getRequestDate() {
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String tempDate = this.requestDate.replaceAll("-", "/");
            return df.parse(tempDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
            return null;
        }
    }

    public void setRequestDate (String requestDate) {
        this.requestDate = requestDate.replace("/", "-");
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason.replaceAll("'", "");
    }

    public String toString() {
        return String.format("DriverNum: %s \nDate: %s \nType: %s \nReason: %s",
                this.getDriverNum(), this.getFormattedRequestDateString(),
                this.getRequestType().getDescription(), this.getReason());
    }

}