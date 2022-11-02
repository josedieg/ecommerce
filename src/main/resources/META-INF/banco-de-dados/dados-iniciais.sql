insert into produto (id, versao, nome, preco, data_criacao, descricao) values (1, 0, 'smartphone 5G', 499.0, date_sub(sysdate(), interval 1 day), 'Melhor smartphone');
insert into produto (id, versao, nome, preco, data_criacao, descricao) values (3, 0, 'notebook I5', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente (id, versao, nome) values (1, 0, 'Joao Santos');
insert into cliente (id, versao, nome) values (2, 0, 'Aparecida Lima');

insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (1, 0, 1, sysdate(), 998.0, 'AGUARDANDO');
insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (2, 0, 1, sysdate(), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);

insert into db_ecommerce.pagamento (dtype,pedido_id,status,numero) values (', 0PagamentoCartao',2,'PROCESSANDO','123123123');

insert into nota_fiscal (pedido_id, versao, xml, data_emissao) values (2, 0, '<xml />', sysdate());

insert into categoria (id, versao, nome) values (1, 0, 'Eletrônicos');
insert into categoria (id, versao, nome) values (2, 0, 'Móveis');

drop procedure if exists buscar_nome_produto;
create procedure buscar_nome_produto(in produto_id int, in produto_id2 int, out produto_nome varchar(255)) begin select nome into produto_nome from produto where id = produto_id; end
