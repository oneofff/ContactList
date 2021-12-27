package com.contacts.dao.implementations;

import com.contacts.dao.DatabaseConnection;

import java.sql.Connection;

public class AttachmentDao {
    Connection connection = DatabaseConnection.getConnection();
}
