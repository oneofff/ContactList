package dao.implementations;

import com.contacts.dao.DatabaseConnection;
import com.contacts.dao.DatabaseException;
import com.contacts.entity.Contact;
import com.contacts.entity.enums.Gender;
import com.contacts.entity.enums.MaritalStatus;
import dao.interfaces.IContactDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao implements IContactDao {
    private static final Logger log = LogManager.getLogger(ContactDao.class);
    private final Connection connection = DatabaseConnection.getConnection();
    private final String insert = "INSERT INTO public.contacts(name, surname, patronymic, birth_date, gender, citizenship, email, company, website, country, city, address, post_index) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)RETURNING id;";
    private final String select = "SELECT id, name, surname, patronymic, birth_date, gender, citizenship, marital_status, email, company, website, country, city, address, post_index FROM public.contacts;";
    private final String update = "UPDATE public.contacts SET id=?, name=?, surname=?, patronymic=?, birth_date=?, gender=?, citizenship=?, email=?, company=?, website=?, country=?, city=?, address=?, post_index=?, marital_status=? WHERE id=?;";
    private final String delete = "DELETE FROM public.contacts WHERE id=?;";
    private final String selectById = "SELECT id, name, surname, patronymic, birth_date, gender, citizenship, marital_status, email, company, website, country, city, address, post_index FROM public.contacts WHERE id=?;";


    public int insertContact(Contact contact) {
        log.trace("Add contact to db " + contact);
        try (var statement = connection.prepareStatement(insert)) {
            setContactToStatement(contact, statement);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            resultSet.close();
            log.info("Contact added " + contact);
            return id;
        } catch (SQLException | RuntimeException ex) {
            log.error("Failed add contact to db " + contact);
            throw new DatabaseException("Failed add contact to db", ex);
        }
    }

    public List<Contact> getContactList() {
        log.trace("Select Contacts form db");
        List<Contact> contactList = new ArrayList<>();
        try (var statement = connection.prepareStatement(select)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contactList.add(getContactFromResult(resultSet));
            }
            resultSet.close();
            log.info("Contacts success selected ");
            return contactList;
        } catch (SQLException | RuntimeException ex) {
            log.error("Failed select contacts from db ");
            throw new DatabaseException("Failed select contacts from db", ex);
        }
    }

    private Contact getContactFromResult(ResultSet resultSet) {
        try {
            return Contact.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .patronymic(resultSet.getString("patronymic"))
                    .birthDate(resultSet.getDate("birth_date"))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .citizenship(resultSet.getString("citizenship"))
                    .maritalStatus(MaritalStatus.valueOf(resultSet.getString("marital_status")))
                    .email(resultSet.getString("email"))
                    .company(resultSet.getString("company"))
                    .website(resultSet.getString("website"))
                    .country(resultSet.getString("country"))
                    .city(resultSet.getString("city"))
                    .address(resultSet.getString("address"))
                    .postIndex(resultSet.getString("post_index"))
                    .build();
        } catch (IllegalArgumentException | SQLException ex) {
            throw new DatabaseException("Failed convert contact from data", ex);
        }
    }

    public void updateContact(Contact contact) {
        log.trace("Update contact on db " + contact);
        try (var statement = connection.prepareStatement(update)) {
            setContactToStatement(contact, statement);
            statement.setInt(13, contact.getId());
            statement.executeUpdate();
            log.info("Contact updated " + contact);
        } catch (SQLException | RuntimeException ex) {
            log.error("Failed update contact on db " + contact);
            throw new DatabaseException("Failed update contact on db", ex);
        }
    }

    public void deleteContact(Contact contact) {
        log.trace("Delete contact on db " + contact);
        try (var statement = connection.prepareStatement(delete)) {
            statement.setInt(1, contact.getId());
            statement.executeUpdate();
            log.info("Contact deleted " + contact);
        } catch (SQLException | RuntimeException ex) {
            log.error("Failed delete contact from db " + contact);
            throw new DatabaseException("Failed delete contact from db", ex);
        }
    }

    public Contact getContactById(int id) {
        log.trace("Get contact by id from db " + id);
        try (var statement = connection.prepareStatement(selectById)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Contact contact = getContactFromResult(resultSet);
            resultSet.close();
            log.info("Contact by id" + contact);
            return contact;
        } catch (SQLException | RuntimeException ex) {
            log.error("Failed delete contact from db ");
            throw new DatabaseException("Failed delete contact from db", ex);
        }
    }

    private void setContactToStatement(Contact contact, PreparedStatement statement) {
        try {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getSurname());
            statement.setString(3, contact.getPatronymic());
            statement.setDate(4, contact.getBirthDate());
            statement.setString(5, contact.getGender().toString());
            statement.setString(6, contact.getCitizenship());
            statement.setString(7, contact.getEmail());
            statement.setString(8, contact.getCompany());
            statement.setString(9, contact.getWebsite());
            statement.setString(10, contact.getCountry());
            statement.setString(11, contact.getAddress());
            statement.setString(12, contact.getPostIndex());
        } catch (SQLException ex) {
            throw new DatabaseException("Failed set contact to db", ex);
        }
    }
}
