-- Adicionar tipos de ativos
INSERT INTO Type_Assets (name) VALUES ('Stock'), ('Crypto'), ('Forex')
ON DUPLICATE KEY UPDATE name=name;

-- Adicionar ativos
INSERT INTO Assets (name, symbol, type_id, price)
VALUES
  ('Apple Inc.', 'AAPL', 1, 175.50),
  ('Bitcoin', 'BTC', 2, 45000.00),
  ('EUR/USD', 'EURUSD', 3, 1.10)
ON DUPLICATE KEY UPDATE name=VALUES(name), symbol=VALUES(symbol), type_id=VALUES(type_id), price=VALUES(price);

-- Adicionar tipos de transações
INSERT INTO Type_Trades (name) VALUES ('Buy'), ('Sell')
ON DUPLICATE KEY UPDATE name=name;

-- Adicionar utilizadores
INSERT INTO Users (username, email, password)
VALUES
  ('user1', 'user1@example.com', 'hashedpassword1'),
  ('user2', 'user2@example.com', 'hashedpassword2')
ON DUPLICATE KEY UPDATE username=VALUES(username), email=VALUES(email), password=VALUES(password);

-- Adicionar carteiras
INSERT INTO Wallets (user_id, balance)
VALUES
  (1, 1000.00),
  (2, 2000.00)
ON DUPLICATE KEY UPDATE user_id=VALUES(user_id), balance=VALUES(balance);