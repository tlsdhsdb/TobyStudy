package com.example.toby;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
	private Long orderId;
	private String currency;
	private BigDecimal foreignCurrencyAccount;
	//돈의 경우, 정확한 계산을 위해 BigDecimal 사용
	private BigDecimal exRate;
	private BigDecimal convertedAmount;
	private LocalDateTime validUntil;

	public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAccount, BigDecimal exRate,
		BigDecimal convertedAmount, LocalDateTime validUntil) {
		this.orderId = orderId;
		this.currency = currency;
		this.foreignCurrencyAccount = foreignCurrencyAccount;
		this.exRate = exRate;
		this.convertedAmount = convertedAmount;
		this.validUntil = validUntil;
	}
	//빌더 패턴을 이용하는 이유 -> 생성자 파라미터 중간에 같은 타입의 데이터가 들어가면, 컴파일러에서는 문제를 잡아주지 않음
	//이러한 경우를 예방하기 위해 빌더패턴을 이용하여 안전하게 생성하는 방법이 있음

	public Long getOrderId() {
		return orderId;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getForeignCurrencyAccount() {
		return foreignCurrencyAccount;
	}

	public BigDecimal getExRate() {
		return exRate;
	}

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	@Override
	public String toString() {
		return "Payment{" +
			"orderId=" + orderId +
			", currency='" + currency + '\'' +
			", foreignCurrencyAccount=" + foreignCurrencyAccount +
			", exRate=" + exRate +
			", convertedAmount=" + convertedAmount +
			", validUntil=" + validUntil +
			'}';
	}
}
