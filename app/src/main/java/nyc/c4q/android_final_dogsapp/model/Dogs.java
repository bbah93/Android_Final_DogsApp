package nyc.c4q.android_final_dogsapp.model;

import java.util.ArrayList;

/**
 * Created by bobbybah on 2/25/18.
 */

public class Dogs {
    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    public class breedList {

        private String status;

        private ArrayList<String> message;

        public String getStatus() {
            return status;
        }

        public ArrayList<String> getMessage() {
            return message;
        }

    }


}
