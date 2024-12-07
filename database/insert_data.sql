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
('Apple Inc.', 'AAPL', 1, 0.00),
('Microsoft Corporation', 'MSFT', 1, 0.00),
('Amazon.com Inc.', 'AMZN', 1, 0.00),
('Alphabet Inc. (Class A)', 'GOOGL', 1, 0.00),
('Alphabet Inc. (Class C)', 'GOOG', 1, 0.00),
('Meta Platforms Inc.', 'META', 1, 0.00),
('Tesla Inc.', 'TSLA', 1, 0.00),
('NVIDIA Corporation', 'NVDA', 1, 0.00),
('Berkshire Hathaway Inc. (Class B)', 'BRK.B', 1, 0.00),
('JPMorgan Chase & Co.', 'JPM', 1, 0.00),
('Johnson & Johnson', 'JNJ', 1, 0.00),
('The Coca-Cola Company', 'KO', 1, 0.00),
('PepsiCo Inc.', 'PEP', 1, 0.00),
('The Walt Disney Company', 'DIS', 1, 0.00),
('Netflix Inc.', 'NFLX', 1, 0.00),
('Adobe Inc.', 'ADBE', 1, 0.00),
('Intel Corporation', 'INTC', 1, 0.00),
('Nike Inc.', 'NKE', 1, 0.00);

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
