/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACc2f893ae62a40b5c8386ff775df3013b";
    public static final String AUTH_TOKEN =
            "ebbd8746b2373469b66175dcaad900ae";
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21695592018"), // to
                        new PhoneNumber("+18564153325"), // from
                        "L'ajout du circuit est avec succès")
                .create();

        System.out.println(message.getSid());
    }
    public static void main() {
        
    }
}
