export interface Trade {
    id: number;
    user: {
      id: number;
      username: string;
      email: string;
      enabled: boolean;
      authorities: { authority: string }[];
    };
    asset: {
      id: number;
      name: string;
      symbol: string;
      typeId: { id: number; name: string };
      price: number; // Preço no momento da transação
    };
    tradeType: { id: number; name: string }; // "Buy" ou "Sell"
    quantity: number; // Quantidade transacionada
    total: number; // Total da transação
    createdAt: string;
  }