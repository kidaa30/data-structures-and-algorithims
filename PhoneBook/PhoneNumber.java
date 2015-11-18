import java.util.Iterator;
import data_structures.*;

public class PhoneNumber implements Comparable<PhoneNumber> {
    String areaCode, prefix, number, phoneNumber;
   
    /* Constructor.  Creates a new PhoneNumber instance.  The parameter
    is a phone number in the form xxx-xxx-xxxx, which is area code -
    prefix - number.  The phone number must be validated, and an
    IllegalArgumentException thrown if it is invalid. */
    public PhoneNumber(String n){
    };
    
    // Follows the specifications of the Comparable Interface.  
    public int compareTo(PhoneNumber n){
    };
       
    // Returns an int representing the hashCode of the PhoneNumber.
    public int hashCode(){
    };
   
    // Private method to validate the Phone Number.  Should be called
    // from the constructor.   
    private void verify(String n){
    };
       
    // Returns the area code of the Phone Number.
    public String getAreaCode(){
    };
       
    // Returns the prefix of the Phone Number.
    public String getPrefix(){
    }
       
    // Returns the the last four digits of the number.
    public String getNumber(){
    }

    // Returns the Phone Number.       
    public String toString(){
    };

    /* Makes sure phone number is in the format: xxx-xxx-xxxx /area-code/prefix/number/ */
    public boolean validateNumber(String number){
    };

}
