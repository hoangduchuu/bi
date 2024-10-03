CREATE TABLE `users`
(
    `user_id`    int PRIMARY KEY AUTO_INCREMENT,
    `username`   varchar(255) UNIQUE            NOT NULL,
    `password`   varchar(255)                   NOT NULL,
    `email`      varchar(255) UNIQUE            NOT NULL,
    `fullname`   varchar(255)                   NOT NULL,
    `phone`      varchar(255),
    `role`       enum ('user', 'club', 'admin') NOT NULL,
    `balance`    decimal(10, 2) DEFAULT 0,
    `created_at` timestamp      DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp      DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `clubs`
(
    `club_id`    int PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL,
    `address`    varchar(255) NOT NULL,
    `phone`      varchar(255) NOT NULL,
    `user_id`    int          NOT NULL,
    `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `chains`
(
    `chain_id`    int PRIMARY KEY AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL,
    `description` text,
    `created_at`  timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `club_chains`
(
    `club_chain_id` int PRIMARY KEY AUTO_INCREMENT,
    `club_id`       int NOT NULL,
    `chain_id`      int NOT NULL,
    `created_at`    timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `tables`
(
    `table_id`       int PRIMARY KEY AUTO_INCREMENT,
    `club_id`        int                                           NOT NULL,
    `number`         int                                           NOT NULL,
    `type`           varchar(255)                                  NOT NULL,
    `status`         enum ('available', 'occupied', 'maintenance') NOT NULL,
    `price_per_hour` decimal(10, 2)                                NOT NULL,
    `created_at`     timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `club_settings`
(
    `club_id`                int PRIMARY KEY,
    `default_price_per_hour` decimal(10, 2) NOT NULL,
    `currency`               varchar(255)   NOT NULL
);

CREATE TABLE `items`
(
    `item_id`    int PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255)           NOT NULL,
    `type`       enum ('food', 'drink') NOT NULL,
    `price`      decimal(10, 2)         NOT NULL,
    `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `open_table_tickets`
(
    `open_table_ticket_id` int PRIMARY KEY AUTO_INCREMENT,
    `user_id`              int       NOT NULL,
    `table_id`             int       NOT NULL,
    `club_id`              int       NOT NULL,
    `start_time`           timestamp NOT NULL,
    `end_time`             timestamp NOT NULL,
    `total_time`           int,
    `discount`             decimal(10, 2),
    `created_at`           timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `invoices`
(
    `invoice_id`           int PRIMARY KEY AUTO_INCREMENT,
    `open_table_ticket_id` int NOT NULL,
    `user_id`              int NOT NULL,
    `club_id`              int NOT NULL,
    `created_at`           timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `invoice_details`
(
    `invoice_detail_id` int PRIMARY KEY AUTO_INCREMENT,
    `invoice_id`        int            NOT NULL,
    `item_id`           int            NOT NULL,
    `quantity`          int            NOT NULL,
    `price`             decimal(10, 2) NOT NULL,
    `created_at`        timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `points`
(
    `point_id`    int PRIMARY KEY AUTO_INCREMENT,
    `user_id`     int NOT NULL,
    `chain_id`    int NOT NULL,
    `total_point` int NOT NULL,
    `created_at`  timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `payment_methods`
(
    `payment_method_id` int PRIMARY KEY AUTO_INCREMENT,
    `name`              varchar(255) NOT NULL,
    `description`       text
);

CREATE TABLE `payment_transactions`
(
    `payment_transaction_id` int PRIMARY KEY AUTO_INCREMENT,
    `open_table_ticket_id`   int            NOT NULL,
    `payment_method_id`      int            NOT NULL,
    `amount`                 decimal(10, 2) NOT NULL,
    `transaction_date`       timestamp      NOT NULL
);

CREATE TABLE `topup_transactions`
(
    `topup_transaction_id` int PRIMARY KEY AUTO_INCREMENT,
    `user_id`              int                          NOT NULL,
    `amount`               decimal(10, 2)               NOT NULL,
    `transaction_date`     timestamp                    NOT NULL,
    `transaction_type`     enum ('manual', 'automatic') NOT NULL,
    `admin_id`             int,
    `created_at`           timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `user_levels`
(
    `level_id`         int PRIMARY KEY AUTO_INCREMENT,
    `name`             varchar(255)   NOT NULL,
    `min_spent_amount` decimal(10, 2) NOT NULL,
    `reward_hours`     int,
    `created_at`       timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `user_level_history`
(
    `level_history_id` int PRIMARY KEY AUTO_INCREMENT,
    `user_id`          int       NOT NULL,
    `level_id`         int       NOT NULL,
    `achieved_at`      timestamp NOT NULL,
    `perfect_shots`    int       DEFAULT 0,
    `created_at`       timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `vouchers`
(
    `voucher_id`     int PRIMARY KEY AUTO_INCREMENT,
    `code`           varchar(255) UNIQUE                               NOT NULL,
    `description`    text,
    `discount_type`  enum ('percentage', 'fixed_amount', 'free_hours') NOT NULL,
    `discount_value` decimal(10, 2)                                    NOT NULL,
    `start_date`     date                                              NOT NULL,
    `end_date`       date                                              NOT NULL,
    `target_type`    enum ('specific_users', 'all_users')              NOT NULL,
    `created_at`     timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `voucher_users`
(
    `voucher_user_id` int PRIMARY KEY AUTO_INCREMENT,
    `voucher_id`      int NOT NULL,
    `user_id`         int NOT NULL,
    `created_at`      timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `perfect_shots`
(
    `perfect_shot_id`      int PRIMARY KEY AUTO_INCREMENT,
    `user_id`              int NOT NULL,
    `open_table_ticket_id` int NOT NULL,
    `video_url`            varchar(255),
    `created_at`           timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           timestamp DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `clubs`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `club_chains`
    ADD FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`);

ALTER TABLE `club_chains`
    ADD FOREIGN KEY (`chain_id`) REFERENCES `chains` (`chain_id`);

ALTER TABLE `tables`
    ADD FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`);

ALTER TABLE `club_settings`
    ADD FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`);

ALTER TABLE `open_table_tickets`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `open_table_tickets`
    ADD FOREIGN KEY (`table_id`) REFERENCES `tables` (`table_id`);

ALTER TABLE `open_table_tickets`
    ADD FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`);

ALTER TABLE `invoices`
    ADD FOREIGN KEY (`open_table_ticket_id`) REFERENCES `open_table_tickets` (`open_table_ticket_id`);

ALTER TABLE `invoices`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `invoices`
    ADD FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`);

ALTER TABLE `invoice_details`
    ADD FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`invoice_id`);

ALTER TABLE `invoice_details`
    ADD FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);

ALTER TABLE `points`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `points`
    ADD FOREIGN KEY (`chain_id`) REFERENCES `chains` (`chain_id`);

ALTER TABLE `payment_transactions`
    ADD FOREIGN KEY (`open_table_ticket_id`) REFERENCES `open_table_tickets` (`open_table_ticket_id`);

ALTER TABLE `payment_transactions`
    ADD FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`payment_method_id`);

ALTER TABLE `topup_transactions`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `topup_transactions`
    ADD FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `user_level_history`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `user_level_history`
    ADD FOREIGN KEY (`level_id`) REFERENCES `user_levels` (`level_id`);

ALTER TABLE `voucher_users`
    ADD FOREIGN KEY (`voucher_id`) REFERENCES `vouchers` (`voucher_id`);

ALTER TABLE `voucher_users`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `perfect_shots`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `perfect_shots`
    ADD FOREIGN KEY (`open_table_ticket_id`) REFERENCES `open_table_tickets` (`open_table_ticket_id`);