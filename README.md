# FoodFlow

<p align="center">
  <img src="https://github.com/user-attachments/assets/3f67a9e1-6633-4e2a-8730-6dac5439f2df" alt="LawConnect Admin Banner" width="80%"/>
</p>
A lightweight Ktor API serving delicious menu data

---

## Description

FoodFlow is a lightweight Ktor-based API that delivers menu and category data for food-service applications. It provides endpoints to list available categories, fetch full or filtered food items, and perform CRUD operations on menu entries. Built with Kotlin and kotlinx-serialization, FoodFlow is ideal for mobile or web apps needing a simple, scalable backend for restaurant menus.

---

## API Endpoints

### `GET /categories`
- **Description:** Retrieve a list of all food categories.  
- **Request Parameters:** _None_  
- **Response Example:**
  ```json
  [
    { "name": "Dinner" },
    { "name": "Lunch" },
    { "name": "Dessert" },
    { "name": "Drink" }
  ]


### `GET /menu`

* **Description:** Retrieve all food items, optionally filtered by category.
* **Query Parameters:**

    * `category` (string, optional) – filter items by category name (case‐insensitive)
* **Response Example (no filter):**

  ```json
  [
    {
      "id": 1,
      "name": "Rovioli",
      "description": "Piza Impermeable Bnina Bela3des wel lobya",
      "price": 2000.0,
      "category": "Dinner"
    },
    {
      "id": 2,
      "name": "Pizza",
      "description": "Impermeable Bnina Bela3des wel lobya",
      "price": 2000.0,
      "category": "Lunch"
    }
  ]
  ```
* **Response Example (with `?category=Lunch`):**

  ```json
  [
    {
      "id": 2,
      "name": "Pizza",
      "description": "Impermeable Bnina Bela3des wel lobya",
      "price": 2000.0,
      "category": "Lunch"
    }
  ]
  ```

### `GET /menu/{id}`

* **Description:** Retrieve a single food item by its ID.
* **Path Parameters:**

    * `id` (integer) – the unique identifier of the food item
* **Response Example (found):**

  ```json
  {
    "id": 1,
    "name": "Rovioli",
    "description": "Piza Impermeable Bnina Bela3des wel lobya",
    "price": 2000.0,
    "category": "Dinner"
  }
  ```
* **Response Codes:**

    * `200 OK` if found
    * `404 Not Found` if no item with that ID exists

### `POST /menu`

* **Description:** Create a new food item.
* **Request Body:** (JSON)

  ```json
  {
    "name": "Burger",
    "description": "Juicy beef burger with toppings",
    "price": 1500.0,
    "category": "Lunch"
  }
  ```
* **Response Example:**

  ```json
  {
    "id": 3,
    "name": "Burger",
    "description": "Juicy beef burger with toppings",
    "price": 1500.0,
    "category": "Lunch"
  }
  ```
* **Response Codes:**

    * `201 Created` with the newly created item
    * `400 Bad Request` if invalid JSON

### `PUT /menu/{id}`

* **Description:** Update an existing food item.
* **Path Parameters:**

    * `id` (integer) – the ID of the item to update
* **Request Body:** (JSON – full item payload)

  ```json
  {
    "name": "Veggie Pizza",
    "description": "Pizza with fresh vegetables",
    "price": 2100.0,
    "category": "Lunch"
  }
  ```
* **Response Codes:**

    * `200 OK` if update succeeded
    * `400 Bad Request` if invalid ID or JSON
    * `404 Not Found` if no item with that ID exists

### `DELETE /menu/{id}`

* **Description:** Delete a food item by its ID.
* **Path Parameters:**

    * `id` (integer) – the ID of the item to delete
* **Response Codes:**

    * `200 OK` if deletion succeeded
    * `400 Bad Request` if invalid ID
    * `404 Not Found` if no item with that ID exists

---

## Installation

### Prerequisites

* **Java Development Kit (JDK):** version 11 or higher
* **Git:** to clone the repository
* **Environment Variable (optional):**

    * `PORT` — override the default HTTP port (defaults to `8080`)

### Clone & Build

```bash
# Clone the repo
git clone https://github.com/your-username/foodflow.git
cd foodflow

# Use the Gradle wrapper to build
./gradlew build
```

### Run the Server

```bash
# Run on default port 8080
./gradlew run

# Or run on a custom port, e.g. 9090
PORT=9090 ./gradlew run
```

