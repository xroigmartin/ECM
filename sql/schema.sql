CREATE TABLE IF NOT EXISTS ecm.domain (
	domain_id INT(10) NOT NULL AUTO_INCREMENT COMMENT 'Domain identifier',
    code_domain VARCHAR(5) NOT NULL COMMENT 'Code of domain',
    code_domain_text VARCHAR(100) COMMENT 'Explain de meaning of code domain', 
    is_enable BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Mark if domain is enable or not',
    PRIMARY KEY (domain_id),
    UNIQUE KEY (code_domain)
);

CREATE TABLE IF NOT EXISTS ecm.domain_value (
    domain_value_id INT(10) NOT NULL AUTO_INCREMENT COMMENT 'Domain value identifier',
    value VARCHAR(5) NOT NULL COMMENT 'Value of domain',
    is_enable BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Mark if value of domain is enable or not',
    domain_id int(10) NOT NULL COMMENT 'Id of domain to which value belong',
    PRIMARY KEY(domain_value_id),
    UNIQUE(value, domain_id),
    FOREIGN KEY (domain_id) REFERENCES ecm.domain(domain_id)
);