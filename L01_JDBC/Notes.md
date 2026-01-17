## Title:

Pure JDBC Student CRUD Project Structure

### Summary:

This project is a Pure JDBC application.
It performs Create, Read, Update, and Delete operations.
Student and Address data are stored in two related tables.
This project helps us understand JDBC clearly before learning Hibernate.

### Learning:

This project is divided into clear packages.
Each package has a specific responsibility.

The `config` package handles database connection details.
It contains database URL, username, password, and driver.
`JDBCConnection` creates and returns a database connection.

The `model` package contains plain Java classes.
`Student` and `Address` represent database tables.
These classes only store data and have getters and setters.

The `repo` package contains database logic.
Each CRUD operation has a separate class.
Insert, Read, Update, and Delete are written using JDBC.
SQL queries are written manually using PreparedStatement.

The `resource` folder contains SQL files.
It is used to create and drop database tables.

`MainApp` is used to test all operations.
It calls repository methods and prints output.

### Traditional JDBC Learning:

In traditional JDBC, everything is done step by step.
First, database properties are defined.
Next, the JDBC driver is loaded.
Then a database connection is created.
SQL queries are written manually.
PreparedStatement is used to execute queries.
ResultSet is used to read data from SELECT queries.
Transactions are handled using commit and rollback.
Finally, all connections are closed.

This method helps us clearly see how Java talks to the database.

---

### Traditional JDBC Teaching (Code Based):

#### Step 1: Define Database Properties

First, database details are stored in one place.

```java
public abstract class AppProperties {
    protected static final String url = "YOUR_JDBC_URL";
    protected static final String user = "YOUR_JDBC_USERNAME";
    protected static final String password = "YOUR_JDBC_PASSWORD";
    protected static final String driver = "DRIVER_NAME";
}
```

This helps us avoid repeating database details.

---

#### Step 2: Create JDBC Connection

Next, we create a class to get database connection.

```java
public class JDBCConnection extends AppProperties {

    public static Connection connection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

This code shows how Java connects to the database.

---

#### Step 3: Create Model Classes

Model classes represent database tables.

```java
public class Student {
    private int studentId;
    private String studentName;
    private String standard;
    private String section;
    private Address studentAddress;
}
```

Model classes only store data.

---

#### Step 4: INSERT Data Using JDBC

First, parent data (Address) is inserted.
Then, child data (Student) is inserted.

```java
conn.setAutoCommit(false);

PreparedStatement psAddress = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
psAddress.executeUpdate();

ResultSet rs = psAddress.getGeneratedKeys();
int addressId = rs.getInt(1);

PreparedStatement psStudent = conn.prepareStatement(studentSql);
psStudent.executeUpdate();

conn.commit();
```

This teaches:

* PreparedStatement
* Generated keys
* Transactions

---

#### Step 5: READ Data Using JDBC

Data is read using SELECT and JOIN.

```java
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    student.setStudentName(rs.getString("name"));
}
```

This teaches:

* SELECT query
* JOIN usage
* ResultSet reading

---

#### Step 6: UPDATE Data Using JDBC

```java
UPDATE student SET name=?, std=?, section=? WHERE id=?
```

PreparedStatement is used to update safely.
Transaction ensures data consistency.

---

#### Step 7: DELETE Data Using JDBC

```java
DELETE FROM student WHERE id=?
DELETE FROM address WHERE address_id=?
```

Child is deleted first.
Parent is deleted next.

---

#### Step 8: Transaction Handling

```java
conn.setAutoCommit(false);
conn.commit();
conn.rollback();
```

This teaches how JDBC handles failures safely.

---

### Pros:

* Clear understanding of database flow
* Full control over SQL queries
* Easy to debug database issues
* Strong foundation for Hibernate and JPA
* Good for learning core Java database concepts

### Cons:

* More code needs to be written
* Manual transaction handling required
* Code becomes lengthy for large projects
* Not suitable for complex applications

### Conclusion:

This project shows Pure JDBC in a traditional way.
It builds strong fundamentals of database programming.
After understanding this project, learning Hibernate becomes easy.
JDBC knowledge is the base for all Java ORM frameworks.

---

### Final traditional advice (important)

If you understand **this project fully**,
Hibernate will feel **easy, not magical**.
