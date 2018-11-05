package wit;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import url.Base;

public class Wit{

    public String url;
    public class Builder implements wit.Builder<Object> {

        TreeMap urlMap = null;
        String url = null;
        public Builder (){

            urlMap = new TreeMap<Integer, String>();
        }

        public Builder message(){

            this.urlMap.put(1, "message");
            return this;
        }

        public Builder speech(){
            this.urlMap.put(1, "speech");
            return this;
        }

        public Builder entities(){

            this.urlMap.put(1, "entities");
            return this;
        }

        public Builder entity_id(String id){
            
            this.urlMap.put(2, id);
            return this;
        }

        public Builder values(){

            this.urlMap.put(3, "values");
            return this;
        }

        public Builder expressions(){

            this.urlMap.put(5, "expressions");
            return this;
        }

        public Builder value_id(String value_id){

            this.urlMap.put(4, value_id);
            return this;
        }

        public Builder apps(){

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
                urlBuilder.append(urlMapEntry.getValue());
            }
            this.url = urlBuilder.toString();
            return new Wit(this);
        }
    }

    private Wit(Builder builder){
        try {

            this.url = builder.url;
        }catch (NullPointerException e){
            Log.d("Wit.java Error", e.getLocalizedMessage());
        }

    }


}
