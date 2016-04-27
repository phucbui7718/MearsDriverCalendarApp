package mears.mearsdrivercalendarapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import entities.DriverSchedule;


public class calendarActivity extends AppCompatActivity
{
    public static  HashSet<Date> events = new HashSet<>();
    public String scheduleJson;

    private TimePicker time_picker;
    private TimePicker time_picker3;
    private Button button_show_time;
    private Button submitWork;
    private TextView textOutput;
    public String txtOut;
    TextView txtView;
    String txtEmpty = "";
    //Convert LinkedHashMap to java objects;
    public List<DriverSchedule> scheduleList = new ArrayList<DriverSchedule>();
    public Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Convert LinkedHashMap to Custom Objects
        ObjectMapper mapper = new ObjectMapper();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        time_picker = (TimePicker) findViewById(R.id.timePicker);
        time_picker3 = (TimePicker) findViewById(R.id.timePicker3);
        button_show_time = (Button) findViewById(R.id.btnShowIntendedSchedule);
        time_picker.setIs24HourView(true);
        time_picker3.setIs24HourView(true);
        txtView = (TextView)findViewById(R.id.txtOutputWindow);
        submitWork = (Button)findViewById(R.id.btnRequestWork);


        button_show_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (time_picker.getCurrentHour() != 0 && time_picker3.getCurrentHour() != 0) {//all but 0 conditions
                            if (time_picker3.getCurrentHour() > time_picker.getCurrentHour()) {//make sure end time is later than start time in non zero start time
                                if (time_picker.getCurrentHour() < 10) {//this is to append a 0 to start time
                                    if (time_picker3.getCurrentHour() < 10) {//this is to append a 0 to start time and end time
                                        txtOut = "If you wish to request the following work schedule: " + (selectedDate.getMonth() + 1) + "/" + selectedDate.getDate() +
                                                "/" + (selectedDate.getYear()+ 1900) + " from " + "0" + time_picker.getCurrentHour() + ":00 hours" + " to " + "0" + time_picker3.getCurrentHour() +
                                                ":00 hours," + " please tap the Request Work button below, or pick another day and/or time.";
                                        txtView.setText(txtOut);
                                    } else if (time_picker3.getCurrentHour() >= 10){//this is to append a 0 to start time only
                                        txtOut = "If you wish to request the following work schedule: " + (selectedDate.getMonth() + 1) + "/" + selectedDate.getDate() +
                                                "/" + (selectedDate.getYear()+ 1900) + " from " + "0" + time_picker.getCurrentHour() + ":00 hours" + " to " + time_picker3.getCurrentHour() +
                                                ":00 hours," + " please tap the Request Work button below, or pick another day and/or time.";
                                        txtView.setText(txtOut);
                                    }
                                } else if (time_picker.getCurrentHour() >= 10  && time_picker3.getCurrentHour() >= 10) {//do not append any zeros
                                    txtOut = "If you wish to request the following work schedule: " + (selectedDate.getMonth() + 1) + "/" + selectedDate.getDate() +
                                            "/" + (selectedDate.getYear()+ 1900) + " from " + time_picker.getCurrentHour() + ":00 hours" + " to " + time_picker3.getCurrentHour() +
                                            ":00 hours," + " please tap the Request Work button below, or pick another day and/or time.";
                                    txtView.setText(txtOut);
                                }
                            } else if (time_picker.getCurrentHour() >= time_picker3.getCurrentHour()) {//do not allow the start times = or > than end times
                                txtOut = "The end time must be later than the start time, and any work past 24:00 hours (00 on the End Time slider) must be scheduled on a separate day:  Please try again.";
                                txtView.setText(txtOut);
                            }
                        }else if (time_picker.getCurrentHour() == 0 && time_picker3.getCurrentHour() > 0){//allow for start time zero hour situation when end time is not 24:00
                            txtOut = "If you wish to request the following work schedule: " + (selectedDate.getMonth() + 1) + "/" + selectedDate.getDate() +
                                    "/" + (selectedDate.getYear()+ 1900) + " from " + "0" + time_picker.getCurrentHour() + ":00 hours" + " to " + "0" + time_picker3.getCurrentHour() +
                                    ":00 hours," + " please tap the Request Work button below, or pick another day and/or time.";
                            txtView.setText(txtOut);
                        }else if (time_picker.getCurrentHour() == 0 && time_picker3.getCurrentHour() == 0){//check independantly for 00 start and end times--do not allow
                            txtOut = "The end time must be later than the start time, and any work past 24:00 hours (00 on the End Time slider) must be scheduled on a separate day:  Please try again.";
                            txtView.setText(txtOut);  //end if tp && tp3 !=0
                        }else if (time_picker.getCurrentHour() >= 10 && time_picker3.getCurrentHour() == 0) {//allow for 24:00 hours end time situation where start time is beyond 9
                            txtOut = "If you wish to request the following work schedule: " + (selectedDate.getMonth() + 1) + "/" + selectedDate.getDate() +
                                    "/" + (selectedDate.getYear()+ 1900) + " from " + time_picker.getCurrentHour() + ":00 hours" + " to " + "24:00 hours," +
                                    " please tap the Request Work button below, or pick another day and/or time.";
                            txtView.setText(txtOut);
                        }else if (time_picker.getCurrentHour() > 0 && time_picker.getCurrentHour() < 10 && time_picker3.getCurrentHour() == 0){//allow for 24:00 hour end time with 9 or less start time
                            txtOut = "If you wish to request the following work schedule: " + (selectedDate.getMonth() + 1) + "/" + selectedDate.getDate() +
                                    "/" + (selectedDate.getYear()+ 1900) + " from " + "0" + time_picker.getCurrentHour() + ":00 hours" + " to " + "24" +
                                    ":00 hours," + " please tap the Request Work button below, or pick another day and/or time.";
                            txtView.setText(txtOut);
                        }

                    }//end of public void onClick(View v)

                }//end on click listener

        );//end of button_show_time.setOnClickListener(

        submitWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(calendarActivity.this, "Request has been submitted!", Toast.LENGTH_LONG).show();
            }
        });


        //Pull the schedule data from the backend.
        List<DriverSchedule> driverSchedule = new ArrayList<DriverSchedule>();
        String driverNum = getIntent().getExtras().getString("userID");

        try {
            driverSchedule = new ScheduleAsyncTask(driverSchedule, driverNum ).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("DRIVERSCHEDULE", driverNum);
        Log.d("DRIVERSCHEDULE", driverSchedule.toString());



        try {
            scheduleJson = mapper.writeValueAsString(driverSchedule);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            scheduleList = mapper.readValue(scheduleJson, mapper.getTypeFactory().constructCollectionType(List.class, DriverSchedule.class));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Put dates into HashSet for color display.


        for (int i = 0; i < scheduleList.size(); i++){
          events.add(scheduleList.get(i).toDate());
      }


        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));


        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(calendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            public void onDayPress(Date date){
                selectedDate = null;
                selectedDate = date;
                txtView.setText("You selected : " + (selectedDate.getMonth() + 1) +"/"+ selectedDate.getDate() + "/" + (selectedDate.getYear()+1900));

                        for (int i = 0; i < scheduleList.size(); i++){
                    if ( scheduleList.get(i).toDate().getYear() == selectedDate.getYear() && scheduleList.get(i).toDate().getMonth() == selectedDate.getMonth() &&
                            scheduleList.get(i).toDate().getDay() == selectedDate.getDay()){
                         String output = (scheduleList.get(i).toDate().getMonth()+1) + "/"+ (scheduleList.get(i).toDate().getDate() +"/"+ (scheduleList.get(i).toDate().getYear()+1900))+ "\n" + "Start Time: "  + scheduleList.get(i).getStartTime() + "\n" + "End time: " + scheduleList.get(i).getEndTime();
                        txtView.setText(output);
                        break;
                    }
                }

            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


//InsertSchedule AsyncTask
class ScheduleAsyncTask extends AsyncTask<Void, List<DriverSchedule>, List<DriverSchedule>> {

    private List<DriverSchedule> newDriverSchedule = new ArrayList<>();
    private String driverNum;

    ScheduleAsyncTask( List<DriverSchedule> newDriverSchedule, String driverNum){
        this.newDriverSchedule = newDriverSchedule;
        this.driverNum = driverNum;
    }

    @Override
    protected List<DriverSchedule> doInBackground(Void... params) {

        // Create Rest Template for request
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://10.0.2.2:8099/get/schedule/{driverNum}";
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

        //Populate the data
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("driverNum", driverNum);
        newDriverSchedule = restTemplate.getForObject(url, List.class, vars);
        return newDriverSchedule;
    }



}
