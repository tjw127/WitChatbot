package wit;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import url.Base;
import wit.chatbot_api_request.ApiRequest;

public class Wit{

    public String url;
    public String arguments;
    public String request_body;

    public static class RequestBodyBuilder implements wit.Builder<Object> {

        JSONObject jsonObject;

        private String request_body;

        RequestBodyBuilder()
        {
            jsonObject = new JSONObject();

        }

        public RequestBodyBuilder id(String id){
            try {
                this.jsonObject.put("id", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder doc(String doc){
            try {
                this.jsonObject.put("doc", doc);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder lookups(String array){
            try {
                this.jsonObject.put("lookups", array);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder values(String values){
            try {
                this.jsonObject.put("values", values);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder expression(String expression){
            try {
                this.jsonObject.put("expression", expression);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder expressions(String array){
            try {
                this.jsonObject.put("expressions", array);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder metadata(String data){
            try {
                this.jsonObject.put("metadata", data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder text(String text){
            try {
                this.jsonObject.put("text", text);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder entities(String array){
            try {
                this.jsonObject.put("entities", array);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder name(String name){
            try {
                this.jsonObject.put("name", name);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder lang(String lang){
            try {
                this.jsonObject.put("lang", lang);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder private_arg(String value){
            try {
                this.jsonObject.put("private", value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public RequestBodyBuilder desc(String value){
            try {
                this.jsonObject.put("desc", value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }


        @Override
        public Object build() {

            this.request_body = this.jsonObject.toString();

            return new Wit(this);
        }
    }


    public static class ArgumentsBuilder implements wit.Builder<Object>{

        HashMap<String, String> params;

        String arguments = null;

        ArgumentsBuilder(){
            params = new HashMap<String, String>();
        }

        public ArgumentsBuilder q(String value){

            this.params.put("q", value);

            return this;
        }

        public ArgumentsBuilder context(String value){

            this.params.put("context", value);

            return this;
        }

        public ArgumentsBuilder msg_id(String value){

            this.params.put("msg_id", value);

            return this;
        }

        public ArgumentsBuilder thread_id(String value){

            this.params.put("thread_id", value);

            return this;
        }

        public ArgumentsBuilder v(String value){

            this.params.put("v", value);

            return this;
        }

        public ArgumentsBuilder n(String value){

            this.params.put("n", value);

            return this;
        }
        @Override
        public Object build() {

            StringBuilder argsStringBuilder = new StringBuilder();

            int args_counter = 0;

            for (Map.Entry entry : this.params.entrySet()){

                args_counter += 1;

                if (args_counter != this.params.size()){

                    String key_value = entry.getKey().toString() + "=" + entry.getValue().toString();

                    argsStringBuilder.append(key_value);
                    argsStringBuilder.append( "&");

                }else {

                    String key_value = entry.getKey().toString() + "=" + entry.getValue().toString();

                    argsStringBuilder.append(key_value);
                }

                this.arguments = argsStringBuilder.toString();
            }
            return new Wit(this);
        }
    }

    public static class RequestBuilder implements ApiRequest {

        private JSONObject requestObject;

        private TreeMap<Integer, MediaType> mediaTypeTreeMap;

        private HashMap<String, String> headers;

        private MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        private MediaType XML
                = MediaType.parse("application/xml; charset=utf-8");

        private String url;

        private String response_string;

        private OkHttpClient client;

        RequestBuilder(){

            requestObject = new JSONObject();

            mediaTypeTreeMap = new TreeMap<Integer, MediaType>();

            headers = new HashMap<String, String>();

            client = new OkHttpClient();
        }

        public RequestBuilder authorization(String bearer_token){

            try {

                this.headers.put("Authorization", "Bearer " + bearer_token);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestBuilder json(){

            try {

                this.mediaTypeTreeMap.put(1, JSON);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestBuilder xml(){

            try {

                this.mediaTypeTreeMap.put(1, XML);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return this;
        }


        public RequestBuilder content_type(String type){

            try {
                this.headers.put("Content-Type", type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestBuilder url(String url, String args){

            this.url = url;

            if (args != null){

                this.url += args;
            }

            return this;
        }

        public RequestBuilder request_body(String json){

            try {
                this.requestObject.put("request_body", json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }
        @Override
        public Object request() {

            RequestBody body = null;
             try {

                 if (this.requestObject.has("request_body")) {

                     body = RequestBody.create(this.mediaTypeTreeMap.firstEntry().getValue(), this.requestObject.getString("request_body"));

                 }

             }catch (Exception e){

                e.printStackTrace();
             }

            Request.Builder request_builder = new Request.Builder();

            try {

                request_builder.url(url);

            }catch(NullPointerException e){

                e.printStackTrace();
            }

            for (Map.Entry entry : this.headers.entrySet()){

                request_builder.header(entry.getKey().toString(), entry.getValue().toString());
            }

            if (body != null){

                request_builder.post(body);
            }


            Request request = request_builder.build();

            Response response = null;

            try {

                response = client.newCall(request).execute();

            } catch (IOException e) {

                e.printStackTrace();
            }

            try {

                this.response_string = response.body().string();

            } catch (Exception e) {

                e.printStackTrace();
            }

            return new Wit(this);
        }
    }
    public static class EndpointsBuilder implements wit.Builder<Object> {

        TreeMap<Integer, String> urlMap;

        String url = null;

        public EndpointsBuilder (){

            urlMap = new TreeMap<Integer, String>();
        }

        public EndpointsBuilder message(){

            this.urlMap.put(1, "message");
            return this;
        }

        public EndpointsBuilder speech(){
            this.urlMap.put(1, "speech");
            return this;
        }

        public EndpointsBuilder entities(){

            this.urlMap.put(1, "entities");
            return this;
        }

        public EndpointsBuilder export(){

            this.urlMap.put(1, "export");
            return this;
        }

        public EndpointsBuilder samples(){

            this.urlMap.put(1, "samples");
            return this;
        }


        public EndpointsBuilder entity_id(String id){

            this.urlMap.put(2, id);
            return this;
        }

        public EndpointsBuilder values(){

            this.urlMap.put(3, "values");
            return this;
        }

        public EndpointsBuilder expressions(){

            this.urlMap.put(5, "expressions");
            return this;
        }

        public EndpointsBuilder value_id(String value_id){

            this.urlMap.put(4, value_id);
            return this;
        }

        public EndpointsBuilder apps(){

            this.urlMap.put(1, "apps");
            return this;
        }

        @Override
        public Object build() {

            Set urlMapSet = this.urlMap.entrySet();

            StringBuilder urlBuilder = new StringBuilder();

            urlBuilder.append(Base.wit_base_url);

            Iterator iterator = urlMapSet.iterator();

            while (iterator.hasNext()){

                Map.Entry urlMapEntry = (Map.Entry) iterator.next();

                urlBuilder.append("/");
                urlBuilder.append(urlMapEntry.getValue());

            }

            this.url = urlBuilder.toString();

            return new Wit(this);
        }
    }

    private Wit(EndpointsBuilder builder){
        try {

            this.url = builder.url;
        }catch (NullPointerException e){
            Log.d("Wit.java Error", e.getLocalizedMessage());
        }

    }

    private Wit(ArgumentsBuilder argumentsBuilder){

        try{

            this.arguments = argumentsBuilder.arguments;

        }catch (NullPointerException e){
            Log.d("Wit.java Error", e.getLocalizedMessage());
        }

    }

    private Wit(RequestBodyBuilder requestBodyBuilder){

        try{

            this.request_body = requestBodyBuilder.request_body;

        }catch (NullPointerException e){
            Log.d("Wit.java Error", e.getLocalizedMessage());
        }

    }

    private Wit(RequestBuilder requestBuilder){

        try{
            Log.v("RequestBuilder", "Success" + requestBuilder.requestObject.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
