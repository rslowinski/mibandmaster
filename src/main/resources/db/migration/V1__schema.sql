CREATE SEQUENCE IF NOT EXISTS users_seq;

CREATE TABLE users
(
    id         bigint       NOT NULL DEFAULT NEXTVAL('users_seq'),
    name       varchar(60)  NOT NULL,
    email      varchar(60)  NOT NULL,
    password   varchar(100) NOT NULL,
    created_at timestamp(0)          DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(0)          DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);


CREATE SEQUENCE IF NOT EXISTS roles_seq;

CREATE TABLE roles
(
    id   bigint      NOT NULL DEFAULT NEXTVAL('roles_seq'),
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_roles_name UNIQUE (name)
);


CREATE TABLE user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX fk_user_roles_role_id ON user_roles (role_id);
