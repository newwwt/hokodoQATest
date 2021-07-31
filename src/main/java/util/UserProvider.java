package util;

import java.util.HashMap;

public class UserProvider {

    public static HashMap<String,String> getIndividualUserWithValidDirectDebit() {
        HashMap<String,String> user = new HashMap<String, String>();
        user.put("firstName", "Automated");
        user.put("lastName", "Tester");
        user.put("sortCode", "40-47-84");
        user.put("accountNumber", "70872490");
        user.put("billingAddressLine1", "1 Commercial St");
        user.put("city", "London");
        user.put("postcode", "E1 7PT");
        user.put("email", "example@example.com");
        return user;
    }

    public static HashMap<String,String> getCompanyWithValidDirectDebit() {
        HashMap<String,String> user = new HashMap<String, String>();
        user.put("companyName", "Automated Test");
        user.put("sortCode", "40-47-84");
        user.put("accountNumber", "70872490");
        user.put("billingAddressLine1", "1 Commercial St");
        user.put("city", "London");
        user.put("postcode", "E1 7PT");
        user.put("email", "example@example.com");
        return user;
    }

}
