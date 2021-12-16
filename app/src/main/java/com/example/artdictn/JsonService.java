package com.example.artdictn;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonService {
    ArrayList<Artwork> fullArtList = new ArrayList<>(0);

    //keyword search result: a list of object with basic info from Chicago museum, only image need to be displayed
    //json structure see:jsonArtList - CHI
    public ArrayList<Artwork> parseArtListJsonChi(String jsonThumbStr) {
        ArrayList<Artwork> fullArtListChi = new ArrayList<>(0);
        try {
            JSONObject jsonObject = new JSONObject(jsonThumbStr);
            JSONArray artArray = jsonObject.getJSONArray("data");
            for(int i=0;i<artArray.length();i++) {
                JSONObject thumbObj = artArray.getJSONObject(i);
                int id = thumbObj.getInt("id");
                String title = thumbObj.getString("title");
                int source = 1;

                //QUESTION: is it how i mark the source??
                fullArtList.add(new Artwork(String.valueOf(id),title,source));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullArtListChi;
    }

    //json structure see:jsonArtList - RIJKS
    public ArrayList<Artwork> parseArtListJsonRijks(String jsonThumbStr) {
        ArrayList<Artwork> fullArtListR = new ArrayList<>(0);
        try {
            JSONObject jsonObject = new JSONObject(jsonThumbStr);
            JSONArray artArray = jsonObject.getJSONArray("artObjects");
            for(int i=0;i<artArray.length();i++) {
                JSONObject thumbObj = artArray.getJSONObject(i);
                String id = thumbObj.getString("objectNumber");
                String title = thumbObj.getString("title");
                JSONObject imgObj = thumbObj.getJSONObject("webImage");
                String image_url = imgObj.getString("url");
                int source = 2;
                Artwork artResult = new Artwork(id,title,image_url,source);
                fullArtListR.add(artResult);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullArtListR;
    }

//    public ArrayList<Artwork> parseListJsonMet(String jsonSearchStr) {
//        ArrayList<Artwork> fullArtListMet = new ArrayList<>(0);
//
//
//    }

//detail info for called after click one artwork, displayed on detailActivity
    //json structure: jsonArtDetail-CHI
//
//    public String parseImgIDJsonChi(String jsonImgStr) {
//        Artwork artThumbnail = new Artwork();
//        JSONObject jsonObject = null;
//        try{
//            jsonObject = new JSONObject(jsonImgStr);
//            JSONObject detailObj = jsonObject.getJSONObject("data");
//            String imageID = detailObj.getString("image_id");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return imageID;
//    }

    public Artwork parseArtDetailJsonChi(String jsonArtStr) {
        Artwork artDetailData = new Artwork();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonArtStr);
            JSONObject detailObj = jsonObject.getJSONObject("data");
            int id = detailObj.getInt("id");
            String title = detailObj.getString("title");
            String artist = detailObj.getString("artist_display");
            String dimensions = detailObj.getString("dimensions");
            String medium = detailObj.getString("medium_display");
            boolean zoomable = detailObj.getBoolean("is_zoomable");
            String image = detailObj.getString("image_id");
            int source = 1;
            artDetailData = new Artwork(String.valueOf(id),title,artist,medium,dimensions,image,null,source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return artDetailData;
    }

    public Artwork parseArtDetailJsonRijks(String jsonArtStr) {
        Artwork artDetailData = new Artwork();


        try {
            JSONObject jsonObject = new JSONObject(jsonArtStr);
            JSONObject detailObj = jsonObject.getJSONObject("artObject");
            String id = detailObj.getString("objectNumber");
            String title = detailObj.getString("title");
            JSONObject imageObj = detailObj.getJSONObject("webImage");
            String img_url = imageObj.getString("url");
            JSONArray makerArray = detailObj.getJSONArray("principalMakers");
            JSONObject makerObj = makerArray.getJSONObject(0);
            String artist = makerObj.getString("labelDesc");
            String dimensions = detailObj.getString("subTitle");
            String description = detailObj.getString("plaqueDescriptionEnglish");
            String medium = detailObj.getString("physicalMedium");
            int source = 2;
            artDetailData = new Artwork(String.valueOf(id),title,artist,medium,dimensions,img_url,description,source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return artDetailData;
    }
}
