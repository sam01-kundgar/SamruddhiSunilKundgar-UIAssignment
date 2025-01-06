README - Reward Points Calculation Application
Overview :
This application is designed to manage customer transactions, calculate reward points, and track the monthly rewards earned by customers. 
The logic for reward points calculation is based on the amount spent during each transaction. 
The application includes APIs to handle customer data, transactions, and reward points.
It also incorporates business logic for calculating the reward points.

Features : 
Customer Entity: Stores the basic details of the customer.
CustomerTransaction Entity: Stores transaction details for each customer, including transaction amount and date.
RewardPoints Entity: Tracks monthly reward points for each customer.
Reward Points Calculation: Points are earned based on the amount spent in each transaction according to the specified logic:
For transaction amounts greater than $100: 2 points per dollar spent.
For transaction amounts between $50 and $100: 1 point per dollar spent.
For transaction amounts below $50: No reward points earned.

Entities
1. Customer
Represents the customer and their associated details.

Fields:
customer_id: Unique identifier for the customer.
name: Name of the customer.
email: Email address of the customer.

2. CustomerTransaction
Stores the transaction details for each customer.

Fields:
transaction_id: Unique identifier for the transaction.
customer_id: Reference to the customer who made the transaction.
transaction_amount: Amount of the transaction.
transaction_date: Date of the transaction.
3. RewardPoints
Tracks the monthly reward points earned by a customer.

Fields:
customer_id: Reference to the customer.
month: Month when the points were calculated (in YYYY-MM format).
points_earned: Total reward points earned for that month.

Reward Points Calculation Logic
Rules for Calculation:
Amount > $100: Customer earns 2 points per dollar.
$50 ≤ Amount ≤ $100: Customer earns 1 point per dollar.
Amount < $50: No points are earned.
Example:
Transaction Amount: $120 → Earn 240 reward points (2 points per dollar).
Transaction Amount: $75 → Earn 75 reward points (1 point per dollar).
Transaction Amount: $45 → Earn 0 reward points (no points for amounts below $50).

APIs
1. POST /customers
Creates a new customer.

Request Body:
json
Copy code
{
  "name": "Teena Desouza",
  "email": "teena01@example.com"
}
Response:
json
{
  "customer_id": 1,
  "name": "Teena Desouza",
  "email": "teena01@example.com"
}
2. POST /transactions
Creates a new transaction for a customer.

Request Body:
json
{
  "customer_id": 1,
  "transaction_amount": 120.00,
  "transaction_date": "2025-01-06"
}
Response:
json
{
  "transaction_id": 1,
  "customer_id": 1,
  "transaction_amount": 120.00,
  "transaction_date": "2025-01-06"
}
3. GET /reward-points/{customer_id}
Fetches the total reward points earned by a customer for a specific month.

Request Parameters:
customer_id: The unique ID of the customer.
Response:
json
{
  "customer_id": 1,
  "month": "2025-01",
  "points_earned": 240
}
How to Run
Install Dependencies: Ensure you have Java JdK 17,SpringBoot 3.4.1,Rest Repository. Install the required dependencies for H2 database:
