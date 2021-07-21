create table if not exists equity
(
    id                    varchar(255) not null,
    country               varchar(255),
    current_cost_per_unit double precision,
    equity_area           varchar(255),
    equity_currency       varchar(255),
    equity_type           varchar(255),
    stock_exchange        varchar(255),
    ticker                varchar(255),
    constraint equity_pkey
    primary key (id)
    );

create table if not exists portfolio
(
    id      varchar(255) not null,
    horizon date,
    name    varchar(255),
    status  varchar(255),
    constraint portfolio_pkey
    primary key (id)
    );

create table if not exists position
(
    id           varchar(255) not null,
    amount       bigint,
    broker       varchar(255),
    current_cost double precision,
    status       varchar(255),
    equity_id    varchar(255),
    portfolio_id varchar(255),
    constraint position_pkey
    primary key (id),
    constraint fk1qi1tcb4xsp13d84m7xgk0qlo
    foreign key (equity_id) references equity,
    constraint fkfasqiaxvlw3whr2epqledio52
    foreign key (portfolio_id) references portfolio
    );

create table if not exists dividend
(
    id              varchar(255) not null,
    amount_per_unit double precision,
    currency        varchar(255),
    date            date,
    dividend_status varchar(255),
    operation_id    varchar(255),
    percentage      double precision,
    total_amount    double precision,
    total_fee       double precision,
    position_id     varchar(255),
    constraint dividend_pkey
    primary key (id),
    constraint fkhgs0t2hc22wo7vgi4vtxjmbno
    foreign key (position_id) references position
    );

create table if not exists operation
(
    id               varchar(255) not null,
    amount           bigint,
    currency         varchar(255),
    date             date,
    operation_status varchar(255),
    operation_type   varchar(255),
    price_per_unit   double precision,
    total_fee        double precision,
    total_price      double precision,
    portfolio_id     varchar(255),
    position_id      varchar(255),
    constraint operation_pkey
    primary key (id),
    constraint fkmx7i3jy70tymr1lhqm7kccneo
    foreign key (portfolio_id) references portfolio,
    constraint fkl74tbbfpn9wltg1onuv61wo2t
    foreign key (position_id) references position
    );

create table if not exists purchase
(
    id                     varchar(255) not null,
    amount                 bigint,
    currency               varchar(255),
    current_cost           double precision,
    date                   date,
    purchase_amount        bigint,
    purchase_cost          double precision,
    purchase_cost_per_unit double precision,
    purchase_fee           double precision,
    constraint purchase_pkey
    primary key (id)
    );

create table if not exists purchase_history
(
    position_id varchar(255) not null,
    purchase_id varchar(255) not null,
    constraint uk_t4juxqm81sogsjl4ymwmpyf5h
    unique (purchase_id),
    constraint fkj1gb5e5p39lpiy3h2xnqyobdk
    foreign key (purchase_id) references purchase,
    constraint fkqi8701wtfwgddxcifn241uv3t
    foreign key (position_id) references position
    );
