package com.example.E_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {

        SpringApplication.run(ECommerceApplication.class, args);

        System.out.println("devesh tenguriya");
	}

    //{
    //    "password": "Hinata8220",
    //    "email":"naruto@example.com"
    //}

    //com.example.ecommerce
    //│
    //├── config
    //│   ├── SecurityConfig.java
    //│   ├── JwtFilter.java
    //│   ├── JwtUtil.java
    //│   └── ModelMapperConfig.java
    //│
    //├── controller
    //│   ├── AuthController.java
    //│   ├── UserController.java
    //│   ├── ProductController.java
    //│   ├── CartController.java
    //│   └── OrderController.java
    //│
    //├── service
    //│   ├── AuthService.java
    //│   ├── UserService.java
    //│   ├── ProductService.java
    //│   ├── CartService.java
    //│   └── OrderService.java
    //│
    //├── repository
    //│   ├── UserRepository.java
    //│   ├── ProductRepository.java
    //│   ├── CartRepository.java
    //│   └── OrderRepository.java
    //│
    //├── entity
    //│   ├── User.java
    //│   ├── Role.java
    //│   ├── Product.java
    //│   ├── Category.java
    //│   ├── Cart.java
    //│   ├── CartItem.java
    //│   ├── Order.java
    //│   └── OrderItem.java
    //│
    //├── dto
    //│   ├── request
    //│   └── response
    //│
    //├── exception
    //│   ├── GlobalExceptionHandler.java
    //│   └── ResourceNotFoundException.java
    //│
    //├── util
    //│   └── ApiResponse.java
    //│
    //└── EcommerceApplication.java

    //2️⃣ Core Modules & Responsibilities
    //🔐 Authentication & Authorization
    //
    //JWT login/register
    //
    //Roles: ADMIN, CUSTOMER
    //
    //Secure endpoints using Spring Security
    //
    //👤 User Module
    //
    //Register & login
    //
    //View profile
    //
    //Admin can manage users
    //
    //📦 Product Module
    //
    //Create / update / delete products (Admin)
    //
    //Browse products (Customer)
    //
    //Category filtering
    //
    //Pagination & sorting
    //
    //🛒 Cart Module
    //
    //Add product to cart
    //
    //Update quantity
    //
    //Remove item
    //
    //View cart
    //
    //📄 Order Module
    //
    //Place order from cart
    //
    //Reduce inventory
    //
    //Order history
    //
    //Order status tracking
    //
    //3️⃣ Database Schema (PostgreSQL)
    //🔹 USER TABLE
    //users (
    //  id BIGSERIAL PRIMARY KEY,
    //  name VARCHAR(100),
    //  email VARCHAR(150) UNIQUE,
    //  password VARCHAR(255),
    //  role VARCHAR(20),
    //  created_at TIMESTAMP
    //)
    //
    //🔹 CATEGORY TABLE
    //categories (
    //  id BIGSERIAL PRIMARY KEY,
    //  name VARCHAR(100)
    //)
    //
    //🔹 PRODUCT TABLE
    //products (
    //  id BIGSERIAL PRIMARY KEY,
    //  name VARCHAR(150),
    //  description TEXT,
    //  price DECIMAL(10,2),
    //  stock INT,
    //  category_id BIGINT,
    //  FOREIGN KEY (category_id) REFERENCES categories(id)
    //)
    //
    //🔹 CART TABLE
    //carts (
    //  id BIGSERIAL PRIMARY KEY,
    //  user_id BIGINT UNIQUE,
    //  FOREIGN KEY (user_id) REFERENCES users(id)
    //)
    //
    //🔹 CART ITEMS
    //cart_items (
    //  id BIGSERIAL PRIMARY KEY,
    //  cart_id BIGINT,
    //  product_id BIGINT,
    //  quantity INT,
    //  FOREIGN KEY (cart_id) REFERENCES carts(id),
    //  FOREIGN KEY (product_id) REFERENCES products(id)
    //)
    //
    //🔹 ORDERS
    //orders (
    //  id BIGSERIAL PRIMARY KEY,
    //  user_id BIGINT,
    //  total_amount DECIMAL(10,2),
    //  status VARCHAR(50),
    //  created_at TIMESTAMP
    //)
    //
    //🔹 ORDER ITEMS
    //order_items (
    //  id BIGSERIAL PRIMARY KEY,
    //  order_id BIGINT,
    //  product_id BIGINT,
    //  quantity INT,
    //  price DECIMAL(10,2),
    //  FOREIGN KEY (order_id) REFERENCES orders(id),
    //  FOREIGN KEY (product_id) REFERENCES products(id)
    //)
    //
    //4️⃣ Step-by-Step Implementation Plan (Follow This Order)
    //🔥 Phase 1 – Foundation
    //
    //Create Spring Boot project
    //
    //Configure PostgreSQL + JPA
    //
    //Create entities & relationships
    //
    //Add repositories
    //
    //🔐 Phase 2 – Security (VERY IMPORTANT)
    //
    //Implement JWT authentication
    //
    //Secure APIs with roles
    //
    //Global exception handling
    //
    //📦 Phase 3 – Business Logic
    //
    //Product CRUD
    //
    //Category management
    //
    //Cart operations
    //
    //Order placement logic
    //
    //🚀 Phase 4 – Advanced Features
    //
    //Pagination & sorting
    //
    //Validation
    //
    //Logging
    //
    //API versioning (/api/v1)

}
