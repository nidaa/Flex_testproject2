package java.bcit.contacts;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * <p>A simple example demonstrating how to interface JEE using JPA/Hibernate
 *   with Flex (i.e. MXML, ActionScript) to produce a Create, Read, Update,
 *   Delete (CRUD) application.</p>
 *  <p>References:</p>
 *  <ul>
 *    <li><a href="https://www.hibernate.org/42.html">Sessions and Transactions</a></li>
 *    <li><a href="http://javanotepad.blogspot.com/2007/08/managing-jpa-entitymanager-lifecycle.html">
 *      Managing JPA EntityManager lifecycle</a></li>
 *    <li><a href="http://javanotepad.blogspot.com/2007/06/how-to-close-jpa-entitymanger-in-web.html">
 *      How to close a JPA EntityManger in web applications</a></li>
 *    <li><a href="http://docs.jboss.org/hibernate/stable/entitymanager/reference/en/html_single/">
 *     Hibernate EntityManager</a></li>
 *  </ul>
 *
 * @author Arron Ferguson
 * @version 1.0
 */
public class ContactsService {

    private static Logger logger = Logger.getLogger(ContactsService.class);

    private static final String PERSISTENCE_UNIT = "contacts";

    private static EntityManagerFactory emf = null;

    static {
        logger.info("LOADING CONTACTSSERVICE CLASS.");
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public ContactsService() {
        super();
    }

    /**
     * <p>Adds a <code>Contact</code> to the list. If the <code>Contact</code>
     *   already exists, no action is taken.</p>
     * @param c the <code>Contact</code> to add.
     */
    public void addContact(Contact c) {
        if(c == null) {
            return;
        }

        EntityManager em = emf.createEntityManager();
        logger.info("PERSISTENCE ENTITYMANAGER ACQUIRED.");

        logger.info("ABOUT TO ADD CONTACT: fName: " + c.getFirstName()
            + ", lName: " + c.getLastName() + ", email:" + c.getEmailAddress()
            + ", phone: " + c.getPhoneNumber());
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contact c2 = em.find(java.bcit.contacts.Contact.class, new Long(122));
            c2.setFirstName("THUNDERSTRUCK");
            em.flush();
            em.merge(c);
            tx.commit();
        } catch (Exception e) {
            logger.error("CONTACT APP PERSISTING ERROR: " + e.getMessage());
            tx.rollback();
        } finally {
            logger.info("CONTACT APP CLOSING ENTITY MANAGER.");
            em.close();
        }
    }

    /**
     * <p>Edits a <code>Contact</code> that already exists within the list. If
     *   the <code>Contact</code> does not exist, the <code>Contact</code> is
     *   added to the list.</p>
     * @param c the <code>Contact</code> to edit.
     */
    public void editContact(Contact c) {
        logger.info("CONTACT TO UPDATE: " + c);
        addContact(c);
    }

    /**
     * <p>Deletes a <code>Contact</code> that already exists within the list. If
     *   the <code>Contact</code> does not exist (based on the id), no action
     *   is taken.</p>
     * @param id the id to used to delete a <code>Contact</code>.
     */
    public void deleteContact(Long id) {
        logger.info("ABOUT TO DELETE CONTACT");

        EntityManager em = emf.createEntityManager();
        logger.info("PERSISTENCE ENTITYMANAGER ACQUIRED.");

        Query contactByIdQuery = em.createNamedQuery("contact.getById");
        contactByIdQuery.setParameter("id", id);
        Contact c = (Contact) contactByIdQuery.getSingleResult();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(c);
            tx.commit();

        } catch (Exception e) {
            logger.error("CONTACT APP PERSISTING ERROR: " + e.getMessage());
            tx.rollback();
        } finally {
            logger.info("CONTACT APP CLOSING ENTITY MANAGER.");
            em.close();
        }
    }

    /**
     * <p>Returns a list of <code>Contact</code> objects. If there are no
     * contacts in the datastore, an empty list is returned.</p>
     * @return the list of <code>Contact</code>s.
     */
    public List<Contact> getContacts() {
        logger.info("ABOUT TO RETRIEVE CONTACTS");
        logger.info("AND THIS INSTANCE IS: " + this + " thread: "
            + Thread.currentThread());

        EntityManager em = emf.createEntityManager();
        logger.info("PERSISTENCE ENTITYMANAGER ACQUIRED.");

        Query findAllContactsQuery =
            em.createNamedQuery("contact.findAll");
        List<Contact> contacts = findAllContactsQuery.getResultList();

        if (contacts != null) {
            logger.debug("CONTACT APP RETRIEVED: " + contacts.size()
                + " CONTACT(S)");
        }
        return contacts;
    }
}
