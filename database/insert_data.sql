-- Inserir dados de teste
-- Inserir Users
INSERT INTO Users (username, email, password) VALUES
('testuser1', 'test1@example.com', 'password1'),
('testuser2', 'test2@example.com', 'password2'),
('testuser3', 'test3@example.com', 'password3');

-- Inserir Type_Assets
INSERT INTO Type_Assets (name) VALUES
('Stock'),
('Crypto'),
('Forex');

-- Inserir Assets
INSERT INTO Assets (name, symbol, type_id, price) VALUES
('Bitcoin', 'BTC', 2, 40000.00),
('Ethereum', 'ETH', 2, 2500.00),
('Apple', 'AAPL', 1, 180.00),
('Gold', 'GLD', 3, 1500.00);

-- Inserir Type_Trades
INSERT INTO Type_Trades (name) VALUES
('Buy'),
('Sell');

-- Inserir Wallets
INSERT INTO Wallets (user_id, balance) VALUES
(1, 10000.00),
(2, 5000.00),
(3, 2000.00);

-- Inserir Portfolio
INSERT INTO Portfolio (user_id, asset_id, quantity) VALUES
(1, 1, 0.5),
(1, 3, 10),
(2, 2, 2),
(3, 4, 1);

-- Inserir Trades
INSERT INTO Trades (user_id, asset_id, trade_type_id, quantity, total) VALUES
(1, 1, 1, 0.5, 20000.00),
(1, 3, 1, 10, 1800.00),
(2, 2, 1, 2, 5000.00),
(3, 4, 1, 1, 1500.00);

-- Inserir StockHistory
INSERT INTO StockHistory (asset_id, price, timestamp) VALUES
(1, 39000.00, '2024-12-01 10:00:00'),
(1, 40000.00, '2024-12-02 10:00:00'),
(2, 2400.00, '2024-12-01 10:00:00'),
(2, 2500.00, '2024-12-02 10:00:00'),
(3, 170.00, '2024-12-01 10:00:00'),
(3, 180.00, '2024-12-02 10:00:00'),
(4, 1450.00, '2024-12-01 10:00:00'),
(4, 1500.00, '2024-12-02 10:00:00');
