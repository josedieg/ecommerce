-- produto
insert into produto (id, nome, preco, data_criacao, descricao)values (1, 'telefone', 499.0, date_sub(sysdate(), interval 1 day), 'Melhor processador');
insert into produto (id, nome, preco, data_criacao, descricao)values (3, 'cadeira', 300.0, date_sub(sysdate(), interval 1 day),'A mais confortavel do mercado');

-- cliente
insert into cliente (cliente_id, nome) values (1, 'Jose Alves');
insert into cliente (cliente_id, nome) values (2, 'Maria');

-- pedido
insert into pedido (id, cliente_pedido_id, data_criacao, total, status) values (1, 1, sysdate(), 100.0, 'AGUARDANDO');

-- item_pedido
insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 5.0, 2);

-- categoria
insert into categoria (id, nome) values (1, 'Eletronicos');
insert into categoria (id, nome) values (2, 'Moveis');