/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Form;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.util.Base64;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Map;
import java.util.Random;

/**AC7521a7a71e65fe2c1e627e7b52ae1429
 *a8bb5b687e2b35d13233a850b310286b
 * @author ezzedine
 */
public class SMSService extends Form {
    public SMSService(String jsonText){
//    public static final String ACCOUNT_SID =
//            "AC7521a7a71e65fe2c1e627e7b52ae1429";
//    public static final String AUTH_TOKEN =
//            "a8bb5b687e2b35d13233a850b310286b";
//    public static void Sms(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(
//                    new PhoneNumber("+21695592018"),
//                    new PhoneNumber("+12513131808"),
//                    "L'ajout du circuit est avec succès")
//                .create();
//        
////                .creator(new PhoneNumber("+21695592018"), // to
////                        new PhoneNumber("+12513131808"), // from
////                        "L'ajout du circuit est avec succès")
////                .create();
//
//        System.out.println(message.getSid());
//    }

   String accountSID = "AC7521a7a71e65fe2c1e627e7b52ae1429";
String authToken = "a8bb5b687e2b35d13233a850b310286b";
String fromPhone = "+12513131808";
String to = "+21695592018";
Random r = new Random();
String val = "hiii" + r.nextInt(10000);
//while (val.length() < 4) {
//    val = "0" + val;
//       return null;
//}

Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", to).
        queryParam("From", fromPhone).
        queryParam("Body", val).
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
        


}
}