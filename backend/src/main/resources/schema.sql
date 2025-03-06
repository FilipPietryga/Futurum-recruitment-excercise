DROP TABLE IF EXISTS campaign;

CREATE TABLE campaign (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bid_amount INT NOT NULL,
    campaign_fund INT NOT NULL,
    status BOOLEAN NOT NULL,
    town VARCHAR(255),
    radius INT,
    keywords VARCHAR(255)
);
