CREATE TABLE IF NOT EXISTS ecm.domain (
	domain_id INT(10) NOT NULL AUTO_INCREMENT COMMENT 'Domain identifier',
    code_domain VARCHAR(5) NOT NULL COMMENT 'Code of domain',
    code_domain_text VARCHAR(100) COMMENT 'Explain de meaning of code domain', 
    is_enable BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Mark if domain is enable or not',
    PRIMARY KEY (domain_id),
    UNIQUE KEY (code_domain)
);