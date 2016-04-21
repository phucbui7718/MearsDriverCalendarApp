package mears.mearsdrivercalendarapp;


import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import entities.Driver;
import entities.DriverRequest;
import entities.DriverSchedule;


public class calendarActivity extends AppCompatActivity
{
    public static  HashSet<Date> events = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Pull the schedule data from the backend.
        List<DriverSchedule> driverSchedule = new ArrayList<>();
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


        //Put dates into HashSet for color display.


//        for (int i = 0; i < driverSchedule.size(); i++){
//
//            Log.d("DRIVERSCHEDULE", driverSchedule.get(i).toDate().toString());
//        }


        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        events.add(new Date());
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(calendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
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
