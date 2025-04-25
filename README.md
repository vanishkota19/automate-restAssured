# REST Assured Automation Framework

A comprehensive REST API testing framework built with REST Assured, TestNG, and Maven. This framework supports data-driven testing using CSV files and includes environment configuration management.

## Features

- **Data-Driven Testing**: Test data management using CSV files
- **Environment Configuration**: Support for multiple environments (QA, Staging, Production)
- **Authentication Handling**: Built-in support for authentication tokens
- **Request/Response Logging**: Detailed logging of API requests and responses
- **Test Reports**: TestNG reports with detailed test execution information
- **Common Headers Management**: Centralized management of common HTTP headers
- **CSV Data Reader**: Utility for reading test data from CSV files
- **JSON Request/Response Handling**: Built-in support for JSON request/response handling

## Project Structure

```
automate-restAssured/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── restassured/
│   │   │           ├── config/
│   │   │           │   ├── ConfigManager.java
│   │   │           │   └── RequestSpecificationBuilder.java
│   │   │           └── utils/
│   │   │               └── TestDataReader.java
│   │   └── resources/
│   │       └── env.config
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── restassured/
│       │           └── tests/
│       │               └── UserAPITest.java
│       └── resources/
│           ├── testdata/
│           │   └── users.csv
│           └── testng.xml
├── pom.xml
└── README.md
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Git

## Dependencies

- REST Assured 5.3.0
- TestNG 7.7.1
- Jackson 2.15.2
- OpenCSV 5.7.1
- Extent Reports 5.0.9
- Lombok 1.18.30

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/vanishkota19/automate-restAssured.git
   cd automate-restAssured
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

3. Configure environment:
   - Edit `src/main/resources/env.config` to set your environment URLs and configurations
   - Update authentication details if required

## Running Tests

1. Run all tests:
   ```bash
   mvn clean test
   ```

2. Run tests for a specific environment:
   ```bash
   mvn clean test -Denv=qa
   ```

3. Run specific test class:
   ```bash
   mvn test -Dtest=UserAPITest
   ```

## Test Data Management

Test data is stored in CSV files under `src/test/resources/testdata/`. The framework includes a utility class `TestDataReader` to read and parse CSV data.

Example CSV format:
```csv
id,name,email,status
1,John Doe,john@example.com,active
2,Jane Smith,jane@example.com,active
3,Bob Wilson,bob@example.com,inactive
```

## Environment Configuration

Environment configurations are managed in `src/main/resources/env.config`:

```properties
# Environment URLs
qa.base.url=https://qa-api.example.com
staging.base.url=https://staging-api.example.com
prod.base.url=https://api.example.com

# Common Headers
Content-Type=application/json
Accept=application/json

# Authentication
auth.username=testuser
auth.password=testpass

# Timeouts
connection.timeout=5000
read.timeout=5000
```

## Adding New Tests

1. Create a new test class in `src/test/java/com/restassured/tests/`
2. Extend the base test class if needed
3. Add your test methods with appropriate annotations
4. Add test data to CSV files if required
5. Update `testng.xml` to include your new test class

Example test method:
```java
@Test(dataProvider = "userData")
public void testCreateUser(Map<String, String> userData) {
    Response response = given()
            .spec(RequestSpecificationBuilder.getDefaultRequestSpec())
            .body(userData)
            .when()
            .post("/users")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .extract()
            .response();
}
```

## Reports

Test execution reports are generated in the `target/surefire-reports` directory after test execution. The reports include:
- Test execution summary
- Detailed test results
- Test suite information
- Test method details

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

Vanish Kota - [@vanishkota19](https://github.com/vanishkota19)

Project Link: [https://github.com/vanishkota19/automate-restAssured](https://github.com/vanishkota19/automate-restAssured)