🛍️ Mini E-Commerce System (JavaFX + Oracle)

A complete JavaFX-based mini e-commerce management system connected to an Oracle database.
This project follows a clean and layered architecture including entity, repository, service, and tools classes — perfect for practicing enterprise-level Java desktop development.


---

🚀 Features

✅ Customer Management – Add, edit, delete, and view customers.
✅ Product Management – Manage products with full CRUD operations.
✅ Order & Order Items – Create orders, manage order details, and track customers’ purchases.
✅ Payment Handling – Manage customer payments and receipts.
✅ Multi-Window Interface – Separate FXML screens for each section with dedicated controllers.
✅ Oracle Database Integration – Real-world database connection with ready SQL scripts.


---

🧩 Project Structure

MiniECommerce/
├── src/
│   └── main/
│       ├── java/
│       │   └── mftplus/
│       │       ├── controller/           # JavaFX controllers (MainMenu, Customer, Product, etc.)
│       │       ├── model/
│       │       │   ├── entity/           # JPA entities (Customer, Product, Order, etc.)
│       │       │   ├── repository/       # Data access layer (Oracle queries)
│       │       │   ├── service/          # Business logic
│       │       │   └── tools/            # Utility classes (DB connection, etc.)
│       │       └── MainApp.java          # Application entry point
│       └── resources/
│           ├── view/                     # FXML UI files
│           │   ├── MainMenuView.fxml
│           │   ├── CustomerView.fxml
│           │   ├── ProductView.fxml
│           │   ├── OrderView.fxml
│           │   ├── OrderItemView.fxml
│           │   └── PaymentView.fxml
│           └── DatabaseCreator.sql       # Oracle DB schema and sample data
├── pom.xml
└── README.md


---

⚙️ Technologies Used

Java 8+

JavaFX

Maven

FXML

Oracle Database

JDBC / DAO pattern



---

🖥️ How to Run

1. Clone the repository:

git clone https://github.com/yourusername/MiniECommerce.git


2. Open the project in IntelliJ IDEA (or another Maven IDE).


3. Configure your Oracle DB connection in tools/DBConnection.java.


4. Run the SQL script resources/DatabaseCreator.sql to create tables.


5. Execute MainApp.java to start the program.




---

🗄️ Database Setup

Example Oracle connection (in DBConnection.java):

String url = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "system";
String password = "your_password";
Connection connection = DriverManager.getConnection(url, user, password);


---

👨‍💻 Author

arian Sadat
Passionate about Java desktop development and building modular, maintainable applications.


---

🪪 License

This project is released under the MIT License — free for educational and personal use.


---