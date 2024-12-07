# Tradexis

Bem-vindo ao **Tradexis**, uma aplicação web simples e intuitiva para trading. Este projeto foi desenvolvido por um grupo de 4 estudantes no âmbito de um módulo de projeto.

---

## 📖 Sobre o Projeto

O **Tradexis** é uma plataforma que permite aos utilizadores simular transações de compra e venda de ativos, monitorizar saldos e visualizar dados de mercado. Este projeto foi desenvolvido com foco no aprendizado de tecnologias modernas e integração de diferentes camadas de software.

### ⚙️ Tecnologias Utilizadas
- **Backend:** Java (Spring Boot)
- **Frontend:** Angular
- **Base de Dados:** SQL
- **Containers:** Docker
- **Outros:** Ferramentas de design para UI/UX e integração CI/CD (quando aplicável).

---

## 🚀 Funcionalidades Principais
- 📊 **Gestão de Ativos**: Visualização de ativos disponíveis para trading.
- 💼 **Simulação de Transações**: Realizar operações de compra e venda.
- 💵 **Carteira Virtual**: Monitorizar saldos e ativos disponíveis.
- 🔒 **Autenticação de Utilizador**: Sistema de login seguro.
- 📈 **Gráficos de Desempenho**: Análise visual de tendências de mercado.

---

## 🛠️ Estrutura do Projeto

```plaintext
/
├── backend/       # Código da API em Spring Boot
├── frontend/      # Interface do utilizador em Angular
├── database/      # Scripts e definições da base de dados
├── docker/        # Configurações de containers Docker
└── README.md      # Documentação do repositório
```

## 🐳 Como Correr a Aplicação com Docker

### Pré-requisitos
- **Docker**: Certifique-se de que o Docker está instalado e em execução na sua máquina.
- **Docker Compose**: Instale o Docker Compose (geralmente incluído no Docker Desktop).

---

### Passos para Configuração

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/seu-username/tradexis.git
   cd tradexis
    ```
   
2. Certifique-se de que as portas necessárias (ex.: 8080 para o backend, 4200 para o frontend) estão livres.

3. Crie e suba os containers utilizando o Docker Compose:
   ```bash
   docker-compose up --build
   ```
   
4. Aguarde até que todos os serviços estejam em execução. Poderá verificar o status no terminal.

5. Aceda à aplicação: [http://localhost:8080](http://localhost:8080)

6. Para executar a aplicação do frontend, utilize o comando noutro terminal:
    ```bash
    cd frontAngular
    npm install
    ng serve
    ```

7. Aceda à aplicação do frontend: [http://localhost:4200](http://localhost:4200)

8. Para parar a execução do backend, utilize o comando:
   ```bash
   docker-compose down
   ```
   
9. Para parar a execução do frontend, utilize o comando:
   ```bash
    Ctrl + C
    ```
---