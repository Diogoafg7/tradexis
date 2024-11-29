-- Adicionar tipos de ativos
INSERT INTO Type_Assets (name) VALUES ('Stock'), ('Crypto'), ('Forex');

-- Adicionar ativos
INSERT INTO Assets (name, symbol, type_id)
VALUES
  ('Apple Inc.', 'AAPL', 1),
  ('Bitcoin', 'BTC', 2),
  ('EUR/USD', 'EURUSD', 3);

-- Adicionar tipos de transações
INSERT INTO Type_Trades (name) VALUES ('Buy'), ('Sell');

-- Adicionar utilizadores
INSERT INTO Users (username, email, password)
VALUES
  ('user1', 'user1@example.com', 'hashedpassword1'),
  ('user2', 'user2@example.com', 'hashedpassword2');

-- Adicionar carteiras
INSERT INTO Wallets (user_id, balance)
VALUES
  (1, 1000.00),
  (2, 2000.00);
