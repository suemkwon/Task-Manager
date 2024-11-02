# Task Manager API

A simple RESTful API for managing tasks built with Clojure and Compojure. This service provides basic CRUD operations for tasks with an in-memory storage solution.

## Prerequisites

Before you begin, ensure you have installed:

- Clojure
- Leiningen

## Installation

Clone the repository:

```bash
git clone https://github.com/YOUR-USERNAME/YOUR-REPO-NAME.git
cd task-manager
```

Install dependencies:

```bash
lein deps
```

## Running the Server
Start the server on port 3000:

```bash
lein run
```

The server will be available at http://localhost:3000.

## API Endpoints

### Get All Tasks
```bash
GET /tasks
curl http://localhost:3000/tasks
```

### Get Task by ID
```bash
GET /tasks/:id
curl http://localhost:3000/tasks/1
```

### Create Task
```bash
POST /tasks
Content-Type: application/json
curl -X POST -H "Content-Type: application/json" \
     -d '{"title":"New Task","description":"Task description"}' \
     http://localhost:3000/tasks
```

### Update Task
```bash
PUT /tasks/:id
Content-Type: application/json
curl -X PUT -H "Content-Type: application/json" \
     -d '{"title":"Updated Task","description":"Updated description"}' \
     http://localhost:3000/tasks/1
```

### Delete Task
```bash
DELETE /tasks/:id
curl -X DELETE http://localhost:3000/tasks/1
```

### Task Structure
Tasks have the following structure:

```json
{
  "id": 1,
  "title": "Task Title",
  "description": "Task Description"
}
```

### Development
To start a development environment with automatic code reloading:

```bash
lein ring server
```

### Project Structure
```bash
task-manager/
├── project.clj           # Project configuration and dependencies
├── README.md             # This file
└── src/
    └── task_manager/
        └── core.clj     # Main application logic and routes
```

### Dependencies
Compojure (1.7.0) - Routing library
Ring (0.3.4) - Web application library
Ring-json (0.5.1) - JSON request/response handling
Clojure data.json (2.4.0) - JSON parsing

### Storage
This implementation uses in-memory storage with atoms. Data will be lost when the server is restarted. For persistence, consider implementing a database solution.

### Contributing
Fork the repository
Create your feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request
