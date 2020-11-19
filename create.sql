
create table brand (
                       id bigint not null,
                       brand_image longblob,
                       name varchar(25),
                       primary key (id)
) engine=MyISAM

create table brand_store (
                             store_id bigint not null,
                             brand_id bigint not null,
                             primary key (store_id, brand_id)
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

create table item_stock (
                            stock_id varchar(255),
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
                       store varchar(255),
                       talle varchar(255),
                       primary key (id)
) engine=MyISAM

create table store (
                       id bigint not null,
                       direccion varchar(100),
                       name varchar(25),
                       primary key (id)
) engine=MyISAM

create table usuario (
                         discriminator varchar(31) not null,
                         ci integer not null,
                         direccion varchar(100),
                         email varchar(100),
                         first_name varchar(25),
                         last_name varchar(25),
                         password varchar(25),
                         telefono integer,
                         primary key (ci)
) engine=MyISAM

alter table brand_store
    add constraint FKoq8vbuqlcp0965tkdnrphd1ge
        foreign key (brand_id)
            references brand (id)

alter table brand_store
    add constraint FK57bbk6i2gmb8tsrcvincf5fyl
        foreign key (store_id)
            references store (id)

alter table item_brand
    add constraint FKlbd6xylaaxin69hhx5thrrpu1
        foreign key (brand_id)
            references brand (id)

alter table item_brand
    add constraint FKp7mw1i4mvwlemfiypakccj98c
        foreign key (item_id)
            references item (id)

alter table item_stock
    add constraint FKph1ei2v0n1gscre4cdneh69en
        foreign key (stock_id)
            references stock (id)

alter table item_stock
    add constraint FK89enx1j16iuuf78iehd94v8uw
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

create table brand (
                       id bigint not null,
                       brand_image longblob,
                       name varchar(25),
                       primary key (id)
) engine=MyISAM

create table brand_store (
                             store_id bigint not null,
                             brand_id bigint not null,
                             primary key (store_id, brand_id)
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

create table item_stock (
                            stock_id varchar(255),
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
                       store varchar(255),
                       talle varchar(255),
                       primary key (id)
) engine=MyISAM

create table store (
                       id bigint not null,
                       direccion varchar(100),
                       name varchar(25),
                       primary key (id)
) engine=MyISAM

create table usuario (
                         discriminator varchar(31) not null,
                         ci integer not null,
                         direccion varchar(100),
                         email varchar(100),
                         first_name varchar(25),
                         last_name varchar(25),
                         password varchar(25),
                         telefono integer,
                         primary key (ci)
) engine=MyISAM

alter table brand_store
    add constraint FKoq8vbuqlcp0965tkdnrphd1ge
        foreign key (brand_id)
            references brand (id)

alter table brand_store
    add constraint FK57bbk6i2gmb8tsrcvincf5fyl
        foreign key (store_id)
            references store (id)

alter table item_brand
    add constraint FKlbd6xylaaxin69hhx5thrrpu1
        foreign key (brand_id)
            references brand (id)

alter table item_brand
    add constraint FKp7mw1i4mvwlemfiypakccj98c
        foreign key (item_id)
            references item (id)

alter table item_stock
    add constraint FKph1ei2v0n1gscre4cdneh69en
        foreign key (stock_id)
            references stock (id)

alter table item_stock
    add constraint FK89enx1j16iuuf78iehd94v8uw
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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

    create table brand (
       id bigint not null,
        brand_image longblob,
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table brand_store (
       store_id bigint not null,
        brand_id bigint not null,
        primary key (store_id, brand_id)
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

    create table item_stock (
       stock_id varchar(255),
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
        store varchar(255),
        talle varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table store (
       id bigint not null,
        direccion varchar(100),
        name varchar(25),
        primary key (id)
    ) engine=MyISAM

    create table usuario (
       discriminator varchar(31) not null,
        ci integer not null,
        direccion varchar(100),
        email varchar(100),
        first_name varchar(25),
        last_name varchar(25),
        password varchar(25),
        telefono integer,
        primary key (ci)
    ) engine=MyISAM

    alter table brand_store 
       add constraint FKoq8vbuqlcp0965tkdnrphd1ge 
       foreign key (brand_id) 
       references brand (id)

    alter table brand_store 
       add constraint FK57bbk6i2gmb8tsrcvincf5fyl 
       foreign key (store_id) 
       references store (id)

    alter table item_brand 
       add constraint FKlbd6xylaaxin69hhx5thrrpu1 
       foreign key (brand_id) 
       references brand (id)

    alter table item_brand 
       add constraint FKp7mw1i4mvwlemfiypakccj98c 
       foreign key (item_id) 
       references item (id)

    alter table item_stock 
       add constraint FKph1ei2v0n1gscre4cdneh69en 
       foreign key (stock_id) 
       references stock (id)

    alter table item_stock 
       add constraint FK89enx1j16iuuf78iehd94v8uw 
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
