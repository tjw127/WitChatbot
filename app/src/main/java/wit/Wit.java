package wit;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import url.Base;
import wit.chatbot_api_request.ApiRequest;

public class Wit{

    public String url;
    public String arguments;

    public static class JSONArgumentsBuilder implements wit.Builder<Object> {

        JSONObject jsonObject;

        JSONArgumentsBuilder()
        {
            jsonObject = new JSONObject();
        }

        public JSONArgumentsBuilder id(String id){
            try {
                this.jsonObject.put("id", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder doc(String doc){
            try {
                this.jsonObject.put("doc", doc);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder lookups(String array){
            try {
                this.jsonObject.put("lookups", array);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder values(String values){
            try {
                this.jsonObject.put("values", values);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder expression(String expression){
            try {
                this.jsonObject.put("expression", expression);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder expressions(String array){
            try {
                this.jsonObject.put("expressions", array);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder metadata(String data){
            try {
                this.jsonObject.put("metadata", data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder text(String text){
            try {
                this.jsonObject.put("text", text);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder entities(String array){
            try {
                this.jsonObject.put("entities", array);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder name(String name){
            try {
                this.jsonObject.put("name", name);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder lang(String lang){
            try {
                this.jsonObject.put("lang", lang);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder private_arg(String value){
            try {
                this.jsonObject.put("private", value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONArgumentsBuilder desc(String value){
            try {
                this.jsonObject.put("desc", value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }


        @Override
        public Object build() {
            return null;
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
        RequestBuilder(){

            requestObject = new JSONObject();
        }

        public RequestBuilder authorization(String bearer_token){

            try {
                this.requestObject.put("Authorization", " Bearer " + bearer_token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestBuilder content_type(String type){

            try {
                this.requestObject.put("Content-Type", type);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestBuilder json_arguments(String json){

            try {
                this.requestObject.put("arguments", json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }
        @Override
        public Object request() {
            return null;
        }
    }
    public static class EndpointsBuilder implements wit.Builder<Object> {

        TreeMap urlMap = null;
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

            int param_counter = 0;

            while (iterator.hasNext()){

                param_counter += 1;

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


}
