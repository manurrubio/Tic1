
    create table admin (
       ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table item (
       id bigint not null,
        category varchar(25),
        description varchar(100),
        imagen1 longblob,
        imagen2 longblob,
        imagen3 longblob,
        imagen_principal longblob,
        name varchar(25),
        price integer not null,
        type varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table item_brand (
       brand_id bigint,
        item_id bigint not null,
        primary key (item_id)
    ) engine=MyISAM

    create table item_store (
       store_id bigint,
        item_id bigint not null,
        primary key (item_id)
    ) engine=MyISAM

    create table stock (
       id varchar(255) not null,
        cantidad bigint,
        color varchar(255),
        store bigint,
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table stock_item (
       item_id bigint,
        stock_id varchar(255) not null,
        primary key (stock_id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table store_admin (
       admin_id integer,
        store_id bigint not null,
        primary key (store_id)
    ) engine=MyISAM

    create table usuario (
       ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_store 
       add constraint FKl0kwdnjciikdd1if67c1d1a3 
       foreign key (store_id) 
       references store (id)

    alter table item_store 
       add constraint FKj0ydimrjjtugln92epkgaql6k 
       foreign key (item_id) 
       references item (id)

    alter table stock_item 
       add constraint FKqo71uip1atsoa1666d3wtp3lv 
       foreign key (item_id) 
       references item (id)

    alter table stock_item 
       add constraint FK1p14bepct2q019cw0nj4onbp1 
       foreign key (stock_id) 
       references stock (id)

    alter table store_admin 
       add constraint FK32t6wttihmlg41im34r3lvcpl 
       foreign key (admin_id) 
       references admin (ci)

    alter table store_admin 
       add constraint FKkugfw12qlh72n1xjirpj31pcl 
       foreign key (store_id) 
       references store (id)

    create table admin (
       ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table item (
       id bigint not null,
        category varchar(25),
        description varchar(100),
        imagen1 longblob,
        imagen2 longblob,
        imagen3 longblob,
        imagen_principal longblob,
        name varchar(25),
        price integer not null,
        type varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table item_brand (
       brand_id bigint,
        item_id bigint not null,
        primary key (item_id)
    ) engine=MyISAM

    create table item_store (
       store_id bigint,
        item_id bigint not null,
        primary key (item_id)
    ) engine=MyISAM

    create table stock (
       id varchar(255) not null,
        cantidad bigint,
        color varchar(255),
        store bigint,
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table stock_item (
       item_id bigint,
        stock_id varchar(255) not null,
        primary key (stock_id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table store_admin (
       admin_id integer,
        store_id bigint not null,
        primary key (store_id)
    ) engine=MyISAM

    create table usuario (
       ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_store 
       add constraint FKl0kwdnjciikdd1if67c1d1a3 
       foreign key (store_id) 
       references store (id)

    alter table item_store 
       add constraint FKj0ydimrjjtugln92epkgaql6k 
       foreign key (item_id) 
       references item (id)

    alter table stock_item 
       add constraint FKqo71uip1atsoa1666d3wtp3lv 
       foreign key (item_id) 
       references item (id)

    alter table stock_item 
       add constraint FK1p14bepct2q019cw0nj4onbp1 
       foreign key (stock_id) 
       references stock (id)

    alter table store_admin 
       add constraint FK32t6wttihmlg41im34r3lvcpl 
       foreign key (admin_id) 
       references admin (ci)

    alter table store_admin 
       add constraint FKkugfw12qlh72n1xjirpj31pcl 
       foreign key (store_id) 
       references store (id)
