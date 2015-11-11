package chumilion.asynctasks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity
{
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        ProgressBarTask progressBarTask = new ProgressBarTask();
        progressBarTask.myActivity = this;
        progressBarTask.execute(5);
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

    public void setUpProgress(int max)
    {
        progressBar.setMax(max);
    }

    public void updateProgress(int run)
    {
        progressBar.setProgress(run);
    }

    public void finishProgress(String result)
    {
        textView.setText(result);
    }
}
class ProgressBarTask extends AsyncTask<Integer, Integer, String>
{
    MainActivity myActivity;
    protected String doInBackground(Integer... tim)
    {
        myActivity.setUpProgress(tim[0]*1000);
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < tim[0]*1000)
        {
            int run = (int) (System.currentTimeMillis() - startTime);
            publishProgress(run);
        }
        return "Finished!";
    }

    protected void onProgressUpdate(Integer... runn)
    {
        myActivity.updateProgress(runn[0]);
    }

    protected void onPostExecute(String result)
    {
        myActivity.finishProgress(result);
    }
}
