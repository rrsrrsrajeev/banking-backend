# Database Setup Guide (Local Development)

This document provides instructions on how to set up, connect to, and manage the local MySQL database for the banking backend using Docker.

---

## 🛠️ Database Connection Details

Use the following credentials to connect to your local MySQL database. **This is for your local development reference.**

| Property | Value |
| :--- | :--- |
| **Host** | `localhost` or `127.0.0.1` |
| **Port** | `3306` |
| **Database Name** | `banking_db` |
| **Username** | `banking_user` |
| **Password** | `banking_password` |
| **Root Password** | `root` |

> [!NOTE]
> These credentials match the configurations in [docker-compose.yml](file:///Users/rajeevranjan/Desktop/javaProject/banking-backend/docker-compose.yml) and [application.properties](file:///Users/rajeevranjan/Desktop/javaProject/banking-backend/src/main/resources/application.properties).

---

## 🚀 Running the Database with Docker

### 1. Start the Database Container
Run this command from the root directory of your project:
```bash
docker-compose up -d
```
*The `-d` flag runs the container in the background (detached mode).*

### 2. Verify Container Status
Check if the MySQL container is running successfully:
```bash
docker ps
```
You should see a container named `banking-mysql` running on port `3306`.

### 3. Check Logs (Troubleshooting)
If you face issues starting the database, inspect the container logs:
```bash
docker logs banking-mysql
```

### 4. Stop the Database
To stop and remove the container (retaining data stored in volumes):
```bash
docker-compose down
```

---

## 🔌 Connecting to the Database

### Method A: Connect via command-line (Docker Exec)
You can access the MySQL client inside the Docker container using this command:
```bash
docker exec -it banking-mysql mysql -u banking_user -p
```
*When prompted, enter your password:* **`banking_password`**

Or log in as root user:
```bash
docker exec -it banking-mysql mysql -u root -p
```
*When prompted, enter the root password:* **`root`**

### Method B: Using Local Database GUIs (DBeaver, DataGrip, VS Code Database Client)
1. Open your database tool and create a new **MySQL** connection.
2. Enter the host, port, database name, username, and password from the table above.
3. Test connection. Ensure your Docker container is running beforehand.

---

## 🔒 Changing Database Credentials (Optional)
If you want to update the database password or username, update it in these two files:

1. **[docker-compose.yml](file:///Users/rajeevranjan/Desktop/javaProject/banking-backend/docker-compose.yml)**:
   ```yaml
   environment:
     MYSQL_USER: new_username
     MYSQL_PASSWORD: new_password
   ```
2. **[application.properties](file:///Users/rajeevranjan/Desktop/javaProject/banking-backend/src/main/resources/application.properties)**:
   ```properties
   spring.datasource.username=new_username
   spring.datasource.password=new_password
   ```
3. After changing credentials in `docker-compose.yml`, recreate the container for changes to take effect:
   ```bash
   docker-compose down
   docker-compose up -d
   ```
