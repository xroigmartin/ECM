INSERT INTO ecm.domain(code_domain, code_domain_text) VALUES ('SEX', 'Sex of person');
INSERT INTO ecm.domainValue(value, domain_id) values("H", (SELECT domain_id from ecm.domain where code_domain="SEX"));
INSERT INTO ecm.domainValue(value, domain_id) values("M", (SELECT domain_id from ecm.domain where code_domain="SEX"));
INSERT INTO ecm.domain(code_domain, code_domain_text) VALUES ('LANG', 'Languages of application');
INSERT INTO ecm.domainValue(value, domain_id) values("ESP", (SELECT domain_id from ecm.domain where code_domain="LANG"));
INSERT INTO ecm.domainValue(value, domain_id) values("CAT", (SELECT domain_id from ecm.domain where code_domain="LANG"));
INSERT INTO ecm.domainValue(value, domain_id) values("ENG", (SELECT domain_id from ecm.domain where code_domain="LANG"));

COMMIT;