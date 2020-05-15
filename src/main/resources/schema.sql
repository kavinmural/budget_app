DROP TABLE IF EXISTS User;

DROP TABLE IF EXISTS Income;

DROP TABLE IF EXISTS Expense;


CREATE TABLE IF NOT EXISTS User (
  id INTEGER PRIMARY KEY,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  active BOOLEAN NOT NULL,
  roles TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  balance INTEGER NOT NULL,
  register_date TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS Income (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  type TEXT NOT NULL,
  description TEXT,
  amount INTEGER NOT NULL,
  created_date TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id)
);


CREATE TABLE IF NOT EXISTS Expense (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  type TEXT NOT NULL,
  description TEXT,
  amount INTEGER NOT NULL,
  created_date TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id)
);