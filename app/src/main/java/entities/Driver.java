package entities;


import org.springframework.data.annotation.Id;

/**
 * Created by Phuc on 2/19/2016.
 */

public class Driver {

    @Id
    private String id;
    private String driverNum;
    private String password;

    public Driver(){

    }

    public Driver(String password, String driverNum) {
        this.password = password;
        this.driverNum = driverNum;
    }

    public String getDriveNum() {
        return driverNum;
    }

    public void setDriverNum(String driverNum) {
        this.driverNum = driverNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
