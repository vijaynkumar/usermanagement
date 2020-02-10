package com.kristal.user.management.common.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	
	public static String  generatePasword() {
		return generatePaasword(8);
	}
	
	public static String generatePaasword(int lenght) {
		int numericSalt = 99;
		String alphabetSalt = "A@v";
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"
                                    +"@#$%^&*()?><!"; 
  
        StringBuilder sb = new StringBuilder(lenght); 
  
        for (int i = 0; i < lenght; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString().concat(numericSalt+alphabetSalt); 
	}
}
