package jdbc.conection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connection {

    public static java.sql.Connection connectionSQL() throws SQLException {
        java.sql.Connection connection = null;
        try (FileInputStream fileInputStream = new FileInputStream("liquibase.properties");) {
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(Config.xml);
//            doc.getDocumentElement().normalize();
//            String URL = doc.getElementsByTagName("URL").item(0).getTextContent();
//            String USER_NAME = doc.getElementsByTagName("USER_NAME").item(0).getTextContent();
//            String PASSWORD = doc.getElementsByTagName("PASSWORD").item(0).getTextContent();

            Properties properties = new Properties();
            properties.load(fileInputStream);
            Class.forName("org.postgresql.Driver");
            String URL = properties.getProperty("url");
            String USER_NAME = properties.getProperty("username");
            String PASSWORD = properties.getProperty("password");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

}
