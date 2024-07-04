package com.example.toby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentService {
	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAccount) throws
		IOException {
		//환율 가져오기
		//https://open.er-api.com/v6/latest/USD

		URL url = new URL("https://open.er-api.com/v6/latest/" +currency);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String res = br.lines().collect(Collectors.joining());
		br.close();

		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(res,ExRateData.class);
		BigDecimal exRate = data.rates().get("KRW");
		System.out.println(exRate);
		//금액 계산
		BigDecimal convertedAmount = foreignCurrencyAccount.multiply(exRate);
		//유효 시간 계산
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);
		return new Payment(orderId,currency,foreignCurrencyAccount,exRate,convertedAmount, validUntil);
	}

	public static void main(String[] args) throws IOException {
		PaymentService paymentService = new PaymentService();
		Payment payment = paymentService.prepare(100L,"USD",BigDecimal.valueOf(50.7));
		System.out.println(payment);
	}
}
