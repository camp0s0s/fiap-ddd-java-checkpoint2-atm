# FIAP Bank - Sistema de ATM (Versão Beta)

## 📌 Sobre o Projeto
Esta é a versão Beta do FIAP Bank ATM. O objetivo é evoluir o sistema de Terminal Bancário (ATM) iniciado na fase Alpha, aplicando conceitos avançados de Programação Orientada a Objetos, Design Patterns e uma arquitetura baseada em camadas (Domain-Driven Design simplificado).

## 🚀 Novidades da Versão Beta
Nesta etapa, implementamos as seguintes melhorias:
- **Design Pattern Template Method:** Padronização do fluxo de saque na classe `Conta`, permitindo regras de taxas específicas para cada tipo de conta.
- **Design Pattern Factory & Singleton:** Centralização da criação de contas através da `ContaFactory`.
- **Value Objects (VO):** Uso de classes como `Dinheiro` e `ContaAcesso` para encapsular lógicas de negócio e aumentar a segurança.
- **Camada de Aplicação:** Introdução de `Services` para isolar a lógica de negócio da interface com o usuário.
- **Tratamento de Exceções:** Sistema robusto para lidar com saldo insuficiente ou contas bloqueadas.

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Paradigma OO** (Encapsulamento, Herança, Polimorfismo e Abstração)
- **Regex** (Para validação de senhas fortes)

## 🏗️ Estrutura de Pacotes
O projeto está organizado seguindo as melhores práticas de mercado:

* `br.fiap.bank.atm.model`: Contém as entidades, enums e objetos de valor (Coração do negócio).
* `br.fiap.bank.atm.application`: Contém os serviços e fábricas que coordenam as ações do sistema.
* `br.fiap.bank.atm.presentation`: Contém o `Main` e o `Controller` que gerencia a interação via console.

## 📋 Como Executar
1. Clone este repositório.
2. Certifique-se de que o JDK 17 ou superior está instalado.
3. Importe o projeto em sua IDE de preferência (VS Code, Eclipse ou IntelliJ).
4. Localize o arquivo `Main.java` no pacote `br.fiap.bank.atm.presentation`.
5. Execute a classe `Main`.

## 🛡️ Regras de Segurança
- A senha deve possuir no mínimo 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial.
- O sistema bloqueia automaticamente o acesso após 3 tentativas incorretas.

---
**Desenvolvido por:** Felipe Campos - RM562752
**Turma:** ESPG2