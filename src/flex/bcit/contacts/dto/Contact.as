package flex.bcit.contacts.dto {

/**
 * <p>A data transfer object used to send and receive data to and from the
 * server respectively.</p>
 */
[RemoteClass(alias="bcit.contacts.Contact")]
public class Contact {

    public function Contact() {
        id = -1;
        lastName = "";
        firstName = "";
        emailAddress = "";
        phoneNumber = "";
    }

    public var id:Number;

    public var lastName:String;

    public var firstName:String;

    public var emailAddress:String;

    public var phoneNumber:String;

    /**
     * <p>The string representation of an object based on this class.</p>
     * @return the string value of a <code>Contact</code> object.
     */
    public function toString():String {
        return id + ", " + firstName + " " + lastName + " " + emailAddress
            + " " + phoneNumber;
    }

}
}
