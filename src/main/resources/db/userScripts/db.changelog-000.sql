drop table if exists UTILISATEUR cascade;

create sequence if not exists utilisateur_seq;
create sequence if not exists info_utilisateur_seq;

/*==============================================================*/
/* Table : UTILISATEUR                                               */
/*==============================================================*/
create table UTILISATEUR (
   ID                   INT4                 not null,
   MAIL                 VARCHAR(100)         null,
   MOT_DE_PASSE         VARCHAR(100)         null,
   constraint UTILISATEUR_pkey primary key (ID)
);

/*==============================================================*/
/* Table : INFO                                           */
/*==============================================================*/
create table INFO (
   ID                   INT4                 not null,
   UTILISATEUR_ID       INT4                 null,
   PRENOM               VARCHAR(50)          null,
   NOM                  VARCHAR(50)          null,
   DATE_DE_NAISSANCE    DATE                 null,
   constraint INFO_pkey primary key (ID)
);

alter table INFO
   add constraint FK_INFO_REFERENCE_UTILI foreign key (UTILISATEUR_ID)
      references UTILISATEUR (ID)
      on delete restrict on update restrict;