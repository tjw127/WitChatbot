package wit;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import url.Base;

public class Wit{

    public String url;
    public String arguments;

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


                if (urlMapSet.size() != param_counter || urlMapSet.size() == 1){

                    urlBuilder.append("/");
                    urlBuilder.append(urlMapEntry.getValue());

                } else {

                    urlBuilder.append(urlMapEntry.getValue());
                }

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
