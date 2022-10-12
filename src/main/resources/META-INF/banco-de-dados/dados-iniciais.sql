insert into produto (id, nome, preco, data_criacao, descricao) values (1, 'smartphone 5G', 499.0, date_sub(sysdate(), interval 1 day), 'Melhor smartphone');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'notebook I5', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente (id, nome) values (1, 'Joao Santos');
insert into cliente (id, nome) values (2, 'Aparecida Lima');

insert into pedido (id, cliente_id, data_criacao, total, status) values (1, 1, sysdate(), 998.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 1, sysdate(), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);

insert into db_ecommerce.pagamento (dtype,pedido_id,status,numero) values ('PagamentoCartao',2,'PROCESSANDO','123123123');

insert into categoria (id, nome) values (1, 'Eletrônicos');
insert into categoria (id, nome) values (2, 'Móveis');