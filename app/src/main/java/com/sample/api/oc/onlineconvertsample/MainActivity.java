package com.sample.api.oc.onlineconvertsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import io.swagger.client.model.Conversion;

public class MainActivity extends AppCompatActivity {

    private List<Conversion> conversions;

    public void setConversions(List<Conversion> conversions) {
        this.conversions = conversions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new GetAudioConversionsInfo(this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Button btnGetConversionsList = (Button) findViewById(R.id.btnGetConversionsList);
        final TextView outputConversionsList = (TextView) findViewById(R.id.outputConversionsList);


        btnGetConversionsList.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        outputConversionsList.append("Audio Conversions");
                        for (Conversion conversion : conversions) {
                            outputConversionsList.append("************************\n");
                            outputConversionsList.append("Target: " + conversion.getTarget().toString() + "\n");
                            outputConversionsList.append("Options: " + conversion.getOptions().toString());
                            outputConversionsList.append("************************\n");
                        }
                    }
                }
        );

    }

}


