# task-manager-backend

This repository contains the backend code for a task manager application.

## How to run

### 1. Clone the repository to your local machine.

```bash
https://github.com/BirtErik/task-manager-backend.git
```

### 2. Docker

This project uses Docker for containerize of PostgreSQL database.
Ensure you have Docker installed on your machine.

Navigate to `docker`directory:

```bash
cd task-manager-backend/docker
```

Now, execute the following command to start the PostgreSQL container:

```bash
docker-compose up
```

### 3. Build the App

In the command line, upon running the application, an admin user will be automatically created and inserted into the
database with the following credentials:

- Email: `admin@admin.com`
- Password: `password`

## Authorization

Upon successful login, a JWT token will be returned.
If you are using Postman to interact with the backend,
copy the JWT token and paste it into the Authorization header. Select the 'Bearer Token' option.