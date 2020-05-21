DELIMITER $$
CREATE TRIGGER set_expiration_date
BEFORE UPDATE
ON world.requests FOR EACH ROW
BEGIN
IF (new.status = '2') THEN
        set new.request_expiration_date = curdate() + interval 1 year;
        INSERT INTO world.requeststatushistory(company_id,description, request_date,status,supervisor,request_expiration_date,changed_status,changed_request_expiration_date,changed_at)
        VALUES(old.company_id, old.description,old.request_date,old.status,old.supervisor,old.request_expiration_date,new.status,new.request_expiration_date,now());
    elseif (new.status != '2') THEN
		set new.request_expiration_date = NULL;
        INSERT INTO world.requeststatushistory(company_id,description, request_date,status,supervisor,request_expiration_date,changed_status,changed_request_expiration_date,changed_at)
        VALUES(old.company_id, old.description,old.request_date,old.status,old.supervisor,old.request_expiration_date,new.status,new.request_expiration_date,now());
       else
        INSERT INTO world.requeststatushistory(company_id,description, request_date,status,supervisor,request_expiration_date,changed_status,changed_request_expiration_date,changed_at)
        VALUES(old.company_id, old.description,old.request_date,old.status,old.supervisor,old.request_expiration_date,new.status,new.request_expiration_date,now());
    END IF;
END$$
DELIMITER ;