package java.bcit.contacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <p>References:</p>
 * <ul>
 *   <li><a href="http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/#entity-mapping-query">
 *     Mapping Queries</a></li>
 *   <li><a href="http://www.infoq.com/news/2007/02/hibernate-annotations">
 *     Configuring Hibernate with Annotations</a></li>
 *   <li><a href="http://docs.jboss.org/hibernate/core/3.3/reference/en/html/queryhql.html">
 *     Chapter 14. HQL: The Hibernate Query Language</a></li>
 * </ul>
 *
 * @author Arron Ferguson
 */
@Entity
@Table(name="contact")
@NamedQueries( {
    @NamedQuery(name = "contact.findAll", query = "from Contact"),
    @NamedQuery(name = "contact.getById", query =
        "select c from Contact c where c.id = :id")
} )
public class Contact {

    private static final long serialVersionUID = 123456789L;

    public Contact() {
        firstName = "N/A";
        lastName = "N/A";
        emailAddress = "N/A";
        phoneNumber = "N/A";
    }

    public Contact(String first, String last, String email, String number) {
        firstName = first;
        lastName = last;
        emailAddress = email;
        phoneNumber = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable=false)
    private long id;

    @Column(name = "lastName", nullable = false, unique = false)
    private String lastName;

    @Column(name = "firstName", nullable = false, unique = false)
    private String firstName;

    @Column(name = "emailAddress", nullable = false, unique = false)
    private String emailAddress;

    @Column(name = "phoneNumber", nullable = false, unique = false)
    private String phoneNumber;

    /**
     *
     * @param number set the contact's phone number.
     */
    public void setPhoneNumber(String number) {
        phoneNumber = number;
    }

    /**
     *
     * @return the contact's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @return the contact's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param address set the contact's email address.
     */
    public void setEmailAddress(String address) {
        emailAddress = address;
    }

    /**
     *
     * @return the contact's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param first set the contact's first name.
     */
    public void setFirstName(String first) {
        firstName = first;
    }

    /**
     *
     * @return the contact's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param last set the contact's last name.
     */
    public void setLastName(String last) {
        lastName = last;
    }

    /**
     *
     * @return the contact's id.
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param newId set the contact's id.
     */
    public void setId(long newId) {
        id = newId;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + emailAddress
            + " " + phoneNumber;
    }

}
