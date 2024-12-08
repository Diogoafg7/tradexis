export interface TypeId {
  id: number;
  name: string;
  createdAt: string; // ou Date, se preferir trabalhar com objetos Date
}

export interface StockData {
  id: number;
  name: string;
  symbol: string;
  typeId: TypeId;
  price: number;
  createdAt: string; // ou Date, se preferir trabalhar com objetos Date
}