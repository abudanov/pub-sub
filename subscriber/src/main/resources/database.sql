create table if not exists PURCHASE
(
	id bigint not null
		constraint purchases_pkey
			primary key,
	msisdn bigint not null,
	timestamp timestamp
);

create table if not exists SUBSCRIPTION
(
    id bigint not null
    constraint subscriptions_pkey
    primary key,
    msisdn bigint not null,
    timestamp timestamp not null
);


