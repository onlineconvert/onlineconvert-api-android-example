package com.sample.api.oc.onlineconvertsample;


import android.os.AsyncTask;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.InformationApi;
import io.swagger.client.model.Conversion;

/**
 * Created by Andres on 16/10/2015.
 */
public class GetAudioConversionsInfo extends AsyncTask<Void, Void, List<Conversion>> {

    private BigDecimal page;
    private String category;
    private String target;
    private List<Conversion> conversions = null;
    private InformationApi informationApi;
    private MainActivity myActivity;

    public GetAudioConversionsInfo(MainActivity activity) {
        this.myActivity = activity;
    }

    @Override
    protected List<Conversion> doInBackground(Void... params) {

        try {
            this.page = new BigDecimal(1);
            this.category = "audio";
            this.target = "";
            this.informationApi = new InformationApi();
            this.conversions = this.informationApi.conversionsGet(category, target, page);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return this.conversions;
    }

    @Override
    protected void onPostExecute(List<Conversion> conversions) {
        myActivity.setConversions(conversions);
    }

}
