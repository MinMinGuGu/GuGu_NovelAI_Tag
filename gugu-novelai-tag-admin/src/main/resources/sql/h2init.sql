CREATE TABLE IF NOT EXISTS gugu_attribute
(
    id
    INT
    AUTO_INCREMENT
    NOT
    NULL,
    category_id
    INT
    NOT
    NULL,
    name
    VARCHAR
(
    255
) NOT NULL,
    value VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS gugu_category
(
    id
    INT
    AUTO_INCREMENT
    NOT
    NULL,
    name
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );
CREATE UNIQUE INDEX IF NOT EXISTS _category_index_name ON gugu_category (name);

CREATE TABLE IF NOT EXISTS gugu_config
(
    id
    INT
    AUTO_INCREMENT
    NOT
    NULL,
    key
    VARCHAR
(
    255
) NOT NULL,
    value VARCHAR
(
    255
) NOT NULL,
    description VARCHAR
(
    255
),
    PRIMARY KEY
(
    id
)
    );
CREATE UNIQUE INDEX IF NOT EXISTS _config_index_name ON gugu_config (key);
