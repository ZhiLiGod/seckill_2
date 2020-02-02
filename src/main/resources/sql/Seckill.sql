-- Seckill Procedure
DELIMITER $$
CREATE PROCEDURE `seckill`.`execute_seckill`
	(in v_seckill_id bigint, in v_phone bigint, in v_kill_time timestamp, out r_result int)
	BEGIN
		DECLARE insert_count int DEFAULT 0;
		START TRANSACTION;
		insert ignore into success_killed
			(seckill_id, user_phone, create_time)
			values (v_seckill_id, v_phone, v_kill_time);
		select row_count() into insert_count;
		IF (insert_count = 0) THEN
			ROLLBACK;
			set r_result = -1
		ELSEIF (insert_count < 0) THEN
			ROLLBACK;
			set r_result = -2
		ELSE
			UPDATE seckill
			SET number = number -1
			WHERE seckill_id = v_seckill_id
			AND end_time > v_kill_time
			AND start_time < v_kill_time
			AND number > 0
			select row_count() into insert_count;
			IF (insert_count = 0) THEN
				ROLLBACK;
				set r_result = 0;
			ELSEIF (insert_count < 0) THEN
				ROLLBACK;
				set r_result = -2;
			ELSE
				COMMIT;
				set r_result = 1;
			END IF;
		END IF;
	END;
$$

DELIMITER ;
set @r_result = -3
call execute_seckill(1003, 18888888, now(), @r_result);
select @r_result;