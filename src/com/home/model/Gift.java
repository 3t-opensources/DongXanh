package com.home.model;

// Generated Feb 24, 2016 9:17:27 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Gift generated by hbm2java
 */
public class Gift implements java.io.Serializable {

	private Integer id;
	private String giftName;
	private Set<PromotionGift> promotionGifts = new HashSet<PromotionGift>(0);

	public Gift() {
	}

	public Gift(String giftName, Set<PromotionGift> promotionGifts) {
		this.giftName = giftName;
		this.promotionGifts = promotionGifts;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Set<PromotionGift> getPromotionGifts() {
		return this.promotionGifts;
	}

	public void setPromotionGifts(Set<PromotionGift> promotionGifts) {
		this.promotionGifts = promotionGifts;
	}

}
