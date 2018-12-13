CREATE TABLE IF NOT EXISTS `management` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(500) NOT NULL,
  `file_name` varchar(200) NOT NULL,
  `step` int(11) DEFAULT 1,
  `status` int(11) DEFAULT 1,
  `created_by` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `hash_file` varchar(200) NOT NULL,
  `duplicate_status` int(11) DEFAULT 0,
  `capture_status` int(11) DEFAULT 0,
  `present_user` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `invoice_data` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `management_id` int(11) DEFAULT NULL,
   `invoice_type_id` int(11) DEFAULT NULL,
   `customer_id` int(11) DEFAULT NULL,
   `customer_name` varchar(200) DEFAULT NULL,
   `invoice_name` varchar(200) DEFAULT NULL,
   `customer_id_level1` int(11) DEFAULT NULL,
   `customer_name_level1` int(11) DEFAULT NULL,
   `user_id` int(11) DEFAULT NULL,
   `user_name` varchar(200) DEFAULT NULL,
   `date_company_received` datetime DEFAULT NULL,
   `date_product_received` datetime DEFAULT NULL,
   `date_sent_late` int(11) DEFAULT 0,
   `notes` text DEFAULT NULL,  
  `product_ids` text DEFAULT NULL, 
  `product_names` text DEFAULT NULL, 
  `total_boxs` text DEFAULT NULL, 
  `quantitys` text DEFAULT NULL, 
  `total_prices` text DEFAULT NULL, 
  `sum_total_price` decimal(18,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `FK_invoice_data_management_id` (`management_id`),
  KEY `FK_invoice_data_user_idx` (`user_id`),
  KEY `FK_invoice_data_customer_idx` (`customer_id`),
  KEY `FK_invoice_data_customer1_idx` (`customer_id_level1`),
  KEY `FK_invoice_data_invoice_type_id` (`invoice_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;