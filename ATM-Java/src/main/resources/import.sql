insert into card(card_id, pin_number) values ('card_1', 1234);
insert into card(card_id, pin_number) values ('card_2', 1235);
insert into card(card_id, pin_number) values('card_3', 1236);
commit;

insert into account(account_bank, card_id, balance) values (0, 1, 100);
insert into account(account_bank, card_id, balance) values (1, 1, 150);
insert into account(account_bank, card_id, balance) values (2, 2, 130);
insert into account(account_bank, card_id, balance) values (3, 2, 160);
insert into account(account_bank, card_id, balance) values (4, 3, 130);
insert into account(account_bank, card_id, balance) values (0, 3, 140);
commit;
