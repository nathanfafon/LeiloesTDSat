# LeiloesTDSat

## Sobre o projeto

Aplicação desktop desenvolvida em Java para apoiar as rotinas de uma casa de leilões. O sistema permite cadastrar produtos com nome e valor, consultar a lista de produtos cadastrados e marcar produtos como vendidos, com todos os dados armazenados em um banco de dados MySQL.

O projeto foi iniciado a partir de uma proposta de informatização de uma casa de leilões e está versionado neste repositório para garantir segurança e qualidade durante o desenvolvimento.

## Tecnologias utilizadas

- Java 18
- Java Swing (interface gráfica)
- JDBC (conexão com o banco)
- MySQL
- NetBeans (IDE)
- Git e GitHub (versionamento)

## Banco de dados

O banco se chama `uc11` e possui a tabela `produtos`, com as colunas `id`, `nome`, `valor` e `status`. O script `uc11.sql` fornecido com o projeto deve ser importado no MySQL depois de criar o schema `uc11`.

## Como executar

1. Clone o repositório: `git clone https://github.com/nathanfafon/LeiloesTDSat.git`
2. Crie o schema `uc11` no MySQL e importe o script `uc11.sql`.
3. Abra a pasta do projeto no NetBeans.
4. Confira o usuário e a senha do banco na classe `conectaDAO`.
5. Execute o projeto pela tela `cadastroVIEW`.
